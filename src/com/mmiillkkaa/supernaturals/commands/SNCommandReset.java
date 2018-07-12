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
/*    */ public class SNCommandReset
/*    */   extends SNCommand
/*    */ {
/*    */   public SNCommandReset()
/*    */   {
/* 35 */     this.requiredParameters = new ArrayList();
/* 36 */     this.optionalParameters = new ArrayList();
/* 37 */     this.senderMustBePlayer = false;
/* 38 */     this.optionalParameters.add("playername");
/* 39 */     this.permissions = "supernatural.admin.command.reset";
/* 40 */     this.helpNameAndParams = "reset | reset [playername]";
/* 41 */     this.helpDescription = "Reset a player's power to zero";
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
/*    */ 
/* 54 */         if (player == null) {
/* 55 */           sendMessage(Language.PLAYER_NOT_FOUND.toString());
/* 56 */           return;
/*    */         }
/* 58 */         SuperNPlayer snplayer = SuperNManager.get(player);
/* 59 */         SuperNManager.alterPower(snplayer, -10000.0D, Language.BY_ADMIN.toString());
/*    */         
/* 61 */         sendMessage(Language.POWER_RESET.toString().replace(LanguageTag.PLAYER.toString(), snplayer.getName()));
/*    */       }
/*    */       
/*    */ 
/* 65 */       return;
/*    */     }
/*    */     
/* 68 */     Player senderPlayer = (Player)this.sender;
/* 69 */     if (!SupernaturalsPlugin.hasPermissions(senderPlayer, this.permissions)) {
/* 70 */       sendMessage(Language.NO_PREMISSION.toString());
/* 71 */       return;
/*    */     }
/* 73 */     if (this.parameters.isEmpty()) {
/* 74 */       SuperNPlayer snplayer = SuperNManager.get(senderPlayer);
/* 75 */       SuperNManager.alterPower(snplayer, -10000.0D, Language.BY_ADMIN.toString());
/*    */     }
/*    */     else {
/* 78 */       String playername = (String)this.parameters.get(0);
/* 79 */       Player player = SupernaturalsPlugin.instance.getServer().getPlayer(playername);
/*    */       
/*    */ 
/* 82 */       if (player == null) {
/* 83 */         sendMessage(Language.PLAYER_NOT_FOUND.toString());
/* 84 */         return;
/*    */       }
/* 86 */       SuperNPlayer snplayer = SuperNManager.get(player);
/* 87 */       SuperNManager.alterPower(snplayer, -10000.0D, Language.BY_ADMIN.toString());
/*    */       
/* 89 */       sendMessage(Language.POWER_RESET.toString().replace(LanguageTag.PLAYER.toString(), snplayer.getName()));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\commands\SNCommandReset.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */