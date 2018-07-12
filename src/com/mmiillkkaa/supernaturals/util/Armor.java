/*    */ package com.mmiillkkaa.supernaturals.util;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Armor
/*    */ {
/* 13 */   private static final double[] armorPoints = { 1.5D, 3.0D, 4.0D, 1.5D };
/*    */   private static final double armorPointReduction = 0.04D;
/*    */   
/*    */   public static int getArmorPoints(Player player) {
/* 17 */     int currentDurability = 0;
/* 18 */     int baseDurability = 0;
/* 19 */     double baseArmorPoints = 0.0D;
/* 20 */     ItemStack[] inventory = player.getInventory().getArmorContents();
/* 21 */     for (int i = 0; i < inventory.length; i++)
/* 22 */       if (inventory[i] != null)
/*    */       {
/*    */ 
/* 25 */         Material m = inventory[i].getType();
/* 26 */         if (m != null)
/*    */         {
/*    */ 
/* 29 */           short maxDurability = m.getMaxDurability();
/* 30 */           if (maxDurability >= 0)
/*    */           {
/*    */ 
/* 33 */             short durability = inventory[i].getDurability();
/* 34 */             baseDurability += maxDurability;
/* 35 */             currentDurability += maxDurability - durability;
/* 36 */             baseArmorPoints += armorPoints[i];
/*    */           } } }
/* 38 */     return (int)Math.round(2.0D * baseArmorPoints * currentDurability / baseDurability);
/*    */   }
/*    */   
/*    */   public static int getReducedDamage(Player player, int damage)
/*    */   {
/* 43 */     int armorPoints = getArmorPoints(player);
/* 44 */     return (int)Math.round(damage - 0.04D * armorPoints * damage);
/*    */   }
/*    */ }


