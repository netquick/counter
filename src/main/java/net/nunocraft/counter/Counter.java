package net.nunocraft.counter;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Counter.MOD_ID)
public class Counter {
    public static final String MOD_ID = "counter";
    public static Integer ticks = 0;
    public static Integer decimals = 0;
    public static Integer seconds = 0;
    public static Integer minutes = 0;
    public static Integer hours = 0;
    public static Integer days = 0;
    public String CounterString = "";
    public boolean active = false;
    private static final Logger LOGGER = LogManager.getLogger();

    public Counter() {
        /*/ Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);*/

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();





        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void renderScreen(RenderGameOverlayEvent event) {
        Minecraft.getInstance().font.draw(event.getMatrixStack(), CounterString, 10, 10, 0xFFAA00);

    }
    @SubscribeEvent
    public void clientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            if (active == true) {
                ticks = ticks + 1;
            }

            if (ticks == 2) {
                decimals = decimals + 1;
                ticks = 0;
            }
            if (decimals == 10) {
                seconds = seconds + 1;
                decimals = 0;
            }
            if (seconds == 60) {
                minutes = minutes + 1;
                seconds = 0;
            }
            if (minutes == 60) {
                hours = hours + 1;
                minutes = 0;
            }
            if (hours == 24) {
                days = days + 1;
                hours = 0;
            }

            CounterString = String.format("%02d", days) + "d : " + String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds) + "." + Integer.toString(decimals);
        }

        //test


    }
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        //event.player.sendMessage(new TextComponent("Command executed"), Util.NIL_UUID);

    }
    @SubscribeEvent
    public void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        active = true;
        resetVar();


    }

    public static void resetVar(){
        ticks = 0;
        decimals = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        days = 0;
    }







}
