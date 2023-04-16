package com.feroov.rotm.entity.projectiles.renderer;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.entity.projectiles.Rocket;
import com.feroov.rotm.entity.projectiles.client.RocketModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class RocketRenderer extends GeoEntityRenderer<Rocket>
{
    public RocketRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new RocketModel());
    }

    @Override
    public ResourceLocation getTextureLocation(Rocket animatable)
    {
        return new ResourceLocation(ROTM.MOD_ID, "textures/entity/projectile/rifleammo.png");
    }

    @Override
    public void preRender(PoseStack poseStack, Rocket animatable, BakedGeoModel model,
                          MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick,
                          int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay,
                red, green, blue, alpha);
        poseStack.scale(1.1F, 1.1F, 1.1F);
    }
}
