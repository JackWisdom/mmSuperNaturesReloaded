/*     */ package com.mmiillkkaa.supernaturals;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.Proxy;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.configuration.InvalidConfigurationException;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfigurationOptions;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.PluginDescriptionFile;
/*     */ import org.bukkit.scheduler.BukkitScheduler;
/*     */ import org.bukkit.scheduler.BukkitTask;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Metrics
/*     */ {
/*     */   private static final int REVISION = 7;
/*     */   private static final String BASE_URL = "http://report.mcstats.org";
/*     */   private static final String REPORT_URL = "/plugin/%s";
/*     */   private static final int PING_INTERVAL = 15;
/*     */   private final Plugin plugin;
/*  87 */   private final Set<Graph> graphs = Collections.synchronizedSet(new HashSet());
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final YamlConfiguration configuration;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private final File configurationFile;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private final String guid;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private final boolean debug;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 113 */   private final Object optOutLock = new Object();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 118 */   private volatile BukkitTask task = null;
/*     */   
/*     */   public Metrics(Plugin plugin) throws IOException {
/* 121 */     if (plugin == null) {
/* 122 */       throw new IllegalArgumentException("Plugin cannot be null");
/*     */     }
/*     */     
/* 125 */     this.plugin = plugin;
/*     */     
/*     */ 
/* 128 */     this.configurationFile = getConfigFile();
/* 129 */     this.configuration = YamlConfiguration.loadConfiguration(this.configurationFile);
/*     */     
/*     */ 
/* 132 */     this.configuration.addDefault("opt-out", Boolean.valueOf(false));
/* 133 */     this.configuration.addDefault("guid", UUID.randomUUID().toString());
/* 134 */     this.configuration.addDefault("debug", Boolean.valueOf(false));
/*     */     
/*     */ 
/* 137 */     if (this.configuration.get("guid", null) == null) {
/* 138 */       this.configuration.options().header("http://mcstats.org").copyDefaults(true);
/*     */       
/* 140 */       this.configuration.save(this.configurationFile);
/*     */     }
/*     */     
/*     */ 
/* 144 */     this.guid = this.configuration.getString("guid");
/* 145 */     this.debug = this.configuration.getBoolean("debug", false);
/*     */   }
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
/*     */   public Graph createGraph(String name)
/*     */   {
/* 159 */     if (name == null) {
/* 160 */       throw new IllegalArgumentException("Graph name cannot be null");
/*     */     }
/*     */     
/*     */ 
/* 164 */     Graph graph = new Graph(name);
/*     */     
/*     */ 
/* 167 */     this.graphs.add(graph);
/*     */     
/*     */ 
/* 170 */     return graph;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addGraph(Graph graph)
/*     */   {
/* 181 */     if (graph == null) {
/* 182 */       throw new IllegalArgumentException("Graph cannot be null");
/*     */     }
/*     */     
/* 185 */     this.graphs.add(graph);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean start()
/*     */   {
/* 197 */     synchronized (this.optOutLock)
/*     */     {
/* 199 */       if (isOptOut()) {
/* 200 */         return false;
/*     */       }
/*     */       
/*     */ 
/* 204 */       if (this.task != null) {
/* 205 */         return true;
/*     */       }
/*     */       
/*     */ 
/* 209 */       this.task = this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(this.plugin, new Runnable()
/*     */       {
/*     */ 
/* 212 */         private boolean firstPost = true;
/*     */         
/*     */         public void run()
/*     */         {
/*     */           try
/*     */           {
/* 218 */             synchronized (Metrics.this.optOutLock)
/*     */             {
/*     */ 
/* 221 */               if ((Metrics.this.isOptOut()) && (Metrics.this.task != null)) {
/* 222 */                 Metrics.this.task.cancel();
/* 223 */                 Metrics.this.task = null;
/*     */                 
/*     */ 
/* 226 */                 for (Metrics.Graph graph : Metrics.this.graphs) {
/* 227 */                   graph.onOptOut();
/*     */                 }
/*     */               }
/*     */             }
/*     */             
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 238 */             Metrics.this.postPlugin(!this.firstPost);
/*     */             
/*     */ 
/*     */ 
/*     */ 
/* 243 */             this.firstPost = false;
/*     */           } catch (IOException e) {
/* 245 */             if (Metrics.this.debug)
/* 246 */               Bukkit.getLogger().log(Level.INFO, "[Metrics] " + e.getMessage()); } } }, 0L, 18000L);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 253 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isOptOut()
/*     */   {
/* 263 */     synchronized (this.optOutLock)
/*     */     {
/*     */       try {
/* 266 */         this.configuration.load(getConfigFile());
/*     */       } catch (IOException ex) {
/* 268 */         if (this.debug) {
/* 269 */           Bukkit.getLogger().log(Level.INFO, "[Metrics] " + ex.getMessage());
/*     */         }
/*     */         
/* 272 */         return true;
/*     */       } catch (InvalidConfigurationException ex) {
/* 274 */         if (this.debug) {
/* 275 */           Bukkit.getLogger().log(Level.INFO, "[Metrics] " + ex.getMessage());
/*     */         }
/*     */         
/* 278 */         return true;
/*     */       }
/* 280 */       return this.configuration.getBoolean("opt-out", false);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void enable()
/*     */     throws IOException
/*     */   {
/* 293 */     synchronized (this.optOutLock)
/*     */     {
/*     */ 
/* 296 */       if (isOptOut()) {
/* 297 */         this.configuration.set("opt-out", Boolean.valueOf(false));
/* 298 */         this.configuration.save(this.configurationFile);
/*     */       }
/*     */       
/*     */ 
/* 302 */       if (this.task == null) {
/* 303 */         start();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void disable()
/*     */     throws IOException
/*     */   {
/* 317 */     synchronized (this.optOutLock)
/*     */     {
/*     */ 
/* 320 */       if (!isOptOut()) {
/* 321 */         this.configuration.set("opt-out", Boolean.valueOf(true));
/* 322 */         this.configuration.save(this.configurationFile);
/*     */       }
/*     */       
/*     */ 
/* 326 */       if (this.task != null) {
/* 327 */         this.task.cancel();
/* 328 */         this.task = null;
/*     */       }
/*     */     }
/*     */   }
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
/*     */   public File getConfigFile()
/*     */   {
/* 346 */     File pluginsFolder = this.plugin.getDataFolder().getParentFile();
/*     */     
/*     */ 
/* 349 */     return new File(new File(pluginsFolder, "PluginMetrics"), "config.yml");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void postPlugin(boolean isPing)
/*     */     throws IOException
/*     */   {
/* 357 */     PluginDescriptionFile description = this.plugin.getDescription();
/* 358 */     String pluginName = description.getName();
/* 359 */     boolean onlineMode = Bukkit.getServer().getOnlineMode();
/*     */     
/*     */ 
/*     */ 
/* 363 */     String pluginVersion = description.getVersion();
/* 364 */     String serverVersion = Bukkit.getVersion();
/* 365 */     int playersOnline = Bukkit.getServer().getOnlinePlayers().size();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 371 */     StringBuilder json = new StringBuilder(1024);
/* 372 */     json.append('{');
/*     */     
/*     */ 
/*     */ 
/* 376 */     appendJSONPair(json, "guid", this.guid);
/* 377 */     appendJSONPair(json, "plugin_version", pluginVersion);
/* 378 */     appendJSONPair(json, "server_version", serverVersion);
/* 379 */     appendJSONPair(json, "players_online", Integer.toString(playersOnline));
/*     */     
/*     */ 
/* 382 */     String osname = System.getProperty("os.name");
/* 383 */     String osarch = System.getProperty("os.arch");
/* 384 */     String osversion = System.getProperty("os.version");
/* 385 */     String java_version = System.getProperty("java.version");
/* 386 */     int coreCount = Runtime.getRuntime().availableProcessors();
/*     */     
/*     */ 
/* 389 */     if (osarch.equals("amd64")) {
/* 390 */       osarch = "x86_64";
/*     */     }
/*     */     
/* 393 */     appendJSONPair(json, "osname", osname);
/* 394 */     appendJSONPair(json, "osarch", osarch);
/* 395 */     appendJSONPair(json, "osversion", osversion);
/* 396 */     appendJSONPair(json, "cores", Integer.toString(coreCount));
/* 397 */     appendJSONPair(json, "auth_mode", onlineMode ? "1" : "0");
/* 398 */     appendJSONPair(json, "java_version", java_version);
/*     */     
/*     */ 
/* 401 */     if (isPing) {
/* 402 */       appendJSONPair(json, "ping", "1");
/*     */     }
/*     */     
/* 405 */     if (this.graphs.size() > 0) {
/* 406 */       synchronized (this.graphs) {
/* 407 */         json.append(',');
/* 408 */         json.append('"');
/* 409 */         json.append("graphs");
/* 410 */         json.append('"');
/* 411 */         json.append(':');
/* 412 */         json.append('{');
/*     */         
/* 414 */         boolean firstGraph = true;
/*     */         
/* 416 */         Iterator<Graph> iter = this.graphs.iterator();
/*     */         
/* 418 */         while (iter.hasNext()) {
/* 419 */           Graph graph = (Graph)iter.next();
/*     */           
/* 421 */           StringBuilder graphJson = new StringBuilder();
/* 422 */           graphJson.append('{');
/*     */           
/* 424 */           for (Plotter plotter : graph.getPlotters()) {
/* 425 */             appendJSONPair(graphJson, plotter.getColumnName(), Integer.toString(plotter.getValue()));
/*     */           }
/*     */           
/*     */ 
/* 429 */           graphJson.append('}');
/*     */           
/* 431 */           if (!firstGraph) {
/* 432 */             json.append(',');
/*     */           }
/*     */           
/* 435 */           json.append(escapeJSON(graph.getName()));
/* 436 */           json.append(':');
/* 437 */           json.append(graphJson);
/*     */           
/* 439 */           firstGraph = false;
/*     */         }
/*     */         
/* 442 */         json.append('}');
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 447 */     json.append('}');
/*     */     
/*     */ 
/* 450 */     URL url = new URL("http://report.mcstats.org" + String.format("/plugin/%s", new Object[] { urlEncode(pluginName) }));
/*     */     
/*     */ 
/*     */     URLConnection connection;

/*     */     
/* 458 */     if (isMineshafterPresent()) {
/* 459 */       connection = url.openConnection(Proxy.NO_PROXY);
/*     */     } else {
/* 461 */       connection = url.openConnection();
/*     */     }
/*     */     
/* 464 */     byte[] uncompressed = json.toString().getBytes();
/* 465 */     byte[] compressed = gzip(json.toString());
/*     */     
/*     */ 
/* 468 */     connection.addRequestProperty("User-Agent", "MCStats/7");
/* 469 */     connection.addRequestProperty("Content-Type", "application/json");
/* 470 */     connection.addRequestProperty("Content-Encoding", "gzip");
/* 471 */     connection.addRequestProperty("Content-Length", Integer.toString(compressed.length));
/*     */     
/* 473 */     connection.addRequestProperty("Accept", "application/json");
/* 474 */     connection.addRequestProperty("Connection", "close");
/*     */     
/* 476 */     connection.setDoOutput(true);
/*     */     
/* 478 */     if (this.debug) {
/* 479 */       System.out.println("[Metrics] Prepared request for " + pluginName + " uncompressed=" + uncompressed.length + " compressed=" + compressed.length);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 485 */     OutputStream os = connection.getOutputStream();
/* 486 */     os.write(compressed);
/* 487 */     os.flush();
/*     */     
/*     */ 
/* 490 */     BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/*     */     
/* 492 */     String response = reader.readLine();
/*     */     
/*     */ 
/* 495 */     os.close();
/* 496 */     reader.close();
/*     */     
/* 498 */     if ((response == null) || (response.startsWith("ERR")) || (response.startsWith("7")))
/*     */     {
/* 500 */       if (response == null) {
/* 501 */         response = "null";
/* 502 */       } else if (response.startsWith("7")) {
/* 503 */         response = response.substring(response.startsWith("7,") ? 2 : 1);
/*     */       }
/*     */       
/*     */ 
/* 507 */       throw new IOException(response);
/*     */     }
/*     */     
/* 510 */     if ((response.equals("1")) || (response.contains("This is your first update this hour")))
/*     */     {
/* 512 */       synchronized (this.graphs) {
/* 513 */         Iterator<Graph> iter = this.graphs.iterator();
/*     */         
/* 515 */         while (iter.hasNext()) {
/* 516 */           Graph graph = (Graph)iter.next();
/*     */           
/* 518 */           for (Plotter plotter : graph.getPlotters()) {
/* 519 */             plotter.reset();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static byte[] gzip(String input)
/*     */   {
/* 534 */
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 535 */     GZIPOutputStream gzos = null;
/*     */     try
/*     */     {
/* 538 */       gzos = new GZIPOutputStream(baos);
/* 539 */       gzos.write(input.getBytes("UTF-8"));
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
/* 550 */       return baos.toByteArray();
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 541 */       e.printStackTrace();
/*     */     } finally {
/* 543 */       if (gzos != null) {
/*     */         try {
/* 545 */           gzos.close();
/*     */         }
/*     */         catch (IOException ignore) {}
/*     */       }
/*     */     }
            return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean isMineshafterPresent()
/*     */   {
/*     */     try
/*     */     {
/* 561 */       Class.forName("mineshafter.MineServer");
/* 562 */       return true;
/*     */     } catch (Exception e) {}
/* 564 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void appendJSONPair(StringBuilder json, String key, String value)
/*     */     throws UnsupportedEncodingException
/*     */   {
/* 578 */     boolean isValueNumeric = false;
/*     */     try
/*     */     {
/* 581 */       if ((value.equals("0")) || (!value.endsWith("0"))) {
/* 582 */         Double.parseDouble(value);
/* 583 */         isValueNumeric = true;
/*     */       }
/*     */     } catch (NumberFormatException e) {
/* 586 */       isValueNumeric = false;
/*     */     }
/*     */     
/* 589 */     if (json.charAt(json.length() - 1) != '{') {
/* 590 */       json.append(',');
/*     */     }
/*     */     
/* 593 */     json.append(escapeJSON(key));
/* 594 */     json.append(':');
/*     */     
/* 596 */     if (isValueNumeric) {
/* 597 */       json.append(value);
/*     */     } else {
/* 599 */       json.append(escapeJSON(value));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static String escapeJSON(String text)
/*     */   {
/* 610 */     StringBuilder builder = new StringBuilder();
/*     */     
/* 612 */     builder.append('"');
/* 613 */     for (int index = 0; index < text.length(); index++) {
/* 614 */       char chr = text.charAt(index);
/*     */       
/* 616 */       switch (chr) {
/*     */       case '"': 
/*     */       case '\\': 
/* 619 */         builder.append('\\');
/* 620 */         builder.append(chr);
/* 621 */         break;
/*     */       case '\b': 
/* 623 */         builder.append("\\b");
/* 624 */         break;
/*     */       case '\t': 
/* 626 */         builder.append("\\t");
/* 627 */         break;
/*     */       case '\n': 
/* 629 */         builder.append("\\n");
/* 630 */         break;
/*     */       case '\r': 
/* 632 */         builder.append("\\r");
/* 633 */         break;
/*     */       default: 
/* 635 */         if (chr < ' ') {
/* 636 */           String t = "000" + Integer.toHexString(chr);
/* 637 */           builder.append("\\u" + t.substring(t.length() - 4));
/*     */         } else {
/* 639 */           builder.append(chr);
/*     */         }
/*     */         break;
/*     */       }
/*     */     }
/* 644 */     builder.append('"');
/*     */     
/* 646 */     return builder.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static String urlEncode(String text)
/*     */     throws UnsupportedEncodingException
/*     */   {
/* 658 */     return URLEncoder.encode(text, "UTF-8");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class Graph
/*     */   {
/*     */     private final String name;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 675 */     private final Set<Metrics.Plotter> plotters = new LinkedHashSet();
/*     */     
/*     */     private Graph(String name) {
/* 678 */       this.name = name;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public String getName()
/*     */     {
/* 687 */       return this.name;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void addPlotter(Metrics.Plotter plotter)
/*     */     {
/* 697 */       this.plotters.add(plotter);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void removePlotter(Metrics.Plotter plotter)
/*     */     {
/* 707 */       this.plotters.remove(plotter);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Set<Metrics.Plotter> getPlotters()
/*     */     {
/* 716 */       return Collections.unmodifiableSet(this.plotters);
/*     */     }
/*     */     
/*     */     public int hashCode()
/*     */     {
/* 721 */       return this.name.hashCode();
/*     */     }
/*     */     
/*     */     public boolean equals(Object object)
/*     */     {
/* 726 */       if (!(object instanceof Graph)) {
/* 727 */         return false;
/*     */       }
/*     */       
/* 730 */       Graph graph = (Graph)object;
/* 731 */       return graph.name.equals(this.name);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected void onOptOut() {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static abstract class Plotter
/*     */   {
/*     */     private final String name;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Plotter()
/*     */     {
/* 756 */       this("Default");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Plotter(String name)
/*     */     {
/* 767 */       this.name = name;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public abstract int getValue();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public String getColumnName()
/*     */     {
/* 787 */       return this.name;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void reset() {}
/*     */     
/*     */ 
/*     */ 
/*     */     public int hashCode()
/*     */     {
/* 798 */       return getColumnName().hashCode();
/*     */     }
/*     */     
/*     */     public boolean equals(Object object)
/*     */     {
/* 803 */       if (!(object instanceof Plotter)) {
/* 804 */         return false;
/*     */       }
/*     */       
/* 807 */       Plotter plotter = (Plotter)object;
/* 808 */       return (plotter.name.equals(this.name)) && (plotter.getValue() == getValue());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\Metrics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */