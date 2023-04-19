package com.feroov.rotm.entity.hostile.model;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.CluckNorris;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class CluckNorrisModel extends GeoModel<CluckNorris>
{
    @Override
    public ResourceLocation getModelResource(CluckNorris animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "geo/clucknorris.json");
    }

    @Override
    public ResourceLocation getTextureResource(CluckNorris animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/clucknorris.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CluckNorris animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "animations/clucknorris.json");
    }

    @Override
    public void setCustomAnimations(CluckNorris animatable, long instanceId, AnimationState<CluckNorris> animationState)
    {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
