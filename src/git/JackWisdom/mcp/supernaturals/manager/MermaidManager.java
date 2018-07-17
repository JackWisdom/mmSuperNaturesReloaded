package git.JackWisdom.mcp.supernaturals.manager;

import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
import git.JackWisdom.mcp.supernaturals.util.Language;
import org.bukkit.Material;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class MermaidManager extends ClassManager{
    @Override
    public double damagerEvent(EntityDamageByEntityEvent event, double damage) {
        if(event.getEntity() instanceof Fish){
            event.setCancelled(true);
            event.getDamager().sendMessage(Language.MERMAID_HURT_FISH.toString());
        }

        return 0;
    }

    @Override
    public void waterAdvanceTime(Player player) {

            /* 266 */     if (player.isDead()) {
                /* 267 */       return;
                /*     */     }
            /*     */
            /* 270 */     if ((player.isInsideVehicle()) &&
                    /* 271 */       ((player.getVehicle() instanceof Boat))) {
                /* 272 */       return;
                /*     */     }
            /*     */
            /*     */
            /* 276 */     SuperNPlayer snplayer = SuperNManager.get(player);
            /*     */
            /* 278 */     Material material = player.getLocation().getBlock().getType();
            /*     */
            /* 280 */     if ( (material == Material.WATER)) {
                /* 281 */       SuperNManager.alterPower(snplayer, SNConfigHandler.mermaidSwimPowerGain, Language.MERMAID_SWIM.toString());
                /*     */     }

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
        if(event.getCause()==EntityDamageEvent.DamageCause.DROWNING){
            event.setCancelled(true);
            return 0;
        }
        return damage;
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
/*
     if (itemInHandMaterial. equals(SNConfigHandler.angelJumpMaterial))
       {
             if (snplayer.getPower() > SNConfigHandler.angelJumpPowerCost) {
                 jump(player, SNConfigHandler.angelJumpDeltaSpeed);
              } else {
                SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
                     }
        }*/
    private void dash(){}

    @Override
    public boolean playerInteract(PlayerInteractEvent event) {
        if(event.getAction()!= Action.LEFT_CLICK_AIR){
            return false;
        }
        Material dm=SNConfigHandler.mermaidDashMaterial;

        Material type=event.getItem().getType();
        if(type==dm){
        jump(event.getPlayer() );
        }
        return false;
    }
    /*     */   private void jump(Player player ) {
        /* 210 */     SuperNPlayer snplayer = SuperNManager.get(player);
        /*     */
        /* 212 */     if (snplayer.getPower() < SNConfigHandler.mermaidDashCost) {
            /* 213 */       SuperNManager.sendMessage(snplayer, Language.NO_POWER.toString());
            /* 214 */       return ;
            /*     */     }
        /* 216 */     SuperNManager.alterPower(snplayer, -SNConfigHandler.mermaidDashCost, Language.MERMAID_DASH.toString());
        /* 222 */     Vector vjadd = new Vector(3, 2.25, 3);
        /* 223 */
        /* 225 */     player.setVelocity(player.getVelocity().add(vjadd));
        /* 226 */     return ;
        /*     */   }

    /*     */     @Override
    /*     */   public void armorCheck(Player player)
    /*     */   {
        /* 231 */     if (!player.hasPermission("supernatural.player.ignorearmor")) {
            /* 232 */       PlayerInventory inv = player.getInventory();
            /* 233 */       ItemStack helmet = inv.getHelmet();
            /* 234 */       ItemStack chest = inv.getChestplate();
            /* 235 */       ItemStack leggings = inv.getLeggings();
            /* 236 */       ItemStack boots = inv.getBoots();
            /*     */
            /* 238 */       if ((helmet != null) &&
                    /* 239 */         (SNConfigHandler.mermaidArmor.contains(helmet.getType()))) {
                /* 240 */         inv.setHelmet(null);
                /* 241 */         dropItem(player, helmet);
                /*     */       }
            /*     */
            /* 244 */       if ((chest != null) &&
                    /* 245 */         (SNConfigHandler.mermaidArmor.contains(chest.getType()))) {
                /* 246 */         inv.setChestplate(null);
                /* 247 */         dropItem(player, chest);
                /*     */       }
            /*     */
            /* 250 */       if ((leggings != null) &&
                    /* 251 */         (SNConfigHandler.mermaidArmor.contains(leggings.getType()))) {
                /* 252 */         inv.setLeggings(null);
                /* 253 */         dropItem(player, leggings);
                /*     */       }

            /* 256 */       if ((boots != null) &&
                    /* 257 */         (SNConfigHandler.mermaidArmor.contains(boots.getType()))) {
                /* 258 */         inv.setBoots(null);
                /* 259 */         dropItem(player, boots);
                /*     */       }
            /*     */     }
        /*     */   }
}
