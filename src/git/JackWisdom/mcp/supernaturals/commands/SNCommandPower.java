/*     */ package git.JackWisdom.mcp.supernaturals.commands;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*     */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */ import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
/*     */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*     */ import git.JackWisdom.mcp.supernaturals.util.LanguageTag;
/*     */ import java.util.ArrayList;
/*     */
/*     */
/*     */ import org.bukkit.Material;
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
/*     */ 
/*     */ public class SNCommandPower
/*     */   extends SNCommand
/*     */ {
/*     */   public SNCommandPower()
/*     */   {
/*  37 */     this.requiredParameters = new ArrayList();
/*  38 */     this.optionalParameters = new ArrayList();
/*  39 */     this.senderMustBePlayer = true;
/*  40 */     this.senderMustBeSupernatural = true;
/*  41 */     this.permissions = "supernatural.command.power";
/*  42 */     this.optionalParameters.add("playername");
/*  43 */     this.optionalParameters.add("power");
/*  44 */     this.helpNameAndParams = "power [amount] | power [playername] [amount]";
/*  45 */     this.helpDescription = "See current power level";
/*     */   }
/*     */   
/*     */ 
/*     */   public void perform()
/*     */   {
/*  51 */     Player senderPlayer = (Player)this.sender;
/*  52 */     String permissions2 = "supernatural.admin.command.power";
/*     */     
/*  54 */     if (this.parameters.isEmpty()) {
/*  55 */       if (!senderPlayer.hasPermission( this.permissions)) {
/*  56 */         sendMessage(Language.NO_PREMISSION.toString());
/*  57 */         return;
/*     */       }
/*  59 */       SuperNPlayer snplayer = SuperNManager.get(senderPlayer);
/*     */       
/*  61 */       sendMessage(Language.POWER_INFO.toString().replace(LanguageTag.TYPE.toString(), snplayer.getType().name()).replace(LanguageTag.POWER.toString(), Double.toString(snplayer.getPower())));
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/*  67 */       if (!senderPlayer.hasPermission( permissions2)) {
/*  68 */         sendMessage(Language.NO_PREMISSION.toString());

/*  69 */         return;
/*     */       }
/*  71 */       if (this.parameters.size() == 1)
/*     */       {
/*     */         int powerGain;
/*     */         try {
/*  75 */           powerGain = Integer.parseInt( this.parameters.get(0));
/*     */         } catch (NumberFormatException e) {
/*  77 */           sendMessage(Language.INVALID_NUMBER.toString());
/*  78 */           return;
/*     */         }
/*  80 */         if (powerGain >= 10000  ) {
/*  81 */           powerGain = 9999 ;
/*     */         }
/*     */         
/*  84 */         SuperNPlayer snplayer = SuperNManager.get(senderPlayer);
/*  85 */         SuperNManager.alterPower(snplayer, powerGain, Language.ADMIN_BOOST.toString());
/*     */       }
/*     */       else {
/*  88 */         String playername = (String)this.parameters.get(0);
/*  89 */         Player player = SupernaturalsPlugin.instance.getServer().getPlayer(playername);
/*     */         
/*  91 */         if (player == null) {
/*  92 */           sendMessage(Language.PLAYER_NOT_FOUND.toString()); return;
/*     */         }
/*     */         
/*     */         int powerGain;
/*     */         try
/*     */         {
/*  98 */           powerGain = Integer.parseInt( this.parameters.get(1));
/*     */         } catch (NumberFormatException e) {
/* 100 */           sendMessage(Language.INVALID_NUMBER.toString());
/* 101 */           return;
/*     */         }
/* 103 */         if (powerGain >= 10000 ) {
/* 104 */           powerGain = 9999 ;
/*     */         }
/* 106 */         sendMessage(Language.POWER_UP.toString());
/* 107 */         SuperNPlayer snplayer = SuperNManager.get(player);
/* 108 */         SuperNManager.alterPower(snplayer, powerGain, Language.ADMIN_BOOST.toString());
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\commands\SNCommandPower.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */