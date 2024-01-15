package me.miguelabreu;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import javax.security.auth.login.LoginException;

import static io.github.cdimascio.dotenv.DslKt.dotenv;

public class DiscordBot {
    public static void main(String[] args) throws LoginException, InterruptedException {

        Dotenv dotenv = Dotenv.configure().load();
        
       JDA bot = JDABuilder.createDefault(dotenv.get("API_KEY"))
                .setActivity(Activity.playing("with a GameBoy Color"))
               .enableIntents(GatewayIntent.MESSAGE_CONTENT)
               .addEventListeners(new BotCommands())
               .addEventListeners(new BotListeners())
               .build().awaitReady();

       // Global commands vs Guild (Server) commands
        // Global commands -> they can be used anywhere: any guild that the bot is in or also in DMs "bot.upsertCommand"
        // Guild commands -> they can only be used in a specific guild (server) "guild.upsertCommand"

        Guild guild = bot.getGuildById("1196223530510065705");

        if(guild != null){
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