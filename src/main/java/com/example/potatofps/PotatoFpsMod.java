/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.ClientModInitializer
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
 *  net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
 *  net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
 *  net.minecraft.class_2561
 *  net.minecraft.class_304
 *  net.minecraft.class_304$class_11900
 *  net.minecraft.class_310
 *  net.minecraft.class_3675$class_307
 *  net.minecraft.class_4063
 */
package com.example.potatofps;

import com.example.potatofps.PotatoConfig;
import com.example.potatofps.PotatoConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.class_2561;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_3675;
import net.minecraft.class_4063;

@Environment(value=EnvType.CLIENT)
public class PotatoFpsMod
implements ClientModInitializer {
    public static float particleMultiplier = 1.0f;
    private class_304 toggleKey;
    private class_304 hudToggleKey;

    public void onInitializeClient() {
        PotatoConfigManager.load();
        this.toggleKey = KeyBindingHelper.registerKeyBinding((class_304)new class_304("key.potatofps.toggle", class_3675.class_307.field_1668, 79, class_304.class_11900.field_62556));
        this.hudToggleKey = KeyBindingHelper.registerKeyBinding((class_304)new class_304("key.potatofps.togglehud", class_3675.class_307.field_1668, 72, class_304.class_11900.field_62556));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.field_1724 == null) {
                return;
            }
            int fps = client.method_47599();
            particleMultiplier = fps < PotatoConfig.targetFps - 20 ? 0.2f : (fps < PotatoConfig.targetFps ? 0.5f : 1.0f);
            while (this.toggleKey.method_1436()) {
                PotatoConfig.potatoMode = !PotatoConfig.potatoMode;
                client.field_1724.method_7353((class_2561)class_2561.method_43470((String)("Potato Mode: " + (PotatoConfig.potatoMode ? "ON" : "OFF"))), true);
            }
            while (this.hudToggleKey.method_1436()) {
                PotatoConfig.showHud = !PotatoConfig.showHud;
                client.field_1724.method_7353((class_2561)class_2561.method_43470((String)("Potato HUD: " + (PotatoConfig.showHud ? "ON" : "OFF"))), true);
            }
            if (!PotatoConfig.potatoMode) {
                return;
            }
            if (PotatoConfig.disableClouds) {
                client.field_1690.method_42528().method_41748((Object)class_4063.field_18162);
            }
            if (PotatoConfig.disableShadows) {
                client.field_1690.method_42435().method_41748((Object)false);
            }
            int render = (Integer)client.field_1690.method_42503().method_41753();
            int sim = (Integer)client.field_1690.method_42510().method_41753();
            if (fps < PotatoConfig.targetFps && render > PotatoConfig.minRender) {
                client.field_1690.method_42503().method_41748((Object)(render - 1));
                client.field_1690.method_42510().method_41748((Object)Math.max(PotatoConfig.minRender, sim - 1));
                client.field_1690.method_42517().method_41748((Object)0.5);
                System.out.println("PotatoFPS lowering render distance \u2192 " + (render - 1));
            }
            if (fps > PotatoConfig.targetFps + 25 && render < PotatoConfig.maxRender) {
                client.field_1690.method_42503().method_41748((Object)(render + 1));
                client.field_1690.method_42510().method_41748((Object)Math.min(PotatoConfig.maxRender, sim + 1));
                client.field_1690.method_42517().method_41748((Object)1.0);
                System.out.println("PotatoFPS increasing render distance \u2192 " + (render + 1));
            }
        });
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            class_310 client = class_310.method_1551();
            if (!PotatoConfig.potatoMode || !PotatoConfig.showHud || client.field_1724 == null) {
                return;
            }
            int fps = client.method_47599();
            int render = (Integer)client.field_1690.method_42503().method_41753();
            int color = fps < PotatoConfig.targetFps - 15 ? 0xFF5555 : (fps < PotatoConfig.targetFps ? 0xFFFF55 : 0x55FF55);
            drawContext.method_51433(client.field_1772, "\ud83e\udd54 Potato Mode | FPS: ", 5, 5, 0xFFFFFF, true);
            drawContext.method_51433(client.field_1772, String.valueOf(fps), 135, 5, color, true);
            drawContext.method_51433(client.field_1772, " | Target: " + PotatoConfig.targetFps + " | Render: " + render, 160, 5, 0xFFFFFF, true);
        });
    }
}
