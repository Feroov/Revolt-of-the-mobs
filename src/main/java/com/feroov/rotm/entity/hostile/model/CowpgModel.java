package com.feroov.rotm.entity.hostile.model;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.Cowpg;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class CowpgModel extends GeoModel<Cowpg>
{
    @Override
    public ResourceLocation getModelResource(Cowpg animatable) {
        return new ResourceLocation(ROTM.MOD_ID, "geo/cowpg.json");
    }

    @Override
    public ResourceLocation getTextureResource(Cowpg animatable) {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/cowpg.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Cowpg animatable) {
        return new ResourceLocation(ROTM.MOD_ID, "animations/cowpg.json");
    }

    @Override
    public void setCustomAnimations(Cowpg animatable, long instanceId, AnimationState<Cowpg> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
