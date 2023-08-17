package com.example.lionproject.repository.senuri;

import com.example.lionproject.domain.entity.SenuriServiceDetail;
import com.example.lionproject.domain.entity.SenuriServiceDetailCheck;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SenuriServiceDetailRepositoryCustom {

    List<SenuriServiceDetailCheck> findFiltered(List<String> area, List<String> jobPosition, List<String> status, String wantedTitle);

    List<SenuriServiceDetailCheck> findFilteredInterestArea(List<String> area);
}
