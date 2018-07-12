/*     */ package git.JackWisdom.mcp.supernaturals.listeners;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*     */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */ import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
/*     */
/*     */
/*     */
/*     */
/*     */ import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
/*     */
/*     */
import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */
/*     */ import org.bukkit.block.Sign;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerKickEvent;
/*     */
/*     */ import org.bukkit.material.Door;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @EventHandler(priority=EventPriority.LOW)
/*     */   public void onPlayerInteract(PlayerInteractEvent event)
/*     */   {
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
/*     */     }
/*     */     
/*     */ 
/*  76 */     Block block = event.getClickedBlock();
/*  77 */     if ((action.equals(Action.RIGHT_CLICK_BLOCK)) || (action.equals(Action.LEFT_CLICK_BLOCK))) {
/*     */       Location blockLoc;
/*     */       try {
/*  80 */         blockLoc = block.getLocation();
/*     */       } catch (NullPointerException e) {
/*  82 */         SupernaturalsPlugin.log("Door trying to close.");
/*  83 */         event.setCancelled(true);
/*  84 */         return;
/*     */       }
/*     */       
/*  87 */       if (block.getType().equals(Material.IRON_DOOR_BLOCK)) {
/*  88 */         for (int x = blockLoc.getBlockX() - 2; x < blockLoc.getBlockX() + 3; x++) {
/*  89 */           for (int y = blockLoc.getBlockY() - 2; y < blockLoc.getBlockY() + 3; 
/*  90 */               y++) {
/*  91 */             for (int z = blockLoc.getBlockZ() - 2; z < blockLoc.getBlockZ() + 3; 
/*  92 */                 z++) {
/*  93 */               Location newLoc = new Location(block.getWorld(), x, y, z);
/*     */               
/*  95 */               Block newBlock = newLoc.getBlock();
/*  96 */               if ((newBlock.getType().equals(Material.SIGN)) || (newBlock.getType().equals(Material.WALL_SIGN)))
/*     */               {
/*     */ 
/*  99 */                 Sign sign = (Sign)newBlock.getState();
/* 100 */                 String[] text = sign.getLines();
/* 101 */                 for (int i = 0; i < text.length; i++) {
/* 102 */                   if (text[i].contains(SNConfigHandler.hunterHallMessage))
/*     */                   {
/* 104 */                     if (this.plugin.getHunterManager().doorIsOpening(blockLoc))
/*     */                     {
/* 106 */                       event.setCancelled(true);
/* 107 */                       return;
/*     */                     }
/* 109 */                     Door door = (Door)block.getState().getData();
/*     */                     
/* 111 */                     boolean open = this.plugin.getHunterManager().doorEvent(player, block, door);
/*     */                     
/*     */ 
/* 114 */                     event.setCancelled(open);
/* 115 */                     return;
/*     */                   }
/* 117 */                   if (text[i].contains(SNConfigHandler.demonHallMessage))
/*     */                   {
/* 119 */                     if (this.plugin.getHunterManager().doorIsOpening(blockLoc))
/*     */                     {
/* 121 */                       event.setCancelled(true);
/* 122 */                       return;
/*     */                     }
/* 124 */                     Door door = (Door)block.getState().getData();
/*     */                     
/* 126 */                     boolean open = this.plugin.getDemonManager().doorEvent(player, block, door);
/*     */                     
/* 128 */                     event.setCancelled(open);
/* 129 */                     return;
/*     */                   }
/* 131 */                   if (text[i].contains(SNConfigHandler.vampireHallMessage))
/*     */                   {
/* 133 */                     if (this.plugin.getHunterManager().doorIsOpening(blockLoc))
/*     */                     {
/* 135 */                       event.setCancelled(true);
/* 136 */                       return;
/*     */                     }
/* 138 */                     Door door = (Door)block.getState().getData();
/*     */                     
/* 140 */                     boolean open = this.plugin.getVampireManager().doorEvent(player, block, door);
/*     */                     
/*     */ 
/* 143 */                     event.setCancelled(open);
/* 144 */                     return;
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 154 */     boolean cancelled = false;
/*     */     
/* 156 */     cancelled = this.plugin.getClassManager(player).playerInteract(event);
/*     */     
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
/* 168 */     if (blockMaterial == Material.getMaterial(SNConfigHandler.vampireAltarInfectMaterial))
/*     */     {
/* 170 */       this.plugin.getVampireManager().useAltarInfect(player, event.getClickedBlock());
/*     */     }
/* 172 */     else if (blockMaterial == Material.getMaterial(SNConfigHandler.vampireAltarCureMaterial))
/*     */     {
/* 174 */       this.plugin.getVampireManager().useAltarCure(player, event.getClickedBlock());
/*     */     }
/* 176 */     else if (blockMaterial == Material.getMaterial(SNConfigHandler.priestAltarMaterial))
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
/* 196 */       if ((snplayer.isVampire()) && (event.getPlayer().getItemInHand().getType().toString().equalsIgnoreCase(SNConfigHandler.vampireJumpMaterial)))
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