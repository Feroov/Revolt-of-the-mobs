package com.feroov.rotm.entity.hostile.model;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.Ninjorse;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class NinjorseModel extends GeoModel<Ninjorse>
{
    @Override
    public ResourceLocation getModelResource(Ninjorse animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "geo/ninjorse.json");
    }

    @Override
    public ResourceLocation getTextureResource(Ninjorse animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/ninjorse.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Ninjorse animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "animations/ninjorse.json");
    }

    @Override
    public void setCustomAnimations(Ninjorse animatable, long instanceId, AnimationState<Ninjorse> animationState)
    {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
