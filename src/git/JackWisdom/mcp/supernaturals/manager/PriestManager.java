/*     */ package git.JackWisdom.mcp.supernaturals.manager;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*     */ import git.JackWisdom.mcp.supernaturals.SuperType;
import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */ import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
/*     */
/*     */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*     */ import git.JackWisdom.mcp.supernaturals.util.LanguageTag;
/*     */
/*     */
/*     */
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Animals;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Monster;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Wolf;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
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
/*     */ public class PriestManager
/*     */   extends HumanManager
/*     */ {
/*     */   public SupernaturalsPlugin plugin;
/*     */   
/*     */   public PriestManager(SupernaturalsPlugin instance)
/*     */   {
/*  49 */     super(instance);
/*  50 */     this.plugin = instance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double victimEvent(EntityDamageEvent event, double damage)
/*     */   {
/*  59 */     return damage;
/*     */   }
/*     */   
/*     */   public double damagerEvent(EntityDamageByEntityEvent event, double damage)
/*     */   {
/*  64 */     Player pDamager = (Player)event.getDamager();
/*  65 */     Entity victim = event.getEntity();
/*     */     
/*  67 */     SuperNPlayer snDamager = SuperNManager.get(pDamager);
/*  68 */     ItemStack item = pDamager.getItemInHand();
/*     */     
/*  70 */     if ((item != null) && 
/*  71 */       (SNConfigHandler.priestWeapons.contains(item.getType()))) {
/*  72 */       SuperNManager.sendMessage(snDamager, Language.PRIEST_LIMIT_WEAPON.toString());
/*     */       
/*  74 */       return 0.0D;
/*     */     }
/*     */     
/*     */ 
/*  78 */     if (((victim instanceof Animals)) && (!(victim instanceof Wolf))) {
/*  79 */       SuperNManager.sendMessage(SuperNManager.get(pDamager), Language.PRIEST_LIMIT_TARGET.toString());
/*     */       
/*  81 */       damage = 0.0D;
/*  82 */     } else if ((victim instanceof Player)) {
/*  83 */       Player pVictim = (Player)victim;
/*  84 */       if (!SupernaturalsPlugin.instance.getPvP(pVictim)) {
/*  85 */         return damage;
/*     */       }
/*  87 */       SuperNPlayer snvictim = SuperNManager.get(pVictim);
/*  88 */       if (snvictim.isSuper()) {
/*  89 */         if (!snvictim.isDemon()) {
/*  90 */           pVictim.setFireTicks(SNConfigHandler.priestFireTicks);
/*     */         }
/*  92 */         damage += damage * SuperNManager.get(pDamager).scale(SNConfigHandler.priestDamageFactorAttackSuper);
/*     */       }
/*     */       else
/*     */       {
/*  96 */         damage += damage * SuperNManager.get(pDamager).scale(SNConfigHandler.priestDamageFactorAttackHuman);
/*     */       }
/*     */       
/*     */     }
/* 100 */     else if ((victim instanceof Monster)) {
/* 101 */       Monster mVictim = (Monster)victim;
/* 102 */       mVictim.setFireTicks(SNConfigHandler.priestFireTicks);
/*     */     }
/* 104 */     return damage;
/*     */   }
/*     */   
/*     */   public void deathEvent(Player player)
/*     */   {
/* 109 */     super.deathEvent(player);
/* 110 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 111 */     SuperNManager.alterPower(snplayer, -SNConfigHandler.priestDeathPowerPenalty, Language.YOU_DIE.toString());
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
/*     */ 
/*     */   public boolean playerInteract(PlayerInteractEvent event)
/*     */   {
/* 128 */     Action action = event.getAction();
/* 129 */     Player player = event.getPlayer();
/* 130 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 131 */     Material itemMaterial = event.getMaterial();
/*     */     
/* 133 */     if ((action.equals(Action.LEFT_CLICK_AIR)) || (action.equals(Action.LEFT_CLICK_BLOCK)))
/*     */     {
/* 135 */       if (itemMaterial.equals(Material.BOWL)) {
/* 136 */         remoteDonations(player);
/* 137 */         return true;
/*     */       }
/*     */     }
/*     */     
/* 141 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void armorCheck(Player player)
/*     */   {
/* 150 */     if (!player.hasPermission("supernatural.player.ignorearmor")) {
/* 151 */       PlayerInventory inv = player.getInventory();
/* 152 */       ItemStack helmet = inv.getHelmet();
/* 153 */       ItemStack chest = inv.getChestplate();
/* 154 */       ItemStack leggings = inv.getLeggings();
/* 155 */       ItemStack boots = inv.getBoots();
/*     */       
/* 157 */       if ((helmet != null) && 
/* 158 */         (!SNConfigHandler.priestArmor.contains(helmet.getType()))) {
/* 159 */         inv.setHelmet(null);
/* 160 */         dropItem(player, helmet);
/*     */       }
/*     */       
/* 163 */       if ((chest != null) && 
/* 164 */         (!SNConfigHandler.priestArmor.contains(chest.getType()))) {
/* 165 */         inv.setChestplate(null);
/* 166 */         dropItem(player, chest);
/*     */       }
/*     */       
/* 169 */       if ((leggings != null) && 
/* 170 */         (!SNConfigHandler.priestArmor.contains(leggings.getType()))) {
/* 171 */         inv.setLeggings(null);
/* 172 */         dropItem(player, leggings);
/*     */       }
/*     */       
/* 175 */       if ((boots != null) && 
/* 176 */         (!SNConfigHandler.priestArmor.contains(boots.getType()))) {
/* 177 */         inv.setBoots(null);
/* 178 */         dropItem(player, boots);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void useAltar(Player player)
/*     */   {
/* 190 */     Location location = player.getLocation();
/* 191 */     World world = location.getWorld();
/* 192 */     int locX = location.getBlockX();
/* 193 */     int locY = location.getBlockY();
/* 194 */     int locZ = location.getBlockZ();
/* 195 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 196 */     int amount = 0;
/* 197 */     int delta = 0;
/* 198 */     if ((world.getName().equalsIgnoreCase(SNConfigHandler.priestChurchWorld)) && 
/* 199 */       (Math.abs(locX - SNConfigHandler.priestChurchLocationX) <= 10) && 
/* 200 */       (Math.abs(locY - SNConfigHandler.priestChurchLocationY) <= 10) && 
/* 201 */       (Math.abs(locZ - SNConfigHandler.priestChurchLocationZ) <= 10)) {
/* 202 */       if (snplayer.isPriest()) {
/* 203 */         if (player.getItemInHand().getType().equals(Material.COAL))
/*     */         {
/* 205 */           SuperNManager.sendMessage(snplayer, Language.PRIEST_LEAVE_CRUSH.toString());
/*     */           
/* 207 */           SuperNManager.cure(snplayer);
/*     */         } else {
/* 209 */           PlayerInventory inv = player.getInventory();
/* 210 */           ItemStack[] items = inv.getContents();
/* 211 */           for (Material mat : SNConfigHandler.priestDonationMap.keySet())
/*     */           {
/* 213 */             for (ItemStack itemStack : items) {
/* 214 */               if ((itemStack != null) && 
/* 215 */                 (itemStack.getType().equals(mat))) {
/* 216 */                 amount += itemStack.getAmount();
/*     */               }
/*     */             }
/*     */             
/* 220 */             delta += amount * ((Integer)SNConfigHandler.priestDonationMap.get(mat)).intValue();
/*     */             
/*     */ 
/* 223 */             amount = 0;
/*     */           }
/* 225 */           for (Material mat : SNConfigHandler.priestDonationMap.keySet())
/*     */           {
/* 227 */             inv.remove(mat);
/*     */           }
/* 229 */           player.updateInventory();
/* 230 */           SuperNManager.sendMessage(snplayer, Language.PRIEST_DONATE_ACCEPT.toString());
/*     */           
/*     */ 
/* 233 */           SuperNManager.alterPower(snplayer, delta, Language.DAEMON_SNARE_NOTICE_SELF.toString());
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 238 */         SuperNManager.sendMessage(snplayer, Language.PRIEST_ALTAR_POWER_HUMAN.toString());
/*     */         
/*     */ 
/* 241 */         if (snplayer.isSuper()) {
/* 242 */           SuperNManager.sendMessage(snplayer, Language.PRIEST_ALTAR_POWER_SUPERNATURAL.toString());
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 247 */           EntityDamageEvent event = new EntityDamageEvent(player, EntityDamageEvent.DamageCause.BLOCK_EXPLOSION, 20);
/*     */           
/* 249 */           player.setLastDamageCause(event);
/* 250 */           player.setHealth(0.0D);
/* 251 */           if (snplayer.isGhoul()) {
/* 252 */             double random = Math.random();
/* 253 */             if (random < SNConfigHandler.ghoulCureChance - 0.1D) {
/* 254 */               SuperNManager.cure(snplayer);
/*     */             }
/*     */           }
/* 257 */           return;
/*     */         }
/* 259 */         if (SNConfigHandler.priestAltarRecipe.playerHasEnough(player))
/*     */         {
/* 261 */           if (!player.hasPermission( "supernatural.player.shrineuse.priest"))
/*     */           {
/* 263 */             SuperNManager.sendMessage(snplayer, Language.PRIEST_ALTAR_NOT_ALLOW.toString());
/*     */             
/*     */ 
/* 266 */             return;
/*     */           }
/* 268 */           SuperNManager.sendMessage(snplayer, Language.PRIEST_DONATE_CONFIRM.toString());
/*     */           
/*     */ 
/* 271 */           SuperNManager.sendMessage(snplayer, SNConfigHandler.priestAltarRecipe.getRecipeLine());
/*     */           
/*     */ 
/* 274 */           SuperNManager.sendMessage(snplayer, Language.PRIEST_DONATE_ENOUGHT.toString());
/*     */           
/*     */ 
/* 277 */           SNConfigHandler.priestAltarRecipe.removeFromPlayer(player);
/*     */           
/* 279 */           SuperNManager.convert(snplayer, SuperType.PRIEST, SNConfigHandler.priestPowerStart);
/*     */         }
/*     */         else {
/* 282 */           SuperNManager.sendMessage(snplayer, Language.PRIEST_DONATE_NOT_ENOGHT.toString());
/*     */           
/*     */ 
/* 285 */           SuperNManager.sendMessage(snplayer, SNConfigHandler.priestAltarRecipe.getRecipeLine());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void remoteDonations(Player player)
/*     */   {
/* 298 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 299 */     PlayerInventory inv = player.getInventory();
/* 300 */     ItemStack[] items = inv.getContents();
/* 301 */     double delta = 0.0D;
/* 302 */     for (Material mat : SNConfigHandler.priestDonationMap.keySet())
/*     */     {
/* 304 */       for (ItemStack itemStack : items) {
/* 305 */         if ((itemStack != null) && 
/* 306 */           (itemStack.getType().equals(mat))) {
/* 307 */           delta = ((Integer)SNConfigHandler.priestDonationMap.get(mat)).intValue();
/* 308 */           if (itemStack.getAmount() == 1) {
/* 309 */             inv.clear(inv.first(itemStack.getType()));
/*     */             break ; }
/* 311 */           itemStack.setAmount(itemStack.getAmount() - 1);
/*     */           
/*     */           break  ;
/*     */         }
/*     */       }
/*     */     }
/*     */     label172:
/* 318 */     if (delta == 0.0D) {
/* 319 */       SuperNManager.sendMessage(snplayer, Language.PRIEST_DONATE_ONLY.toString());
/*     */     }
/*     */     else {
/* 322 */       player.updateInventory();
/* 323 */       SuperNManager.sendMessage(snplayer, Language.PRIEST_DONATE_REWARD.toString());
/*     */       
/* 325 */       SuperNManager.alterPower(snplayer, delta * 0.5D, Language.PRIEST_DONATE_NOTICE_SELF.toString());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void spellEvent(EntityDamageByEntityEvent event, Player target)
/*     */   {
/* 336 */     Player player = (Player)event.getDamager();
/* 337 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 338 */     Material itemMaterial = player.getItemInHand().getType();
/*     */     
/* 340 */     boolean cancelled = false;
/*     */     
/* 342 */     if (player.getItemInHand() == null) {
/* 343 */       return;
/*     */     }
/*     */     
/* 346 */     if (itemMaterial != null) {
/* 347 */       if (SNConfigHandler.priestSpellMaterials.contains(itemMaterial)) {
/* 348 */         if (itemMaterial.equals(SNConfigHandler.priestSpellMaterials.get(0)))
/*     */         {
/* 350 */           banish(player, target);
/* 351 */           cancelled = true;
/* 352 */         } else if (itemMaterial.equals(SNConfigHandler.priestSpellMaterials.get(1)))
/*     */         {
/* 354 */           exorcise(player, target);
/* 355 */           cancelled = true;
/* 356 */         } else if (itemMaterial.equals(SNConfigHandler.priestSpellMaterials.get(2)))
/*     */         {
/* 358 */           cancelled = cure(player, target, itemMaterial);
/* 359 */         } else if (itemMaterial.equals(SNConfigHandler.priestSpellMaterials.get(3)))
/*     */         {
/* 361 */           cancelled = heal(player, target);
/* 362 */         } else if (itemMaterial.equals(SNConfigHandler.priestSpellMaterials.get(4)))
/*     */         {
/* 364 */           drainPower(player, target);
/* 365 */           cancelled = true;
/*     */         }
/* 367 */         if (!event.isCancelled()) {
/* 368 */           event.setCancelled(cancelled);
/*     */         }
/* 370 */       } else if (itemMaterial.toString().equalsIgnoreCase(SNConfigHandler.priestSpellGuardianAngel))
/*     */       {
/* 372 */         cancelled = guardianAngel(player, target);
/* 373 */         if (!event.isCancelled()) {
/* 374 */           event.setCancelled(cancelled);
/*     */         }
/* 376 */       } else if (itemMaterial.equals(Material.BOWL)) {
/* 377 */         remoteDonations(player);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean banish(Player player, Player victim) {
/* 383 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 384 */     SuperNPlayer snvictim = SuperNManager.get(victim);
/* 385 */     if (!SupernaturalsPlugin.instance.getPvP(victim)) {
/* 386 */       SuperNManager.sendMessage(snplayer, Language.NOT_ALLOW_PVP.toString());
/*     */       
/* 388 */       return false;
/*     */     }
/* 390 */     if (snplayer.getPower() > SNConfigHandler.priestPowerBanish) {
/* 391 */       if (snvictim.isSuper()) {
/* 392 */         SuperNManager.alterPower(snplayer, -SNConfigHandler.priestPowerBanish, Language.PRIEST_BANISH_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), victim.getName()));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 398 */         SuperNManager.sendMessage(snvictim, Language.PRIEST_BANISH_NOTICE_OTHER.toString().replace(LanguageTag.PLAYER.toString(), snplayer.getName()));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 403 */         victim.teleport(SNConfigHandler.priestBanishLocation);
/* 404 */         ItemStack item = player.getItemInHand();
/* 405 */         if (item.getAmount() == 1) {
/* 406 */           player.setItemInHand(null);
/*     */         } else {
/* 408 */           item.setAmount(player.getItemInHand().getAmount() - 1);
/*     */         }
/* 410 */         return true;
/*     */       }
/* 412 */       SuperNManager.sendMessage(snplayer, Language.ONLY_ON_SUPERNATUTAL.toString());
/*     */       
/* 414 */       return false;
/*     */     }
/* 416 */     SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/* 417 */     return false;
/*     */   }
/*     */   
/*     */   public boolean heal(Player player, Player victim)
/*     */   {
/* 422 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 423 */     SuperNPlayer snvictim = SuperNManager.get(victim);
/* 424 */     if (snplayer.getPower() > SNConfigHandler.priestPowerHeal) {
/* 425 */       if ((!snvictim.isSuper()) && (victim.getHealth() < victim.getMaxHealth()) && (!victim.isDead()))
/*     */       {
/*     */ 
/* 428 */         SuperNManager.alterPower(snplayer, -SNConfigHandler.priestPowerHeal, Language.PRIEST_HEAL_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), victim.getName()));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 434 */         SuperNManager.sendMessage(snvictim, Language.PRIEST_HEAL_NOTICE_OTHER.toString().replace(LanguageTag.PLAYER.toString(), snplayer.getName()));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 439 */         double health = victim.getHealth() + SNConfigHandler.priestHealAmount;
/*     */         
/* 441 */         if (health > victim.getMaxHealth()) {
/* 442 */           health = victim.getMaxHealth();
/*     */         }
/* 444 */         victim.setHealth(health);
/* 445 */         ItemStack item = player.getItemInHand();
/* 446 */         if (item.getAmount() == 1) {
/* 447 */           player.setItemInHand(null);
/*     */         } else {
/* 449 */           item.setAmount(player.getItemInHand().getAmount() - 1);
/*     */         }
/* 451 */         return true;
/*     */       }
/* 453 */       SuperNManager.sendMessage(snplayer, Language.ONLY_ON_PLAYER.toString());
/*     */       
/* 455 */       return false;
/*     */     }
/*     */     
/* 458 */     SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/* 459 */     return false;
/*     */   }
/*     */   
/*     */   public boolean exorcise(Player player, Player victim)
/*     */   {
/* 464 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 465 */     SuperNPlayer snvictim = SuperNManager.get(victim);
/* 466 */     if (!SupernaturalsPlugin.instance.getPvP(victim)) {
/* 467 */       SuperNManager.sendMessage(snplayer, Language.NOT_ALLOW_PVP.toString());
/*     */       
/* 469 */       return false;
/*     */     }
/* 471 */     if (snplayer.getPower() > SNConfigHandler.priestPowerExorcise) {
/* 472 */       if (snvictim.isSuper()) {
/* 473 */         SuperNManager.alterPower(snplayer, -SNConfigHandler.priestPowerExorcise, Language.PRIEST_EXORISE_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), victim.getName()));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 479 */         SuperNManager.sendMessage(snvictim, Language.PRIEST_EXORISE_NOTICE_OTHER.toString().replace(LanguageTag.PLAYER.toString(), snplayer.getName()));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 484 */         SuperNManager.cure(snvictim);
/* 485 */         ItemStack item = player.getItemInHand();
/* 486 */         if (item.getAmount() == 1) {
/* 487 */           player.setItemInHand(null);
/*     */         } else {
/* 489 */           item.setAmount(player.getItemInHand().getAmount() - 1);
/*     */         }
/* 491 */         return true;
/*     */       }
/* 493 */       SuperNManager.sendMessage(snplayer, Language.ONLY_ON_SUPERNATUTAL.toString());
/*     */       
/* 495 */       return false;
/*     */     }
/*     */     
/* 498 */     SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/* 499 */     return false;
/*     */   }
/*     */   
/*     */   public boolean cure(Player player, Player victim, Material material)
/*     */   {
/* 504 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 505 */     SuperNPlayer snvictim = SuperNManager.get(victim);
/* 506 */     if (snplayer.getPower() > SNConfigHandler.priestPowerCure) {
/* 507 */       if (snvictim.isSuper()) {
/* 508 */         if (victim.getItemInHand().getType().equals(material)) {
/* 509 */           SuperNManager.alterPower(snplayer, -SNConfigHandler.priestPowerCure, Language.PRIEST_CURE_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), victim.getName()));
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 515 */           SuperNManager.sendMessage(snvictim, Language.PRIEST_CURE_NOTICE_OTHER.toString().replace(LanguageTag.PLAYER.toString(), snplayer.getName()));
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 520 */           SuperNManager.cure(snvictim);
/* 521 */           ItemStack item = player.getItemInHand();
/* 522 */           ItemStack item2 = victim.getItemInHand();
/* 523 */           if (item.getAmount() == 1) {
/* 524 */             player.setItemInHand(null);
/*     */           } else {
/* 526 */             item.setAmount(player.getItemInHand().getAmount() - 1);
/*     */           }
/* 528 */           if (item2.getAmount() == 1) {
/* 529 */             victim.setItemInHand(null);
/*     */           } else {
/* 531 */             item2.setAmount(victim.getItemInHand().getAmount() - 1);
/*     */           }
/* 533 */           return true;
/*     */         }
/* 535 */         SuperNManager.sendMessage(snplayer, Language.PRIEST_CURE_NOT_HOLD.toString().replace(LanguageTag.PLAYER.toString(), snvictim.getName()).replace(LanguageTag.MATERIAL.toString(), material.toString()));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 543 */         return false;
/*     */       }
/*     */       
/* 546 */       SuperNManager.sendMessage(snplayer, Language.ONLY_ON_SUPERNATUTAL.toString());
/*     */       
/* 548 */       return false;
/*     */     }
/*     */     
/* 551 */     SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/* 552 */     return false;
/*     */   }
/*     */   
/*     */   public boolean drainPower(Player player, Player victim)
/*     */   {
/* 557 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 558 */     SuperNPlayer snvictim = SuperNManager.get(victim);
/* 559 */     if (!SupernaturalsPlugin.instance.getPvP(victim)) {
/* 560 */       SuperNManager.sendMessage(snplayer, Language.NOT_ALLOW_PVP.toString());
/*     */       
/* 562 */       return false;
/*     */     }
/* 564 */     if (snplayer.getPower() > SNConfigHandler.priestPowerDrain) {
/* 565 */       if (snvictim.isSuper()) {
/* 566 */         double power = snvictim.getPower();
/* 567 */         power *= SNConfigHandler.priestDrainFactor;
/* 568 */         SuperNManager.alterPower(snplayer, -SNConfigHandler.priestPowerDrain, Language.PRIEST_DRAIN_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), snvictim.getName()));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 574 */         SuperNManager.alterPower(snvictim, -power, Language.PRIEST_DRAIN_NOTICE_OTHER.toString().replace(LanguageTag.PLAYER.toString(), snplayer.getName()));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 580 */         ItemStack item = player.getItemInHand();
/* 581 */         if (item.getAmount() == 1) {
/* 582 */           player.setItemInHand(null);
/*     */         } else {
/* 584 */           item.setAmount(player.getItemInHand().getAmount() - 1);
/*     */         }
/* 586 */         return true;
/*     */       }
/* 588 */       SuperNManager.sendMessage(snplayer, Language.ONLY_ON_SUPERNATUTAL.toString());
/*     */       
/* 590 */       return false;
/*     */     }
/*     */     
/* 593 */     SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/* 594 */     return false;
/*     */   }
/*     */   
/*     */   public boolean guardianAngel(Player player, Player victim)
/*     */   {
/* 599 */     SuperNPlayer priest = SuperNManager.get(player);
/* 600 */     SuperNPlayer snvictim = SuperNManager.get(victim);
/*     */     
/* 602 */     if (priest.getPower() > SNConfigHandler.priestPowerGuardianAngel) {
/* 603 */       if (!snvictim.isSuper()) {
/* 604 */         if (SupernaturalsPlugin.instance.getDataHandler().hasAngel(priest))
/*     */         {
/* 606 */           SuperNManager.sendMessage(priest, Language.PRIEST_GUARDANGEL_REMOVE_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), SupernaturalsPlugin.instance.getDataHandler().getAngelPlayer(priest).getName()));
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 615 */           SuperNManager.sendMessage(SupernaturalsPlugin.instance.getDataHandler().getAngelPlayer(priest), Language.PRIEST_GUARDANGEL_REMOVE_NOTICE_OTHER.toString());
/*     */           
/*     */ 
/*     */ 
/* 619 */           SupernaturalsPlugin.instance.getDataHandler().removeAngel(priest);
/*     */         }
/*     */         
/* 622 */         SuperNManager.sendMessage(snvictim, Language.PRIEST_GUARDANGEL_NOTICE_OTHER.toString());
/*     */         
/* 624 */         SuperNManager.alterPower(priest, -SNConfigHandler.priestPowerGuardianAngel, Language.PRIEST_GUARDANGEL_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), snvictim.getName()));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 630 */         SupernaturalsPlugin.instance.getDataHandler().addAngel(priest, snvictim);
/*     */         
/*     */ 
/* 633 */         ItemStack item = player.getItemInHand();
/* 634 */         if (item.getAmount() == 1) {
/* 635 */           player.setItemInHand(null);
/*     */         } else {
/* 637 */           item.setAmount(player.getItemInHand().getAmount() - 1);
/*     */         }
/* 639 */         return true;
/*     */       }
/* 641 */       SuperNManager.sendMessage(priest, Language.ONLY_ON_PLAYER.toString());
/*     */       
/* 643 */       return false;
/*     */     }
/* 645 */     SuperNManager.sendMessage(priest, Language.NO_POWER.toString());
/* 646 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\manager\PriestManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */