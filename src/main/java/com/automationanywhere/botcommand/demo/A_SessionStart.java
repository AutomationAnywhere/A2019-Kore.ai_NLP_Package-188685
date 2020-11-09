package com.automationanywhere.botcommand.demo;

import Utils.KoreSession;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;

import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.core.security.SecureString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;
//import java.net.http.HttpResponse;

/**
 * @author Brendan Sapience
 */


@BotCommand
@CommandPkg(
        label = "Start Session",
        name = "StartSession",
        description = "Start Session",
        icon = "kore1.svg",
        node_label = "Start Session",
        group_label="Admin",
        comment = true,
        text_color="#3771c8",
        background_color = "#3771c8"
)
public class A_SessionStart {

    private static final Logger logger = LogManager.getLogger(A_SessionStart.class);

    @Sessions
    private Map<String, Object> sessions;

    private static final Messages MESSAGES = MessagesFactory.getMessages("com.automationanywhere.botcommand.demo.messages");


    @com.automationanywhere.commandsdk.annotations.GlobalSessionContext
    private com.automationanywhere.bot.service.GlobalSessionContext globalSessionContext;

    public void setGlobalSessionContext(com.automationanywhere.bot.service.GlobalSessionContext globalSessionContext) {
        this.globalSessionContext = globalSessionContext;
    }

    @Execute
    public void start(@Idx(index = "1", type = TEXT) @Pkg(label = "Session name",  default_value_type = STRING, default_value = "Default") @NotEmpty String sessionName,
                      @Idx(index = "2", type = AttributeType.CREDENTIAL) @Pkg(label = "Webhook Client ID",  default_value_type = STRING, default_value = "") @NotEmpty SecureString ClientID,
                      @Idx(index = "3", type = AttributeType.CREDENTIAL) @Pkg(label = "Webhook Client Secret",  default_value_type = STRING, default_value = "") @NotEmpty SecureString ClientSecret,
                      @Idx(index = "4", type = TEXT) @Pkg(label = "Webhook URL",  default_value_type = STRING, default_value = "") @NotEmpty String WebhookURL
    ) throws Exception {

        // Check for existing session
        if (this.sessions.containsKey(sessionName)){
            throw new BotCommandException(MESSAGES.getString("SessionNameInUse",sessionName)) ;
        }
        KoreSession bms = new KoreSession(ClientID,ClientSecret,WebhookURL);
        this.sessions.put(sessionName, bms);

    }

    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }

}
