/*     */ package git.JackWisdom.mcp.supernaturals.manager;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*     */ import git.JackWisdom.mcp.supernaturals.SuperType;
import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */
import git.JackWisdom.mcp.supernaturals.inventory.VampCureGui;
import git.JackWisdom.mcp.supernaturals.inventory.VampInfectGui;
import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
/*     */
/*     */ import git.JackWisdom.mcp.supernaturals.util.GeometryUtil;
/*     */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*     */ import git.JackWisdom.mcp.supernaturals.util.LanguageTag;
/*     */
/*     */ import java.util.ArrayList;
/*     */
/*     */
/*     */ import org.bukkit.Bukkit;
import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */
/*     */
/*     */
/*     */ import org.bukkit.block.Block;
/*     */
/*     */
/*     */ import org.bukkit.entity.*;
/*     */
/*     */
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.material.Door;
import org.bukkit.scheduler.BukkitRunnable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VampireManager
/*     */   extends ClassManager
/*     */ {
/*  55 */   private String permissions = "supernatural.player.preventsundamage";
/*  56 */   public SupernaturalsPlugin plugin = SupernaturalsPlugin.instance;
/*  57 */   private ArrayList<Location> hallDoors = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double victimEvent(EntityDamageEvent event, double damage)
/*     */   {
/*  65 */     Player victim = (Player)event.getEntity();
/*  66 */     SuperNPlayer snVictim = SuperNManager.get(victim);
/*  67 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.DROWNING)) {
/*  68 */       if (snVictim.getPower() > SNConfigHandler.vampireDrowningCost) {
/*  69 */         SuperNManager.alterPower(snVictim, -SNConfigHandler.vampireDrowningCost, Language.VAMPIRE_HATE_WATER.toString());
/*     */         
/*     */ 
/*  72 */         event.setCancelled(true);
/*  73 */         return 0.0D;
/*     */       }
/*  75 */       SuperNManager.sendMessage(snVictim, Language.VAMPIRE_WATER_DAMAGE_NOTICE.toString());
/*     */       
/*  77 */       return damage;
/*     */     }
/*  79 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
/*  80 */       event.setCancelled(true);
/*  81 */       return 0.0D; }
/*  82 */     if ((event instanceof EntityDamageByEntityEvent)) {
/*  83 */       EntityDamageByEntityEvent edbeEvent = (EntityDamageByEntityEvent)event;
/*  84 */       Entity damager = edbeEvent.getDamager();
/*  85 */       if ((damager instanceof Projectile))
/*  86 */         return damage;
/*  87 */       if ((damager instanceof Player)) {
/*  88 */         Player pDamager = (Player)damager;
/*  89 */         ItemStack item = pDamager.getItemInHand();
/*     */         
/*  91 */         if (item != null) {
/*  92 */           if (SNConfigHandler.woodMaterials.contains(item.getType())) {
/*  93 */             damage += damage * SNConfigHandler.woodFactor;
/*  94 */             SuperNManager.sendMessage(snVictim, Language.VAMPIRE_HATE_WOOD.toString());
/*     */           }
/*     */           else {
/*  97 */             damage -= damage * snVictim.scale(1.0D - SNConfigHandler.vampireDamageReceivedFactor);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 104 */     return damage;
/*     */   }

    @Override
    public void eatItem(PlayerItemConsumeEvent event) {
        /* 213 */       if (SNConfigHandler.foodMaterials.contains(event.getItem().getType())) {
            /* 214 */        event.getPlayer().sendMessage(Language.VAMPIRE_LIMIT_EAT.toString());
            /* 216 */        event.getPlayer().setFoodLevel(0);
        }
    }

    /*     */
/*     */   public void deathEvent(Player player)
/*     */   {
/* 109 */     SuperNPlayer snplayer = SuperNManager.get(player);
/*     */     
/* 111 */     SuperNManager.alterPower(snplayer, -SNConfigHandler.vampireDeathPowerPenalty, Language.YOU_DIE.toString());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void killEvent(Player pDamager, SuperNPlayer damager, SuperNPlayer victim)
/*     */   {
/* 119 */     if (victim == null) {
/* 120 */       pDamager.setFoodLevel(pDamager.getFoodLevel() + SNConfigHandler.vampireHungerRegainMob);
/*     */       
/* 122 */       SuperNManager.alterPower(damager, SNConfigHandler.vampireKillPowerCreatureGain, Language.KILL_CREATURE.toString());
/*     */     }
/*     */     else
/*     */     {
/* 126 */       if (!victim.isSuper()) {
/* 127 */         pDamager.setFoodLevel(pDamager.getFoodLevel() + SNConfigHandler.vampireHungerRegainPlayer);
/*     */       }
/*     */       
/* 130 */       double random = Math.random();
/* 131 */       if (victim.getPower() > SNConfigHandler.vampireKillPowerPlayerGain) {
/* 132 */         SuperNManager.alterPower(damager, SNConfigHandler.vampireKillPowerPlayerGain, Language.KILL_PLAYER.toString());
/*     */       }
/*     */       else
/*     */       {
/* 136 */         SuperNManager.sendMessage(damager, Language.NO_POWER_GAIN.toString());
/*     */       }
/*     */       
/* 139 */       if ((SNConfigHandler.vampireKillSpreadCurse) && (!victim.isSuper()) && 
/* 140 */         (random < SNConfigHandler.spreadChance)) {
/* 141 */         SuperNManager.sendMessage(victim, Language.VAMPIRE_DEATH.toString());
/*     */         
/* 143 */         SuperNManager.convert(victim, SuperType.VAMPIRE);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public double damagerEvent(EntityDamageByEntityEvent event, double damage)
/*     */   {
/* 151 */     Entity damager = event.getDamager();
/* 152 */     Player pDamager = (Player)damager;
/* 153 */     SuperNPlayer snDamager = SuperNManager.get(pDamager);
/*     */     
/* 155 */     ItemStack item = pDamager.getItemInHand();
/*     */     
/* 157 */     if ((item != null) && 
/* 158 */       (SNConfigHandler.vampireWeapons.contains(item.getType()))) {
/* 159 */       SuperNManager.sendMessage(snDamager, Language.VAMPIRE_LIMIT_WEAPON.toString());
/*     */       
/* 161 */       return 0.0D;
/*     */     }
/*     */     
/*     */ 
/* 165 */     damage += damage * snDamager.scale(SNConfigHandler.vampireDamageFactor);
/* 166 */     return damage;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean playerInteract(PlayerInteractEvent event)
/*     */   {
/* 176 */     Action action = event.getAction();
/* 177 */     Player player = event.getPlayer();
/* 178 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 179 */     Material itemMaterial = event.getMaterial();
/*     */     
/* 181 */     if ((action.equals(Action.LEFT_CLICK_AIR)) || (action.equals(Action.LEFT_CLICK_BLOCK)))
/*     */     {
/* 183 */       if (player.getItemInHand() == null) {
/* 184 */         return false;
/*     */       }
/*     */       
/* 187 */       if (itemMaterial.equals(SNConfigHandler.vampireJumpMaterial))
/*     */       {
/* 189 */         SuperNManager.jump(player, SNConfigHandler.jumpDeltaSpeed, true);
/*     */         
/* 191 */         event.setCancelled(true);
/* 192 */         return true; }
/* 193 */       if (itemMaterial.equals(SNConfigHandler.vampireMaterial))
/*     */       {
/* 195 */         teleport(player);
/* 196 */         event.setCancelled(true);
/* 197 */         return true;
/*     */       }
/*     */     }
/*     */     
/* 201 */     if ((!action.equals(Action.RIGHT_CLICK_AIR)) && (!action.equals(Action.RIGHT_CLICK_BLOCK)))
/*     */     {
/* 203 */       if (itemMaterial.equals(Material.CAKE)) {
/* 204 */         event.setCancelled(true);
/* 205 */         SuperNManager.sendMessage(snplayer, Language.VAMPIRE_LIMIT_EAT.toString());
/*     */         
/* 207 */         return true;
/*     */       }
/* 209 */       return false;
/*     */     }
/*     */     

/* 224 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void armorCheck(Player player)
/*     */   {
     if (!player.hasPermission("supernatural.player.ignorearmor")) {
        PlayerInventory inv = player.getInventory();
        ItemStack helmet = inv.getHelmet();
        ItemStack chest = inv.getChestplate();
       ItemStack leggings = inv.getLeggings();
       ItemStack boots = inv.getBoots();

      if ((helmet != null) &&
        (!SNConfigHandler.vampireArmor.contains(helmet.getType())) && (!helmet.getType().isBlock()))
       {

         inv.setHelmet(null);
          dropItem(player, helmet);
       }

        if ((chest != null) &&
         (!SNConfigHandler.vampireArmor.contains(chest.getType()))) {
          inv.setChestplate(null);
          dropItem(player, chest);
      }

       if ((leggings != null) &&
         (!SNConfigHandler.vampireArmor.contains(leggings.getType()))) {
          inv.setLeggings(null);
        dropItem(player, leggings);
      }

        if ((boots != null) &&
          (!SNConfigHandler.vampireArmor.contains(boots.getType()))) {
         inv.setBoots(null);
      dropItem(player, boots);
       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void gainPowerAdvanceTime(SuperNPlayer snplayer, int milliseconds)
/*     */   {
/* 273 */     double deltaSeconds = milliseconds / 1000.0D;
/* 274 */     double deltaPower = deltaSeconds * SNConfigHandler.vampireTimePowerGained;
/*     */     
/* 276 */     SuperNManager.alterPower(snplayer, (int) deltaPower);
/*     */   }

/*     */   
/*     */   public void teleport(Player player)
/*     */   {

/* 292 */     SuperNPlayer snplayer = SuperNManager.get(player);

/* 295 */       if (snplayer.getPower() > SNConfigHandler.vampireTeleportCost) {
/* 296 */         SuperNManager.alterPower(snplayer, -SNConfigHandler.vampireTeleportCost, Language.VAMPIRE_TELEPORT_NOTICE_SELF.toString());
/*     */
                player.launchProjectile(EnderPearl.class,player.getVelocity().multiply(1.2));
/* 306 */         return;
/*     */       }
/* 308 */       SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/* 310 */

/* 315 */     return;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void useAltarInfect(Player player, Block centerBlock)
/*     */   {
/* 325 */     SuperNPlayer snplayer = SuperNManager.get(player);
/*     */     
/*     */ 
/* 328 */     int count = GeometryUtil.countNearby(centerBlock,  (SNConfigHandler.vampireAltarInfectMaterialSurround), SNConfigHandler.vampireAltarInfectMaterialRadius);

/* 333 */     if (count == 0) {
/* 334 */       return;
/*     */     }
/*     */     
/* 337 */     if (count < SNConfigHandler.vampireAltarInfectMaterialSurroundCount) {
/* 338 */       SuperNManager.sendMessage(snplayer, Language.VAMPIRE_ALTAR_INFECT_NOT_ENOUGH.toString().replace(LanguageTag.MATERIAL.toString(), SNConfigHandler.vampireAltarInfectMaterial.name().toLowerCase().replace('_', ' ')).replaceAll(LanguageTag.MATERIAL_SURROUND.toString(), SNConfigHandler.vampireAltarInfectMaterialSurround.name().replace('_', ' ')));
/* 354 */       return;
/*     */     }
/*     */     
/*     */ 
/* 358 */     SuperNManager.sendMessage(snplayer, Language.VAMPIRE_ALTAR_INFECT_INFO.toString());
/*     */     
/*     */ 
/* 361 */     if (!player.hasPermission( "supernatural.player.shrineuse.vampire"))
/*     */     {
/* 363 */       SuperNManager.sendMessage(snplayer, Language.VAMPIRE_ALTAR_NOT_ALLOW.toString());
/*     */       
/* 365 */       return;
/*     */     }
/*     */     
/*     */ 
/* 369 */     if (snplayer.isVampire()) {
/* 370 */       SuperNManager.sendMessage(snplayer, Language.VAMPIRE_ALTAR_INFECT_VAMPIRE.toString());
/*     */       
/* 372 */       return; }
/* 373 */     if (snplayer.isSuper()) {
/* 374 */       SuperNManager.sendMessage(snplayer, Language.VAMPIRE_ALTAR_INFECT_SUPERNATURAL.toString());
/*     */       
/* 376 */       return;
/*     */     }


    new BukkitRunnable() {
        @Override
        public void run() {
            VampInfectGui vampInfectGui=new VampInfectGui(SNConfigHandler.vampireAltarInfectRecipe,Language.GUI_VAMINFECT_TITLE.toString());
            vampInfectGui.openInv(player);
        }
    }.runTaskAsynchronously(SupernaturalsPlugin.instance);
/*     */   }
/*     */   
/*     */   public void useAltarCure(Player player, Block centerBlock)
/*     */   {
/* 413 */     SuperNPlayer snplayer = SuperNManager.get(player);
/*     */     
/*     */ 
/* 416 */     int count = GeometryUtil.countNearby(centerBlock, (SNConfigHandler.vampireAltarCureMaterialSurround), SNConfigHandler.vampireAltarCureMaterialRadius);
/*     */     
/*     */ 
/* 419 */     if (count == 0) {
/* 420 */       return;
/*     */     }
/*     */     
/* 423 */     if (count < SNConfigHandler.vampireAltarCureMaterialSurroundCount) {
/* 424 */       SuperNManager.sendMessage(snplayer, Language.VAMPIRE_ALTAR_CURE_FAIL.toString().replace(LanguageTag.MATERIAL.toString(), SNConfigHandler.vampireAltarCureMaterial.name().toLowerCase().replace('_', ' ').replace('_', ' ')).replaceAll(LanguageTag.MATERIAL_SURROUND.toString(), SNConfigHandler.vampireAltarCureMaterialSurround.name().replace('_', ' ')));
/*     */

/* 441 */       return;
/*     */     }
/*     */     
/*     */ 
/* 445 */     SuperNManager.sendMessage(snplayer, Language.VAMPIRE_ALTAR_CURE_INFO.toString());
/*     */     
/*     */ 
/* 448 */     if (!player.hasPermission("supernatural.player.shrineuse.vampire"))
/*     */     {
/* 450 */       SuperNManager.sendMessage(snplayer, Language.VAMPIRE_ALTAR_NOT_ALLOW.toString());
/*     */       
/* 452 */       return;
/*     */     }
/*     */     
/*     */ 
/* 456 */     if (!snplayer.isVampire()) {
/* 457 */       SuperNManager.sendMessage(snplayer, Language.VAMPIRE_ALTAR_CURE_FAIL.toString());
return;
/*     */     }

    new BukkitRunnable() {
        @Override
        public void run() {
            VampCureGui bc=new VampCureGui(SNConfigHandler.vampireAltarCureRecipe,Language.GUI_VAMCURE_TITLE.toString());
            bc.openInv(player);
        }
    }.runTaskAsynchronously(SupernaturalsPlugin.instance);
/*     */   }

/*     */   public boolean combustAdvanceTime(Player player, long milliseconds)
/*     */   {
/* 498 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 499 */     if (!standsInSunlight(player)) {
/* 500 */       return false;
/*     */     }
/*     */     
/* 503 */     if (!SNConfigHandler.vampireBurnInSunlight) {
/* 504 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 508 */     int ticksTillNext = (int)(milliseconds / 1000.0D * 20.0D);
/*     */     
/*     */ 
/* 511 */     ticksTillNext += 5;
/*     */     
/* 513 */     if ((player.getFireTicks() <= 0) && (SNConfigHandler.vampireBurnMessageEnabled))
/*     */     {
/* 515 */       SuperNManager.sendMessage(snplayer, Language.VAMPIRE_HATE_SUNLIGHT.toString());
/*     */     }
/*     */     
/*     */ 
/* 519 */     player.setFireTicks(ticksTillNext + SNConfigHandler.vampireCombustFireTicks);
/*     */     
/* 521 */     return true;
/*     */   }
/*     */   
/*     */   public boolean standsInSunlight(Player player)
/*     */   {
/* 526 */     return player.getLocation().getBlock().getLightFromSky()>=14&&((player.getInventory().getHelmet()==null) ||((player.getInventory().getHelmet().getType()==Material.AIR) ));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 

/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 


/*     */   public boolean doorEvent(Player player, Door door) {

/* 607 */     SuperNPlayer snplayer = SuperNManager.get(player);
/*     */     
/* 609 */
/* 613 */     if (snplayer.isVampire()) {
    door.setOpen(true);
/* 633 */       SupernaturalsPlugin.instance.getServer().getScheduler().scheduleSyncDelayedTask(SupernaturalsPlugin.instance, new Runnable()
/*     */       {
/*     */         public void run() {
/* 640 */           try {
    door.setOpen(false);
    }catch (Exception e){}; } }, 20L);
/* 643 */       return true;
/*     */     }
/* 645 */     SuperNManager.sendMessage(snplayer, Language.VAMPIRE_ONLY.toString());
/* 646 */     return true;
/*     */   }
/*     */   

/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\manager\VampireManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */