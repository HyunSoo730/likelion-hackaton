package com.example.lionproject.Batch.Reader;

import com.example.lionproject.OpenApi.CallOpenApi;
import com.example.lionproject.OpenApi.CallResponse.Raw.SenuriServiceDetailRawResponse;
import com.example.lionproject.domain.dto.WebClientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class SenuriServiceDetailReader implements ItemReader<List<SenuriServiceDetailRawResponse>> {

    private final CallOpenApi callOpenApi;
    private final List<String> jobIds;

    private static boolean IS_END = false;

    @Override
    public List<SenuriServiceDetailRawResponse> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(IS_END){
            return null;
        }
        if(jobIds == null){
            return null;
        }
        if(jobIds.isEmpty() || jobIds.size() == 0){
            return null;
        }

        IS_END = true;
        return jobIds.stream()
                .map(id -> callOpenApi.CallSenuriServiceDetail(id))
                .collect(Collectors.toList());
    }
}
