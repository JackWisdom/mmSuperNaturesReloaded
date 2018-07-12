package git.JackWisdom.mcp.supernaturals.listeners;

import git.JackWisdom.mcp.supernaturals.UsingData;
import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerGather implements Listener ,UsingData {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerJoin(PlayerJoinEvent event){
        if(!event.getPlayer().isOnline()){
            return;
        }
   SuperNManager.load(event.getPlayer().getUniqueId());

    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerQuitEvent(PlayerQuitEvent event){
      SuperNManager.unLoad(event.getPlayer());
    }
}
