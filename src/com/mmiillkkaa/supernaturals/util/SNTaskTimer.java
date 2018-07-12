/*    */ package com.mmiillkkaa.supernaturals.util;
/*    */ 
/*    */ import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
/*    */ import com.mmiillkkaa.supernaturals.manager.SuperNManager;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.scheduler.BukkitScheduler;
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
/*    */ public class SNTaskTimer
/*    */   implements Runnable
/*    */ {
/*    */   private SupernaturalsPlugin plugin;
/*    */   
/*    */   public SNTaskTimer(SupernaturalsPlugin plugin)
/*    */   {
/* 29 */     this.plugin = plugin;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void run()
/*    */   {
/* 36 */     this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
/*    */     {
/*    */       public void run()
/*    */       {
/* 40 */         SNTaskTimer.this.plugin.getSuperManager().advanceTime(SuperNManager.findAllOnline());
/*    */       }
/*    */     });
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\util\SNTaskTimer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */