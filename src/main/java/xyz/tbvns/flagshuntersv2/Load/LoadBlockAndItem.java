package xyz.tbvns.flagshuntersv2.Load;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xyz.tbvns.flagshuntersv2.Blocks.Flag;

import java.util.function.ToIntFunction;

public class LoadBlockAndItem {

        static ToIntFunction<BlockState> GlowState = BlockBehaviour.BlockStateBase::getLightEmission;

        // Create a Deferred Register to hold Blocks which will all be registered under the "flaghunter" namespace
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "flagshunters");
        // Create a Deferred Register to hold Items which will all be registered under the "flaghunter" namespace
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "flagshunters");
        //Blocks
        //Super Ice Block:

        public static final RegistryObject<Block> RED_FLAG = BLOCKS.register("redflag", () ->
                new Flag(BlockBehaviour.Properties.of()
                        .noCollission()
                        .sound(SoundType.WOOD)
                        .noOcclusion()
                        .lightLevel(state -> 5)
                ));

        public static final RegistryObject<Block> BLUE_FLAG = BLOCKS.register("blueflag", () ->
                new Flag(BlockBehaviour.Properties.of()
                        .noCollission()
                        .sound(SoundType.WOOD)
                        .noOcclusion()
                        .lightLevel(state -> 5)
                ));

        public static final RegistryObject<Block> GREEN_FLAG = BLOCKS.register("greenflag", () ->
                new Flag(BlockBehaviour.Properties.of()
                        .noCollission()
                        .sound(SoundType.WOOD)
                        .noOcclusion()
                        .lightLevel(state -> 5)
                ));
        public static final RegistryObject<Block> YELLOW_FLAG = BLOCKS.register("yellowflag", () ->
                new Flag(BlockBehaviour.Properties.of()
                        .noCollission()
                        .sound(SoundType.WOOD)
                        .noOcclusion()
                        .lightLevel(state -> 5)
                ));
        //Items / BlockItems
        //Super Ice Item:

        //TODO: Re add tabs
        //public static final RegistryObject<Item> RED_FLAG_ITEM = ITEMS.register("redflag", () ->
        //        new BlockItem(RED_FLAG.get(),
        //                new Item.Properties()
        //                        .tab(HunterFlagsCreativeTabs.INSTANCE)
        //        ));
//
        //public static final RegistryObject<Item> BLUE_FLAG_ITEM = ITEMS.register("blueflag", () ->
        //        new BlockItem(BLUE_FLAG.get(),
        //                new Item.Properties()
        //                        .tab(HunterFlagsCreativeTabs.INSTANCE)
        //        ));
//
        //public static final RegistryObject<Item> GREEN_FLAG_ITEM = ITEMS.register("greenflag", () ->
        //        new BlockItem(GREEN_FLAG.get(),
        //                new Item.Properties()
        //                        .tab(HunterFlagsCreativeTabs.INSTANCE)
        //        ));
//
        //public static final RegistryObject<Item> YELLOW_FLAG_ITEM = ITEMS.register("yellowflag", () ->
        //        new BlockItem(YELLOW_FLAG.get(),
        //                new Item.Properties()
        //                        .tab(HunterFlagsCreativeTabs.INSTANCE)
        //        ));

    public static void Load(IEventBus modEventBus) {
        //Register Blocks
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
    }
}
