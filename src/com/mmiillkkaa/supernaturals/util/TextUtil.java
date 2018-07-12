/*    */ package com.mmiillkkaa.supernaturals.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import org.bukkit.Material;
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
/*    */ public class TextUtil
/*    */ {
/*    */   public static String repeat(String s, int times)
/*    */   {
/* 30 */     if (times <= 0) {
/* 31 */       return "";
/*    */     }
/* 33 */     return s + repeat(s, times - 1);
/*    */   }
/*    */   
/*    */   public static ArrayList<String> split(String str)
/*    */   {
/* 38 */     return new ArrayList(Arrays.asList(str.trim().split("\\s+")));
/*    */   }
/*    */   
/*    */   public static String implode(List<String> list, String glue) {
/* 42 */     String ret = "";
/* 43 */     for (int i = 0; i < list.size(); i++) {
/* 44 */       if (i != 0) {
/* 45 */         ret = ret + glue;
/*    */       }
/* 47 */       ret = ret + (String)list.get(i);
/*    */     }
/* 49 */     return ret;
/*    */   }
/*    */   
/*    */   public static String implode(List<String> list) {
/* 53 */     return implode(list, " ");
/*    */   }
/*    */   
/*    */   public static String getMaterialName(Material material) {
/* 57 */     String ret = material.toString();
/* 58 */     ret = ret.replace('_', ' ');
/* 59 */     ret = ret.toLowerCase();
/* 60 */     return ret;
/*    */   }
/*    */   
/*    */   public static String upperCaseFirst(String string) {
/* 64 */     return string.substring(0, 1).toUpperCase() + string.substring(1);
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\util\TextUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */