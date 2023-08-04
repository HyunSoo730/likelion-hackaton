package com.example.lionproject.OpenApi;

import com.example.lionproject.OpenApi.CallResponse.Raw.ListPublicReservationEducationRaw;
import com.example.lionproject.OpenApi.CallResponse.Raw.ListPublicReservationMedicalRaw;
import com.example.lionproject.OpenApi.CallResponse.Raw.SenuriServiceRawResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

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

    @Test
    void givenNothing_whenCallingPublicReservationEducationRaw_thenSuccess() throws Exception {
        // Given

        // When
        ListPublicReservationEducationRaw response = callOpenApi.CallListPublicReservationEducation(1, 5);

        // Then
        Assertions.assertThat(response.fetchResultCode()).isEqualTo("INFO-000");
    }

    @Test
    void givenPagingInfo_whenCallingSenuriService_thenSuccess() throws Exception {
        // Given

        // When
        SenuriServiceRawResponse response = callOpenApi.CallSenuriService(1, 1);

        // Then
        Assertions.assertThat(response.fetchResultCode()).isEqualTo("00");
    }
}