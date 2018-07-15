package git.JackWisdom.mcp.supernaturals.inventory;

import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
import git.JackWisdom.mcp.supernaturals.util.Recipes;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public abstract class IGui implements InventoryHolder {
    protected Inventory inventory;
    @Nonnull
    public abstract InventoryType getType();
    public IGui(Recipes recipes,String title){
        inventory= Bukkit.createInventory(this,18);
        for(ItemStack i:recipes.getItemStacks()){
            inventory.addItem(i);
        }
        //recipe muse lower than 9
    }
    public IGui(Inventory inventory){
        this.inventory=inventory;
    }
    protected List<Material> getMaterialByLine(int line){
        int start=(line-1)*9 +1;
        int end=line*9;
        ArrayList<Material> stacks=new ArrayList<>();
        while (start<=end){
            if(inventory.getItem(start)==null||inventory.getItem(start).getType()==Material.AIR){
                continue;
            }
            stacks.add(inventory.getItem(start).getType());
        }
        return stacks;
    }
    protected List<ItemStack> getItemsByLine(int line){
        int start=(line-1)*9 +1;
        int end=line*9;
        ArrayList<ItemStack> stacks=new ArrayList<>();
        while (start<=end){
            if(inventory.getItem(start)==null||inventory.getItem(start).getType()==Material.AIR){
                continue;
            }
            stacks.add(inventory.getItem(start));
        }
        return stacks;
    }
    protected boolean equalsByMaterial(List<Material> obj,List<Material> sample){
        for(Material m:obj){
            if(sample.contains(m)){
                return false;
            }
        }
        return true;
    }
    protected boolean equals(List<ItemStack> s1,List<ItemStack> s2){
        int i=0;
        if(s1.size()!=s2.size()){
            return false;
        }
        while (i<=s1.size()){
            if(itemEqual(s1.get(i),s2.get(i))){
                continue;
            }
            return false;
        }
        return true;
    }
    protected boolean itemEqual(ItemStack im,ItemStack im2){
        return im.getType()==im2.getType()&&im.getAmount()==im2.getAmount();
    }
    public boolean accept(){
        return equals(getItemsByLine(1),getItemsByLine(2));
    }

    public void openInv(Player player){
        player.openInventory(inventory);
    }
    public Inventory getInv(){
        return inventory;
    }

}
