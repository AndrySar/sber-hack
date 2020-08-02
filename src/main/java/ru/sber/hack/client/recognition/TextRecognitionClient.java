package ru.sber.hack.client.recognition;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sber.hack.client.recognition.request.TextRecognitionRequest;
import ru.sber.hack.client.recognition.response.TextRecognitionResponse;

@Service
public class TextRecognitionClient {

    private final static Logger log = LoggerFactory.getLogger(TextRecognitionClient.class);

    private final RestTemplate restTemplate;
    private static final String BASE_URL = "http://vahella.me:5000";

    private final ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    public TextRecognitionClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String recognitionText(String text) throws JsonProcessingException {

        log.info("Input text {}", text);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        TextRecognitionRequest recognitionRequest = new TextRecognitionRequest(text);


        HttpEntity<String> request =
                new HttpEntity<String>(objectWriter.writeValueAsString(recognitionRequest), headers);

        ResponseEntity<String> responseEntityStr = restTemplate.
                postForEntity(BASE_URL + "/recognition", request, String.class);

        log.info("Status code {}", responseEntityStr.getStatusCode());

        if (responseEntityStr.getStatusCode() == HttpStatus.OK) {
            ObjectMapper mapper = new ObjectMapper();

            try {
                String path = mapper.readValue(responseEntityStr.getBody(), TextRecognitionResponse.class).getPath();

                log.info("Return path {}", path);

                return path;
            } catch (IOException e) {
                log.error(e.getMessage());
                e.printStackTrace();
                return "";
            }
        } else {
            return "";
        }

    }
}
