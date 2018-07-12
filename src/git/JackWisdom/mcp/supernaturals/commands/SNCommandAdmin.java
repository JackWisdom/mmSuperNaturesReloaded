/*    */ package git.JackWisdom.mcp.supernaturals.commands;
/*    */ 
/*    */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*    */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */
import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
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
/*    */ 
/*    */ public class SNCommandAdmin
/*    */   extends SNCommand
/*    */ {
/* 33 */   private static List<String> adminHelpMessages = new ArrayList();
/*    */   
/*    */   public SNCommandAdmin()
/*    */   {
/* 37 */     this.requiredParameters = new ArrayList();
/* 38 */     this.optionalParameters = new ArrayList();
/* 39 */     this.senderMustBePlayer = false;
/* 40 */     this.senderMustBeSupernatural = false;
/* 41 */     this.permissions = "supernatural.admin.command.adminhelp";
/*    */   }
/*    */   
/*    */ 
/*    */   public void perform()
/*    */   {
/* 47 */     adminHelpMessages.add(String.format("*** %s ***", new Object[] { Language.SN_ADMIN_HELP }));
/*    */     
/* 49 */     adminHelpMessages.add(String.format("/sn cure <%s> %s- %s", new Object[] { Language.PLAYER_NAME, ChatColor.WHITE, Language.SN_ADMIN_CMD_CURE }));
/*    */     
/*    */ 
/* 52 */     adminHelpMessages.add(String.format("/sn convert <%s> [%s] %s- %s", new Object[] { Language.PLAYER_NAME, Language.SUPERNATURAL_TYPE, ChatColor.WHITE, Language.SN_ADMIN_CMD_CONVERT }));
/*    */     
/*    */ 
/* 55 */     adminHelpMessages.add(String.format("/sn reset <%s> %s- %s", new Object[] { Language.PLAYER_NAME, ChatColor.WHITE, Language.SN_ADMIN_CMD_RESET }));
/*    */     
/*    */ 
/* 58 */     adminHelpMessages.add(String.format("/sn power <%s> [%s] %s- %s", new Object[] { Language.PLAYER_NAME, Language.POWER, ChatColor.WHITE, Language.SN_ADMIN_CMD_POWER }));
/*    */     
/*    */ 
/* 61 */     adminHelpMessages.add(String.format("/sn rmtarget <%s> %s- %s ", new Object[] { Language.PLAYER_NAME, ChatColor.WHITE, Language.SN_ADMIN_CMD_RMTARGET }));
/*    */     
/*    */ 
/* 64 */     adminHelpMessages.add(String.format("/sn save %s- %s", new Object[] { ChatColor.WHITE, Language.SN_ADMIN_CMD_SAVE }));
/*    */     
/* 66 */     adminHelpMessages.add(String.format("/sn reload %s- %s", new Object[] { ChatColor.WHITE, Language.SN_ADMIN_CMD_RELOAD }));
/*    */     
/* 68 */     adminHelpMessages.add(String.format("/sn restartTask %s- %s", new Object[] { ChatColor.WHITE, Language.SN_ADMIN_CMD_RESTARTTASK }));
/*    */     
/* 70 */     adminHelpMessages.add(String.format("/sn setchurch %s- %s", new Object[] { ChatColor.WHITE, Language.SN_ADMIN_CMD_SETCHRUSH }));
/*    */     
/* 72 */     adminHelpMessages.add(String.format("/sn setbanish %s- %s", new Object[] { ChatColor.WHITE, Language.SN_ADMIN_CMD_SETBANISH }));
/*    */     
/* 74 */     adminHelpMessages.add(String.format("/sn setup %s- %s", new Object[] { ChatColor.WHITE, Language.SN_ADMIN_SETUP }));
/*    */     
/*    */ 
/* 77 */     if (!(this.sender instanceof Player)) {
/* 78 */       sendMessage(adminHelpMessages);
/* 79 */       return;
/*    */     }
/* 81 */     Player player = (Player)this.sender;
/* 82 */     if (player.hasPermission( this.permissions)) {
/* 83 */       sendMessage(adminHelpMessages);
/*    */     } else {
/* 85 */       sendMessage(Language.NO_PREMISSION.toString());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\commands\SNCommandAdmin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */