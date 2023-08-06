package pl.gatomek.waterusage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WaterUsageApplication.class)
@ActiveProfiles("it2")

public class SomeTest3IT {

    private RestTemplate restTemplate = new RestTemplate();

    @LocalServerPort
    private int port;

    @Test
    public void readingEmptyFolderTest() throws IOException {

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Resource> response = restTemplate.exchange(
                createURLWithPort("/rest/water-day-usage"),
                HttpMethod.GET, entity, Resource.class);

        Resource body = response.getBody();
        assertNotEquals( null, body);

        String file = body.getContentAsString( Charset.forName("UTF-8"));
        String[] lines = file.split( System.lineSeparator());

        assertEquals( 2, lines.length);
        assertEquals( "Date,ZW,CW,TW", lines[0]);
        assertEquals( "2023-05-01,5000,7000,12000", lines[1]);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
