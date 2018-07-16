package git.JackWisdom.mcp.supernaturals.listeners;

import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
import git.JackWisdom.mcp.supernaturals.SuperType;
import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
import git.JackWisdom.mcp.supernaturals.inventory.*;
import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
import git.JackWisdom.mcp.supernaturals.util.Language;
import git.JackWisdom.mcp.supernaturals.util.LanguageTag;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class InventoryListener implements Listener {
    @EventHandler
    public void onc(InventoryClickEvent event){

        if(!(event.getInventory().getHolder() instanceof IGui)){
            return;
        }

        if(event.getRawSlot()<=8){
            event.setCancelled(true);
        }
    }
   @EventHandler
   public void onC(InventoryCloseEvent event){
       if(event.getInventory().getHolder()==null||!(event.getInventory().getHolder() instanceof IGui)){
           return;
       }
       if(!(event.getPlayer() instanceof Player)){
           return;
       }
       Player player= (Player) event.getPlayer();
       IGui iGui= (IGui) event.getInventory().getHolder();
       SuperNPlayer snplayer=SuperNManager.get(player);

        new BukkitRunnable() {
            @Override
            public void run() {
                switch (iGui.getType()){
                    case HUMAN:{
                        DonateGui donateGui= (DonateGui)iGui;
                        if(donateGui.accept()){
                            SuperNManager.convert(SuperNManager.get(event.getPlayer().getUniqueId()), SuperType.PRIEST, SNConfigHandler.priestPowerStart);
                            event.getPlayer().sendMessage(Language.PRIEST_DONATE_CONFIRM.toString());
                            event.getPlayer().sendMessage(Language.PRIEST_DONATE_ENOUGHT.toString());

                        }else{
                            event.getPlayer().sendMessage(Language.PRIEST_DONATE_NOT_ENOGHT.toString());

                        }
                        return;
                    }

                    case VAMPIRE_I:{
                        VampInfectGui donateGui= (VampInfectGui) iGui;
                        if(donateGui.accept()){
                            player.sendMessage(Language.VAMPIRE_ALTAR_CONFIRM.toString());
                            player.sendMessage(Language.VAMPIRE_ALTAR_INFECT_SUCCESS.toString().replace(LanguageTag.MATERIAL.toString(), SNConfigHandler.vampireAltarInfectMaterial.name().toLowerCase().replace('_', ' ')).replace(LanguageTag.MATERIAL_SURROUND.toString(), SNConfigHandler.vampireAltarInfectMaterialSurround.name().toLowerCase().replace('_', ' ')));
                            SuperNManager.convert(snplayer, SuperType.VAMPIRE, SNConfigHandler.vampirePowerStart);
                        }else {
                            player.sendMessage(Language.GUI_EXACTLY.toString());
                        }
                        return;
                    }
                    case VAMPIRE_C:{
                        VampCureGui donateGui=(VampCureGui) iGui;
                        if(donateGui.accept()){
                            player.sendMessage( Language.VAMPIRE_ALTAR_CONFIRM.toString());
                            player.sendMessage(Language.VAMPIRE_ALTAR_CURE_SUCCESS.toString().replace(LanguageTag.MATERIAL.toString(), SNConfigHandler.vampireAltarCureMaterial.name().toLowerCase().replace('_', ' ')).replace(LanguageTag.MATERIAL_SURROUND.toString(), SNConfigHandler.vampireAltarCureMaterialSurround.name().toLowerCase().replace('_', ' ')));
                            SuperNManager.cure(snplayer);
                        }else {
                            player.sendMessage(Language.GUI_EXACTLY.toString());
                        }
                        return;
                    }
                }
         }
        }.runTaskAsynchronously(SupernaturalsPlugin.instance);

   }
}
