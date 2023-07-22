package com.example.lionproject.OpenApi;

import com.example.lionproject.OpenApi.CallResponse.Raw.ListPublicReservationMedicalRaw;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootTest
class CallOpenApiTest {

    @Autowired
    private WebClient webClient;

    @Autowired
    private CallOpenApi callOpenApi;

    @Test
    void givenNothing_whenCallingPublicReservationMedicalRaw_thenSuccess() throws Exception {
        // Given

        // When
        ListPublicReservationMedicalRaw response = callOpenApi.CallListPublicReservationMedical(1, 5);

        // Then
        Assertions.assertThat(response.fetchResultCode()).isEqualTo("INFO-000");


    }
}