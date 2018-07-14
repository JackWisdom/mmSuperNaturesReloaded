package git.JackWisdom.mcp.supernaturals.inventory;

import git.JackWisdom.mcp.supernaturals.util.Recipes;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nonnull;

public class VampInfectGui extends IGui {
    @Nonnull
    @Override
    public InventoryType getType() {
        return InventoryType.VAMPIRE_I;
    }

    public VampInfectGui(Recipes recipes) {
        super(recipes);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
