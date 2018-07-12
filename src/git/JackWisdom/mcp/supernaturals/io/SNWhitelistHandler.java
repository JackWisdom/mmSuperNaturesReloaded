/*    */ package git.JackWisdom.mcp.supernaturals.io;
/*    */ 
/*    */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*    */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SNWhitelistHandler
/*    */ {
/* 18 */   public static FileConfiguration whitelistYaml = null;
/*    */   
/*    */ 
/* 21 */   public static File whitelistYamlFile = null;
/*    */   public static SupernaturalsPlugin plugin;
/* 23 */   public static List<String> playersInWhitelist = new ArrayList();
/*    */   
/*    */   public SNWhitelistHandler(SupernaturalsPlugin instance) {
/* 26 */     plugin = instance;
/*    */   }
/*    */   
/*    */   public static void reloadWhitelist() {
/* 30 */     if (whitelistYamlFile == null) {
/* 31 */       whitelistYamlFile = new File(plugin.getDataFolder(), "whitelistYaml.yml");
/*    */     }
/*    */     
/* 34 */     whitelistYaml = YamlConfiguration.loadConfiguration(whitelistYamlFile);
/* 35 */     playersInWhitelist = getPlayersInWhitelistYAML();
/*    */   }
/*    */   
/*    */   public static FileConfiguration getWhitelist() {
/* 39 */     if (whitelistYaml == null) {
/* 40 */       reloadWhitelist();
/*    */     }
/* 42 */     return whitelistYaml;
/*    */   }
/*    */   
/*    */   public static void saveWhitelist() {
/* 46 */     if ((whitelistYaml == null) || (whitelistYamlFile == null)) {
/* 47 */       return;
/*    */     }
/*    */     try {
/* 50 */       whitelistYaml.save(whitelistYamlFile);
/*    */     } catch (IOException ex) {
/* 52 */       Logger.getLogger("Minecraft").log(Level.SEVERE, "Could not save config to " + whitelistYamlFile, ex);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void addPlayer(String playerName)
/*    */   {
/* 58 */     playersInWhitelist.add(playerName);
/* 59 */     whitelistYaml.set("WhitelistedPlayers", playersInWhitelist);
/*    */   }
/*    */   
/*    */   public static boolean isWhitelisted(SuperNPlayer player) {
/* 63 */     if (!SNConfigHandler.enableJoinCommand) {
/* 64 */       return true;
/*    */     }
/* 66 */     return getPlayersInWhitelistYAML().contains(player.getName());
/*    */   }
/*    */   
/*    */   public static List<String> getPlayersInWhitelistYAML()
/*    */   {
/* 71 */     return getWhitelist().getStringList("WhitelistedPlayers");
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\io\SNWhitelistHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */