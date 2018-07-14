package git.JackWisdom.mcp.supernaturals;

import git.JackWisdom.mcp.supernaturals.manager.ClassManager;
import static git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin.instance;
import java.util.HashMap;

public enum SuperType implements UsingData {
    HUMAN(Type.Normal,humans,instance.getHumanManager()), ANGLE(Type.Super,angels,instance.getAngelManager()),
    VAMPIRE(Type.Super,vampires,instance.getVampireManager()), PRIEST(Type.Super,priests,instance.getPriestManager()),
    WEREWOLF(Type.Super,werewolves,instance.getWereManager())  , GHOUL(Type.Demon,ghouls,instance.getGhoulManager())
    , WITCHHUNTER(Type.Super,hunters,instance.getHunterManager()), DEMON(Type.Demon,demons,instance.getDemonManager());

    //是否是超能力者
    //食尸鬼和恶魔不算

    Type type;
    HashMap belong;
    ClassManager manager;
    SuperType(Type type, HashMap belong, ClassManager manager){
this.type=type;
this.belong=belong;
this.manager=manager;

    }
    public boolean isSuper(){
        return type==Type.Super;
    }
    public boolean isDemon(){
        return type==Type.Demon;
    }
    public boolean isHuman(){
        return type==Type.Normal;
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
enum Type{
    Super,Normal,Demon;
}