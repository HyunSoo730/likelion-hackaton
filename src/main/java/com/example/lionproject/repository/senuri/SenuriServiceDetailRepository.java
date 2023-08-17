package com.example.lionproject.repository.senuri;

import com.example.lionproject.domain.entity.SenuriServiceDetailCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SenuriServiceDetailRepository extends JpaRepository<SenuriServiceDetailCheck, Long>, SenuriServiceDetailRepositoryCustom {

    @Query("SELECT DISTINCT s FROM SenuriServiceDetailCheck s WHERE s.toAcptDd >= :today AND s.plDetAddr LIKE %:city% GROUP BY s.jobId ORDER BY s.toAcptDd ASC")
    List<SenuriServiceDetailCheck> findAfterTodayAndCityOrderByToAcptDdAsc(@Param("today") String today, @Param("city") String city);


}
