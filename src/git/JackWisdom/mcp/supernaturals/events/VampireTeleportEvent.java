package git.JackWisdom.mcp.supernaturals.events;

import git.JackWisdom.mcp.supernaturals.SuperNPlayer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class VampireTeleportEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    boolean isCancelled;

    SuperNPlayer player;
    Location loc;
    public VampireTeleportEvent(SuperNPlayer player, Location from){
        this.player=player;
        this.loc=from;
    }
    public SuperNPlayer getSNPlayer(){
        return player;
    }
    public Player getPlayer(){
        return Bukkit.getPlayer(player.getUuid());
    }
    public Location getFrom(){
        return loc;
    }
    public Location getTo(){
        return player.getTeleport();
    }
    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled=b;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
