package com.automationanywhere.botcommand.demo;

import Utils.KoreSession;
import Utils.KoreUtils;
import com.automationanywhere.botcommand.data.impl.StringValue;

import java.util.HashMap;
import java.util.Map;

public class ClassificationAsTextTests {

    public static void main(String[] args){

        String ClientID = "cs-093a68a5-8d04-512c-8dde-1e457082b4ea";
        String ClientSecret = "+odnK34yCy32p2uLu//B/T1BgA6dqahoev4U7wChpu4=";
        String WebhookURL = "https://bots.kore.ai/chatbot/hooks/st-fbf9fd97-d50a-52e0-aa59-147a4c51bdc8";
        String SessionName = "Default";
        String TextPayload = "Hi, What's my PTO count?";
        RunTextOnModel command = new RunTextOnModel();

        KoreSession myBackendServ = new KoreSession(KoreUtils.StringToSecureString(ClientID), KoreUtils.StringToSecureString(ClientSecret), WebhookURL);

        Map<String,Object> mso = new HashMap<String,Object>();
        mso.put(SessionName,myBackendServ);
        command.setSessions(mso);

        StringValue d = command.action(SessionName,TextPayload);

            System.out.println("DEBUG: "+d.get());



    }
}
