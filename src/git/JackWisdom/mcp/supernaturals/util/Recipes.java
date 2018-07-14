/*    */ package git.JackWisdom.mcp.supernaturals.util;
/*    */ 
/*    */ import java.util.*;
/*    */
/*    */
/*    */
/*    */ import java.util.Map.Entry;
/*    */
/*    */
/*    */
import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

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
/* 36 */ private   List<ItemStack> itemStacks;
/*    */   public Recipes(){
        itemStacks=new ArrayList<>();
}
            public void setItemStacks(List<ItemStack> l){
            itemStacks=l;
            }
            public List<ItemStack> getItemStacks(){
    return itemStacks;
            }

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

