/*     */ package git.JackWisdom.mcp.supernaturals.listeners;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*     */
import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */ import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
/*     */
/*     */
/*     */
/*     */
/*     */ import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
/*     */
/*     */
import git.JackWisdom.mcp.supernaturals.util.Language;
import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
/*     */
import org.bukkit.entity.Player;
/*     */
import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerKickEvent;
/*     */
/*     */ import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.material.Door;

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SNPlayerListener
/*     */   implements Listener
/*     */ {
/*     */   public SupernaturalsPlugin plugin;
/*  43 */   private String worldPermission = "supernatural.world.enabled";
/*     */   
/*     */   public SNPlayerListener(SupernaturalsPlugin instance) {
/*  46 */     this.plugin = instance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */


    @EventHandler
    public void onplayereat(PlayerItemConsumeEvent event){
        Player p=event.getPlayer();
        SuperNPlayer superNPlayer=SuperNManager.get(p);
        superNPlayer.getManager().eatItem(event);
    }

    /* 已经移除和玩家战斗恢复和怪物休战
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerPVP(EntityDamageByEntityEvent event){
        if(!(event.getEntity() instanceof Player &&event.getDamager() instanceof Player)){
            return;
        }
        Player d= (Player) event.getDamager();
        Player v= (Player) event.getEntity();
        SuperNPlayer sv=SuperNPlayer.getPlayerOnline(v);
        SuperNPlayer sd=SuperNPlayer.getPlayerOnline(d);
        if(sv.getType().hasTruce()&&!sv.gett){
            d.sendMessage(Language.TRUCE_RESTORE.toString());
        }
    }

*/


            private void doorEvent(Player player,Sign sign){
                if(!(sign.getBlockData() instanceof WallSign)){
                    return;
                }
               BlockFace facing= ((WallSign)sign.getBlockData()).getFacing().getOppositeFace();
               Block b=sign.getBlock().getRelative(facing);

               if(b.getType()!=Material.IRON_DOOR){
                   return;
               }
               Door door= (Door) b.getState().getData();
               if(door.isOpen()){
                   return;
               }
                 String msg=sign.getLine(0);

               if(msg.equals(SNConfigHandler.hunterHallMessage)){
                 this.plugin.getHunterManager().doorEvent(player,  door);
               }else if (msg.equals(SNConfigHandler.demonHallMessage)){
                   this.plugin.getDemonManager().doorEvent(player,  door);
               }else if (msg.equals(SNConfigHandler.vampireHallMessage)){
                   this.plugin.getVampireManager().doorEvent(player,  door);
               }
            }
/*     */   @EventHandler(priority=EventPriority.LOW)
/*     */   public void onPlayerInteract(PlayerInteractEvent event)
/*     */   {
            if(event.getHand()==EquipmentSlot.OFF_HAND){
                return;
            }
/*  61 */     Action action = event.getAction();
/*  62 */     Player player = event.getPlayer();
/*  63 */     SuperNPlayer snplayer = SuperNManager.get(player);
/*     */     
/*  65 */     if ((!action.equals(Action.RIGHT_CLICK_AIR)) && (!action.equals(Action.LEFT_CLICK_AIR)) && (event.isCancelled()))
/*     */     {
/*  67 */       return;
/*     */     }
/*     */     
/*  70 */     if ((!player.hasPermission( this.worldPermission)) && (SNConfigHandler.multiworld))
/*     */     {
/*  72 */       return;

    }
/*  76 */     Block block = event.getClickedBlock();
/*  77 */     if ((action.equals(Action.RIGHT_CLICK_BLOCK))) {
                if(block.getType()==Material.WALL_SIGN){
                    doorEvent(player,  (Sign)block.getState());
                }
/*     */     }
/* 154 */     boolean cancelled = false;
/* 156 */     cancelled = SuperNManager.get(player).getManager().playerInteract(event);
/* 158 */     if (cancelled) {
/* 159 */       return;
/*     */     }
/*     */     
/* 162 */     if (!action.equals(Action.RIGHT_CLICK_BLOCK)) {
/* 163 */       return;
/*     */     }
/*     */     
/* 166 */     Material blockMaterial = event.getClickedBlock().getType();
/*     */     
/* 168 */     if (blockMaterial == (SNConfigHandler.vampireAltarInfectMaterial))
/*     */     {
/* 170 */       this.plugin.getVampireManager().useAltarInfect(player, event.getClickedBlock());
/*     */     }
/* 172 */     else if (blockMaterial == (SNConfigHandler.vampireAltarCureMaterial))
/*     */     {
/* 174 */       this.plugin.getVampireManager().useAltarCure(player, event.getClickedBlock());
/*     */     }
/* 176 */     else if (blockMaterial == (SNConfigHandler.priestAltarMaterial))
/*     */     {
/* 178 */       this.plugin.getPriestManager().useAltar(player);
/*     */     }
/*     */   }
/*     */
/*     */   @EventHandler(priority=EventPriority.LOW)
/*     */   public void onPlayerKick(PlayerKickEvent event) {
/* 184 */     if (event.isCancelled()) {
/* 185 */       return;
/*     */     }
/*     */     
/* 188 */     if ((!event.getPlayer().hasPermission( this.worldPermission)) && (SNConfigHandler.multiworld))
/*     */     {
/* 190 */       return;
/*     */     }
/*     */     
/* 193 */     if ((event.getLeaveMessage().contains("Flying")) || (event.getReason().contains("Flying")))
/*     */     {
/* 195 */       SuperNPlayer snplayer = SuperNManager.get(event.getPlayer());
/* 196 */       if ((snplayer.isVampire()) && (event.getPlayer().getItemInHand().getType().equals(SNConfigHandler.vampireJumpMaterial)))
/*     */       {
/* 203 */         event.setCancelled(true);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\listeners\SNPlayerListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */