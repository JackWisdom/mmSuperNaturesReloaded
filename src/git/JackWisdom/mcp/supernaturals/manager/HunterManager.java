/*     */ package git.JackWisdom.mcp.supernaturals.manager;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*     */ import git.JackWisdom.mcp.supernaturals.SuperType;
import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */ import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
/*     */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*     */ import git.JackWisdom.mcp.supernaturals.util.LanguageTag;
/*     */ import java.util.*;
/*     */
/*     */
/*     */
/*     */ import java.util.logging.Level;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */
/*     */
/*     */ import org.bukkit.block.Block;
/*     */
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.material.Door;

/*     */ public class HunterManager
/*     */   extends HumanManager
/*     */ {
    public enum ArrowType{
        NORMAL,POWER,FIRE,TRIPLE,GRAPPLE,DOUBLE;
    }
/*     */   public HunterManager(SupernaturalsPlugin instance)
/*     */   {
/*  54 */     super(instance);
/*     */   }
/*     */   
/*  57 */   private HashMap<Arrow, ArrowType> arrowMap = new HashMap();
/*  58 */   private HashMap<UUID, ArrowType> hunterMap = new HashMap();
                  //UID是猎人的UID
/*  59 */   private ArrayList<Player> grapplingPlayers = new ArrayList();
/*  60 */   private ArrayList<Player> drainedPlayers = new ArrayList();
/*  61 */   private ArrayList<Location> hallDoors = new ArrayList();
/*  62 */   private ArrayList<SuperNPlayer> playerInvites = new ArrayList();
/*  63 */   private static ArrayList<SuperNPlayer> bountyList = new ArrayList();
/*     */   
/*  65 */   private ArrowType arrowType=ArrowType.NORMAL;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double victimEvent(EntityDamageEvent event, double damage)
/*     */   {
/*  73 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
/*  74 */       damage -= SNConfigHandler.hunterFallReduction;
/*  75 */       if (damage < 0.0D) {
/*  76 */         damage = 0.0D;
/*     */       }
/*     */     }
/*  79 */     return damage;
/*     */   }
/*     */   
/*     */   public boolean shootArrow(Player shooter, EntityShootBowEvent event)
/*     */   {
/*  84 */     if ((event.getProjectile() instanceof Arrow)) {
/*  85 */       boolean cancelled = shoot(shooter, (Arrow)event.getProjectile());
/*  86 */       if (cancelled) {
/*  87 */         return true;
/*     */       }
/*     */     }
/*  90 */     return false;
/*     */   }
/*     */   
/*     */   public double damagerEvent(EntityDamageByEntityEvent event, double damage)
/*     */   {
/*  95 */     if ((event.getDamager() instanceof Projectile)) {
/*  96 */       Entity victim = event.getEntity();
/*  97 */       if ((event.getDamager() instanceof Arrow)) {
/*  98 */         Arrow arrow = (Arrow)event.getDamager();
/*  99 */         if (getArrowMap().containsKey(arrow)) {
/* 100 */           this.arrowType = getArrowType(arrow);
/*     */         } else {
/* 102 */           this.arrowType = ArrowType.NORMAL;
/*     */         }
/*     */       }
/* 105 */       if (this.arrowType==ArrowType.POWER) {
/* 106 */         damage += damage * SNConfigHandler.hunterPowerArrowDamage;
/* 107 */       } else if (this.arrowType==ArrowType.FIRE) {
/* 108 */         victim.setFireTicks(SNConfigHandler.hunterFireArrowFireTicks);
/*     */       }
/* 110 */       return damage;
/*     */     }
/* 112 */     Player pDamager = (Player)event.getDamager();
/* 113 */     SuperNPlayer snDamager = SuperNManager.get(pDamager);
/* 114 */     ItemStack item = pDamager.getItemInHand();
/*     */     
/*     */ 
/* 117 */     if ((item != null) && 
/* 118 */       (SNConfigHandler.hunterWeapons.contains(item.getType()))) {
/* 119 */       SuperNManager.sendMessage(snDamager, Language.WITCHHUNTER_LIMIT_WEAPON.toString());
/*     */       
/* 121 */       return 0.0D;
/*     */     }
/*     */     
/* 124 */     return damage;
/*     */   }
/*     */   
/*     */ 
/*     */   public void deathEvent(Player player)
/*     */   {
/* 130 */     if (player == null) {
/* 131 */       return;
/*     */     }
/* 133 */     super.deathEvent(player);
/* 134 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 135 */     SuperNManager.alterPower(snplayer, -SNConfigHandler.hunterDeathPowerPenalty, Language.YOU_DIE.toString());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void killEvent(Player pDamager, SuperNPlayer damager, SuperNPlayer victim)
/*     */   {
/* 143 */     if (victim == null) {
/* 144 */       if (SNConfigHandler.hunterKillPowerCreatureGain > 0) {
/* 145 */         SuperNManager.alterPower(damager, SNConfigHandler.hunterKillPowerCreatureGain, Language.KILL_CREATURE.toString());
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 150 */     else if (victim.getPower() > SNConfigHandler.hunterKillPowerPlayerGain) {
/* 151 */       SuperNManager.alterPower(damager, SNConfigHandler.hunterKillPowerPlayerGain, Language.KILL_PLAYER.toString());
/*     */       
/*     */ 
/* 154 */       if (checkBounty(victim)) {
/* 155 */         SuperNManager.alterPower(damager, SNConfigHandler.hunterBountyCompletion, Language.WITCHHUNTER_BOUNTY_GET.toString());
/*     */         
/*     */ 
/* 158 */         removeBounty(victim);
/* 159 */         addBounty();
/*     */       }
/*     */     } else {
/* 162 */       SuperNManager.sendMessage(damager, Language.NO_POWER_GAIN.toString());
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
/* 175 */     Action action = event.getAction();
/* 176 */     Player player = event.getPlayer();
/* 177 */     SuperNPlayer snplayer = SuperNManager.get(player);
/*     */     
/* 179 */     if ((action.equals(Action.LEFT_CLICK_AIR)) || (action.equals(Action.LEFT_CLICK_BLOCK)))
/*     */     {
/* 181 */       if (player.getItemInHand() == null) {
/* 182 */         return false;
/*     */       }
/*     */       
/* 185 */       if (player.getItemInHand().getType().equals(Material.BOW)) {
/* 186 */         changeArrowType(snplayer);
/* 187 */         return true;
/*     */       }
/*     */     }
/* 190 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void armorCheck(Player player)
/*     */   {
/* 199 */     if (!player.hasPermission("supernatural.player.ignorearmor")) {
/* 200 */       PlayerInventory inv = player.getInventory();
/* 201 */       ItemStack helmet = inv.getHelmet();
/* 202 */       ItemStack chest = inv.getChestplate();
/* 203 */       ItemStack leggings = inv.getLeggings();
/* 204 */       ItemStack boots = inv.getBoots();
/*     */       
/* 206 */       if ((helmet != null) && 
/* 207 */         (!SNConfigHandler.hunterArmor.contains(helmet.getType()))) {
/* 208 */         inv.setHelmet(null);
/* 209 */         dropItem(player, helmet);
/*     */       }
/*     */       
/* 212 */       if ((chest != null) && 
/* 213 */         (!SNConfigHandler.hunterArmor.contains(chest.getType()))) {
/* 214 */         inv.setChestplate(null);
/* 215 */         dropItem(player, chest);
/*     */       }
/*     */       
/* 218 */       if ((leggings != null) && 
/* 219 */         (!SNConfigHandler.hunterArmor.contains(leggings.getType()))) {
/* 220 */         inv.setLeggings(null);
/* 221 */         dropItem(player, leggings);
/*     */       }
/*     */       
/* 224 */       if ((boots != null) && 
/* 225 */         (!SNConfigHandler.hunterArmor.contains(boots.getType()))) {
/* 226 */         inv.setBoots(null);
/* 227 */         dropItem(player, boots);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ArrayList<SuperNPlayer> getBountyList()
/*     */   {
/* 238 */     return bountyList;
/*     */   }
/*     */   
/*     */   public static boolean checkBounty(SuperNPlayer snplayer) {
/* 242 */     return bountyList.contains(snplayer);
/*     */   }
/*     */   
/*     */   public static boolean removeBounty(SuperNPlayer snplayer) {
/* 246 */     if (bountyList.contains(snplayer)) {
/* 247 */       bountyList.remove(snplayer);
/* 248 */       return true;
/*     */     }
/* 250 */     return false;
/*     */   }
/*     */   
/*     */   public static void addBounty() {
/* 254 */     ArrayList targets = new ArrayList(SuperNManager.getSupernaturals());
/* 255 */     boolean bountyFound = false;
/* 256 */     Random generator = new Random();
/* 257 */     int count = 0;
/*     */     
/* 259 */     while (!bountyFound) {
/* 260 */       int randomIndex = generator.nextInt(targets.size());
/* 261 */       SuperNPlayer sntarget = (SuperNPlayer)targets.iterator();
/*     */       
/* 263 */       if ((!bountyList.contains(sntarget)) && (sntarget.isSuper())) {
/* 264 */         bountyList.add(sntarget);
/* 265 */         SupernaturalsPlugin.instance.getServer().broadcastMessage(Language.WITCHHUNTER_BOUNTY_ADD.toString().replace(LanguageTag.PLAYER.toString(), sntarget.getName()));
/*     */         
/*     */ 
/*     */ 
/* 269 */         bountyFound = true;
/* 270 */         return;
/*     */       }
/* 272 */       count++;
/* 273 */       if (count > 50) {
/* 274 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void updateBounties() {
/* 280 */     List<SuperNPlayer> snplayers = new ArrayList();
/*     */     
/* 282 */     if (bountyList.isEmpty()) {
/* 283 */       return;
/*     */     }
/*     */     
/* 286 */     for (SuperNPlayer snplayer : bountyList) {
/* 287 */       if (!snplayer.isSuper()) {
/* 288 */         snplayers.add(snplayer);
/*     */       }
/*     */     }
/*     */     
/* 292 */     for (SuperNPlayer snplayer : snplayers) {
/* 293 */       removeBounty(snplayer);
/* 294 */       addBounty();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void createBounties() {
/* 299 */     List<SuperNPlayer> targets = new ArrayList<>(SuperNManager.getSupernaturals());
/* 300 */     if (targets.size() == 0) {
/* 301 */       SupernaturalsPlugin.log(Level.WARNING, "No targets found for WitchHunters!");
/*     */       
/* 303 */       return;
/*     */     }
/*     */     
/* 306 */     int numberFound = 0;
/* 307 */     Random generator = new Random();
/* 308 */     int count = 0;
/*     */     
/* 310 */     while (numberFound < SNConfigHandler.hunterMaxBounties) {
/* 311 */       int randomIndex = generator.nextInt(targets.size());
/* 312 */       SuperNPlayer sntarget = (SuperNPlayer)targets.get(randomIndex);
/* 313 */       if ((!bountyList.contains(sntarget)) && (sntarget.isSuper())) {
/* 314 */         bountyList.add(sntarget);
/* 315 */         numberFound++;
/* 316 */         SupernaturalsPlugin.instance.getServer().broadcastMessage(Language.WITCHHUNTER_BOUNTY_ADD.toString().replace(LanguageTag.PLAYER.toString(), sntarget.getName()));
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 321 */       count++;
/* 322 */       if (count > 100) {
/* 323 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void addDoorLocation(Location location)
/*     */   {
/* 333 */     if (!this.hallDoors.contains(location)) {
/* 334 */       this.hallDoors.add(location);
/*     */     }
/*     */   }
/*     */   
/*     */   private void removeDoorLocation(Location location) {
/* 339 */     this.hallDoors.remove(location);
/*     */   }
/*     */   
/*     */   public boolean doorIsOpening(Location location) {
/* 343 */     return this.hallDoors.contains(location);
/*     */   }
/*     */   
/*     */   public boolean doorEvent(Player player, Block block, Door door) {
/* 347 */     if (door.isOpen()) {
/* 348 */       return true;
/*     */     }
/*     */     
/* 351 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 352 */     boolean open = false;
/*     */     
/* 354 */     final Location loc = block.getLocation();
/*     */     
/*     */ 
/*     */ 
/* 358 */     if (snplayer.isHuman()) {
/* 359 */       open = join(snplayer);
/*     */     }
/*     */     
/* 362 */     if ((snplayer.isHunter()) || ((snplayer.isHuman()) && (open))) { Location newLoc;
/* 363 */       if (door.isTopHalf()) {
/* 364 */          newLoc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ());
/*     */         
/* 366 */         Block newBlock = newLoc.getBlock();
/* 367 */         block.setTypeIdAndData(71, (byte)(block.getData() + 4), false);
/* 368 */         newBlock.setTypeIdAndData(71, (byte)(newBlock.getData() + 4), false);
/*     */       }
/*     */       else {
/* 371 */         newLoc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ());
/*     */         
/* 373 */         Block newBlock = newLoc.getBlock();
/* 374 */         block.setTypeIdAndData(71, (byte)(block.getData() + 4), false);
/* 375 */         newBlock.setTypeIdAndData(71, (byte)(newBlock.getData() + 4), false);
/*     */       }
/*     */       
/*     */ 
/* 379 */       addDoorLocation(loc);
/* 380 */       addDoorLocation(newLoc);
/*     */       
/* 382 */       SupernaturalsPlugin.instance.getServer().getScheduler().scheduleSyncDelayedTask(SupernaturalsPlugin.instance, new Runnable()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         public void run() {
/* 389 */           HunterManager.this.closeDoor(loc); } }, 20L);
/*     */       
/*     */ 
/* 392 */       return true;
/*     */     }
/* 394 */     SuperNManager.sendMessage(snplayer, Language.WITCHHUNTER_ONLY.toString());
/*     */     
/* 396 */     return true;
/*     */   }
/*     */   
/*     */   private void closeDoor(Location loc) {
/* 400 */     Block block = loc.getBlock();
/* 401 */     Door door = (Door)block.getState().getData();
/* 402 */     if (!door.isOpen()) {
/*     */       return;
/*     */     }
/*     */     
/*     */ 
/*     */     Location newLoc;
/*     */     
/* 409 */     if (door.isTopHalf()) {
/* 410 */         newLoc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ());
/*     */       
/* 412 */       Block newBlock = newLoc.getBlock();
/* 413 */       block.setTypeIdAndData(71, (byte)(block.getData() - 4), false);
/* 414 */       newBlock.setTypeIdAndData(71, (byte)(newBlock.getData() - 4), false);
/*     */     }
/*     */     else {
/* 417 */       newLoc = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ());
/*     */       
/* 419 */       Block newBlock = newLoc.getBlock();
/* 420 */       block.setTypeIdAndData(71, (byte)(block.getData() - 4), false);
/* 421 */       newBlock.setTypeIdAndData(71, (byte)(newBlock.getData() - 4), false);
/*     */     }
/*     */     
/*     */ 
/* 425 */     removeDoorLocation(loc);
/* 426 */     removeDoorLocation(newLoc);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void invite(final SuperNPlayer snplayer)
/*     */   {
/* 434 */     SupernaturalsPlugin.instance.getServer().getScheduler().scheduleSyncDelayedTask(SupernaturalsPlugin.instance, new Runnable()
/*     */     {
/*     */ 
/*     */ 
/*     */       public void run()
/*     */       {
/*     */ 
/* 441 */         SuperNManager.sendMessage(snplayer, Language.WITCHHUNTER_INVIT_GREETING.toString());
/*     */         
/*     */ 
/* 444 */         SuperNManager.sendMessage(snplayer, Language.WITCHHUNTER_INVIT_PROMPT.toString());
/*     */         
/*     */ 
/* 447 */         if (!HunterManager.this.playerInvites.contains(snplayer))
/* 448 */           HunterManager.this.playerInvites.add(snplayer); } }, 200L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean join(SuperNPlayer snplayer)
/*     */   {
/* 455 */     if (this.playerInvites.contains(snplayer)) {
/* 456 */       SuperNManager.sendMessage(snplayer, Language.WITCHHUNTER_INVIT_SUCCESS.toString());
/*     */       
/* 458 */       SuperNManager.convert(snplayer, SuperType.WITCHHUNTER, SNConfigHandler.hunterPowerStart);
/*     */       
/* 460 */       return true;
/*     */     }
/* 462 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean changeArrowType(SuperNPlayer snplayer)
/*     */   {
/* 470 */     ArrowType currentType =  this.hunterMap.get(snplayer);
/* 471 */     if (currentType == null) {
/* 472 */       currentType =ArrowType.NORMAL;
/*     */     }
/*     */     
/* 475 */     ArrowType nextType = ArrowType.NORMAL;
/*     */     
/* 477 */     for (int i = 0; i < SNConfigHandler.hunterArrowTypes.size(); i++) {
/* 478 */       if (((String)SNConfigHandler.hunterArrowTypes.get(i)).equalsIgnoreCase(currentType.name()))
/*     */       {
/* 480 */         int newI = i + 1;
/* 481 */         if (newI >= SNConfigHandler.hunterArrowTypes.size()) {
/* 482 */           newI = 0;
/*     */         }
/* 484 */         nextType =  ArrowType.valueOf(SNConfigHandler.hunterArrowTypes.get(newI));
/* 485 */         break;
/*     */       }
/*     */     }
/*     */     
/* 489 */     this.hunterMap.put(snplayer.getUuid(), nextType);
/* 490 */     SuperNManager.sendMessage(snplayer, Language.WITCHHUNTER_ARROW_CHANGE.toString().replace(LanguageTag.TYPE.toString(), nextType.name()));
/*     */     
/* 492 */     return true;
/*     */   }
/*     */   
/*     */   public ArrowType getArrowType(Arrow arrow) {
/* 496 */     return  this.arrowMap.get(arrow);
/*     */   }
/*     */   
/*     */   public HashMap<Arrow, ArrowType> getArrowMap() {
/* 500 */     return this.arrowMap;
/*     */   }
/*     */   
/*     */   public void removeArrow(final Arrow arrow) {
/* 504 */     SupernaturalsPlugin.instance.getServer().getScheduler().scheduleSyncDelayedTask(SupernaturalsPlugin.instance, new Runnable()
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       public void run() {
/* 511 */         HunterManager.this.arrowMap.remove(arrow); } }, 20L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean shoot(final Player player, final Arrow arrow)
/*     */   {
/* 522 */     final SuperNPlayer snplayer = SuperNManager.get(player);
/*     */     
/* 524 */     if (!player.getInventory().contains(Material.ARROW)) {
/* 525 */       return false;
/*     */     }
/*     */     
/* 528 */     if (!SupernaturalsPlugin.instance.getPvP(player)) {
/* 529 */       ArrowType arrowType =  this.hunterMap.get(snplayer);
/* 530 */       if (arrowType == null) {
/* 531 */         this.hunterMap.put(snplayer.getUuid(),ArrowType.NORMAL);
/* 532 */         return false;
/*     */       }
/* 534 */       if (arrowType!=ArrowType.NORMAL) {
/* 535 */         SuperNManager.sendMessage(snplayer, Language.NOT_ALLOW_PVP.toString());
/*     */       }
/*     */       
/* 538 */       return false;
/*     */     }
/*     */     
/* 541 */     if (this.drainedPlayers.contains(player)) {
/* 542 */       player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.ARROW, 1));
/*     */       
/* 544 */       SuperNManager.sendMessage(snplayer, Language.WITCHHUNTER_POWERARROW_COOLDOWN.toString());
/*     */       
/* 546 */       return true;
/*     */     }
/*     */     
/* 549 */     ArrowType arrowType =  this.hunterMap.get(snplayer);
/* 550 */     if (arrowType == null) {
/* 551 */       arrowType =ArrowType.NORMAL;
/*     */     }
/*     */     
/* 554 */     if (arrowType==ArrowType.FIRE) {
/* 555 */       if (snplayer.getPower() > SNConfigHandler.hunterPowerArrowFire) {
/* 556 */         SuperNManager.alterPower(snplayer, -SNConfigHandler.hunterPowerArrowFire, Language.WITCHHUNTER_FIREARROW_NOTICE_SELF.toString());
/*     */         
/*     */ 
/* 559 */         this.arrowMap.put(arrow, arrowType);
/* 560 */         arrow.setFireTicks(SNConfigHandler.hunterFireArrowFireTicks);
/* 561 */         return false;
/*     */       }
/* 563 */       SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/*     */       
/* 565 */       SuperNManager.sendMessage(snplayer, Language.WITCHHUNTER_ARROW_SWITCH_NORMAL.toString());
/*     */       
/* 567 */       this.hunterMap.put(snplayer.getUuid(), ArrowType.NORMAL);
/* 568 */       return false;
/*     */     }
/* 570 */     if (arrowType==ArrowType.TRIPLE) {
/* 571 */       if (snplayer.getPower() > SNConfigHandler.hunterPowerArrowTriple) {
/* 572 */         SuperNManager.alterPower(snplayer, -SNConfigHandler.hunterPowerArrowTriple, Language.WITCHHUNTER_TRIPLEARROW_NOTICE_SELF.toString());
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 577 */         this.arrowMap.put(arrow, arrowType);
/* 578 */         SupernaturalsPlugin.instance.getServer().getScheduler().scheduleSyncDelayedTask(SupernaturalsPlugin.instance, new Runnable()
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           public void run() {
/* 585 */             HunterManager.this.splitArrow(player, arrow); } }, 4L);
/*     */         
/*     */ 
/* 588 */         return false;
/*     */       }
/* 590 */       SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/*     */       
/* 592 */       SuperNManager.sendMessage(snplayer, Language.WITCHHUNTER_ARROW_SWITCH_NORMAL.toString());
/*     */       
/* 594 */       this.hunterMap.put(snplayer.getUuid(), ArrowType.NORMAL);
/* 595 */       return false;
/*     */     }
/* 597 */     if (arrowType==ArrowType.POWER) {
/* 598 */       if (snplayer.getPower() > SNConfigHandler.hunterPowerArrowPower) {
/* 599 */         SuperNManager.alterPower(snplayer, -SNConfigHandler.hunterPowerArrowPower, Language.WITCHHUNTER_POWERARROW_NOTICE_SELF.toString());
/*     */         
/*     */ 
/* 602 */         this.arrowMap.put(arrow, arrowType);
/* 603 */         this.drainedPlayers.add(player);
/* 604 */         SupernaturalsPlugin.instance.getServer().getScheduler().scheduleSyncDelayedTask(SupernaturalsPlugin.instance, new Runnable()
/*     */         {
/*     */ 
/*     */ 
/*     */           public void run()
/*     */           {
/*     */ 
/* 611 */             HunterManager.this.drainedPlayers.remove(player);
/* 612 */             if (player.isOnline()) {
/* 613 */               SuperNManager.sendMessage(snplayer, Language.WITCHHUNTER_POWERARROW_READY.toString());
/*     */             }
/*     */             
/*     */ 
/*     */ 
/*     */ 
/* 619 */             SupernaturalsPlugin.log(snplayer.getName() + " is no longer drained."); } }, SNConfigHandler.hunterCooldown / 50);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 624 */         return false;
/*     */       }
/* 626 */       SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/*     */       
/* 628 */       SuperNManager.sendMessage(snplayer, Language.WITCHHUNTER_ARROW_SWITCH_NORMAL.toString());
/*     */       
/* 630 */       this.hunterMap.put(snplayer.getUuid(), ArrowType.NORMAL);
/* 631 */       return false;
/*     */     }
/* 633 */     if (arrowType==ArrowType.GRAPPLE) {
/* 634 */       if (snplayer.getPower() > SNConfigHandler.hunterPowerArrowGrapple) {
/* 635 */         SuperNManager.alterPower(snplayer, -SNConfigHandler.hunterPowerArrowGrapple, Language.WITCHHUNTER_GRAPPLEARROW_NOTICE_SELF.toString());
/*     */         
/*     */ 
/*     */ 
/* 639 */         this.arrowMap.put(arrow, arrowType);
/* 640 */         return false;
/*     */       }
/* 642 */       SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/*     */       
/* 644 */       SuperNManager.sendMessage(snplayer, Language.WITCHHUNTER_ARROW_SWITCH_NORMAL.toString());
/*     */       
/* 646 */       this.hunterMap.put(snplayer.getUuid(),ArrowType.NORMAL);
/* 647 */       return false;
/*     */     }
/*     */     
/* 650 */     return false;
/*     */   }
/*     */   
/*     */   public void splitArrow(final Player player, final Arrow arrow)
/*     */   {
/* 655 */     ((Arrow)player.launchProjectile(Arrow.class)).setVelocity(arrow.getVelocity());
/* 656 */     ArrowType arrowType =  arrowMap.get(arrow);
/* 657 */     if (arrowType==ArrowType.GRAPPLE) {
/* 658 */       this.arrowMap.put(arrow, ArrowType.DOUBLE);
/* 659 */       SupernaturalsPlugin.instance.getServer().getScheduler().scheduleSyncDelayedTask(SupernaturalsPlugin.instance, new Runnable()
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         public void run() {
/* 666 */           HunterManager.this.splitArrow(player, arrow); } }, 4L);
/*     */     }
/*     */     else
/*     */     {
/* 670 */       this.arrowMap.remove(arrow);
/*     */     }
/*     */   }
/*     */ }

