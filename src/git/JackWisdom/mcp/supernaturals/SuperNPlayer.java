/*     */ package git.JackWisdom.mcp.supernaturals;
/*     */ 
/*     */ import java.io.Serializable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
/*     */ import git.JackWisdom.mcp.supernaturals.manager.ClassManager;
import git.JackWisdom.mcp.supernaturals.util.Color;
import git.JackWisdom.mcp.supernaturals.util.Language;
import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
/*     */ 

/*     */ public class SuperNPlayer
/*     */   implements Serializable,UsingData
/*     */ {
/*     */   private static final long serialVersionUID = -2693531379993789149L;
        public UUID uuid;
        public SuperType type =SuperType.HUMAN;
        public SuperType oldType =SuperType.HUMAN;
        public double oldPower = 0.0D;
        public double superPower = 0.0D;
        public boolean truce = true;
        public int truceTimer = 0;
        public UUID protecting=null;
        public Location teleport =null;
        //牧师保护的UID
        public HashSet<SuperType> hunterApp=new HashSet<SuperType>(){
           @Override
           public boolean add(SuperType e) {
               if(e.isHuman()){
               return false;}
               //只允许非人类
               return super.add(e);
           }
        };

/*     */   
/*     */   public SuperNPlayer() {}
/*     */   
           public SuperNPlayer(UUID uuid)
            {
             this.uuid=uuid;
             this.type = SuperType.HUMAN;
             this.oldType =SuperType.HUMAN;
             this.oldPower = 0.0D;
             this.superPower = 0.0D;
             this.truce = true;
             this.truceTimer = 0;
             this.teleport =null;
             this.hunterApp=new HashSet<>();
             this.protecting=null;
            }
              public SuperNPlayer(Player player)
              {
                 this(player.getUniqueId());
              }
    //猎人猎杀的超能力者种类
              public HashSet getHuntApp(){
               return hunterApp;
            }
            //吸血鬼的传送位置
            public void setTeleport(Location loc){
               this.teleport =loc;
            }
             //吸血鬼是否可以传送
              public boolean hasTeleport(){
                    return teleport ==null;
              }
              //获取吸血鬼的传送位置
            public org.bukkit.Location getTeleport() {
               Location location = this.teleport;
              org.bukkit.Location bLocation = new org.bukkit.Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
             return bLocation;
              }
/*     */   public UUID getUuid()
/*     */   {
/*  57 */     return this.uuid;
/*     */   }
            public HashMap getBelong(){
            return getType().getBelong();
            }
            public UUID getProtecting(){
            return this.protecting;
            }
            public ClassManager getManager(){
    return getType().getManager();
            }
          public Player getProtectingPlayer(){
          return Bukkit.getPlayer(protecting);
            }
            public void setProtecting(UUID uuid){
            this.protecting=uuid;
            }
/*     */   public SuperType getType() {
/*  65 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(SuperType type) {
/*  69 */     this.type = type;
/*     */   }
/*     */   
/*     */   public SuperType getOldType() {
/*  73 */     return this.oldType;
/*     */   }
/*     */   
/*     */   public void setOldType(SuperType type) {
/*  77 */     this.oldType = type;
/*     */   }
/*     */   
/*     */   public double getOldPower() {
/*  81 */     return this.oldPower;
/*     */   }
/*     */   
/*     */   public void setOldPower(double amount) {
/*  85 */     this.oldPower = amount;
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
/* 118 */     return getType().isSuper();
/*     */   }
/*     */   
/*     */   public boolean isAngel() {
/* 128 */     return getType()==SuperType.ANGLE;
/*     */   }
/*     */   
/*     */   public boolean isHuman() {
/* 135 */     return getType().isHuman();
/*     */   }
/*     */   
/*     */   public boolean isVampire() {
/* 142 */     return getType()==SuperType.VAMPIRE;
/*     */   }
/*     */   
/*     */   public boolean isPriest() {
/* 149 */    return getType()==SuperType.PRIEST;
/*     */   }
/*     */   
/*     */   public boolean isWere() {
/* 156 */   return getType()==SuperType.WEREWOLF;
/*     */   }
/*     */   
/*     */   public boolean isGhoul() {
/* 163 */    return getType()==SuperType.GHOUL;
/*     */   }
/*     */   
/*     */   public boolean isHunter() {
/* 170 */    return getType()==SuperType.WITCHHUNTER;
/*     */   }
/*     */   
/*     */   public boolean isDemon() {
/* 177 */  return getType()==SuperType.DEMON;
/*     */   }

/*     */   public double scale(double input) {
/* 184 */     double powerPercentage = input * (getPower() / 10000.0D);
/* 185 */     return powerPercentage;
/*     */   }
    public static SuperNPlayer getPlayerOnline(Player p){
    return getPlayerOnline(p.getUniqueId());
    }
    public static SuperNPlayer getPlayerOnline(String  p){
    UUID uid=null;
    try {
    uid=UUID.fromString(p);
    }catch (Exception e){
        return null;
    }

        return getPlayerOnline(uid);
    }
/*     */   public static SuperNPlayer getPlayerOnline(UUID uuid){
            if(superpowers.get(uuid)==null){
                return null;
            }
             if(superpowers.get(uuid).getUuid()==null){
                superpowers.remove(uuid);
                return null;
             }
             return superpowers.get(uuid);
            }
/*     */   public boolean isOnline() {

/* 189 */     for(Player p:Bukkit.getOnlinePlayers()) {
                if(p.getUniqueId().equals(uuid)){
                    return true;
                }
                }
                return false;
/*     */   }
            public String getPrefix(){
    SuperNPlayer snplayer=this;
         String prefix="";
                if (snplayer.isPriest()) {
                    prefix = Color.PRIEST.prefix(Language.SN_PREIEST_NAME); } else if (snplayer.isVampire()) {
                    prefix = Color.VAMPIRE.prefix(Language.SN_VAMPIRE_NAME); } else if (snplayer.isGhoul()) {
                    prefix = Color.GHOUL.prefix(Language.SN_GHOUL_NAME); } else  if (snplayer.isWere()) {
                    prefix = Color.WEREWOLF.prefix(Language.SN_WEREWOLF_NAME); } else   if (snplayer.isHunter()) {
                    prefix = Color.WITCHHUNTER.prefix(Language.SN_WITCHHUNTER_NAME); } else if (snplayer.isDemon()) {
                    prefix = Color.DEMON.prefix(Language.SN_DEMON_NAME); } else  if (snplayer.isAngel()) {
                    prefix = Color.ANGEL.prefix(Language.SN_ANGEL_NAME);
                } else{
                    prefix = Color.HUMAN.prefix(Language.SN_HUMAN_NAME);
                }
                return prefix;
            }
            @Nullable
            public Player getPlayer(){
            if(!isOnline()){
                return null;
            }
            return Bukkit.getPlayer(getUuid());
            }
            public boolean hasPermission(String permission){
            if(getPlayer()==null){
                return false;
            }
            return getPlayer().hasPermission(permission);
            }
            public String getName(){
            if(getPlayer()==null){
                return "无";
            }
            return getPlayer().getName();
            }
    public boolean isDead() {
        if(getPlayer()==null){
            return true;
        }
        return getPlayer().isDead();
        }
        public void save(){
        SupernaturalsPlugin.instance.getDataHandler().save(this);
            SupernaturalsPlugin.log("saving data for"+uuid.toString());
        }
    public int hashCode()
    {
/* 199 */     return uuid.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj)
/*     */   {
/* 204 */     if ((obj instanceof SuperNPlayer)) {
/* 205 */       return this.hashCode()==obj.hashCode();
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

