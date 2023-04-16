package com.feroov.rotm.entity.projectiles.client;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.projectiles.Rocket;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RocketModel extends GeoModel<Rocket>
{
    @Override
    public ResourceLocation getModelResource(Rocket animatable) {
        return new ResourceLocation(ROTM.MOD_ID, "geo/projectile/rocket.json");
    }

    @Override
    public ResourceLocation getTextureResource(Rocket animatable) {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/projectile/rocket.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Rocket animatable) {
        return new ResourceLocation(ROTM.MOD_ID, "animations/empty.animation.json");
    }
}