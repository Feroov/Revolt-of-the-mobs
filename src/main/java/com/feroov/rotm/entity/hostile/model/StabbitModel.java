package com.feroov.rotm.entity.hostile.model;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.Stabbit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class StabbitModel extends GeoModel<Stabbit>
{
    @Override
    public ResourceLocation getModelResource(Stabbit animatable) {
        return new ResourceLocation(ROTM.MOD_ID, "geo/stabbit.json");
    }

    @Override
    public ResourceLocation getTextureResource(Stabbit animatable) {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/stabbit.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Stabbit animatable) {
        return new ResourceLocation(ROTM.MOD_ID, "animations/stabbit.json");
    }

    @Override
    public void setCustomAnimations(Stabbit animatable, long instanceId, AnimationState<Stabbit> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
