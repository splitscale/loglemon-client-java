package com.splitscale;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class Loglemon {

  private Loglemon() {
    // default constructor
  }

  private static final String url = "http://splitscale.systems:8081/logs";

  public static void sendLog(String log) {
    try {
      URL endpointUrl = new URL(url);
      URLConnection connection = endpointUrl.openConnection();
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setDoOutput(true);

      if (connection instanceof HttpURLConnection) {
        HttpURLConnection httpConnection = (HttpURLConnection) connection;
        httpConnection.setRequestMethod("POST");
        httpConnection.setDoOutput(true);
        httpConnection.setFixedLengthStreamingMode(log.getBytes(StandardCharsets.UTF_8).length);
        httpConnection.connect();

        // Write the log string to the request body
        httpConnection.getOutputStream().write(log.getBytes(StandardCharsets.UTF_8));

        httpConnection.getResponseCode(); // This sends the request
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
