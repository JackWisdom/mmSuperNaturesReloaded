/*    */ package com.mmiillkkaa.supernaturals.util;
/*    */ 
/*    */ import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
/*    */ import com.mmiillkkaa.supernaturals.manager.HunterManager;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Arrow;
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
/*    */ public class ArrowUtil
/*    */   implements Runnable
/*    */ {
/*    */   private Player player;
/*    */   private Arrow arrow;
/*    */   private Location targetLocation;
/*    */   private long startTime;
/*    */   private double grappleDistance;
/*    */   
/*    */   public ArrowUtil(Player player, Arrow arrow)
/*    */   {
/* 38 */     this.player = player;
/* 39 */     this.arrow = arrow;
/*    */   }
/*    */   
/*    */   public ArrowUtil(Player player, Location targetLocation) {
/* 43 */     this.player = player;
/* 44 */     this.targetLocation = targetLocation;
/* 45 */     this.startTime = System.currentTimeMillis();
/* 46 */     this.grappleDistance = player.getLocation().distance(targetLocation);
/* 47 */     this.arrow = null;
/*    */   }
/*    */   
/*    */   public ArrowUtil(Player player, Location targetLocation, long startTime, double grappleDistance)
/*    */   {
/* 52 */     this.player = player;
/* 53 */     this.targetLocation = targetLocation;
/* 54 */     this.startTime = startTime;
/* 55 */     this.grappleDistance = grappleDistance;
/* 56 */     this.arrow = null;
/*    */   }
/*    */   
/*    */   public void run()
/*    */   {
/* 61 */     if (this.arrow != null) {
/* 62 */       SupernaturalsPlugin.instance.getHunterManager().splitArrow(this.player, this.arrow);
/*    */       
/* 64 */       return;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\util\ArrowUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */