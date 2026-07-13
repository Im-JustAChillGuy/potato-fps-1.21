package com.example.potatofps.mixin;

import com.example.potatofps.PotatoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(value = EnvType.CLIENT)
@Mixin(EntityRenderer.class)
public class EntityRenderMixin<T extends Entity> {

    // NOTE: retargeted onto shouldRender() -- in current 1.21.11 EntityRenderer.render()
    // no longer takes an entity/x/y/z directly (it now renders from a pre-built
    // EntityRenderState), so the original render()-based cull can't be expressed the
    // same way anymore. shouldRender() still gets entity + camera position and is the
    // natural place to do a distance-based cull; returning false here skips the entity
    // before Minecraft even builds a render state for it, which is at least as cheap.
    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private void potatofps$cancelFarEntities(T entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) {
            return;
        }
        double distance = entity.distanceTo(client.player);
        if (distance > (double) PotatoConfig.entityRenderDistance) {
            cir.setReturnValue(false);
        }
    }
}
}
