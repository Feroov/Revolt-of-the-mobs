package com.feroov.rotm.entity.projectiles.client;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.projectiles.DeagleAmmo;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DeagleModel extends GeoModel<DeagleAmmo>
{
    @Override
    public ResourceLocation getModelResource(DeagleAmmo animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "geo/projectile/rifleammo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DeagleAmmo animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/projectile/rifleammo.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DeagleAmmo animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "animations/empty.animation.json");
    }
}