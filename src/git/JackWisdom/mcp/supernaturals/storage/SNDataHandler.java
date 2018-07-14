/*     */ package git.JackWisdom.mcp.supernaturals.storage;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SuperNPlayer;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ import java.util.UUID;

/*     */ import git.JackWisdom.mcp.supernaturals.UsingData;
/*     */
/*     */ 

/*     */ public abstract class SNDataHandler implements UsingData
/*     */
/*     */ {

            public abstract SuperNPlayer load(UUID uuid);
            public abstract void save(SuperNPlayer player);

/*     */   public boolean hasAngel(SuperNPlayer snplayer)
/*     */   {
            for(SuperNPlayer p:priests.values()){
                if(p.getProtecting().equals(snplayer.getUuid())){
                    return true;
                }
            }
            return false;
/*     */   }
/*     */
/*     */   public void removeAngel(SuperNPlayer snplayer) {
              for(SuperNPlayer p:priests.values()){
                  if(p.getProtecting()==null){
                      return;
                  }
              if(p.getProtecting().equals(snplayer.getUuid())){
                    p.setProtecting(null);
               }
    }
/*     */   }

/*     */   public void addAngel(SuperNPlayer snplayer, SuperNPlayer sntarget) {
/* 127 */    snplayer.setProtecting(sntarget.getUuid());
/*     */   }

/*     */ }


