/*    */ package com.mmiillkkaa.supernaturals.commands;
/*    */ 
/*    */ import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
/*    */ import com.mmiillkkaa.supernaturals.util.Language;
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
/*    */ public class SNCommandRestartTask
/*    */   extends SNCommandReload
/*    */ {
/*    */   public SNCommandRestartTask()
/*    */   {
/* 31 */     this.senderMustBePlayer = true;
/* 32 */     this.permissions = "supernatural.admin.command.task";
/* 33 */     this.helpNameAndParams = "";
/* 34 */     this.helpDescription = "Restarts the task timer.";
/*    */   }
/*    */   
/*    */   public void perform()
/*    */   {
/* 39 */     Player senderPlayer = (Player)this.sender;
/* 40 */     if (!SupernaturalsPlugin.hasPermissions(senderPlayer, this.permissions)) {
/* 41 */       sendMessage(Language.NO_PREMISSION.toString());
/* 42 */       return;
/*    */     }
/* 44 */     SupernaturalsPlugin.restartTask();
/* 45 */     sendMessage(Language.TASK_TIMER_RESTART.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\commands\SNCommandRestartTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */