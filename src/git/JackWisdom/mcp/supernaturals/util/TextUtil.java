/*    */ package git.JackWisdom.mcp.supernaturals.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
import java.util.List;
/*    */ import git.JackWisdom.mcp.supernaturals.SuperType;
import org.bukkit.Material;
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
   public static String implode(Collection list, String glue) {
     StringBuilder sb=new StringBuilder();
     for(Object obj:list){
         sb.append(obj.toString());
         sb.append(glue);
     }
     return sb.toString();
 }

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

