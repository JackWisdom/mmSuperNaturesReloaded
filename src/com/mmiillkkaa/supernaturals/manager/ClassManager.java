/*    */ package com.mmiillkkaa.supernaturals.manager;
/*    */ 
/*    */ import com.mmiillkkaa.supernaturals.SuperNPlayer;
/*    */ import com.mmiillkkaa.supernaturals.util.Language;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.event.entity.EntityShootBowEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ClassManager
/*    */ {
/*    */   public double damagerEvent(EntityDamageByEntityEvent event, double damage)
/*    */   {
/* 39 */     return damage;
/*    */   }
/*    */   
/*    */   public boolean shootArrow(Player shooter, EntityShootBowEvent event) {
/* 43 */     return false;
/*    */   }
/*    */   
/*    */   public void spellEvent(EntityDamageByEntityEvent event, Player target) {}
/*    */   
/*    */   public double victimEvent(EntityDamageEvent event, double damage)
/*    */   {
/* 50 */     return damage;
/*    */   }
/*    */   
/*    */ 
/*    */   public void deathEvent(Player player) {}
/*    */   
/*    */ 
/*    */   public void killEvent(Player pDamager, SuperNPlayer damager, SuperNPlayer victim) {}
/*    */   
/*    */   public boolean playerInteract(PlayerInteractEvent event)
/*    */   {
/* 61 */     return false;
/*    */   }
/*    */   
/*    */   public void armorCheck(Player player) {}
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


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\manager\ClassManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */