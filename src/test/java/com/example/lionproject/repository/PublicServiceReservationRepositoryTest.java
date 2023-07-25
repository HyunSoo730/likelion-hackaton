package com.example.lionproject.repository;

import com.example.lionproject.domain.entity.PublicServiceReservation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PublicServiceReservationRepositoryTest {

    @Autowired
    private PublicServiceReservationRepository publicServiceReservationRepository;


    @Test
    void given_when_then() throws Exception {
        // Given
        List<PublicServiceReservation> data = createPublicServiceReservation(10);
        publicServiceReservationRepository.saveAllAndFlush(data);

        // When
        List<String> result = publicServiceReservationRepository.findAllServiceId();

        // Then
        Assertions.assertThat(result.size()).isEqualTo(10);
    }

    public static List<PublicServiceReservation> createPublicServiceReservation(int size) {
        List<PublicServiceReservation> data = new ArrayList<>();
        for(int i = 1; i <= size; i++){
            data.add(PublicServiceReservation.of(String.valueOf(i)));
        }

        return data;
    }

}