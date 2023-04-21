package com.feroov.rotm.entity.hostile.renderer;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.MonsterTank;
import com.feroov.rotm.entity.hostile.model.MonsterTankModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MonsterTankRenderer extends GeoEntityRenderer<MonsterTank>
{
    public MonsterTankRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new MonsterTankModel());
        this.shadowRadius = 0.64F;
    }

    @Override
    public ResourceLocation getTextureLocation(MonsterTank animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/monstertank.png");
    }

    @Override
    public void render(MonsterTank entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight)
    {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    protected float getDeathMaxRotation(MonsterTank entityLivingBaseIn)
    {
        return 0;
    }
}
