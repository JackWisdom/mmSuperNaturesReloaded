/*     */ package com.mmiillkkaa.supernaturals.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
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
/*     */ public class GeometryUtil
/*     */ {
/*     */   public static ArrayList<Block> getBallBlocks(Block centerBlock, double radius)
/*     */   {
/*  33 */     return getRadiusBlocks(centerBlock, radius, true);
/*     */   }
/*     */   
/*     */   public static ArrayList<Block> getCubeBlocks(Block centerBlock, double radius)
/*     */   {
/*  38 */     return getRadiusBlocks(centerBlock, radius, false);
/*     */   }
/*     */   
/*     */   public static ArrayList<Block> getCubeBlocksAroundPlayer(Block centerBlock, double radius)
/*     */   {
/*  43 */     ArrayList<Block> retval = getRadiusBlocks(centerBlock, radius, false);
/*  44 */     retval.remove(centerBlock);
/*  45 */     retval.remove(centerBlock.getRelative(BlockFace.UP));
/*  46 */     return retval;
/*     */   }
/*     */   
/*     */   public static ArrayList<Block> getRadiusBlocks(Block centerBlock, double radius, boolean ball)
/*     */   {
/*  51 */     return getRadiusBlocks(centerBlock, radius, ball, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ArrayList<Block> getRadiusBlocks(Block centerBlock, double radius, boolean ball, double xFromRadiusFactor, double xToRadiusFactor, double yFromRadiusFactor, double yToRadiusFactor, double zFromRadiusFactor, double zToRadiusFactor)
/*     */   {
/*  61 */     ArrayList<Block> blocks = new ArrayList();
/*  62 */     int xFrom = (int)(-xFromRadiusFactor * radius);
/*  63 */     int xTo = (int)(xToRadiusFactor * radius);
/*  64 */     int yFrom = (int)(-yFromRadiusFactor * radius);
/*  65 */     int yTo = (int)(yToRadiusFactor * radius);
/*  66 */     int zFrom = (int)(-zFromRadiusFactor * radius);
/*  67 */     int zTo = (int)(zToRadiusFactor * radius);
/*     */     
/*  69 */     for (int y = yFrom; y <= yTo; y++) {
/*  70 */       for (int z = zFrom; z <= zTo; z++) {
/*  71 */         for (int x = xFrom; x <= xTo; x++) {
/*  72 */           if ((!ball) || (x * x + y * y + z * z <= radius * radius)) {
/*  73 */             blocks.add(centerBlock.getRelative(x, -y, z));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*  78 */     return blocks;
/*     */   }
/*     */   
/*     */ 
/*     */   public static double distanceBetweenLocations(Location location1, Location location2)
/*     */   {
/*  84 */     double X = location1.getX() - location2.getX();
/*  85 */     double Y = location1.getY() - location2.getY();
/*  86 */     double Z = location1.getZ() - location2.getZ();
/*  87 */     return Math.sqrt(X * X + Y * Y + Z * Z);
/*     */   }
/*     */   
/*     */   public static int countNearby(Block centerBlock, Material material, double radius)
/*     */   {
/*  92 */     ArrayList<Block> ballBlocks = getBallBlocks(centerBlock, radius);
/*     */     
/*  94 */     int count = 0;
/*  95 */     for (Block block : ballBlocks) {
/*  96 */       if (block.getType() == material) {
/*  97 */         count++;
/*     */       }
/*     */     }
/* 100 */     return count;
/*     */   }
/*     */ }


