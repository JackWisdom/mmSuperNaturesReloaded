package git.JackWisdom.mcp.supernaturals.events;

import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DemonFireBallDamageEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled;
    Player player;
    LivingEntity entity;
    double damage;
    int fireTick;

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public int getFireTick() {
        return fireTick;
    }

    public Player getPlayer() {
        return player;
    }
    public SuperNPlayer getSNPlayer() {
        return SuperNManager.get(player);
    }
    public LivingEntity getEntity() {
        return entity;
    }

    public void setFireTick(int fireTick) {
        this.fireTick = fireTick;
    }

    public DemonFireBallDamageEvent(Player shooter, LivingEntity entity, double damage, int fireTick){
        this.player=shooter;
        this.entity=entity;

        this.damage=damage;
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
