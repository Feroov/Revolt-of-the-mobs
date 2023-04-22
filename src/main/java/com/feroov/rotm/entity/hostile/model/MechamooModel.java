package com.feroov.rotm.entity.hostile.model;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.Mechamoo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MechamooModel extends GeoModel<Mechamoo>
{
    @Override
    public ResourceLocation getModelResource(Mechamoo animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "geo/mechamoo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Mechamoo animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/mechamoo.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Mechamoo animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "animations/mechamoo.json");
    }

    @Override
    public void setCustomAnimations(Mechamoo animatable, long instanceId, AnimationState<Mechamoo> animationState)
    {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
