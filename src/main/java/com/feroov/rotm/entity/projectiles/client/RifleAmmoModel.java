package com.feroov.rotm.entity.projectiles.client;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.projectiles.RifleAmmo;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RifleAmmoModel extends GeoModel<RifleAmmo>
{
    @Override
    public ResourceLocation getModelResource(RifleAmmo animatable) {
        return new ResourceLocation(ROTM.MOD_ID, "geo/rifleammo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RifleAmmo animatable) {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/projectile/rifleammo.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RifleAmmo animatable) {
        return new ResourceLocation(ROTM.MOD_ID, "animations/empty.animation.json");
    }
}