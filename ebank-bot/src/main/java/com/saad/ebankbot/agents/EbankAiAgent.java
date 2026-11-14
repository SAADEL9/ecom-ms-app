package com.saad.ebankbot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class EbankAiAgent {
    private final ChatClient chatClient;

    public EbankAiAgent(ChatClient.Builder chatClient, ChatMemory chatMemory , ToolCallbackProvider tools) {
        this.chatClient = chatClient
                .defaultSystem("""
                        vous etes un assistant qui ce charge de repondre aux questions 
                        de l'utilisateur à propos des clients et des comptes bancaires , en fonction du context fourni .
                        si aucun context n'est fourni , repond avec  JE NE SAIS PAS .
                        """)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build()).defaultTools(tools)
                .build();
    }


    public String chat( String query, String conversationId) {
        return chatClient.prompt()
                .user(query)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }
}
