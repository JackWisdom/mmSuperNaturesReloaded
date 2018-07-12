/*     */ package git.JackWisdom.mcp.supernaturals.io;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*     */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */ import java.io.*;
/*     */
/*     */
/*     */
/*     */
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.UUID;

/*     */ import git.JackWisdom.mcp.supernaturals.UsingData;
import git.JackWisdom.mcp.supernaturals.util.Location;

import org.yaml.snakeyaml.Yaml;
/*     */
/*     */ 

/*     */ public class SNDataHandler implements UsingData
/*     */
/*     */ {

/*     */   private static final long serialVersionUID = 2266551481298554973L;
/*  42 */   private HashMap<SuperNPlayer, Location> teleportLocations = new HashMap();
            //吸血鬼 传送位置

            //牧师 守护对象
/*  44 */   private HashMap<SuperNPlayer, ArrayList<String>> hunterApps = new HashMap();
            //人类 猎杀的超能力种类
/*     */
/*  46 */   private static File data= new File(SupernaturalsPlugin.instance.getDataFolder(),"storage");
            private static File playeryData=new File(data,"players");
            public SNDataHandler(){
                if(!data.exists()){
                    data.mkdir();
                }
                if(!playeryData.exists()){
                    playeryData.mkdir();
                }
            }
            public SuperNPlayer read(UUID uuid){
                SuperNPlayer player=null;
                try {
                File data=new File(playeryData,uuid+".yml");

                if(!data.exists()){
                  data.createNewFile();
                  player=new SuperNPlayer(uuid);
                }
                   player= new Yaml().loadAs(new FileInputStream(data),SuperNPlayer.class);
                } catch (IOException e) {
                    SupernaturalsPlugin.log("error while loading"+uuid+".yml");
                }
                return player;
            }
            public void write(SuperNPlayer player){
                try {
                    File data = new File(playeryData, player.getUuid() + ".yml");
                    if (!data.exists()) {
                        data.createNewFile();
                    }
                    new Yaml().dump(player, new FileWriter(data));
                }catch (Exception e){
                    SupernaturalsPlugin.log("error while saving"+player.getUuid()+".yml");
                }
            }

/*     */   public boolean hasAngel(SuperNPlayer snplayer)
/*     */   {
            for(SuperNPlayer p:priests.values()){
                if(p.getProtecting().equals(snplayer.getUuid())){
                    return true;
                }
            }
            return false;
/*     */   }
/*     */
/*     */   public void removeAngel(SuperNPlayer snplayer) {
              for(SuperNPlayer p:priests.values()){
              if(p.getProtecting().equals(snplayer.getUuid())){
                    p.setProtecting(null);
               }
    }
/*     */   }
/*     */
/*     */   public UUID getProtectingPlayer(SuperNPlayer snplayer) {
/* 123 */     return snplayer.getProtecting();
/*     */   }
/*     */
/*     */   public void addAngel(SuperNPlayer snplayer, SuperNPlayer sntarget) {
/* 127 */    snplayer.setProtecting(sntarget.getUuid());
/*     */   }

/*     */ }


