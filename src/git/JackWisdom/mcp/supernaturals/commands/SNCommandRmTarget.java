/*     */ package git.JackWisdom.mcp.supernaturals.commands;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*     */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */ import git.JackWisdom.mcp.supernaturals.manager.HunterManager;
/*     */ import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
/*     */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*     */ import git.JackWisdom.mcp.supernaturals.util.LanguageTag;
/*     */ import java.util.ArrayList;
/*     */
/*     */
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
                 SuperNPlayer snplayer=null;
                    for(Player p:Bukkit.getOnlinePlayers()){
                        if (p.getName().equals(playername)){
                            snplayer=SuperNManager.get(p);
                        }
                    }
/*  50 */
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
/*  73 */     if (!senderPlayer.hasPermission( this.permissions)) {
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
/*  88 */                        SuperNPlayer snplayer=null;
              for(Player p:Bukkit.getOnlinePlayers()){
                   if (p.getName().equals(playername)){
                       snplayer=SuperNManager.get(p);
                   }
             }
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