/*     */ package git.JackWisdom.mcp.supernaturals.manager;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*     */ import git.JackWisdom.mcp.supernaturals.SuperType;
import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */ import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
/*     */
/*     */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*     */
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */
/*     */ import org.bukkit.entity.*;
/*     */
/*     */
/*     */
/*     */
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
/*     */
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.util.List;

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

    @Override
    public void eatItem(PlayerItemConsumeEvent event) {

    }

    /*     */
/*     */   public double damagerEvent(EntityDamageByEntityEvent event, double damage)
/*     */   {
/*  63 */     return damage;
/*     */   }

    @Override
    public void waterAdvanceTime(Player player) {

    }



    @Override
    public boolean shootArrow(Player shooter, EntityShootBowEvent event) {
        return false;
    }

    @Override
    public void spellEvent(EntityDamageByEntityEvent event, Player target) {

    }

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
/*  80 */     snplayer.getHuntApp().clear();
/*     */     
/*  84 */     if (e == null) {
/*  85 */       return;
/*     */     }
/*     */     
/*  88 */     if ((e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) && 
/*  89 */       (player.getItemInHand().getType().equals(Material.FEATHER))) {
/*  90 */       SuperNManager.sendMessage(snplayer, Language.HUMAN_TO_ANGEL.toString());
/*  92 */       SuperNManager.convert(snplayer, SuperType.ANGEL, SNConfigHandler.angelPowerStart);
/*     */     }
/*  97 */     if ((e.getCause().equals(EntityDamageEvent.DamageCause.LAVA)) || (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE)) || (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)))
/*     */     {
/*     */ 
/* 100 */       if ((player.getWorld().getEnvironment().equals(World.Environment.NETHER)) && (
/* 101 */         (this.plugin.getDemonManager().checkPlayerApp(player)) || (this.plugin.getDemonManager().checkInventory(player))))
/*     */       {
/* 103 */         SuperNManager.sendMessage(snplayer, Language.HUMAN_TO_DAEMON.toString());
/*     */         
/* 105 */         SuperNManager.convert(snplayer, SuperType.DEMON, SNConfigHandler.demonPowerStart);
/*     */       }
/*     */     }
/* 111 */     if ((e instanceof EntityDamageByEntityEvent)) {
/* 112 */       if ((((EntityDamageByEntityEvent)e).getDamager() instanceof LivingEntity)) {
/* 113 */         lDamager = (LivingEntity)((EntityDamageByEntityEvent)e).getDamager();
/*     */       }
/* 115 */       else if ((((EntityDamageByEntityEvent)e).getDamager() instanceof Projectile)) {

                ProjectileSource shooter=((Projectile)((EntityDamageByEntityEvent)e).getDamager()).getShooter();
/* 116 */         if(shooter instanceof LivingEntity){
                    lDamager= (LivingEntity) shooter;
                    //memaid
                    if(((EntityDamageByEntityEvent)e).getDamager()  instanceof Trident&&player.getWorld().hasStorm()){
                        boolean isInWater= player.getLocation().getBlock().getType()==Material.WATER;
                        List<Entity> entities=player.getNearbyEntities(3,3,3);
                        int dophant=0;
                        for(Entity ent:entities){
                            if(ent instanceof Dolphin){
                                dophant=dophant+1;
                            }
                        }
                        boolean isDophinNearBy3=dophant>=3;
                        if(isInWater&&isDophinNearBy3){
                            SuperNManager.convert(snplayer, SuperType.MERMAID, SNConfigHandler.mermaidPowerStart);
                        }
                    }
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
/* 124 */         SuperNManager.convert(snplayer, SuperType.GHOUL, SNConfigHandler.ghoulPowerStart);
/*     */         
/* 126 */         SuperNManager.sendMessage(snplayer, Language.HUMAN_TO_GHOUL.toString());
/*     */       }
/*     */       
/*     */ 
/* 130 */       if (((lDamager instanceof Wolf)) && 
/* 131 */         (!((Wolf)lDamager).isTamed()) && (SuperNManager.worldTimeIsNight(player)))
/*     */       {
/* 133 */         SuperNManager.convert(snplayer, SuperType.WEREWOLF, SNConfigHandler.werePowerStart);
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