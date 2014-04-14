package com.optimaize.soapworks;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 */
public interface SoapWebServiceProvider {
    @NotNull
    List<SoapWebService> getAll();
}
