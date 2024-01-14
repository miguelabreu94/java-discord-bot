package me.miguelabreu;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import static io.github.cdimascio.dotenv.DslKt.dotenv;

public class DiscordBot {
    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure().load();
        System.out.println();
        
       JDA bot = JDABuilder.createDefault(dotenv.get("API_KEY"))
                .setActivity(Activity.playing("with a GameBoy Color"))
                .build();
    }
}