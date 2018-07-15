/*    */ package git.JackWisdom.mcp.supernaturals.manager;
/*    */ 
/*    */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*    */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*    */
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.event.entity.EntityShootBowEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

/*    */ public abstract class ClassManager
/*    */ {
/*    */   public abstract double damagerEvent(EntityDamageByEntityEvent event, double damage);
/*    */   public abstract void waterAdvanceTime(Player player);
/*    */   public abstract boolean shootArrow(Player shooter, EntityShootBowEvent event);
/*    */   
/*    */   public abstract void spellEvent(EntityDamageByEntityEvent event, Player target) ;
/*    */   
/*    */   public abstract double victimEvent(EntityDamageEvent event, double damage);
/*    */   public abstract void eatItem(PlayerItemConsumeEvent event);
/*    */ 
/*    */   public abstract void deathEvent(Player player);
/*    */   
/*    */ 
/*    */   public abstract void killEvent(Player pDamager, SuperNPlayer damager, SuperNPlayer victim);
/*    */   
/*    */   public abstract boolean playerInteract(PlayerInteractEvent event);
/*    */   
/*    */   public abstract void armorCheck(Player player) ;
/*    */
/*    */   public void dropItem(Player player, ItemStack item)
/*    */   {
/* 68 */     if (!player.hasPermission("supernatural.player.ignorearmor")) {
/* 69 */       SuperNPlayer snplayer = SuperNManager.get(player);
/* 70 */       SuperNManager.sendMessage(snplayer, Language.INVALID_ARMOR_TYPE.toString());
/*    */       
/* 72 */       Item newItem = player.getWorld().dropItem(player.getLocation(), item);
/*    */       
/* 74 */       newItem.setItemStack(item);
/*    */     }
/*    */   }
/*    */ }

