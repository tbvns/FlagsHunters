package xyz.tbvns.flagshuntersv2.Load;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xyz.tbvns.flagshuntersv2.FlagsHuntersV2;

public class LoadSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENT = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FlagsHuntersV2.MODID);

    //Load Sound
    public static final RegistryObject<SoundEvent> GAME_START = soundEventRegistryObject("game_start");
    public static final RegistryObject<SoundEvent> NOTIF_SOUND = soundEventRegistryObject("notif_sound");
    public static final RegistryObject<SoundEvent> POS_FOUND_SOUND = soundEventRegistryObject("pos_found_sound");
    public static final RegistryObject<SoundEvent> TEAM_ELIMINATED = soundEventRegistryObject("team_eliminated");

    //Random Stuff
    private static RegistryObject<SoundEvent> soundEventRegistryObject(String name) {
        ResourceLocation id = new ResourceLocation(FlagsHuntersV2.MODID, name);
        return SOUND_EVENT.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(FlagsHuntersV2.MODID, name)));
    }
    //Load function
    public static void Load(IEventBus eventBus) {
        SOUND_EVENT.register(eventBus);
    }
}
