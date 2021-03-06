/*     */ package git.JackWisdom.mcp.supernaturals.listeners;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*     */ import git.JackWisdom.mcp.supernaturals.SuperType;
import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */ import git.JackWisdom.mcp.supernaturals.io.SNConfigHandler;
/*     */ import git.JackWisdom.mcp.supernaturals.io.SNWhitelistHandler;
/*     */
/*     */ import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
/*     */ import git.JackWisdom.mcp.supernaturals.util.Color;
/*     */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*     */ import git.JackWisdom.mcp.supernaturals.util.LanguageTag;
/*     */
/*     */
/*     */ import org.bukkit.World;
/*     */
/*     */ import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;

import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.event.player.PlayerPortalEvent;

import java.util.HashSet;

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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SNPlayerMonitor
/*     */   implements Listener
/*     */ {            @EventHandler
public void playerDeath(PlayerDeathEvent event){
    Player pVictim = event.getEntity();
    if ((!pVictim.hasPermission(this.worldPermission)) && (SNConfigHandler.multiworld)) {
        return;
    }
    if (!pVictim.isOnline()) {
        return;
    }
    SuperNPlayer snplayer = SuperNManager.get(pVictim);
    Entity killer=event.getEntity().getKiller();
    snplayer.getManager().deathEvent(event.getEntity());
    if(killer==null){
        return;
    }
    if(killer instanceof Wolf){
        Wolf wolf= (Wolf)killer;
        if(!(wolf.getOwner() instanceof Player)){return;}
        Player owner= (Player) wolf.getOwner();
        SuperNManager.get(owner).getManager().killEvent(owner,SuperNManager.get(owner),snplayer);
        return;
    }
    if(!(killer instanceof Player)){ return;}
    Player pkiller=pVictim.getKiller();
    SuperNPlayer snkiller=SuperNManager.get(pkiller);
    if(snkiller.isHunter()){
        if(pVictim.getName().equals(pkiller.getName())){
            SuperNManager.sendMessage(snplayer, Language.KILL_SELF.toString());
            SuperNManager.sendMessage(snplayer, Language.WITCHHUNTER_KILL_SELF.toString());
        }//is suicide
        return;
    }
    if(!snkiller.isHuman()){return;}
    HashSet<SuperType> supersKilled=snkiller.getHuntApp();
    supersKilled.add(snplayer.getType());
    if(supersKilled.size()>=3){
        this.plugin.getHunterManager().invite(snkiller);
    }
}
/*     */   private SupernaturalsPlugin plugin;
/*  43 */   private String worldPermission = "supernatural.world.enabled";
/*     */   
/*     */   public SNPlayerMonitor(SupernaturalsPlugin instance) {
/*  46 */     this.plugin = instance;
/*     */   }
            //PlayerPrefix
/*     */   @EventHandler(priority=EventPriority.MONITOR)
/*     */   public void onPlayerChat(AsyncPlayerChatEvent event)
/*     */   {
/*  58 */     if (event.isCancelled()) {
/*  59 */       return;
/*     */     }
/*  61 */     Player player = event.getPlayer();
/*  62 */     SuperNPlayer snplayer = SuperNManager.get(player);

/*  64 */
            //PlayerPrefixSetting
/*  82 */     event.setFormat(event.getFormat().replace("[SN]", snplayer.getPrefix()));
/*     */   }
    /*     *//*
    @EventHandler(priority=EventPriority.MONITOR)
   public void onPlayerPortal(PlayerPortalEvent event) {
     if (event.isCancelled()) {
        return;
      }
    if (event.getTo() == null) {
     return;
    }
     if (event.getTo().getWorld() == null) {
        return;
      }
      Player player = event.getPlayer();
      if (player == null) {
       return;
    }
    if ((!player.hasPermission( this.worldPermission)) && (SNConfigHandler.multiworld))
    {
      return;
      }
   if (event.getTo().getWorld().getEnvironment().equals(World.Environment.NETHER))
   {
      this.plugin.getDemonManager().checkInventory(player);
      }
    }
   */
/*     */   @EventHandler(priority=EventPriority.MONITOR)
/*     */   public void onPlayerJoin(PlayerJoinEvent event) {
/* 112 */     Player player = event.getPlayer();
/* 113 */     if ((!event.getPlayer().hasPermission( this.worldPermission)) && (SNConfigHandler.multiworld))
/*     */     {
/* 115 */       return;
/*     */     }
/* 117 */     SuperNPlayer snplayer = SuperNManager.get(player);
/* 118 */     if (!SNWhitelistHandler.isWhitelisted(SuperNManager.get(player))) {
/* 119 */       SuperNManager.sendMessage(snplayer, Language.CLASS_RESET.toString());
/*     */     }
/*     */     
/*     */ 
/* 123 */     if (player.hasPermission( "supernatural.admin.infinitepower"))
/*     */     {
/* 125 */       snplayer.setPower(10000 );
/*     */     }
/*     */     
/* 128 */     if (!SNConfigHandler.enableLoginMessage) {
/* 129 */       return;
/*     */     }
/* 131 */     boolean vanished = false;
/* 132 */     for (Player onePlayer : this.plugin.getServer().getOnlinePlayers()) {
/* 133 */       if ((onePlayer != player) && 
/* 134 */         (!onePlayer.canSee(player))) {
/* 135 */         vanished = true;
/*     */       }
/*     */     }
/*     */     

/* 140 */     if (vanished) {
/* 141 */       return;
/*     */     }
/*     */     
/* 144 */     if (SNConfigHandler.enableColors) {
/* 145 */       if (snplayer.isHuman()) {
/* 146 */         player.setDisplayName(player.getDisplayName().trim().replace(player.getName(), Color.HUMAN.name(player.getName())));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 151 */         this.plugin.getServer().broadcastMessage(Color.HUMAN.name(Language.SN_HUMAN_NAME) + Language.CLASS_JOIN_SERVER.toString().replace(LanguageTag.PLAYER.toString(), player.getName()));
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 156 */       else if (snplayer.isVampire()) {
/* 157 */         player.setDisplayName(player.getDisplayName().trim().replace(player.getName(), Color.VAMPIRE.name(player.getName())));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 162 */         this.plugin.getServer().broadcastMessage(Color.VAMPIRE.name(Language.SN_VAMPIRE_NAME) + Language.CLASS_JOIN_SERVER.toString().replace(LanguageTag.PLAYER.toString(), player.getName()));
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 167 */       else if (snplayer.isWere()) {
/* 168 */         player.setDisplayName(player.getDisplayName().trim().replace(player.getName(), Color.WEREWOLF.name(player.getName())));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 173 */         this.plugin.getServer().broadcastMessage(Color.WEREWOLF.name(Language.SN_WEREWOLF_NAME) + Language.CLASS_JOIN_SERVER.toString().replace(LanguageTag.PLAYER.toString(), player.getName()));
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 178 */       else if (snplayer.isGhoul()) {
/* 179 */         player.setDisplayName(player.getDisplayName().trim().replace(player.getName(), Color.GHOUL.name(player.getName())));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 184 */         this.plugin.getServer().broadcastMessage(Color.GHOUL.name(Language.SN_GHOUL_NAME) + Language.CLASS_JOIN_SERVER.toString().replace(LanguageTag.PLAYER.toString(), player.getName()));
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 189 */       else if (snplayer.isPriest()) {
/* 190 */         player.setDisplayName(player.getDisplayName().trim().replace(player.getName(), Color.PRIEST.name(player.getName())));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 195 */         this.plugin.getServer().broadcastMessage(Color.PRIEST.name(Language.SN_PREIEST_NAME) + Language.CLASS_JOIN_SERVER.toString().replace(LanguageTag.PLAYER.toString(), player.getName()));
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 200 */       else if (snplayer.isHunter())
/*     */       {
/* 202 */         player.setDisplayName(player.getDisplayName().trim().replace(player.getName(), Color.WITCHHUNTER.name(player.getName())));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 207 */         this.plugin.getServer().broadcastMessage(Color.WITCHHUNTER.name(Language.SN_WITCHHUNTER_NAME) + Language.CLASS_JOIN_SERVER.toString().replace(LanguageTag.PLAYER.toString(), player.getName()));
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 212 */       else if (snplayer.isDemon()) {
/* 213 */         player.setDisplayName(player.getDisplayName().trim().replace(player.getName(), Color.DEMON.name(player.getName())));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 218 */         this.plugin.getServer().broadcastMessage(Color.DEMON.name(Language.SN_DEMON_NAME) + Language.CLASS_JOIN_SERVER.toString().replace(LanguageTag.PLAYER.toString(), player.getName()));
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 223 */       else if (snplayer.isAngel()) {
/* 224 */         player.setDisplayName(player.getDisplayName().trim().replace(player.getName(), Color.ANGEL.name(player.getName())));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 229 */         this.plugin.getServer().broadcastMessage(Color.ANGEL.name(Language.SN_ANGEL_NAME) + Language.CLASS_JOIN_SERVER.toString().replace(LanguageTag.PLAYER.toString(), player.getName()));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\listeners\SNPlayerMonitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */