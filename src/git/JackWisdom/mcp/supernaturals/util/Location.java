/*    */ package git.JackWisdom.mcp.supernaturals.util;
/*    */ 
/*    */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*    */ import java.io.Serializable;
/*    */
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

    public Location(String world, double aDouble, double aDouble1, double aDouble2) {
    this.world=world;this.x=aDouble;this.y=aDouble1;this.z=aDouble2;
    }

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
/*    */   public String getWorldName(){
    return world;
}
/*    */   public World getWorld() {
/* 41 */     return SupernaturalsPlugin.instance.getServer().getWorld(this.world);
/*    */   }
/*    */ }


