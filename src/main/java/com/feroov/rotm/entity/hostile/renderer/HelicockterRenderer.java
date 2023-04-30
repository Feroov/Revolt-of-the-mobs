package com.feroov.rotm.entity.hostile.renderer;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.hostile.Helicockter;
import com.feroov.rotm.entity.hostile.model.HelicockterModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HelicockterRenderer extends GeoEntityRenderer<Helicockter>
{
    public HelicockterRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new HelicockterModel());
        this.shadowRadius = 0.44F;
    }

    @Override
    public ResourceLocation getTextureLocation(Helicockter animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/clucknorris.png");
    }

    @Override
    public void render(Helicockter entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight)
    {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    protected float getDeathMaxRotation(Helicockter entityLivingBaseIn)
    {
        return 0;
    }
}
