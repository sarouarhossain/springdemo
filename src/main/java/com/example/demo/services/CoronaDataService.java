package com.example.demo.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;
import java.net.http.HttpClient;

@Service
public class CoronaDataService {
  private static Logger logger = LoggerFactory.getLogger(CoronaDataService.class);

  private static String VIRUS_DATA_URL =
      "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/archived_data/archived_time_series/time_series_2019-ncov-Deaths.csv";

  public void getData() throws IOException {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(VIRUS_DATA_URL, String.class);
    logger.info("Test Response status: " + responseEntity.getStatusCode());
    String body = responseEntity.getBody();
    // logger.info(body);
    StringReader stringReader = new StringReader(body);

    Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);

    int count = 0;
    for (CSVRecord csvRecord : records) {
      logger.info(csvRecord.toString());
      count++;
      if (count == 5) break;
    }
  }
}
