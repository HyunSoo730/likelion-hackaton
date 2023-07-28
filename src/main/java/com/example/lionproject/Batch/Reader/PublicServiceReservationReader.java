package com.example.lionproject.Batch.Reader;


import com.example.lionproject.domain.dto.WebClientDTO;
import com.example.lionproject.service.Api.WebClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

@Slf4j
@RequiredArgsConstructor
public class PublicServiceReservationReader implements ItemReader<WebClientDTO> {

    private final WebClientService webClientService;

    private static int size = 10;
    private static int startIndex = 1;
    private static int lastIndex = -1;
    private static boolean IS_END = false;

    @Override
    public WebClientDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (IS_END) {
            return null;
        }

        WebClientDTO response = webClientService.getPublicServiceReservation(startIndex, startIndex + size - 1);
        if (lastIndex == -1) {
            lastIndex = response.fetchListTotalCount();
        }

        startIndex += size;
        if (startIndex > lastIndex) {
            IS_END = true;
        }

        return response;
    }
}
