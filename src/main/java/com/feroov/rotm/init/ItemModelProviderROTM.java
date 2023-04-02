package com.feroov.rotm.init;

import com.feroov.rotm.ROTM;
import com.feroov.rotm.item.ItemsROTM;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ItemModelProviderROTM extends ItemModelProvider
{
    public ItemModelProviderROTM(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ROTM.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
//        simpleItem(ModItems.BLACK_OPAL);
        withExistingParent(ItemsROTM.GUNSWINE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ROTM.MOD_ID,"block/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ROTM.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(ROTM.MOD_ID,"item/" + item.getId().getPath()));
    }
}
