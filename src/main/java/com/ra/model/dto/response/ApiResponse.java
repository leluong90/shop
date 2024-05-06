package com.ra.model.dto.response;

import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.Annotation;

@Getter
@Setter
public class ApiResponse implements io.swagger.v3.oas.annotations.responses.ApiResponse {
    private String message ;
    private Boolean status ;

    @Override
    public String description() {
        return null;
    }

    @Override
    public String responseCode() {
        return null;
    }

    @Override
    public Header[] headers() {
        return new Header[0];
    }

    @Override
    public Link[] links() {
        return new Link[0];
    }

    @Override
    public Content[] content() {
        return new Content[0];
    }

    @Override
    public Extension[] extensions() {
        return new Extension[0];
    }

    @Override
    public String ref() {
        return null;
    }

    @Override
    public boolean useReturnTypeSchema() {
        return false;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
