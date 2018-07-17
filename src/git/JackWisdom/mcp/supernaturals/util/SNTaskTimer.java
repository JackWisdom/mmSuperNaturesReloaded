/*    */ package git.JackWisdom.mcp.supernaturals.util;
/*    */ 
/*    */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*    */ import git.JackWisdom.mcp.supernaturals.UsingData;
import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
import org.bukkit.Bukkit;

/*    */ public class SNTaskTimer
/*    */   implements Runnable,UsingData
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
/* 36 */      SNTaskTimer.this.plugin.getSuperManager().advanceTime(superpowers.values());
/*    */   }
/*    */ }


