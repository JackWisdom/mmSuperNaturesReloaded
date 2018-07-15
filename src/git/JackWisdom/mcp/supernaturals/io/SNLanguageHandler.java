/*     */ package git.JackWisdom.mcp.supernaturals.io;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*     */ import java.io.File;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.StandardCopyOption;
/*     */
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.logging.Level;
/*     */
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
/*     */ 
/*     */ public class SNLanguageHandler
/*     */ {
/*     */   public static SupernaturalsPlugin plugin;
/*     */   public static YamlConfiguration config;
/*     */   public static String configName;
/*     */   public static File configDir;
/*     */   public static File configFile;
/*     */   public static String language;
/*     */   public static String defaultLanguage;
/*     */   public static ArrayList<String> languageFiles;
/*     */   
/*     */   public SNLanguageHandler(SupernaturalsPlugin instance)
/*     */   {

/*  27 */     plugin = instance;
/*  29 */     defaultLanguage = "en";
/*  30 */     languageFiles = new ArrayList(Arrays.asList(new String[] { defaultLanguage, "zh" }));
                language=defaultLanguage;
/*     */   }
/*     */   public static void getConfiguration()
/*     */   {
/*  36 */     configDir=new File(plugin.getDataFolder(),"language");
/*     */     try
/*     */     {
/*  39 */       configDir.mkdir();
/*     */     } catch (Exception e) {
/*  41 */       SupernaturalsPlugin.log(Level.WARNING, String.format("Can't create the directory for language files!!",e));
/*     */     }
/*     */     
/*     */ 
/*     */
        //create lang files
     for (String lang : languageFiles) {
         SupernaturalsPlugin.log("loading lang file"+lang);
       File file = getLanguageFile(lang);
        if (lang != defaultLanguage) {
          try {
            if (!file.exists()) {
                String s=String.format("%s/%s.yml", new Object[] { "language", lang });
              plugin.saveResource(s ,true);
                SupernaturalsPlugin.log("savingResource "+s);
            }
          }
          catch (Exception e)
         {
           SupernaturalsPlugin.log(Level.WARNING, String.format("Can not create the language file: %s.yml - %s!!", new Object[] { lang, e }));
          }
      }
/*  61 */       config = loadValues(file);
/*  62 */       saveConfig(config, file);
                //初始化語言文件
/*     */     }
/*     */     
/*     */ 
/*  66 */     language = SNConfigHandler.language;
/*  67 */     SupernaturalsPlugin.log(Level.INFO, String.format("Use language file: %s.yml", new Object[] { language }));
/*     */     
/*  69 */     File file = getLanguageFile(SNConfigHandler.language);
/*  70 */     if (file.exists()) {
/*  71 */       config = loadValues(file);
/*     */     }
/*     */     else {
/*  74 */       SupernaturalsPlugin.log(Level.INFO, String.format("Fail to loading Language file: %s.yml, use default!", new Object[] { language }));
/*     */       
/*     */ 
/*  77 */       config = loadDefaults();
/*     */     }
                setUpLang();

/*     */   }
            private static void setUpLang(){
            for(Language l:Language.values()){

                l.setDef(config.getString(l.name().toLowerCase()));

            }
            plugin.getLogger().info("successfully apply language "+language);
            }
/*     */   
/*     */   public static File getLanguageFile(String lang) {
/*     */     File file=new File(configDir,lang+".yml");
/*  84 */     return file;
/*     */   }
/*     */   
/*     */   public static YamlConfiguration loadValues(File file) {
/*  88 */     config = YamlConfiguration.loadConfiguration(file);
/*  92 */     return config;
/*     */   }

/*     */
/*     */   public static YamlConfiguration loadDefaults() {
/* 103 */     config = new YamlConfiguration();
/* 104 */     for (Language l : Language.values()) {
/* 105 */       config.set(l.getPath(), config.getString(l.getPath(), l.getDef()));
/*     */     }
/* 107 */     return config;
/*     */   }
/*     */   
/*     */   public static void saveConfig(YamlConfiguration config, File file)
/*     */   {
/*     */     try {
/* 113 */       if (file.exists()) {
/* 114 */         File bakFile = new File(file.getAbsolutePath() + ".bak");
/* 115 */         Files.copy(file.toPath(), bakFile.toPath(), new java.nio.file.CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/*     */       }
/*     */       
/* 118 */       config.save(file);
/*     */     } catch (Exception e) {
/* 120 */       SupernaturalsPlugin.log(Level.WARNING, String.format("Language file writing error - %s", new Object[] { e }));
/*     */     }
/*     */   }
/*     */   
  public static void reloadConfig()
   {
     File file = getLanguageFile(language);
    config = loadValues(file);
            setUpLang();
/*     */   }
/*     */   
/*     */   public static YamlConfiguration getConfig() {
/* 131 */     return config;
/*     */   }
/*     */ }


