package com.saad.ebankbot.discord;

import com.saad.ebankbot.agents.EbankAiAgent;
import com.zgamelogic.discord.annotations.DiscordController;
import com.zgamelogic.discord.annotations.DiscordMapping;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.ai.chat.prompt.Prompt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@DiscordController
public class DiscordBot {

    private final EbankAiAgent ebankAiAgent;
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public DiscordBot(EbankAiAgent ebankAiAgent) {
        this.ebankAiAgent = ebankAiAgent;
    }
    @DiscordMapping
    private void perform(MessageReceivedEvent event){
        if (event.getAuthor().isBot()) return;
        String query = event.getMessage().getContentRaw();
        String response = ebankAiAgent.chat(new Prompt(query), event.getChannel().getId());
        event.getChannel().sendMessage(response).queue();
    }
}