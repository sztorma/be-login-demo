package com.sztorma.logindemo.security.model;

import lombok.Data;

@Data
public class CaptchaRequest {

    private String captchaToken;

    public CaptchaRequest() {
    }

    public CaptchaRequest(String captchaToken) {
        this.captchaToken = captchaToken;
    }

}
