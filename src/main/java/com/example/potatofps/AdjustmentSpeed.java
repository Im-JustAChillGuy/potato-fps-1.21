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
public enum AdjustmentSpeed {
    SUPER_SLOW,
    SLOW,
    NORMAL,
    FAST,
    SUPER_FAST;

}
