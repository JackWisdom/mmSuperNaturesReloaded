package git.JackWisdom.mcp.supernaturals.hooks;

import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.entity.Player;

public class PAPIHook extends  PlaceholderHook {

    @Override
    public String onPlaceholderRequest(Player p, String params) {
        switch (params){
            case "mm_player_type":{
                return SuperNManager.get(p).getPrefix();
            }
            case "mm_player_power":{
                String  s=String.valueOf(SuperNManager.get(p).getPower());
                return s.substring(s.indexOf("."));
            }
        }
        return "";
    }

    public void hook() {
        PlaceholderAPI.registerPlaceholderHook("mm_player_type",this);
        PlaceholderAPI.registerPlaceholderHook("mm_player_power",this);
    }
}
