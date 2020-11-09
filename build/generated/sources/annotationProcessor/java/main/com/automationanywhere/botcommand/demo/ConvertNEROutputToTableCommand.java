package com.automationanywhere.botcommand.demo;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
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

public final class ConvertNEROutputToTableCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(ConvertNEROutputToTableCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    ConvertNEROutputToTable command = new ConvertNEROutputToTable();
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

    if(parameters.containsKey("TextToAnalyze") && parameters.get("TextToAnalyze") != null && parameters.get("TextToAnalyze").get() != null) {
      convertedParameters.put("TextToAnalyze", parameters.get("TextToAnalyze").get());
      if(!(convertedParameters.get("TextToAnalyze") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","TextToAnalyze", "String", parameters.get("TextToAnalyze").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("TextToAnalyze") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","TextToAnalyze"));
    }

    if(parameters.containsKey("EntitySeparator") && parameters.get("EntitySeparator") != null && parameters.get("EntitySeparator").get() != null) {
      convertedParameters.put("EntitySeparator", parameters.get("EntitySeparator").get());
      if(!(convertedParameters.get("EntitySeparator") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","EntitySeparator", "String", parameters.get("EntitySeparator").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("EntitySeparator") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","EntitySeparator"));
    }

    if(parameters.containsKey("EntityTypeValueSeparator") && parameters.get("EntityTypeValueSeparator") != null && parameters.get("EntityTypeValueSeparator").get() != null) {
      convertedParameters.put("EntityTypeValueSeparator", parameters.get("EntityTypeValueSeparator").get());
      if(!(convertedParameters.get("EntityTypeValueSeparator") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","EntityTypeValueSeparator", "String", parameters.get("EntityTypeValueSeparator").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("EntityTypeValueSeparator") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","EntityTypeValueSeparator"));
    }

    command.setSessions(sessionMap);
    try {
      Optional<Value> result =  Optional.ofNullable(command.action((String)convertedParameters.get("sessionName"),(String)convertedParameters.get("TextToAnalyze"),(String)convertedParameters.get("EntitySeparator"),(String)convertedParameters.get("EntityTypeValueSeparator")));
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","action"));
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
