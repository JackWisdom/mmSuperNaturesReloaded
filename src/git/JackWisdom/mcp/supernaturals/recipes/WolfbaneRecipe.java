package git.JackWisdom.mcp.supernaturals.recipes;

import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class WolfbaneRecipe {
      private ItemStack itemStack;
      private String key="WolfBane";
      ShapedRecipe recipe;
      public WolfbaneRecipe(){
          itemStack=new ItemStack(SNConfigHandler.wolfBane);
          ItemMeta im=itemStack.getItemMeta();
          im.setDisplayName(SNConfigHandler.wolfBaneDispaly);
          im.setLore(SNConfigHandler.wolfBaneLore);
          itemStack.setItemMeta(im);
          recipe=new ShapedRecipe(NamespacedKey.randomKey(),itemStack);
          List<String> rec=SNConfigHandler.wolfRecipe;

          recipe.shape(rec.get(0),rec.get(1),rec.get(2));
          for(Map<?, ?> mp:SNConfigHandler.wolfBaneRecipeKey){
            for(Object s:mp.keySet()){
                recipe.setIngredient(((String)s).charAt(0),Material.valueOf(mp.get(s).toString()));
            }
          }
          Bukkit.getServer().addRecipe(recipe);
      }
      public ItemStack getItemStack(){
          return itemStack;
      }
      public boolean isTheSame(ItemStack im){
          if(im.getType()!=getItemStack().getType()){
              return false;
          }
          if(!im.hasItemMeta()){
            return false;
          }
          if(!im.getItemMeta().getDisplayName().equals(getItemStack().getItemMeta().getDisplayName())){
              return false;
          }
          if(!im.getItemMeta().getLore().equals(getItemStack().getItemMeta().getLore())){
              return false;
          }
          return true;
      }

}
