package com.example.lionproject.repository.custom.impl;

import com.example.lionproject.domain.entity.PublicServiceReservation;
import com.example.lionproject.repository.custom.PublicServiceReservationRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PublicServiceReservationRepositoryCustomImpl implements PublicServiceReservationRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<PublicServiceReservation> findByFiltered(List<String> areaNM, List<String> reserveType, List<String> maxClassNM,
                                                         List<String> minClassNM, List<String> svcStatNM, List<String> payAtNM, Pageable pageable) {

        String query = "SELECT p FROM PublicServiceReservation p where ";

        List<String> whereClause = new ArrayList<>();
        if(checkParamList(areaNM)){
            whereClause.add("p.areaNM in :areaNM");
        }
        if(checkParamList(reserveType)){
            whereClause.add("p.reserveType in :reserveType");
        }
        if(checkParamList(maxClassNM)){
            whereClause.add("p.maxClassNM in :maxClassNM");
        }
        if(checkParamList(minClassNM)){
            whereClause.add("p.minClassNM in :minClassNM");
        }
        if(checkParamList(svcStatNM)){
            whereClause.add("p.svcStatNM in :svcStatNM");
        }
        if(checkParamList(payAtNM)){
            whereClause.add("p.payAtNM in :payAtNM");
        }

        if(checkParamList(whereClause)){
            query += String.join(" and ", whereClause);
        }

        TypedQuery<PublicServiceReservation> typedQuery
                = em.createQuery(query, PublicServiceReservation.class);
        if(checkParamList(areaNM)){
            typedQuery.setParameter("areaNM", areaNM);
        }
        if(checkParamList(reserveType)){
            typedQuery.setParameter("reserveType", reserveType);
        }
        if(checkParamList(maxClassNM)){
            typedQuery.setParameter("maxClassNM", maxClassNM);
        }
        if(checkParamList(minClassNM)){
            typedQuery.setParameter("minClassNM", minClassNM);
        }
        if(checkParamList(svcStatNM)){
            typedQuery.setParameter("svcStatNM", svcStatNM);
        }
        if(checkParamList(payAtNM)){
            typedQuery.setParameter("payAtNM", payAtNM);
        }

        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();

        return typedQuery.setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    private <T> boolean checkParamList(List<T> param) {
        return param == null ? false : param.isEmpty() || param.size() == 0 ? false : true;
    }

}
