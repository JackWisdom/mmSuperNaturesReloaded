package git.JackWisdom.mcp.supernaturals.inventory;

import git.JackWisdom.mcp.supernaturals.util.Recipes;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DonateGui extends IGui {
    protected Inventory inventory;

    public DonateGui(Recipes recipes) {
        super(recipes);
    }

    @Override
    public InventoryType getType() {
        return InventoryType.HUMAN;
    }


    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
