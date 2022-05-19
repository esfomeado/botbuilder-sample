package org.example;

import java.util.Map;

import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.bot.integration.AdapterWithErrorHandler;
import com.microsoft.bot.integration.BotFrameworkHttpAdapter;
import com.microsoft.bot.integration.Configuration;
import com.microsoft.bot.schema.Activity;

public class ErrorHandler
    extends BotFrameworkHttpAdapter
{
    public ErrorHandler( final Configuration withConfiguration )
    {
        super( withConfiguration );

        setOnTurnError( ( turnContext, exception ) -> {
            final Activity activity = turnContext.getActivity();
            final Map<String, JsonNode> properties = activity.getProperties();

            // THIS SHOULD BE FALSE
            if ( properties.isEmpty() )
            {
                LoggerFactory.getLogger( AdapterWithErrorHandler.class ).error( "THIS SHOULD NOT BE EMPTY" );
            }

            System.out.println( exception );

            return null;
        } );
    }
}
