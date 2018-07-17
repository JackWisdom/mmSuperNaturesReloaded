/*     */ package git.JackWisdom.mcp.supernaturals.io;
/*     */ 
/*     */ import git.JackWisdom.mcp.supernaturals.SupernaturalsPlugin;
/*     */ import git.JackWisdom.mcp.supernaturals.util.Recipes;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */
import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */
/*     */ import org.bukkit.configuration.Configuration;
/*     */
/*     */ import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
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
/*     */ 
/*     */ public class SNConfigHandler
/*     */ {
            public static List<Map<?,?>> wolfBaneRecipeKey;
            public static Material wolfBane;
            public static String wolfBaneDispaly;
            public static List<String> wolfBaneLore;
            public static List<String> wolfRecipe;
/*     */   public static SupernaturalsPlugin plugin;
/*     */   public static Configuration config;
/*     */   public static String language;
            public static boolean useSql;
/*     */   public static boolean convertNode;
/*     */   public static boolean vampireKillSpreadCurse;
/*     */   public static boolean ghoulKillSpreadCurse;
/*     */   public static boolean ghoulRightClickSummon;
/*     */   public static boolean wereKillSpreadCurse;
/*     */   public static boolean vampireBurnInSunlight;
/*     */   public static boolean vampireBurnMessageEnabled;
/*     */   public static boolean wolfTruce;
/*     */   public static boolean enableColors;
/*     */   public static boolean multiworld;
/*     */   public static boolean spanish;
/*     */   public static boolean enableLoginMessage;
/*     */   public static boolean enableJoinCommand;
/*     */   public static double spreadChance;
/*     */   public static double vampireDamageFactor;
/*     */   public static double ghoulDamageFactor;
/*     */   public static double woodFactor;
/*     */   public static double vampireDamageReceivedFactor;
/*     */   public static double ghoulDamageReceivedFactor;
/*     */   public static double jumpDeltaSpeed;
/*     */   public static double angelJumpDeltaSpeed;
/*     */   public static double dashDeltaSpeed;
/*     */   public static double ghoulHealthGained;
/*     */   public static double wereHealthGained;
/*     */   public static double vampireAltarInfectMaterialRadius;
/*     */   public static double vampireAltarCureMaterialRadius;
/*     */   public static int vampireTimePowerGained;
/*     */   public static double vampireTimeHealthGained;
/*     */   public static int vampireHealthCost;
/*     */   public static double wereDamageFall;
/*     */   public static double wereDamageFactor;
/*     */   public static double priestDamageFactorAttackSuper;
/*     */   public static double priestDamageFactorAttackHuman;
/*     */   public static double priestDrainFactor;
/*     */   public static double hunterPowerArrowDamage;
/*     */   public static double ghoulCureChance;

/*     */   public static int jumpBloodCost;
/*     */   public static int dashBloodCost;
/*     */   public static int truceBreakTime;
/*     */   public static int vampireAltarInfectMaterialSurroundCount;
/*     */   public static int vampireAltarCureMaterialSurroundCount;
/*     */   public static int vampirePowerStart;
/*     */   public static int ghoulPowerStart;
/*     */   public static int ghoulDamageWater;
/*     */   public static int werePowerStart;
/*     */   public static int vampireDeathPowerPenalty;
/*     */   public static int ghoulDeathPowerPenalty;
/*     */   public static int wereDeathPowerPenalty;
/*     */   public static int priestDeathPowerPenalty;
/*     */   public static int vampireKillPowerCreatureGain;
/*     */   public static int ghoulKillPowerCreatureGain;
/*     */   public static int wereKillPowerCreatureGain;
/*     */   public static int vampireKillPowerPlayerGain;
/*     */   public static int ghoulKillPowerPlayerGain;
/*     */   public static int wereKillPowerPlayerGain;
/*     */   public static double angelHealHealthGain;
/*     */   public static int angelHealPowerCost;
/*     */   public static int angelSummonPowerCost;
/*     */   public static int angelCurePowerCost;
/*     */   public static int angelJumpPowerCost;
/*     */   public static int angelSwimPowerGain;
/*     */   public static int angelPowerStart;
/*     */   public static int angelKillMonsterPowerGain;
/*     */   public static int vampireCombustFireTicks;
/*     */   public static int vampireDrowningCost;
/*     */   public static int vampireTeleportCost;
/*     */   public static int vampireHungerRegainPlayer;
/*     */   public static int vampireHungerRegainMob;
/*     */   public static int priestPowerBanish;
/*     */   public static int priestPowerHeal;
/*     */   public static int priestPowerCure;
/*     */   public static int priestPowerExorcise;
/*     */   public static int priestPowerDrain;
/*     */   public static int priestPowerGuardianAngel;
/*     */   public static int priestHealAmount;
/*     */   public static int priestPowerStart;
/*     */   public static int priestFireTicks;
/*     */   public static int werePowerSummonCost;
/*     */   public static int werePowerFood;
/*     */   public static int ghoulPowerSummonCost;
/*     */   public static int ghoulPowerBond;
/*     */   public static int demonHealing;
/*     */   public static int demonDeathPowerPenalty;
/*     */   public static int demonPowerFireball;
/*     */   public static int demonPowerSnare;
/*     */   public static int demonSnareDuration;
/*     */   public static int demonPowerGain;
/*     */   public static int demonPowerLoss;
/*     */   public static int demonPowerStart;
/*     */   public static int demonKillPowerCreatureGain;
/*     */   public static int demonKillPowerPlayerGain;
/*     */   public static int demonSnowballAmount;
/*     */   public static int demonFireballDamage;
/*     */   public static int demonFireTicks;
/*     */   public static int demonConvertPower;
/*     */   public static int hunterDeathPowerPenalty;
/*     */   public static int hunterPowerArrowFire;
/*     */   public static int hunterPowerArrowTriple;
/*     */   public static int hunterPowerArrowGrapple;
/*     */   public static int hunterPowerArrowPower;
/*     */   public static int hunterCooldown;
/*     */   public static int hunterKillPowerPlayerGain;
/*     */   public static int hunterKillPowerCreatureGain;
/*     */   public static int hunterFallReduction;
/*     */   public static int hunterFireArrowFireTicks;
/*     */   public static int hunterPowerStart;
/*     */   public static int hunterMaxBounties;
/*     */   public static int hunterBountyCompletion;
            public static String sqlPrefix;
            public static String sqlUrl;
/*     */   public static Material vampireAltarInfectMaterial;
/*     */   public static Material vampireAltarCureMaterial;
/*     */   public static Material vampireAltarInfectMaterialSurround;
/*     */   public static Material vampireAltarCureMaterialSurround;

/*     */   public static Material priestAltarMaterial;
/*     */   public static Material priestSpellGuardianAngel;
/*     */   public static Material wolfMaterial;


    public static int mermaidSwimPowerGain;
    public static Material mermaidDashMaterial;
  //  public static Material mermaidFetchMaterial;
    public static int mermaidDashCost;
//    public static int mermaidFetchCost;



/*     */   public static Material ghoulMaterial;
/*     */   public static Material ghoulBondMaterial;
/*     */   public static Material vampireMaterial;
/*     */   public static Material vampireTeleportMaterial;
/*     */   public static Material vampireJumpMaterial;
/*     */   public static Material dashMaterial;
/*     */   public static Material demonMaterial;
/*     */   public static Material demonSnareMaterial;
/*     */   public static String hunterHallMessage;
/*     */   public static String demonHallMessage;
/*     */   public static String vampireHallMessage;
/*     */   public static Material angelJumpMaterial;
/*     */   public static Material angelSummonCowMaterial;
/*     */   public static Material angelCureMaterial;
/*     */   public static Material angelSummonPigMaterial;
/*     */   public static Material angelSummonWolfMaterial;
    public static Material angelSummonSheepMaterial;
    public static Material angelSummonChickenMaterial;
    public static Material angelSummonRabbitMaterial;
/*     */   public static Material angelHealMaterial;
/*     */   public static Location priestChurchLocation;
/*     */   public static Location priestBanishLocation;
/* 180 */   public static List<String> supernaturalTypes = new ArrayList();
/* 181 */   public static List<String> hunterArrowTypes = new ArrayList();
/*     */   
/* 183 */   public static List<Material> woodMaterials = new ArrayList();
/* 184 */   public static List<EntityType> vampireTruce = new ArrayList();
/* 185 */   public static List<Material> foodMaterials = new ArrayList();
/* 186 */   public static List<Material> ghoulWeapons = new ArrayList();
/* 187 */   public static List<Material> demonWeapons = new ArrayList();
/* 188 */   public static List<Material> priestWeapons = new ArrayList();
/* 189 */   public static List<Material> vampireWeapons = new ArrayList();
/* 190 */   public static List<Material> hunterWeapons = new ArrayList();
/* 191 */   public static List<Material> wereWeapons = new ArrayList();
/* 192 */   public static List<Material> angelWeapons = new ArrayList();
/* 193 */   public static List<Material> ghoulWeaponImmunity = new ArrayList();
/* 194 */   public static List<EntityType> ghoulTruce = new ArrayList();
/* 195 */   public static List<Material> priestSpellMaterials = new ArrayList();
/* 196 */   public static HashMap<Material, Integer> priestDonationMap = new HashMap();
/* 197 */   public static List<Material> burnableBlocks = new ArrayList();
/* 198 */   public static List<Material> hunterArmor = new ArrayList();
/* 199 */   public static List<Material> ghoulArmor = new ArrayList();
/* 200 */   public static List<Material> demonArmor = new ArrayList();
/* 201 */   public static List<Material> priestArmor = new ArrayList();
/* 202 */   public static List<Material> vampireArmor = new ArrayList();
/* 203 */   public static List<Material> wereArmor = new ArrayList();
/* 204 */   public static List<Material> angelArmor = new ArrayList();
    public static List<Material> mermaidArmor = new ArrayList();
    public static List<Material> mermaidWeapon = new ArrayList();
/*     */   public static String priestChurchWorld;
/*     */   
/*     */   public static int priestChurchLocationX;
/*     */   public static int priestChurchLocationY;
/*     */   public static int priestChurchLocationZ;
/*     */   public static String priestBanishWorld;
/*     */   public static int priestBanishLocationX;
/*     */   public static int priestBanishLocationY;
/*     */   public static int priestBanishLocationZ;
            public static int mermaidPowerStart;

    /* 215 */   private static List<String> ghoulWeaponsString = new ArrayList();
/* 216 */   private static List<String> demonWeaponsString = new ArrayList();
/* 217 */   private static List<String> priestWeaponsString = new ArrayList();
/* 218 */   private static List<String> hunterWeaponsString = new ArrayList();
/* 219 */   private static List<String> vampireWeaponsString = new ArrayList();
/* 220 */   private static List<String> wereWeaponsString = new ArrayList();
/* 221 */   private static List<String> angelWeaponsString = new ArrayList();
/* 222 */   private static List<String> ghoulWeaponImmunityString = new ArrayList();
/* 223 */   private static List<String> woodMaterialsString = new ArrayList();
/* 224 */   private static List<String> vampireTruceString = new ArrayList();
/* 225 */   private static List<String> foodMaterialsString = new ArrayList();
/* 226 */   private static List<String> ghoulTruceString = new ArrayList();
/* 227 */   private static List<String> vampireAltarInfectMaterialsString = new ArrayList();
/* 228 */   private static List<String> vampireAltarCureMaterialsString = new ArrayList();
/* 229 */   private static List<Integer> vampireAltarCureQuantities = new ArrayList();
/* 230 */   private static List<Integer> vampireAltarInfectQuantities = new ArrayList();
/* 231 */   private static List<String> priestMaterialsString = new ArrayList();
/* 232 */   private static List<String> priestAltarMaterialsString = new ArrayList();
/* 233 */   private static List<Integer> priestAltarQuantities = new ArrayList();
/* 234 */   private static List<String> priestDonationMaterialsString = new ArrayList();


/* 237 */   private static List<Integer> priestDonationRewards = new ArrayList();
/* 238 */   private static List<String> burnableBlocksString = new ArrayList();
/* 239 */   private static List<String> hunterArmorString = new ArrayList();
/* 240 */   private static List<String> ghoulArmorString = new ArrayList();
/* 241 */   private static List<String> demonArmorString = new ArrayList();
/* 242 */   private static List<String> priestArmorString = new ArrayList();
/* 243 */   private static List<String> vampireArmorString = new ArrayList();
/* 244 */   private static List<String> wereArmorString = new ArrayList();
/* 245 */   private static List<String> angelArmorString = new ArrayList();
/*     */   

/* 248 */   public static HashSet<Material> transparent = new HashSet();
/*     */   
/* 250 */   public static Recipes vampireAltarInfectRecipe = new Recipes();
/* 251 */   public static Recipes vampireAltarCureRecipe = new Recipes();
/* 252 */   public static Recipes priestAltarRecipe = new Recipes();


    /*     */
/*     */   static {

/* 282 */     transparent.add(Material.WATER);

/* 284 */     transparent.add(Material.AIR);
/*     */   }
/*     */   
/*     */   public SNConfigHandler(SupernaturalsPlugin instance) {
/* 288 */     plugin = instance;
/*     */   }
/*     */   
/*     */   public static void getConfiguration() {
/* 292 */     config = plugin.getConfig();
/* 293 */     loadValues(config);
/*     */   }
/*     */   
/*     */   public static void loadValues(Configuration config) {
/* 297 */     File configFile = new File(plugin.getDataFolder(), "config.yml");
/* 298 */     if ((configFile.exists()) && (config.getString("Version") != plugin.getDescription().getVersion()))
/*     */     {
/*     */ 
/* 301 */       config.set("Version", plugin.getDescription().getVersion());
/* 302 */       saveConfig();
/*     */     }
/* 304 */     if (!configFile.exists()) {
/* 305 */       config.options().copyDefaults(true);
/* 306 */       saveConfig();
/*     */     }
                String wb="Were.Wolfbane.";
                wolfBane=Material.valueOf(config.getString(wb+"Material"));
                wolfBaneRecipeKey =config.getMapList(wb+"RecipeKey");
                wolfBaneDispaly=config.getString(wb+"CustomName");
                wolfBaneLore=config.getStringList(wb+"Description");
                wolfRecipe=config.getStringList(wb+"Recipe");
              sqlPrefix=config.getString("Storage.prefix");
              useSql=config.getBoolean("Storage.sql");
              sqlUrl=config.getString("Storage.url");
/* 309 */     language = config.getString("Language");
/* 310 */     convertNode = config.getBoolean("UseConvertPermissionNode");
/* 311 */     multiworld = config.getBoolean("MultiWorld", false);
/* 312 */     enableColors = config.getBoolean("EnableChatColors", true);
/* 313 */     truceBreakTime = config.getInt("Supernatural.Truce.BreakTime", 120000);
/* 314 */     supernaturalTypes = config.getStringList("Supernatural.Types");
/* 315 */     spreadChance = config.getDouble("Supernatural.SpreadChance", 0.35D);
/* 316 */     enableLoginMessage = config.getBoolean("EnableLoginMessage");
/* 317 */     spanish = config.getBoolean("Spanish");
/* 318 */     enableJoinCommand = config.getBoolean("EnableJoinCommand");
/*     */     
/* 320 */     woodMaterialsString = config.getStringList("Material.Wooden");
/* 321 */     foodMaterialsString = config.getStringList("Material.Food");



/*     */     mermaidPowerStart=config.getInt("Mermaid.Power.Start");
    mermaidSwimPowerGain=config.getInt("Mermaid.Power.Swim.PowerGain");

    for(String s:config.getStringList("Mermaid.Armor")){
        mermaidArmor.add(Material.valueOf(s));
    }
    for(String s:config.getStringList(("Mermaid.Weapon.Restrictions"))){
        mermaidWeapon.add(Material.valueOf(s));
    }
    String sm="Mermaid.Material.";
    String sp="Mermaid.Power.";
    mermaidDashMaterial=Material.valueOf(config.getString(sm+"Dash"));
  //  mermaidFetchMaterial= Material.valueOf(config.getString(sm+"Fetch"));
    mermaidDashCost=config.getInt(sp+"DashCost");
//    mermaidFetchCost=config.getInt(sp+"FetchCost");


/* 323 */     vampireJumpMaterial =Material.valueOf( config.getString("Vampire.Materials.Jump", "ROSE_RED"));
/*     */     
/*     */ 
/* 326 */     vampirePowerStart = config.getInt("Vampire.Power.Start", 10000);
/* 327 */     vampireKillSpreadCurse = config.getBoolean("Vampire.Kill.SpreadCurse", true);
/*     */     
/* 329 */     vampireTimePowerGained = config.getInt("Vampire.Time.PowerGained", 15);
/*     */     
/* 331 */     vampireKillPowerCreatureGain = config.getInt("Vampire.Power.Kill.CreatureGain", 100);
/*     */     
/* 333 */     vampireKillPowerPlayerGain = config.getInt("Vampire.Power.Kill.PlayerGain", 500);
/*     */     
/* 335 */     vampireDeathPowerPenalty = config.getInt("Vampire.Power.DeathPenalty", 10000);
/*     */     
/* 337 */     vampireDamageFactor = config.getDouble("Vampire.DamageFactor.AttackBonus", 0.3D);
/*     */     
/* 339 */     vampireDamageReceivedFactor = config.getDouble("Vampire.DamageFactor.DefenseBonus", 0.8D);
/*     */     
/* 341 */     woodFactor = config.getDouble("Vampire.DamageFactor.Wood", 1.5D);
/* 342 */     vampireBurnInSunlight = config.getBoolean("Vampire.Burn.InSunlight", true);
/*     */     
/* 344 */     vampireBurnMessageEnabled = config.getBoolean("Vampire.Burn.MessageEnabled", true);
/*     */     
/* 346 */     vampireCombustFireTicks = config.getInt("Vampire.Burn.FireTicks", 3);
/*     */     
/* 348 */     jumpDeltaSpeed = config.getDouble("Vampire.JumpDelta", 1.2D);
/* 349 */     jumpBloodCost = config.getInt("Vampire.Power.JumpCost", 1000);
/* 350 */     vampireTimeHealthGained = config.getDouble("Vampire.Time.HealthGained", 0.1);
/*     */     
/* 352 */     vampireHealthCost = config.getInt("Vampire.Power.HealingCost", 60);
/* 353 */     vampireDrowningCost = config.getInt("Vampire.Power.DrowningCost", 90);
/* 354 */     vampireTeleportCost = config.getInt("Vampire.Power.TeleportCost", 9000);
/* 355 */     vampireTeleportMaterial = Material.valueOf(config.getString("Vampire.TeleportMarker.Material", "ROSE_RED"));
/*     */     
/* 357 */     vampireTruceString = config.getStringList("Vampire.Truce.Creatures");
/* 358 */     vampireMaterial =Material.valueOf(  config.getString("Vampire.Spell.Material", "BOOK") );

/* 361 */     vampireWeaponsString = config.getStringList("Vampire.Weapon.Restrictions");
/*     */     
/* 363 */     vampireArmorString = config.getStringList("Vampire.Armor");
/* 364 */     vampireHungerRegainPlayer = config.getInt("Vampire.GainHunger.Player");
/* 365 */     vampireHungerRegainMob = config.getInt("Vampire.GainHunger.Mob");
/*     */     
/* 367 */     vampireAltarInfectMaterial = Material.valueOf(config.getString( "Vampire.Altar.Infect.Material", "GOLD_BLOCK") );
/*     */     
/* 369 */     vampireAltarInfectMaterialSurround = Material.valueOf(config.getString(("Vampire.Altar.Infect.Surrounding.Material")));
/*     */     
/* 371 */     vampireAltarInfectMaterialRadius = config.getDouble("Vampire.Altar.Infect.Surrounding.Radius", 7.0D);
/*     */     
/* 373 */     vampireAltarInfectMaterialSurroundCount = config.getInt("Vampire.Altar.Infect.Surrounding.Count", 20);
/*     */     
/* 375 */     vampireAltarInfectMaterialsString = config.getStringList("Vampire.Altar.Infect.Recipe.Materials");
/*     */     
/* 377 */     vampireAltarInfectQuantities = config.getIntegerList("Vampire.Altar.Infect.Recipe.Quantities");
/*     */     
/*     */ 
/* 380 */     vampireAltarCureMaterial = Material.valueOf(config.getString(("Vampire.Altar.Cure.Material")));
/*     */     
/* 382 */     vampireAltarCureMaterialSurround =Material.valueOf(config.getString(  "Vampire.Altar.Cure.Surrounding.Material", "GLOWSTONE" ));
/*     */     
/* 384 */     vampireAltarCureMaterialRadius = config.getDouble("Vampire.Altar.Cure.Surrounding.Radius", 7.0D);
/*     */     
/* 386 */     vampireAltarCureMaterialSurroundCount = config.getInt("Vampire.Altar.Cure.Surrounding.Count", 20);
/*     */     
/* 388 */     vampireAltarCureMaterialsString = config.getStringList("Vampire.Altar.Cure.Recipe.Materials");
/*     */     
/* 390 */     vampireAltarCureQuantities = config.getIntegerList("Vampire.Altar.Cure.Recipe.Quantities");
/*     */     
/*     */ 
/* 393 */     vampireHallMessage =  (config.getString(("Vampire.Hall.Message")));
/*     */     
/*     */ 
/* 396 */     priestChurchWorld = config.getString("Priest.Church.World", "world");
/* 397 */     priestChurchLocationX = config.getInt("Priest.Church.Location.X", 0);
/* 398 */     priestChurchLocationY = config.getInt("Priest.Church.Location.Y", 80);
/* 399 */     priestChurchLocationZ = config.getInt("Priest.Church.Location.Z", 0);
/* 400 */     priestBanishWorld = config.getString("Priest.Banish.World", "world");
/* 401 */     priestBanishLocationX = config.getInt("Priest.Banish.Location.X", 0);
/* 402 */     priestBanishLocationY = config.getInt("Priest.Banish.Location.Y", 80);
/* 403 */     priestBanishLocationZ = config.getInt("Priest.Banish.Location.Z", 0);
/*     */     
/* 405 */     priestPowerStart = config.getInt("Priest.Power.StartingAmount", 10000);
/* 406 */     priestDeathPowerPenalty = config.getInt("Priest.Power.DeathPenalty", 2000);
/*     */     
/* 408 */     priestDamageFactorAttackSuper = config.getDouble("Priest.DamageFactor.AttackBonusSuper", 1.0D);
/*     */     
/* 410 */     priestDamageFactorAttackHuman = config.getDouble("Priest.DamageFactor.AttackBonusHuman", 0.0D);
/*     */     
/* 412 */     priestPowerBanish = config.getInt("Priest.Power.Banish", 4000);
/* 413 */     priestPowerHeal = config.getInt("Priest.Power.HealOther", 1000);
/* 414 */     priestHealAmount = config.getInt("Priest.Spell.HealAmount", 10);
/* 415 */     priestPowerExorcise = config.getInt("Priest.Power.Exorcise", 9000);
/* 416 */     priestPowerCure = config.getInt("Priest.Power.Cure", 1000);
/* 417 */     priestPowerDrain = config.getInt("Priest.Power.Drain", 1000);
/* 418 */     priestPowerGuardianAngel = config.getInt("Priest.Power.GuardianAngel", 5000);
/*     */     
/* 420 */     priestDrainFactor = config.getDouble("Priest.Spell.DrainFactor", 0.15D);
/* 421 */     priestFireTicks = config.getInt("Priest.DamageFactor.FireTicks", 50);
/* 422 */     priestAltarMaterial = Material.valueOf(config.getString(("Priest.Church.AltarMaterial")));
/*     */     
/* 424 */     priestMaterialsString = config.getStringList("Priest.Spell.Material");
/* 425 */     priestSpellGuardianAngel = Material.valueOf(config.getString("Priest.Spell.GuardianAngelMaterial", "WHITE_WOOL"));
/*     */     
/* 427 */     priestAltarMaterialsString = config.getStringList("Priest.Church.Recipe.Materials");
/*     */     
/* 429 */     priestAltarQuantities = config.getIntegerList("Priest.Church.Recipe.Quantities");
/*     */     
/* 431 */     priestDonationMaterialsString = config.getStringList("Priest.Church.Donation.Materials");
/*     */     
/* 433 */     priestDonationRewards = config.getIntegerList("Priest.Church.Donation.Rewards");
/*     */     
/* 435 */     priestArmorString = config.getStringList("Priest.Armor");
/* 436 */     priestWeaponsString = config.getStringList("Priest.Weapon.Restrictions");
/*     */     
/*     */ 
/* 439 */     ghoulPowerStart = config.getInt("Ghoul.Power.Start", 5000);
/* 440 */     ghoulKillSpreadCurse = config.getBoolean("Ghoul.Kill.SpreadCurse", true);
/*     */     
/* 442 */     ghoulKillPowerCreatureGain = config.getInt("Ghoul.Power.Kill.CreatureGain", 200);
/*     */     
/* 444 */     ghoulKillPowerPlayerGain = config.getInt("Ghoul.Power.Kill.PlayerGain", 1000);
/*     */     
/* 446 */     ghoulDeathPowerPenalty = config.getInt("Ghoul.Power.DeathPenalty", 2000);
/*     */     
/* 448 */     ghoulDamageReceivedFactor = config.getDouble("Ghoul.DamageFactor.DefenseBonus", 0.65D);
/*     */     
/* 450 */     ghoulWeaponsString = config.getStringList("Ghoul.Weapon.Restrictions");
/* 451 */     ghoulTruceString = config.getStringList("Ghoul.Truce.Creatures");
/* 452 */     ghoulDamageFactor = config.getDouble("Ghoul.DamageFactor.AttackBonus", 2.0D);
/*     */     
/* 454 */     ghoulDamageWater = config.getInt("Ghoul.WaterDamage", 4);
/* 455 */     ghoulHealthGained = config.getDouble("Ghoul.Time.HealthGained", 0.2);
/* 456 */     ghoulMaterial = Material.valueOf(config.getString(("Ghoul.Summon.Material")));
/* 457 */     ghoulBondMaterial = Material.valueOf(config.getString(("Ghoul.UnholyBond.Material")));
/*     */     
/* 459 */     ghoulPowerSummonCost = config.getInt("Ghoul.Power.Summon", 1000);
/* 460 */     ghoulPowerBond = config.getInt("Ghoul.Power.UnholyBond", 50);
/* 461 */     ghoulWeaponImmunityString = config.getStringList("Ghoul.Immunity");
/* 462 */     ghoulArmorString = config.getStringList("Ghoul.Armor");
/* 463 */     ghoulRightClickSummon = config.getBoolean("Ghoul.RightClickSummon");
/* 464 */     ghoulCureChance = config.getDouble("Ghoul.CureChance");
/*     */     
/* 466 */     dashDeltaSpeed = config.getDouble("Were.DashDelta", 4.0D);
/* 467 */     dashBloodCost = config.getInt("Were.Power.Dash", 400);
/*     */     
/* 469 */     werePowerStart = config.getInt("Were.Power.Start", 5000);
/* 470 */     wereKillSpreadCurse = config.getBoolean("Were.Kill.SpreadCurse", true);
/* 471 */     wereKillPowerCreatureGain = config.getInt("Were.Power.Kill.CreatureGain", 20);
/*     */     
/* 473 */     wereKillPowerPlayerGain = config.getInt("Were.Power.Kill.PlayerGain", 100);
/*     */     
/* 475 */     werePowerFood = config.getInt("Were.Power.Food", 100);
/* 476 */     wereDeathPowerPenalty = config.getInt("Were.Power.DeathPenalty", 2000);
/* 477 */     wereDamageFall = config.getDouble("Were.DamageFactor.Fall", 0.5D);
/* 478 */     wereDamageFactor = config.getDouble("Were.DamageFactor.AttackBonus", 5.0D);
/* 479 */     wereHealthGained = config.getDouble("Were.Time.HealthGained", 0.5);
/* 480 */     wolfMaterial = Material.valueOf( (config.getString("Were.Material.Summon", "PORKCHOP")));
/* 481 */     werePowerSummonCost = config.getInt("Were.Power.Summon", 2000);
/* 482 */     wolfTruce = config.getBoolean("Were.WolfTruce", true);
/* 483 */     dashMaterial = Material.valueOf( (config.getString("Were.Material.Dash", "FEATHER")));

/*     */     
/* 489 */     wereArmorString = config.getStringList("Were.Armor");
/* 490 */     wereWeaponsString = config.getStringList("Were.Weapon.Restrictions");
/*     */     
/* 492 */     demonPowerStart = config.getInt("Demon.Power.Start", 10000);
/* 493 */     demonDeathPowerPenalty = config.getInt("Demon.Power.DeathPenalty", 10000);
/*     */     
/* 495 */     demonKillPowerCreatureGain = config.getInt("Demon.Power.CreatureKill", 20);
/*     */     
/* 497 */     demonKillPowerPlayerGain = config.getInt("Demon.Power.PlayerKill", 100);
/* 498 */     demonPowerGain = config.getInt("Demon.Power.Gain", 40);
/* 499 */     demonPowerLoss = config.getInt("Demon.Power.Loss", 4);
/* 500 */     demonPowerFireball = config.getInt("Demon.Power.Fireball", 2000);
/* 501 */     demonHealing = config.getInt("Demon.Healing", 1);
/* 502 */     demonMaterial = Material.valueOf(config.getString(("Demon.Fireball.Material")));
/* 503 */     demonFireballDamage = config.getInt("Demon.Fireball.Damage", 10);
/* 504 */     demonPowerSnare = config.getInt("Demon.Power.Snare", 1000);
/* 505 */     demonSnareDuration = config.getInt("Demon.Snare.Duration", 10000);
/* 506 */     demonSnareMaterial = Material.valueOf(config.getString(("Demon.Snare.Material")));
/*     */     
/* 508 */     demonSnowballAmount = config.getInt("Demon.SnowballAmount", 30);
/* 509 */     demonArmorString = config.getStringList("Demon.Armor");
/* 510 */     demonWeaponsString = config.getStringList("Demon.Weapon.Restrictions");
/* 511 */     demonFireTicks = config.getInt("Demon.DamageFactor.FireTicks", 50);
/* 512 */     demonConvertPower = config.getInt("Demon.Power.Convert", 2000);
/* 513 */     demonHallMessage =  (config.getString(("Demon.Hall.Message")));
/*     */     
/* 515 */     hunterPowerStart = config.getInt("WitchHunter.Power.StartingPower", 10000);
/*     */     
/* 517 */     hunterDeathPowerPenalty = config.getInt("WitchHunter.Power.DeathPenalty", 500);
/*     */     
/* 519 */     hunterKillPowerPlayerGain = config.getInt("WitchHunter.Power.PlayerKill", 2000);
/*     */     
/* 521 */     hunterKillPowerCreatureGain = config.getInt("WitchHunter.Power.CreatureKill", 0);
/*     */     
/* 523 */     hunterBountyCompletion = config.getInt("WitchHunter.Bounty.CompletionBonus", 8000);
/*     */     
/* 525 */     hunterPowerArrowFire = config.getInt("WitchHunter.Power.ArrowFire", 100);
/*     */     
/* 527 */     hunterPowerArrowTriple = config.getInt("WitchHunter.Power.ArrowTriple", 100);
/*     */     
/* 529 */     hunterPowerArrowGrapple = config.getInt("WitchHunter.Power.ArrowGrapple", 500);
/*     */     
/* 531 */     hunterPowerArrowPower = config.getInt("WitchHunter.Power.ArrowPower", 1000);
/*     */     
/* 533 */     hunterPowerArrowDamage = config.getDouble("WitchHunter.ArrowPower.DamageFactor", 2.0D);
/*     */     
/* 535 */     hunterCooldown = config.getInt("WitchHunter.PowerArrow.Cooldown", 15000);
/*     */     
/* 537 */     hunterArmorString = config.getStringList("WitchHunter.Armor");
/* 538 */     hunterFallReduction = config.getInt("WitchHunter.FallReduction", 3);
/* 539 */     hunterFireArrowFireTicks = config.getInt("WitchHunter.FireArrow.FireTicks", 100);
/*     */     
/* 541 */     hunterArrowTypes = config.getStringList("WitchHunter.ArrowTypes");
/* 542 */     hunterHallMessage =  (config.getString(("WitchHunter.Hall.Message")));
/*     */     
/* 544 */     hunterMaxBounties = config.getInt("WitchHunter.Bounty.MaxNumber", 5);
/* 545 */     hunterWeaponsString = config.getStringList("WitchHunter.Weapon.Restrictions");
/*     */     
/*     */ 
/* 548 */     angelHealHealthGain = config.getDouble("Angel.Power.Heal.HealthGain");
/* 549 */     angelHealPowerCost = config.getInt("Angel.Power.Heal.PowerCost", 3000);
/* 550 */     angelSummonPowerCost = config.getInt("Angel.Power.Summon.PowerCost", 5000);
/*     */     
/* 552 */     angelCurePowerCost = config.getInt("Angel.Power.Cure.PowerCost", 6000);
/* 553 */     angelJumpPowerCost = config.getInt("Angel.Power.Jump.PowerCost", 1000);
/* 554 */     angelSwimPowerGain = config.getInt("Angel.Power.Swim.PowerGain", 50);

/* 555 */     angelJumpDeltaSpeed = config.getDouble("Angel.JumpDelta", 1.2D);
/* 556 */     angelKillMonsterPowerGain = config.getInt("Angel.Power.Kill.MonsterGain", 30);
/*     */     
/* 558 */     angelPowerStart = config.getInt("Angel.Power.Start", 10000);
/* 559 */     angelJumpMaterial =Material.valueOf(config.getString( "Angel.Materials.Jump"  ));
/*     */     
/* 561 */     angelCureMaterial =Material.valueOf(config.getString( "Angel.Materials.Cure" ));
/* 562 */     angelSummonCowMaterial = Material.valueOf(config.getString(("Angel.Materials.Summon.Cow")));
    angelSummonSheepMaterial = Material.valueOf(config.getString("Angel.Materials.Summon.Sheep", "MUTTON"));
    angelSummonChickenMaterial = Material.valueOf(config.getString("Angel.Materials.Summon.Chicken", "CHICKEN"));
    angelSummonRabbitMaterial = Material.valueOf(config.getString("Angel.Materials.Summon.Rabbit", "RABBIT"));

    /* 564 */     angelSummonPigMaterial = Material.valueOf(config.getString(("Angel.Materials.Summon.Pig")));
/*     */     
/* 566 */     angelSummonWolfMaterial = Material.valueOf(config.getString(("Angel.Materials.Summon.Wolf")));
/*     */     
/* 568 */     angelHealMaterial = Material.valueOf(config.getString(("Angel.Materials.Heal")));
/* 569 */     angelArmorString = config.getStringList("Angel.Armor");
/* 570 */     angelWeaponsString = config.getStringList("Angel.Weapons.Restrictions");
/*     */     
/* 572 */     for (String weapon : angelWeaponsString) {
                
/* 573 */       angelWeapons.add(Material.valueOf(weapon));
/*     */     }
/*     */     
/* 576 */     for (String armor : angelArmorString) {
/* 577 */       angelArmor.add(Material.valueOf(armor));
/*     */     }
/*     */     
/* 580 */     for (String wood : woodMaterialsString) {
/* 581 */       woodMaterials.add(Material.valueOf(wood));
/*     */     }
/*     */     
/* 584 */     for (String food : foodMaterialsString) {
/* 585 */       foodMaterials.add(Material.valueOf(food));
/*     */     }
/*     */     
/* 588 */     for (String block : burnableBlocksString) {
/* 589 */       burnableBlocks.add(Material.valueOf(block));
/*     */     }
/*     */     
/* 592 */     for (String creature : vampireTruceString) {
/* 593 */       EntityType cType = EntityType.valueOf(creature);
/* 594 */       if (cType != null) {
/* 595 */         vampireTruce.add(cType);
/*     */       }
/*     */     }
/*     */     
/* 599 */     for (String material : priestMaterialsString) {
/* 600 */       priestSpellMaterials.add(Material.valueOf(material));
/*     */     }
/*     */     
/* 603 */     for (String creature : ghoulTruceString) {
/* 604 */       EntityType cType = EntityType.valueOf(creature);
/* 605 */       if (cType != null) {
/* 606 */         ghoulTruce.add(cType);
/*     */       }
/*     */     }
/*     */     
/* 610 */     for (String weapon : ghoulWeaponsString) {
/* 611 */       ghoulWeapons.add(Material.valueOf(weapon));
/*     */     }
/*     */     
/* 614 */     for (String weapon : demonWeaponsString) {
/* 615 */       demonWeapons.add(Material.valueOf(weapon));
/*     */     }
/*     */     
/* 618 */     for (String weapon : hunterWeaponsString) {
/* 619 */       hunterWeapons.add(Material.valueOf(weapon));
/*     */     }
/*     */     
/* 622 */     for (String weapon : priestWeaponsString) {
/* 623 */       priestWeapons.add(Material.valueOf(weapon));
/*     */     }
/*     */     
/* 626 */     for (String weapon : vampireWeaponsString) {
/* 627 */       vampireWeapons.add(Material.valueOf(weapon));
/*     */     }
/*     */     
/* 630 */     for (String weapon : wereWeaponsString) {
/* 631 */       wereWeapons.add(Material.valueOf(weapon));
/*     */     }
/*     */     
/* 634 */     for (String weapon : ghoulWeaponImmunityString) {
/* 635 */       ghoulWeaponImmunity.add(Material.valueOf(weapon));
/*     */     }
/*     */     
/* 638 */     for (String armor : hunterArmorString) {
/* 639 */       hunterArmor.add(Material.valueOf(armor));
/*     */     }
/*     */     
/* 642 */     for (String armor : ghoulArmorString) {
/* 643 */       ghoulArmor.add(Material.valueOf(armor));
/*     */     }
/*     */     
/* 646 */     for (String armor : demonArmorString) {
/* 647 */       demonArmor.add(Material.valueOf(armor));
/*     */     }
/*     */     
/* 650 */     for (String armor : priestArmorString) {
 
/* 651 */       priestArmor.add(Material.valueOf(armor));
/*     */     }
/*     */     
/* 654 */     for (String armor : vampireArmorString) {
/* 655 */       vampireArmor.add(Material.valueOf(armor));
/*     */     }
/*     */     
/* 658 */     for (String armor : wereArmorString) {
/* 659 */       wereArmor.add(Material.valueOf(armor));
/*     */     }
/*     */     
/* 662 */     for (int i = 0; i < vampireAltarInfectMaterialsString.size(); i++) {
/* 663 */       Material material = Material.valueOf((String)vampireAltarInfectMaterialsString.get(i));
/*     */       
/* 665 */       int quantity = 1;
/*     */       try {
/* 667 */         quantity = ((Integer)vampireAltarInfectQuantities.get(i)).intValue();
/*     */       } catch (Exception e) {
/* 669 */         e.printStackTrace();
/* 670 */         SupernaturalsPlugin.log("Invalid VAMPIRE_I Infect Altar Quantities!");
/*     */       }
/*     */       
/* 673 */       vampireAltarInfectRecipe.getItemStacks().add(new ItemStack(material, Integer.valueOf(quantity)));
/*     */     }
/*     */     
/* 676 */     for (int i = 0; i < vampireAltarCureMaterialsString.size(); i++) {
/* 677 */       Material material = Material.valueOf((String)vampireAltarCureMaterialsString.get(i));
/*     */       
/* 679 */       int quantity = 1;
/*     */       try {
/* 681 */         quantity = ((Integer)vampireAltarCureQuantities.get(i)).intValue();
/*     */       } catch (Exception e) {
/* 683 */         e.printStackTrace();
/* 684 */         SupernaturalsPlugin.log("Invalid VAMPIRE_I Cure Altar Quantities!");
/*     */       }
/*     */       
/* 687 */       vampireAltarCureRecipe.getItemStacks().add(new ItemStack(material, Integer.valueOf(quantity)));
/*     */     }
/*     */     
/* 690 */     for (int i = 0; i < priestAltarMaterialsString.size(); i++) {
/* 691 */       Material material = Material.valueOf((String)priestAltarMaterialsString.get(i));
/*     */
/* 693 */       int quantity = 1;
/*     */       try {
/* 695 */         quantity = ((Integer)priestAltarQuantities.get(i)).intValue();
/*     */       } catch (Exception e) {
/* 697 */         e.printStackTrace();
/* 698 */         SupernaturalsPlugin.log("Invalid PRIEST Altar Quantities!");
/*     */       }
                
/* 700 */       priestAltarRecipe.getItemStacks().add(new ItemStack(material, Integer.valueOf(quantity)));
/*     */     }
/*     */     

/*     */     
/* 716 */     for (int i = 0; i < priestDonationMaterialsString.size(); i++) {
/* 717 */       Material material = Material.valueOf((String)priestDonationMaterialsString.get(i));
/*     */       
/* 719 */       int reward = 1;
/*     */       try {
/* 721 */         reward = ((Integer)priestDonationRewards.get(i)).intValue();
/*     */       } catch (Exception e) {
/* 723 */         e.printStackTrace();
/* 724 */         SupernaturalsPlugin.log("Invalid priest donation reward!");
/*     */       }
/* 726 */       priestDonationMap.put(material, Integer.valueOf(reward));
/*     */     }
/*     */     
/* 729 */     priestChurchLocation = new Location(plugin.getServer().getWorld(priestChurchWorld), priestChurchLocationX, priestChurchLocationY, priestChurchLocationZ);
/*     */     
/*     */ 
/* 732 */     priestBanishLocation = new Location(plugin.getServer().getWorld(priestBanishWorld), priestBanishLocationX, priestBanishLocationY, priestBanishLocationZ);
/*     */   }
/*     */   
/*     */ 
/*     */   public static void saveConfig()
/*     */   {
/* 738 */     plugin.saveConfig();
/*     */   }
/*     */   
/*     */   public static void reloadConfig() {
/* 742 */     plugin.reloadConfig();
/* 743 */     loadValues(config);
/*     */   }
/*     */   
/*     */   public static Configuration getConfig() {
/* 747 */     return config;
/*     */   }
/*     */ }


