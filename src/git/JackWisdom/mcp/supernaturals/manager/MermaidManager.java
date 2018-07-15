package git.JackWisdom.mcp.supernaturals.manager;

import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
        //美人鱼不能吃曲奇
        if(event.getItem().getType()==Material.COOKIE){
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON,100,1));
        }
    }

    @Override
    public void deathEvent(Player player) {
   if(!(player.getLastDamageCause() instanceof EntityDamageByEntityEvent)){
       return;
   }
   EntityDamageByEntityEvent event= (EntityDamageByEntityEvent) player.getLastDamageCause();
   if(!(event.getDamager() instanceof Trident)){
       return;
   }
   if(player.isSwimming()){
       return;
   }
   //美人鱼不能被三叉戟在岸上打死
   SuperNManager.cure(player);
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
