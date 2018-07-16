/*    */ package git.JackWisdom.mcp.supernaturals.listeners;
/*    */ 
/*    */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*    */ import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
/*    */
/*    */ import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
/*    */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*    */
/*    */
import org.bukkit.Material;
/*    */
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ import org.bukkit.event.block.SignChangeEvent;
/*    */ import org.bukkit.inventory.ItemStack;
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
/*    */ public class SNBlockListener
/*    */   implements Listener
/*    */ {
/*    */   private SupernaturalsPlugin plugin;
/* 40 */   private String permissions = "supernatural.player.witchhuntersign";
/* 41 */   private String worldPermission = "supernatural.world.enabled";
/*    */   
/*    */   public SNBlockListener(SupernaturalsPlugin instance) {
/* 44 */     this.plugin = instance;
/*    */   }
/*    */   
/*    */   @EventHandler(priority=EventPriority.LOW)
/*    */   public void onBlockBreak(BlockBreakEvent event) {
/* 49 */     Block eventBlock = event.getBlock();
/* 50 */     if (eventBlock.getType().equals(Material.COBWEB)) {
/* 51 */       for (Block block : this.plugin.getDemonManager().getWebs().keySet()) {
/* 52 */         if (block.equals(eventBlock)) {
/* 53 */           event.setCancelled(true);
/* 54 */           block.setType(Material.AIR);
/* 55 */           this.plugin.getDemonManager().removeWeb(block);
/* 56 */           return;
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler(priority=EventPriority.LOW)
/*    */   public void onSignChange(SignChangeEvent event) {
/* 64 */     Player player = event.getPlayer();
/* 65 */     String  text = event.getLine(0);
/* 66 */     if ((!player.hasPermission( this.worldPermission)) && (SNConfigHandler.multiworld))
/*    */     {
/* 68 */       return;
/*    */     }
/* 70 *
/* 71 */       if (text.equals((SNConfigHandler.hunterHallMessage))) {
/* 72 */         if (!player.hasPermission( this.permissions)) {
/* 73 */           SuperNManager.sendMessage(SuperNManager.get(player), Language.WITCHHUNTER_SIGN_NOT_ALLOW.toString());
/*    */           
/* 75 */           event.setCancelled(true);
/* 76 */           event.getBlock().setType(Material.AIR);
/* 77 */           player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.SIGN, 1));
/*    */         }
/*    */
/* 80 */         return;
/*    */       }
/*    */
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\listeners\SNBlockListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */