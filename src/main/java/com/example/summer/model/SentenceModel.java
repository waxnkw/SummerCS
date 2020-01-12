package com.example.summer.model;

import com.example.summer.domain.SentencePO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SentenceModel {
    @JsonIgnore
    private String sentenceId;

    private String rawStr;

    private ArrayList<String> tokens;

    /***************************************************************************
     *                                                                         *
     * Constructor                                                  *
     *                                                                         *
     **************************************************************************/
    public SentenceModel(SentencePO po){
        this.sentenceId = po.getSentenceId();
        this.rawStr = po.getRawStr();
        this.tokens = po.getTokens();
    }
}
