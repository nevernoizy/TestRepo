import app.WalletApplication;

import app.models.OperationType;
import app.models.RequestModel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.http.ResponseEntity;


import java.util.UUID;

import static app.models.OperationType.WITHDRAW;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WalletApplication.class)

public class MyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGet() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/v1/wallets/654d58d8-3661-450d-be9e-a32148a5ea58", String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("3050", response.getBody());
    }

    @Test
    public void testPost1() {
        RequestModel requestModel = new RequestModel();
        requestModel.setValletId(UUID.fromString("654d58d8-3661-450d-be9e-a32148a5ea58"));
        requestModel.setOperationType(OperationType.DEPOSIT);
        requestModel.setAmount(50);
        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/v1/wallet", requestModel, String.class);
        assertEquals(200, response.getStatusCodeValue());


        ResponseEntity<String> response1 = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/v1/wallets/654d58d8-3661-450d-be9e-a32148a5ea58", String.class);
        assertEquals(200, response1.getStatusCodeValue());
        assertEquals("3100", response1.getBody());
    }

    @Test
    public void testPost2() {
        RequestModel requestModel = new RequestModel();
        requestModel.setValletId(UUID.fromString("654d58d8-3661-450d-be9e-a32148a5ea58"));
        requestModel.setOperationType(WITHDRAW);
        requestModel.setAmount(50);
        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/v1/wallet", requestModel, String.class);
        assertEquals(200, response.getStatusCodeValue());


        ResponseEntity<String> response1 = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/v1/wallets/654d58d8-3661-450d-be9e-a32148a5ea58", String.class);
        assertEquals(200, response1.getStatusCodeValue());
        assertEquals("3050", response1.getBody());
    }
}