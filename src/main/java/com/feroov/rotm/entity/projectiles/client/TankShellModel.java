package com.feroov.rotm.entity.projectiles.client;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.projectiles.TankShell;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TankShellModel extends GeoModel<TankShell>
{
    @Override
    public ResourceLocation getModelResource(TankShell animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "geo/projectile/rifleammo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TankShell animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/projectile/rifleammo.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TankShell animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "animations/empty.animation.json");
    }
}