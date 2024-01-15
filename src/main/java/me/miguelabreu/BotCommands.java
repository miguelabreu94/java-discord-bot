package me.miguelabreu;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class BotCommands extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if(event.getName().equals("fart")){ // command name

            event.deferReply().queue(); // awaiting message

            event.getHook().sendMessage("You just farted").queue(); // if we don't want users to see this message, .setEphemeral

        } else if (event.getName().equals("food")){

            OptionMapping option = event.getOption("name");

            if(option == null){
                event.reply("for some reason, the food name was not provided").queue();
                return;
            }

            String favouriteFood = option.getAsString();

            event.reply("Your favourite food is : "  + favouriteFood).queue();


        } else if (event.getName().equals("sum")){

            OptionMapping operand1 = event.getOption("operand1");
            OptionMapping operand2 = event.getOption("operand2");

            if(operand1 == null || operand2 == null){
                event.reply("no numbers were provided").queue();
                return;
            }

            int sum = operand1.getAsInt() + operand2.getAsInt();

            event.reply("The sum is : " + sum).queue();
        }



    }
}
