package com.example.lionproject.repository.seoulApi;

import com.example.lionproject.domain.seoulApi.PublicReservationLastUpdated;
import com.example.lionproject.domain.seoulApi.enums.PublicReservationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublicReservationLastUpdatedRepository extends JpaRepository<PublicReservationLastUpdated, Long> {

    Optional<PublicReservationLastUpdated> findFirstByPublicReservationTypeOrderByLastUpdatedDesc(PublicReservationType type);
}
