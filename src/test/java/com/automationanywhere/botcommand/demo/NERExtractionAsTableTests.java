package com.automationanywhere.botcommand.demo;

import Utils.KoreSession;
import Utils.KoreUtils;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NERExtractionAsTableTests {

    public static void main(String[] args){

        String ClientID = "cs-cfae93a7-1789-5348-81be-b0a5610deee8";
        String ClientSecret = "PDNraDheMGUhglJD9EglipjO5ANoQnwru4uKkr/iaWE=";
        String WebhookURL = "https://bots.kore.ai/chatbot/hooks/st-99f2e683-1644-57fe-9dfb-c45f8eebc01d";
        String SessionName = "Default";
        String TextPayload = "Hey, what's the status on my order 4856TJV20561629 (store: Boston)";

        RunTextOnModel command0 = new RunTextOnModel();
        ConvertNEROutputToTable command = new ConvertNEROutputToTable();


        KoreSession myBackendServ = new KoreSession(KoreUtils.StringToSecureString(ClientID), KoreUtils.StringToSecureString(ClientSecret), WebhookURL);

        Map<String,Object> mso = new HashMap<String,Object>();
        mso.put(SessionName,myBackendServ);
        command0.setSessions(mso);
        command.setSessions(mso);

        StringValue KoreResp = command0.action(SessionName,TextPayload);

        String KoreRespStr = KoreResp.get();
        TableValue d = command.action(SessionName,KoreRespStr,";",":");
        Table table = d.get();
        List<Row> AllRows = table.getRows();

        for(int i=0;i<AllRows.size();i++){

            Row dv = AllRows.get(i);
            String aRowOfStrings = "";
            for(int j = 0;j < dv.getValues().size();j++){
                Value myVal = dv.getValues().get(j);
                if(aRowOfStrings.equals("")){
                    aRowOfStrings = myVal.get().toString();
                }else{
                    aRowOfStrings = aRowOfStrings +","+myVal.get().toString();
                }

            }
            System.out.println("DEBUG Row "+i+": "+aRowOfStrings);

        }

    }
}
