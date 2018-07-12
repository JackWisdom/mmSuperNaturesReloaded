/*    */ package git.JackWisdom.mcp.supernaturals.commands;
/*    */ 
/*    */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*    */ import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
/*    */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*    */ import java.util.ArrayList;
/*    */
/*    */
/*    */
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
/*    */ public class SNCommandSetBanish
/*    */   extends SNCommand
/*    */ {
/*    */   public SNCommandSetBanish()
/*    */   {
/* 34 */     this.requiredParameters = new ArrayList();
/* 35 */     this.optionalParameters = new ArrayList();
/* 36 */     this.senderMustBePlayer = true;
/* 37 */     this.senderMustBeSupernatural = true;
/* 38 */     this.permissions = "supernatural.admin.command.setbanish";
/* 39 */     this.helpNameAndParams = "";
/* 40 */     this.helpDescription = "Sets the current location as the priests' banish location";
/*    */   }
/*    */   
/*    */ 
/*    */   public void perform()
/*    */   {
/* 46 */     Player senderPlayer = (Player)this.sender;
/* 47 */     if (!senderPlayer.hasPermission( this.permissions)) {
/* 48 */       sendMessage(Language.NO_PREMISSION.toString());
/* 49 */       return;
/*    */     }
/*    */     
/* 52 */     double currentX = senderPlayer.getLocation().getX();
/* 53 */     double currentY = senderPlayer.getLocation().getY();
/* 54 */     double currentZ = senderPlayer.getLocation().getZ();
/*    */     
/* 56 */     SNConfigHandler.priestBanishWorld = senderPlayer.getWorld().getName();
/* 57 */     SNConfigHandler.priestBanishLocationX = (int)currentX;
/* 58 */     SNConfigHandler.priestBanishLocationY = (int)currentY;
/* 59 */     SNConfigHandler.priestBanishLocationZ = (int)currentZ;
/*    */     
/* 61 */     SNConfigHandler.priestBanishLocation = senderPlayer.getLocation();
/*    */     
/* 63 */     SNConfigHandler.getConfig().set("PRIEST.Banish.World", SNConfigHandler.priestBanishWorld);
/*    */     
/* 65 */     SNConfigHandler.getConfig().set("PRIEST.Banish.Location.X", Integer.valueOf(SNConfigHandler.priestBanishLocationX));
/*    */     
/* 67 */     SNConfigHandler.getConfig().set("PRIEST.Banish.Location.Y", Integer.valueOf(SNConfigHandler.priestBanishLocationY));
/*    */     
/* 69 */     SNConfigHandler.getConfig().set("PRIEST.Banish.Location.Z", Integer.valueOf(SNConfigHandler.priestBanishLocationZ));
/*    */     
/*    */ 
/* 72 */     SNConfigHandler.saveConfig();
/*    */     
/* 74 */     sendMessage(Language.SET_BANISH.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\commands\SNCommandSetBanish.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */