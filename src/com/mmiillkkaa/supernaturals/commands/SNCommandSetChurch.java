/*    */ package com.mmiillkkaa.supernaturals.commands;
/*    */ 
/*    */ import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
/*    */ import com.mmiillkkaa.supernaturals.io.SNConfigHandler;
/*    */ import com.mmiillkkaa.supernaturals.util.Language;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.configuration.Configuration;
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
/*    */ public class SNCommandSetChurch
/*    */   extends SNCommand
/*    */ {
/*    */   public SNCommandSetChurch()
/*    */   {
/* 33 */     this.requiredParameters = new ArrayList();
/* 34 */     this.optionalParameters = new ArrayList();
/* 35 */     this.senderMustBePlayer = true;
/* 36 */     this.senderMustBeSupernatural = true;
/* 37 */     this.permissions = "supernatural.admin.command.setchurch";
/* 38 */     this.helpNameAndParams = "";
/* 39 */     this.helpDescription = "Sets the current location as the church";
/*    */   }
/*    */   
/*    */ 
/*    */   public void perform()
/*    */   {
/* 45 */     Player senderPlayer = (Player)this.sender;
/* 46 */     if (!SupernaturalsPlugin.hasPermissions(senderPlayer, this.permissions)) {
/* 47 */       sendMessage(Language.NO_PREMISSION.toString());
/* 48 */       return;
/*    */     }
/*    */     
/* 51 */     double currentX = senderPlayer.getLocation().getX();
/* 52 */     double currentY = senderPlayer.getLocation().getY();
/* 53 */     double currentZ = senderPlayer.getLocation().getZ();
/*    */     
/* 55 */     SNConfigHandler.priestChurchWorld = senderPlayer.getWorld().getName();
/* 56 */     SNConfigHandler.priestChurchLocationX = (int)currentX;
/* 57 */     SNConfigHandler.priestChurchLocationY = (int)currentY;
/* 58 */     SNConfigHandler.priestChurchLocationZ = (int)currentZ;
/* 59 */     SNConfigHandler.priestChurchLocation = senderPlayer.getLocation();
/*    */     
/* 61 */     SNConfigHandler.getConfig().set("Priest.Church.World", SNConfigHandler.priestChurchWorld);
/*    */     
/* 63 */     SNConfigHandler.getConfig().set("Priest.Church.Location.X", Integer.valueOf(SNConfigHandler.priestChurchLocationX));
/*    */     
/* 65 */     SNConfigHandler.getConfig().set("Priest.Church.Location.Y", Integer.valueOf(SNConfigHandler.priestChurchLocationY));
/*    */     
/* 67 */     SNConfigHandler.getConfig().set("Priest.Church.Location.Z", Integer.valueOf(SNConfigHandler.priestChurchLocationZ));
/*    */     
/*    */ 
/* 70 */     SNConfigHandler.saveConfig();
/*    */     
/* 72 */     sendMessage(Language.SET_CHRUSH.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\commands\SNCommandSetChurch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */