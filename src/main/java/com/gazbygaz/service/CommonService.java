package com.gazbygaz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public abstract class CommonService {

    @Autowired
    private MessageSource messageSource;

    protected String getMessageForResponseCode(final String code,final Object[] params) {
        String key = "code." + code;
        return messageSource.getMessage(key, params, "Message", null);
    }
}
