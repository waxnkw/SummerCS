package com.example.summer.utility.json.deserialzer;

import com.example.summer.utility.enums.ProjectTypeEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class ProjectTypeEnumDeserializer extends JsonDeserializer<ProjectTypeEnum> {

    @Override
    public ProjectTypeEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return ProjectTypeEnum.valueOf(p.getValueAsString());
    }
}
