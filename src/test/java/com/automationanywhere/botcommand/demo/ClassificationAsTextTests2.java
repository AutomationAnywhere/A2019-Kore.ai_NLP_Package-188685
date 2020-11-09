package com.automationanywhere.botcommand.demo;

import Utils.KoreSession;
import Utils.KoreUtils;
import com.automationanywhere.botcommand.data.impl.StringValue;

import java.util.HashMap;
import java.util.Map;

public class ClassificationAsTextTests2 {

    public static void main(String[] args){

        String ClientID = "cs-04bf758d-88a3-5069-a112-fb50bcc77bdf";
        String ClientSecret = "p/etCK0qSxCyZYlsg24WKDGc5R+0UDhiA8F1wNxdzaQ=";
        String WebhookURL = "https://bots.kore.ai/chatbot/hooks/st-e6e47b2d-ba4f-5984-adf3-3a8e4fb82690";
        String SessionName = "Default";
        String TextPayload = "Hello team, I’ve some items I’d like a shipping quote for from San Francisco that need delivery within a few days – they’re perishable. Box one is Approx 10 lbs, with dimensions: 13\" x 9\" x 11\". Box 2 is Approx 5 lbs with dimensions: 8\" x 8\" x 8\" Best Regards Chris Sullivan | Sales Engineer Mobile: (781) 600-4248 <tel:(781) 600-4248> | www.automationanywhere.com <http://www.automationanywhere.com> Automation Anywhere <http://www.automationanywhere.com> <https://twitter.com/AutomationAnywh> <https://www.linkedin.com/company/automation-anywhere/> <https://www.facebook.com/AutomationAnywhereSoftware> <https://www.instagram.com/automation_anywhere/> <https://www.youtube.com/c/automationanywhere> <https://smart.automationanywhere.com/v2/a/aari_english/5f7e0020bc0720f65e8ce899-zYZw9/httpswww.automationanywhere.comproductsaariutm_mediumemail-marketingutm_sourceemail-signature-bannerutm_campaignFY21-Q3-GLOB-DG-AARI-INTERNAL-EMAIL-BAN-EMLutm_contentaari-landing-page> ";
        RunTextOnModel command = new RunTextOnModel();

        KoreSession myBackendServ = new KoreSession(KoreUtils.StringToSecureString(ClientID), KoreUtils.StringToSecureString(ClientSecret), WebhookURL);

        Map<String,Object> mso = new HashMap<String,Object>();
        mso.put(SessionName,myBackendServ);
        command.setSessions(mso);

        StringValue d = command.action(SessionName,TextPayload);

            System.out.println("DEBUG: "+d.get());



    }
}
