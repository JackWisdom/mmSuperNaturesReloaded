/*    */ package com.mmiillkkaa.supernaturals.commands;
/*    */ 
/*    */ import com.mmiillkkaa.supernaturals.SuperNPlayer;
/*    */ import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
/*    */ import com.mmiillkkaa.supernaturals.manager.SuperNManager;
/*    */ import com.mmiillkkaa.supernaturals.util.Language;
/*    */ import com.mmiillkkaa.supernaturals.util.LanguageTag;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.Server;
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
/*    */ public class SNCommandCure
/*    */   extends SNCommand
/*    */ {
/*    */   public SNCommandCure()
/*    */   {
/* 37 */     this.requiredParameters = new ArrayList();
/* 38 */     this.optionalParameters = new ArrayList();
/* 39 */     this.senderMustBePlayer = false;
/* 40 */     this.optionalParameters.add("playername");
/* 41 */     this.permissions = "supernatural.admin.command.cure";
/*    */   }
/*    */   
/*    */   public void perform()
/*    */   {
/* 46 */     if (!(this.sender instanceof Player)) {
/* 47 */       if (this.parameters.isEmpty()) {
/* 48 */         sendMessage(Language.MISSING_PLAYER.toString());
/*    */       } else {
/* 50 */         String playername = (String)this.parameters.get(0);
/* 51 */         Player player = SupernaturalsPlugin.instance.getServer().getPlayer(playername);
/*    */         
/* 53 */         if (player == null) {
/* 54 */           sendMessage(Language.PLAYER_NOT_FOUND.toString());
/* 55 */           return;
/*    */         }
/* 57 */         sendMessage(Language.FULL_CURE.toString().replace(LanguageTag.PLAYER.toString(), player.getDisplayName()));
/*    */         
/*    */ 
/* 60 */         SuperNPlayer snplayer = SuperNManager.get(player);
/* 61 */         SuperNManager.cure(snplayer);
/*    */       }
/* 63 */       return;
/*    */     }
/* 65 */     Player senderPlayer = (Player)this.sender;
/* 66 */     if (!SupernaturalsPlugin.hasPermissions(senderPlayer, this.permissions)) {
/* 67 */       sendMessage(Language.NO_PREMISSION.toString());
/* 68 */       return;
/*    */     }
/*    */     
/* 71 */     if (this.parameters.isEmpty()) {
/* 72 */       SuperNPlayer snplayer = SuperNManager.get(senderPlayer);
/* 73 */       SuperNManager.cure(snplayer);
/*    */     } else {
/* 75 */       String playername = (String)this.parameters.get(0);
/* 76 */       Player player = SupernaturalsPlugin.instance.getServer().getPlayer(playername);
/*    */       
/* 78 */       if (player == null) {
/* 79 */         sendMessage(Language.MISSING_PLAYER.toString());
/* 80 */         return;
/*    */       }
/* 82 */       sendMessage(Language.FULL_CURE.toString().replace(LanguageTag.PLAYER.toString(), player.getDisplayName()));
/*    */       
/*    */ 
/* 85 */       SuperNPlayer snplayer = SuperNManager.get(player);
/* 86 */       SuperNManager.cure(snplayer);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\commands\SNCommandCure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */