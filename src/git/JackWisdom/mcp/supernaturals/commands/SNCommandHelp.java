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
/*    */ public class SNCommandHelp
/*    */   extends SNCommand
/*    */ {
/* 33 */   private static List<String> helpMessages = new ArrayList();
/*    */   
/*    */   public SNCommandHelp()
/*    */   {
/* 37 */     this.requiredParameters = new ArrayList();
/* 38 */     this.optionalParameters = new ArrayList();
/* 39 */     this.senderMustBePlayer = false;
/* 40 */     this.senderMustBeSupernatural = false;
/* 41 */     this.permissions = "supernatural.command.help";
/*    */   }
/*    */   
/*    */   public void perform()
/*    */   {
/* 46 */     helpMessages.add(String.format("*** %s ***", new Object[] { Language.SUPERNATURAL_HELP }));
/*    */     
/* 48 */     helpMessages.add(String.format("/sn Power %s- %s", new Object[] { ChatColor.WHITE, Language.SN_CMD_POWER }));
/*    */     
/* 50 */     helpMessages.add(String.format("/sn List %s- %s", new Object[] { ChatColor.WHITE, Language.SN_CMD_LIST }));
/*    */     
/* 52 */     helpMessages.add(String.format("/sn Classes %s- %s", new Object[] { ChatColor.WHITE, Language.SN_CMD_CLASSES }));
/*    */     
/* 54 */     helpMessages.add(String.format("/sn KillList %s- %s", new Object[] { ChatColor.WHITE, Language.SN_CMD_KILLIST }));
/*    */     
/*    */ 
/* 57 */     if (!(this.sender instanceof Player)) {
/* 58 */       helpMessages.add(String.format("/sn admin %s- %s", new Object[] { ChatColor.WHITE, Language.SN_CMD_ADMIN }));
/*    */       
/* 60 */       sendMessage(helpMessages);
/* 61 */       return;
/*    */     }
/* 63 */     String permissions2 = "supernatural.command.adminhelp";
/* 64 */     Player senderPlayer = (Player)this.sender;
/*    */     
/* 66 */     if (senderPlayer.hasPermission( permissions2)) {
/* 67 */       helpMessages.add(String.format("/sn admin %s- %s", new Object[] { ChatColor.WHITE, Language.SN_CMD_ADMIN }));
/*    */     }
/*    */     
/*    */ 
/* 71 */     if (!senderPlayer.hasPermission( this.permissions)) {
/* 72 */       sendMessage(Language.NO_PREMISSION.toString());
/* 73 */       return;
/*    */     }
/* 75 */     sendMessage(helpMessages);
/*    */   }
/*    */ }


