/*    */ package com.mmiillkkaa.supernaturals.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Comparator;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.SortedSet;
/*    */ import java.util.TreeSet;
/*    */ import com.sun.org.apache.regexp.internal.RE;
import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Recipes
/*    */ {
/* 36 */   public Map<Material, Integer> materialQuantities = new HashMap();
/*    */   
/*    */   public void removeFromPlayer(Player player)
/*    */   {
/* 40 */     Inventory inventory = player.getInventory();
/* 41 */     for (Material material : this.materialQuantities.keySet()) {
/* 42 */       inventory.removeItem(new ItemStack[] { new ItemStack(material.getId(), ((Integer)this.materialQuantities.get(material)).intValue()) });
/*    */     }
/*    */     
/* 45 */     player.updateInventory();
/*    */   }
/*    */   
/*    */   public boolean playerHasEnough(Player player) {
/* 49 */     Inventory inventory = player.getInventory();
/* 50 */     for (Material material : this.materialQuantities.keySet()) {
/* 51 */       if (getMaterialCountFromInventory(material, inventory) < ((Integer)this.materialQuantities.get(material)).intValue())
/*    */       {
/* 53 */         return false;
/*    */       }
/*    */     }
/* 56 */     return true;
/*    */   }
/*    */   
/*    */   public static int getMaterialCountFromInventory(Material material, Inventory inventory)
/*    */   {
/* 61 */     int count = 0;
/* 62 */     for (ItemStack stack : inventory.all(material).values()) {
/* 63 */       count += stack.getAmount();
/*    */     }
/* 65 */     return count;
/*    */   }
/*    */   
/*    */   public String getRecipeLine() {
/* 69 */     ArrayList<String> lines = new ArrayList();
/* 70 */     for (Map.Entry<Material, Integer> item : entriesSortedByValues(this.materialQuantities)) {
/* 71 */       lines.add("" + item.getValue() + " " + TextUtil.getMaterialName((Material)item.getKey()));
/*    */     }
/*    */     
/* 74 */     return TextUtil.implode(lines, ", ");
/*    */   }
/*    */   
/*    */ 
/*    */   public static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map)
/*    */   {
/* 80 */     SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet(new entryComparator());
/* 89 */     sortedEntries.addAll(map.entrySet());
/* 90 */     return sortedEntries;
/*    */   }
            static class entryComparator implements  Comparator<Map.Entry<Object,? extends Comparable>>{
                @Override
                public int compare(Entry o1, Entry o2) {
                   return  ((Comparable) o1.getValue()).compareTo(o2);
                }
            }
/*    */ }

