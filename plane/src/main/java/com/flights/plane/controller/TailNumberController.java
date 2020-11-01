package com.flights.plane.controller;

import com.flights.plane.utils.PlaneUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controls all relative to flight information
 *
 * @author VSaldanya
 */
@RestController
@RequestMapping(value = "/v1/flight-information-tail", produces = MediaType.APPLICATION_JSON_VALUE)
public class TailNumberController {

  @GetMapping("/{tailNumber}")
  public @ResponseBody
  ResponseEntity<?> getTailNumberInformation(@PathVariable String tailNumber) {
    String s = PlaneUtils.getResourceFileAsString("data/flights.json");
    return ResponseEntity.ok(s);
  }
}
