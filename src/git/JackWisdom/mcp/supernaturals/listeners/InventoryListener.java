package git.JackWisdom.mcp.supernaturals.listeners;

import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
import git.JackWisdom.mcp.supernaturals.SuperType;
import git.JackWisdom.mcp.supernaturals.inventory.DonateGui;
import git.JackWisdom.mcp.supernaturals.inventory.IGui;
import git.JackWisdom.mcp.supernaturals.inventory.PDonateGui;
import git.JackWisdom.mcp.supernaturals.inventory.VampInfectGui;
import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
import git.JackWisdom.mcp.supernaturals.util.Language;
import git.JackWisdom.mcp.supernaturals.util.LanguageTag;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryListener implements Listener {
    @EventHandler
    public void onc(InventoryClickEvent event){
        if(!(event.getInventory() instanceof DonateGui)){
            return;
        }
        if(event.getRawSlot()==1){
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
           case PRIEST:{
               PDonateGui donateGui= (PDonateGui) iGui;
               if(donateGui.accept()){
                player.sendMessage(Language.PRIEST_DONATE_ACCEPT.toString());
                SuperNManager.alterPower(snplayer, donateGui.getPower(), Language.DAEMON_SNARE_NOTICE_SELF.toString());
               }else {
                player.sendMessage(Language.PRIEST_DONATE_ONLY.toString());
               }
           }
           case VAMPIRE_I:{
               VampInfectGui donateGui= (VampInfectGui) iGui;
               if(donateGui.accept()){
                player.sendMessage(Language.VAMPIRE_ALTAR_CONFIRM.toString());
                player.sendMessage(Language.VAMPIRE_ALTAR_INFECT_SUCCESS.toString().replace(LanguageTag.MATERIAL.toString(), SNConfigHandler.vampireAltarInfectMaterial.toLowerCase().replace('_', ' ')).replace(LanguageTag.MATERIAL_SURROUND.toString(), SNConfigHandler.vampireAltarInfectMaterialSurround.toLowerCase().replace('_', ' ')));
                   SuperNManager.convert(snplayer, SuperType.VAMPIRE, SNConfigHandler.vampirePowerStart);
               }else {
                player.sendMessage(Language.GUI_EXACTLY.toString());
               }
           }
           case VAMPIRE_C:{

           }
       }

   }
}
