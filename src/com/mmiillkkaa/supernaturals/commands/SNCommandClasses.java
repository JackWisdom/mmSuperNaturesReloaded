/*    */ package com.mmiillkkaa.supernaturals.commands;
/*    */ 
/*    */ import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
/*    */ import com.mmiillkkaa.supernaturals.util.Language;
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
/*    */ public class SNCommandClasses
/*    */   extends SNCommand
/*    */ {
/* 32 */   private static List<String> classMessages = new ArrayList();
/*    */   
/*    */   public SNCommandClasses()
/*    */   {
/* 36 */     this.requiredParameters = new ArrayList();
/* 37 */     this.optionalParameters = new ArrayList();
/* 38 */     this.senderMustBePlayer = false;
/* 39 */     this.senderMustBeSupernatural = false;
/* 40 */     this.permissions = "supernatural.command.classes";
/*    */   }
/*    */   
/*    */ 
/*    */   public void perform()
/*    */   {
/* 46 */     classMessages.add(String.format("*** %s ***", new Object[] { Language.SUPERNATURAL_CLASS }));
/*    */     
/* 48 */     classMessages.add(String.format("%s: %s- %s", new Object[] { Language.SN_HUMAN_NAME, ChatColor.WHITE, Language.SN_HUMAN_DESC }));
/*    */     
/* 50 */     classMessages.add(String.format("%s: %s- %s", new Object[] { Language.SN_PREIEST_NAME, ChatColor.WHITE, Language.SN_PRIEST_DESC }));
/*    */     
/* 52 */     classMessages.add(String.format("%s: %s- %s", new Object[] { Language.SN_VAMPIRE_NAME, ChatColor.WHITE, Language.SN_VAMPIRE_DESC }));
/*    */     
/* 54 */     classMessages.add(String.format("%s: %s- %s", new Object[] { Language.SN_GHOUL_NAME, ChatColor.WHITE, Language.SN_GHOUL_DESC }));
/*    */     
/* 56 */     classMessages.add(String.format("%s: %s- %s", new Object[] { Language.SN_WEREWOLF_NAME, ChatColor.WHITE, Language.SN_WEREWOLF_DESC }));
/*    */     
/*    */ 
/* 59 */     classMessages.add(String.format("%s: %s- %s", new Object[] { Language.SN_WITCHHUNTER_NAME, ChatColor.WHITE, Language.SN_WITCHHUNTER_DESC }));
/*    */     
/*    */ 
/* 62 */     classMessages.add(String.format("%s: %s- %s", new Object[] { Language.SN_DEMON_NAME, ChatColor.WHITE, Language.SN_DEMON_DESC }));
/*    */     
/* 64 */     classMessages.add(String.format("%s: %s- %s", new Object[] { Language.SN_ANGEL_NAME, ChatColor.WHITE, Language.SN_ANGEL_DESC }));
/*    */     
/*    */ 
/* 67 */     if (!(this.sender instanceof Player)) {
/* 68 */       sendMessage(classMessages);
/* 69 */       return;
/*    */     }
/* 71 */     Player senderPlayer = (Player)this.sender;
/*    */     
/* 73 */     if (!SupernaturalsPlugin.hasPermissions(senderPlayer, this.permissions)) {
/* 74 */       sendMessage(Language.NO_PREMISSION.toString());
/* 75 */       return;
/*    */     }
/*    */     
/* 78 */     sendMessage(classMessages);
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\commands\SNCommandClasses.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */