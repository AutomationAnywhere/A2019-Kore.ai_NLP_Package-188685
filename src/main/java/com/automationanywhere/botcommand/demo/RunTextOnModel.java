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
@CommandPkg(label="Run Model on Text",
        name="Run Model on Text",
        description="Run Model on Text",
        icon="kore1.svg",
        group_label="Models",
        node_label="Run Model on Text",
        return_type= STRING,
        return_label="Raw Output",
        return_required=true,
        comment = true,
        text_color="#3771c8",
        background_color = "#3771c8"
)


public class RunTextOnModel {

    private static final Logger logger = LogManager.getLogger(RunTextOnModel.class);

    private static final Messages MESSAGES = MessagesFactory.getMessages("com.automationanywhere.botcommand.demo.messages");

    @Sessions
    private Map<String, Object> sessions;

    @Execute
    public StringValue action(
            @Idx(index = "1", type = TEXT) @Pkg(label = "Session name", default_value_type = STRING,  default_value = "Default") @NotEmpty String sessionName,
            @Idx(index = "2", type = AttributeType.TEXT) @Pkg(label = "Text To Extract Entities From", default_value_type = STRING) @NotEmpty String TextToAnalyze
    )
    {

        KoreSession serv = (KoreSession) this.sessions.get(sessionName);
        String TextResult = "";
        RestRequests rRequests = new RestRequests(serv);
        TextToAnalyze = KoreUtils.sanitizeText(TextToAnalyze);
        String StrRes = "";
        try {

            StrRes = rRequests.InferenceOnNERModel(TextToAnalyze);
            //System.out.println("DEBUG:"+StrRes);
            TextResult = RestResponse.ProcessNERResponseAsText(StrRes);

        } catch (IOException e) {
            logger.error("API Error: "+StrRes);
            throw new BotCommandException(MESSAGES.getString("APIError",StrRes)) ;
        } catch (ParseException e) {
            logger.error("API Error: "+StrRes);
            throw new BotCommandException(MESSAGES.getString("APIError",StrRes)) ;
        }

        return new StringValue(TextResult);

    }
    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
}
