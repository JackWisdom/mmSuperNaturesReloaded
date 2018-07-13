package git.JackWisdom.mcp.supernaturals.events;

import git.JackWisdom.mcp.supernaturals.SuperNPlayer;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class VampireJumpEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    boolean isCancelled;

    Player player;


    double deltaSpeed;
    boolean upOnly;
    public VampireJumpEvent(Player player, double deltaSpeed, boolean upOnly){
        this.player=player;
        this.deltaSpeed=deltaSpeed;
        this.upOnly=upOnly;
    }

    public double getDeltaSpeed() {
        return deltaSpeed;
    }

    public boolean isUpOnly() {
        return upOnly;
    }
    public SuperNPlayer getSNPlayer(){
        return SuperNPlayer.getPlayerOnline(player);
    }
    public Player getPlayer(){
        return player;
    }
    public Location getLocation(){
        return player.getLocation();
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
