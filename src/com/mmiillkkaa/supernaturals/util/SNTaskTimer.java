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


