package com.feroov.rotm.entity.hostile.model;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.Helicockter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class HelicockterModel extends GeoModel<Helicockter>
{
    @Override
    public ResourceLocation getModelResource(Helicockter animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "geo/helicockter.json");
    }

    @Override
    public ResourceLocation getTextureResource(Helicockter animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/helicockter.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Helicockter animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "animations/helicockter.json");
    }

    @Override
    public void setCustomAnimations(Helicockter animatable, long instanceId, AnimationState<Helicockter> animationState)
    {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
