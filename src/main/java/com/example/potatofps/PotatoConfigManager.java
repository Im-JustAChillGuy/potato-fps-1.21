/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.loader.api.FabricLoader
 */
package com.example.potatofps;

import com.example.potatofps.PotatoConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

@Environment(value=EnvType.CLIENT)
public class PotatoConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "potatofps.json");

    public static void load() {
        try {
            if (!CONFIG_FILE.exists()) {
                PotatoConfigManager.save();
                return;
            }
            FileReader reader = new FileReader(CONFIG_FILE);
            JsonObject json = (JsonObject)GSON.fromJson((Reader)reader, JsonObject.class);
            reader.close();
            if (json == null) {
                return;
            }
            PotatoConfig.potatoMode = json.get("potatoMode").getAsBoolean();
            PotatoConfig.disableClouds = json.get("disableClouds").getAsBoolean();
            PotatoConfig.disableShadows = json.get("disableShadows").getAsBoolean();
            PotatoConfig.minRender = json.get("minRender").getAsInt();
            PotatoConfig.maxRender = json.get("maxRender").getAsInt();
            PotatoConfig.targetFps = json.get("targetFps").getAsInt();
            PotatoConfig.adjustmentSpeed = json.get("adjustmentSpeed").getAsInt();
            PotatoConfig.showHud = json.get("showHud").getAsBoolean();
            PotatoConfig.entityRenderDistance = json.get("entityRenderDistance").getAsInt();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            FileWriter writer = new FileWriter(CONFIG_FILE);
            JsonObject json = new JsonObject();
            json.addProperty("potatoMode", Boolean.valueOf(PotatoConfig.potatoMode));
            json.addProperty("disableClouds", Boolean.valueOf(PotatoConfig.disableClouds));
            json.addProperty("disableShadows", Boolean.valueOf(PotatoConfig.disableShadows));
            json.addProperty("minRender", (Number)PotatoConfig.minRender);
            json.addProperty("maxRender", (Number)PotatoConfig.maxRender);
            json.addProperty("targetFps", (Number)PotatoConfig.targetFps);
            json.addProperty("adjustmentSpeed", (Number)PotatoConfig.adjustmentSpeed);
            json.addProperty("showHud", Boolean.valueOf(PotatoConfig.showHud));
            json.addProperty("entityRenderDistance", (Number)PotatoConfig.entityRenderDistance);
            GSON.toJson((JsonElement)json, (Appendable)writer);
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
