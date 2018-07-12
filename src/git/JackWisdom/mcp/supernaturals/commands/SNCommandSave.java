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
                     {


                 if (!sender .hasPermission( this.permissions)) {
                  sendMessage(Language.NO_PREMISSION.toString());
                  return;
               }
              SupernaturalsPlugin.saveAll();
              sendMessage(Language.ALL_DATA_SAVE.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\commands\SNCommandSave.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */