package git.JackWisdom.mcp.supernaturals.storage;

import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
import git.JackWisdom.mcp.supernaturals.SuperType;
import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashSet;
import java.util.UUID;

public class FileDataHandler extends SNDataHandler {
    private File data= new File(SupernaturalsPlugin.instance.getDataFolder(),"storage");
    public FileDataHandler(){
        if(!data.exists()){
            data.mkdir();
        }
    }
    @Override
    public SuperNPlayer load(UUID uuid) {
        if(!data.exists()){
            data.mkdir();
        }
        SuperNPlayer player=new SuperNPlayer();
        try {
            File data=new File( this.data,uuid+".yml");

            if(!data.exists()){
                data.createNewFile();
            }
            YamlConfiguration cfg=YamlConfiguration.loadConfiguration(data);
            player.uuid=UUID.fromString(cfg.getString("uuid"));
            player.type=SuperType.valueOf(cfg.getString("type"));
            String tel="teleport.";
            String world=cfg.getString(tel+"world");
            if(!world.equals("NONE")) {
                player.teleport=new Location(Bukkit.getWorld(world),cfg.getDouble(tel+"X"),
                        cfg.getDouble(tel+"Y"),cfg.getDouble(tel+"Z"));
            }
            player.superPower=cfg.getDouble("power");
            String uid=cfg.getString("protecting");
            if(uid!=null){
                player.protecting=UUID.fromString(uid);
            }
            player.oldType=SuperType.valueOf(cfg.getString("oldType"));
            player.oldPower=cfg.getDouble("oldPower");
            player.hunterApp= (HashSet<SuperType>) cfg.get("hunterApp");
        } catch (Exception e) {
            player=null;
            SupernaturalsPlugin.log("error while loading"+uuid+".yml creating new data for it");
        }
        if(player==null){
            player=new SuperNPlayer(uuid);
            SupernaturalsPlugin.log("creating data file for newbit "+player.getUuid());
        }
        return player;
    }

    @Override
    public void save(SuperNPlayer player) {
        if(!data.exists()){
            data.mkdir();
        }
            if(player==null||player.getUuid()==null){
                return;
            }
        try {
            File data = new File(this.data, player.getUuid() + ".yml");
            if (!data.exists()) {
                data.createNewFile();
            }
            YamlConfiguration cfg=YamlConfiguration.loadConfiguration(data);
            cfg.set("uuid",player.getUuid().toString());
            cfg.set("type",player.getType().name());
            if(player.teleport==null){
                cfg.set("teleport.world","NONE");
            }else {
                String t="teleport.";
                cfg.set(t+"world",player.teleport.getWorld().getName());
                cfg.set(t+"X",player.teleport.getX());
                cfg.set(t+"Y",player.teleport.getY());
                cfg.set(t+"Z",player.teleport.getZ());
            }
            cfg.set("protecting",player.protecting);
            cfg.set("oldType",player.oldType.name());
            cfg.set("oldPower",player.oldPower);
            cfg.set("power",player.superPower);
            cfg.set("hunterApp",player.hunterApp);
            cfg.save(data);
        }catch (Exception e){
            e.printStackTrace();
            SupernaturalsPlugin.log("error while saving"+player.getUuid()+".yml");
        }
    }
}
