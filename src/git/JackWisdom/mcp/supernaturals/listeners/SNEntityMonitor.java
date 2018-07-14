/*     */ package git.JackWisdom.mcp.supernaturals.listeners;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*     */ import git.JackWisdom.mcp.supernaturals.SuperType;
import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */ import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
/*     */
/*     */
/*     */
/*     */ import git.JackWisdom.mcp.supernaturals.manager.HunterManager;
import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
/*     */ import git.JackWisdom.mcp.supernaturals.manager.WereManager;
/*     */ import git.JackWisdom.mcp.supernaturals.util.EntityUtil;
/*     */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*     */ import java.util.ArrayList;
import java.util.HashSet;
/*     */
/*     */
/*     */
import org.bukkit.Material;
/*     */
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.Hash;
import org.bukkit.entity.*;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;

/*     */ import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
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
    /*     */
    public SNEntityMonitor(SupernaturalsPlugin instance) {
        /*  57 */
        this.plugin = instance;
        /*     */
    }

    /*     */
    /*     */
    @EventHandler(priority = EventPriority.MONITOR)
    /*     */ public void onProjectileHit(ProjectileHitEvent event) {
        /*  62 */
        if ((event.getEntity() instanceof Arrow)) {
            /*  63 */
            Arrow arrow = (Arrow) event.getEntity();
            /*  64 */
            if (!this.plugin.getHunterManager().getArrowMap().containsKey(arrow)) {
                return;
            }
            if (!(arrow.getShooter() instanceof Player)) {
                return;
            }
            /*  65 */
            Player player = (Player) arrow.getShooter();
            /*  66 */
            if ((!player.hasPermission(worldPermission)) && (SNConfigHandler.multiworld))
                /*     */ {
                /*     */
                /*  69 */
                return;
                /*     */
            }
            /*  71 */
            HunterManager.ArrowType arrowType = this.plugin.getHunterManager().getArrowMap().get(arrow);
            /*     */
            /*  73 */
            if (arrowType == HunterManager.ArrowType.TRIPLE) {
                /*  74 */
                player.teleport(arrow.getLocation());
                /*  75 */
            } else if (arrowType == HunterManager.ArrowType.FIRE) {
                /*  76 */
                arrow.getLocation();
                /*  77 */
                Block block = arrow.getWorld().getBlockAt(arrow.getLocation());
                /*     */
                /*  79 */
                if ((block != null) &&
                        /*  80 */             (SNConfigHandler.burnableBlocks.contains(block.getType())))
                    /*     */ {
                    /*  82 */
                    block.setType(Material.FIRE);
                    /*     */
                }
                /*     */
            }
            /*     */
            /*  86 */
            this.plugin.getHunterManager().removeArrow(arrow);
            /*     */
            /*     */
        }
        /*     */
    }

    /*     */
    /*     */
    @EventHandler(priority = EventPriority.MONITOR)
    /*     */ public void onEntityDamage(EntityDamageByEntityEvent event) {
        /*  93 */
        if (event.isCancelled()) {
            /*  94 */
            return;
            /*     */
        }

        /* 100 */
        Entity victim = event.getEntity();
        /*     */
        /* 102 */
        Entity damager = event.getDamager();
        /* 103 */
        Player pDamager = null;
        /*     */
        /*     */
        /* 106 */
        if ((damager instanceof Projectile)) {
            /* 107 */
            if ((((Projectile) damager).getShooter() instanceof Player)) {
                /* 108 */
                pDamager = (Player) ((Projectile) damager).getShooter();
                /*     */
            }
            /* 110 */
        } else if ((damager instanceof Player)) {
            /* 111 */
            pDamager = (Player) damager;
            /*     */
        }
        /* 113 */
        if (damager == null) {
            /* 114 */
            return;
            /*     */
        }
        /* 116 */
        if (pDamager == null) {
            /* 117 */
            return;
            /*     */
        }
        /* 119 */
        SuperNPlayer snDamager = SuperNManager.get(pDamager);
        /*     */
        /* 121 */
        if ((victim instanceof Creature)) {
            /* 122 */
            Creature cVictim = (Creature) victim;
            /*     */
            /*     */
            /* 125 */
            if ((snDamager.isVampire()) && (SNConfigHandler.vampireTruce.contains(EntityUtil.entityTypeFromEntity(cVictim))))
                /*     */ {
                /*     */
                /* 128 */
                this.plugin.getSuperManager().truceBreak(snDamager);
                /* 129 */
            } else if ((snDamager.isGhoul()) && (SNConfigHandler.ghoulTruce.contains(EntityUtil.entityTypeFromEntity(cVictim))))
                /*     */ {
                /*     */
                /* 132 */
                this.plugin.getSuperManager().truceBreak(snDamager);
                /*     */
            }
            /*     */
        }
        /*     */
        /*     */
    }

    /*     */
    /*     */
    @EventHandler(priority = EventPriority.MONITOR)
    /*     */ public void onEntityDeath(EntityDeathEvent event) {
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }
        LivingEntity entity = (LivingEntity) event.getEntity();
        if (entity.getKiller() == null || !(entity.getKiller() instanceof LivingEntity)) {
            return;
        }
        Player pDamager = null;
        /* 143 */
        Entity lDamager = event.getEntity().getKiller();

        /*     */
        /* 155 */
        if ((lDamager instanceof Player)) {
            /* 156 */
            pDamager = (Player) lDamager;
            /*     */
        }
        /*     */
        /* 159 */
        if (((entity instanceof Monster)) &&
                /* 160 */       (pDamager != null)) {
            /* 161 */
            SuperNPlayer snplayer = SuperNManager.get(pDamager);
            /* 162 */
            if (snplayer.isAngel()) {
                /* 163 */
                SuperNManager.alterPower(snplayer, SNConfigHandler.angelKillMonsterPowerGain, Language.KILL_MONSTER.toString());
                /*     */
            }
            /*     */
        }
        /*     */
        /*     */
        /*     */
        /*     */
        /* 170 */
        if ((entity instanceof Wolf)) {
            /* 171 */
            WereManager.removeWolf((Wolf) entity);
            /*     */
        }
        /*     */
        /* 174 */
        if (((entity instanceof Creature)) &&
                /* 175 */       (pDamager != null)) {
            /* 176 */
            if ((!pDamager.hasPermission(this.worldPermission)) && (SNConfigHandler.multiworld))
                /*     */ {
                /* 178 */
                return;
                /*     */
            }
            /* 180 */
            SuperNPlayer snDamager = SuperNManager.get(pDamager);
            /* 181 */
            SuperNManager.get(pDamager).getManager().killEvent(pDamager, snDamager, null);
            /*     */
        }

        /*     */
    }
}


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\listeners\SNEntityMonitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */