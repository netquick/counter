package net.nunocraft.counter.event;


import net.nunocraft.counter.Counter;
//import net.nunocraft.counter.command.ReturnHomeCommand;
import net.nunocraft.counter.command.ReturnHomeCommand;
import net.nunocraft.counter.command.SetHomeCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import net.nunocraft.counter.command.counterResetCommand;

@Mod.EventBusSubscriber(modid = Counter.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new SetHomeCommand(event.getDispatcher());
        new ReturnHomeCommand(event.getDispatcher());
        new counterResetCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
        if(!event.getOriginal().getLevel().isClientSide()) {
            event.getPlayer().getPersistentData().putIntArray(Counter.MOD_ID + "homepos",
                    event.getOriginal().getPersistentData().getIntArray(Counter.MOD_ID + "homepos"));
        }
    }
}
