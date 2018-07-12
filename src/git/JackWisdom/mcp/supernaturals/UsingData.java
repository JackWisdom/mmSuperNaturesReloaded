package git.JackWisdom.mcp.supernaturals;


import java.util.HashMap;

import java.util.UUID;

public interface UsingData {
      HashMap<UUID,SuperNPlayer> superpowers=new HashMap<>();
      //superPowers是所有玩家
      HashMap<UUID,SuperNPlayer> vampires = new HashMap<>();
      HashMap<UUID,SuperNPlayer> werewolves = new HashMap<>();
      HashMap<UUID,SuperNPlayer> ghouls = new HashMap<>();
      HashMap<UUID,SuperNPlayer> priests = new HashMap<>();
      HashMap<UUID,SuperNPlayer> hunters = new HashMap<>();
      HashMap<UUID,SuperNPlayer> demons = new HashMap<>();
      HashMap<UUID,SuperNPlayer> angels = new HashMap<>();

}
