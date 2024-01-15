package listerners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import org.jetbrains.annotations.NotNull;

public class BotListeners extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        boolean isBot = event.getAuthor().isBot();

        if(!isBot){
             String messageSent = event.getMessage().getContentRaw();
             event.getChannel().sendMessage("Isto foi enviado: " +messageSent + "\n Que toino(a)").queue();}

        }

    }
