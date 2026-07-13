/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  me.shedaniel.clothconfig2.api.AbstractConfigListEntry
 *  me.shedaniel.clothconfig2.api.ConfigBuilder
 *  me.shedaniel.clothconfig2.api.ConfigCategory
 *  me.shedaniel.clothconfig2.api.ConfigEntryBuilder
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2561
 *  net.minecraft.class_437
 */
package com.example.potatofps;

import com.example.potatofps.AdjustmentSpeed;
import com.example.potatofps.PotatoConfig;
import com.example.potatofps.PotatoConfigManager;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_437;

@Environment(value=EnvType.CLIENT)
public class PotatoConfigScreen {
    public static class_437 create(class_437 parent) {
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle((class_2561)class_2561.method_43470((String)"Potato FPS Settings")).setSavingRunnable(() -> PotatoConfigManager.save());
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory general = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"Performance"));
        general.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Enable Potato Mode"), PotatoConfig.potatoMode).setDefaultValue(true).setSaveConsumer(newValue -> {
            PotatoConfig.potatoMode = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Disable Entity Shadows"), PotatoConfig.disableShadows).setDefaultValue(true).setSaveConsumer(newValue -> {
            PotatoConfig.disableShadows = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Disable Clouds"), PotatoConfig.disableClouds).setDefaultValue(true).setSaveConsumer(newValue -> {
            PotatoConfig.disableClouds = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Disable Vignette"), PotatoConfig.disableVignette).setDefaultValue(true).setSaveConsumer(newValue -> {
            PotatoConfig.disableVignette = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Ultra Particle Reduction"), PotatoConfig.ultraParticleReduction).setDefaultValue(false).setSaveConsumer(newValue -> {
            PotatoConfig.ultraParticleReduction = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Reduce Entity Render Distance"), PotatoConfig.reduceEntityDistance).setDefaultValue(true).setSaveConsumer(newValue -> {
            PotatoConfig.reduceEntityDistance = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Disable Rain Splash Particles"), PotatoConfig.disableRainParticles).setDefaultValue(true).setSaveConsumer(newValue -> {
            PotatoConfig.disableRainParticles = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Minimum Render Distance"), PotatoConfig.minRender, 2, 32).setDefaultValue(8).setSaveConsumer(newValue -> {
            PotatoConfig.minRender = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Maximum Render Distance"), PotatoConfig.maxRender, 4, 32).setDefaultValue(16).setSaveConsumer(newValue -> {
            PotatoConfig.maxRender = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Target FPS"), PotatoConfig.targetFps, 30, 240).setDefaultValue(70).setSaveConsumer(newValue -> {
            PotatoConfig.targetFps = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Show Potato HUD"), PotatoConfig.showHud).setDefaultValue(true).setSaveConsumer(newValue -> {
            PotatoConfig.showHud = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43470((String)"Adjustment Speed"), AdjustmentSpeed.class, (Enum)AdjustmentSpeed.values()[PotatoConfig.adjustmentSpeed]).setDefaultValue((Enum)AdjustmentSpeed.SLOW).setSaveConsumer(newValue -> {
            PotatoConfig.adjustmentSpeed = newValue.ordinal();
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Entity Render Distance"), PotatoConfig.entityRenderDistance, 16, 128).setDefaultValue(64).setSaveConsumer(value -> {
            PotatoConfig.entityRenderDistance = value;
            PotatoConfigManager.save();
        }).build());
        return builder.build();
    }
}
