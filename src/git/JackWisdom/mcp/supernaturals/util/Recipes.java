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

/*    */ }

