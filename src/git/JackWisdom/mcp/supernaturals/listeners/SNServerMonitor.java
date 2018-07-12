/*    */ package git.JackWisdom.mcp.supernaturals.listeners;
/*    */ 
/*    */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*    */ import java.util.logging.Level;
/*    */
import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.server.PluginEnableEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class SNServerMonitor
/*    */   implements Listener
/*    */ {
/*    */   public SupernaturalsPlugin plugin;
/*    */   
/*    */   public SNServerMonitor(SupernaturalsPlugin instance)
/*    */   {
/* 18 */     this.plugin = instance;
/*    */   }
/*    */   
/*    */   @EventHandler(priority=EventPriority.MONITOR)
/*    */   public void onPluginEnable(PluginEnableEvent event) {
/* 23 */     if (SupernaturalsPlugin.foundPerms) {
/* 24 */       return;
/*    */     }
/* 26 */     Plugin enabledPlugin = event.getPlugin();
/* 27 */     if (enabledPlugin.toString().startsWith("PermissionsEx")) {
/* 28 */       SupernaturalsPlugin.log("Found PermissionsEx!");
/* 29 */       SupernaturalsPlugin.foundPerms = true;
/* 30 */     } else if (enabledPlugin.toString().startsWith("GroupManager")) {
/* 31 */       SupernaturalsPlugin.log("Found GroupManager");
/* 32 */       SupernaturalsPlugin.foundPerms = true;
/* 33 */     } else if (enabledPlugin.toString().startsWith("bPermissions")) {
/* 34 */       SupernaturalsPlugin.log("Found bPermissions.");
/* 35 */       SupernaturalsPlugin.log(Level.WARNING, "If something goes wrong with bPermissions and this plugin, I will not help!");
/*    */       
/*    */ 
/* 38 */       SupernaturalsPlugin.foundPerms = true;
/* 39 */     } else if (enabledPlugin.toString().startsWith("PermissionsBukkit")) {
/* 40 */       SupernaturalsPlugin.log("Found PermissionsBukkit!");
/* 41 */       SupernaturalsPlugin.foundPerms = true;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\listeners\SNServerMonitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */