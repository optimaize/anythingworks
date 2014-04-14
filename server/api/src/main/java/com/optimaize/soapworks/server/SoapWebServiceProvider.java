package com.optimaize.soapworks.server;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 */
public interface SoapWebServiceProvider {
    @NotNull
    List<SoapWebService> getAll();
}
