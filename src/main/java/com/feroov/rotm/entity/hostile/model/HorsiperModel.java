package com.feroov.rotm.entity.hostile.model;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.Horsiper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class HorsiperModel extends GeoModel<Horsiper>
{
    @Override
    public ResourceLocation getModelResource(Horsiper animatable) {
        return new ResourceLocation(ROTM.MOD_ID, "geo/horsiper.json");
    }

    @Override
    public ResourceLocation getTextureResource(Horsiper animatable) {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/horsiper.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Horsiper animatable) {
        return new ResourceLocation(ROTM.MOD_ID, "animations/horsiper.json");
    }

    @Override
    public void setCustomAnimations(Horsiper animatable, long instanceId, AnimationState<Horsiper> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
