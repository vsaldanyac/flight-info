package com.flights.flight.controller;

import com.flights.flight.service.FlightService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FlightControllerShould {

  public static final String TAIL_NUMBER = "A";
  public static final String FLIGHT_NUMBER = "A";
  public static final String VALID_TOKEN = "1234";
  public static final String INVALID_TOKEN = "12";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FlightService flightService;

  @Before
  public void setup() {
    initMocks(this);
  }

  @Test
  public void shouldExistGetFlightsInfo() throws Exception {
    this.mockMvc.perform(get(String.format("/v1/flight-information/%s/%s", TAIL_NUMBER, FLIGHT_NUMBER))
        .header("X-Auth", VALID_TOKEN)
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(status().is2xxSuccessful()
    );
  }

  @Test
  public void shouldReturnForbiddenWithInvalidToken() throws Exception {
    this.mockMvc.perform(get(String.format("/v1/flight-information/%s/%s", TAIL_NUMBER, FLIGHT_NUMBER))
        .header("X-Auth", INVALID_TOKEN)
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(status().is4xxClientError()
    );
  }

  @Test
  public void shouldReturnErrorWithoutAdminToken() throws Exception {
    this.mockMvc.perform(get(String.format("/v1/flight-information/%s/%s", TAIL_NUMBER, FLIGHT_NUMBER))
        .contentType(MediaType.APPLICATION_JSON)
    ).andExpect(status().is4xxClientError()
    );
  }
}
