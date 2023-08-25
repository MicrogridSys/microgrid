package com.ruoyi.microgrid.tools;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

/*
* 在调用readTree()时，改变对应Date类型的格式
* */
public class TimestampToDateUtils extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        long seconds = jsonParser.getValueAsLong();
        return new Date(seconds * 1000);
    }
}