package com.example.lionproject.repository.employment;

import com.example.lionproject.domain.entity.Employment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentRepositoryCustom {

    // 유료/무료 + 마감/접수중 + 교육명(검색어) 로 필터 적용
    List<Employment> findByFiltered(String registCost, String applyState, String subject);

}
