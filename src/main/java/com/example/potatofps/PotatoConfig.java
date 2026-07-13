/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 */
package com.example.potatofps;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(value=EnvType.CLIENT)
public class PotatoConfig {
    public static boolean potatoMode = true;
    public static boolean disableShadows = true;
    public static boolean disableClouds = true;
    public static boolean disableVignette = true;
    public static boolean ultraParticleReduction = false;
    public static boolean reduceEntityDistance = true;
    public static boolean disableRainParticles = true;
    public static int minRender = 4;
    public static int maxRender = 16;
    public static int targetFps = 70;
    public static boolean showHud = true;
    public static int adjustmentSpeed = 1;
    public static int entityRenderDistance = 64;
}
