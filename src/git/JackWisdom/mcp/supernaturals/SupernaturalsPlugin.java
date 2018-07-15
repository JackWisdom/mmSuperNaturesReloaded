/*     */ package git.JackWisdom.mcp.supernaturals;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommand;
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommandAdmin;
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommandClasses;
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommandConvert;
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommandCure;
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommandHelp;
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommandJoin;
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommandKillList;
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommandList;
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommandPower;
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommandReload;
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommandReset;
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommandRestartTask;
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommandRmTarget;
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommandSave;
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommandSetBanish;
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommandSetChurch;
/*     */ import git.JackWisdom.mcp.supernaturals.commands.SNCommandSetup;
/*     */ import git.JackWisdom.mcp.supernaturals.hooks.PAPIHook;
import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
/*     */ import git.JackWisdom.mcp.supernaturals.recipes.RecipeManager;
import git.JackWisdom.mcp.supernaturals.storage.FileDataHandler;
import git.JackWisdom.mcp.supernaturals.storage.MySqlDataHandler;
import git.JackWisdom.mcp.supernaturals.storage.SNDataHandler;
/*     */ import git.JackWisdom.mcp.supernaturals.io.SNLanguageHandler;
/*     */
/*     */ import git.JackWisdom.mcp.supernaturals.io.SNWhitelistHandler;
/*     */ import git.JackWisdom.mcp.supernaturals.listeners.*;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ import git.JackWisdom.mcp.supernaturals.manager.AngelManager;
/*     */
/*     */ import git.JackWisdom.mcp.supernaturals.manager.DemonManager;
/*     */ import git.JackWisdom.mcp.supernaturals.manager.GhoulManager;
/*     */ import git.JackWisdom.mcp.supernaturals.manager.HumanManager;
/*     */ import git.JackWisdom.mcp.supernaturals.manager.HunterManager;
/*     */ import git.JackWisdom.mcp.supernaturals.manager.PriestManager;
/*     */ import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
/*     */ import git.JackWisdom.mcp.supernaturals.manager.VampireManager;
/*     */ import git.JackWisdom.mcp.supernaturals.manager.WereManager;
/*     */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*     */ import git.JackWisdom.mcp.supernaturals.util.LanguageTag;

/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.sql.SQLException;
import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */
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
/*     */   extends JavaPlugin implements UsingData
/*     */ {
/*     */   public static SupernaturalsPlugin instance;
/*  91 */   private final SNConfigHandler snConfig=new SNConfigHandler(this);
/*  92 */   private final SNLanguageHandler  snLanguage = new SNLanguageHandler(this);
/*  93 */   private SNDataHandler snData;
/*  94 */   private SNWhitelistHandler snWhitelist;
/*     */   
/*  96 */   private SuperNManager  superManager;
/*  97 */   private HumanManager  humanManager;
/*  98 */   private VampireManager vampManager;
/*  99 */   private PriestManager priestManager;
            private RecipeManager recipeManager;
/* 100 */   private WereManager wereManager;
/* 101 */   private GhoulManager  ghoulManager;
/* 102 */   private HunterManager hunterManager;
/* 103 */   private DemonManager demonManager;
/* 104 */   private AngelManager angelManager;
/*     */   
/* 106 */   public List<SNCommand> commands;
/*     */   public static PAPIHook papiHook;
/*     */   private static File dataFolder;
              public PluginManager pm;

    public static void saveAll() {
        for(SuperNPlayer sn:superpowers.values()){
            instance.getDataHandler().save(sn);
        };
    }

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
            public HumanManager getHumanManager() {
        /* 155 */     return this.humanManager;
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

/*     */   public RecipeManager getRecipeManager(){
        return this.recipeManager;
}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onDisable()
/*     */   {
/* 193 */     SuperNManager.cancelTimer();
/* 197 */     this.demonManager.removeAllWebs();
/* 198 */     PluginDescriptionFile pdfFile = getDescription();
                saveAll();
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
    commands = new ArrayList();

    demonManager = new DemonManager(this);
    hunterManager = new HunterManager(this);
    angelManager = new AngelManager();
    snWhitelist = new SNWhitelistHandler(this);
    superManager = new SuperNManager(this);
    humanManager = new HumanManager(this);
    vampManager = new VampireManager();
    wereManager = new WereManager();
    ghoulManager = new GhoulManager();
    priestManager = new PriestManager(this);

/* 209 */     this.pm = getServer().getPluginManager();
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
              this.pm.registerEvents(new PlayerGather(),this);
              this.pm.registerEvents(new InventoryListener(),this);
/* 237 */     PluginDescriptionFile pdfFile = getDescription();
/* 238 */     log(pdfFile.getName() + " version " + pdfFile.getVersion() + " enabled.");
/*     */     
/*     */ 
/* 241 */     dataFolder = getDataFolder();
/* 242 */     SNConfigHandler.getConfiguration();
/* 243 */     SNLanguageHandler.getConfiguration();
/* 248 */     SNWhitelistHandler.reloadWhitelist();
                //recipe must load after config
              recipeManager=new RecipeManager();

            //using mysql or not
            if(SNConfigHandler.useSql){
                try {
                    snData=new MySqlDataHandler();
                } catch (SQLException e) {
                    snData=null;

                    e.printStackTrace();
                }

            }
            if (this.snData == null) {
            this.snData = new FileDataHandler();
                log("Using File to storage data");
            }else {
                log("Using MySql to storage data");
            }
/*     */     
/* 254 */     SuperNManager.startTimer();
/* 255 */     HunterManager.createBounties();
                hook();
/*     */     try {
/* 258 */       Metrics metrics = new Metrics(this);
/* 259 */       metrics.start();
/*     */     } catch (IOException e) {
/* 261 */       log("Couldn't start Metrics.");
/*     */     }
           }
            private void hook(){
            if(pm.isPluginEnabled("PlaceHolderAPI")){
                papiHook =new PAPIHook();
                papiHook.hook();
                log("Hooked with PAPI");
            }
            }
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

/*     */   public static void reConfig() {
/* 336 */     SNConfigHandler.reloadConfig();
/* 337 */     SNLanguageHandler.reloadConfig();
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


/*     */   


/*     */   public static void log(String msg)
/*     */   {
/* 428 */     log(Level.INFO, msg);
/*     */   }
/*     */   
/*     */   public static void log(Level level, String msg) {
/* 432 */     instance.getLogger().log(level, msg);
/*     */   }
/*     */ }
