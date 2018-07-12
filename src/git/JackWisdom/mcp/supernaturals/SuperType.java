package git.JackWisdom.mcp.supernaturals;

public enum SuperType {
    HUMAN(Type.Normal), ANGLE(Type.Super), VAMPIRE(Type.Super), PRIEST(Type.Super), WEREWOLF(Type.Super), GHOUL(Type.Demon)
    , WITCHHUNTER(Type.Super), DEMON(Type.Demon);

    //是否是超能力者
    //食尸鬼和恶魔不算
    Type type;
    SuperType(Type type){
this.type=type;
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

}
enum Type{
    Super,Normal,Demon;
}