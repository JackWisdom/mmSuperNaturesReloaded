/*     */ package com.mmiillkkaa.supernaturals.manager;
/*     */ 
/*     */ import com.mmiillkkaa.supernaturals.SuperNPlayer;
/*     */ import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
/*     */ import com.mmiillkkaa.supernaturals.io.SNConfigHandler;
/*     */ import com.mmiillkkaa.supernaturals.util.Language;
/*     */ import com.mmiillkkaa.supernaturals.util.Recipes;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Wolf;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.Inventory;
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
/*     */ 
/*     */ public class WereManager
/*     */   extends ClassManager
/*     */ {
/*  51 */   private String permissions2 = "supernatural.player.wolfbane";
/*  52 */   private static HashMap<Wolf, SuperNPlayer> wolvesMap = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double victimEvent(EntityDamageEvent event, double damage)
/*     */   {
/*  60 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
/*  61 */       damage *= SNConfigHandler.wereDamageFall;
/*     */     }
/*  63 */     return damage;
/*     */   }
/*     */   
/*     */   public double damagerEvent(EntityDamageByEntityEvent event, double damage)
/*     */   {
/*  68 */     Entity damager = event.getDamager();
/*  69 */     Player pDamager = (Player)damager;
/*  70 */     SuperNPlayer snDamager = SuperNManager.get(pDamager);
/*  71 */     ItemStack item = pDamager.getItemInHand();
/*     */     
/*  73 */     if ((SuperNManager.worldTimeIsNight(pDamager)) && 
/*  74 */       (item != null)) {
/*  75 */       if (SNConfigHandler.wereWeapons.contains(item.getType())) {
/*  76 */         SuperNManager.sendMessage(snDamager, Language.WEREWOLF_WEAPON_LIMIT.toString());
/*     */         
/*  78 */         damage = 0.0D;
/*     */       } else {
/*  80 */         damage += damage * snDamager.scale(SNConfigHandler.wereDamageFactor);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  85 */     return damage;
/*     */   }
/*     */   
/*     */   public void deathEvent(Player player)
/*     */   {
/*  90 */     SuperNPlayer snplayer = SuperNManager.get(player);
/*     */     
/*  92 */     SuperNManager.alterPower(snplayer, -SNConfigHandler.wereDeathPowerPenalty, Language.YOU_DIE.toString());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void killEvent(Player pDamager, SuperNPlayer damager, SuperNPlayer victim)
/*     */   {
/* 100 */     if (victim == null) {
/* 101 */       SuperNManager.alterPower(damager, SNConfigHandler.wereKillPowerCreatureGain, Language.KILL_CREATURE.toString());
/*     */     }
/*     */     else
/*     */     {
/* 105 */       double random = Math.random();
/* 106 */       if (victim.getPower() > SNConfigHandler.wereKillPowerPlayerGain) {
/* 107 */         SuperNManager.alterPower(damager, SNConfigHandler.wereKillPowerPlayerGain, Language.KILL_PLAYER.toString());
/*     */       }
/*     */       else
/*     */       {
/* 111 */         SuperNManager.sendMessage(damager, Language.NO_POWER_GAIN.toString());
/*     */       }
/*     */       
/* 114 */       if ((SNConfigHandler.wereKillSpreadCurse) && (!victim.isSuper()) && (SuperNManager.worldTimeIsNight(SupernaturalsPlugin.instance.getServer().getPlayer(victim.getName()))))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 119 */         if (random < SNConfigHandler.spreadChance) {
/* 120 */           SuperNManager.sendMessage(victim, Language.WEREWOLF_DEATH.toString());
/*     */           
/* 122 */           SuperNManager.convert(victim, "werewolf");
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
/*     */ 
/*     */ 
/*     */   public boolean playerInteract(PlayerInteractEvent event)
/*     */   {
/* 137 */     Action action = event.getAction();
/* 138 */     Player player = event.getPlayer();
/* 139 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 140 */     Material itemMaterial = event.getMaterial();
/*     */     
/* 142 */     if ((action.equals(Action.LEFT_CLICK_AIR)) || (action.equals(Action.LEFT_CLICK_BLOCK)))
/*     */     {
/* 144 */       if (player.getItemInHand() == null) {
/* 145 */         return false;
/*     */       }
/*     */       
/* 148 */       if (itemMaterial.toString().equalsIgnoreCase(SNConfigHandler.wolfMaterial))
/*     */       {
/* 150 */         if (SuperNManager.worldTimeIsNight(player)) {
/* 151 */           summon(player);
/* 152 */           event.setCancelled(true);
/* 153 */           return true;
/*     */         }
/* 155 */         SuperNManager.sendMessage(snplayer, Language.WEREWOLF_ABILITY_LIMIT.toString());
/*     */         
/* 157 */         return false;
/*     */       }
/* 159 */       if (itemMaterial.toString().equalsIgnoreCase(SNConfigHandler.wolfbaneMaterial))
/*     */       {
/* 161 */         if (!SupernaturalsPlugin.hasPermissions(player, this.permissions2)) {
/* 162 */           return false;
/*     */         }
/* 164 */         if (SuperNManager.worldTimeIsNight(player)) {
/* 165 */           SuperNManager.sendMessage(snplayer, Language.WEREWOLF_CURE_LIMIT.toString());
/*     */           
/* 167 */           return false;
/*     */         }
/* 169 */         wolfbane(player);
/* 170 */         event.setCancelled(true);
/* 171 */         return true;
/*     */       }
/* 173 */       if (itemMaterial.toString().equalsIgnoreCase(SNConfigHandler.dashMaterial))
/*     */       {
/* 175 */         if (SuperNManager.worldTimeIsNight(player)) {
/* 176 */           SuperNManager.jump(event.getPlayer(), SNConfigHandler.dashDeltaSpeed, false);
/*     */           
/* 178 */           event.setCancelled(true);
/* 179 */           return true;
/*     */         }
/* 181 */         SuperNManager.sendMessage(snplayer, Language.WEREWOLF_ABILITY_LIMIT.toString());
/*     */         
/* 183 */         return false;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 188 */     if ((!action.equals(Action.RIGHT_CLICK_AIR)) && (!action.equals(Action.RIGHT_CLICK_BLOCK)))
/*     */     {
/* 190 */       return false;
/*     */     }
/*     */     
/* 193 */     if (action.equals(Action.RIGHT_CLICK_AIR)) {
/* 194 */       if ((SuperNManager.worldTimeIsNight(player)) && 
/* 195 */         (itemMaterial != null) && 
/* 196 */         (SNConfigHandler.foodMaterials.contains(itemMaterial))) {
/* 197 */         if (itemMaterial.equals(Material.BREAD)) {
/* 198 */           SuperNManager.sendMessage(snplayer, Language.WEREWOLF_EAT_LIMIT.toString());
/*     */           
/* 200 */           return false;
/*     */         }
/* 202 */         SuperNManager.alterPower(snplayer, SNConfigHandler.werePowerFood, Language.WEREWOLF_EAT.toString());
/*     */         
/*     */ 
/* 205 */         player.setFoodLevel(player.getFoodLevel() + 6);
/*     */         
/*     */ 
/* 208 */         Inventory inv = player.getInventory();
/* 209 */         inv.removeItem(new ItemStack[] { new ItemStack(itemMaterial, 1) });
/* 210 */         player.updateInventory();
/* 211 */         return true;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 216 */       if ((itemMaterial != null) && 
/* 217 */         (SNConfigHandler.foodMaterials.contains(itemMaterial))) {
/* 218 */         if (player.getFoodLevel() == 20) {
/* 219 */           return false;
/*     */         }
/* 221 */         if (itemMaterial.equals(Material.BREAD)) {
/* 222 */           SuperNManager.sendMessage(snplayer, Language.WEREWOLF_EAT_LIMIT.toString());
/*     */           
/* 224 */           return false;
/*     */         }
/* 226 */         SuperNManager.alterPower(snplayer, SNConfigHandler.werePowerFood, Language.WEREWOLF_EAT.toString());
/*     */         
/*     */ 
/* 229 */         player.setFoodLevel(player.getFoodLevel() + 6);
/*     */         
/*     */ 
/* 232 */         Inventory inv = player.getInventory();
/* 233 */         inv.removeItem(new ItemStack[] { new ItemStack(itemMaterial, 1) });
/* 234 */         player.updateInventory();
/* 235 */         return true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 240 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void armorCheck(Player player)
/*     */   {
/* 249 */     if (!player.hasPermission("supernatural.player.ignorearmor")) {
/* 250 */       PlayerInventory inv = player.getInventory();
/* 251 */       ItemStack helmet = inv.getHelmet();
/* 252 */       ItemStack chest = inv.getChestplate();
/* 253 */       ItemStack leggings = inv.getLeggings();
/* 254 */       ItemStack boots = inv.getBoots();
/*     */       
/* 256 */       if ((helmet != null) && 
/* 257 */         (!SNConfigHandler.wereArmor.contains(helmet.getType())) && (!helmet.getType().equals(Material.WOOL)))
/*     */       {
/* 259 */         inv.setHelmet(null);
/* 260 */         dropItem(player, helmet);
/*     */       }
/*     */       
/* 263 */       if ((chest != null) && 
/* 264 */         (!SNConfigHandler.wereArmor.contains(chest.getType()))) {
/* 265 */         inv.setChestplate(null);
/* 266 */         dropItem(player, chest);
/*     */       }
/*     */       
/* 269 */       if ((leggings != null) && 
/* 270 */         (!SNConfigHandler.wereArmor.contains(leggings.getType()))) {
/* 271 */         inv.setLeggings(null);
/* 272 */         dropItem(player, leggings);
/*     */       }
/*     */       
/* 275 */       if ((boots != null) && 
/* 276 */         (!SNConfigHandler.wereArmor.contains(boots.getType()))) {
/* 277 */         inv.setBoots(null);
/* 278 */         dropItem(player, boots);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean wolfbane(Player player)
/*     */   {
/* 289 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 290 */     if (SNConfigHandler.wereWolfbaneRecipe.playerHasEnough(player)) {
/* 291 */       SuperNManager.sendMessage(snplayer, Language.WEREWOLF_POTION_NOTICE_SELF.toString());
/*     */       
/* 293 */       SuperNManager.sendMessage(snplayer, SNConfigHandler.wereWolfbaneRecipe.getRecipeLine());
/*     */       
/* 295 */       SNConfigHandler.wereWolfbaneRecipe.removeFromPlayer(player);
/* 296 */       SuperNManager.cure(snplayer);
/* 297 */       return true;
/*     */     }
/* 299 */     SuperNManager.sendMessage(snplayer, Language.WEREWOLF_POTION_NEED.toString());
/*     */     
/* 301 */     SuperNManager.sendMessage(snplayer, SNConfigHandler.wereWolfbaneRecipe.getRecipeLine());
/*     */     
/* 303 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean summon(Player player)
/*     */   {
/* 312 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 313 */     ItemStack item = player.getItemInHand();
/* 314 */     if (!SupernaturalsPlugin.instance.getSpawn(player)) {
/* 315 */       SuperNManager.sendMessage(snplayer, Language.WEREWOLF_SUMMON_NOT_ALLOW.toString());
/*     */       
/* 317 */       return false;
/*     */     }
/* 319 */     if (SuperNManager.worldTimeIsNight(player)) {
/* 320 */       if (snplayer.getPower() >= SNConfigHandler.werePowerSummonCost) {
/* 321 */         int i = 0;
/* 322 */         for (Wolf wolf : wolvesMap.keySet()) {
/* 323 */           if (((SuperNPlayer)wolvesMap.get(wolf)).equals(snplayer)) {
/* 324 */             i++;
/*     */           }
/*     */         }
/* 327 */         if (i <= 4) {
/* 328 */           Wolf wolf = (Wolf)player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
/*     */           
/* 330 */           wolf.setTamed(true);
/* 331 */           wolf.setOwner(player);
/* 332 */           wolf.setHealth(8.0D);
/* 333 */           wolvesMap.put(wolf, snplayer);
/* 334 */           SuperNManager.alterPower(snplayer, -SNConfigHandler.werePowerSummonCost, Language.WEREWOLF_SUMMON_WOLF.toString());
/*     */           
/*     */ 
/* 337 */           if (item.getAmount() == 1) {
/* 338 */             player.setItemInHand(null);
/*     */           } else {
/* 340 */             item.setAmount(player.getItemInHand().getAmount() - 1);
/*     */           }
/* 342 */           return true;
/*     */         }
/* 344 */         SuperNManager.sendMessage(snplayer, Language.WEREWOLF_SUMMON_TOO_MUCH_WOLF.toString());
/*     */         
/* 346 */         return false;
/*     */       }
/*     */       
/* 349 */       SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/*     */       
/* 351 */       return false;
/*     */     }
/*     */     
/* 354 */     SuperNManager.sendMessage(snplayer, Language.WEREWOLF_ABILITY_LIMIT.toString());
/*     */     
/* 356 */     return false;
/*     */   }
/*     */   
/*     */   public static HashMap<Wolf, SuperNPlayer> getWolves()
/*     */   {
/* 361 */     return wolvesMap;
/*     */   }
/*     */   
/*     */   public static void removeWolf(Wolf wolf) {
/* 365 */     if (wolvesMap.containsKey(wolf)) {
/* 366 */       wolvesMap.remove(wolf);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void removePlayer(SuperNPlayer player) {
/* 371 */     List<Wolf> removeWolf = new ArrayList();
/* 372 */     for (Wolf wolf : wolvesMap.keySet()) {
/* 373 */       if (((SuperNPlayer)wolvesMap.get(wolf)).equals(player)) {
/* 374 */         wolf.setTamed(false);
/* 375 */         removeWolf.add(wolf);
/*     */       }
/*     */     }
/* 378 */     for (Wolf wolf : removeWolf) {
/* 379 */       wolvesMap.remove(wolf);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\manager\WereManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */