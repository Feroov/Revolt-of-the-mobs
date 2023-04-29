package com.feroov.rotm.entity.hostile.model;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.Gigahorse;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GigahorseModel extends GeoModel<Gigahorse>
{
    @Override
    public ResourceLocation getModelResource(Gigahorse animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "geo/gigahorse.json");
    }

    @Override
    public ResourceLocation getTextureResource(Gigahorse animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/gigahorse.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Gigahorse animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "animations/gigahorse.json");
    }

    @Override
    public void setCustomAnimations(Gigahorse animatable, long instanceId, AnimationState<Gigahorse> animationState)
    {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
