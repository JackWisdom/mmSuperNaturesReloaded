/*     */ package git.JackWisdom.mcp.supernaturals.util;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */ import git.JackWisdom.mcp.supernaturals.io.SNLanguageHandler;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ 
/*     */ public enum Language
/*     */ {
            SN_MERMAID_NAME("sn_mermaid_name","Mermaid"),
            GUI_DONATE_TITLE("gui_donate_title","becoming a priest"),

            GUI_VAMCURE_TITLE("gui_vamcure_title","donate to cure"),
            GUI_VAMINFECT_TITLE("gui_vaminfect_title","donate to infect"),
            GUI_EXACTLY("gui_exactly","you must place exactly as the frist line did,your items have been removed for not putting right item"),
/*  10 */   ADMIN_BOOST("admin_boost", "Admin boost!"), 
/*  11 */   ALL_DATA_SAVE("all_data_save", "All config/player data has been saved!"), 
/*  12 */   ALREADY_WHITELISTED("already_whitelisted", "You are already whitelisted!"), 
/*  13 */   ANGEL_CURE_NOTICE_OTHER("angel_cure_notice_other", "&cCured by <PLAYER>"), 
/*  14 */   ANGEL_CURE_NOTICE_SELF("angel_cure_notice_self", "Cured <PLAYER>"), 
/*  15 */   ANGEL_DEATH("angel_death", "Flames have removed your holy powers!"), 
/*  16 */   ANGEL_HEAL_NOTICE_OTHER("angel_heal_notice_other", "&cHealed by <PLAYER>"), 
/*  17 */   ANGEL_HEAL_NOTICE_SELF("angel_heal_notice_self", "Healing <PLAYER>"), 
/*  18 */   ANGEL_LIMIT_TARGET("angel_limit_target", "Angels cannot use diamond swords on animals!"), 
/*  19 */   ANGEL_LIMIT_WEAPON("angel_limit_weapon", "Angels cannot use this weapon!"), 
/*  20 */   ANGEL_SUMMON_COW("angel_summon_cow", "Summoned cow."), 
/*  21 */   ANGEL_SUMMON_PIG("angel_summon_pig", "Summoned pig."),
    ANGEL_SUMMON_SHEEP("angel_summon_sheep", "Summoned sheep."),
    /*  22 */   ANGEL_SUMMON_TOO_MUCH_WOLF("angel_summon_too_much_wolf", "&cToo much wolves, not enough space! (Stop spawning wolves!)"),
/*  23 */   ANGEL_SUMMON_WOLF("angel_summon_wolf", "Summoned wolf."),
            ANGEL_SUMMON_WOLF_NAME("angel_summon_wolf_name","Angel's wolf"),
/*  24 */   ANGEL_SUPERJUMP("angel_superjump", "SuperJump!"), 
/*  25 */   ANGEL_SUPERJUMP_TRIGGER("angel_superjump_trigger", "SuperJump!"), 
/*  26 */   ANGEL_SWIM("angel_swim", "Swimming in water"), 
/*  27 */   BY_ADMIN("by_admin", "Admin"), 
/*  28 */   CLASS_JOIN_SERVER("class_join_server", " <PLAYER> &6has joined the server."), 
/*  29 */   CLASS_RESET("class_reset", "Your class has been reset because you are trying to bypass the mmSupernaturals whitelist!"), 
/*  30 */   CONFIG_RELOAD("config_reload", "Config file has been reloaded"), 
/*  31 */   DAEMON_CONVERT_NOTICE_OTHER("daemon_convert_notice_other", "&cYou have been converted to a demon by <PLAYER>"), 
/*  32 */   DAEMON_CONVERT_NOTICE_OTHER_DESC("daemon_convert_notice_other_desc", "&cHeat builds up in your body..."), 
/*  33 */   DAEMON_CONVERT_NOTICE_SELF("daemon_convert_notice_self", "Converted <PLAYER>"), 
/*  34 */   DAEMON_DEATH("daemon_death", "Your icy death has cooled the infernal fires raging within your body."), 
/*  35 */   DAEMON_FIREBALL_NOTICE_SELF("daemon_fireball_notice_self", "Fireball!"), 
/*  36 */   DAEMON_LIMIT_WEAPON("daemon_limit_weapon", "Demons cannot use this weapon!"), 
/*  37 */   DAEMON_LOVE_LAVA("daemon_love_lava", "Lava!"), 
/*  38 */   DAEMON_ONLY("daemon_only", "Demons Only!"), 
/*  39 */   DAEMON_SNARE_NOTICE_SELF("daemon_snare_notice_self", "Snare!"), 

/*  41 */   FULL_CURE("full_cure", "&f<PLAYER>&c was cured of any curse!"), 
/*  42 */   GHOUL_BOND_NOT_ALLOW("ghoul_bond_not_allow", "You cannot form a bond with a human."), 
/*  43 */   GHOUL_BOND_NOTICE_OTHER("ghoul_bond_notice_other", "You now have an Unholy Bond with <PLAYER>!"), 
/*  44 */   GHOUL_BOND_NOTICE_SELF("ghoul_bond_notice_self", "You now have an Unholy Bond with <PLAYER>"), 
/*  45 */   GHOUL_BOND_REMOVE_NOTICE_OTHER("ghoul_bond_remove_notice_other", "Unholy Bond removed!"), 
/*  46 */   GHOUL_BOND_REMOVE_NOTICE_SELF("ghoul_bond_remove_notice_self", "&cRemoved Unholy Bond from &f<PLAYER>"), 
/*  47 */   GHOUL_BOND_TRIGGER("ghoul_bond_trigger", "Unholy Bond!"), 
/*  48 */   GHOUL_DEATH("ghoul_death", "Your body dies... You feel a deep hatred for the living."), 
/*  49 */   GHOUL_HATE_WATER("ghoul_hate_water", "Ghouls disintegrate in water!  Get Out Quick!"), 
/*  50 */   GHOUL_IMMUNE_WEAPON("ghoul_immune_weapon", "Ghouls are immune to that weapon!"), 
/*  51 */   GHOUL_LIMIT_WEAPON("ghoul_limit_weapon", "Ghouls cannot use this weapon!"), 
/*  52 */   GHOUL_SUMMON_NOT_ALLOW("ghoul_summon_not_allow", "You cannot summon here."), 
/*  53 */   GHOUL_SUMMON_NOTICE_SELF("ghoul_summon_notice_self", "Summoning a Zombie!"), 
/*  54 */   HEALTH_REGEN("health_regen", "Healing!"), 
/*  55 */   HUMAN_TO_ANGEL("human_to_angel", "Your spirit is lifted"), 
/*  56 */   HUMAN_TO_DAEMON("human_to_daemon", "Hellfire races through your veins!"), 
/*  57 */   HUMAN_TO_GHOUL("human_to_ghoul", "You have been transformed into a GHOUL!"),
/*  58 */   HUMAN_TO_WEREWOLF("human_to_werewolf", "You have mutated into a werewolf!"), 
/*  59 */   INVALID_ARMOR_TYPE("invalid_armor_type", "Your class cannot wear this type of armor!"), 
/*  60 */   INVALID_NUMBER("invalid_number", "Invalid Number."), 
/*  61 */   INVALID_OPTION("invalid_option", "Invalid option."), 
/*  62 */   INVALID_SUPERNATURAL_TYPE("invalid_supernatural_type", "Supernatural Type invalid!"), 
/*  63 */   JOIN_NOT_ENABLE("join_not_enable", "This is not enabled, you are automatically in the mmSupernaturals fun!"), 
/*  64 */   KILL_CREATURE("kill_creature", "Creature death!"), 
/*  65 */   KILL_MONSTER("kill_monster", "Killed Monster"), 
/*  66 */   KILL_PLAYER("kill_player", "Player killed!"), 
/*  67 */   KILL_SELF("kill_self", "You have killed yourself!"), 
/*  68 */   MISSING_PARAMETERS("missing_parameters", "Missing parameters. You must enter <AMOUNT> more."), 
/*  69 */   MISSING_PLAYER("missing_player", "Missing player!"), 
/*  70 */   NO_POWER("no_power", "Not enough power!"), 
/*  71 */   NO_POWER_GAIN("no_power_gain", "You cannot gain power from a player with no power themselves."), 
/*  72 */   NO_PREMISSION("no_premission", "You don't have premission to use this command."), 
/*  73 */   NO_SUCH_COMMAND("no_such_command", "&cNo such command \"&f<CMD>&c\". Try /sn help"), 
/*  74 */   NOT_ALLOW_PVP("not_allow_pvp", "You can't do this in non-PvP areas"), 
/*  75 */   NOT_SUPERNATURAL("not_supernatural", "Player is not a supernatural!"), 
/*  76 */   NOT_WITCHHUNTER("not_witchhunter", "You are not a WitchHunter!"), 
/*  77 */   ONLINE_PLAYERS("online_players", "&fOnline Supernatural Players&c"), 
/*  78 */   ONLY_IN_GAME("only_in_game", "This command can only be used by ingame players."), 
/*  79 */   ONLY_IN_GAME_COMMAND("only_in_game_command", "This command, sn <CMD>, is player-only"), 
/*  80 */   ONLY_ON_PLAYER("only_on_player", "You can not use this on supernatural!"), 
/*  81 */   ONLY_ON_SUPERNATUTAL("only_on_supernatutal", "You can not use this on player!"), 
/*  82 */   PLAYER_NAME("player_name", "PlayerName"), 
/*  83 */   PLAYER_NOT_FOUND("player_not_found", "Player not found!"), 
/*  84 */   POWER("power", "POWER"), 
/*  85 */   POWER_INFO("power_info", "You are a &f<TYPE>&c and your current power level is: &f<POWER>"), 
/*  86 */   POWER_RESET("power_reset", "Power reset for player: <PLAYER>"), 
/*  87 */   POWER_UP("power_up", "&f<PLAYER>&c has been powered up!"), 
/*  88 */   PRIEST_ALTAR_NOT_ALLOW("priest_altar_not_allow", "You cannot use priest altars."), 
/*  89 */   PRIEST_ALTAR_POWER_HUMAN("priest_altar_power_human", "The Church Altar radiates holy power."), 
/*  90 */   PRIEST_ALTAR_POWER_SUPERNATURAL("priest_altar_power_supernatural", "The holy power of the Church tears you asunder!"), 
/*  91 */   PRIEST_BANISH_NOTICE_OTHER("priest_banish_notice_other", "You were banished by &f<PLAYER>&c!"), 
/*  92 */   PRIEST_BANISH_NOTICE_SELF("priest_banish_notice_self", "Banished <PLAYER>"), 
/*  93 */   PRIEST_CURE_NOT_HOLD("priest_cure_not_hold", "&f<PLAYER>&c is not holding &f<MATERIAL>&c."), 
/*  94 */   PRIEST_CURE_NOTICE_OTHER("priest_cure_notice_other", "&f<PLAYER>&c has restored your humanity"), 
/*  95 */   PRIEST_CURE_NOTICE_SELF("priest_cure_notice_self", "Cured <PLAYER>"), 
/*  96 */   PRIEST_DONATE_ACCEPT("priest_donate_accept", "The Church accepts your gracious donations of Bread, Fish, Grilled Pork and Apples."), 
/*  97 */   PRIEST_DONATE_CONFIRM("priest_donate_confirm", "You donate these items to the Church"),
/*  98 */   PRIEST_DONATE_ENOUGHT("priest_donate_enought", "The Church recognizes your holy spirit and accepts you into the priesthood."), 
/*  99 */   PRIEST_DONATE_NOT_ENOGHT("priest_donate_not_enoght", "The Church judges your intended donate insufficient. You must place the items exactly as the frist line "),
/* 100 */   PRIEST_DONATE_NOTICE_SELF("priest_donate_notice_self", "Donations!"), 
/* 101 */   PRIEST_DONATE_ONLY("priest_donate_only", "The Church only accepts donations of Bread, Fish, Grilled Pork and Apples.Your items have been taken for your rudeness"),
/* 102 */   PRIEST_DONATE_REWARD("priest_donate_reward", "You receive some power for your remote donations."), 
/* 103 */   PRIEST_DRAIN_NOTICE_OTHER("priest_drain_notice_other", "Drained by <PLAYER>"), 
/* 104 */   PRIEST_DRAIN_NOTICE_SELF("priest_drain_notice_self", "Drained <PLAYER>'s power!'"), 
/* 105 */   PRIEST_EXORISE_NOTICE_OTHER("priest_exorise_notice_other", "You were exorcised by &f<PLAYER>&c!"), 
/* 106 */   PRIEST_EXORISE_NOTICE_SELF("priest_exorise_notice_self", "Exorcised <PLAYER>"), 
/* 107 */   PRIEST_GUARDANGEL_NOTICE_OTHER("priest_guardangel_notice_other", "You now have a Guardian Angel!"), 
/* 108 */   PRIEST_GUARDANGEL_NOTICE_SELF("priest_guardangel_notice_self", "Guardian Angel on &f<PLAYER>&c!"), 
/* 109 */   PRIEST_GUARDANGEL_REMOVE_NOTICE_OTHER("priest_guardangel_remove_notice_other", "Guardian Angel removed!"), 
/* 110 */   PRIEST_GUARDANGEL_REMOVE_NOTICE_SELF("priest_guardangel_remove_notice_self", "Removed Guardian Angel from &f<PLAYER>"), 
/* 111 */   PRIEST_GUARDANGEL_TRIGGER("priest_guardangel_trigger", "Guardian Angel used!"), 
/* 112 */   PRIEST_HEAL_NOTICE_OTHER("priest_heal_notice_other", "You were healed by &f<PLAYER>&c!"), 
/* 113 */   PRIEST_HEAL_NOTICE_SELF("priest_heal_notice_self", "Healed <PLAYER>"), 
/* 114 */   PRIEST_LEAVE_CRUSH("priest_leave_crush", "The Church excommunicates you!"), 
/* 115 */   PRIEST_LIMIT_TARGET("priest_limit_target", "You cannot hurt innocent animals."), 
/* 116 */   PRIEST_LIMIT_WEAPON("priest_limit_weapon", "Priests cannot use this weapon!"), 
/* 117 */   SET_BANISH("set_banish", "Banish location set."), 
/* 118 */   SET_CHRUSH("set_chrush", "Church location set."), 
/* 119 */   SN_ADMIN_CMD_CONVERT("sn_admin_cmd_convert", "Turn self or player into any supernatural."), 
/* 120 */   SN_ADMIN_CMD_CONVERT_NOTICE("sn_admin_cmd_convert_notice", "You are now a &f<TYPE>&c!"), 
/* 121 */   SN_ADMIN_CMD_CURE("sn_admin_cmd_cure", "Cure self or player."), 
/* 122 */   SN_ADMIN_CMD_CURE_NOTICE("sn_admin_cmd_cure_notice", "You have been restored to humanity!"), 
/* 123 */   SN_ADMIN_CMD_POWER("sn_admin_cmd_power", "Give power to self or player."), 
/* 124 */   SN_ADMIN_CMD_POWER_NOTICE("sn_admin_cmd_power_notice", "Power: &f<AMOUNT>&c(&f<DELTA>&c) <REASON>"), 
/* 125 */   SN_ADMIN_CMD_RELOAD("sn_admin_cmd_reload", "Reload data from disk."), 
/* 126 */   SN_ADMIN_CMD_RESET("sn_admin_cmd_reset", "Reset self or player's power."), 
/* 127 */   SN_ADMIN_CMD_RESTARTTASK("sn_admin_cmd_restarttask", "Restarts the Task Timer."), 
/* 128 */   SN_ADMIN_CMD_REVERT_NOTICE("sn_admin_cmd_revert_notice", "You been reverted to your previous state of being a &f<TYPE>&c!"), 
/* 129 */   SN_ADMIN_CMD_RMTARGET("sn_admin_cmd_rmtarget", "Removes player from current WitchHunter kill list."), 
/* 130 */   SN_ADMIN_CMD_SAVE("sn_admin_cmd_save", "Save data to disk."), 
/* 131 */   SN_ADMIN_CMD_SETBANISH("sn_admin_cmd_setbanish", "Sets your current location as the priests' banish spot."), 
/* 132 */   SN_ADMIN_CMD_SETCHRUSH("sn_admin_cmd_setchrush", "Sets your current location as the priests' church."), 
/* 133 */   SN_ADMIN_HELP("sn_admin_help", "&fSupernatural Admin Help&c"), 
/* 134 */   SN_ADMIN_SETUP("sn_admin_setup", "Setup wizard let you quick setup the plugin."), 
/* 135 */   SN_ANGEL_DESC("sn_angel_desc", "A HUMAN with a free spirit."),
/* 136 */   SN_ANGEL_NAME("sn_angel_name", "Angel"), 
/* 137 */   SN_CMD_ADMIN("sn_cmd_admin", "Show list of admin-only commands"), 
/* 138 */   SN_CMD_CLASSES("sn_cmd_classes", "Show the list of available Super-classes."), 
/* 139 */   SN_CMD_JOIN_NO_PREMISSION("sn_cmd_join_no_premission", "You do not have permission to convert to a <TYPE>"), 
/* 140 */   SN_CMD_JOIN_NOT_USE("sn_cmd_join_not_use", "You have not used the \"/sn join\" command!"), 
/* 141 */   SN_CMD_KILLIST("sn_cmd_killist", "Show the list of current WitchHunter targets."), 
/* 142 */   SN_CMD_LIST("sn_cmd_list", "List supernaturals on the server."), 
/* 143 */   SN_CMD_POWER("sn_cmd_power", "Show current power level."), 
/* 144 */   SN_DEMON_DESC("sn_demon_desc", "Possesses an unholy union with fire."), 
/* 145 */   SN_DEMON_NAME("sn_demon_name", "DEMON"),
/* 146 */   SN_GHOUL_DESC("sn_ghoul_desc", "Slow and very durable."), 
/* 147 */   SN_GHOUL_NAME("sn_ghoul_name", "GHOUL"),
/* 148 */   SN_HUMAN_DESC("sn_human_desc", "Your standard run of the mill person."), 
/* 149 */   SN_HUMAN_NAME("sn_human_name", "HUMAN"),
/* 150 */   SN_PREIEST_NAME("sn_preiest_name", "Preiest"), 
/* 151 */   SN_PRIEST_DESC("sn_priest_desc", "A person with significant power over the unholy."), 
/* 152 */   SN_SETUP_CHRUSH_CMD("sn_setup_chrush_cmd", "Use the command /sn setChurch to allow players to use the altar."), 
/* 153 */   SN_SETUP_CHRUSH_USAGE("sn_setup_chrush_usage", "Players can use the altar by right-clicking the <MATERIAL>"), 
/* 154 */   SN_SETUP_CHURCH_ALTAR("sn_setup_church_altar", "To do this, make an altar, and put a <MATERIAL> in the center."), 
/* 155 */   SN_SETUP_CHURCH_GREETING("sn_setup_church_greeting", "First, we will make a Church."), 
/* 156 */   SN_SETUP_CHURSH_WITHIN_BLOCK("sn_setup_chursh_within_block", "Within 10 blocks of your <MATERIAL> in the altar,"), 
/* 157 */   SN_SETUP_CONTINUE("sn_setup_continue", "To continue, type /sn setup again."), 
/* 158 */   SN_SETUP_FINISH("sn_setup_finish", "Setup is finished! Congratulations!"), 
/* 159 */   SN_SETUP_GREETING("sn_setup_greeting", "Hey there! This command will help you setup mmSupernaturals!"), 
/* 160 */   SN_SETUP_HALL_GREETING("sn_setup_hall_greeting", "Okay, next, we have to make a WitchHunter hall."), 
/* 161 */   SN_SETUP_HALL_SIGN("sn_setup_hall_sign", "Next to an Iron Door, place a sign with <MSG> on the 2nd line."), 
/* 162 */   SN_VAMPIRE_DESC("sn_vampire_desc", "No they don't sparkle!"), 
/* 163 */   SN_VAMPIRE_NAME("sn_vampire_name", "VAMPIRE_I"),
/* 164 */   SN_WEREWOLF_DESC("sn_werewolf_desc", "Gain significant powers at night."), 
/* 165 */   SN_WEREWOLF_NAME("sn_werewolf_name", "WEREWOLF"),
/* 166 */   SN_WITCHHUNTER_DESC("sn_witchhunter_desc", "Expert at bows and stealth."), 
/* 167 */   SN_WITCHHUNTER_NAME("sn_witchhunter_name", "WitchHunter"), 
/* 168 */   SUPERNATURAL_CLASS("supernatural_class", "&fSupernatural Classes&c"), 
/* 169 */   SUPERNATURAL_HELP("supernatural_help", "&fSupernatural Help&c"), 
/* 170 */   SUPERNATURAL_TYPE("supernatural_type", "SupernaturalType"), 
/* 171 */   TAGEST_CURRENT("tagest_current", "&fCurrent WitchHunter Targets&c"), 
/* 172 */   TARGET_NOT_ACTIVE_NOTICE_OTHER("target_not_active_notice_other", "You are not an active target."), 
/* 173 */   TARGET_NOT_ACTIVE_NOTICE_SELF("target_not_active_notice_self", "&f<PLAYER>&c is not an active target."), 
/* 174 */   TARGET_REMOVE_NOTICE_OTHER("target_remove_notice_other", "You were removed from the target list!"), 
/* 175 */   TARGET_REMOVE_NOTICE_SELF("target_remove_notice_self", "&f<PLAYER>&c was removed from the target list!"), 
/* 176 */   TASK_TIMER_RESTART("task_timer_restart", "Task Timer has been restarted."), 
/* 177 */   TOO_MANY_PARAMETERS("too_many_parameters", "Too many parameters."), 
/* 178 */   TRUCE_BREAK("truce_break", "You temporarily broke your truce with monsters!"), 
/* 179 */   TRUCE_RESTORE("truce_restore", "Your truce with monsters has been restored!"), 
/* 180 */   TRY_HELP("try_help", "Try /sn help"), 
/* 181 */   TYPE_ALREADY("type_already", "&f<PLAYER>&c is already a &f<TYPE>&c !"), 
/* 182 */   TYPE_TURN("type_turn", "&f<PLAYER>&c was turned into a &f<TYPE>&c !"), 
/* 183 */   TYPE_TURN_BACK("type_turn_back", "&f<PLAYER>&c was turned BACK into a &f<TYPE>&c !"), 
/* 184 */   UNKNOW_COMMAND("unknow_command", "&cUnknown command. Try /sn help"), 
/* 185 */   VAMPIRE_ALTAR_CONFIRM("vampire_altar_confirm", "You use these items on the altar"),
/* 186 */   VAMPIRE_ALTAR_CURE_FAIL("vampire_altar_cure_fail", "It can probably cure curses, but you feel fine."), 
/* 187 */   VAMPIRE_ALTAR_CURE_INFO("vampire_altar_cure_info", "This altar looks pure and clean."), 
/* 188 */   VAMPIRE_ALTAR_CURE_NOT_ENOUGH("vampire_altar_cure_not_enough", "Something happens... The <MATERIAL> draws energy from the <MATERIAL_SURROUND>... But there doesn't seem to be enough <MATERIAL_SURROUND> nearby."), 
/* 189 */   VAMPIRE_ALTAR_CURE_SUCCESS("vampire_altar_cure_success", "The <MATERIAL> draws energy from the <MATERIAL_SURROUND>... Then the energy rushes through you and you feel pure and clean."), 
/* 190 */   VAMPIRE_ALTAR_INFECT_INFO("vampire_altar_infect_info", "This altar looks really evil."), 
/* 191 */   VAMPIRE_ALTAR_INFECT_NOT_ENOUGH("vampire_altar_infect_not_enough", "Something happens... The <MATERIAL> draws energy from the <MATERIAL_SURROUND>... But there doesn't seem to be enough <MATERIAL_SURROUND> nearby."), 
/* 192 */   VAMPIRE_ALTAR_INFECT_SUCCESS("vampire_altar_infect_success", "The <MATERIAL> draws energy from the <MATERIAL_SURROUND>... The energy rushes through you and you feel a bitter cold..."), 
/* 193 */   VAMPIRE_ALTAR_INFECT_SUPERNATURAL("vampire_altar_infect_supernatural", "This is of no use to you as you are already supernatural."), 
/* 194 */   VAMPIRE_ALTAR_INFECT_VAMPIRE("vampire_altar_infect_vampire", "This is of no use to you as you are already a vampire."), 

/* 196 */   VAMPIRE_ALTAR_NOT_ALLOW("vampire_altar_not_allow", "You cannot use vampire altars."), 
/* 197 */   VAMPIRE_DEATH("vampire_death", "You feel your heart stop! You have contracted vampirism."), 
/* 198 */   VAMPIRE_HATE_SUNLIGHT("vampire_hate_sunlight", "Vampires burn in sunlight! Take cover!"), 
/* 199 */   VAMPIRE_HATE_WATER("vampire_hate_water", "Water!"), 
/* 200 */   VAMPIRE_HATE_WOOD("vampire_hate_wood", "Vampires have a weakness to wood!"), 
/* 201 */   VAMPIRE_LIMIT_EAT("vampire_limit_eat", "Vampires can't eat food. You must drink blood instead."), 
/* 202 */   VAMPIRE_LIMIT_WEAPON("vampire_limit_weapon", "Vampires cannot use this weapon!"), 
/* 203 */   VAMPIRE_ONLY("vampire_only", "Vampires Only!"), 
/* 204 */   VAMPIRE_TELEPORT_NOT_SET("vampire_teleport_not_set", "You have not set a teleport location yet!"), 
/* 205 */   VAMPIRE_TELEPORT_NOTICE_SELF("vampire_teleport_notice_self", "Teleport!"), 
/* 206 */   VAMPIRE_TELEPORT_SET("vampire_teleport_set", "Teleport Location Saved!"), 
/* 207 */   VAMPIRE_WATER_DAMAGE_NOTICE("vampire_water_damage_notice", "Not enough power to prevent water damage!"), 
/* 208 */   WEREWOLF_ABILITY_LIMIT("werewolf_ability_limit", "Cannot use this ability during the day."), 
/* 209 */   WEREWOLF_CURE_LIMIT("werewolf_cure_limit", "Cannot cure lycanthropy during the night."), 
/* 210 */   WEREWOLF_DEATH("werewolf_death", "Your basic nature changes... You feel more in touch with your animal side."), 
/* 211 */   WEREWOLF_EAT("werewolf_eat", "Eating!"), 
/* 212 */   WEREWOLF_EAT_LIMIT("werewolf_eat_limit", "Werewolves do not gain power from Bread."),
            WEREWOLF_POTION_ONLY("werewolf_potion_only","You ate wolf bane and happend nothing"),
            WEREWOLF_POTION_SUCCEED("werewolf_potion_succeed","You ate wolf bane you feels not like to kill creatures"),
/* 215 */   WEREWOLF_SUMMON_NOT_ALLOW("werewolf_summon_not_allow", "You cannot summon here."), 
/* 216 */   WEREWOLF_SUMMON_TOO_MUCH_WOLF("werewolf_summon_too_much_wolf", "You already have all the wolves you can control."), 
/* 217 */   WEREWOLF_SUMMON_WOLF("werewolf_summon_wolf", "Summoning wolf!"), 
/* 218 */   WEREWOLF_WEAPON_LIMIT("werewolf_weapon_limit", "Werewolves cannot use this weapon at night!"), 
/* 219 */   WERWWOLF_DASH_TRIGGER("werwwolf_dash_trigger", "Dash!"), 
/* 220 */   WITCHHUNTER_ARROW_CHANGE("witchhunter_arrow_change", "Changed to arrow type: &f<TYPE>"), 
/* 221 */   WITCHHUNTER_ARROW_SWITCH_NORMAL("witchhunter_arrow_switch_normal", "Switching to normal arrows."), 
/* 222 */   WITCHHUNTER_BOUNTY_ADD("witchhunter_bounty_add", "&f<PLAYER>&c has been added to the WitchHunter target list!"), 
/* 223 */   WITCHHUNTER_BOUNTY_GET("witchhunter_bounty_get", "Bounty Fulfilled!"), 
/* 224 */   WITCHHUNTER_FIREARROW_NOTICE_SELF("witchhunter_firearrow_notice_self", "Fire Arrow!"), 
/* 225 */   WITCHHUNTER_GRAPPLEARROW_NOTICE_SELF("witchhunter_grapplearrow_notice_self", "Grapple Arrow!"), 
/* 226 */   WITCHHUNTER_INVIT_GREETING("witchhunter_invit_greeting", "You have been invited to join the WitchHunter society!"), 
/* 227 */   WITCHHUNTER_INVIT_PROMPT("witchhunter_invit_prompt", "If you wish to accept this invitation visit a WitchHunters' Hall"), 
/* 228 */   WITCHHUNTER_INVIT_SUCCESS("witchhunter_invit_success", "Welcome to the WitchHunter society!"), 
/* 229 */   WITCHHUNTER_KILL_SELF("witchhunter_kill_self", "This action, voluntary or not, has rescinded your status as a WitchHunter."), 
/* 230 */   WITCHHUNTER_LIMIT_WEAPON("witchhunter_limit_weapon", "WitchHunters cannot use this weapon!"), 
/* 231 */   WITCHHUNTER_ONLY("witchhunter_only", "WitchHunters Only!"), 
/* 232 */   WITCHHUNTER_POWERARROW_COOLDOWN("witchhunter_powerarrow_cooldown", "You are still recovering from Power Shot."), 
/* 233 */   WITCHHUNTER_POWERARROW_NOTICE_SELF("witchhunter_powerarrow_notice_self", "Power Arrow!"), 
/* 234 */   WITCHHUNTER_POWERARROW_READY("witchhunter_powerarrow_ready", "You can shoot again!"), 
/* 235 */   WITCHHUNTER_SIGN_NOT_ALLOW("witchhunter_sign_not_allow", "You do not have permission to create WitchHunter signs"), 
/* 236 */   WITCHHUNTER_TRIPLEARROW_NOTICE_SELF("witchhunter_triplearrow_notice_self", "Triple Arrow!"), 
/* 237 */   YOU_DIE("you_die", "You died!");
/*     */   
/*     */   private String def;
/*     */   private String path;
/*     */   
/*     */   private Language(String path, String def)
/*     */   {
/* 244 */     this.path = path;
/* 245 */     this.def = def;
/*     */   }
/*     */   
/*     */   public String getDef() {
/* 249 */     return this.def;
/*     */   }
/*     */   
/*     */   public String getPath() {
/* 253 */     return this.path;
/*     */   }
/*     */   
/*     */   public void setDef(String def) {
                if(def==null){
                    return;
                }
/* 257 */     this.def = def;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 261 */     String result = this.def;
/* 272 */     result = ChatColor.translateAlternateColorCodes('&', result);
/* 273 */     return result;
/*     */   }
/*     */ }
