package Utils;

import com.automationanywhere.core.security.SecureString;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;

public class KoreSession {
    public SecureString getSecureClientID() {
        return ClientID;
    }
    public SecureString getSecureClientSecret() {
        return ClientSecret;
    }
    public String getWebhookURL() {
        return WebhookURL;
    }

    private SecureString ClientID;
    private SecureString ClientSecret;
    private String WebhookURL;

    public KoreSession(SecureString ClientID, SecureString ClientSecret, String WebhookURL){
        this.ClientID = ClientID;
        this.ClientSecret = ClientSecret;
        this.WebhookURL = WebhookURL;
    }


}
