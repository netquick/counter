package net.nunocraft.counter.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.Util;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.nunocraft.counter.Counter;




public class counterResetCommand {

     public counterResetCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("counter").then(Commands.literal("reset").executes((command) -> {
            return counterReset(command.getSource());
        })));
    }

    private int counterReset(CommandSourceStack source) throws CommandSyntaxException {
        /*ServerPlayerEntity player = source.asPlayer();
        BlockPos playerPos = player.getPosition();
        String pos = "(" + playerPos.getX() + ", " + playerPos.getY() + ", " + playerPos.getZ() + ")";*/

        //resetting Counter
        //Counter counter = new Counter();
        //counter.resetVar();

        //source.sendMessage(new StringTextComponent("reached command"), true);
        //source.getPlayerOrException().sendMessage(new TextComponent("Command executed"), Util.NIL_UUID);
        source.sendSuccess(new TextComponent("Counter reset"), true);
        //source.sendMessage(new TextComponent("Command executed"), Util.NIL_UUID);
        Counter.resetVar();
        return 1;
    }



}
