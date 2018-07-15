package git.JackWisdom.mcp.supernaturals.manager;

import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class MermaidManager extends ClassManager{
    @Override
    public double damagerEvent(EntityDamageByEntityEvent event, double damage) {
        return 0;
    }

    @Override
    public void waterAdvanceTime(Player player) {

    }

    @Override
    public boolean shootArrow(Player shooter, EntityShootBowEvent event) {
        return false;
    }

    @Override
    public void spellEvent(EntityDamageByEntityEvent event, Player target) {

    }

    @Override
    public double victimEvent(EntityDamageEvent event, double damage) {
        return 0;
    }

    @Override
    public void eatItem(PlayerItemConsumeEvent event) {

    }

    @Override
    public void deathEvent(Player player) {

    }

    @Override
    public void killEvent(Player pDamager, SuperNPlayer damager, SuperNPlayer victim) {

    }

    @Override
    public boolean playerInteract(PlayerInteractEvent event) {
        return false;
    }

    @Override
    public void armorCheck(Player player) {

    }
}
