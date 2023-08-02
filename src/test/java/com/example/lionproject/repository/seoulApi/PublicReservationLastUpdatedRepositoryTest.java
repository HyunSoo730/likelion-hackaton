package com.example.lionproject.repository.seoulApi;

import com.example.lionproject.domain.seoulApi.PublicReservationLastUpdated;
import com.example.lionproject.domain.seoulApi.enums.PublicReservationType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class PublicReservationLastUpdatedRepositoryTest {

    @Autowired
    private PublicReservationLastUpdatedRepository repository;

    @Test
    void givenTestData_whenCallingOrderByLastUpdatedDesc_thenSuccess() throws Exception {
        // Given
        final int size = 5;
        List<PublicReservationLastUpdated> testData = createTestData(size);
        repository.saveAllAndFlush(testData);

        LocalDate today = LocalDate.now();
        LocalDate lastDate = today.plusDays(size);

        // When
        PublicReservationLastUpdated result = repository.findFirstByPublicReservationTypeOrderByLastUpdatedDesc(PublicReservationType.GENERAL)
                .orElseGet(() -> PublicReservationLastUpdated.of(0L, PublicReservationType.GENERAL, today));
        System.out.println("result = " + result);

        // Then
        Assertions.assertThat(result.getLastUpdated()).isEqualTo(lastDate);
    }

    public static List<PublicReservationLastUpdated> createTestData(int size) {
        LocalDate today = LocalDate.now();
        List<PublicReservationLastUpdated> data = new ArrayList<>();
        for(int i = 1; i <= size; i++){
            data.add(PublicReservationLastUpdated.of(
                    (long) i, PublicReservationType.GENERAL, today.plusDays(i)));
        }

        return data;
    }


}