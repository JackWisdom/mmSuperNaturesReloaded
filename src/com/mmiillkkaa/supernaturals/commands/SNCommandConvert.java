/*     */ package com.mmiillkkaa.supernaturals.commands;
/*     */ 
/*     */ import com.mmiillkkaa.supernaturals.SuperNPlayer;
/*     */ import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
/*     */ import com.mmiillkkaa.supernaturals.io.SNConfigHandler;
/*     */ import com.mmiillkkaa.supernaturals.manager.SuperNManager;
/*     */ import com.mmiillkkaa.supernaturals.util.Language;
/*     */ import com.mmiillkkaa.supernaturals.util.LanguageTag;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SNCommandConvert
/*     */   extends SNCommand
/*     */ {
/*     */   public SNCommandConvert()
/*     */   {
/*  36 */     this.requiredParameters = new ArrayList();
/*  37 */     this.optionalParameters = new ArrayList();
/*  38 */     this.senderMustBePlayer = false;
/*  39 */     this.optionalParameters.add("playername");
/*  40 */     this.requiredParameters.add("supernaturalType");
/*  41 */     this.permissions = "supernatural.admin.command.curse";
/*  42 */     this.helpNameAndParams = "convert [playername] [supernaturalType]";
/*  43 */     this.helpDescription = "Instantly turn a player into a supernatural.";
/*     */   }
/*     */   
/*  46 */   public static String permission2 = "supernatural.admin.partial.curse";
/*     */   
/*     */ 
/*     */   public void perform()
/*     */   {
/*  51 */     if (!(this.sender instanceof Player)) {
/*  52 */       if (this.parameters.size() == 1) {
/*  53 */         sendMessage(Language.MISSING_PLAYER.toString());
/*     */       } else {
/*  55 */         String playername = (String)this.parameters.get(0);
/*  56 */         String superType = ((String)this.parameters.get(1)).toLowerCase();
/*  57 */         Player player = SupernaturalsPlugin.instance.getServer().getPlayer(playername);
/*     */         
/*     */ 
/*  60 */         if (player == null) {
/*  61 */           sendMessage(Language.PLAYER_NOT_FOUND.toString());
/*  62 */           return;
/*     */         }
/*     */         
/*  65 */         if (!SNConfigHandler.supernaturalTypes.contains(superType)) {
/*  66 */           sendMessage(Language.INVALID_SUPERNATURAL_TYPE.toString());
/*     */           
/*  68 */           return;
/*     */         }
/*     */         
/*  71 */         SuperNPlayer snplayer = SuperNManager.get(player);
/*  72 */         if (snplayer.getType().equalsIgnoreCase(superType)) {
/*  73 */           sendMessage(Language.TYPE_ALREADY.toString().replace(LanguageTag.PLAYER.toString(), player.getName()).replace(LanguageTag.TYPE.toString(), superType));
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/*  78 */         else if (snplayer.getOldType().equalsIgnoreCase(superType)) {
/*  79 */           sendMessage(Language.TYPE_TURN_BACK.toString().replace(LanguageTag.PLAYER.toString(), player.getName()).replace(LanguageTag.TYPE.toString(), superType));
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*  84 */           SuperNManager.revert(snplayer);
/*     */         } else {
/*  86 */           sendMessage(Language.TYPE_TURN.toString().replace(LanguageTag.PLAYER.toString(), player.getName()).replace(LanguageTag.TYPE.toString(), superType));
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*  91 */           SuperNManager.convert(snplayer, superType);
/*     */         }
/*     */       }
/*  94 */       return;
/*     */     }
/*     */     
/*  97 */     Player senderPlayer = (Player)this.sender;
/*  98 */     if (this.parameters.size() == 1) {
/*  99 */       if (!SupernaturalsPlugin.hasPermissions(senderPlayer, permission2)) {
/* 100 */         sendMessage(Language.NO_PREMISSION.toString());
/* 101 */         return;
/*     */       }
/* 103 */       String superType = ((String)this.parameters.get(0)).toLowerCase();
/*     */       
/* 105 */       if (!SNConfigHandler.supernaturalTypes.contains(superType)) {
/* 106 */         sendMessage(Language.INVALID_SUPERNATURAL_TYPE.toString());
/* 107 */         return;
/*     */       }
/*     */       
/* 110 */       SuperNPlayer snplayer = SuperNManager.get(senderPlayer);
/* 111 */       if (snplayer.getType().equalsIgnoreCase(superType)) {
/* 112 */         sendMessage(Language.TYPE_ALREADY.toString().replace(LanguageTag.PLAYER.toString(), senderPlayer.getName()).replace(LanguageTag.TYPE.toString(), superType));
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 117 */       else if (snplayer.getOldType().equalsIgnoreCase(superType)) {
/* 118 */         sendMessage(Language.TYPE_TURN_BACK.toString().replace(LanguageTag.PLAYER.toString(), senderPlayer.getName()).replace(LanguageTag.TYPE.toString(), superType));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 123 */         SuperNManager.revert(snplayer);
/*     */       } else {
/* 125 */         sendMessage(Language.TYPE_TURN.toString().replace(LanguageTag.PLAYER.toString(), senderPlayer.getName()).replace(LanguageTag.TYPE.toString(), superType));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 130 */         SuperNManager.convert(snplayer, superType);
/*     */       }
/*     */     }
/*     */     else {
/* 134 */       if (!SupernaturalsPlugin.hasPermissions(senderPlayer, this.permissions)) {
/* 135 */         sendMessage(Language.NO_PREMISSION.toString());
/* 136 */         return;
/*     */       }
/* 138 */       String playername = (String)this.parameters.get(0);
/* 139 */       String superType = ((String)this.parameters.get(1)).toLowerCase();
/* 140 */       Player player = SupernaturalsPlugin.instance.getServer().getPlayer(playername);
/*     */       
/*     */ 
/* 143 */       if (player == null) {
/* 144 */         sendMessage(Language.PLAYER_NOT_FOUND.toString());
/* 145 */         return;
/*     */       }
/*     */       
/* 148 */       if (!SNConfigHandler.supernaturalTypes.contains(superType)) {
/* 149 */         sendMessage(Language.INVALID_SUPERNATURAL_TYPE.toString());
/* 150 */         return;
/*     */       }
/*     */       
/* 153 */       SuperNPlayer snplayer = SuperNManager.get(player);
/* 154 */       if (snplayer.getType().equalsIgnoreCase(superType)) {
/* 155 */         sendMessage(Language.TYPE_ALREADY.toString().replace(LanguageTag.PLAYER.toString(), player.getName()).replace(LanguageTag.TYPE.toString(), superType));
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 160 */       else if (snplayer.getOldType().equalsIgnoreCase(superType)) {
/* 161 */         sendMessage(Language.TYPE_TURN_BACK.toString().replace(LanguageTag.PLAYER.toString(), player.getName()).replace(LanguageTag.TYPE.toString(), superType));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 166 */         SuperNManager.revert(snplayer);
/*     */       } else {
/* 168 */         sendMessage(Language.TYPE_TURN.toString().replace(LanguageTag.PLAYER.toString(), player.getName()).replace(LanguageTag.TYPE.toString(), superType));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 173 */         SuperNManager.convert(snplayer, superType);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\commands\SNCommandConvert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */