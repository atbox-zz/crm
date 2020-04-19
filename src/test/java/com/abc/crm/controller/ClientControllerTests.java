package com.abc.crm.controller;

import com.abc.crm.controller.dto.req.ClientReqDto;
import com.abc.crm.controller.dto.res.CodeMessage;
import com.abc.crm.controller.dto.res.ResOneDto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTests {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    void addOne_johnByOperator() throws Exception {

        ClientReqDto clientReqDto = ClientReqDto.builder()
                .companyId(1L)
                .name("john")
                .email("john@abc.com")
                .phone("0911222333")
                .build();

        HttpHeaders headers = createHttpHeaders("operator", "123");

        HttpEntity<ClientReqDto> request = new HttpEntity<>(clientReqDto, headers);

        ResponseEntity<ResOneDto> response =
                testRestTemplate.postForEntity("/client/add", request, ResOneDto.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());

        ResOneDto resOneDto = response.getBody();
        Assertions.assertEquals(CodeMessage.SUCCESS.getCode(), resOneDto.getCode());
        Assertions.assertEquals(CodeMessage.SUCCESS.getMessage(), resOneDto.getMessage());

        int id = (int) resOneDto.getData();
        Assertions.assertTrue(id > 0);
    }

    private HttpHeaders createHttpHeaders(String username, String password) {
        String basicCredentials = username + ":" + password;
        byte[] base64Bytes = Base64.encodeBase64(basicCredentials.getBytes());
        String basicBase64EncodeCredentials = new String(base64Bytes);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + basicBase64EncodeCredentials);
        return headers;
    }



}
