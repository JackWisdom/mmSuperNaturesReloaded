/*    */ package git.JackWisdom.mcp.supernaturals.io;
/*    */ 
/*    */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*    */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.FileReader;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.logging.Level;
/*    */ import org.yaml.snakeyaml.DumperOptions;
/*    */ import org.yaml.snakeyaml.TypeDescription;
/*    */ import org.yaml.snakeyaml.Yaml;
/*    */ import org.yaml.snakeyaml.constructor.Constructor;
/*    */ import org.yaml.snakeyaml.nodes.Tag;
/*    */ import org.yaml.snakeyaml.representer.Representer;
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
/*    */ public class SNPlayerHandler
/*    */ {
/*    */   public static List<SuperNPlayer> load(File file)
/*    */   {
/* 45 */     Constructor constructor = new Constructor();
/* 46 */     constructor.addTypeDescription(new TypeDescription(SuperNPlayer.class, new Tag("player")));
/*    */     
/*    */ 
/* 49 */     Yaml yaml = new Yaml(constructor);
/*    */     try
/*    */     {
/* 52 */       List<SuperNPlayer> supernaturals = (List)yaml.load(new FileReader(file));
/*    */       
/*    */ 
/* 55 */       if (supernaturals == null) {
/* 56 */         return new ArrayList();
/*    */       }
/*    */       
/* 59 */       return supernaturals;
/*    */     } catch (FileNotFoundException e) {
/* 61 */       SupernaturalsPlugin.log(Level.WARNING, "Player data not found!"); }
/* 62 */     return null;
/*    */   }
/*    */   
/*    */   public static void save(List<SuperNPlayer> supernaturals, File file)
/*    */   {
/* 67 */     Representer representer = new Representer();
/* 68 */     representer.addClassTag(SuperNPlayer.class, new Tag("player"));
/*    */     
/* 70 */     DumperOptions options = new DumperOptions();
/* 71 */     options.setWidth(300);
/* 72 */     options.setIndent(4);
/*    */     
/* 74 */     Yaml yaml = new Yaml(representer, options);
/*    */     try
/*    */     {
/* 77 */       yaml.dump(supernaturals, new FileWriter(file));
/*    */     } catch (IOException e) {
/* 79 */       SupernaturalsPlugin.log(Level.WARNING, "Player data could not be written!");
/*    */       
/* 81 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\io\SNPlayerHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */