package git.JackWisdom.mcp.supernaturals;

import git.JackWisdom.mcp.supernaturals.manager.ClassManager;
import git.JackWisdom.mcp.supernaturals.util.Language;

import static git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin.instance;
import java.util.HashMap;

public enum SuperType implements UsingData {
    HUMAN(humans,instance.getHumanManager()), ANGEL(angels,instance.getAngelManager()),
    VAMPIRE(vampires,instance.getVampireManager()), PRIEST(priests,instance.getPriestManager()),
    WEREWOLF(werewolves,instance.getWereManager())  , GHOUL(ghouls,instance.getGhoulManager())
    , WITCHHUNTER(hunters,instance.getHunterManager()), DEMON(demons,instance.getDemonManager());

    //是否是超能力者
    //食尸鬼和恶魔不算

    HashMap belong;
    ClassManager manager;
    SuperType(HashMap belong, ClassManager manager){
this.belong=belong;
this.manager=manager;

    }
    public boolean isSuper(){
        return this==VAMPIRE||this== ANGEL ||this==WEREWOLF||this==WITCHHUNTER;
    }
    public boolean isDemon(){
        return this==VAMPIRE||this==GHOUL||this==DEMON;
    }
    public String getLocalizedName(){

        return Language.valueOf("SN_"+name()+"_NAME").toString();
    }
    public ClassManager getManager(){
        return manager;
    }
    public HashMap getBelong(){
        return belong;
    }
    public boolean hasTruce(){
        return this==WEREWOLF||this==GHOUL||this==VAMPIRE;
    }
}
