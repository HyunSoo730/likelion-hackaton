package com.example.lionproject.domain.seoulApi;

import com.example.lionproject.domain.seoulApi.enums.PublicReservationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PublicReservationLastUpdated {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PublicReservationType publicReservationType;

    private LocalDate lastUpdated;

    public static PublicReservationLastUpdated of(Long id, PublicReservationType publicReservationType, LocalDate lastUpdated) {
        return new PublicReservationLastUpdated(id, publicReservationType, lastUpdated);
    }
    public static PublicReservationLastUpdated of(PublicReservationType publicReservationType, LocalDate lastUpdated) {
        return PublicReservationLastUpdated.of(null, publicReservationType, lastUpdated);
    }
}
