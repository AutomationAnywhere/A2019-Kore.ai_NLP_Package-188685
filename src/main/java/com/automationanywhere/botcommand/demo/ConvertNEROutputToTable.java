package com.automationanywhere.botcommand.demo;

import Utils.KoreSession;
import Utils.RestRequests;
import Utils.RestResponse;
import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import com.automationanywhere.commandsdk.model.AttributeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.util.Map;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;
import static com.automationanywhere.commandsdk.model.DataType.TABLE;
//import java.net.http.HttpResponse;

/**
 * @author Bren Sapience
 */

@BotCommand
@CommandPkg(
        label="Convert Entities To Table",
        name="Convert Entities To Table",
        description="Convert Entities To Table",
        icon="kore1.svg",
        group_label="Utilities",
        node_label="Convert Entities To Table",
        return_type= TABLE,
        return_label="Table of Entities",
        return_required=true,
        comment = true,
        text_color="#3771c8",
        background_color = "#3771c8"
)


public class ConvertNEROutputToTable {

    private static final Logger logger = LogManager.getLogger(ConvertNEROutputToTable.class);

    private static final Messages MESSAGES = MessagesFactory.getMessages("com.automationanywhere.botcommand.demo.messages");

    @Sessions
    private Map<String, Object> sessions;

    @Execute
    public TableValue action(
            @Idx(index = "1", type = TEXT) @Pkg(label = "Session name", default_value_type = STRING,  default_value = "Default") @NotEmpty String sessionName,
            @Idx(index = "2", type = AttributeType.TEXT) @Pkg(label = "Raw Text to Convert", default_value_type = STRING) @NotEmpty String TextToAnalyze,
            @Idx(index = "3", type = AttributeType.TEXT) @Pkg(label = "Separator between Entities", default_value_type = STRING, default_value = ";") @NotEmpty String EntitySeparator,
            @Idx(index = "4", type = AttributeType.TEXT) @Pkg(label = "Separator between Entity Type and Entity Value", default_value_type = STRING, default_value = ":") @NotEmpty String EntityTypeValueSeparator

    )
    {

        KoreSession serv = (KoreSession) this.sessions.get(sessionName);
        Table TableRes = new Table();
        RestRequests rRequests = new RestRequests(serv);

        try {
            TableRes = RestResponse.ProcessNERResponseAsTable(TextToAnalyze,EntitySeparator,EntityTypeValueSeparator);

        } catch (ParseException e) {
            logger.error("API Error: "+TextToAnalyze);
            throw new BotCommandException(MESSAGES.getString("APIError",TextToAnalyze)) ;
        }

        return new TableValue(TableRes);


    }
    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
}
