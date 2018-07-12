/*    */ package com.mmiillkkaa.supernaturals.util;
/*    */ 
/*    */ import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
/*    */ import java.io.Serializable;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Location
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8884729998863928105L;
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   private String world;
/*    */   
/*    */   public Location(org.bukkit.Location location)
/*    */   {
/* 22 */     this.x = location.getX();
/* 23 */     this.y = location.getY();
/* 24 */     this.z = location.getZ();
/* 25 */     this.world = location.getWorld().getName();
/*    */   }
/*    */   
/*    */   public double getX() {
/* 29 */     return this.x;
/*    */   }
/*    */   
/*    */   public double getY() {
/* 33 */     return this.y;
/*    */   }
/*    */   
/*    */   public double getZ() {
/* 37 */     return this.z;
/*    */   }
/*    */   
/*    */   public World getWorld() {
/* 41 */     return SupernaturalsPlugin.instance.getServer().getWorld(this.world);
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\util\Location.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */