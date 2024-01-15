package modals;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;

public class ModalCommands extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (event.getName().equals("sup")){

            //Create a new Modal and display it to the user

            TextInput name = TextInput.create("sup-name", "Name", TextInputStyle.SHORT)
                    .setPlaceholder("Enter someones name")
                    .setMinLength(1)
                    .setRequired(true)
                    .build();

            TextInput message = TextInput.create("sup-message","Message", TextInputStyle.PARAGRAPH)
                    .setMinLength(10)
                    .setMaxLength(100)
                    .setRequired(true)
                    .setValue("I think you're awesome! Just kidding.")
                    .build();

            Modal modal = Modal.create("sup-modal", "Say Sup")
                    .addActionRows(ActionRow.of(name), ActionRow.of(message))
                    .build();

            event.replyModal(modal).queue();

        }else if(event.getName().equals("multiply")){

            //Make a modal to accept the two numbers to multiply
            TextInput operand1 = TextInput.create("operand1", "Operand 1", TextInputStyle.SHORT)
                    .setPlaceholder("Enter a number")
                    .setMinLength(1)
                    .setRequired(true)
                    .build();

            TextInput operand2 = TextInput.create("operand2", "Operand 2", TextInputStyle.SHORT)
                    .setPlaceholder("Enter a number")
                    .setMinLength(1)
                    .setRequired(true)
                    .build();

            Modal modal = Modal.create("multiply-modal", "Multiply")
                    .addActionRows(ActionRow.of(operand1), ActionRow.of(operand2))
                    .build();

            event.replyModal(modal).queue();
        }
    }

    public void queueModal(Guild guild, String guildID){
        if(guild.getGuildChannelById(guildID) != null){
            guild.upsertCommand("multiply", "Multiply two numbers together").queue();
            guild.upsertCommand("sup","say wassup to someone").queue();
        }
    }

}