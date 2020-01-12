package com.example.summer.utility.json.deserialzer;

import com.example.summer.model.EmailModel;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/*
 * @program: summerCS_Phase_I
 * @description: EmailModel 的自定义deserialozer
 * @author: 161250193
 * @create: 2018/3/17
 */
public class EmailModelDeserializer extends JsonDeserializer<EmailModel>{
    @Override
    public EmailModel deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        EmailModel emailModel = new EmailModel();
        emailModel.setAddress(p.getValueAsString());
        return emailModel;
    }
}
