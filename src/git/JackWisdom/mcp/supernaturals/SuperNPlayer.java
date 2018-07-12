/*     */ package git.JackWisdom.mcp.supernaturals;
/*     */ 
/*     */ import java.io.Serializable;
import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.entity.Player;

import javax.annotation.Nullable;
/*     */ 

/*     */ public class SuperNPlayer
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2693531379993789149L;
/*     */   public UUID uuid;
/*  32 */   public SuperType superType=SuperType.HUMAN;
/*  33 */   public SuperType oldSuperType =SuperType.HUMAN;
/*  34 */   public double oldSuperPower = 0.0D;
/*  35 */   public double superPower = 0.0D;
/*  36 */   public boolean truce = true;
/*  37 */   public int truceTimer = 0;
/*     */   
/*     */   public SuperNPlayer() {}
/*     */   
           public SuperNPlayer(UUID uuid)
            {
             this.uuid=uuid;
             this.superType = SuperType.HUMAN;
             this.oldSuperType =SuperType.HUMAN;
             this.oldSuperPower = 0.0D;
             this.superPower = 0.0D;
             this.truce = true;
             this.truceTimer = 0;
            }
    public SuperNPlayer(Player player)
    {
        this(player.getUniqueId());
    }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public UUID getUuid()
/*     */   {
/*  57 */     return this.getUuid();
/*     */   }

/*     */   public SuperType getType() {
/*  65 */     return this.superType;
/*     */   }
/*     */   
/*     */   public void setType(SuperType type) {
/*  69 */     this.superType = type;
/*     */   }
/*     */   
/*     */   public SuperType getOldType() {
/*  73 */     return this.oldSuperType;
/*     */   }
/*     */   
/*     */   public void setOldType(SuperType type) {
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
/*     */   
/*     */   public double scale(double input) {
/* 184 */     double powerPercentage = input * (getPower() / 10000.0D);
/* 185 */     return powerPercentage;
/*     */   }
/*     */   
/*     */   public boolean isOnline() {

/* 189 */     for(Player p:Bukkit.getOnlinePlayers()) {
                if(p.getUniqueId().equals(uuid)){
                    return true;
                }
                }
                return false;
/*     */   }
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
/*     */   
/*     */ 
/*     */   public int hashCode()
/*     */   {
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

