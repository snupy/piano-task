package ru.malik.piano_task.server.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Timestamp;

public class UnixTimestampDeserializer extends JsonDeserializer<Timestamp> {
    private static final Logger logger = LogManager.getLogger(UnixTimestampDeserializer.class);

    @Override
    public Timestamp deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String timestamp = jp.getText().trim();

        try {
            return new Timestamp(Long.valueOf(timestamp + "000"));
        } catch (NumberFormatException e) {
            logger.warn("Unable to deserialize timestamp: " + timestamp, e);
            return null;
        }
    }
}
