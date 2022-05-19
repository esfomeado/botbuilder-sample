package org.example;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.codepoetics.protonpack.collectors.CompletableFutures;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.microsoft.bot.builder.MessageFactory;
import com.microsoft.bot.builder.TurnContext;
import com.microsoft.bot.builder.teams.TeamsActivityHandler;
import com.microsoft.bot.builder.teams.TeamsInfo;
import com.microsoft.bot.integration.BotFrameworkHttpAdapter;
import com.microsoft.bot.schema.Activity;
import com.microsoft.bot.schema.ChannelAccount;
import com.microsoft.bot.schema.ConversationReference;

public class EchoBot
    extends TeamsActivityHandler
{
    private final ConversationReferences conversationReferences;

    private final BotFrameworkHttpAdapter adapter;

    public EchoBot( final ConversationReferences conversationReferences, final BotFrameworkHttpAdapter adapter )
    {
        this.conversationReferences = conversationReferences;
        this.adapter = adapter;
    }

    @Override
    protected CompletableFuture<Void> onMembersAdded( final List<ChannelAccount> membersAdded, final TurnContext turnContext )
    {
        final Activity activity = MessageFactory.text( "Hello" );
        activity.setProperties( "test", JsonNodeFactory.instance.textNode( "test" ) );

        final ConversationReference conversationReference = conversationReferences.get( "test" );

        return adapter.continueConversation( "", conversationReference, turnContext1 -> turnContext1.sendActivity( activity ).thenAccept( null ) );
    }

    @Override
    protected CompletableFuture<Void> onConversationUpdateActivity( final TurnContext turnContext )
    {
        addConversationReference( turnContext );
        return super.onConversationUpdateActivity( turnContext );
    }

    private void addConversationReference( final TurnContext turnContext )
    {
        final ConversationReference conversationReference = turnContext.getActivity().getConversationReference();
        conversationReferences.put( "test", conversationReference );
    }
}
