/*     */ package com.mmiillkkaa.supernaturals.commands;
/*     */ 
/*     */ import com.mmiillkkaa.supernaturals.SuperNPlayer;
/*     */ import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
/*     */ import com.mmiillkkaa.supernaturals.manager.HunterManager;
/*     */ import com.mmiillkkaa.supernaturals.manager.SuperNManager;
/*     */ import com.mmiillkkaa.supernaturals.util.Language;
/*     */ import com.mmiillkkaa.supernaturals.util.LanguageTag;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ 
/*     */ public class SNCommandRmTarget
/*     */   extends SNCommand
/*     */ {
/*     */   public SNCommandRmTarget()
/*     */   {
/*  36 */     this.requiredParameters = new ArrayList();
/*  37 */     this.optionalParameters = new ArrayList();
/*  38 */     this.senderMustBePlayer = false;
/*  39 */     this.optionalParameters.add("playername");
/*  40 */     this.permissions = "supernatural.admin.command.rmtarget";
/*     */   }
/*     */   
/*     */   public void perform()
/*     */   {
/*  45 */     if (!(this.sender instanceof Player)) {
/*  46 */       if (this.parameters.isEmpty()) {
/*  47 */         sendMessage(Language.MISSING_PLAYER.toString());
/*     */       } else {
/*  49 */         String playername = (String)this.parameters.get(0);
/*  50 */         SuperNPlayer snplayer = SuperNManager.get(playername);
/*     */         
/*  52 */         if (snplayer == null) {
/*  53 */           sendMessage(Language.PLAYER_NOT_FOUND.toString());
/*  54 */           return;
/*     */         }
/*     */         
/*  57 */         if (HunterManager.removeBounty(snplayer)) {
/*  58 */           sendMessage(Language.TARGET_REMOVE_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), snplayer.getName()));
/*     */           
/*     */ 
/*  61 */           HunterManager.addBounty();
/*  62 */           return;
/*     */         }
/*  64 */         sendMessage(Language.TARGET_NOT_ACTIVE_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), snplayer.getName()));
/*     */         
/*     */ 
/*  67 */         return;
/*     */       }
/*     */       
/*  70 */       return;
/*     */     }
/*  72 */     Player senderPlayer = (Player)this.sender;
/*  73 */     if (!SupernaturalsPlugin.hasPermissions(senderPlayer, this.permissions)) {
/*  74 */       sendMessage(Language.NO_PREMISSION.toString());
/*  75 */       return;
/*     */     }
/*     */     
/*  78 */     if (this.parameters.isEmpty()) {
/*  79 */       SuperNPlayer snplayer = SuperNManager.get(senderPlayer);
/*  80 */       if (HunterManager.removeBounty(snplayer)) {
/*  81 */         sendMessage(Language.TARGET_REMOVE_NOTICE_OTHER.toString());
/*     */       }
/*     */       else {
/*  84 */         sendMessage(Language.TARGET_NOT_ACTIVE_NOTICE_OTHER.toString());
/*     */       }
/*     */     } else {
/*  87 */       String playername = (String)this.parameters.get(0);
/*  88 */       SuperNPlayer snplayer = SuperNManager.get(playername);
/*     */       
/*  90 */       if (snplayer == null) {
/*  91 */         sendMessage(Language.PLAYER_NOT_FOUND.toString());
/*  92 */         return;
/*     */       }
/*     */       
/*  95 */       if (HunterManager.removeBounty(snplayer)) {
/*  96 */         sendMessage(Language.TARGET_REMOVE_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), snplayer.getName()));
/*     */         
/*     */ 
/*  99 */         HunterManager.addBounty();
/*     */       } else {
/* 101 */         sendMessage(Language.TARGET_NOT_ACTIVE_NOTICE_SELF.toString().replace(LanguageTag.PLAYER.toString(), snplayer.getName()));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\commands\SNCommandRmTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */