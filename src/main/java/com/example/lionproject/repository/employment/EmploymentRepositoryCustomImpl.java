package com.example.lionproject.repository.employment;

import com.example.lionproject.domain.entity.Employment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class EmploymentRepositoryCustomImpl implements EmploymentRepositoryCustom{

    private final EntityManager em;

    @Override
    public List<Employment> findByFiltered(String registCost, String applyState, String subject) {

        String query = "SELECT e FROM Employment e where ";
        List<String> whereClause = new ArrayList<>();

        if (checkString(registCost)) {
            whereClause.add("e.registCost = :registCost");
        }
        if (checkString(applyState)) {
            whereClause.add("e.applyState = :applyState");
        }
        if (checkString(subject)) {
            whereClause.add("e.subject LIKE :subject");
        }

        if (checkParamList(whereClause)) {
            query += String.join(" and ", whereClause);
        }
        log.info("query = {}", query);

        TypedQuery<Employment> typedQuery = em.createQuery(query, Employment.class);
        if (checkString(registCost)) {
            typedQuery.setParameter("registCost",registCost);
        }
        if (checkString(applyState)) {
            typedQuery.setParameter("applyState", applyState);
        }
        if (checkString(subject)) {
            typedQuery.setParameter("subject", "%" + subject + "%");
        }
        return typedQuery.getResultList();
    }

    private <T> boolean checkParamList(List<T> param) {
        return param == null ? false : param.isEmpty() || param.size() == 0 ? false : true;
    }

    private boolean checkString(String param) {
        return param == null ? false : param.isEmpty() ? false : true;
    }
}
