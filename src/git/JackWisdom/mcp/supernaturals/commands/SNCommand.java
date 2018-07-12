/*     */ package git.JackWisdom.mcp.supernaturals.commands;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.util.Language;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandSender;
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
/*     */ 
/*     */ public class SNCommand
/*     */ {
/*     */   public List<String> requiredParameters;
/*     */   public List<String> optionalParameters;
/*     */   public String permissions;
/*     */   public String helpNameAndParams;
/*     */   public String helpDescription;
/*     */   public boolean senderMustBePlayer;
/*     */   public boolean senderMustBeSupernatural;
/*     */   public CommandSender sender;
/*     */   public List<String> parameters;
/*     */   
/*     */   public SNCommand()
/*     */   {
/*  44 */     this.requiredParameters = new ArrayList();
/*  45 */     this.optionalParameters = new ArrayList();
/*  46 */     this.permissions = "";
/*  47 */     this.senderMustBePlayer = false;
/*  48 */     this.senderMustBeSupernatural = false;
/*  49 */     this.helpNameAndParams = "fail!";
/*  50 */     this.helpDescription = "no description";
/*     */   }
/*     */   
/*     */   public String getName() {
/*  54 */     String name = getClass().getName().toLowerCase();
/*  55 */     if (name.lastIndexOf('.') > 0) {
/*  56 */       name = name.substring(name.lastIndexOf('.') + 1);
/*     */     }
/*  58 */     return name.substring(9);
/*     */   }
/*     */   
/*     */   public String getBaseName() {
/*  62 */     return "sn";
/*     */   }
/*     */   
/*     */   public void execute(CommandSender sender, List<String> parameters) {
/*  66 */     this.sender = sender;
/*  67 */     this.parameters = parameters;
/*     */     
/*  69 */     if (!validateCall()) {
/*  70 */       sendMessage(Language.TRY_HELP.toString());
/*  71 */       return;
/*     */     }
/*     */     
/*  74 */     perform();
/*     */   }
/*     */   
/*     */ 
/*     */   public void perform() {}
/*     */   
/*     */   public void sendMessage(String message)
/*     */   {
/*  82 */     this.sender.sendMessage(ChatColor.RED + message);
/*     */   }
/*     */   
/*     */   public void sendMessage(List<String> messages) {
/*  86 */     for (String message : messages) {
/*  87 */       sendMessage(message);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean validateCall()
/*     */   {
/*  94 */     if ((this.senderMustBePlayer) && (!(this.sender instanceof Player))) {
/*  95 */       sendMessage(Language.ONLY_IN_GAME.toString());
/*  96 */       return false;
/*     */     }
/*     */     
/*  99 */     if (this.parameters.size() < this.requiredParameters.size()) {
/* 100 */       int missing = this.requiredParameters.size() - this.parameters.size();
/* 101 */       sendMessage(Language.MISSING_PARAMETERS.toString().replace("<AMOUNT>", Integer.toString(missing)));
/*     */       
/* 103 */       return false;
/*     */     }
/*     */     
/* 106 */     if (this.parameters.size() > this.requiredParameters.size() + this.optionalParameters.size())
/*     */     {
/* 108 */       sendMessage(Language.TOO_MANY_PARAMETERS.toString());
/* 109 */       return false;
/*     */     }
/*     */     
/* 112 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\jackw\Desktop\mmSupernaturals for 1.7.2.jar!\com\mmiillkkaa\supernaturals\commands\SNCommand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */