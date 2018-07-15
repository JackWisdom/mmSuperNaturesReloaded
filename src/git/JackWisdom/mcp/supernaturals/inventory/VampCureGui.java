package git.JackWisdom.mcp.supernaturals.inventory;

import git.JackWisdom.mcp.supernaturals.util.Recipes;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nonnull;

public class VampCureGui extends IGui {
    public VampCureGui(Recipes recipes,String title) {
        super(recipes,title);
    }

    @Nonnull
    @Override
    public InventoryType getType() {
        return InventoryType.VAMPIRE_C;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
