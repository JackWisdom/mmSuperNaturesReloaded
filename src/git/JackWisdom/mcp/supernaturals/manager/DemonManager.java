/*     */ package git.JackWisdom.mcp.supernaturals.manager;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*     */ import git.JackWisdom.mcp.supernaturals.SuperType;
import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */ import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
/*     */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*     */ import git.JackWisdom.mcp.supernaturals.util.LanguageTag;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */
/*     */ import org.bukkit.World;
/*     */
/*     */ import org.bukkit.block.Biome;
/*     */ import org.bukkit.block.Block;
/*     */
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Fireball;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.material.Door;
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
/*     */ public class DemonManager
/*     */   extends ClassManager
/*     */ {
/*     */   public DemonManager(SupernaturalsPlugin plugin)
/*     */   {
/*  53 */     this.plugin = plugin;
/*     */   }
/*     */   
/*  56 */   private ArrayList<Location> hallDoors = new ArrayList();
/*  57 */   private HashMap<Block, Location> webMap = new HashMap();
/*  58 */   private ArrayList<Player> demonApps = new ArrayList();
/*  59 */   private List<Player> demons = new ArrayList();
/*     */   
/*     */ 
/*     */   private SupernaturalsPlugin plugin;
/*     */   
/*     */ 
/*     */ 
/*     */   public double victimEvent(EntityDamageEvent event, double damage)
/*     */   {
/*  68 */     Player victim = (Player)event.getEntity();
/*  69 */     SuperNPlayer snVictim = SuperNManager.get(victim);
/*     */     
/*  71 */     if ((event.getCause().equals(EntityDamageEvent.DamageCause.FIRE)) || (event.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) || (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) || (event.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)))
/*     */     {
/*     */ 
/*     */ 
/*  75 */       victim.setFireTicks(0);
/*  76 */       event.setCancelled(true);
/*  77 */       return 0.0D; }
/*  78 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
/*  79 */       event.setCancelled(true);
/*  80 */       return 0.0D; }
/*  81 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.LAVA)) {
/*  82 */       final Player dPlayer = victim;
/*  83 */       if (!this.demons.contains(dPlayer)) {
/*  84 */         this.demons.add(dPlayer);
/*  85 */         heal(victim);
/*  86 */         SuperNManager.alterPower(snVictim, SNConfigHandler.demonPowerGain, Language.DAEMON_LOVE_LAVA.toString());
/*     */         
/*     */ 
/*  89 */         SupernaturalsPlugin.instance.getServer().getScheduler().scheduleSyncDelayedTask(SupernaturalsPlugin.instance, new Runnable()
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           public void run() {
/*  96 */             DemonManager.this.demons.remove(dPlayer); } }, 41L);
/*     */       }
/*     */       
/*     */ 
/* 100 */       victim.setFireTicks(0);
/* 101 */       event.setCancelled(true);
/* 102 */       return 0.0D;
/*     */     }
/* 104 */     return damage;
/*     */   }
/*     */   
/*     */   public double damagerEvent(EntityDamageByEntityEvent event, double damage)
/*     */   {
/* 109 */     Entity damager = event.getDamager();
/* 110 */     Player pDamager = (Player)damager;
/* 111 */     Entity victim = event.getEntity();
/* 112 */     SuperNPlayer snDamager = SuperNManager.get(pDamager);
/* 113 */     ItemStack item = pDamager.getItemInHand();
/*     */     
/* 115 */     if ((item != null) && 
/* 116 */       (SNConfigHandler.demonWeapons.contains(item.getType()))) {
/* 117 */       SuperNManager.sendMessage(snDamager, Language.DAEMON_LIMIT_WEAPON.toString());
/*     */       
/* 119 */       damage = 0.0D;
/*     */     }

/* 131 */     return damage;
/*     */   }
/*     */   
/*     */   public void deathEvent(Player player)
/*     */   {
/* 136 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 137 */     EntityDamageEvent e = player.getLastDamageCause();
/*     */     
/* 139 */     SuperNManager.alterPower(snplayer, -SNConfigHandler.demonDeathPowerPenalty, Language.YOU_DIE.toString());
/*     */     
/*     */ 
/*     */ 
/* 143 */     if (e == null) {
/* 144 */       return;
/*     */     }
/* 146 */     if (e.getCause().equals(EntityDamageEvent.DamageCause.DROWNING)) {
/* 147 */       int pLocX = player.getLocation().getBlockX();
/* 148 */       int pLocY = player.getLocation().getBlockZ();
/* 149 */       Biome pBiome = player.getWorld().getBiome(pLocX, pLocY);
/* 150 */       if ((snplayer.isDemon()) && (
/* 151 */         (pBiome == Biome.TAIGA) || (pBiome == Biome.FROZEN_OCEAN) || (pBiome == Biome.FROZEN_RIVER) || (pBiome == Biome.ICE_MOUNTAINS) || (pBiome == Biome.COLD_BEACH)))
/*     */       {
/*     */ 
/*     */ 
/* 155 */         if (player.getInventory().contains(Material.SNOW_BALL, SNConfigHandler.demonSnowballAmount))
/*     */         {
/* 157 */           SuperNManager.sendMessage(snplayer, Language.DAEMON_DEATH.toString());
/*     */           
/* 159 */           SuperNManager.cure(snplayer);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void killEvent(Player pDamager, SuperNPlayer damager, SuperNPlayer victim)
/*     */   {
/* 169 */     if (victim == null) {
/* 170 */       SuperNManager.alterPower(damager, SNConfigHandler.demonKillPowerCreatureGain, Language.KILL_CREATURE.toString());
/*     */ 
/*     */ 
/*     */     }
/* 174 */     else if (victim.getPower() > SNConfigHandler.demonKillPowerPlayerGain) {
/* 175 */       SuperNManager.alterPower(damager, SNConfigHandler.demonKillPowerPlayerGain, Language.KILL_PLAYER.toString());
/*     */     }
/*     */     else
/*     */     {
/* 179 */       SuperNManager.sendMessage(damager, Language.NO_POWER_GAIN.toString());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean playerInteract(PlayerInteractEvent event)
/*     */   {
/* 192 */     Action action = event.getAction();
/* 193 */     Player player = event.getPlayer();
/*     */     
/* 195 */     Material itemMaterial = player.getItemInHand().getType();
/*     */     
/* 197 */     boolean cancelled = false;
/*     */     
/* 199 */     if (player.getItemInHand() == null) {
/* 200 */       return false;
/*     */     }
/*     */     
/* 203 */     if ((!action.equals(Action.LEFT_CLICK_AIR)) && (!action.equals(Action.LEFT_CLICK_BLOCK)))
/*     */     {
/* 205 */       return false;
/*     */     }
/*     */     
/* 208 */     if (itemMaterial.toString().equalsIgnoreCase(SNConfigHandler.demonMaterial))
/*     */     {
/* 210 */       cancelled = fireball(player);
/* 211 */       if ((!event.isCancelled()) && (cancelled)) {
/* 212 */         event.setCancelled(true);
/*     */       }
/* 214 */       return true; }
/* 215 */     if (itemMaterial.toString().equalsIgnoreCase(SNConfigHandler.demonSnareMaterial))
/*     */     {
/* 217 */       Player target = SupernaturalsPlugin.instance.getSuperManager().getTarget(player);
/*     */       
/* 219 */       cancelled = snare(player, target);
/* 220 */       if ((!event.isCancelled()) && (cancelled)) {
/* 221 */         event.setCancelled(true);
/*     */       }
/* 223 */       return true;
/*     */     }
/* 225 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void armorCheck(Player player)
/*     */   {
/* 234 */     if (!player.hasPermission("supernatural.player.ignorearmor")) {
/* 235 */       PlayerInventory inv = player.getInventory();
/* 236 */       ItemStack helmet = inv.getHelmet();
/* 237 */       ItemStack chest = inv.getChestplate();
/* 238 */       ItemStack leggings = inv.getLeggings();
/* 239 */       ItemStack boots = inv.getBoots();
/*     */       
/* 241 */       if ((helmet != null) && 
/* 242 */         (!SNConfigHandler.demonArmor.contains(helmet.getType())) && (!helmet.getType().equals(Material.WOOL)))
/*     */       {
/* 244 */         inv.setHelmet(null);
/* 245 */         dropItem(player, helmet);
/*     */       }
/*     */       
/* 248 */       if ((chest != null) && 
/* 249 */         (!SNConfigHandler.demonArmor.contains(chest.getType()))) {
/* 250 */         inv.setChestplate(null);
/* 251 */         dropItem(player, chest);
/*     */       }
/*     */       
/* 254 */       if ((leggings != null) && 
/* 255 */         (!SNConfigHandler.demonArmor.contains(leggings.getType()))) {
/* 256 */         inv.setLeggings(null);
/* 257 */         dropItem(player, leggings);
/*     */       }
/*     */       
/* 260 */       if ((boots != null) && 
/* 261 */         (!SNConfigHandler.demonArmor.contains(boots.getType()))) {
/* 262 */         inv.setBoots(null);
/* 263 */         dropItem(player, boots);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean checkInventory(Player player)
/*     */   {
/* 274 */     PlayerInventory inv = player.getInventory();
/* 275 */     ItemStack helmet = inv.getHelmet();
/* 276 */     ItemStack chestplate = inv.getChestplate();
/* 277 */     ItemStack leggings = inv.getLeggings();
/* 278 */     ItemStack boots = inv.getBoots();
/* 279 */     if ((helmet != null) && (chestplate != null) && (leggings != null) && (boots != null))
/*     */     {
/* 281 */       if ((helmet.getType().equals(Material.LEATHER_HELMET)) && (chestplate.getType().equals(Material.LEATHER_CHESTPLATE)) && (leggings.getType().equals(Material.LEATHER_LEGGINGS)) && (boots.getType().equals(Material.LEATHER_BOOTS)))
/*     */       {
/*     */ 
/*     */ 
/* 285 */         this.demonApps.add(player);
/* 286 */         return true;
/*     */       }
/*     */     }
/* 289 */     return false;
/*     */   }
/*     */   
/*     */   public boolean checkPlayerApp(Player player) {
/* 293 */     if (this.demonApps.contains(player)) {
/* 294 */       this.demonApps.remove(player);
/* 295 */       return true;
/*     */     }
/* 297 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public HashMap<Block, Location> getWebs()
/*     */   {
/* 305 */     return this.webMap;
/*     */   }
/*     */   
/*     */   public void removeWeb(Block block) {
/* 309 */     this.webMap.remove(block);
/*     */   }
/*     */   
/*     */   public void removeAllWebs() {
/* 313 */     for (Block block : this.webMap.keySet()) {
/* 314 */       block.setType(Material.AIR);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void powerAdvanceTime(Player player, int seconds)
/*     */   {
/* 323 */     if (!player.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
/* 324 */       if ((player.getLocation().getBlock().getType().equals(Material.FIRE)) || (player.getLocation().getBlock().getType().equals(Material.LAVA)))
/*     */       {
/*     */ 
/* 327 */         return;
/*     */       }
/* 329 */       SuperNPlayer snplayer = SuperNManager.get(player);
/* 330 */       SuperNManager.alterPower(snplayer, -(SNConfigHandler.demonPowerLoss * seconds));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void heal(Player player)
/*     */   {
/* 340 */     if ((player.isDead()) || (player.getHealth() == 20.0D)) {
/* 341 */       return;
/*     */     }
/*     */     
/* 344 */     double health = player.getHealth();
/* 345 */     health += SNConfigHandler.demonHealing;
/* 346 */     if (health > player.getMaxHealth()) {
/* 347 */       health = player.getMaxHealth();
/*     */     }
/* 349 */     player.setHealth(health);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void spellEvent(EntityDamageByEntityEvent event, Player target)
/*     */   {
/* 358 */     Player player = (Player)event.getDamager();
/* 359 */     Material itemMaterial = player.getItemInHand().getType();
/*     */     
/* 361 */     boolean cancelled = false;
/*     */     
/* 363 */     if (player.getItemInHand() == null) {
/* 364 */       return;
/*     */     }
/*     */     
/* 367 */     if (itemMaterial.equals(Material.NETHERRACK)) {
/* 368 */       cancelled = convert(player, target);
/* 369 */       if (!event.isCancelled()) {
/* 370 */         event.setCancelled(cancelled);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean fireball(Player player) {
/* 376 */     SuperNPlayer snplayer = SuperNManager.get(player);

/* 382 */     if (snplayer.getPower() < SNConfigHandler.demonPowerFireball) {
/* 383 */       SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/* 384 */       return false;
/*     */     }
/* 386 */     Location loc = player.getEyeLocation().toVector().add(player.getLocation().getDirection().multiply(2)).toLocation(player.getWorld(), player.getLocation().getYaw(), player.getLocation().getPitch());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 392 */     Fireball fireball = (Fireball)player.getWorld().spawn(loc, Fireball.class);
/* 393 */     fireball.setShooter(player);
/* 394 */     fireball.setYield(0.0F);
/* 395 */     SuperNManager.alterPower(SuperNManager.get(player), -SNConfigHandler.demonPowerFireball, Language.DAEMON_FIREBALL_NOTICE_SELF.toString());
/*     */     
/*     */ 
/* 398 */     ItemStack item = player.getItemInHand();
/* 399 */     if (item.getAmount() == 1) {
/* 400 */       player.setItemInHand(null);
/*     */     } else {
/* 402 */       item.setAmount(player.getItemInHand().getAmount() - 1);
/*     */     }
/* 404 */     return true;
/*     */   }
/*     */   
/*     */   public boolean convert(Player player, Player target) {
/* 408 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 409 */     SuperNPlayer snvictim = SuperNManager.get(target);
/* 410 */     if (snplayer.getPower() < SNConfigHandler.demonConvertPower) {
/* 411 */       SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/* 412 */       return false;
/*     */     }
/* 414 */     if (target.getItemInHand().getType().equals(Material.NETHERRACK)) {
/* 415 */       SuperNManager.alterPower(snplayer, -SNConfigHandler.demonConvertPower, Language.DAEMON_CONVERT_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), target.getName()));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 420 */       SuperNManager.convert(snvictim, SuperType.DEMON);
/* 421 */       SuperNManager.sendMessage(snvictim, Language.DAEMON_CONVERT_NOTICE_OTHER_DESC.toString());
/*     */       
/* 423 */       SuperNManager.sendMessage(snvictim, Language.DAEMON_CONVERT_NOTICE_OTHER.toString().replace(LanguageTag.PLAYER.toString(), player.getName()));
/*     */       
/*     */ 
/*     */ 
/* 427 */       return true;
/*     */     }
/* 429 */     return false;
/*     */   }
/*     */   
/*     */   public boolean snare(Player player, Player target) {
/* 433 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 434 */     if (snplayer.getPower() < SNConfigHandler.demonPowerSnare) {
/* 435 */       SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/* 436 */       return false;
/*     */     }
/*     */     Block block;

/* 440 */     if (target == null) {
/* 441 */       block = player.getTargetBlock(null, 20);
/*     */     } else {
/* 443 */       block = target.getLocation().getBlock();
/*     */     }
/*     */     
/* 446 */     final Location loc = block.getLocation();
/*     */     
/* 448 */     for (int x = loc.getBlockX() - 1; x < loc.getBlockX() + 2; x++) {
/* 449 */       for (int y = loc.getBlockY() - 1; y < loc.getBlockY() + 2; y++) {
/* 450 */         for (int z = loc.getBlockZ() - 1; z < loc.getBlockZ() + 2; z++) {
/* 451 */           Location newLoc = new Location(block.getWorld(), x, y, z);
/* 452 */           Block newBlock = newLoc.getBlock();
/* 453 */           if (newBlock.getTypeId() == 0) {
/* 454 */             newBlock.setType(Material.WEB);
/* 455 */             this.webMap.put(newBlock, loc);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 461 */     SupernaturalsPlugin.instance.getServer().getScheduler().scheduleSyncDelayedTask(SupernaturalsPlugin.instance, new Runnable()
/*     */     {
/*     */ 
/*     */ 
/*     */       public void run()
/*     */       {
/*     */ 
/* 468 */         List<Block> blocks = new ArrayList();
/* 469 */         for (Block block : DemonManager.this.webMap.keySet()) {
/* 470 */           if (((Location)DemonManager.this.webMap.get(block)).equals(loc)) {
/* 471 */             block.setType(Material.AIR);
/* 472 */             blocks.add(block);
/*     */           }
/*     */         }
/* 475 */         for (Block block : blocks)
/* 476 */           DemonManager.this.webMap.remove(block); } }, SNConfigHandler.demonSnareDuration / 50);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 481 */     ItemStack item = player.getItemInHand();
/* 482 */     if (item.getAmount() == 1) {
/* 483 */       player.setItemInHand(null);
/*     */     } else {
/* 485 */       item.setAmount(player.getItemInHand().getAmount() - 1);
/*     */     }
/*     */     
/* 488 */     SuperNManager.alterPower(SuperNManager.get(player), -SNConfigHandler.demonPowerSnare, Language.DAEMON_SNARE_NOTICE_SELF.toString());
/*     */     
/*     */ 
/* 491 */     return true;
/*     */   }
/*     */   
/*     */   private void addDoorLocation(Location location) {
/* 495 */     if (!this.hallDoors.contains(location)) {
/* 496 */       this.hallDoors.add(location);
/*     */     }
/*     */   }
/*     */   
/*     */   private void removeDoorLocation(Location location) {
/* 501 */     this.hallDoors.remove(location);
/*     */   }
/*     */   
/*     */   public boolean doorIsOpening(Location location) {
/* 505 */     if (this.hallDoors.contains(location)) {
/* 506 */       return true;
/*     */     }
/* 508 */     return false;
/*     */   }
/*     */   
/*     */   public boolean doorEvent(Player player, Block block, Door door) {
/* 512 */     if (door.isOpen()) {
/* 513 */       return true;
/*     */     }
/*     */     
/* 516 */     SuperNPlayer snplayer = SuperNManager.get(player);
/*     */     
/* 518 */     final Location loc = block.getLocation();
/*     */     
/*     */ 
/*     */ 
/* 522 */     if (snplayer.isDemon()) { Location newLoc;
/* 523 */       if (door.isTopHalf()) {
/* 524 */           newLoc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ());
/*     */         
/* 526 */         Block newBlock = newLoc.getBlock();
/* 527 */         block.setTypeIdAndData(71, (byte)(block.getData() + 4), false);
/* 528 */         newBlock.setTypeIdAndData(71, (byte)(newBlock.getData() + 4), false);
/*     */       }
/*     */       else {
/* 531 */         newLoc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ());
/*     */         
/* 533 */         Block newBlock = newLoc.getBlock();
/* 534 */         block.setTypeIdAndData(71, (byte)(block.getData() + 4), false);
/* 535 */         newBlock.setTypeIdAndData(71, (byte)(newBlock.getData() + 4), false);
/*     */       }
/*     */       
/*     */ 
/* 539 */       addDoorLocation(loc);
/* 540 */       addDoorLocation(newLoc);
/*     */       
/* 542 */       SupernaturalsPlugin.instance.getServer().getScheduler().scheduleSyncDelayedTask(SupernaturalsPlugin.instance, new Runnable()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         public void run() {
/* 549 */           DemonManager.this.closeDoor(loc); } }, 20L);
/*     */       
/*     */ 
/* 552 */       return true;
/*     */     }
/* 554 */     SuperNManager.sendMessage(snplayer, Language.DAEMON_ONLY.toString());
/* 555 */     return true;
/*     */   }
/*     */   
/*     */   private void closeDoor(Location loc) {
/* 559 */     Block block = loc.getBlock();
/* 560 */     Door door = (Door)block.getState().getData();
/* 561 */     if (!door.isOpen()) {
/*     */       return;
/*     */     }
/*     */     
/*     */ 
/*     */     Location newLoc;
/*     */     
/* 568 */     if (door.isTopHalf()) {
/* 569 */       newLoc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ());
/*     */       
/* 571 */       Block newBlock = newLoc.getBlock();
/* 572 */       block.setTypeIdAndData(71, (byte)(block.getData() - 4), false);
/* 573 */       newBlock.setTypeIdAndData(71, (byte)(newBlock.getData() - 4), false);
/*     */     }
/*     */     else {
/* 576 */       newLoc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ());
/*     */       
/* 578 */       Block newBlock = newLoc.getBlock();
/* 579 */       block.setTypeIdAndData(71, (byte)(block.getData() - 4), false);
/* 580 */       newBlock.setTypeIdAndData(71, (byte)(newBlock.getData() - 4), false);
/*     */     }
/*     */     
/*     */ 
/* 584 */     removeDoorLocation(loc);
/* 585 */     removeDoorLocation(newLoc);
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\manager\DemonManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */