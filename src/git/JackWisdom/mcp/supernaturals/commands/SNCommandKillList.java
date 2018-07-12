/*    */ package git.JackWisdom.mcp.supernaturals.commands;
/*    */ 
/*    */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*    */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*    */ import git.JackWisdom.mcp.supernaturals.manager.HunterManager;
/*    */ import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
/*    */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
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
/*    */ public class SNCommandKillList
/*    */   extends SNCommand
/*    */ {
/*    */   public SNCommandKillList()
/*    */   {
/* 38 */     this.requiredParameters = new ArrayList();
/* 39 */     this.optionalParameters = new ArrayList();
/* 40 */     this.senderMustBePlayer = true;
/* 41 */     this.permissions = "supernatural.command.killlist";
/* 42 */     this.helpNameAndParams = "convert [playername] [supernaturalType]";
/* 43 */     this.helpDescription = "Instantly turn a player into a supernatural.";
/*    */   }
/*    */   
/*    */ 
/*    */   public void perform()
/*    */   {
/* 49 */     Player senderPlayer = (Player)this.sender;
/* 50 */     SuperNPlayer snSender = SuperNManager.get(senderPlayer);
/* 51 */     if (!senderPlayer.hasPermission( this.permissions)) {
/* 52 */       sendMessage(Language.NO_PREMISSION.toString());
/* 53 */       return;
/*    */     }
/*    */     
/* 56 */     if (!snSender.isHunter()) {
/* 57 */       sendMessage(Language.NOT_WITCHHUNTER.toString());
/*    */     }
/*    */     
/* 60 */     ArrayList<SuperNPlayer> bountyList = HunterManager.getBountyList();
/*    */     
/*    */ 
/* 63 */     List<String> messages = new ArrayList();
/* 64 */     messages.add(String.format("*** %s ***", new Object[] { Language.NOT_WITCHHUNTER }));
/* 65 */     for (SuperNPlayer snplayer : bountyList) {
/* 66 */       messages.add(ChatColor.WHITE + snplayer.getName());
/*    */     }
/*    */     
/*    */ 
/* 70 */     sendMessage(messages);
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\commands\SNCommandKillList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */