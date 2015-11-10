package com.optimaize.anythingworks.server.implgrizzly.rest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import javax.ws.rs.core.MediaType;


/**
 * Adds "charset=utf-8" to the Content-Type header if there is no charset in it yet.
 *
 * HOW TO USE
 * ==========
 * When using Grizzly:
 * resourceConfig.register(CharsetResponseFilter.class);
 *
 *
 * WHY THIS CLASS
 * ==============
 * This is to ensure that browsers correctly display content.
 * It's important because users look at REST services and the JSON responses in browsers, and will
 * complain that "it's broken" although it's just the browser that is broken.
 *
 * For example right now Chrome and Firefox on Windows show garbled characters.
 * See open bug https://code.google.com/p/chromium/issues/detail?id=438464
 * Many users saw such issues over the years,
 * see http://stackoverflow.com/questions/9359728/jersey-web-service-json-utf-8-encoding
 * so because these issues don't just go away, it's better to use this filter to be safe. Even if it would
 * "technically not be necessary".
 *
 * SOURCE
 * ======
 * Copied from http://stackoverflow.com/questions/5514087/jersey-rest-default-character-encoding/20569571
 * on 2015-11-10
 *
 */
public class CharsetResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) {
        MediaType type = response.getMediaType();
        if (type != null) {
            String contentType = type.toString();
            if (!contentType.contains("charset")) {
                contentType = contentType + ";charset=utf-8";
                response.getHeaders().putSingle("Content-Type", contentType);
            }
        }
    }
}