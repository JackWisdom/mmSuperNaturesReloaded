/*     */ package com.mmiillkkaa.supernaturals.manager;
/*     */ 
/*     */ import com.mmiillkkaa.supernaturals.SuperNPlayer;
/*     */ import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
/*     */ import com.mmiillkkaa.supernaturals.io.SNConfigHandler;
/*     */ import com.mmiillkkaa.supernaturals.io.SNDataHandler;
/*     */ import com.mmiillkkaa.supernaturals.io.SNWhitelistHandler;
/*     */ import com.mmiillkkaa.supernaturals.util.EntityUtil;
/*     */ import com.mmiillkkaa.supernaturals.util.Language;
/*     */ import com.mmiillkkaa.supernaturals.util.LanguageTag;
/*     */ import com.mmiillkkaa.supernaturals.util.SNTaskTimer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Creature;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Wolf;
/*     */ import org.bukkit.scheduler.BukkitScheduler;
/*     */ import org.bukkit.util.Vector;
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
/*     */ public class SuperNManager
/*     */ {
/*     */   public static SupernaturalsPlugin plugin;
/*  51 */   public String worldPermission = "supernatural.world.enabled";
/*  52 */   public static String infPowerPermissions = "supernatural.admin.infinitepower";
/*     */   
/*  54 */   public static List<SuperNPlayer> supernaturals = new ArrayList();
/*  55 */   public transient int taskCounter = 0;
/*     */   public static int timer;
/*     */   
/*     */   public SuperNManager(SupernaturalsPlugin plugin) {
/*  59 */     plugin = plugin;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static List<SuperNPlayer> getSupernaturals()
/*     */   {
/*  67 */     return supernaturals;
/*     */   }
/*     */   
/*     */   public static void setSupernaturals(List<SuperNPlayer> supernaturals) {
/*  71 */     if (supernaturals != null) {
/*  72 */       supernaturals = supernaturals;
/*     */     }
/*     */   }
/*     */   
/*     */   public static SuperNPlayer get(String playername) {
/*  77 */     for (SuperNPlayer supernatural : supernaturals) {
/*  78 */       if (supernatural.getName().equalsIgnoreCase(playername)) {
/*  79 */         return supernatural;
/*     */       }
/*     */     }
/*     */     
/*  83 */     SuperNPlayer snplayer = new SuperNPlayer(playername);
/*  84 */     supernaturals.add(snplayer);
/*  85 */     return snplayer;
/*     */   }
/*     */   
/*     */   public static SuperNPlayer get(Player player) {
/*  89 */     for (SuperNPlayer supernatural : supernaturals) {
/*  90 */       if (supernatural.getName().equalsIgnoreCase(player.getName())) {
/*  91 */         return supernatural;
/*     */       }
/*     */     }
/*  94 */     SuperNPlayer snplayer = new SuperNPlayer(player.getName());
/*  95 */     supernaturals.add(snplayer);
/*  96 */     return snplayer;
/*     */   }
/*     */   
/*     */   public static Set<SuperNPlayer> findAllOnline() {
/* 100 */     Set<SuperNPlayer> snplayers = new HashSet();
/* 101 */     for (Player player : SupernaturalsPlugin.instance.getServer().getOnlinePlayers())
/*     */     {
/* 103 */       snplayers.add(get(player));
/*     */     }
/* 105 */     return snplayers;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void convert(SuperNPlayer snplayer, String superType)
/*     */   {
/* 113 */     convert(snplayer, superType, 0);
/*     */   }
/*     */   
/*     */   public static void convert(SuperNPlayer snplayer, String superType, int powerLevel)
/*     */   {
/* 118 */     if (!SNConfigHandler.supernaturalTypes.contains(superType)) {
/* 119 */       return;
/*     */     }
/* 121 */     if ((!SNWhitelistHandler.isWhitelisted(snplayer)) && (!snplayer.isHuman())) {
/* 122 */       sendMessage(snplayer, Language.SN_CMD_JOIN_NOT_USE.toString());
/*     */       
/* 124 */       return;
/*     */     }
/* 126 */     if ((SNConfigHandler.convertNode) && 
/* 127 */       (SupernaturalsPlugin.hasPermissions(snplayer, "supernatural.convert." + superType + ".allow")))
/*     */     {
/* 129 */       sendMessage(snplayer, Language.SN_CMD_JOIN_NO_PREMISSION.toString().replace(LanguageTag.TYPE.toString(), superType));
/*     */       
/*     */ 
/*     */ 
/* 133 */       return;
/*     */     }
/*     */     
/* 136 */     String type = superType.toLowerCase();
/* 137 */     snplayer.setOldType(snplayer.getType());
/* 138 */     snplayer.setOldPower(snplayer.getPower());
/*     */     
/* 140 */     snplayer.setType(type);
/* 141 */     if (SupernaturalsPlugin.hasPermissions(plugin.getServer().getPlayer(snplayer.getName()), infPowerPermissions))
/*     */     {
/*     */ 
/* 144 */       snplayer.setPower(10000.0D);
/*     */     } else {
/* 146 */       snplayer.setPower(powerLevel);
/*     */     }
/*     */     
/* 149 */     snplayer.setTruce(true);
/*     */     
/* 151 */     sendMessage(snplayer, Language.SN_ADMIN_CMD_CURE_NOTICE.toString().replace(LanguageTag.TYPE.toString(), superType));
/*     */     
/* 153 */     SupernaturalsPlugin.log(snplayer.getName() + " turned into a " + ChatColor.WHITE + superType + ChatColor.RED + "!");
/*     */     
/*     */ 
/* 156 */     updateName(snplayer);
/* 157 */     HunterManager.updateBounties();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 162 */     if (snplayer.getOldType().equals("werewolf")) {
/* 163 */       WereManager.removePlayer(snplayer);
/*     */     }
/* 165 */     SupernaturalsPlugin.instance.getGhoulManager().removeBond(snplayer);
/* 166 */     SupernaturalsPlugin.instance.getDataHandler().removeAngel(snplayer);
/*     */     
/* 168 */     SupernaturalsPlugin.saveData();
/*     */   }
/*     */   
/*     */   public static void cure(SuperNPlayer snplayer) {
/* 172 */     if (snplayer.getOldType().equals("priest")) {
/* 173 */       revert(snplayer);
/* 174 */       return;
/*     */     }
/* 176 */     snplayer.setOldType(snplayer.getType());
/* 177 */     snplayer.setOldPower(snplayer.getPower());
/*     */     
/* 179 */     snplayer.setType("human");
/* 180 */     snplayer.setPower(0.0D);
/*     */     
/* 182 */     snplayer.setTruce(true);
/*     */     
/* 184 */     updateName(snplayer);
/* 185 */     HunterManager.updateBounties();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 190 */     if (snplayer.getOldType().equals("werewolf")) {
/* 191 */       WereManager.removePlayer(snplayer);
/*     */     }
/* 193 */     SupernaturalsPlugin.instance.getGhoulManager().removeBond(snplayer);
/* 194 */     SupernaturalsPlugin.instance.getDataHandler().removeAngel(snplayer);
/*     */     
/* 196 */     sendMessage(snplayer, Language.SN_ADMIN_CMD_CURE_NOTICE.toString());
/*     */     
/* 198 */     SupernaturalsPlugin.log(snplayer.getName() + " was restored to humanity!");
/*     */     
/* 200 */     SupernaturalsPlugin.saveData();
/*     */   }
/*     */   
/*     */   public static void revert(SuperNPlayer snplayer) {
/* 204 */     String oldType = snplayer.getOldType();
/* 205 */     double oldPower = snplayer.getOldPower();
/*     */     
/* 207 */     snplayer.setOldType(snplayer.getType());
/* 208 */     snplayer.setOldPower(snplayer.getPower());
/*     */     
/* 210 */     snplayer.setType(oldType);
/* 211 */     snplayer.setPower(oldPower);
/*     */     
/* 213 */     snplayer.setTruce(true);
/*     */     
/* 215 */     updateName(snplayer);
/* 216 */     HunterManager.updateBounties();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 221 */     if (snplayer.getOldType().equals("werewolf")) {
/* 222 */       WereManager.removePlayer(snplayer);
/*     */     }
/* 224 */     SupernaturalsPlugin.instance.getGhoulManager().removeBond(snplayer);
/* 225 */     SupernaturalsPlugin.instance.getDataHandler().removeAngel(snplayer);
/*     */     
/* 227 */     sendMessage(snplayer, Language.SN_ADMIN_CMD_REVERT_NOTICE.toString().replace(LanguageTag.TYPE.toString(), oldType));
/*     */     
/* 229 */     SupernaturalsPlugin.log(snplayer.getName() + " was reverted to the previous state of being a " + oldType + "!");
/*     */     
/*     */ 
/* 232 */     SupernaturalsPlugin.saveData();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void alterPower(SuperNPlayer snplayer, double delta)
/*     */   {
/* 241 */     if (SupernaturalsPlugin.hasPermissions(SupernaturalsPlugin.instance.getServer().getPlayer(snplayer.getName()), infPowerPermissions))
/*     */     {
/*     */ 
/* 244 */       if (delta < 0.0D) {
/* 245 */         return;
/*     */       }
/*     */     }
/* 248 */     snplayer.setPower(snplayer.getPower() + delta);
/*     */   }
/*     */   
/*     */   public static void alterPower(SuperNPlayer snplayer, double delta, String reason)
/*     */   {
/* 253 */     if ((Bukkit.getServer().getPlayer(snplayer.getName()) != null) && (SupernaturalsPlugin.hasPermissions(Bukkit.getServer().getPlayer(snplayer.getName()), infPowerPermissions)))
/*     */     {
/*     */ 
/* 256 */       if (delta < 0.0D) {
/* 257 */         return;
/*     */       }
/*     */     }
/* 260 */     alterPower(snplayer, delta);
/* 261 */     sendMessage(snplayer, Language.SN_ADMIN_CMD_POWER_NOTICE.toString().replace(LanguageTag.AMOUNT.toString(), Double.toString(snplayer.getPower())).replace(LanguageTag.DELTA.toString(), Double.toString(delta)).replace(LanguageTag.REASON.toString(), reason));
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean jump(Player player, double deltaSpeed, boolean upOnly)
/*     */   {
/* 277 */     SuperNPlayer snplayer = get(player);
/*     */     
/* 279 */     if (upOnly) {
/* 280 */       if (snplayer.getPower() - SNConfigHandler.jumpBloodCost <= 0.0D) {
/* 281 */         sendMessage(snplayer, Language.NO_POWER.toString());
/*     */         
/* 283 */         return false;
/*     */       }
/* 285 */       alterPower(snplayer, -SNConfigHandler.jumpBloodCost, Language.ANGEL_SUPERJUMP_TRIGGER.toString());
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 290 */       if (snplayer.getPower() - SNConfigHandler.dashBloodCost <= 0.0D) {
/* 291 */         sendMessage(snplayer, Language.NO_POWER.toString());
/*     */         
/* 293 */         return false;
/*     */       }
/* 295 */       alterPower(snplayer, -SNConfigHandler.dashBloodCost, Language.WERWWOLF_DASH_TRIGGER.toString());
/*     */     }
/*     */     
/*     */     Vector vjadd;
/*     */     
/*     */     Vector vjadd;
/*     */     
/* 302 */     if (upOnly) {
/* 303 */       vjadd = new Vector(0, 1, 0);
/*     */     } else {
/* 305 */       Vector vhor = player.getLocation().getDirection();
/* 306 */       vjadd = new Vector(vhor.getX(), 0.0D, vhor.getZ());
/* 307 */       vjadd.normalize();
/*     */     }
/* 309 */     vjadd.multiply(deltaSpeed);
/*     */     
/* 311 */     player.setVelocity(player.getVelocity().add(vjadd));
/* 312 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean truceIsBroken(SuperNPlayer snplayer)
/*     */   {
/* 320 */     return snplayer.getTruce();
/*     */   }
/*     */   
/*     */   public void truceBreak(SuperNPlayer snplayer) {
/* 324 */     if (!snplayer.isSuper()) {
/* 325 */       snplayer.setTruce(true);
/* 326 */       return;
/*     */     }
/* 328 */     if (snplayer.getTruce()) {
/* 329 */       sendMessage(snplayer, Language.TRUCE_BREAK.toString());
/*     */     }
/*     */     
/* 332 */     snplayer.setTruce(false);
/* 333 */     snplayer.setTruceTimer(SNConfigHandler.truceBreakTime);
/*     */   }
/*     */   
/*     */   public static void truceRestore(SuperNPlayer snplayer) {
/* 337 */     sendMessage(snplayer, Language.TRUCE_RESTORE.toString());
/* 338 */     snplayer.setTruce(true);
/* 339 */     snplayer.setTruceTimer(0);
/*     */     
/*     */ 
/* 342 */     Player player = SupernaturalsPlugin.instance.getServer().getPlayer(snplayer.getName());
/*     */     
/* 344 */     for (LivingEntity entity : player.getWorld().getLivingEntities()) {
/* 345 */       if ((entity instanceof Creature))
/*     */       {
/*     */ 
/*     */ 
/* 349 */         if ((snplayer.isVampire()) && (SNConfigHandler.vampireTruce.contains(EntityUtil.entityTypeFromEntity(entity))))
/*     */         {
/*     */ 
/* 352 */           Creature creature = (Creature)entity;
/* 353 */           LivingEntity target = creature.getTarget();
/* 354 */           if ((target != null) && (creature.getTarget().equals(player))) {
/* 355 */             creature.setTarget(null);
/*     */           }
/* 357 */         } else if ((snplayer.isGhoul()) && (SNConfigHandler.ghoulTruce.contains(EntityUtil.entityTypeFromEntity(entity))))
/*     */         {
/*     */ 
/* 360 */           Creature creature = (Creature)entity;
/* 361 */           LivingEntity target = creature.getTarget();
/* 362 */           if ((target != null) && (creature.getTarget().equals(player))) {
/* 363 */             creature.setTarget(null);
/*     */           }
/* 365 */         } else if ((snplayer.isWere()) && (SNConfigHandler.wolfTruce) && ((entity instanceof Wolf)))
/*     */         {
/* 367 */           Creature creature = (Creature)entity;
/* 368 */           LivingEntity target = creature.getTarget();
/* 369 */           if ((target != null) && (creature.getTarget().equals(player)))
/* 370 */             creature.setTarget(null);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void truceBreakAdvanceTime(SuperNPlayer snplayer, int milliseconds) {
/* 377 */     if (snplayer.getTruce()) {
/* 378 */       return;
/*     */     }
/*     */     
/* 381 */     truceBreakTimeLeftAlter(snplayer, -milliseconds);
/*     */   }
/*     */   
/*     */   public int truceBreakTimeLeftGet(SuperNPlayer snplayer) {
/* 385 */     return snplayer.getTruceTimer();
/*     */   }
/*     */   
/*     */   private void truceBreakTimeLeftAlter(SuperNPlayer snplayer, int delta) {
/* 389 */     if (snplayer.getTruceTimer() + delta < 0) {
/* 390 */       truceRestore(snplayer);
/*     */     } else {
/* 392 */       snplayer.setTruceTimer(snplayer.getTruceTimer() + delta);
/*     */     }
/* 394 */     SupernaturalsPlugin.saveData();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void regenAdvanceTime(Player player, int milliseconds)
/*     */   {
/* 402 */     if (player.isDead()) {
/* 403 */       return;
/*     */     }
/*     */     
/* 406 */     SuperNPlayer snplayer = get(player);
/* 407 */     double currentHealth = player.getHealth();
/*     */     
/* 409 */     if (currentHealth == player.getMaxHealth()) {
/* 410 */       return;
/*     */     }
/*     */     
/* 413 */     double deltaSeconds = milliseconds / 1000.0D;
/*     */     
/*     */     double deltaHeal;
/* 416 */     if (snplayer.isVampire()) {
/* 417 */       if (snplayer.getPower() <= SNConfigHandler.vampireHealthCost) {
/* 418 */         return;
/*     */       }
/*     */       
/* 421 */       double deltaHeal = deltaSeconds * SNConfigHandler.vampireTimeHealthGained;
/* 422 */       alterPower(snplayer, -SNConfigHandler.vampireHealthCost, Language.HEALTH_REGEN.toString());
/*     */     }
/*     */     else {
/*     */       double deltaHeal;
/* 426 */       if (snplayer.isGhoul()) {
/* 427 */         if ((player.getWorld().hasStorm()) && (!plugin.getGhoulManager().isUnderRoof(player)))
/*     */         {
/* 429 */           return;
/*     */         }
/* 431 */         deltaHeal = deltaSeconds * SNConfigHandler.ghoulHealthGained;
/*     */       } else {
/* 433 */         if (!worldTimeIsNight(player)) {
/* 434 */           return;
/*     */         }
/* 436 */         deltaHeal = deltaSeconds * SNConfigHandler.wereHealthGained;
/*     */       }
/*     */     }
/* 439 */     double healthDelta = deltaHeal;
/* 440 */     double targetHealth = currentHealth + healthDelta;
/* 441 */     if (targetHealth > player.getMaxHealth()) {
/* 442 */       targetHealth = player.getMaxHealth();
/*     */     }
/*     */     try {
/* 445 */       player.setHealth(targetHealth);
/*     */     } catch (IllegalArgumentException e) {
/* 447 */       SupernaturalsPlugin.log("Attempted to regen dead player.");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Player getTarget(Player player)
/*     */   {
/* 456 */     List<Block> blocks = player.getLineOfSight(SNConfigHandler.transparent, 20);
/*     */     
/* 458 */     List<Entity> entities = player.getNearbyEntities(21.0D, 21.0D, 21.0D);
/* 459 */     for (Iterator i$ = blocks.iterator(); i$.hasNext();) { block = (Block)i$.next();
/* 460 */       for (Entity entity : entities) {
/* 461 */         if ((entity instanceof Player)) {
/* 462 */           Player victim = (Player)entity;
/* 463 */           Location location = victim.getLocation();
/* 464 */           Location feetLocation = new Location(location.getWorld(), location.getX(), location.getY() - 1.0D, location.getZ());
/*     */           
/*     */ 
/* 467 */           Location groundLocation = new Location(location.getWorld(), location.getX(), location.getY() - 2.0D, location.getZ());
/*     */           
/*     */ 
/* 470 */           if ((location.getBlock().equals(block)) || (feetLocation.getBlock().equals(block)) || (groundLocation.getBlock().equals(block)))
/*     */           {
/*     */ 
/* 473 */             return victim; }
/*     */         }
/*     */       }
/*     */     }
/*     */     Block block;
/* 478 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void sendMessage(SuperNPlayer snplayer, String message)
/*     */   {
/* 486 */     Player player = SupernaturalsPlugin.instance.getServer().getPlayer(snplayer.getName());
/*     */     
/* 488 */     if (!player.isOnline()) {
/* 489 */       return;
/*     */     }
/* 491 */     player.sendMessage(ChatColor.RED + message);
/*     */   }
/*     */   
/*     */   public static void sendMessage(SuperNPlayer snplayer, List<String> messages) {
/* 495 */     for (String message : messages) {
/* 496 */       sendMessage(snplayer, message);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void updateName(SuperNPlayer snplayer) {
/* 501 */     Player player = SupernaturalsPlugin.instance.getServer().getPlayer(snplayer.getName());
/*     */     
/* 503 */     String name = player.getName();
/* 504 */     String displayname = player.getDisplayName().trim();
/*     */     
/*     */     ChatColor color;
/*     */     ChatColor color;
/* 508 */     if (snplayer.isPriest()) {
/* 509 */       color = ChatColor.GOLD; } else { ChatColor color;
/* 510 */       if (snplayer.isVampire()) {
/* 511 */         color = ChatColor.DARK_PURPLE; } else { ChatColor color;
/* 512 */         if (snplayer.isGhoul()) {
/* 513 */           color = ChatColor.DARK_GRAY; } else { ChatColor color;
/* 514 */           if (snplayer.isWere()) {
/* 515 */             color = ChatColor.BLUE; } else { ChatColor color;
/* 516 */             if (snplayer.isHunter()) {
/* 517 */               color = ChatColor.GREEN; } else { ChatColor color;
/* 518 */               if (snplayer.isDemon()) {
/* 519 */                 color = ChatColor.RED; } else { ChatColor color;
/* 520 */                 if (snplayer.isAngel()) {
/* 521 */                   color = ChatColor.AQUA;
/*     */                 } else
/* 523 */                   color = ChatColor.WHITE; } } } } } }
/*     */     String updatedname;
/*     */     String updatedname;
/* 526 */     if (displayname.contains("ï¿½." + name)) {
/* 527 */       updatedname = displayname.replaceFirst(" ï¿½." + name, " " + color + name);
/*     */     }
/*     */     else {
/* 530 */       updatedname = displayname.replaceFirst(name, color + name);
/*     */     }
/*     */     
/* 533 */     if (SNConfigHandler.enableColors) {
/* 534 */       player.setDisplayName(updatedname);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean worldTimeIsNight(Player player)
/*     */   {
/* 543 */     long time = player.getWorld().getTime() % 24000L;
/*     */     
/* 545 */     return (time < 0L) || (time > 12400L);
/*     */   }
/*     */   
/*     */   public static void startTimer() {
/* 549 */     timer = SupernaturalsPlugin.instance.getServer().getScheduler().scheduleSyncRepeatingTask(SupernaturalsPlugin.instance, new SNTaskTimer(SupernaturalsPlugin.instance), 0L, 20L);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 554 */     if (timer == -1) {
/* 555 */       SupernaturalsPlugin.log(Level.WARNING, "Timer failed!");
/*     */     }
/*     */   }
/*     */   
/*     */   public static void cancelTimer() {
/* 560 */     SupernaturalsPlugin.instance.getServer().getScheduler().cancelTask(timer);
/*     */   }
/*     */   
/*     */   public void advanceTime(Set<SuperNPlayer> snplayers)
/*     */   {
/* 565 */     for (SuperNPlayer snplayer : snplayers) {
/* 566 */       Player player = plugin.getServer().getPlayer(snplayer.getName());
/*     */       
/* 568 */       if (player == null) {
/* 569 */         return;
/*     */       }
/*     */       
/* 572 */       if ((!SupernaturalsPlugin.hasPermissions(player, this.worldPermission)) && (SNConfigHandler.multiworld))
/*     */       {
/* 574 */         return;
/*     */       }
/*     */       
/* 577 */       this.taskCounter += 1;
/* 578 */       if (this.taskCounter >= 30) {
/* 579 */         this.taskCounter = 0;
/*     */       }
/*     */       
/* 582 */       if (snplayer.isVampire()) {
/* 583 */         if (this.taskCounter % 3 == 0) {
/* 584 */           regenAdvanceTime(player, 3000);
/*     */         }
/* 586 */         if (this.taskCounter % 3 == 0) {
/* 587 */           plugin.getVampireManager().combustAdvanceTime(player, 3000L);
/* 588 */           plugin.getVampireManager().gainPowerAdvanceTime(snplayer, 3000);
/*     */         }
/*     */       }
/* 591 */       else if (snplayer.isGhoul()) {
/* 592 */         if (this.taskCounter % 10 == 0) {
/* 593 */           regenAdvanceTime(player, 10000);
/*     */         }
/* 595 */         plugin.getGhoulManager().waterAdvanceTime(player);
/* 596 */       } else if (snplayer.isWere()) {
/* 597 */         if (this.taskCounter % 5 == 0) {
/* 598 */           regenAdvanceTime(player, 5000);
/*     */         }
/* 600 */       } else if ((snplayer.isAngel()) && 
/* 601 */         (this.taskCounter % 10 == 0)) {
/* 602 */         plugin.getAngelManager().waterAdvanceTime(player);
/*     */       }
/*     */       
/*     */ 
/* 606 */       plugin.getClassManager(player).armorCheck(player);
/*     */       
/* 608 */       if ((snplayer.isDemon()) && 
/* 609 */         (this.taskCounter % 5 == 0)) {
/* 610 */         plugin.getDemonManager().powerAdvanceTime(player, 5);
/*     */       }
/*     */       
/*     */ 
/* 614 */       if ((snplayer.isSuper()) && 
/* 615 */         (this.taskCounter % 3 == 0)) {
/* 616 */         truceBreakAdvanceTime(snplayer, 3000);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\manager\SuperNManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */