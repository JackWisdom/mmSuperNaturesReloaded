/*    */ package com.mmiillkkaa.supernaturals.util;
/*    */ 
/*    */ import org.bukkit.ChatColor;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum Color
/*    */ {
/*  9 */   HUMAN("&f"), 
/* 10 */   VAMPIRE("&5"), 
/* 11 */   WEREWOLF("&9"), 
/* 12 */   GHOUL("&8"), 
/* 13 */   PRIEST("&6"), 
/* 14 */   WITCHHUNTER("&a"), 
/* 15 */   DEMON("&c"), 
/* 16 */   ANGEL("&b");
/*    */   
/*    */   private String code;
/*    */   
/*    */   private Color(String code)
/*    */   {
/* 22 */     this.code = code;
/*    */   }
/*    */   
/*    */   public String prefix(String name) {
/* 26 */     return ChatColor.translateAlternateColorCodes('&', this.code + name);
/*    */   }
/*    */   
/*    */   public String prefix(Language lang) {
/* 30 */     return ChatColor.translateAlternateColorCodes('&', this.code + lang.toString());
/*    */   }
/*    */   
/*    */   public String name(String name) {
/* 34 */     return ChatColor.translateAlternateColorCodes('&', this.code + name.toString() + "&f");
/*    */   }
/*    */   
/*    */   public String name(Language lang) {
/* 38 */     return ChatColor.translateAlternateColorCodes('&', this.code + lang.toString() + "&f");
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\util\Color.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */