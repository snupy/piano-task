package ru.malik.piano_task.server.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

public class UnixTimestampDeserializerTest {

    private UnixTimestampDeserializer deserializer;
    private ObjectMapper mapper;

    @Before
    public void setUp() {
        deserializer = new UnixTimestampDeserializer();
        mapper = new ObjectMapper();
    }

    @Test
    public void deserialize() throws Exception {
        String json = String.format("{\"value\":%s}", "\"2\"");
        Timestamp ts = deserialiseTimestamp(json);
        assertEquals(new Timestamp(2000), ts);
    }

    private Timestamp deserialiseTimestamp(String json) throws IOException {
        InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        JsonParser parser = mapper.getFactory().createParser(stream);
        DeserializationContext ctxt = mapper.getDeserializationContext();
        parser.nextToken();
        parser.nextToken();
        parser.nextToken();
        return deserializer.deserialize(parser, ctxt);
    }
}