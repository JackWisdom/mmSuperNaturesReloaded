/*     */ package git.JackWisdom.mcp.supernaturals.manager;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*     */ import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
/*     */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*     */ import git.JackWisdom.mcp.supernaturals.util.LanguageTag;
/*     */ import java.util.HashMap;
/*     */
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Animals;
/*     */ import org.bukkit.entity.Boat;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Wolf;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AngelManager
/*     */   extends ClassManager
/*     */ {
/*  33 */   public HashMap<Wolf, SuperNPlayer> angelWolfMap = new HashMap();
/*     */   
/*     */   public void deathEvent(Player player)
/*     */   {
/*  37 */     SuperNPlayer snplayer = SuperNManager.get(player);
/*  38 */     if ((player.getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.LAVA)) || (player.getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.FIRE)) || (player.getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  43 */       SuperNManager.sendMessage(snplayer, Language.ANGEL_DEATH.toString());
/*     */       
/*  45 */       SuperNManager.cure(snplayer);
/*     */     }
/*     */   }
/*     */   
/*     */   public double damagerEvent(EntityDamageByEntityEvent event, double damage)
/*     */   {
/*  51 */     Player player = (Player)event.getDamager();
/*  52 */     SuperNPlayer snplayer = SuperNManager.get(player);
/*  53 */     if (((event.getEntity() instanceof Animals)) && 
/*  54 */       (player.getItemInHand().getType().equals(Material.DIAMOND_SWORD))) {
/*  55 */       SuperNManager.sendMessage(snplayer, Language.ANGEL_LIMIT_TARGET.toString());
/*     */       
/*  57 */       event.setCancelled(true);
/*  58 */       return 0.0D;
/*     */     }
/*     */     
/*  61 */     if (SNConfigHandler.angelWeapons.contains(player.getItemInHand())) {
/*  62 */       SuperNManager.sendMessage(snplayer, Language.ANGEL_LIMIT_WEAPON.toString());
/*     */       
/*  64 */       event.setCancelled(true);
/*  65 */       return 0.0D;
/*     */     }
/*  67 */     return damage;
/*     */   }
/*     */   
/*     */   public void spellEvent(EntityDamageByEntityEvent event, Player target)
/*     */   {
/*  72 */     Player player = (Player)event.getDamager();
/*  73 */     SuperNPlayer snplayer = SuperNManager.get(player);
/*  74 */     SuperNPlayer sntarget = SuperNManager.get(target);
/*  75 */     if (player.getItemInHand().getType().toString().equals(SNConfigHandler.angelHealMaterial))
/*     */     {
/*  77 */       if (snplayer.getPower() > SNConfigHandler.angelHealPowerCost) {
/*  78 */         if (target.getHealth() + SNConfigHandler.angelHealHealthGain <= target.getMaxHealth())
/*     */         {
/*  80 */           target.setHealth(target.getHealth() + SNConfigHandler.angelHealHealthGain);
/*     */         }
/*     */         else {
/*  83 */           target.setHealth(target.getMaxHealth());
/*     */         }
/*  85 */         SuperNManager.alterPower(snplayer, -SNConfigHandler.angelHealPowerCost, Language.ANGEL_HEAL_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), target.getName()));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  91 */         target.sendMessage(Language.ANGEL_HEAL_NOTICE_OTHER.toString().replace(LanguageTag.PLAYER.toString(), player.getName()));
/*     */       }
/*     */       else
/*     */       {
/*  95 */         SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/*     */       }
/*     */     }
/*     */     
/*  99 */     if (player.getItemInHand().getType().toString().equals(SNConfigHandler.angelCureMaterial))
/*     */     {
/* 101 */       if (snplayer.getPower() > SNConfigHandler.angelCurePowerCost) {
/* 102 */         if (sntarget.isSuper()) {
/* 103 */           SuperNManager.cure(sntarget);
/* 104 */           target.sendMessage(Language.ANGEL_CURE_NOTICE_OTHER.toString().replace(LanguageTag.PLAYER.toString(), player.getName()));

/* 107 */           SuperNManager.alterPower(snplayer, -SNConfigHandler.angelCurePowerCost, Language.ANGEL_CURE_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), target.getName()));
/*     */ 
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/*     */ 
/* 114 */           SuperNManager.sendMessage(snplayer, Language.NOT_SUPERNATURAL.toString());
/*     */         }
/*     */       }
/*     */       else {
/* 118 */         SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean playerInteract(PlayerInteractEvent event)
/*     */   {
/* 126 */     Player player = event.getPlayer();
/* 127 */     Action action = event.getAction();
/* 128 */     Material itemInHandMaterial = player.getItemInHand().getType();
/* 129 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 130 */     if ((action.equals(Action.LEFT_CLICK_AIR)) || (action.equals(Action.LEFT_CLICK_BLOCK)))
/*     */     {
/* 132 */       if (itemInHandMaterial.toString().equals(SNConfigHandler.angelJumpMaterial))
/*     */       {
/* 134 */         if (snplayer.getPower() > SNConfigHandler.angelJumpPowerCost) {
/* 135 */           jump(player, SNConfigHandler.angelJumpDeltaSpeed);
/*     */         } else {
/* 137 */           SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/*     */         }
/*     */       }
/*     */       
/* 141 */       Block targetBlock = player.getTargetBlock(null, 20);
/* 142 */       Location targetBlockLocation = targetBlock.getLocation();
/* 143 */       Location plusOne = new Location(targetBlockLocation.getWorld(), targetBlockLocation.getBlockX(), targetBlockLocation.getBlockY() + 1, targetBlockLocation.getBlockZ());
/*     */       
/*     */ 
/*     */ 
/* 147 */       if ((itemInHandMaterial.equals(Material.RAW_BEEF)) || (itemInHandMaterial.equals(Material.BONE)) || (itemInHandMaterial.equals(Material.PORK)))
/*     */       {
/*     */ 
/* 150 */         if (snplayer.getPower() > SNConfigHandler.angelSummonPowerCost) {
/* 151 */           if (itemInHandMaterial.toString().equals(SNConfigHandler.angelSummonCowMaterial))
/*     */           {
/* 153 */             player.getWorld().spawnEntity(plusOne, EntityType.COW);
/* 154 */             event.setCancelled(true);
/* 155 */             SuperNManager.alterPower(snplayer, -SNConfigHandler.angelSummonPowerCost, Language.ANGEL_SUMMON_COW.toString());
/*     */             
/*     */ 
/* 158 */             return true;
/*     */           }
/* 160 */           if (itemInHandMaterial.toString().equals(SNConfigHandler.angelSummonWolfMaterial))
/*     */           {
/* 162 */             int wolves = 0;
/* 163 */             for (Wolf wolf : this.angelWolfMap.keySet()) {
/* 164 */               if (((SuperNPlayer)this.angelWolfMap.get(wolf)).equals(snplayer)) {
/* 165 */                 wolves++;
/*     */               }
/*     */             }
/* 168 */             if (wolves > 4) {
/* 169 */               player.sendMessage(Language.ANGEL_SUMMON_TOO_MUCH_WOLF.toString());
/*     */               
/* 171 */               return true;
/*     */             }
/* 173 */             Wolf spawnedWolf = (Wolf)player.getWorld().spawnEntity(plusOne, EntityType.WOLF);
/*     */             
/* 175 */             spawnedWolf.setTamed(true);
/* 176 */             spawnedWolf.setOwner(player);
/* 177 */             spawnedWolf.setHealth(8.0D);
/* 178 */             event.setCancelled(true);
/* 179 */             SuperNManager.alterPower(snplayer, -SNConfigHandler.angelSummonPowerCost, Language.ANGEL_SUMMON_WOLF.toString());
/*     */             
/*     */ 
/* 182 */             return true;
/*     */           }
/* 184 */           if (itemInHandMaterial.toString().equals(SNConfigHandler.angelSummonPigMaterial))
/*     */           {
/* 186 */             player.getWorld().spawnEntity(plusOne, EntityType.PIG);
/* 187 */             event.setCancelled(true);
/* 188 */             SuperNManager.alterPower(snplayer, -SNConfigHandler.angelSummonPowerCost, Language.ANGEL_SUMMON_PIG.toString());
/*     */             
/*     */ 
/* 191 */             return true;
/*     */           }
/*     */         }
/*     */       }
/* 195 */       return false;
/*     */     }
/* 197 */     return false;
/*     */   }
/*     */   
/*     */   public double victimEvent(EntityDamageEvent event, double damage)
/*     */   {
/* 202 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
/* 203 */       event.setCancelled(true);
/* 204 */       return 0.0D;
/*     */     }
/* 206 */     return damage;
/*     */   }
/*     */   
/*     */   public static boolean jump(Player player, double deltaSpeed) {
/* 210 */     SuperNPlayer snplayer = SuperNManager.get(player);
/*     */     
/* 212 */     if (snplayer.getPower() < SNConfigHandler.angelJumpPowerCost) {
/* 213 */       SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
/* 214 */       return false;
/*     */     }
/* 216 */     SuperNManager.alterPower(snplayer, -SNConfigHandler.angelJumpPowerCost, Language.ANGEL_SUPERJUMP.toString());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 222 */     Vector vjadd = new Vector(0, 1, 0);
/* 223 */     vjadd.multiply(deltaSpeed);
/*     */     
/* 225 */     player.setVelocity(player.getVelocity().add(vjadd));
/* 226 */     return true;
/*     */   }
/*     */   
/*     */   public void armorCheck(Player player)
/*     */   {
/* 231 */     if (!player.hasPermission("supernatural.player.ignorearmor")) {
/* 232 */       PlayerInventory inv = player.getInventory();
/* 233 */       ItemStack helmet = inv.getHelmet();
/* 234 */       ItemStack chest = inv.getChestplate();
/* 235 */       ItemStack leggings = inv.getLeggings();
/* 236 */       ItemStack boots = inv.getBoots();
/*     */       
/* 238 */       if ((helmet != null) && 
/* 239 */         (!SNConfigHandler.angelArmor.contains(helmet.getType()))) {
/* 240 */         inv.setHelmet(null);
/* 241 */         dropItem(player, helmet);
/*     */       }
/*     */       
/* 244 */       if ((chest != null) && 
/* 245 */         (!SNConfigHandler.angelArmor.contains(chest.getType()))) {
/* 246 */         inv.setChestplate(null);
/* 247 */         dropItem(player, chest);
/*     */       }
/*     */       
/* 250 */       if ((leggings != null) && 
/* 251 */         (!SNConfigHandler.angelArmor.contains(leggings.getType()))) {
/* 252 */         inv.setLeggings(null);
/* 253 */         dropItem(player, leggings);
/*     */       }
/*     */       
/* 256 */       if ((boots != null) && 
/* 257 */         (!SNConfigHandler.angelArmor.contains(boots.getType()))) {
/* 258 */         inv.setBoots(null);
/* 259 */         dropItem(player, boots);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void waterAdvanceTime(Player player)
/*     */   {
/* 266 */     if (player.isDead()) {
/* 267 */       return;
/*     */     }
/*     */     
/* 270 */     if ((player.isInsideVehicle()) && 
/* 271 */       ((player.getVehicle() instanceof Boat))) {
/* 272 */       return;
/*     */     }
/*     */     
/*     */ 
/* 276 */     SuperNPlayer snplayer = SuperNManager.get(player);
/*     */     
/* 278 */     Material material = player.getLocation().getBlock().getType();
/*     */     
/* 280 */     if ((material == Material.STATIONARY_WATER) || (material == Material.WATER)) {
/* 281 */       SuperNManager.alterPower(snplayer, SNConfigHandler.angelSwimPowerGain, Language.ANGEL_SWIM.toString());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\manager\AngelManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */