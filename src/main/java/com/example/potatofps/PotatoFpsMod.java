package com.example.potatofps;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

@Environment(value = EnvType.CLIENT)
public class PotatoFpsMod implements ClientModInitializer {
    public static float particleMultiplier = 1.0f;
    private KeyBinding toggleKey;
    private KeyBinding hudToggleKey;

    public void onInitializeClient() {
        PotatoConfigManager.load();

        // NOTE: KeyBinding.Category no longer has static constants like MISC in current
        // 1.21.11 -- categories are now created/registered via Category.create(String).
        KeyBinding.Category category = KeyBinding.Category.create("potato-fps");

        this.toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.potatofps.toggle", InputUtil.Type.KEYSYM, 79, category));
        this.hudToggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.potatofps.togglehud", InputUtil.Type.KEYSYM, 72, category));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) {
                return;
            }
            int fps = client.getCurrentFps();
            particleMultiplier = fps < PotatoConfig.targetFps - 20 ? 0.2f : (fps < PotatoConfig.targetFps ? 0.5f : 1.0f);

            while (this.toggleKey.wasPressed()) {
                PotatoConfig.potatoMode = !PotatoConfig.potatoMode;
                client.player.sendMessage(Text.literal("Potato Mode: " + (PotatoConfig.potatoMode ? "ON" : "OFF")), true);
            }
            while (this.hudToggleKey.wasPressed()) {
                PotatoConfig.showHud = !PotatoConfig.showHud;
                client.player.sendMessage(Text.literal("Potato HUD: " + (PotatoConfig.showHud ? "ON" : "OFF")), true);
            }

            if (!PotatoConfig.potatoMode) {
                return;
            }

            if (PotatoConfig.disableClouds) {
                client.options.getCloudRenderMode().setValue(CloudRenderMode.OFF);
            }
            if (PotatoConfig.disableShadows) {
                client.options.getEntityShadows().setValue(false);
            }

            int render = (Integer) client.options.getViewDistance().getValue();
            int sim = (Integer) client.options.getSimulationDistance().getValue();

            if (fps < PotatoConfig.targetFps && render > PotatoConfig.minRender) {
                client.options.getViewDistance().setValue(render - 1);
                client.options.getSimulationDistance().setValue(Math.max(PotatoConfig.minRender, sim - 1));
                client.options.getEntityDistanceScaling().setValue(0.5);
                System.out.println("PotatoFPS lowering render distance \u2192 " + (render - 1));
            }
            if (fps > PotatoConfig.targetFps + 25 && render < PotatoConfig.maxRender) {
                client.options.getViewDistance().setValue(render + 1);
                client.options.getSimulationDistance().setValue(Math.min(PotatoConfig.maxRender, sim + 1));
                client.options.getEntityDistanceScaling().setValue(1.0);
                System.out.println("PotatoFPS increasing render distance \u2192 " + (render + 1));
            }
        });

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (!PotatoConfig.potatoMode || !PotatoConfig.showHud || client.player == null) {
                return;
            }
            int fps = client.getCurrentFps();
            int render = (Integer) client.options.getViewDistance().getValue();
            int color = fps < PotatoConfig.targetFps - 15 ? 0xFF5555 : (fps < PotatoConfig.targetFps ? 0xFFFF55 : 0x55FF55);
            drawContext.drawText(client.textRenderer, "\ud83e\udd54 Potato Mode | FPS: ", 5, 5, 0xFFFFFF, true);
            drawContext.drawText(client.textRenderer, String.valueOf(fps), 135, 5, color, true);
            drawContext.drawText(client.textRenderer, " | Target: " + PotatoConfig.targetFps + " | Render: " + render, 160, 5, 0xFFFFFF, true);
        });
    }
}
