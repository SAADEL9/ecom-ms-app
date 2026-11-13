package com.saad.ebankbot.controllers;

import com.saad.ebankbot.agents.EbankAiAgent;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EbankChatbotController {

    private final EbankAiAgent ebankAiAgent;

    public EbankChatbotController(EbankAiAgent ebankAiAgent) {
        this.ebankAiAgent = ebankAiAgent;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(name = "query", defaultValue = "bonjour") String query,
                       @RequestParam(name = "conversationId", defaultValue = "default") String conversationId) {
        return ebankAiAgent.chat(query,conversationId);
    }
}
