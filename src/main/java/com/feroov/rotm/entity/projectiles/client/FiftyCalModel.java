package com.feroov.rotm.entity.projectiles.client;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.projectiles.FiftyCal;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FiftyCalModel extends GeoModel<FiftyCal>
{
    @Override
    public ResourceLocation getModelResource(FiftyCal animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "geo/projectile/50cal.json");
    }

    @Override
    public ResourceLocation getTextureResource(FiftyCal animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/projectile/50cal.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FiftyCal animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "animations/empty.animation.json");
    }
}