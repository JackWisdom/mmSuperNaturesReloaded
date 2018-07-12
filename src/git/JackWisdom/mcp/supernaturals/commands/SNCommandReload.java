/*    */ package git.JackWisdom.mcp.supernaturals.commands;
/*    */ 
/*    */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*    */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*    */ import java.util.ArrayList;
/*    */
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
/*    */ public class SNCommandReload
/*    */   extends SNCommand
/*    */ {
/*    */   public SNCommandReload()
/*    */   {
/* 32 */     this.requiredParameters = new ArrayList();
/* 33 */     this.optionalParameters = new ArrayList();
/* 34 */     this.optionalParameters.add("type");
/* 35 */     this.senderMustBePlayer = false;
/* 36 */     this.permissions = "supernatural.admin.command.reload";
/* 37 */     this.helpNameAndParams = "";
/* 38 */     this.helpDescription = "Reload Config or Data files";
/*    */   }
/*    */   
/*    */   public void perform()
/*    */   {
/* 43 */     if (!(this.sender instanceof Player)) {
/* 44 */       if (this.parameters.isEmpty()) {
/* 45 */         SupernaturalsPlugin.reConfig();
/* 46 */         sendMessage(Language.CONFIG_RELOAD.toString());
/*    */       }
/* 48 */       else if (((String)this.parameters.get(0)).equalsIgnoreCase("config")) {
/* 49 */         SupernaturalsPlugin.reConfig();
/* 50 */         sendMessage(Language.CONFIG_RELOAD.toString());
/* 51 */       } else   {
/* 55 */         sendMessage(Language.INVALID_OPTION.toString());
/*    */       }
/*    */       
/* 58 */       return;
/*    */     }
/* 60 */     Player senderPlayer = (Player)this.sender;
/* 61 */     if (!senderPlayer.hasPermission( this.permissions)) {
/* 62 */       sendMessage(Language.NO_PREMISSION.toString());
/* 63 */       return;
/*    */     }
/* 65 */     if (this.parameters.isEmpty()) {
/* 66 */       SupernaturalsPlugin.reConfig();
/* 67 */       sendMessage(Language.CONFIG_RELOAD.toString());
/*    */     }
/* 69 */     else if (((String)this.parameters.get(0)).equalsIgnoreCase("config")) {
/* 70 */       SupernaturalsPlugin.reConfig();
/* 71 */       sendMessage(Language.CONFIG_RELOAD.toString());
/* 72 */     } else  {
/* 76 */       sendMessage(Language.INVALID_OPTION.toString());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\commands\SNCommandReload.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */