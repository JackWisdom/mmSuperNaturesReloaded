package git.JackWisdom.mcp.supernaturals;


import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.Hash;

import java.util.HashMap;

import java.util.UUID;

public interface UsingData {
    public static final   HashMap<UUID,SuperNPlayer> superpowers=new HashMap<>();
      //superPowers是所有玩家
      public static final HashMap<UUID,Integer> truceBreak=new HashMap<>();
      public static final HashMap<UUID,SuperNPlayer> vampires = new HashMap<>();
      public static final HashMap<UUID,SuperNPlayer> werewolves = new HashMap<>();
      public static final HashMap<UUID,SuperNPlayer> ghouls = new HashMap<>();
      public static final  HashMap<UUID,SuperNPlayer> priests = new HashMap<>();
      public static final HashMap<UUID,SuperNPlayer> hunters = new HashMap<>();
      public static final HashMap<UUID,SuperNPlayer> demons = new HashMap<>();
      public static final  HashMap<UUID,SuperNPlayer> angels = new HashMap<>();
      public static final  HashMap<UUID,SuperNPlayer> humans = new HashMap<>();
  public static final  HashMap<UUID,SuperNPlayer> mermaids = new HashMap<>();
}
