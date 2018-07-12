/*     */ package com.mmiillkkaa.supernaturals.manager;
/*     */ 
/*     */ import com.mmiillkkaa.supernaturals.SuperNPlayer;
/*     */ import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
/*     */ import com.mmiillkkaa.supernaturals.io.SNConfigHandler;
/*     */ import com.mmiillkkaa.supernaturals.util.Language;
/*     */ import com.mmiillkkaa.supernaturals.util.LanguageTag;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Boat;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GhoulManager
/*     */   extends ClassManager
/*     */ {
/*  51 */   private String permissions = "supernatural.player.preventwaterdamage";
/*  52 */   private HashMap<SuperNPlayer, SuperNPlayer> bonds = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double victimEvent(EntityDamageEvent event, double damage)
/*     */   {
/*  60 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
/*  61 */       event.setCancelled(true);
/*  62 */       return 0.0D; }
/*  63 */     if ((event instanceof EntityDamageByEntityEvent)) {
/*  64 */       EntityDamageByEntityEvent edbeEvent = (EntityDamageByEntityEvent)event;
/*  65 */       Entity damager = edbeEvent.getDamager();
/*  66 */       if ((damager instanceof Player)) {
/*  67 */         Player pDamager = (Player)damager;
/*  68 */         SuperNPlayer snDamager = SuperNManager.get(pDamager);
/*  69 */         Player victim = (Player)event.getEntity();
/*  70 */         SuperNPlayer snVictim = SuperNManager.get(victim);
/*  71 */         ItemStack item = pDamager.getItemInHand();
/*     */         
/*  73 */         if (item != null) {
/*  74 */           if (SNConfigHandler.ghoulWeaponImmunity.contains(item.getType()))
/*     */           {
/*  76 */             damage = 0.0D;
/*  77 */             SuperNManager.sendMessage(snDamager, Language.GHOUL_IMMUNE_WEAPON.toString());
/*     */           }
/*     */           else {
/*  80 */             damage -= damage * snVictim.scale(1.0D - SNConfigHandler.ghoulDamageReceivedFactor);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  87 */     return damage;
/*     */   }
/*     */   
/*     */   public double damagerEvent(EntityDamageByEntityEvent event, double damage)
/*     */   {
/*  92 */     Entity damager = event.getDamager();
/*  93 */     Player pDamager = (Player)damager;
/*  94 */     SuperNPlayer snDamager = SuperNManager.get(pDamager);
/*  95 */     ItemStack item = pDamager.getItemInHand();
/*     */     
/*  97 */     if (item != null) {
/*  98 */       if (SNConfigHandler.ghoulWeapons.contains(item.getType())) {
/*  99 */         SuperNManager.sendMessage(snDamager, Language.GHOUL_LIMIT_WEAPON.toString());
/*     */         
/* 101 */         damage = 0.0D;
/*     */       } else {
/* 103 */         damage += damage * snDamager.scale(SNConfigHandler.ghoulDamageFactor);
/*     */       }
/*     */     }
/*     */     
/* 107 */     return damage;
/*     */   }
/*     */   
/*     */   public void deathEvent(Player player)
/*     */   {
/* 112 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 113 */     SuperNManager.alterPower(snplayer, -SNConfigHandler.ghoulDeathPowerPenalty, Language.YOU_DIE.toString());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void killEvent(Player pDamager, SuperNPlayer damager, SuperNPlayer victim)
/*     */   {
/* 121 */     if (victim == null) {
/* 122 */       SuperNManager.alterPower(damager, SNConfigHandler.ghoulKillPowerCreatureGain, Language.KILL_CREATURE.toString());
/*     */     }
/*     */     else
/*     */     {
/* 126 */       double random = Math.random();
/* 127 */       if (victim.getPower() > SNConfigHandler.ghoulKillPowerPlayerGain) {
/* 128 */         SuperNManager.alterPower(damager, SNConfigHandler.ghoulKillPowerPlayerGain, Language.KILL_PLAYER.toString());
/*     */       }
/*     */       else
/*     */       {
/* 132 */         SuperNManager.sendMessage(damager, Language.NO_POWER_GAIN.toString());
/*     */       }
/*     */       
/* 135 */       if ((SNConfigHandler.ghoulKillSpreadCurse) && (!victim.isSuper()) && 
/* 136 */         (random < SNConfigHandler.spreadChance)) {
/* 137 */         SuperNManager.sendMessage(victim, Language.GHOUL_DEATH.toString());
/*     */         
/* 139 */         SuperNManager.convert(victim, "ghoul");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean playerInteract(PlayerInteractEvent event)
/*     */   {
/* 151 */     Action action = event.getAction();
/* 152 */     Player player = event.getPlayer();
/*     */     
/* 154 */     Material itemMaterial = event.getMaterial();
/*     */     
/* 156 */     if (((SNConfigHandler.ghoulRightClickSummon) && ((action.equals(Action.RIGHT_CLICK_AIR)) || (action.equals(Action.RIGHT_CLICK_BLOCK)))) || ((!SNConfigHandler.ghoulRightClickSummon) && ((action.equals(Action.LEFT_CLICK_AIR)) || (action.equals(Action.LEFT_CLICK_BLOCK)))))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 162 */       if (itemMaterial.toString().equalsIgnoreCase(SNConfigHandler.ghoulMaterial))
/*     */       {
/* 164 */         summon(player);
/* 165 */         event.setCancelled(true);
/* 166 */         return true;
/*     */       }
/*     */     }
/* 169 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void armorCheck(Player player)
/*     */   {
/* 178 */     if (!player.hasPermission("supernatural.player.ignorearmor")) {
/* 179 */       PlayerInventory inv = player.getInventory();
/* 180 */       ItemStack helmet = inv.getHelmet();
/* 181 */       ItemStack chest = inv.getChestplate();
/* 182 */       ItemStack leggings = inv.getLeggings();
/* 183 */       ItemStack boots = inv.getBoots();
/*     */       
/* 185 */       if ((helmet != null) && 
/* 186 */         (!SNConfigHandler.ghoulArmor.contains(helmet.getType())) && (!helmet.getType().equals(Material.WOOL)))
/*     */       {
/* 188 */         inv.setHelmet(null);
/* 189 */         dropItem(player, helmet);
/*     */       }
/*     */       
/* 192 */       if ((chest != null) && 
/* 193 */         (!SNConfigHandler.ghoulArmor.contains(chest.getType()))) {
/* 194 */         inv.setChestplate(null);
/* 195 */         dropItem(player, chest);
/*     */       }
/*     */       
/* 198 */       if ((leggings != null) && 
/* 199 */         (!SNConfigHandler.ghoulArmor.contains(leggings.getType()))) {
/* 200 */         inv.setLeggings(null);
/* 201 */         dropItem(player, leggings);
/*     */       }
/*     */       
/* 204 */       if ((boots != null) && 
/* 205 */         (!SNConfigHandler.ghoulArmor.contains(boots.getType()))) {
/* 206 */         inv.setBoots(null);
/* 207 */         dropItem(player, boots);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void waterAdvanceTime(Player player)
/*     */   {
/* 218 */     if (player.isDead()) {
/* 219 */       return;
/*     */     }
/* 221 */     if (SupernaturalsPlugin.hasPermissions(player, this.permissions)) {
/* 222 */       return;
/*     */     }
/* 224 */     if ((player.isInsideVehicle()) && 
/* 225 */       ((player.getVehicle() instanceof Boat))) {
/* 226 */       return;
/*     */     }
/*     */     
/*     */ 
/* 230 */     Material material = player.getLocation().getBlock().getType();
/*     */     
/* 232 */     if ((material == Material.STATIONARY_WATER) || (material == Material.WATER)) {
/* 233 */       double health = player.getHealth() - SNConfigHandler.ghoulDamageWater;
/*     */       
/* 235 */       if (health < 0.0D) {
/* 236 */         health = 0.0D;
/*     */       }
/* 238 */       player.setHealth(health);
/* 239 */       EntityDamageEvent event = new EntityDamageEvent(player, EntityDamageEvent.DamageCause.DROWNING, SNConfigHandler.ghoulDamageWater);
/*     */       
/* 241 */       player.setLastDamageCause(event);
/* 242 */       SuperNManager.sendMessage(SuperNManager.get(player), Language.GHOUL_HATE_WATER.toString());
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
/* 253 */     Player player = (Player)event.getDamager();
/* 254 */     SuperNPlayer snplayer = SuperNManager.get(player);
/*     */     
/* 256 */     Material itemMaterial = player.getItemInHand().getType();
/*     */     
/* 258 */     if (player.getItemInHand() == null) {
/* 259 */       return;
/*     */     }
/*     */     
/* 262 */     if (itemMaterial.toString().equalsIgnoreCase(SNConfigHandler.ghoulBondMaterial))
/*     */     {
/* 264 */       boolean success = createBond(player, target);
/* 265 */       if (!success) {
/* 266 */         return;
/*     */       }
/* 268 */       event.setCancelled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeBond(SuperNPlayer player) {
/* 273 */     if (this.bonds.containsKey(player)) {
/* 274 */       SuperNManager.sendMessage(player, Language.GHOUL_BOND_REMOVE_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), ((SuperNPlayer)this.bonds.get(player)).getName()));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 279 */       SuperNManager.sendMessage((SuperNPlayer)this.bonds.get(player), Language.GHOUL_BOND_REMOVE_NOTICE_OTHER.toString());
/*     */       
/* 281 */       this.bonds.remove(player);
/* 282 */       return;
/*     */     }
/* 284 */     if (this.bonds.containsValue(player)) {
/* 285 */       for (SuperNPlayer ghoul : this.bonds.keySet()) {
/* 286 */         if (((SuperNPlayer)this.bonds.get(ghoul)).equals(player)) {
/* 287 */           SuperNManager.sendMessage(player, Language.GHOUL_BOND_REMOVE_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), ((SuperNPlayer)this.bonds.get(ghoul)).getName()));
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 292 */           SuperNManager.sendMessage((SuperNPlayer)this.bonds.get(player), Language.GHOUL_BOND_REMOVE_NOTICE_OTHER.toString());
/*     */           
/* 294 */           this.bonds.remove(ghoul);
/* 295 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean createBond(Player player, Player victim)
/*     */   {
/* 303 */     SuperNPlayer ghoul = SuperNManager.get(player);
/*     */     
/* 305 */     if (victim == null) {
/* 306 */       if (this.bonds.containsKey(ghoul)) {
/* 307 */         SuperNManager.sendMessage(ghoul, Language.GHOUL_BOND_REMOVE_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), ((SuperNPlayer)this.bonds.get(ghoul)).getName()));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 312 */         SuperNManager.sendMessage((SuperNPlayer)this.bonds.get(ghoul), Language.GHOUL_BOND_REMOVE_NOTICE_OTHER.toString());
/*     */         
/* 314 */         this.bonds.remove(ghoul);
/*     */       }
/* 316 */       return false;
/*     */     }
/*     */     
/* 319 */     SuperNPlayer snvictim = SuperNManager.get(victim);
/*     */     
/* 321 */     if (snvictim.isSuper()) {
/* 322 */       if (this.bonds.containsKey(ghoul)) {
/* 323 */         SuperNManager.sendMessage(ghoul, Language.GHOUL_BOND_REMOVE_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), ((SuperNPlayer)this.bonds.get(ghoul)).getName()));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 328 */         SuperNManager.sendMessage((SuperNPlayer)this.bonds.get(ghoul), Language.GHOUL_BOND_REMOVE_NOTICE_OTHER.toString());
/*     */         
/* 330 */         this.bonds.remove(ghoul);
/*     */       }
/*     */       
/* 333 */       SuperNManager.sendMessage(ghoul, Language.GHOUL_BOND_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), victim.getName()));
/*     */       
/*     */ 
/*     */ 
/* 337 */       SuperNManager.sendMessage(snvictim, Language.GHOUL_BOND_NOTICE_OTHER.toString().replace(LanguageTag.PLAYER.toString(), ghoul.getName()));
/*     */       
/*     */ 
/*     */ 
/* 341 */       this.bonds.put(ghoul, snvictim);
/*     */       
/* 343 */       ItemStack item = player.getItemInHand();
/* 344 */       if (item.getAmount() == 1) {
/* 345 */         player.setItemInHand(null);
/*     */       } else {
/* 347 */         item.setAmount(player.getItemInHand().getAmount() - 1);
/*     */       }
/* 349 */       return true;
/*     */     }
/* 351 */     SuperNManager.sendMessage(ghoul, Language.GHOUL_BOND_NOT_ALLOW.toString());
/*     */     
/* 353 */     return false;
/*     */   }
/*     */   
/*     */   public boolean checkBond(Player player) {
/* 357 */     SuperNPlayer snvictim = SuperNManager.get(player);
/* 358 */     SuperNPlayer snplayer = null;
/*     */     
/* 360 */     if (this.bonds.containsValue(snvictim)) {
/* 361 */       for (SuperNPlayer ghoul : this.bonds.keySet()) {
/* 362 */         if (((SuperNPlayer)this.bonds.get(ghoul)).equals(snvictim)) {
/* 363 */           snplayer = ghoul;
/* 364 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 369 */     if (snplayer == null) {
/* 370 */       return false;
/*     */     }
/*     */     
/* 373 */     if ((!snplayer.isOnline()) || (snplayer.isDead())) {
/* 374 */       return false;
/*     */     }
/*     */     
/* 377 */     Player gPlayer = SupernaturalsPlugin.instance.getServer().getPlayer(snplayer.getName());
/*     */     
/*     */ 
/* 380 */     if (!gPlayer.getWorld().equals(player.getWorld())) {
/* 381 */       return false;
/*     */     }
/*     */     
/* 384 */     double distance = gPlayer.getLocation().distance(player.getLocation());
/*     */     
/* 386 */     if (distance > 10.0D) {
/* 387 */       return false;
/*     */     }
/*     */     
/* 390 */     return snplayer.getPower() > SNConfigHandler.ghoulPowerBond;
/*     */   }
/*     */   
/*     */   public SuperNPlayer getGhoul(SuperNPlayer snplayer) {
/* 394 */     if (this.bonds.containsValue(snplayer)) {
/* 395 */       for (SuperNPlayer ghoul : this.bonds.keySet()) {
/* 396 */         if (((SuperNPlayer)this.bonds.get(ghoul)).equals(snplayer)) {
/* 397 */           return ghoul;
/*     */         }
/*     */       }
/*     */     }
/* 401 */     return null;
/*     */   }
/*     */   
/*     */   public boolean summon(Player player) {
/* 405 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 406 */     ItemStack item = player.getItemInHand();
/* 407 */     if (!SupernaturalsPlugin.instance.getSpawn(player)) {
/* 408 */       SuperNManager.sendMessage(snplayer, Language.GHOUL_SUMMON_NOT_ALLOW.toString());
/*     */       
/* 410 */       return false;
/*     */     }
/* 412 */     if (snplayer.getPower() > SNConfigHandler.ghoulPowerSummonCost) {
/* 413 */       player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
/*     */       
/* 415 */       SuperNManager.alterPower(snplayer, -SNConfigHandler.ghoulPowerSummonCost, Language.GHOUL_SUMMON_NOTICE_SELF.toString());
/*     */       
/*     */ 
/* 418 */       if (item.getAmount() == 1) {
/* 419 */         player.setItemInHand(null);
/*     */       } else {
/* 421 */         item.setAmount(player.getItemInHand().getAmount() - 1);
/*     */       }
/* 423 */       return true;
/*     */     }
/* 425 */     SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/* 426 */     return false;
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
/*     */   public boolean isUnderRoof(Player player)
/*     */   {
/* 440 */     boolean retVal = false;
/* 441 */     Block blockCurrent = player.getLocation().getBlock();
/*     */     
/* 443 */     if (player.getLocation().getY() >= 126.0D) {
/* 444 */       retVal = false;
/*     */     }
/*     */     else
/*     */     {
/* 448 */       while (blockCurrent.getY() + 1 <= 127) {
/* 449 */         blockCurrent = blockCurrent.getRelative(BlockFace.UP);
/*     */         
/* 451 */         if (!blockCurrent.getType().equals(Material.AIR)) {
/* 452 */           retVal = true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 457 */     return retVal;
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\manager\GhoulManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */