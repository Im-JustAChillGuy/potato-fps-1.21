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
import net.minecraft.text.Text;
import net.minecraft.client.gui.screen.Screen;

@Environment(value=EnvType.CLIENT)
public class PotatoConfigScreen {
    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(Text.literal((String)"Potato FPS Settings")).setSavingRunnable(() -> PotatoConfigManager.save());
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory general = builder.getOrCreateCategory(Text.literal((String)"Performance"));
        general.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle(Text.literal((String)"Enable Potato Mode"), PotatoConfig.potatoMode).setDefaultValue(true).setSaveConsumer(newValue -> {
            PotatoConfig.potatoMode = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle(Text.literal((String)"Disable Entity Shadows"), PotatoConfig.disableShadows).setDefaultValue(true).setSaveConsumer(newValue -> {
            PotatoConfig.disableShadows = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle(Text.literal((String)"Disable Clouds"), PotatoConfig.disableClouds).setDefaultValue(true).setSaveConsumer(newValue -> {
            PotatoConfig.disableClouds = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle(Text.literal((String)"Disable Vignette"), PotatoConfig.disableVignette).setDefaultValue(true).setSaveConsumer(newValue -> {
            PotatoConfig.disableVignette = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle(Text.literal((String)"Ultra Particle Reduction"), PotatoConfig.ultraParticleReduction).setDefaultValue(false).setSaveConsumer(newValue -> {
            PotatoConfig.ultraParticleReduction = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle(Text.literal((String)"Reduce Entity Render Distance"), PotatoConfig.reduceEntityDistance).setDefaultValue(true).setSaveConsumer(newValue -> {
            PotatoConfig.reduceEntityDistance = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle(Text.literal((String)"Disable Rain Splash Particles"), PotatoConfig.disableRainParticles).setDefaultValue(true).setSaveConsumer(newValue -> {
            PotatoConfig.disableRainParticles = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startIntSlider(Text.literal((String)"Minimum Render Distance"), PotatoConfig.minRender, 2, 32).setDefaultValue(8).setSaveConsumer(newValue -> {
            PotatoConfig.minRender = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startIntSlider(Text.literal((String)"Maximum Render Distance"), PotatoConfig.maxRender, 4, 32).setDefaultValue(16).setSaveConsumer(newValue -> {
            PotatoConfig.maxRender = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startIntSlider(Text.literal((String)"Target FPS"), PotatoConfig.targetFps, 30, 240).setDefaultValue(70).setSaveConsumer(newValue -> {
            PotatoConfig.targetFps = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startBooleanToggle(Text.literal((String)"Show Potato HUD"), PotatoConfig.showHud).setDefaultValue(true).setSaveConsumer(newValue -> {
            PotatoConfig.showHud = newValue;
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startEnumSelector(Text.literal((String)"Adjustment Speed"), AdjustmentSpeed.class, (Enum)AdjustmentSpeed.values()[PotatoConfig.adjustmentSpeed]).setDefaultValue((Enum)AdjustmentSpeed.SLOW).setSaveConsumer(newValue -> {
            PotatoConfig.adjustmentSpeed = newValue.ordinal();
        }).build());
        general.addEntry((AbstractConfigListEntry)entryBuilder.startIntSlider(Text.literal((String)"Entity Render Distance"), PotatoConfig.entityRenderDistance, 16, 128).setDefaultValue(64).setSaveConsumer(value -> {
            PotatoConfig.entityRenderDistance = value;
            PotatoConfigManager.save();
        }).build());
        return builder.build();
    }
}
