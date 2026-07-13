/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1297
 *  net.minecraft.class_310
 *  net.minecraft.class_897
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.example.potatofps.mixin;

import com.example.potatofps.PotatoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_310;
import net.minecraft.class_897;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_897.class})
public class EntityRenderMixin<T extends class_1297> {
    @Inject(method={"method_3936"}, at={@At(value="HEAD")}, cancellable=true)
    private void cancelFarEntities(T entity, double x, double y, double z, float yaw, float tickDelta, CallbackInfo ci) {
        class_310 client = class_310.method_1551();
        if (client.field_1724 == null) {
            return;
        }
        double distance = entity.method_5739((class_1297)client.field_1724);
        if (distance > (double)PotatoConfig.entityRenderDistance) {
            ci.cancel();
        }
    }
}
