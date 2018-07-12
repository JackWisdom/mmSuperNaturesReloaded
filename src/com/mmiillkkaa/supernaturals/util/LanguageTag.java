/*    */ package com.mmiillkkaa.supernaturals.util;
/*    */ 
/*    */ public enum LanguageTag
/*    */ {
/*  5 */   AMOUNT("<AMOUNT>"), 
/*  6 */   CLASS("<CLASS>"), 
/*  7 */   CMD("<CMD>"), 
/*  8 */   DELTA("<DELTA>"), 
/*  9 */   POWER("<POWER>"), 
/* 10 */   PLAYER("<PLAYER>"), 
/* 11 */   REASON("<REASON>"), 
/* 12 */   TYPE("<TYPE>"), 
/* 13 */   MATERIAL("<MATERIAL>"), 
/* 14 */   MATERIAL_SURROUND("<MATERIAL_SURROUND>"), 
/* 15 */   MSG("MSG");
/*    */   
/*    */   private String tag;
/*    */   
/*    */   private LanguageTag(String tag)
/*    */   {
/* 21 */     this.tag = tag;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 25 */     return this.tag;
/*    */   }
/*    */ }


