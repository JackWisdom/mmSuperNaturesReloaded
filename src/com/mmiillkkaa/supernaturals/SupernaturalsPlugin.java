/*     */ package com.mmiillkkaa.supernaturals;
/*     */ 
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommand;
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommandAdmin;
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommandClasses;
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommandConvert;
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommandCure;
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommandHelp;
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommandJoin;
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommandKillList;
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommandList;
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommandPower;
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommandReload;
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommandReset;
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommandRestartTask;
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommandRmTarget;
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommandSave;
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommandSetBanish;
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommandSetChurch;
/*     */ import com.mmiillkkaa.supernaturals.commands.SNCommandSetup;
/*     */ import com.mmiillkkaa.supernaturals.io.SNConfigHandler;
/*     */ import com.mmiillkkaa.supernaturals.io.SNDataHandler;
/*     */ import com.mmiillkkaa.supernaturals.io.SNLanguageHandler;
/*     */ import com.mmiillkkaa.supernaturals.io.SNPlayerHandler;
/*     */ import com.mmiillkkaa.supernaturals.io.SNWhitelistHandler;
/*     */ import com.mmiillkkaa.supernaturals.listeners.SNBlockListener;
/*     */ import com.mmiillkkaa.supernaturals.listeners.SNEntityListener;
/*     */ import com.mmiillkkaa.supernaturals.listeners.SNEntityMonitor;
/*     */ import com.mmiillkkaa.supernaturals.listeners.SNPlayerListener;
/*     */ import com.mmiillkkaa.supernaturals.listeners.SNPlayerMonitor;
/*     */ import com.mmiillkkaa.supernaturals.listeners.SNServerMonitor;
/*     */ import com.mmiillkkaa.supernaturals.manager.AngelManager;
/*     */ import com.mmiillkkaa.supernaturals.manager.ClassManager;
/*     */ import com.mmiillkkaa.supernaturals.manager.DemonManager;
/*     */ import com.mmiillkkaa.supernaturals.manager.GhoulManager;
/*     */ import com.mmiillkkaa.supernaturals.manager.HumanManager;
/*     */ import com.mmiillkkaa.supernaturals.manager.HunterManager;
/*     */ import com.mmiillkkaa.supernaturals.manager.PriestManager;
/*     */ import com.mmiillkkaa.supernaturals.manager.SuperNManager;
/*     */ import com.mmiillkkaa.supernaturals.manager.VampireManager;
/*     */ import com.mmiillkkaa.supernaturals.manager.WereManager;
/*     */ import com.mmiillkkaa.supernaturals.util.Language;
/*     */ import com.mmiillkkaa.supernaturals.util.LanguageTag;
/*     */ import com.sk89q.worldedit.Vector;
/*     */ import com.sk89q.worldguard.bukkit.BukkitUtil;
/*     */ import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
/*     */ import com.sk89q.worldguard.protection.ApplicableRegionSet;
/*     */ import com.sk89q.worldguard.protection.flags.DefaultFlag;
/*     */ import com.sk89q.worldguard.protection.managers.RegionManager;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.PluginDescriptionFile;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SupernaturalsPlugin
/*     */   extends JavaPlugin
/*     */ {
/*     */   public static SupernaturalsPlugin instance;
/*  91 */   private final SNConfigHandler snConfig = new SNConfigHandler(this);
/*  92 */   private final SNLanguageHandler snLanguage = new SNLanguageHandler(this);
/*  93 */   private SNDataHandler snData = new SNDataHandler();
/*  94 */   private SNWhitelistHandler snWhitelist = new SNWhitelistHandler(this);
/*     */   
/*  96 */   private SuperNManager superManager = new SuperNManager(this);
/*  97 */   private HumanManager humanManager = new HumanManager(this);
/*  98 */   private VampireManager vampManager = new VampireManager();
/*  99 */   private PriestManager priestManager = new PriestManager(this);
/* 100 */   private WereManager wereManager = new WereManager();
/* 101 */   private GhoulManager ghoulManager = new GhoulManager();
/* 102 */   private HunterManager hunterManager = new HunterManager(this);
/* 103 */   private DemonManager demonManager = new DemonManager(this);
/* 104 */   private AngelManager angelManager = new AngelManager();
/*     */   
/* 106 */   public List<SNCommand> commands = new ArrayList();
/*     */   
/*     */   private static File dataFolder;
/*     */   
/* 110 */   public static boolean foundPerms = false;
/*     */   public PluginManager pm;
/*     */   
/*     */   public SNDataHandler getDataHandler()
/*     */   {
/* 115 */     return this.snData;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SuperNManager getSuperManager()
/*     */   {
/* 123 */     return this.superManager;
/*     */   }
/*     */   
/*     */   public SNConfigHandler getConfigManager() {
/* 127 */     return this.snConfig;
/*     */   }
/*     */   
/*     */   public SNLanguageHandler getLanguageManager() {
/* 131 */     return this.snLanguage;
/*     */   }
/*     */   
/*     */   public SNWhitelistHandler getWhitelistHandler() {
/* 135 */     return this.snWhitelist;
/*     */   }
/*     */   
/*     */   public VampireManager getVampireManager() {
/* 139 */     return this.vampManager;
/*     */   }
/*     */   
/*     */   public AngelManager getAngelManager() {
/* 143 */     return this.angelManager;
/*     */   }
/*     */   
/*     */   public PriestManager getPriestManager() {
/* 147 */     return this.priestManager;
/*     */   }
/*     */   
/*     */   public WereManager getWereManager() {
/* 151 */     return this.wereManager;
/*     */   }
/*     */   
/*     */   public GhoulManager getGhoulManager() {
/* 155 */     return this.ghoulManager;
/*     */   }
/*     */   
/*     */   public HunterManager getHunterManager() {
/* 159 */     return this.hunterManager;
/*     */   }
/*     */   
/*     */   public DemonManager getDemonManager() {
/* 163 */     return this.demonManager;
/*     */   }
/*     */   
/*     */   public ClassManager getClassManager(Player player) {
/* 167 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 168 */     if (snplayer.getType().equalsIgnoreCase("demon"))
/* 169 */       return this.demonManager;
/* 170 */     if (snplayer.getType().equalsIgnoreCase("ghoul"))
/* 171 */       return this.ghoulManager;
/* 172 */     if (snplayer.getType().equalsIgnoreCase("witchhunter"))
/* 173 */       return this.hunterManager;
/* 174 */     if (snplayer.getType().equalsIgnoreCase("priest"))
/* 175 */       return this.priestManager;
/* 176 */     if (snplayer.getType().equalsIgnoreCase("vampire"))
/* 177 */       return this.vampManager;
/* 178 */     if (snplayer.getType().equalsIgnoreCase("werewolf"))
/* 179 */       return this.wereManager;
/* 180 */     if (snplayer.getType().equalsIgnoreCase("angel")) {
/* 181 */       return this.angelManager;
/*     */     }
/* 183 */     return this.humanManager;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onDisable()
/*     */   {
/* 193 */     SuperNManager.cancelTimer();
/* 194 */     this.snData.write();
/*     */     
/* 196 */     saveData();
/* 197 */     this.demonManager.removeAllWebs();
/* 198 */     PluginDescriptionFile pdfFile = getDescription();
/* 199 */     log(pdfFile.getName() + " version " + pdfFile.getVersion() + " disabled.");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onEnable()
/*     */   {
/* 207 */     instance = this;
/* 208 */     getDataFolder().mkdir();
/* 209 */     this.pm = getServer().getPluginManager();
/*     */     
/*     */ 
/* 212 */     this.commands.add(new SNCommandHelp());
/* 213 */     this.commands.add(new SNCommandAdmin());
/* 214 */     this.commands.add(new SNCommandPower());
/* 215 */     this.commands.add(new SNCommandReload());
/* 216 */     this.commands.add(new SNCommandSave());
/* 217 */     this.commands.add(new SNCommandConvert());
/* 218 */     this.commands.add(new SNCommandCure());
/* 219 */     this.commands.add(new SNCommandList());
/* 220 */     this.commands.add(new SNCommandClasses());
/* 221 */     this.commands.add(new SNCommandSetChurch());
/* 222 */     this.commands.add(new SNCommandSetBanish());
/* 223 */     this.commands.add(new SNCommandReset());
/* 224 */     this.commands.add(new SNCommandKillList());
/* 225 */     this.commands.add(new SNCommandRmTarget());
/* 226 */     this.commands.add(new SNCommandRestartTask());
/* 227 */     this.commands.add(new SNCommandJoin());
/* 228 */     this.commands.add(new SNCommandSetup());
/*     */     
/* 230 */     this.pm.registerEvents(new SNBlockListener(this), this);
/* 231 */     this.pm.registerEvents(new SNEntityListener(this), this);
/* 232 */     this.pm.registerEvents(new SNEntityMonitor(this), this);
/* 233 */     this.pm.registerEvents(new SNPlayerListener(this), this);
/* 234 */     this.pm.registerEvents(new SNPlayerMonitor(this), this);
/* 235 */     this.pm.registerEvents(new SNServerMonitor(this), this);
/*     */     
/* 237 */     PluginDescriptionFile pdfFile = getDescription();
/* 238 */     log(pdfFile.getName() + " version " + pdfFile.getVersion() + " enabled.");
/*     */     
/*     */ 
/* 241 */     dataFolder = getDataFolder();
/* 242 */     SNConfigHandler.getConfiguration();
/* 243 */     SNLanguageHandler.getConfiguration();
/*     */     
/* 245 */     loadData();
/* 246 */     this.snData = SNDataHandler.read();
/*     */     
/* 248 */     SNWhitelistHandler.reloadWhitelist();
/*     */     
/* 250 */     if (this.snData == null) {
/* 251 */       this.snData = new SNDataHandler();
/*     */     }
/*     */     
/* 254 */     SuperNManager.startTimer();
/* 255 */     HunterManager.createBounties();
/* 256 */     setupPermissions();
/*     */     try {
/* 258 */       Metrics metrics = new Metrics(this);
/* 259 */       metrics.start();
/*     */     } catch (IOException e) {
/* 261 */       log("Couldn't start Metrics.");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
/*     */   {
/* 272 */     if ((sender instanceof Player)) {
/* 273 */       List<String> parameters = new ArrayList(Arrays.asList(args));
/* 274 */       handleCommand(sender, parameters, true);
/* 275 */       return true;
/*     */     }
/* 277 */     List<String> parameters = new ArrayList(Arrays.asList(args));
/* 278 */     handleCommand(sender, parameters, false);
/* 279 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void handleCommand(CommandSender sender, List<String> parameters, boolean isPlayer)
/*     */   {
/* 285 */     if (parameters.size() == 0) {
/* 286 */       for (SNCommand vampcommand : this.commands) {
/* 287 */         if (vampcommand.getName().equalsIgnoreCase("help")) {
/* 288 */           vampcommand.execute(sender, parameters);
/* 289 */           return;
/*     */         }
/*     */       }
/* 292 */       sender.sendMessage(Language.UNKNOW_COMMAND.toString());
/* 293 */       return;
/*     */     }
/*     */     
/* 296 */     String command = ((String)parameters.get(0)).toLowerCase();
/* 297 */     parameters.remove(0);
/*     */     
/* 299 */     for (SNCommand vampcommand : this.commands) {
/* 300 */       if (command.equals(vampcommand.getName())) {
/* 301 */         if ((!isPlayer) && (vampcommand.senderMustBePlayer)) {
/* 302 */           sender.sendMessage(Language.ONLY_IN_GAME_COMMAND.toString().replace(LanguageTag.CMD.toString(), command));
/*     */         }
/*     */         
/* 305 */         vampcommand.execute(sender, parameters);
/* 306 */         return;
/*     */       }
/*     */     }
/*     */     
/* 310 */     sender.sendMessage(Language.NO_SUCH_COMMAND.toString().replace(LanguageTag.CMD.toString(), command));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void saveAll()
/*     */   {
/* 319 */     File file = new File(dataFolder, "data.yml");
/* 320 */     SNPlayerHandler.save(SuperNManager.getSupernaturals(), file);
/*     */     
/* 322 */     SNConfigHandler.saveConfig();
/*     */   }
/*     */   
/*     */   public static void saveData() {
/* 326 */     File file = new File(dataFolder, "data.yml");
/* 327 */     SNPlayerHandler.save(SuperNManager.getSupernaturals(), file);
/*     */   }
/*     */   
/*     */   public static void loadData() {
/* 331 */     File file = new File(dataFolder, "data.yml");
/* 332 */     SuperNManager.setSupernaturals(SNPlayerHandler.load(file));
/*     */   }
/*     */   
/*     */   public static void reConfig() {
/* 336 */     SNConfigHandler.reloadConfig();
/* 337 */     SNLanguageHandler.reloadConfig();
/*     */   }
/*     */   
/*     */   public static void reloadData() {
/* 341 */     File file = new File(dataFolder, "data.yml");
/* 342 */     SuperNManager.setSupernaturals(SNPlayerHandler.load(file));
/*     */   }
/*     */   
/*     */   public static void restartTask() {
/* 346 */     SuperNManager.cancelTimer();
/* 347 */     SuperNManager.startTimer();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setupPermissions()
/*     */   {
/* 355 */     if (this.pm.isPluginEnabled("PermissionsEx")) {
/* 356 */       log("Found PermissionsEx!");
/* 357 */       foundPerms = true;
/* 358 */     } else if (this.pm.isPluginEnabled("PermissionsBukkit")) {
/* 359 */       log("Found PermissionsBukkit!");
/* 360 */       foundPerms = true;
/* 361 */     } else if (this.pm.isPluginEnabled("bPermissions")) {
/* 362 */       log("Found bPermissions.");
/* 363 */       log(Level.WARNING, "If something goes wrong with bPermissions and this plugin, I will not help!");
/*     */       
/* 365 */       foundPerms = true;
/* 366 */     } else if (this.pm.isPluginEnabled("GroupManager")) {
/* 367 */       log("Found GroupManager.");
/* 368 */       foundPerms = true;
/*     */     }
/*     */     
/* 371 */     if (!foundPerms) {
/* 372 */       log("Permission system not detected, defaulting to SuperPerms");
/* 373 */       log("A permissions system may be detected later, just wait.");
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean hasPermissions(Player player, String permissions) {
/* 378 */     return player.hasPermission(permissions);
/*     */   }
/*     */   
/*     */   public static boolean hasPermissions(SuperNPlayer player, String permissions) {
/* 382 */     return instance.getServer().getPlayer(player.getName()).hasPermission(permissions);
/*     */   }
/*     */   
/*     */   private WorldGuardPlugin getWorldGuard()
/*     */   {
/* 387 */     Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
/*     */     
/*     */ 
/* 390 */     if ((plugin == null) || (!(plugin instanceof WorldGuardPlugin))) {
/* 391 */       return null;
/*     */     }
/*     */     
/* 394 */     return (WorldGuardPlugin)plugin;
/*     */   }
/*     */   
/*     */   public boolean getPvP(Player player) {
/* 398 */     WorldGuardPlugin worldGuard = instance.getWorldGuard();
/*     */     
/* 400 */     if (worldGuard == null) {
/* 401 */       return true;
/*     */     }
/* 403 */     Vector pt = BukkitUtil.toVector(player.getLocation());
/* 404 */     RegionManager regionManager = worldGuard.getRegionManager(player.getWorld());
/*     */     
/* 406 */     ApplicableRegionSet set = regionManager.getApplicableRegions(pt);
/* 407 */     return set.allows(DefaultFlag.PVP);
/*     */   }
/*     */   
/*     */   public boolean getSpawn(Player player) {
/* 411 */     WorldGuardPlugin worldGuard = instance.getWorldGuard();
/*     */     
/* 413 */     if (worldGuard == null) {
/* 414 */       return true;
/*     */     }
/* 416 */     Vector pt = BukkitUtil.toVector(player.getLocation());
/* 417 */     RegionManager regionManager = worldGuard.getRegionManager(player.getWorld());
/*     */     
/* 419 */     ApplicableRegionSet set = regionManager.getApplicableRegions(pt);
/* 420 */     return set.allows(DefaultFlag.MOB_SPAWNING);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void log(String msg)
/*     */   {
/* 428 */     log(Level.INFO, msg);
/*     */   }
/*     */   
/*     */   public static void log(Level level, String msg) {
/* 432 */     Logger.getLogger("Minecraft").log(level, "[" + instance.getDescription().getFullName() + "] " + msg);
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\SupernaturalsPlugin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */