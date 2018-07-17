/*     */ package git.JackWisdom.mcp.supernaturals.listeners;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*     */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */ import git.JackWisdom.mcp.supernaturals.events.DemonFireBallDamageEvent;
import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
/*     */
/*     */
/*     */
/*     */ import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
/*     */ import git.JackWisdom.mcp.supernaturals.util.Armor;
/*     */ import git.JackWisdom.mcp.supernaturals.util.EntityUtil;
/*     */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*     */
/*     */
/*     */ import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Fireball;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Wolf;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityExplodeEvent;
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
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
/*     */ public class SNEntityListener
/*     */   implements Listener
/*     */ {
/*     */   private SupernaturalsPlugin plugin;
/*  47 */   private String worldPermission = "supernatural.world.enabled";
/*     */   
/*     */   public SNEntityListener(SupernaturalsPlugin instance) {
/*  50 */     this.plugin = instance;
/*     */   }
/*     */   
/*     */   @EventHandler(priority=EventPriority.NORMAL)
/*     */   public void onEntityShootBowzBroLol(EntityShootBowEvent event) {
/*  55 */     if (event.isCancelled())
/*     */     {
/*  57 */       return;
/*     */     }
/*  59 */     if (!(event.getEntity() instanceof Player)) {
/*  60 */       return;
/*     */     }
/*  62 */     Player shooter = (Player)event.getEntity();
/*  63 */     boolean cancel = SuperNManager.get(shooter).getType().getManager().shootArrow(shooter, event);
/*     */     
/*  65 */     event.setCancelled(cancel);
/*     */   }
/*
    @EventHandler
            public void onDemonExplode(EntityDamageByEntityEvent event){
            if(event.getCause()!=EntityDamageEvent.DamageCause.ENTITY_EXPLOSION){
                return;
            }
            if(!(event.getDamager() instanceof Fireball)){
                return;
            }
            if(!(event.getEntity() instanceof LivingEntity)){
                return;
            }
            LivingEntity entity= (LivingEntity) event.getEntity();
            Fireball ball= (Fireball) event.getDamager();
            if(!(ball.getShooter() instanceof Player)){return;}
            Player p= (Player) ball.getShooter();
            if(SuperNManager.get(p)==null||!SuperNManager.get(p).isDemon()){
                return;
            }//now the shooter is demon and shoot a living entity




            }
             */
    //恶魔火球
    @EventHandler(priority=EventPriority.NORMAL)
   public void onEntityDemonExplode(EntityExplodeEvent event) {
    Fireball fireball;
      if (!(event.getEntity() instanceof Fireball)) { return;}
       fireball = (Fireball)event.getEntity();
        if (!(fireball.getShooter() instanceof Player)) {
            return;
        }
        Player shooter=((Player)fireball.getShooter());
        if(!SuperNManager.get(shooter).isDemon()){
            return;
        }
          if ((!shooter.hasPermission(this.worldPermission)) && (SNConfigHandler.multiworld))
         {
           return;
         }
       for (Entity entity : fireball.getNearbyEntities(3.0D, 3.0D, 3.0D)){
           if ((!(entity instanceof LivingEntity))) {
               continue;
           }
           if ((entity instanceof Player)) {
               Player player = (Player)entity;
               SuperNPlayer snplayer = SuperNManager.get(player);
                if(snplayer.isDemon()){
                    continue;
                }
           }
           LivingEntity lEntity = (LivingEntity)entity;
           DemonFireBallDamageEvent ie=new DemonFireBallDamageEvent(shooter,lEntity,SNConfigHandler.demonFireballDamage,SNConfigHandler.demonFireTicks);
           Bukkit.getPluginManager().callEvent(ie);
           if(ie.isCancelled()){
               continue;
           }
           lEntity.damage(ie.getDamage());
           lEntity.setFireTicks(ie.getFireTick());



        }
    }

/*     */   
/*     */   @EventHandler(priority=EventPriority.NORMAL)
/*     */   public void onEntityDamage(EntityDamageEvent event) {
/* 102 */     if (event.isCancelled()) {
/* 103 */       return;
/*     */     }
/*     */     
/* 106 */     Entity victim = event.getEntity();
/* 107 */     double damage = event.getDamage();
/*     */     
/*     */ 
/* 110 */     if ((event instanceof EntityDamageByEntityEvent)) {
/* 111 */       EntityDamageByEntityEvent edbeEvent = (EntityDamageByEntityEvent)event;
/* 112 */       if (((edbeEvent.getDamager() instanceof Player)) && ((victim instanceof Player)))
/*     */       {
/* 114 */         Player pVictim = (Player)victim;
/* 115 */         SuperNManager.get(pVictim).getManager().spellEvent(edbeEvent, pVictim);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 120 */     if ((event instanceof EntityDamageByEntityEvent)) {
/* 121 */       EntityDamageByEntityEvent edbeEvent = (EntityDamageByEntityEvent)event;
/* 122 */       Entity damager = edbeEvent.getDamager();
/*     */       
/* 124 */       if ((damager instanceof Player)) {
/* 125 */         if ((!((Player)damager).hasPermission( this.worldPermission)) && (SNConfigHandler.multiworld))
/*     */         {
/* 127 */           return;
/*     */         }
/*     */         
/* 130 */         damage = SuperNManager.get(((Player)damager)).getManager().damagerEvent(edbeEvent, damage);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 136 */     if ((victim instanceof Player)) {
/* 137 */       Player pVictim = (Player)victim;
/* 138 */       if ((!pVictim.hasPermission( this.worldPermission)) && (SNConfigHandler.multiworld))
/*     */       {
/* 140 */         return;
/*     */       }
/* 142 */       damage = SuperNManager.get(pVictim).getManager().victimEvent(event, damage);
/*     */       
/* 144 */       SuperNPlayer snvictim = SuperNManager.get(pVictim);
/*     */       
/* 146 */       if (this.plugin.getGhoulManager().checkBond(pVictim)) {
/* 147 */         double damageAfterArmor = Armor.getReducedDamage(pVictim, (int)Math.round(damage));
/*     */         
/* 149 */         if (damageAfterArmor > 1.0D) {
/* 150 */           damage /= 2.0D;
/* 151 */           damageAfterArmor /= 2.0D;
/* 152 */           SuperNPlayer ghoul = this.plugin.getGhoulManager().getGhoul(snvictim);
/*     */           
/* 154 */           Player gPlayer = this.plugin.getServer().getPlayer(ghoul.getName());
/*     */           
/* 156 */           double ghoulDamage = damageAfterArmor;
/* 157 */           ghoulDamage -= ghoulDamage * ghoul.scale(1.0D - SNConfigHandler.ghoulDamageReceivedFactor);
/*     */           
/* 159 */           int health = (int)(gPlayer.getHealth() - ghoulDamage);
/* 160 */           if (health < 0) {
/* 161 */             health = 0;
/*     */           }
/* 163 */           gPlayer.setHealth(health);
/* 164 */           SuperNManager.alterPower(ghoul, -SNConfigHandler.ghoulPowerBond, Language.GHOUL_BOND_TRIGGER.toString());
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 170 */       if (this.plugin.getDataHandler().hasAngel(snvictim)) {
/* 171 */         double damageAfterArmor = Armor.getReducedDamage(pVictim, (int)Math.round(damage));
/*     */         
/* 173 */         if (pVictim.getHealth() - damageAfterArmor <= 0.0D) {
/* 174 */           SuperNManager.sendMessage(snvictim, Language.PRIEST_GUARDANGEL_TRIGGER.toString());
/*     */           
/* 176 */           this.plugin.getDataHandler().removeAngel(snvictim);
/* 177 */           pVictim.setHealth(20.0D);
/* 178 */           event.setDamage(0.0D);
/* 179 */           event.setCancelled(true);
/* 180 */           return;
/*     */         }
/*     */       }
/*     */     }
/* 184 */     event.setDamage((int)Math.round(damage));
/*     */   }
/*     */   
/*     */   @EventHandler(priority=EventPriority.NORMAL)
/*     */   public void onEntityTarget(EntityTargetEvent event) {
/* 189 */     if (event.isCancelled()) {
/* 190 */       return;
/*     */     }
/* 192 */     if (!(event.getTarget() instanceof Player)) {
/* 193 */       return;
/*     */     }
/*     */     
/* 196 */     if (event.getEntity() == null) {
/* 197 */       return;
/*     */     }
/*     */     
/* 200 */     if ((!((Player)event.getTarget()).hasPermission( this.worldPermission)) && (SNConfigHandler.multiworld))
/*     */     {

/* 202 */       return;
/*     */     }
/*     */     
/* 205 */     SuperNPlayer snplayer = SuperNManager.get((Player)event.getTarget());
/*     */     
/* 207 */     if (!snplayer.getTruce()) {
/* 208 */       return;
/*     */     }
/*     */     
/* 211 */     if (EntityUtil.entityTypeFromEntity(event.getEntity()) == null) {
/* 212 */       return;
/*     */     }
/*     */     
/* 215 */     if ((snplayer.isVampire()) && (SNConfigHandler.vampireTruce.contains(EntityUtil.entityTypeFromEntity(event.getEntity()))))
/*     */     {
/*     */ 
/* 218 */       event.setCancelled(true);
/* 219 */     } else if ((snplayer.isGhoul()) && (SNConfigHandler.ghoulTruce.contains(EntityUtil.entityTypeFromEntity(event.getEntity()))))
/*     */     {
/*     */ 
/* 222 */       event.setCancelled(true);
/* 223 */     } else if ((snplayer.isWere()) && (SNConfigHandler.wolfTruce) && ((event.getEntity() instanceof Wolf)))
/*     */     {
/* 225 */       event.setCancelled(true);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\listeners\SNEntityListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */