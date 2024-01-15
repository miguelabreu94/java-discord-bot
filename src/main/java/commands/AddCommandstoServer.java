package commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

// Global commands vs Guild (Server) commands
// Global commands -> they can be used anywhere: any guild that the bot is in or also in DMs "bot.upsertCommand"
// Guild commands -> they can only be used in a specific guild (server) "guild.upsertCommand"

public class AddCommandstoServer extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        String command = event.getName();
        switch(command){

            case "fart":
                event.deferReply().queue(); // awaiting message

                event.getHook().sendMessage("You just farted").queue(); // if we don't want users to see this message, .setEphemeral
            break;

            case "food":
                OptionMapping option = event.getOption("name");

                if(option == null){
                    event.reply("for some reason, the food name was not provided").queue();
                    return;
                }

                String favouriteFood = option.getAsString();

                event.reply("Your favourite food is : "  + favouriteFood).queue();
                break;

            case "sum":
                OptionMapping operand1 = event.getOption("operand1");
                OptionMapping operand2 = event.getOption("operand2");

                if(operand1 == null || operand2 == null){
                    event.reply("no numbers were provided").queue();
                    return;
                }

                int sum = operand1.getAsInt() + operand2.getAsInt();

                event.reply("The sum is : " + sum).queue();
        break;
        }

//        if(event.getName().equals("fart")){ // command name
//
//            event.deferReply().queue(); // awaiting message
//
//            event.getHook().sendMessage("You just farted").queue(); // if we don't want users to see this message, .setEphemeral
//
//        } else if (event.getName().equals("food")){
//
//            OptionMapping option = event.getOption("name");
//
//            if(option == null){
//                event.reply("for some reason, the food name was not provided").queue();
//                return;
//            }
//
//            String favouriteFood = option.getAsString();
//
//            event.reply("Your favourite food is : "  + favouriteFood).queue();
//
//
//        } else if (event.getName().equals("sum")){
//
//            OptionMapping operand1 = event.getOption("operand1");
//            OptionMapping operand2 = event.getOption("operand2");
//
//            if(operand1 == null || operand2 == null){
//                event.reply("no numbers were provided").queue();
//                return;
//            }
//
//            int sum = operand1.getAsInt() + operand2.getAsInt();
//
//            event.reply("The sum is : " + sum).queue();
//        }
    }

    public void addCommandstoServer(JDA bot, Guild guild, String guildID){
        if(guild.getGuildChannelById(guildID) != null){
            guild.upsertCommand("fart","fart really hard").queue();
            guild.upsertCommand("food", "Name your favourite food")
                    .addOption(OptionType.STRING,"name","the name of your favourite food", true)
                    .queue();
            guild.upsertCommand("sum","Had 2 numbers together")
//                    .addOption(OptionType.INTEGER,"operand1", "first number",true)
//                    .addOption(OptionType.INTEGER,"operand2","second number",true)
                    .addOptions(new OptionData(OptionType.INTEGER,"operand1","first number",true)
                                    .setRequiredRange(1,Integer.MAX_VALUE),
                            new OptionData(OptionType.INTEGER,"operand2","second number",true)
                                    .setRequiredRange(1,Integer.MAX_VALUE))
                    .queue();
        }

        // another way to add commands

//        CommandListUpdateAction commands = bot.updateCommands();
//
//        commands.addCommands(
//                Commands.slash("fart","fart really hard"),
//                Commands.slash("food","Announce your fav food")
//                        .addOption(OptionType.STRING,"food","the name of your fav food",true)
//                        ....
//        )
//
//        commands.queue();

    }
}
