/*     */ package com.mmiillkkaa.supernaturals.io;
/*     */ 
/*     */ import com.mmiillkkaa.supernaturals.SuperNPlayer;
/*     */ import com.mmiillkkaa.supernaturals.SupernaturalsPlugin;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.logging.Level;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.entity.Player;
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
/*     */ public class SNDataHandler
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2266551481298554973L;
/*  42 */   private HashMap<SuperNPlayer, com.mmiillkkaa.supernaturals.util.Location> teleportLocations = new HashMap();
/*  43 */   private HashMap<SuperNPlayer, SuperNPlayer> angels = new HashMap();
/*  44 */   private HashMap<SuperNPlayer, ArrayList<String>> hunterApps = new HashMap();
/*     */   
/*  46 */   private static String path = "plugins/mmSupernaturals/storage.dat";
/*     */   
/*     */ 
/*     */ 
/*     */   public void write()
/*     */   {
/*     */     try
/*     */     {
/*  54 */       ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
/*     */       
/*  56 */       oos.writeObject(this);
/*  57 */       oos.flush();
/*  58 */       oos.close();
/*     */     } catch (Exception e) {
/*  60 */       SupernaturalsPlugin.log(Level.WARNING, "Storage Data could not be written!");
/*     */       
/*  62 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static SNDataHandler read() {
/*  67 */     SNDataHandler handler = null;
/*     */     try {
/*  69 */       ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
/*     */       
/*  71 */       handler = (SNDataHandler)ois.readObject();
/*  72 */       ois.close();
/*     */     } catch (Exception e) {
/*  74 */       SupernaturalsPlugin.log(Level.WARNING, "Storage Data not found.");
/*     */     }
/*  76 */     return handler;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addTeleport(SuperNPlayer player)
/*     */   {
/*  84 */     this.teleportLocations.put(player, new com.mmiillkkaa.supernaturals.util.Location(SupernaturalsPlugin.instance.getServer().getPlayer(player.getName()).getLocation()));
/*     */   }
/*     */   
/*     */   public boolean checkPlayer(SuperNPlayer player)
/*     */   {
/*  89 */     if (this.teleportLocations.containsKey(player)) {
/*  90 */       return true;
/*     */     }
/*  92 */     return false;
/*     */   }
/*     */   
/*     */   public org.bukkit.Location getTeleport(SuperNPlayer player) {
/*  96 */     com.mmiillkkaa.supernaturals.util.Location location = (com.mmiillkkaa.supernaturals.util.Location)this.teleportLocations.get(player);
/*  97 */     org.bukkit.Location bLocation = new org.bukkit.Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
/*     */     
/*     */ 
/* 100 */     return bLocation;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasAngel(SuperNPlayer snplayer)
/*     */   {
/* 108 */     if (this.angels.containsValue(snplayer)) {
/* 109 */       return true;
/*     */     }
/* 111 */     return false;
/*     */   }
/*     */   
/*     */   public void removeAngel(SuperNPlayer snplayer) {
/* 115 */     for (SuperNPlayer player : this.angels.keySet()) {
/* 116 */       if (((SuperNPlayer)this.angels.get(player)).equals(snplayer)) {
/* 117 */         this.angels.remove(player);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public SuperNPlayer getAngelPlayer(SuperNPlayer snplayer) {
/* 123 */     return (SuperNPlayer)this.angels.get(snplayer);
/*     */   }
/*     */   
/*     */   public void addAngel(SuperNPlayer snplayer, SuperNPlayer sntarget) {
/* 127 */     this.angels.put(snplayer, sntarget);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArrayList<String> getPlayerApp(SuperNPlayer player)
/*     */   {
/* 135 */     return (ArrayList)this.hunterApps.get(player);
/*     */   }
/*     */   
/*     */   public void addPlayerApp(SuperNPlayer player, ArrayList<String> kills) {
/* 139 */     this.hunterApps.put(player, kills);
/*     */   }
/*     */   
/*     */   public boolean playerHasApp(SuperNPlayer player) {
/* 143 */     if (this.hunterApps.containsKey(player)) {
/* 144 */       return true;
/*     */     }
/* 146 */     return false;
/*     */   }
/*     */   
/*     */   public void removePlayerApp(SuperNPlayer player) {
/* 150 */     this.hunterApps.remove(player);
/*     */   }
/*     */   
/*     */   public HashMap<SuperNPlayer, ArrayList<String>> getApps() {
/* 154 */     return this.hunterApps;
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\io\SNDataHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */