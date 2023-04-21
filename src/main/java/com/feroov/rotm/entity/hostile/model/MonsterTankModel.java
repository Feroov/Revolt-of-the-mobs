package com.feroov.rotm.entity.hostile.model;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.MonsterTank;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MonsterTankModel extends GeoModel<MonsterTank>
{
    @Override
    public ResourceLocation getModelResource(MonsterTank animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "geo/cowpg.json");
    }

    @Override
    public ResourceLocation getTextureResource(MonsterTank animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/cowpg.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MonsterTank animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "animations/cowpg.json");
    }

    @Override
    public void setCustomAnimations(MonsterTank animatable, long instanceId, AnimationState<MonsterTank> animationState)
    {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
