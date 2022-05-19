package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.microsoft.bot.builder.Bot;
import com.microsoft.bot.integration.BotFrameworkHttpAdapter;
import com.microsoft.bot.integration.Configuration;
import com.microsoft.bot.integration.spring.BotController;
import com.microsoft.bot.integration.spring.BotDependencyConfiguration;

@SpringBootApplication
@Import( { BotController.class } )
public class Application
    extends BotDependencyConfiguration
{
    public static void main( String[] args )
    {
        SpringApplication.run( Application.class, args );
    }

    @Bean
    public ConversationReferences getConversationReferences()
    {
        return new ConversationReferences();
    }

    @Bean
    public Bot getBot( ConversationReferences conversationReferences, Configuration configuration )
    {
        return new EchoBot( conversationReferences, getBotFrameworkHttpAdaptor( configuration ) );
    }

    @Override
    public BotFrameworkHttpAdapter getBotFrameworkHttpAdaptor( Configuration configuration )
    {
        return new ErrorHandler( configuration );
    }
}
