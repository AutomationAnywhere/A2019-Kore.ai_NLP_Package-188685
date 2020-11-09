package Utils;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.model.Schema;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class RestResponse{
    private static final Messages MESSAGES = MessagesFactory.getMessages("com.automationanywhere.botcommand.demo.messages");
    private static final Logger logger = LogManager.getLogger(RestResponse.class);
    public static String ProcessNERResponseAsText(String JsonResponse) throws ParseException {

        JSONParser parse = new JSONParser();
        JSONObject jobj = (JSONObject) parse.parse(JsonResponse);

        String theText = "";
        // Setting up rows
        //System.out.println("DEBUG:"+JsonResponse);
        try{
            JSONArray AllTexts = (JSONArray) jobj.get("text");
            if(AllTexts.size() == 0){
                logger.error("Backend Error: "+JsonResponse);
                throw new BotCommandException(MESSAGES.getString("APIError",JsonResponse)) ;
               // return "";
            }
            theText = (String) AllTexts.get(0);
        }catch(ClassCastException e){
            //this means  the output doesnt contain an array
            logger.error("Backend Error: "+JsonResponse);
            throw new BotCommandException(MESSAGES.getString("APIError",JsonResponse)) ;
            //theText = "";
        }
       // JSONArray AllTexts = (JSONArray) jobj.get("text");

        return theText;
    }
    public static Table ProcessNERResponseAsTable(String RawText, String EntitySeparator, String TypeValueSeparator) throws ParseException {

        String[] AllEntities = RawText.split(EntitySeparator);

        Table table = new Table();

        List<Row> AllRows = new ArrayList<Row>();

        List<Schema> ListOfHeaders = new ArrayList<Schema>();
        ListOfHeaders.add(new Schema("entity"));
        ListOfHeaders.add(new Schema("value"));
        String theText = "";
        table.setSchema(ListOfHeaders);

        //System.out.println("DEBUG:"+RawText);
        try{

            if(AllEntities.length == 0){
                logger.error("Backend Error: "+RawText);
                throw new BotCommandException(MESSAGES.getString("APIError",RawText)) ;
            }
        }catch(ClassCastException e){
            //this means  the output doesnt contain an array
            logger.error("Backend Error: "+RawText);
            throw new BotCommandException(MESSAGES.getString("APIError",RawText)) ;
            //theText = "";
        }

        for(int i=0;i<AllEntities.length;i++){
            String theRawEntity = AllEntities[i];
            String[] EntityInfo = theRawEntity.split(TypeValueSeparator);
            String EntityType = theRawEntity.split(TypeValueSeparator)[0];
            String EntityValue = "";
            if(EntityInfo.length > 1){ EntityValue = theRawEntity.split(TypeValueSeparator)[1];}

            if(EntityValue.contains(",")){EntityValue = "\""+EntityValue+"\"";}

            Row aRow = new Row();
            List<com.automationanywhere.botcommand.data.Value> ListOfValues = new ArrayList<Value>();
            ListOfValues.add(new StringValue(EntityType));
            ListOfValues.add(new StringValue(EntityValue));

            aRow.setValues(ListOfValues);
            AllRows.add(aRow);
        }

        table.setRows(AllRows);
        return table;
    }

}