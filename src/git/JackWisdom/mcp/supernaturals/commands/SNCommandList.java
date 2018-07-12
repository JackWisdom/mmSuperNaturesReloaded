/*     */ package git.JackWisdom.mcp.supernaturals.commands;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*     */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */ import git.JackWisdom.mcp.supernaturals.UsingData;
import git.JackWisdom.mcp.supernaturals.manager.SuperNManager;
/*     */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*     */ import git.JackWisdom.mcp.supernaturals.util.TextUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.entity.Player;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SNCommandList
/*     */   extends SNCommand implements UsingData
/*     */ {
/*     */   public SNCommandList()
/*     */   {
/*  37 */     this.requiredParameters = new ArrayList();
/*  38 */     this.optionalParameters = new ArrayList();
/*  39 */     this.senderMustBePlayer = false;
/*  40 */     this.permissions = "supernatural.command.list";
/*  41 */     this.helpNameAndParams = "list";
/*  42 */     this.helpDescription = "查看服务器上的所有超自然生物.";
/*     */   }
/*     */   
/*     */   public void perform()
/*     */   {

/*  74 */     List<String> messages = new ArrayList();
/*  75 */     messages.add(String.format("*** %s ***", new Object[] { Language.ONLINE_PLAYERS }));
/*  76 */     messages.add(String.format("%s: %s%s", new Object[] { Language.SN_VAMPIRE_NAME, ChatColor.WHITE, TextUtil.implode(vampires.values(), ", ") }));
/*     */     
/*  78 */     messages.add(String.format("%s: %s%s", new Object[] { Language.SN_WEREWOLF_NAME, ChatColor.WHITE, TextUtil.implode(werewolves.values(), ", ") }));
/*     */     
/*  80 */     messages.add(String.format("%s: %s%s", new Object[] { Language.SN_GHOUL_NAME, ChatColor.WHITE, TextUtil.implode(ghouls.values(), ", ") }));
/*     */     
/*  82 */     messages.add(String.format("%s: %s%s", new Object[] { Language.SN_PREIEST_NAME, ChatColor.WHITE, TextUtil.implode(priests.values(), ", ") }));
/*     */     
/*  84 */     messages.add(String.format("%s: %s%s", new Object[] { Language.SN_WITCHHUNTER_NAME, ChatColor.WHITE, TextUtil.implode(hunters.values(), ", ") }));
/*     */     
/*  86 */     messages.add(String.format("%s: %s%s", new Object[] { Language.SN_DEMON_NAME, ChatColor.WHITE, TextUtil.implode(demons.values(), ", ") }));
/*     */     
/*  88 */     messages.add(String.format("%s: %s%s", new Object[] { Language.SN_ANGEL_NAME, ChatColor.WHITE, TextUtil.implode(angels.values(), ", ") }));
/*     */     
/*     */ 
/*  91 */     if (!(this.sender instanceof Player))
/*     */     {
/*  93 */       sendMessage(messages);
/*  94 */       return;
/*     */     }
/*     */     
/*  97 */     Player senderPlayer = (Player)this.sender;
/*  98 */     if (!senderPlayer.hasPermission( this.permissions)) {
/*  99 */       sendMessage(Language.NO_PREMISSION.toString());
/* 100 */       return;
/*     */     }
/*     */     
/*     */ 
/* 104 */     sendMessage(messages);
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\commands\SNCommandList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */