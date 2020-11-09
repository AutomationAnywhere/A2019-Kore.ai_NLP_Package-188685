package com.automationanywhere.botcommand.demo;

import Utils.KoreSession;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;

import java.util.Map;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;
//import java.net.http.HttpResponse;

/**
 * @author Brendan Sapience
 */

@BotCommand
@CommandPkg(
        label = "End Session",
        name = "EndSession",
        description = "End Session",
        icon = "kore1.svg",
        node_label = "End Session",
        group_label="Admin",
        comment = true,
        text_color="#3771c8",
        background_color = "#3771c8")

public class A_SessionEnd {

    @Sessions
    private Map<String, Object> sessions;

    @Execute
    public void end(
            @Idx(index = "1", type = TEXT) @Pkg(label = "Session name", default_value_type = STRING,
                    default_value = "Default") @NotEmpty String sessionName) {

        KoreSession serv  = (KoreSession) this.sessions.get(sessionName);

        sessions.remove(sessionName);

    }

    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
}