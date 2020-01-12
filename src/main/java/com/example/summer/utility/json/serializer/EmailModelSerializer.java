package com.example.summer.utility.json.serializer;

import com.example.summer.model.EmailModel;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/*
 * @program: summerCS_Phase_I
 * @description: EmailModel 的自定义serialozer
 * @author: 161250193
 * @create: 2018/3/17
 */
public class EmailModelSerializer extends JsonSerializer<EmailModel> {
    @Override
    public void serialize(EmailModel value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.getAddress());
    }
}
