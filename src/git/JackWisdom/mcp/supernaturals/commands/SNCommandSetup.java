/*    */ package git.JackWisdom.mcp.supernaturals.commands;
/*    */ 
/*    */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*    */ import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
/*    */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*    */ import git.JackWisdom.mcp.supernaturals.util.LanguageTag;
/*    */ import java.util.ArrayList;
/*    */
import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class SNCommandSetup
/*    */   extends SNCommand
/*    */ {
/* 14 */   public int stepNumber = 0;
/*    */   
/*    */   public SNCommandSetup()
/*    */   {
/* 18 */     this.requiredParameters = new ArrayList();
/* 19 */     this.optionalParameters = new ArrayList();
/* 20 */     this.senderMustBePlayer = true;
/* 21 */     this.senderMustBeSupernatural = false;
/* 22 */     this.permissions = "supernatural.command.setup";
/*    */   }
/*    */   
/*    */   public void perform()
/*    */   {
/* 27 */     if (!(this.sender instanceof Player)) {
/* 28 */       sendMessage(Language.ONLY_IN_GAME.toString());
/* 29 */       return;
/*    */     }
/* 31 */     Player player = (Player)this.sender;
/* 32 */     if (player.hasPermission( this.permissions)) {
/* 33 */       if (this.stepNumber == 0) {
/* 34 */         sendMessage(Language.SN_SETUP_CHURCH_GREETING.toString());
/* 35 */         sendMessage(Language.SN_SETUP_CONTINUE.toString());
/* 36 */         this.stepNumber = 1;
/* 37 */       } else if (this.stepNumber == 1) {
/* 38 */         sendMessage(Language.SN_SETUP_CHURCH_GREETING.toString());
/* 39 */         sendMessage(Language.SN_SETUP_CHURCH_ALTAR.toString().replace(LanguageTag.MATERIAL.toString(), SNConfigHandler.priestAltarMaterial.name().toLowerCase().replace("_", " ")));
/*    */         
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 45 */         sendMessage(Language.SN_SETUP_CONTINUE.toString());
/* 46 */         this.stepNumber = 2;
/* 47 */       } else if (this.stepNumber == 2) {
/* 48 */         sendMessage(Language.SN_SETUP_CHURSH_WITHIN_BLOCK.toString().replace(LanguageTag.MATERIAL.toString(), SNConfigHandler.priestAltarMaterial.name().toLowerCase().replace("_", " ")));
/*    */         
/*    */ 
/*    */ 
/*    */ 
/* 53 */         sendMessage(Language.SN_SETUP_CHRUSH_CMD.toString());
/* 54 */         sendMessage(Language.SN_SETUP_CHRUSH_USAGE.toString().replace(LanguageTag.MATERIAL.toString(), SNConfigHandler.priestAltarMaterial.name().toLowerCase().replace("_", " ")));
/*    */         
/*    */ 
/*    */ 
/*    */ 
/* 59 */         sendMessage(Language.SN_SETUP_CONTINUE.toString());
/* 60 */         this.stepNumber = 3;
/* 61 */       } else if (this.stepNumber == 3) {
/* 62 */         sendMessage(Language.SN_SETUP_HALL_GREETING.toString());
/* 63 */         sendMessage(Language.SN_SETUP_HALL_SIGN.toString().replace(LanguageTag.MSG.toString(), SNConfigHandler.hunterHallMessage));
/*    */         
/*    */ 
/* 66 */         sendMessage(Language.SN_SETUP_FINISH.toString());
/* 67 */         this.stepNumber = 0;
/*    */       }
/*    */     } else {
/* 70 */       sendMessage(Language.NO_PREMISSION.toString());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\commands\SNCommandSetup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */