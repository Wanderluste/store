package com.cy.store.service;

public interface IGeoCodingService {
    String getLocation(String address);
    String getAddress(double lng, double lat);
}
