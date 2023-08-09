package com.example.lionproject.repository;

import com.example.lionproject.domain.entity.PublicServiceReservation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ImportAutoConfiguration(PublicServiceReservation.class)
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
    void givenTestData_whenCallingFindAllDistinctServiceId_thenSuccess() throws Exception {
        // Given

        // When
        Set<String> result = publicServiceReservationRepository.findAllDistinctServiceId();
        // Then
        Assertions.assertThat(result.size()).isEqualTo(10);
    }

    @Test
    void givenParameters_whenCallingFindByFiltered_thenSuccess() throws Exception {
        // Given
        final List<String> areaNM = List.of("서초구", "송파구");
        final List<String> reserveType = new ArrayList<>();
        final List<String> maxClassNM = new ArrayList<>();
        final List<String> minClassNM = new ArrayList<>();
        final List<String> svcStatNM = new ArrayList<>();
        final List<String> payAtNM = new ArrayList<>();
        final Pageable page = PageRequest.of(0, 15);

        // When
        List<PublicServiceReservation> result = publicServiceReservationRepository.findByFiltered(areaNM, reserveType, maxClassNM,
                minClassNM, svcStatNM, payAtNM, page);

        // Then
        List<String> actual = publicServiceReservationRepository.findByAreaNMIn(areaNM, page).getContent()
                .stream()
                .map(p -> p.getServiceId())
                .collect(Collectors.toList());

       result.forEach(p -> Assertions.assertThat(p.getServiceId()).isIn(actual));

    }

    public static List<PublicServiceReservation> createPublicServiceReservation(int size) {
        List<PublicServiceReservation> data = new ArrayList<>();
        for(int i = 1; i <= size; i++){
            data.add(PublicServiceReservation.of(String.valueOf(i)));
        }

        return data;
    }

}