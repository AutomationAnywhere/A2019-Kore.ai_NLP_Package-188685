package com.automationanywhere.botcommand.demo;

import Utils.KoreSession;
import Utils.KoreUtils;
import Utils.RestRequests;
import Utils.RestResponse;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import com.automationanywhere.commandsdk.model.AttributeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Map;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;
//import java.net.http.HttpResponse;

/**
 * @author Bren Sapience
 */

@BotCommand
@CommandPkg(label="Sanitize Text",
        name="Sanitize Text",
        description="Sanitize Text",
        icon="kore1.svg",
        group_label="Utilities",
        node_label="Sanitize Text",
        return_type= STRING,
        return_label="Sanitized Text",
        return_required=true,
        comment = true,
        text_color="#3771c8",
        background_color = "#3771c8"
)


public class SanitizeText {
    private static final Logger logger = LogManager.getLogger(SanitizeText.class);

    private static final Messages MESSAGES = MessagesFactory.getMessages("com.automationanywhere.botcommand.demo.messages");

    @Sessions
    private Map<String, Object> sessions;

    @Execute
    public StringValue action(
            @Idx(index = "1", type = AttributeType.TEXT) @Pkg(label = "Text To Extract", default_value_type = STRING) @NotEmpty String TextToAnalyze
    )
    {
        TextToAnalyze = KoreUtils.sanitizeText(TextToAnalyze);
        return new StringValue(TextToAnalyze);
    }
    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
}
