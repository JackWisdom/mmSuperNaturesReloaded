package git.JackWisdom.mcp.supernaturals.inventory;

import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
import git.JackWisdom.mcp.supernaturals.util.Recipes;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

//priest using donateGUI
public class PDonateGui extends IGui {
    @Override
    public InventoryType getType() {
        return InventoryType.PRIEST;
    }

    public PDonateGui(Recipes recipes,String title) {
        super(recipes, title);
    }
    @Override
    public boolean accept(){
    return super.equalsByMaterial(super.getMaterialByLine(1),super.getMaterialByLine(2));
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
    public int getPower(){
                int amount=0;
                int delta=0;
                List<ItemStack> items=getItemsByLine(2);
                   for (Material mat : SNConfigHandler.priestDonationMap.keySet())
                       {
                          for (ItemStack itemStack : items) {
                               if ((itemStack != null) &&
                                         (itemStack.getType().equals(mat))) {
                                      amount += itemStack.getAmount();
                                   }
                             }

                          delta += amount * ((Integer)SNConfigHandler.priestDonationMap.get(mat)).intValue();


                         amount = 0;
                        }
                        return delta;
    }
}
