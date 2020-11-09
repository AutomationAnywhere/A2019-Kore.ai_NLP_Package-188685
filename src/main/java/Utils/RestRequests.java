package Utils;

import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import java.io.*;

public class RestRequests {
    private static final Messages MESSAGES = MessagesFactory.getMessages("com.automationanywhere.botcommand.demo.messages");
    private static final Logger LOGGER = LogManager.getLogger(RestRequests.class);

    private KoreSession bSession;

    public RestRequests(KoreSession session){this.bSession = session; }

    public String InferenceOnNERModel(String TextToExtract) throws IOException, ParseException {

        String JWTToken = KoreUtils.generateJWTToken(this.bSession);

        //System.out.println("DEBUG JWT:"+JWTToken);

        CloseableHttpClient client = HttpClientBuilder.create().build();

        // URL
        String CompleteURL = this.bSession.getWebhookURL();
        LOGGER.info("URL for NER Request: "+CompleteURL);
        String payload = KoreUtils.GetNERJsonPayload(TextToExtract);
        //System.out.println("DEBUG:"+payload);
        LOGGER.info("NER Payload: "+payload);
        HttpPost httpPost = new HttpPost(CompleteURL);
        httpPost.addHeader("Authorization" , "bearer "+JWTToken);
        httpPost.addHeader("Content-Type" , "application/json");
        StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);

        try{
            CloseableHttpResponse response = client.execute(httpPost);

            String Res = EntityUtils.toString(response.getEntity());
            return Res;


        }catch (Exception e){
            throw new BotCommandException(MESSAGES.getString("APIError",e)) ;
        }


    }

}
