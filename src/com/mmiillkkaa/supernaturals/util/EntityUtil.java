/*    */ package com.mmiillkkaa.supernaturals.util;
/*    */ 
/*    */ import org.bukkit.entity.Creature;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
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
/*    */ public class EntityUtil
/*    */ {
/*    */   public static EntityType entityTypeFromEntity(Entity entity)
/*    */   {
/* 29 */     if (!(entity instanceof Creature)) {
/* 30 */       return null;
/*    */     }
/*    */     
/* 33 */     String name = entity.getClass().getSimpleName();
/* 34 */     name = name.substring(5);
/*    */     
/* 36 */     return EntityType.fromName(name);
/*    */   }
/*    */ }


