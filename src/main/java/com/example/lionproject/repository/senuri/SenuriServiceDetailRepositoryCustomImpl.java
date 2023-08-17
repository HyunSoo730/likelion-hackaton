package com.example.lionproject.repository.senuri;

import com.example.lionproject.domain.entity.SenuriServiceDetail;
import com.example.lionproject.domain.entity.SenuriServiceDetailCheck;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class SenuriServiceDetailRepositoryCustomImpl implements SenuriServiceDetailRepositoryCustom{

    private final EntityManager em;

    /**
     * 지역구, 채용공고형태, 공고상태, 검색어로 필터링 적용
     */
    @Override
    public List<SenuriServiceDetailCheck> findFiltered(List<String> area, List<String> jobPosition, List<String> status, String wantedTitle) {

        String query = "SELECT s FROM SenuriServiceDetailCheck s where ";
        List<String> whereClause = new ArrayList<>();

//        if (checkParamList(area)) {
//            List<String> likeConditions = area.stream()
//                    .map(areaName -> "s.plDetAddr LIKE :plDetAddr")
//                    .collect(Collectors.toList());
//            whereClause.add("(" + String.join(" OR ", likeConditions) + ")");
//        }
        if (checkParamList(area)) {
            List<String> likeConditions = area.stream()
                    .map(areaName -> "s.plDetAddr LIKE :plDetAddr_" + areaName)
                    .collect(Collectors.toList());
            // -> 이렇게 해야지만 파라미터 이름을 구별할 수 있음 :plDetAddr 이렇게만 쓰면 하이버네이트는 하나의 파라미터로 인식해서
            // 아무리 리스트에 4개의 값이 있어도 하나의 파라미터에만 값을 넣게 돼 !! name을 구분해줘야지 제대로 넣을 수 있음 !
            whereClause.add("(" + String.join(" OR ", likeConditions) + ")");
        }
        if (checkParamList(jobPosition)) {
            whereClause.add("s.emplymShpNm in :emplymShpNm");
        }
        if (checkParamList(status)) {
            whereClause.add("s.deadline in :deadline");
        }
        if (checkSearchWord(wantedTitle)) {
            whereClause.add("s.wantedTitle LIKE :wantedTitle");
        }
        if (checkParamList(whereClause)) {
            query += String.join(" and ", whereClause);
        }
        query += " GROUP BY s.jobId";
        query += " ORDER BY s.toAcptDd ASC";  //종료접수일 순으로 오름차순 진행.

        log.info("query = {} ", query);

        TypedQuery<SenuriServiceDetailCheck> typedQuery
                = em.createQuery(query, SenuriServiceDetailCheck.class);


//        if (checkParamList(area)) {
//            area.forEach(areaName -> typedQuery.setParameter("plDetAddr", "%" + areaName + "%"));
//        }
        if (checkParamList(area)) {
            area.forEach(areaName -> typedQuery.setParameter("plDetAddr_" + areaName, "%" + areaName + "%"));
        }
        if (checkParamList(jobPosition)) {
            typedQuery.setParameter("emplymShpNm", jobPosition);
        }
        if (checkParamList(status)) {
            typedQuery.setParameter("deadline", status);
        }
        if (checkSearchWord(wantedTitle)) {
            typedQuery.setParameter("wantedTitle", "%" + wantedTitle + "%");
        }

        return typedQuery.getResultList();
    }

    private <T> boolean checkParamList(List<T> param) {
        return param == null ? false : param.isEmpty() || param.size() == 0 ? false : true;
//        return param != null && !param.isEmpty();
    }

    private boolean checkSearchWord(String param) {  //검색어 필터링
        return param == null ? false : param.isEmpty() ? false : true;
//        return param != null && !param.isEmpty();
    }
}
