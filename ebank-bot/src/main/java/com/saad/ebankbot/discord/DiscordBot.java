package com.saad.ebankbot.discord;

import com.saad.ebankbot.agents.EbankAiAgent;
import com.zgamelogic.discord.annotations.DiscordController;
import com.zgamelogic.discord.annotations.DiscordMapping;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

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
    private void perform(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String query = event.getMessage().getContentRaw().trim();
        if (query.isEmpty()) return;

        event.getChannel().sendTyping().queue();

        executor.submit(() -> {
            try {
                String response = ebankAiAgent.chat(query, "discord-" + event.getChannel().getId());
                for (String part : split(response, 2000)) {
                    event.getMessage().reply(part).queue();
                }
            } catch (Exception e) {
                event.getMessage().reply("Sorry, something went wrong.").queue();
            }
        });
    }

    private static List<String> split(String text, int max) {
        List<String> parts = new ArrayList<>();
        for (int i = 0; i < text.length(); i += max) {
            parts.add(text.substring(i, Math.min(text.length(), i + max)));
        }
        return parts;
    }
}