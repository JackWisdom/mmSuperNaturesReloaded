package git.JackWisdom.mcp.supernaturals.events;

import git.JackWisdom.mcp.supernaturals.SuperType;
import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerChangeTypeEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    SuperType oldt,newt;
    double   newp;
    public PlayerChangeTypeEvent(SuperType oldt, SuperType newt, double newp){

        this.oldt=oldt;
        this.newp=newp;
        this.newt=newt;
    }
    public SuperType getOldType(){
        return oldt;
    }
    public SuperType getNewType(){
        return newt;
    }

    public double getNewPower(){
        return newp;
    }
    public SuperNManager getManager(){
        return SupernaturalsPlugin.instance.getSuperManager();
    }
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
