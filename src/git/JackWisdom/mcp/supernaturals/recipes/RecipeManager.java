package git.JackWisdom.mcp.supernaturals.recipes;

public class RecipeManager {
    public WolfbaneRecipe getWolfbaneRecipe() {
        return wolfbaneRecipe;
    }

    private WolfbaneRecipe wolfbaneRecipe;
    public RecipeManager(){
        wolfbaneRecipe=new WolfbaneRecipe();
    }

}
