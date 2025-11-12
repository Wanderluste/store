package com.cy.store.controller;

import com.cy.store.service.IGeoCodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/map")
public class MapController extends BaseController{
    @Autowired
    private IGeoCodingService geoCodingService;
    @GetMapping("/geo")
    public ResponseEntity<?> addressToGeo(@RequestParam String address) {
        return ResponseEntity.ok(geoCodingService.getLocation(address));
    }

    @GetMapping("/regeo")
    public ResponseEntity<?> geoToAddress(@RequestParam double lng, @RequestParam double lat) {
        return ResponseEntity.ok(geoCodingService.getAddress(lng, lat));
    }
}
