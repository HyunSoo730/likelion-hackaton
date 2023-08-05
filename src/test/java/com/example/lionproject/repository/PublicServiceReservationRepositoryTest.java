package com.example.lionproject.repository;

import com.example.lionproject.domain.entity.PublicServiceReservation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class PublicServiceReservationRepositoryTest {

    @Autowired
    private PublicServiceReservationRepository publicServiceReservationRepository;

    @BeforeAll
    void create_testData(){
        List<PublicServiceReservation> data = createPublicServiceReservation(10);
        data.addAll(createPublicServiceReservation(10));
        publicServiceReservationRepository.saveAll(data);
    }

    @Test
    void givenTestData_whenCallingFindAllServiceId_thenSuccess() throws Exception {
        // Given

        // When
        List<String> result = publicServiceReservationRepository.findAllServiceId();

        // Then
        Assertions.assertThat(result.size()).isEqualTo(20);
    }

    @Test
    @Deprecated void givenTestData_whenCallingFindAllDistinctServiceId_thenSuccess() throws Exception {
        // Given

        // When
        Set<String> result = publicServiceReservationRepository.findAllDistinctServiceId();

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