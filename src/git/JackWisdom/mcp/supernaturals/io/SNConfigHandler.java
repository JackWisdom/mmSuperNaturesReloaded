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
/*     */   public static double vampireTimePowerGained;
/*     */   public static double vampireTimeHealthGained;
/*     */   public static double vampireHealthCost;
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
/*     */   public static int angelHealHealthGain;
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
/*     */   public static String vampireAltarInfectMaterial;
/*     */   public static String vampireAltarCureMaterial;
/*     */   public static String vampireAltarInfectMaterialSurround;
/*     */   public static String vampireAltarCureMaterialSurround;
/*     */   public static String vampireHelmet;
/*     */   public static String priestAltarMaterial;
/*     */   public static String priestSpellGuardianAngel;
/*     */   public static String wolfMaterial;
/*     */   public static String wolfbaneMaterial;
/*     */   public static String ghoulMaterial;
/*     */   public static String ghoulBondMaterial;
/*     */   public static String vampireMaterial;
/*     */   public static String vampireTeleportMaterial;
/*     */   public static String vampireJumpMaterial;
/*     */   public static String dashMaterial;
/*     */   public static String demonMaterial;
/*     */   public static String demonSnareMaterial;
/*     */   public static String hunterHallMessage;
/*     */   public static String demonHallMessage;
/*     */   public static String vampireHallMessage;
/*     */   public static String angelJumpMaterial;
/*     */   public static String angelSummonCowMaterial;
/*     */   public static String angelCureMaterial;
/*     */   public static String angelSummonPigMaterial;
/*     */   public static String angelSummonWolfMaterial;
/*     */   public static String angelHealMaterial;
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
/*     */   
/*     */   public static String priestChurchWorld;
/*     */   
/*     */   public static int priestChurchLocationX;
/*     */   public static int priestChurchLocationY;
/*     */   public static int priestChurchLocationZ;
/*     */   public static String priestBanishWorld;
/*     */   public static int priestBanishLocationX;
/*     */   public static int priestBanishLocationY;
/*     */   public static int priestBanishLocationZ;
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
/* 235 */   private static List<String> wereWolfbaneMaterialsString = new ArrayList();
/* 236 */   private static List<Integer> wereWolfbaneQuantities = new ArrayList();
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
/* 247 */   public static Map<Material, Double> materialOpacity = new HashMap();
/* 248 */   public static HashSet<Material> transparent = new HashSet();
/*     */   
/* 250 */   public static Recipes vampireAltarInfectRecipe = new Recipes();
/* 251 */   public static Recipes vampireAltarCureRecipe = new Recipes();
/* 252 */   public static Recipes priestAltarRecipe = new Recipes();
/* 253 */   public static Recipes wereWolfbaneRecipe = new Recipes();
/*     */   
/*     */   static {
/* 256 */     materialOpacity.put(Material.AIR, Double.valueOf(0.0D));
/* 257 */     materialOpacity.put(Material.SAPLING, Double.valueOf(0.3D));
/* 258 */     materialOpacity.put(Material.LEAVES, Double.valueOf(0.3D));
/* 259 */     materialOpacity.put(Material.GLASS, Double.valueOf(0.5D));
/* 260 */     materialOpacity.put(Material.YELLOW_FLOWER, Double.valueOf(0.1D));
/* 261 */     materialOpacity.put(Material.RED_ROSE, Double.valueOf(0.1D));
/* 262 */     materialOpacity.put(Material.BROWN_MUSHROOM, Double.valueOf(0.1D));
/* 263 */     materialOpacity.put(Material.RED_MUSHROOM, Double.valueOf(0.1D));
/* 264 */     materialOpacity.put(Material.TORCH, Double.valueOf(0.1D));
/* 265 */     materialOpacity.put(Material.FIRE, Double.valueOf(0.0D));
/* 266 */     materialOpacity.put(Material.MOB_SPAWNER, Double.valueOf(0.3D));
/* 267 */     materialOpacity.put(Material.REDSTONE_WIRE, Double.valueOf(0.0D));
/* 268 */     materialOpacity.put(Material.CROPS, Double.valueOf(0.2D));
/* 269 */     materialOpacity.put(Material.SIGN, Double.valueOf(0.1D));
/* 270 */     materialOpacity.put(Material.SIGN_POST, Double.valueOf(0.2D));
/* 271 */     materialOpacity.put(Material.LEVER, Double.valueOf(0.1D));
/* 272 */     materialOpacity.put(Material.STONE_PLATE, Double.valueOf(0.0D));
/* 273 */     materialOpacity.put(Material.WOOD_PLATE, Double.valueOf(0.0D));
/* 274 */     materialOpacity.put(Material.REDSTONE_TORCH_OFF, Double.valueOf(0.1D));
/* 275 */     materialOpacity.put(Material.REDSTONE_TORCH_ON, Double.valueOf(0.1D));
/* 276 */     materialOpacity.put(Material.STONE_BUTTON, Double.valueOf(0.0D));
/* 277 */     materialOpacity.put(Material.SUGAR_CANE_BLOCK, Double.valueOf(0.3D));
/* 278 */     materialOpacity.put(Material.FENCE, Double.valueOf(0.2D));
/* 279 */     materialOpacity.put(Material.DIODE_BLOCK_OFF, Double.valueOf(0.0D));
/* 280 */     materialOpacity.put(Material.DIODE_BLOCK_ON, Double.valueOf(0.0D));
/*     */     
/* 282 */     transparent.add(Material.WATER);
/* 283 */     transparent.add(Material.STATIONARY_WATER);
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
/*     */     
/* 323 */     vampireJumpMaterial = config.getString("VAMPIRE.Materials.Jump", "RED_ROSE");
/*     */     
/*     */ 
/* 326 */     vampirePowerStart = config.getInt("VAMPIRE.Power.Start", 10000);
/* 327 */     vampireKillSpreadCurse = config.getBoolean("VAMPIRE.Kill.SpreadCurse", true);
/*     */     
/* 329 */     vampireTimePowerGained = config.getDouble("VAMPIRE.Time.PowerGained", 15.0D);
/*     */     
/* 331 */     vampireKillPowerCreatureGain = config.getInt("VAMPIRE.Power.Kill.CreatureGain", 100);
/*     */     
/* 333 */     vampireKillPowerPlayerGain = config.getInt("VAMPIRE.Power.Kill.PlayerGain", 500);
/*     */     
/* 335 */     vampireDeathPowerPenalty = config.getInt("VAMPIRE.Power.DeathPenalty", 10000);
/*     */     
/* 337 */     vampireDamageFactor = config.getDouble("VAMPIRE.DamageFactor.AttackBonus", 0.3D);
/*     */     
/* 339 */     vampireDamageReceivedFactor = config.getDouble("VAMPIRE.DamageFactor.DefenseBonus", 0.8D);
/*     */     
/* 341 */     woodFactor = config.getDouble("VAMPIRE.DamageFactor.Wood", 1.5D);
/* 342 */     vampireBurnInSunlight = config.getBoolean("VAMPIRE.Burn.InSunlight", true);
/*     */     
/* 344 */     vampireBurnMessageEnabled = config.getBoolean("VAMPIRE.Burn.MessageEnabled", true);
/*     */     
/* 346 */     vampireCombustFireTicks = config.getInt("VAMPIRE.Burn.FireTicks", 3);
/*     */     
/* 348 */     jumpDeltaSpeed = config.getDouble("VAMPIRE.JumpDelta", 1.2D);
/* 349 */     jumpBloodCost = config.getInt("VAMPIRE.Power.JumpCost", 1000);
/* 350 */     vampireTimeHealthGained = config.getDouble("VAMPIRE.Time.HealthGained", 0.5D);
/*     */     
/* 352 */     vampireHealthCost = config.getDouble("VAMPIRE.Power.HealingCost", 60.0D);
/* 353 */     vampireDrowningCost = config.getInt("VAMPIRE.Power.DrowningCost", 90);
/* 354 */     vampireTeleportCost = config.getInt("VAMPIRE.Power.TeleportCost", 9000);
/* 355 */     vampireTeleportMaterial = config.getString("VAMPIRE.TeleportMarker.Material", "RED_ROSE");
/*     */     
/* 357 */     vampireTruceString = config.getStringList("VAMPIRE.Truce.Creatures");
/* 358 */     vampireMaterial = config.getString("VAMPIRE.Spell.Material", "BOOK");
/* 359 */     vampireHelmet = config.getString("VAMPIRE.Burn.HelmetProtection", "GOLD_HELMET");
/*     */     
/* 361 */     vampireWeaponsString = config.getStringList("VAMPIRE.Weapon.Restrictions");
/*     */     
/* 363 */     vampireArmorString = config.getStringList("VAMPIRE.Armor");
/* 364 */     vampireHungerRegainPlayer = config.getInt("VAMPIRE.GainHunger.Player");
/* 365 */     vampireHungerRegainMob = config.getInt("VAMPIRE.GainHunger.Mob");
/*     */     
/* 367 */     vampireAltarInfectMaterial = config.getString("VAMPIRE.Altar.Infect.Material", "GOLD_BLOCK");
/*     */     
/* 369 */     vampireAltarInfectMaterialSurround = config.getString("VAMPIRE.Altar.Infect.Surrounding.Material", "OBSIDIAN");
/*     */     
/* 371 */     vampireAltarInfectMaterialRadius = config.getDouble("VAMPIRE.Altar.Infect.Surrounding.Radius", 7.0D);
/*     */     
/* 373 */     vampireAltarInfectMaterialSurroundCount = config.getInt("VAMPIRE.Altar.Infect.Surrounding.Count", 20);
/*     */     
/* 375 */     vampireAltarInfectMaterialsString = config.getStringList("VAMPIRE.Altar.Infect.Recipe.Materials");
/*     */     
/* 377 */     vampireAltarInfectQuantities = config.getIntegerList("VAMPIRE.Altar.Infect.Recipe.Quantities");
/*     */     
/*     */ 
/* 380 */     vampireAltarCureMaterial = config.getString("VAMPIRE.Altar.Cure.Material", "LAPIS_BLOCK");
/*     */     
/* 382 */     vampireAltarCureMaterialSurround = config.getString("VAMPIRE.Altar.Cure.Surrounding.Material", "GLOWSTONE");
/*     */     
/* 384 */     vampireAltarCureMaterialRadius = config.getDouble("VAMPIRE.Altar.Cure.Surrounding.Radius", 7.0D);
/*     */     
/* 386 */     vampireAltarCureMaterialSurroundCount = config.getInt("VAMPIRE.Altar.Cure.Surrounding.Count", 20);
/*     */     
/* 388 */     vampireAltarCureMaterialsString = config.getStringList("VAMPIRE.Altar.Cure.Recipe.Materials");
/*     */     
/* 390 */     vampireAltarCureQuantities = config.getIntegerList("VAMPIRE.Altar.Cure.Recipe.Quantities");
/*     */     
/*     */ 
/* 393 */     vampireHallMessage = config.getString("VAMPIRE.Hall.Message", "Vampires");
/*     */     
/*     */ 
/* 396 */     priestChurchWorld = config.getString("PRIEST.Church.World", "world");
/* 397 */     priestChurchLocationX = config.getInt("PRIEST.Church.Location.X", 0);
/* 398 */     priestChurchLocationY = config.getInt("PRIEST.Church.Location.Y", 80);
/* 399 */     priestChurchLocationZ = config.getInt("PRIEST.Church.Location.Z", 0);
/* 400 */     priestBanishWorld = config.getString("PRIEST.Banish.World", "world");
/* 401 */     priestBanishLocationX = config.getInt("PRIEST.Banish.Location.X", 0);
/* 402 */     priestBanishLocationY = config.getInt("PRIEST.Banish.Location.Y", 80);
/* 403 */     priestBanishLocationZ = config.getInt("PRIEST.Banish.Location.Z", 0);
/*     */     
/* 405 */     priestPowerStart = config.getInt("PRIEST.Power.StartingAmount", 10000);
/* 406 */     priestDeathPowerPenalty = config.getInt("PRIEST.Power.DeathPenalty", 2000);
/*     */     
/* 408 */     priestDamageFactorAttackSuper = config.getDouble("PRIEST.DamageFactor.AttackBonusSuper", 1.0D);
/*     */     
/* 410 */     priestDamageFactorAttackHuman = config.getDouble("PRIEST.DamageFactor.AttackBonusHuman", 0.0D);
/*     */     
/* 412 */     priestPowerBanish = config.getInt("PRIEST.Power.Banish", 4000);
/* 413 */     priestPowerHeal = config.getInt("PRIEST.Power.HealOther", 1000);
/* 414 */     priestHealAmount = config.getInt("PRIEST.Spell.HealAmount", 10);
/* 415 */     priestPowerExorcise = config.getInt("PRIEST.Power.Exorcise", 9000);
/* 416 */     priestPowerCure = config.getInt("PRIEST.Power.Cure", 1000);
/* 417 */     priestPowerDrain = config.getInt("PRIEST.Power.Drain", 1000);
/* 418 */     priestPowerGuardianAngel = config.getInt("PRIEST.Power.GuardianAngel", 5000);
/*     */     
/* 420 */     priestDrainFactor = config.getDouble("PRIEST.Spell.DrainFactor", 0.15D);
/* 421 */     priestFireTicks = config.getInt("PRIEST.DamageFactor.FireTicks", 50);
/* 422 */     priestAltarMaterial = config.getString("PRIEST.Church.AltarMaterial", "DIAMOND_BLOCK");
/*     */     
/* 424 */     priestMaterialsString = config.getStringList("PRIEST.Spell.Material");
/* 425 */     priestSpellGuardianAngel = config.getString("PRIEST.Spell.Material.GuardianAngel", "WOOL");
/*     */     
/* 427 */     priestAltarMaterialsString = config.getStringList("PRIEST.Church.Recipe.Materials");
/*     */     
/* 429 */     priestAltarQuantities = config.getIntegerList("PRIEST.Church.Recipe.Quantities");
/*     */     
/* 431 */     priestDonationMaterialsString = config.getStringList("PRIEST.Church.Donation.Materials");
/*     */     
/* 433 */     priestDonationRewards = config.getIntegerList("PRIEST.Church.Donation.Rewards");
/*     */     
/* 435 */     priestArmorString = config.getStringList("PRIEST.Armor");
/* 436 */     priestWeaponsString = config.getStringList("PRIEST.Weapon.Restrictions");
/*     */     
/*     */ 
/* 439 */     ghoulPowerStart = config.getInt("GHOUL.Power.Start", 5000);
/* 440 */     ghoulKillSpreadCurse = config.getBoolean("GHOUL.Kill.SpreadCurse", true);
/*     */     
/* 442 */     ghoulKillPowerCreatureGain = config.getInt("GHOUL.Power.Kill.CreatureGain", 200);
/*     */     
/* 444 */     ghoulKillPowerPlayerGain = config.getInt("GHOUL.Power.Kill.PlayerGain", 1000);
/*     */     
/* 446 */     ghoulDeathPowerPenalty = config.getInt("GHOUL.Power.DeathPenalty", 2000);
/*     */     
/* 448 */     ghoulDamageReceivedFactor = config.getDouble("GHOUL.DamageFactor.DefenseBonus", 0.65D);
/*     */     
/* 450 */     ghoulWeaponsString = config.getStringList("GHOUL.Weapon.Restrictions");
/* 451 */     ghoulTruceString = config.getStringList("GHOUL.Truce.Creatures");
/* 452 */     ghoulDamageFactor = config.getDouble("GHOUL.DamageFactor.AttackBonus", 2.0D);
/*     */     
/* 454 */     ghoulDamageWater = config.getInt("GHOUL.WaterDamage", 4);
/* 455 */     ghoulHealthGained = config.getDouble("GHOUL.Time.HealthGained", 0.1D);
/* 456 */     ghoulMaterial = config.getString("GHOUL.Summon.Material", "PORK");
/* 457 */     ghoulBondMaterial = config.getString("GHOUL.UnholyBond.Material", "BONE");
/*     */     
/* 459 */     ghoulPowerSummonCost = config.getInt("GHOUL.Power.Summon", 1000);
/* 460 */     ghoulPowerBond = config.getInt("GHOUL.Power.UnholyBond", 50);
/* 461 */     ghoulWeaponImmunityString = config.getStringList("GHOUL.Immunity");
/* 462 */     ghoulArmorString = config.getStringList("GHOUL.Armor");
/* 463 */     ghoulRightClickSummon = config.getBoolean("GHOUL.RightClickSummon");
/* 464 */     ghoulCureChance = config.getDouble("GHOUL.CureChance");
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
/* 479 */     wereHealthGained = config.getDouble("Were.Time.HealthGained", 0.2D);
/* 480 */     wolfMaterial = config.getString("Were.Material.Summon", "PORK");
/* 481 */     werePowerSummonCost = config.getInt("Were.Power.Summon", 2000);
/* 482 */     wolfTruce = config.getBoolean("Were.WolfTruce", true);
/* 483 */     dashMaterial = config.getString("Were.Material.Dash", "FEATHER");
/* 484 */     wolfbaneMaterial = config.getString("Were.Wolfbane.Trigger", "BOWL");
/* 485 */     wereWolfbaneMaterialsString = config.getStringList("Were.Wolfbane.Materials");
/*     */     
/* 487 */     wereWolfbaneQuantities = config.getIntegerList("Were.Wolfbane.Quantities");
/*     */     
/* 489 */     wereArmorString = config.getStringList("Were.Armor");
/* 490 */     wereWeaponsString = config.getStringList("Were.Weapon.Restrictions");
/*     */     
/* 492 */     demonPowerStart = config.getInt("DEMON.Power.Start", 10000);
/* 493 */     demonDeathPowerPenalty = config.getInt("DEMON.Power.DeathPenalty", 10000);
/*     */     
/* 495 */     demonKillPowerCreatureGain = config.getInt("DEMON.Power.CreatureKill", 20);
/*     */     
/* 497 */     demonKillPowerPlayerGain = config.getInt("DEMON.Power.PlayerKill", 100);
/* 498 */     demonPowerGain = config.getInt("DEMON.Power.Gain", 40);
/* 499 */     demonPowerLoss = config.getInt("DEMON.Power.Loss", 4);
/* 500 */     demonPowerFireball = config.getInt("DEMON.Power.Fireball", 2000);
/* 501 */     demonHealing = config.getInt("DEMON.Healing", 1);
/* 502 */     demonMaterial = config.getString("DEMON.Fireball.Material", "REDSTONE");
/* 503 */     demonFireballDamage = config.getInt("DEMON.Fireball.Damage", 10);
/* 504 */     demonPowerSnare = config.getInt("DEMON.Power.Snare", 1000);
/* 505 */     demonSnareDuration = config.getInt("DEMON.Snare.Duration", 10000);
/* 506 */     demonSnareMaterial = config.getString("DEMON.Snare.Material", "INK_SACK");
/*     */     
/* 508 */     demonSnowballAmount = config.getInt("DEMON.SnowballAmount", 30);
/* 509 */     demonArmorString = config.getStringList("DEMON.Armor");
/* 510 */     demonWeaponsString = config.getStringList("DEMON.Weapon.Restrictions");
/* 511 */     demonFireTicks = config.getInt("DEMON.DamageFactor.FireTicks", 50);
/* 512 */     demonConvertPower = config.getInt("DEMON.Power.Convert", 2000);
/* 513 */     demonHallMessage = config.getString("DEMON.Hall.Message", "Demons");
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
/* 542 */     hunterHallMessage = config.getString("WitchHunter.Hall.Message", "WitchHunter");
/*     */     
/* 544 */     hunterMaxBounties = config.getInt("WitchHunter.Bounty.MaxNumber", 5);
/* 545 */     hunterWeaponsString = config.getStringList("WitchHunter.Weapon.Restrictions");
/*     */     
/*     */ 
/* 548 */     angelHealHealthGain = config.getInt("Angel.Power.Heal.HealthGain");
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
/* 559 */     angelJumpMaterial = config.getString("Angel.Materials.Jump", "YELLOW_FLOWER");
/*     */     
/* 561 */     angelCureMaterial = config.getString("Angel.Materials.Cure", "PAPER");
/* 562 */     angelSummonCowMaterial = config.getString("Angel.Materials.Summon.Cow", "RAW_BEEF");
/*     */     
/* 564 */     angelSummonPigMaterial = config.getString("Angel.Materials.Summon.Pig", "PORK");
/*     */     
/* 566 */     angelSummonWolfMaterial = config.getString("Angel.Materials.Summon.Wolf", "BONE");
/*     */     
/* 568 */     angelHealMaterial = config.getString("Angel.Materials.Heal", "FEATHER");
/* 569 */     angelArmorString = config.getStringList("Angel.Armor");
/* 570 */     angelWeaponsString = config.getStringList("Angel.Weapons.Restrictions");
/*     */     
/* 572 */     for (String weapon : angelWeaponsString) {
/* 573 */       angelWeapons.add(Material.getMaterial(weapon));
/*     */     }
/*     */     
/* 576 */     for (String armor : angelArmorString) {
/* 577 */       angelArmor.add(Material.getMaterial(armor));
/*     */     }
/*     */     
/* 580 */     for (String wood : woodMaterialsString) {
/* 581 */       woodMaterials.add(Material.getMaterial(wood));
/*     */     }
/*     */     
/* 584 */     for (String food : foodMaterialsString) {
/* 585 */       foodMaterials.add(Material.getMaterial(food));
/*     */     }
/*     */     
/* 588 */     for (String block : burnableBlocksString) {
/* 589 */       burnableBlocks.add(Material.getMaterial(block));
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
/* 600 */       priestSpellMaterials.add(Material.getMaterial(material));
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
/* 611 */       ghoulWeapons.add(Material.getMaterial(weapon));
/*     */     }
/*     */     
/* 614 */     for (String weapon : demonWeaponsString) {
/* 615 */       demonWeapons.add(Material.getMaterial(weapon));
/*     */     }
/*     */     
/* 618 */     for (String weapon : hunterWeaponsString) {
/* 619 */       hunterWeapons.add(Material.getMaterial(weapon));
/*     */     }
/*     */     
/* 622 */     for (String weapon : priestWeaponsString) {
/* 623 */       priestWeapons.add(Material.getMaterial(weapon));
/*     */     }
/*     */     
/* 626 */     for (String weapon : vampireWeaponsString) {
/* 627 */       vampireWeapons.add(Material.getMaterial(weapon));
/*     */     }
/*     */     
/* 630 */     for (String weapon : wereWeaponsString) {
/* 631 */       wereWeapons.add(Material.getMaterial(weapon));
/*     */     }
/*     */     
/* 634 */     for (String weapon : ghoulWeaponImmunityString) {
/* 635 */       ghoulWeaponImmunity.add(Material.getMaterial(weapon));
/*     */     }
/*     */     
/* 638 */     for (String armor : hunterArmorString) {
/* 639 */       hunterArmor.add(Material.getMaterial(armor));
/*     */     }
/*     */     
/* 642 */     for (String armor : ghoulArmorString) {
/* 643 */       ghoulArmor.add(Material.getMaterial(armor));
/*     */     }
/*     */     
/* 646 */     for (String armor : demonArmorString) {
/* 647 */       demonArmor.add(Material.getMaterial(armor));
/*     */     }
/*     */     
/* 650 */     for (String armor : priestArmorString) {
/* 651 */       priestArmor.add(Material.getMaterial(armor));
/*     */     }
/*     */     
/* 654 */     for (String armor : vampireArmorString) {
/* 655 */       vampireArmor.add(Material.getMaterial(armor));
/*     */     }
/*     */     
/* 658 */     for (String armor : wereArmorString) {
/* 659 */       wereArmor.add(Material.getMaterial(armor));
/*     */     }
/*     */     
/* 662 */     for (int i = 0; i < vampireAltarInfectMaterialsString.size(); i++) {
/* 663 */       Material material = Material.getMaterial((String)vampireAltarInfectMaterialsString.get(i));
/*     */       
/* 665 */       int quantity = 1;
/*     */       try {
/* 667 */         quantity = ((Integer)vampireAltarInfectQuantities.get(i)).intValue();
/*     */       } catch (Exception e) {
/* 669 */         e.printStackTrace();
/* 670 */         SupernaturalsPlugin.log("Invalid VAMPIRE Infect Altar Quantities!");
/*     */       }
/*     */       
/* 673 */       vampireAltarInfectRecipe.materialQuantities.put(material, Integer.valueOf(quantity));
/*     */     }
/*     */     
/* 676 */     for (int i = 0; i < vampireAltarCureMaterialsString.size(); i++) {
/* 677 */       Material material = Material.getMaterial((String)vampireAltarCureMaterialsString.get(i));
/*     */       
/* 679 */       int quantity = 1;
/*     */       try {
/* 681 */         quantity = ((Integer)vampireAltarCureQuantities.get(i)).intValue();
/*     */       } catch (Exception e) {
/* 683 */         e.printStackTrace();
/* 684 */         SupernaturalsPlugin.log("Invalid VAMPIRE Cure Altar Quantities!");
/*     */       }
/*     */       
/* 687 */       vampireAltarCureRecipe.materialQuantities.put(material, Integer.valueOf(quantity));
/*     */     }
/*     */     
/* 690 */     for (int i = 0; i < priestAltarMaterialsString.size(); i++) {
/* 691 */       Material material = Material.getMaterial((String)priestAltarMaterialsString.get(i));
/*     */       
/* 693 */       int quantity = 1;
/*     */       try {
/* 695 */         quantity = ((Integer)priestAltarQuantities.get(i)).intValue();
/*     */       } catch (Exception e) {
/* 697 */         e.printStackTrace();
/* 698 */         SupernaturalsPlugin.log("Invalid PRIEST Altar Quantities!");
/*     */       }
/* 700 */       priestAltarRecipe.materialQuantities.put(material, Integer.valueOf(quantity));
/*     */     }
/*     */     
/* 703 */     for (int i = 0; i < wereWolfbaneMaterialsString.size(); i++) {
/* 704 */       Material material = Material.getMaterial((String)wereWolfbaneMaterialsString.get(i));
/*     */       
/* 706 */       int quantity = 1;
/*     */       try {
/* 708 */         quantity = ((Integer)wereWolfbaneQuantities.get(i)).intValue();
/*     */       } catch (Exception e) {
/* 710 */         e.printStackTrace();
/* 711 */         SupernaturalsPlugin.log("Invalid Wolfbane Quantities!");
/*     */       }
/* 713 */       wereWolfbaneRecipe.materialQuantities.put(material, Integer.valueOf(quantity));
/*     */     }
/*     */     
/* 716 */     for (int i = 0; i < priestDonationMaterialsString.size(); i++) {
/* 717 */       Material material = Material.getMaterial((String)priestDonationMaterialsString.get(i));
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


