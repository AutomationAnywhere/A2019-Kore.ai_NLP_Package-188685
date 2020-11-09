package Utils;

import com.automationanywhere.core.security.SecureString;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.bouncycastle.util.encoders.UTF8;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import shadow.org.apache.commons.io.IOUtils;
import shadow.org.apache.tools.ant.types.resources.Resources;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class KoreUtils {

    public static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    public static String GetNERJsonPayload(String NERText){

        String NERPayloadFile = "NERPayloadSample.json";
        String Payload = "";

        try {

            // for static access, uses the class name directly
            InputStream is = KoreUtils.class.getClassLoader().getResourceAsStream(NERPayloadFile);
            StringWriter writer = new StringWriter();
            IOUtils.copy(is, writer,"UTF8");
            Payload = writer.toString();

            //Payload = new String(Files.readAllBytes(Paths.get(KoreUtils.class.getResource(NERPayloadFile).toURI())));
            Payload = Payload.replace("<PAYLOAD_REPLACE>",sanitizeText(NERText));
            return Payload;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";

    }

    public static String generateJWTToken(KoreSession session) throws RuntimeException {
        String ClientID = session.getSecureClientID().getInsecureString();
        String ClientSecret = session.getSecureClientSecret().getInsecureString();
        String header = "{\"typ\":\"JWT\",\"alg\":\"HS256\"}";
        //System.out.println("HEADER:"+header);
        String base64UrlHeader = Base64.getUrlEncoder().withoutPadding().encodeToString(header.getBytes());

        String payload = "{\"sub\":\"AABBCCDD\",\"appId\":\"" + ClientID + "\"}";
        //System.out.println("PAYLOAD:"+payload);
        String base64UrlPayload = Base64.getUrlEncoder().withoutPadding().encodeToString(payload.getBytes());

        try {
            String base64UrlSignature = hmacEncode(base64UrlHeader + "." + base64UrlPayload, ClientSecret);
            String JWTToken = base64UrlHeader + "." + base64UrlPayload + "." + base64UrlSignature;
            //System.out.println("TOKEN:"+JWTToken);
            return JWTToken;
        } catch (Exception e) {
            throw new RuntimeException("Unable to generate a JWT token.");
        }
    }

    public static String sanitizeText(String myText){
        myText = myText.replaceAll("\n"," ");
        myText = myText.replaceAll("\r"," ");
        myText = myText.replaceAll("\t"," ");
        myText = myText.replaceAll(" +"," ");
        myText = myText.replaceAll("\""," ");//String stored as JSON, need to remove double quotes
        myText = myText.replaceAll("[Oo][Rr][Dd] ","Order ");
        //System.out.println("DEBUG:"+myText);
        return myText;
    }
    private static String hmacEncode(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(sha256_HMAC.doFinal(data.getBytes()));
    }

    public static SecureString StringToSecureString(String myString){

        char[] SecStringArr = new char[myString.length()];
        for (int i = 0; i < myString.length(); i++) { SecStringArr[i] = myString.charAt(i); }
        SecureString secString = new SecureString(SecStringArr);
        return secString;
    }

}
