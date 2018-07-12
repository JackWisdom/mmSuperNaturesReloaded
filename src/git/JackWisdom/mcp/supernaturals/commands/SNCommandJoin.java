/*    */ package git.JackWisdom.mcp.supernaturals.commands;
/*    */ 
/*    */ import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
/*    */ import git.JackWisdom.mcp.supernaturals.io.SNWhitelistHandler;
/*    */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*    */
import org.bukkit.entity.Player;
/*    */ 
/*    */ public class SNCommandJoin extends SNCommand
/*    */ {
/*    */   public SNCommandJoin()
/*    */   {
/* 12 */     this.permissions = "";
/* 13 */     this.senderMustBePlayer = true;
/* 14 */     this.senderMustBeSupernatural = false;
/* 15 */     this.helpNameAndParams = "sn join";
/* 16 */     this.helpDescription = "Join in on the mmSupernatuals fun!";
/*    */   }
/*    */   
/*    */   public void perform()
/*    */   {
/* 21 */     if (!SNConfigHandler.enableJoinCommand) {
/* 22 */       sendMessage(Language.JOIN_NOT_ENABLE.toString());
/* 23 */       return;
/*    */     }
/* 25 */     Player senderPlayer = (Player)this.sender;
/* 26 */     if (SNWhitelistHandler.playersInWhitelist.contains(senderPlayer.getName()))
/*    */     {
/* 28 */       sendMessage(Language.ALREADY_WHITELISTED.toString());
/* 29 */       return;
/*    */     }
/* 31 */     SNWhitelistHandler.addPlayer(senderPlayer.getName());
/*    */   }
/*    */ }


