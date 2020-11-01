package com.flights.plane.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class PlaneUtils {

  public static String getResourceFileAsString(String fileName) {
    ClassLoader classLoader = PlaneUtils.class.getClassLoader();
    try (InputStream is = classLoader.getResourceAsStream(fileName)) {
      if (is == null) {
        return null;
      }
      try (InputStreamReader isr = new InputStreamReader(is);
           BufferedReader reader = new BufferedReader(isr)) {
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
      }
    } catch (Exception e) {
      return null;
    }
  }
}
