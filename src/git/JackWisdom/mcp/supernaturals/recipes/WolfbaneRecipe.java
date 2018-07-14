package git.JackWisdom.mcp.supernaturals.recipes;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class WolfbaneRecipe {
      ItemStack itemStack;
      ShapedRecipe recipe;
      public WolfbaneRecipe(){
          itemStack=new ItemStack();
          ItemMeta im=itemStack.getItemMeta();
          im.setDisplayName();
          im.setLore();
          itemStack.setItemMeta(im);
          recipe=new ShapedRecipe(itemStack);
          recipe.shape();
          Bukkit.getServer().addRecipe(recipe);
      }

}
