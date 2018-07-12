/*     */ package com.mmiillkkaa.supernaturals.manager;
/*     */ 
/*     */ import com.mmiillkkaa.supernaturals.SuperNPlayer;
/*     */ import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
/*     */ import com.mmiillkkaa.supernaturals.io.SNConfigHandler;
/*     */ import com.mmiillkkaa.supernaturals.io.SNDataHandler;
/*     */ import com.mmiillkkaa.supernaturals.util.Language;
/*     */ import java.util.HashMap;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.World.Environment;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.PigZombie;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.entity.Wolf;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

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
/*     */ public class HumanManager
/*     */   extends ClassManager
/*     */ {
/*     */   private SupernaturalsPlugin plugin;
/*     */   
/*     */   public HumanManager(SupernaturalsPlugin instance)
/*     */   {
/*  43 */     this.plugin = instance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public HumanManager() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double victimEvent(EntityDamageEvent event, double damage)
/*     */   {
/*  58 */     return damage;
/*     */   }
/*     */   
/*     */   public double damagerEvent(EntityDamageByEntityEvent event, double damage)
/*     */   {
/*  63 */     return damage;
/*     */   }
/*     */   
/*     */   public void deathEvent(Player player)
/*     */   {
/*  68 */     if (player == null) {
/*  69 */       return;
/*     */     }
/*     */     
/*  72 */     SuperNPlayer snplayer = SuperNManager.get(player);
/*  73 */     LivingEntity lDamager = null;
/*  74 */     EntityDamageEvent e = player.getLastDamageCause();
/*     */     
/*  76 */     if (snplayer == null) {
/*  77 */       return;
/*     */     }
/*     */     
/*  80 */     if (this.plugin.getDataHandler().getApps().containsKey(snplayer)) {
/*  81 */       this.plugin.getDataHandler().removePlayerApp(snplayer);
/*     */     }
/*     */     
/*  84 */     if (e == null) {
/*  85 */       return;
/*     */     }
/*     */     
/*  88 */     if ((e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) && 
/*  89 */       (player.getItemInHand().getType().equals(Material.FEATHER))) {
/*  90 */       SuperNManager.sendMessage(snplayer, Language.HUMAN_TO_ANGEL.toString());
/*     */       
/*  92 */       SuperNManager.convert(snplayer, "angel", SNConfigHandler.angelPowerStart);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  97 */     if ((e.getCause().equals(EntityDamageEvent.DamageCause.LAVA)) || (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE)) || (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)))
/*     */     {
/*     */ 
/* 100 */       if ((player.getWorld().getEnvironment().equals(World.Environment.NETHER)) && (
/* 101 */         (this.plugin.getDemonManager().checkPlayerApp(player)) || (this.plugin.getDemonManager().checkInventory(player))))
/*     */       {
/* 103 */         SuperNManager.sendMessage(snplayer, Language.HUMAN_TO_DAEMON.toString());
/*     */         
/* 105 */         SuperNManager.convert(snplayer, "demon", SNConfigHandler.demonPowerStart);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 111 */     if ((e instanceof EntityDamageByEntityEvent)) {
/* 112 */       if ((((EntityDamageByEntityEvent)e).getDamager() instanceof LivingEntity)) {
/* 113 */         lDamager = (LivingEntity)((EntityDamageByEntityEvent)e).getDamager();
/*     */       }
/* 115 */       else if ((((EntityDamageByEntityEvent)e).getDamager() instanceof Projectile)) {
                ProjectileSource shooter=((Projectile)((EntityDamageByEntityEvent)e).getDamager()).getShooter();
/* 116 */         if(shooter instanceof LivingEntity){
                    lDamager= (LivingEntity) shooter;
            }else {
    return;
            }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 121 */     if (lDamager != null) {
/* 122 */       if ((player.getWorld().getEnvironment().equals(World.Environment.NETHER)) && 
/* 123 */         ((lDamager instanceof PigZombie))) {
/* 124 */         SuperNManager.convert(snplayer, "ghoul", SNConfigHandler.ghoulPowerStart);
/*     */         
/* 126 */         SuperNManager.sendMessage(snplayer, Language.HUMAN_TO_GHOUL.toString());
/*     */       }
/*     */       
/*     */ 
/* 130 */       if (((lDamager instanceof Wolf)) && 
/* 131 */         (!((Wolf)lDamager).isTamed()) && (SuperNManager.worldTimeIsNight(player)))
/*     */       {
/* 133 */         SuperNManager.convert(snplayer, "werewolf", SNConfigHandler.werePowerStart);
/*     */         
/* 135 */         SuperNManager.sendMessage(snplayer, Language.HUMAN_TO_WEREWOLF.toString());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void killEvent(Player pDamager, SuperNPlayer damager, SuperNPlayer victim) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean playerInteract(PlayerInteractEvent event)
/*     */   {
/* 153 */     return false;
/*     */   }
/*     */   
/*     */   public void armorCheck(Player player) {}
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\manager\HumanManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */