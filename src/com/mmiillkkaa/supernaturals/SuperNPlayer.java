/*     */ package com.mmiillkkaa.supernaturals;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class SuperNPlayer
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2693531379993789149L;
/*     */   public String playername;
/*  32 */   public String superType = "human";
/*  33 */   public String oldSuperType = "human";
/*  34 */   public double oldSuperPower = 0.0D;
/*  35 */   public double superPower = 0.0D;
/*  36 */   public boolean truce = true;
/*  37 */   public int truceTimer = 0;
/*     */   
/*     */   public SuperNPlayer() {}
/*     */   
/*     */   public SuperNPlayer(String playername)
/*     */   {
/*  43 */     this.playername = playername;
/*  44 */     this.superType = "human";
/*  45 */     this.oldSuperType = "human";
/*  46 */     this.oldSuperPower = 0.0D;
/*  47 */     this.superPower = 0.0D;
/*  48 */     this.truce = true;
/*  49 */     this.truceTimer = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  57 */     return this.playername;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  61 */     this.playername = name;
/*     */   }
/*     */   
/*     */   public String getType() {
/*  65 */     return this.superType;
/*     */   }
/*     */   
/*     */   public void setType(String type) {
/*  69 */     this.superType = type;
/*     */   }
/*     */   
/*     */   public String getOldType() {
/*  73 */     return this.oldSuperType;
/*     */   }
/*     */   
/*     */   public void setOldType(String type) {
/*  77 */     this.oldSuperType = type;
/*     */   }
/*     */   
/*     */   public double getOldPower() {
/*  81 */     return this.oldSuperPower;
/*     */   }
/*     */   
/*     */   public void setOldPower(double amount) {
/*  85 */     this.oldSuperPower = amount;
/*     */   }
/*     */   
/*     */   public double getPower() {
/*  89 */     return this.superPower;
/*     */   }
/*     */   
/*     */   public void setPower(double amount) {
/*  93 */     this.superPower = limitDouble(amount);
/*     */   }
/*     */   
/*     */   public boolean getTruce() {
/*  97 */     return this.truce;
/*     */   }
/*     */   
/*     */   public void setTruce(boolean truce) {
/* 101 */     this.truce = truce;
/* 102 */     this.truceTimer = 0;
/*     */   }
/*     */   
/*     */   public int getTruceTimer() {
/* 106 */     return this.truceTimer;
/*     */   }
/*     */   
/*     */   public void setTruceTimer(int timer) {
/* 110 */     this.truceTimer = timer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSuper()
/*     */   {
/* 118 */     if ((getType().equalsIgnoreCase("human")) || (getType().equalsIgnoreCase("priest")) || (getType().equalsIgnoreCase("witchhunter")) || (getType().equalsIgnoreCase("angel")))
/*     */     {
/*     */ 
/*     */ 
/* 122 */       return false;
/*     */     }
/* 124 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isAngel() {
/* 128 */     if (getType().equalsIgnoreCase("angel")) {
/* 129 */       return true;
/*     */     }
/* 131 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isHuman() {
/* 135 */     if (getType().equalsIgnoreCase("human")) {
/* 136 */       return true;
/*     */     }
/* 138 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isVampire() {
/* 142 */     if (getType().equalsIgnoreCase("vampire")) {
/* 143 */       return true;
/*     */     }
/* 145 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isPriest() {
/* 149 */     if (getType().equalsIgnoreCase("priest")) {
/* 150 */       return true;
/*     */     }
/* 152 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isWere() {
/* 156 */     if (getType().equalsIgnoreCase("werewolf")) {
/* 157 */       return true;
/*     */     }
/* 159 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isGhoul() {
/* 163 */     if (getType().equalsIgnoreCase("ghoul")) {
/* 164 */       return true;
/*     */     }
/* 166 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isHunter() {
/* 170 */     if (getType().equalsIgnoreCase("witchhunter")) {
/* 171 */       return true;
/*     */     }
/* 173 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isDemon() {
/* 177 */     if (getType().equalsIgnoreCase("demon")) {
/* 178 */       return true;
/*     */     }
/* 180 */     return false;
/*     */   }
/*     */   
/*     */   public double scale(double input) {
/* 184 */     double powerPercentage = input * (getPower() / 10000.0D);
/* 185 */     return powerPercentage;
/*     */   }
/*     */   
/*     */   public boolean isOnline() {
/* 189 */     return SupernaturalsPlugin.instance.getServer().getPlayer(this.playername) != null;
/*     */   }
/*     */   
/*     */   public boolean isDead() {
/* 193 */     return SupernaturalsPlugin.instance.getServer().getPlayer(this.playername).isDead();
/*     */   }
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 199 */     return this.playername.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj)
/*     */   {
/* 204 */     if ((obj instanceof SuperNPlayer)) {
/* 205 */       return this.playername.equals(((SuperNPlayer)obj).getName());
/*     */     }
/* 207 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double limitDouble(double d, double min, double max)
/*     */   {
/* 214 */     if (d < min) {
/* 215 */       return min;
/*     */     }
/* 217 */     if (d > max) {
/* 218 */       return max;
/*     */     }
/* 220 */     return d;
/*     */   }
/*     */   
/*     */   public double limitDouble(double d) {
/* 224 */     return limitDouble(d, 0.0D, 10000.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\SuperNPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */