package com.feroov.rotm.entity.hostile.model;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.Gunswine;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GunswineModel extends GeoModel<Gunswine>
{
    @Override
    public ResourceLocation getModelResource(Gunswine animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "geo/gunswine.json");
    }

    @Override
    public ResourceLocation getTextureResource(Gunswine animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/gunswine.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Gunswine animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "animations/gunswine.json");
    }

    @Override
    public void setCustomAnimations(Gunswine animatable, long instanceId, AnimationState<Gunswine> animationState)
    {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
