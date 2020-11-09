package com.automationanywhere.botcommand.demo;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import com.automationanywhere.core.security.SecureString;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class A_SessionStartCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(A_SessionStartCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    A_SessionStart command = new A_SessionStart();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("sessionName") && parameters.get("sessionName") != null && parameters.get("sessionName").get() != null) {
      convertedParameters.put("sessionName", parameters.get("sessionName").get());
      if(!(convertedParameters.get("sessionName") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","sessionName", "String", parameters.get("sessionName").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("sessionName") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","sessionName"));
    }

    if(parameters.containsKey("ClientID") && parameters.get("ClientID") != null && parameters.get("ClientID").get() != null) {
      convertedParameters.put("ClientID", parameters.get("ClientID").get());
      if(!(convertedParameters.get("ClientID") instanceof SecureString)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","ClientID", "SecureString", parameters.get("ClientID").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("ClientID") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","ClientID"));
    }

    if(parameters.containsKey("ClientSecret") && parameters.get("ClientSecret") != null && parameters.get("ClientSecret").get() != null) {
      convertedParameters.put("ClientSecret", parameters.get("ClientSecret").get());
      if(!(convertedParameters.get("ClientSecret") instanceof SecureString)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","ClientSecret", "SecureString", parameters.get("ClientSecret").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("ClientSecret") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","ClientSecret"));
    }

    if(parameters.containsKey("WebhookURL") && parameters.get("WebhookURL") != null && parameters.get("WebhookURL").get() != null) {
      convertedParameters.put("WebhookURL", parameters.get("WebhookURL").get());
      if(!(convertedParameters.get("WebhookURL") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","WebhookURL", "String", parameters.get("WebhookURL").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("WebhookURL") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","WebhookURL"));
    }

    command.setSessions(sessionMap);
    command.setGlobalSessionContext(globalSessionContext);
    try {
      command.start((String)convertedParameters.get("sessionName"),(SecureString)convertedParameters.get("ClientID"),(SecureString)convertedParameters.get("ClientSecret"),(String)convertedParameters.get("WebhookURL"));Optional<Value> result = Optional.empty();
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","start"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(),e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }
}
