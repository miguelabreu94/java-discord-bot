package me.miguelabreu;
import commands.AddCommandstoServer;
import listerners.BotListeners;
import modals.ModalCommands;
import modals.ModalListeners;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class DiscordBot {
    public static void main(String[] args) throws LoginException, InterruptedException {

        Dotenv dotenv = Dotenv.configure().load(); // environment variables
        
       JDA bot = JDABuilder.createDefault(dotenv.get("API_KEY"))
               .setActivity(Activity.playing("with a GameBoy Color"))
               .enableIntents(GatewayIntent.MESSAGE_CONTENT)
               .addEventListeners(new AddCommandstoServer(), new BotListeners(),
                       new ModalCommands(), new ModalListeners())
               .build().awaitReady();

        Guild guild = bot.getGuildById(dotenv.get("GUILD_ID"));

        AddCommandstoServer botL = new AddCommandstoServer();
        botL.addCommandstoServer(bot,guild,dotenv.get("GUILD_ID"));

        ModalCommands mCmd = new ModalCommands();
        mCmd.queueModal(guild,dotenv.get("GUILD_ID"));


        }
    }