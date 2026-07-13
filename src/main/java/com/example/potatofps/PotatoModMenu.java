package com.example.potatofps;

import com.example.potatofps.PotatoConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(value=EnvType.CLIENT)
public class PotatoModMenu
implements ModMenuApi {
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> PotatoConfigScreen.create(parent);
    }
}
