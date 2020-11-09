package com.automationanywhere.botcommand.demo;

import Utils.KoreUtils;

public class StartSessionTests {

    public static void main(String[] args){

        String ClientID = "";
        String ClientSecret = "";
        String WebhookURL = "";
        String SessionName = "Default";

        A_SessionStart command = new A_SessionStart();

        try {
            command.start(SessionName, KoreUtils.StringToSecureString(ClientID), KoreUtils.StringToSecureString(ClientSecret), WebhookURL);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
