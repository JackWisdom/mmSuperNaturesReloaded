/*    */ package git.JackWisdom.mcp.supernaturals.commands;
/*    */ 
/*    */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*    */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*    */
import org.bukkit.entity.Player;
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
/*    */ public class SNCommandSave
/*    */   extends SNCommandReload
/*    */ {
/*    */   public SNCommandSave()
/*    */   {
/* 31 */     this.senderMustBePlayer = false;
/* 32 */     this.permissions = "supernatural.admin.command.save";
/* 33 */     this.helpNameAndParams = "";
/* 34 */     this.helpDescription = "Save data from disk.";
/*    */   }
/*    */   
/*    */   public void perform()
/*    */   {
/* 39 */     if (!(this.sender instanceof Player)) {
/* 40 */       SupernaturalsPlugin.saveAll();
/* 41 */       sendMessage(Language.ALL_DATA_SAVE.toString());
/*    */     }
/* 43 */     Player senderPlayer = (Player)this.sender;
/* 44 */     if (!senderPlayer.hasPermission( this.permissions)) {
/* 45 */       sendMessage(Language.NO_PREMISSION.toString());
/* 46 */       return;
/*    */     }
/* 48 */     SupernaturalsPlugin.saveAll();
/* 49 */     sendMessage(Language.ALL_DATA_SAVE.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\commands\SNCommandSave.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */