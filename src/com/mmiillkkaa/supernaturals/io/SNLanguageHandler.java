/*     */ package com.mmiillkkaa.supernaturals.io;
/*     */ 
/*     */ import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
/*     */ import com.mmiillkkaa.supernaturals.util.Language;
/*     */ import java.io.File;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.StandardCopyOption;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.logging.Level;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ 
/*     */ public class SNLanguageHandler
/*     */ {
/*     */   public static SupernaturalsPlugin plugin;
/*     */   public static YamlConfiguration config;
/*     */   public static String configName;
/*     */   public static String configDir;
/*     */   public static File configFile;
/*     */   public static String language;
/*     */   public static String defaultLanguage;
/*     */   public static ArrayList<String> languageFiles;
/*     */   
/*     */   public SNLanguageHandler(SupernaturalsPlugin instance)
/*     */   {
/*  27 */     plugin = instance;
/*  28 */     configDir = "language";
/*  29 */     defaultLanguage = "en";
/*  30 */     languageFiles = new ArrayList(Arrays.asList(new String[] { defaultLanguage, "zh_TW" }));
/*     */   }
/*     */   
/*     */ 
/*     */   public static void getConfiguration()
/*     */   {
/*  36 */     File dir = new File(String.format("%s/%s/", new Object[] { plugin.getDataFolder().getAbsolutePath(), configDir }));
/*     */     try
/*     */     {
/*  39 */       Files.createDirectory(dir.toPath(), new FileAttribute[0]);
/*     */     } catch (Exception e) {
/*  41 */       SupernaturalsPlugin.log(Level.WARNING, String.format("Can't create the directory for language files!!", new Object[] { e }));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  46 */     for (String lang : languageFiles) {
/*  47 */       File file = getLanguageFile(lang);
/*  48 */       if (lang != defaultLanguage) {
/*     */         try {
/*  50 */           if (!file.exists()) {
/*  51 */             plugin.saveResource(String.format("%s/%s.yml", new Object[] { configDir, lang }), true);
/*     */           }
/*     */         }
/*     */         catch (Exception e)
/*     */         {
/*  56 */           SupernaturalsPlugin.log(Level.WARNING, String.format("Can not create the language file: %s.yml - %s!!", new Object[] { lang, e }));
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*  61 */       config = loadValues(file);
/*  62 */       saveConfig(config, file);
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
/*  77 */       config = loadValues();
/*     */     }
/*     */   }
/*     */   
/*     */   public static File getLanguageFile(String lang) {
/*  82 */     File file = new File(String.format("%s/%s/%s.yml", new Object[] { plugin.getDataFolder().getAbsolutePath(), configDir, lang }));
/*     */     
/*  84 */     return file;
/*     */   }
/*     */   
/*     */   public static YamlConfiguration loadValues(File file) {
/*  88 */     config = YamlConfiguration.loadConfiguration(file);
/*  89 */     for (Language l : Language.values()) {
/*  90 */       config.set(l.getPath(), config.getString(l.getPath(), l.getDef()));
/*     */     }
/*  92 */     return config;
/*     */   }
/*     */   
/*     */   public static YamlConfiguration loadValues(YamlConfiguration config) {
/*  96 */     for (Language l : Language.values()) {
/*  97 */       config.set(l.getPath(), config.getString(l.getPath(), l.getDef()));
/*     */     }
/*  99 */     return config;
/*     */   }
/*     */   
/*     */   public static YamlConfiguration loadValues() {
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
/*     */   public static void reloadConfig()
/*     */   {
/* 126 */     File file = getLanguageFile(language);
/* 127 */     config = loadValues(file);
/*     */   }
/*     */   
/*     */   public static YamlConfiguration getConfig() {
/* 131 */     return config;
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\io\SNLanguageHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */