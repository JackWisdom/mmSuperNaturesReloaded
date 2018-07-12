/*     */ package com.mmiillkkaa.supernaturals.listeners;
/*     */ 
/*     */ import com.mmiillkkaa.supernaturals.SuperNPlayer;
/*     */ import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
/*     */ import com.mmiillkkaa.supernaturals.io.SNConfigHandler;
/*     */ import com.mmiillkkaa.supernaturals.io.SNDataHandler;
/*     */ import com.mmiillkkaa.supernaturals.manager.ClassManager;
/*     */ import com.mmiillkkaa.supernaturals.manager.HunterManager;
/*     */ import com.mmiillkkaa.supernaturals.manager.SuperNManager;
/*     */ import com.mmiillkkaa.supernaturals.manager.WereManager;
/*     */ import com.mmiillkkaa.supernaturals.util.EntityUtil;
/*     */ import com.mmiillkkaa.supernaturals.util.Language;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.Creature;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Monster;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.entity.Wolf;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;

/*     */ import org.bukkit.event.entity.ProjectileHitEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SNEntityMonitor
/*     */   implements Listener
/*     */ {
/*     */   private SupernaturalsPlugin plugin;
/*  54 */   private String worldPermission = "supernatural.world.enabled";
/*     */   
/*     */   public SNEntityMonitor(SupernaturalsPlugin instance) {
/*  57 */     this.plugin = instance;
/*     */   }
/*     */   
/*     */   @EventHandler(priority=EventPriority.MONITOR)
/*     */   public void onProjectileHit(ProjectileHitEvent event) {
/*  62 */     if ((event.getEntity() instanceof Arrow)) {
/*  63 */       Arrow arrow = (Arrow)event.getEntity();
/*  64 */       if (!this.plugin.getHunterManager().getArrowMap().containsKey(arrow)) {  return;}
                if(!(arrow.getShooter() instanceof Player)){
    return;
                }
/*  65 */         Player player = (Player)arrow.getShooter();
/*  66 */         if ((!SupernaturalsPlugin.hasPermissions(player, this.worldPermission)) && (SNConfigHandler.multiworld))
/*     */         {
/*     */ 
/*  69 */           return;
/*     */         }
/*  71 */         String arrowType = (String)this.plugin.getHunterManager().getArrowMap().get(arrow);
/*     */         
/*  73 */         if (arrowType.equalsIgnoreCase("grapple")) {
/*  74 */           player.teleport(arrow.getLocation());
/*  75 */         } else if (arrowType.equalsIgnoreCase("fire")) {
/*  76 */           arrow.getLocation();
/*  77 */           Block block = arrow.getWorld().getBlockAt(arrow.getLocation());
/*     */           
/*  79 */           if ((block != null) && 
/*  80 */             (SNConfigHandler.burnableBlocks.contains(block.getType())))
/*     */           {
/*  82 */             block.setType(Material.FIRE);
/*     */           }
/*     */         }
/*     */         
/*  86 */         this.plugin.getHunterManager().removeArrow(arrow);
/*     */
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler(priority=EventPriority.MONITOR)
/*     */   public void onEntityDamage(EntityDamageByEntityEvent event) {
/*  93 */     if (event.isCancelled()) {
/*  94 */       return;
/*     */     }

/* 100 */       Entity victim = event.getEntity();
/*     */       
/* 102 */       Entity damager = event.getDamager();
/* 103 */       Player pDamager = null;
/*     */       
/*     */ 
/* 106 */       if ((damager instanceof Projectile)) {
/* 107 */         if ((((Projectile)damager).getShooter() instanceof Player)) {
/* 108 */           pDamager = (Player)((Projectile)damager).getShooter();
/*     */         }
/* 110 */       } else if ((damager instanceof Player)) {
/* 111 */         pDamager = (Player)damager;
/*     */       }
/* 113 */       if (damager == null) {
/* 114 */         return;
/*     */       }
/* 116 */       if (pDamager == null) {
/* 117 */         return;
/*     */       }
/* 119 */       SuperNPlayer snDamager = SuperNManager.get(pDamager);
/*     */       
/* 121 */       if ((victim instanceof Creature)) {
/* 122 */         Creature cVictim = (Creature)victim;
/*     */         
/*     */ 
/* 125 */         if ((snDamager.isVampire()) && (SNConfigHandler.vampireTruce.contains(EntityUtil.entityTypeFromEntity(cVictim))))
/*     */         {
/*     */ 
/* 128 */           this.plugin.getSuperManager().truceBreak(snDamager);
/* 129 */         } else if ((snDamager.isGhoul()) && (SNConfigHandler.ghoulTruce.contains(EntityUtil.entityTypeFromEntity(cVictim))))
/*     */         {
/*     */ 
/* 132 */           this.plugin.getSuperManager().truceBreak(snDamager);
/*     */         }
/*     */       }
/*     */
/*     */   }
/*     */   
/*     */   @EventHandler(priority=EventPriority.MONITOR)
/*     */   public void onEntityDeath(EntityDamageByEntityEvent event) {
/* 140 */     Entity entity = event.getEntity();
                Player pDamager = null;
/* 143 */     Entity lDamager=event.getDamager();
/*     */     
/*     */ 
/* 155 */     if ((lDamager instanceof Player)) {
/* 156 */       pDamager = (Player)lDamager;
/*     */     }
/*     */     
/* 159 */     if (((entity instanceof Monster)) && 
/* 160 */       (pDamager != null)) {
/* 161 */       SuperNPlayer snplayer = SuperNManager.get(pDamager);
/* 162 */       if (snplayer.isAngel()) {
/* 163 */         SuperNManager.alterPower(snplayer, SNConfigHandler.angelKillMonsterPowerGain, Language.KILL_MONSTER.toString());
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 170 */     if ((entity instanceof Wolf)) {
/* 171 */       WereManager.removeWolf((Wolf)entity);
/*     */     }
/*     */     
/* 174 */     if (((entity instanceof Creature)) && 
/* 175 */       (pDamager != null)) {
/* 176 */       if ((!SupernaturalsPlugin.hasPermissions(pDamager, this.worldPermission)) && (SNConfigHandler.multiworld))
/*     */       {
/* 178 */         return;
/*     */       }
/* 180 */       SuperNPlayer snDamager = SuperNManager.get(pDamager);
/* 181 */       SupernaturalsPlugin.instance.getClassManager(pDamager).killEvent(pDamager, snDamager, null);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 186 */     if (!(entity instanceof Player)) {
/* 187 */       return;
/*     */     }
/*     */     
/* 190 */     Player pVictim = (Player)entity;
/*     */     
/* 192 */     if ((!SupernaturalsPlugin.hasPermissions(pVictim, this.worldPermission)) && (SNConfigHandler.multiworld))
/*     */     {
/* 194 */       return;
/*     */     }
/*     */     
/* 197 */     if (!pVictim.isOnline()) {
/* 198 */       return;
/*     */     }
/*     */     
/* 201 */     SuperNPlayer snplayer = SuperNManager.get(pVictim);
/*     */     
/* 203 */     if (lDamager != null) {
/* 204 */       if ((lDamager instanceof Player)) {
/* 205 */         pDamager = (Player)lDamager;
/* 206 */         SuperNPlayer snDamager = SuperNManager.get(pDamager);
/* 207 */         if (snplayer.isHunter()) {
/* 208 */           if (snDamager.equals(snplayer)) {
/* 209 */             SuperNManager.sendMessage(snplayer, Language.KILL_SELF.toString());
/*     */             
/* 211 */             SuperNManager.sendMessage(snplayer, Language.WITCHHUNTER_KILL_SELF.toString());
/*     */             
/* 213 */             SuperNManager.cure(snplayer);
/*     */           }
/* 215 */         } else if (snDamager.isHuman()) {
/* 216 */           ArrayList<String> supersKilled = new ArrayList();
/* 217 */           if (this.plugin.getDataHandler().playerHasApp(snDamager)) {
/* 218 */             supersKilled = this.plugin.getDataHandler().getPlayerApp(snDamager);
/*     */             
/* 220 */             if (!supersKilled.contains(snplayer.getType())) {
/* 221 */               supersKilled.add(snplayer.getType());
/* 222 */               if (supersKilled.size() >= 3) {
/* 223 */                 this.plugin.getHunterManager().invite(snDamager);
/*     */               }
/*     */             }
/*     */           } else {
/* 227 */             supersKilled.add(snplayer.getType());
/*     */           }
/* 229 */           this.plugin.getDataHandler().addPlayerApp(snDamager, supersKilled);
/*     */         }
/*     */         
/* 232 */         SupernaturalsPlugin.instance.getClassManager(pDamager).killEvent(pDamager, snDamager, snplayer);
/*     */       }
/* 234 */       else if ((lDamager instanceof Wolf)) {
/* 235 */         Wolf wolf = (Wolf)lDamager;
/* 236 */         if (!wolf.isTamed()) {
/* 237 */           SupernaturalsPlugin.instance.getClassManager(pVictim).deathEvent(pVictim);
/*     */           
/* 239 */           return;
/*     */         }
/* 241 */         if (!(wolf.getOwner() instanceof Player)) {
/* 242 */           SupernaturalsPlugin.instance.getClassManager(pVictim).deathEvent(pVictim);
/*     */           
/* 244 */           return;
/*     */         }
/* 246 */         pDamager = (Player)wolf.getOwner();
/* 247 */         SuperNPlayer snDamager = SuperNManager.get(pDamager);
/* 248 */         SupernaturalsPlugin.instance.getClassManager(pDamager).killEvent(pDamager, snDamager, snplayer);
/*     */       }
/*     */     }
/*     */     
/* 252 */     SupernaturalsPlugin.instance.getClassManager(pVictim).deathEvent(pVictim);
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\listeners\SNEntityMonitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */