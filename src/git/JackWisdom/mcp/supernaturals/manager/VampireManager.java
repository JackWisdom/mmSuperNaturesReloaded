/*     */ package git.JackWisdom.mcp.supernaturals.manager;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*     */ import git.JackWisdom.mcp.supernaturals.SuperType;
import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */ import git.JackWisdom.mcp.supernaturals.events.VampireTeleportEvent;
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
/*     */ import org.bukkit.World;
/*     */
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
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
/* 187 */       if (itemMaterial.toString().equalsIgnoreCase(SNConfigHandler.vampireJumpMaterial))
/*     */       {
/* 189 */         SuperNManager.jump(player, SNConfigHandler.jumpDeltaSpeed, true);
/*     */         
/* 191 */         event.setCancelled(true);
/* 192 */         return true; }
/* 193 */       if (itemMaterial.toString().equalsIgnoreCase(SNConfigHandler.vampireMaterial))
/*     */       {
/* 195 */         teleport(player);
/* 196 */         event.setCancelled(true);
/* 197 */         return true;
/*     */       }
/*     */     }
/*     */     
/* 201 */     if ((!action.equals(Action.RIGHT_CLICK_AIR)) && (!action.equals(Action.RIGHT_CLICK_BLOCK)))
/*     */     {
/* 203 */       if (itemMaterial.equals(Material.CAKE_BLOCK)) {
/* 204 */         event.setCancelled(true);
/* 205 */         SuperNManager.sendMessage(snplayer, Language.VAMPIRE_LIMIT_EAT.toString());
/*     */         
/* 207 */         return true;
/*     */       }
/* 209 */       return false;
/*     */     }
/*     */     
/* 212 */     if (itemMaterial != null) {
/* 213 */       if (SNConfigHandler.foodMaterials.contains(itemMaterial)) {
/* 214 */         SuperNManager.sendMessage(snplayer, Language.VAMPIRE_LIMIT_EAT.toString());
/*     */         
/* 216 */         event.setCancelled(true);
/* 217 */         return true; }
/* 218 */       if (itemMaterial.toString().equalsIgnoreCase(SNConfigHandler.vampireTeleportMaterial))
/*     */       {
/* 220 */         setTeleport(player);
/* 221 */         return true;
/*     */       }
/*     */     }
/* 224 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void armorCheck(Player player)
/*     */   {
/* 233 */     if (!player.hasPermission("supernatural.player.ignorearmor")) {
/* 234 */       PlayerInventory inv = player.getInventory();
/* 235 */       ItemStack helmet = inv.getHelmet();
/* 236 */       ItemStack chest = inv.getChestplate();
/* 237 */       ItemStack leggings = inv.getLeggings();
/* 238 */       ItemStack boots = inv.getBoots();
/*     */
/* 240 */       if ((helmet != null) && 
/* 241 */         (!SNConfigHandler.vampireArmor.contains(helmet.getType())) && (!helmet.getType().equals(Material.WOOL)))
/*     */       {

/* 243 */         inv.setHelmet(null);
/* 244 */         dropItem(player, helmet);
/*     */       }
/*     */       
/* 247 */       if ((chest != null) && 
/* 248 */         (!SNConfigHandler.vampireArmor.contains(chest.getType()))) {
/* 249 */         inv.setChestplate(null);
/* 250 */         dropItem(player, chest);
/*     */       }
/*     */       
/* 253 */       if ((leggings != null) && 
/* 254 */         (!SNConfigHandler.vampireArmor.contains(leggings.getType()))) {
/* 255 */         inv.setLeggings(null);
/* 256 */         dropItem(player, leggings);
/*     */       }
/*     */       
/* 259 */       if ((boots != null) && 
/* 260 */         (!SNConfigHandler.vampireArmor.contains(boots.getType()))) {
/* 261 */         inv.setBoots(null);
/* 262 */         dropItem(player, boots);
/*     */       }
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
/* 276 */     SuperNManager.alterPower(snplayer, deltaPower);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTeleport(Player player)
/*     */   {
/* 284 */     SuperNPlayer snplayer = SuperNManager.get(player);
/*     */     
/* 286 */     snplayer.setTeleport(player.getLocation());
/* 287 */     SuperNManager.sendMessage(snplayer, Language.VAMPIRE_TELEPORT_SET.toString());
/*     */   }
/*     */   
/*     */   public boolean teleport(Player player)
/*     */   {

/* 292 */     SuperNPlayer snplayer = SuperNManager.get(player);

/* 293 */     ItemStack item = player.getItemInHand();
/* 294 */     if (snplayer.getTeleport()!=null) {
/* 295 */       if (snplayer.getPower() > SNConfigHandler.vampireTeleportCost) {
/* 296 */         SuperNManager.alterPower(snplayer, -SNConfigHandler.vampireTeleportCost, Language.VAMPIRE_TELEPORT_NOTICE_SELF.toString());
/*     */         
/*     */                  VampireTeleportEvent event=new VampireTeleportEvent(snplayer, player.getLocation());
                           Bukkit.getPluginManager().callEvent(event);
                        if(event.isCancelled()){
                           return false;
                       }
/* 299 */         player.teleport(snplayer.getTeleport());
/*     */         
/* 301 */         if (item.getAmount() == 1) {
/* 302 */           player.setItemInHand(null);
/*     */         } else {
/* 304 */           item.setAmount(player.getItemInHand().getAmount() - 1);
/*     */         }
/* 306 */         return true;
/*     */       }
/* 308 */       SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/*     */       
/* 310 */       return false;
/*     */     }
/*     */     
/* 313 */     SuperNManager.sendMessage(snplayer, Language.VAMPIRE_TELEPORT_NOT_SET.toString());
/*     */     
/* 315 */     return false;
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
/* 328 */     int count = GeometryUtil.countNearby(centerBlock, Material.getMaterial(SNConfigHandler.vampireAltarInfectMaterialSurround), SNConfigHandler.vampireAltarInfectMaterialRadius);

/* 333 */     if (count == 0) {
/* 334 */       return;
/*     */     }
/*     */     
/* 337 */     if (count < SNConfigHandler.vampireAltarInfectMaterialSurroundCount) {
/* 338 */       SuperNManager.sendMessage(snplayer, Language.VAMPIRE_ALTAR_INFECT_NOT_ENOUGH.toString().replace(LanguageTag.MATERIAL.toString(), SNConfigHandler.vampireAltarInfectMaterial.toLowerCase().replace('_', ' ')).replaceAll(LanguageTag.MATERIAL_SURROUND.toString(), SNConfigHandler.vampireAltarInfectMaterialSurround.toLowerCase().replace('_', ' ')));
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
/* 416 */     int count = GeometryUtil.countNearby(centerBlock, Material.getMaterial(SNConfigHandler.vampireAltarCureMaterialSurround), SNConfigHandler.vampireAltarCureMaterialRadius);
/*     */     
/*     */ 
/* 419 */     if (count == 0) {
/* 420 */       return;
/*     */     }
/*     */     
/* 423 */     if (count < SNConfigHandler.vampireAltarCureMaterialSurroundCount) {
/* 424 */       SuperNManager.sendMessage(snplayer, Language.VAMPIRE_ALTAR_CURE_FAIL.toString().replace(LanguageTag.MATERIAL.toString(), SNConfigHandler.vampireAltarCureMaterial.toLowerCase().replace('_', ' ').replace('_', ' ')).replaceAll(LanguageTag.MATERIAL_SURROUND.toString(), SNConfigHandler.vampireAltarCureMaterialSurround.toLowerCase().replace('_', ' ')));
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
/* 526 */     Material material = player.getLocation().getBlock().getType();
/* 527 */     World playerWorld = player.getWorld();
/*     */     
/* 529 */     if (player.hasPermission( this.permissions)) {
/* 530 */       return false;
/*     */     }
/*     */     
/* 533 */     return (!player.getWorld().getEnvironment().equals(World.Environment.NETHER)) && (!SuperNManager.worldTimeIsNight(player)) && (!isUnderRoof(player)) && (!material.equals(Material.STATIONARY_WATER)) && (!material.equals(Material.WATER)) && (!playerWorld.hasStorm()) && (!hasHelmet(player));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasHelmet(Player player)
/*     */   {
/* 541 */     if ((player.getInventory().getHelmet() != null) && 
/* 542 */       (player.getInventory().getHelmet().getType().toString().equalsIgnoreCase(SNConfigHandler.vampireHelmet)))
/*     */     {
/* 544 */       return true;
/*     */     }
/*     */     
/* 547 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isUnderRoof(Player player)
/*     */   {
/* 556 */     boolean retVal = false;
/* 557 */     Block blockCurrent = player.getLocation().getBlock();
/*     */     
/* 559 */     if (player.getLocation().getY() >= 254.0D) {
/* 560 */       retVal = false;
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 565 */       double opacityAccumulator = 0.0D;
/*     */       
/*     */ 
/* 568 */       while (blockCurrent.getY() + 1 <= 255) {
/* 569 */         blockCurrent = blockCurrent.getRelative(BlockFace.UP);
/*     */         
/* 571 */         Double opacity = (Double)SNConfigHandler.materialOpacity.get(blockCurrent.getType());
/*     */         
/* 573 */         if (opacity == null) {
/* 574 */           retVal = true;
/*     */         }
/*     */         else
/*     */         {
/* 578 */           opacityAccumulator += opacity.doubleValue();
/* 579 */           if (opacityAccumulator >= 1.0D) {
/* 580 */             retVal = true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 585 */     return retVal;
/*     */   }
/*     */   
/*     */   private void addDoorLocation(Location location) {
/* 589 */     if (!this.hallDoors.contains(location)) {
/* 590 */       this.hallDoors.add(location);
/*     */     }
/*     */   }
/*     */   
/*     */   private void removeDoorLocation(Location location) {
/* 595 */     this.hallDoors.remove(location);
/*     */   }
/*     */   
/*     */   public boolean doorIsOpening(Location location) {
/* 599 */     return this.hallDoors.contains(location);
/*     */   }
/*     */   
/*     */   public boolean doorEvent(Player player, Block block, Door door) {
/* 603 */     if (door.isOpen()) {
/* 604 */       return true;
/*     */     }
/*     */     
/* 607 */     SuperNPlayer snplayer = SuperNManager.get(player);
/*     */     
/* 609 */     final Location loc = block.getLocation();
/*     */     
/*     */ 
/*     */ 
/* 613 */     if (snplayer.isVampire()) { Location newLoc;
/* 614 */       if (door.isTopHalf()) {
/* 615 */          newLoc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ());
/*     */         
/* 617 */         Block newBlock = newLoc.getBlock();
/* 618 */         block.setTypeIdAndData(71, (byte)(block.getData() + 4), false);
/* 619 */         newBlock.setTypeIdAndData(71, (byte)(newBlock.getData() + 4), false);
/*     */       }
/*     */       else {
/* 622 */         newLoc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ());
/*     */         
/* 624 */         Block newBlock = newLoc.getBlock();
/* 625 */         block.setTypeIdAndData(71, (byte)(block.getData() + 4), false);
/* 626 */         newBlock.setTypeIdAndData(71, (byte)(newBlock.getData() + 4), false);
/*     */       }
/*     */       
/*     */ 
/* 630 */       addDoorLocation(loc);
/* 631 */       addDoorLocation(newLoc);
/*     */       
/* 633 */       SupernaturalsPlugin.instance.getServer().getScheduler().scheduleSyncDelayedTask(SupernaturalsPlugin.instance, new Runnable()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         public void run() {
/* 640 */           VampireManager.this.closeDoor(loc); } }, 20L);
/*     */       
/*     */ 
/* 643 */       return true;
/*     */     }
/* 645 */     SuperNManager.sendMessage(snplayer, Language.VAMPIRE_ONLY.toString());
/* 646 */     return true;
/*     */   }
/*     */   
/*     */   private void closeDoor(Location loc) {
/* 650 */     Block block = loc.getBlock();
/* 651 */     Door door = (Door)block.getState().getData();
/* 652 */     if (!door.isOpen()) {
/*     */       return;
/*     */     }
/*     */     
/*     */ 
/*     */     Location newLoc;
/*     */     
/* 659 */     if (door.isTopHalf()) {
/* 660 */        newLoc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ());
/*     */       
/* 662 */       Block newBlock = newLoc.getBlock();
/* 663 */       block.setTypeIdAndData(71, (byte)(block.getData() - 4), false);
/* 664 */       newBlock.setTypeIdAndData(71, (byte)(newBlock.getData() - 4), false);
/*     */     }
/*     */     else {
/* 667 */       newLoc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ());
/*     */       
/* 669 */       Block newBlock = newLoc.getBlock();
/* 670 */       block.setTypeIdAndData(71, (byte)(block.getData() - 4), false);
/* 671 */       newBlock.setTypeIdAndData(71, (byte)(newBlock.getData() - 4), false);
/*     */     }
/*     */     
/*     */ 
/* 675 */     removeDoorLocation(loc);
/* 676 */     removeDoorLocation(newLoc);
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\manager\VampireManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */