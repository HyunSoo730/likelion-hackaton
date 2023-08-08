package com.example.lionproject.service.Api;

import com.example.lionproject.domain.dto.EmploymentJsonDto;
import com.example.lionproject.domain.dto.Volunteer;
import com.example.lionproject.domain.dto.WebClientDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.http.codec.xml.Jaxb2XmlEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
@Transactional
@Slf4j
public class WebClientService {

    private final String BASE_URL = "http://openAPI.seoul.go.kr:8088";
    private final String BASE_URL_1365 = "http://openapi.1365.go.kr/openapi/service";
    private final WebClient webClient;

    /**
     * 객체지향적으로 Bean으로 등록해서 받아와서 사용
     * 등록은 xml전용으로 해놓고 가져와서 json으로 사용해야 할 경우 변경
     */
    public WebClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Value("${api.hyunsoo.key}")
    private String api_key;

    @Value("${api.key.1365}")
    private String api_key_1365;



    public WebClientDTO get(Integer startIndex, Integer endIndex) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        //이렇게 URI의 빌드 설정을 완료하면 WebClient의 인스턴스를 생성할 때 해당 UriBuilder로 uri를 만든다고 설정하면 된다.
        // 기본 세팅 진행
        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        //필요한 값들 요청.
        WebClientDTO response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("{api_key}/json/ListPublicReservationCulture/{startIndex}/{endIndex}")
                        .build(api_key, startIndex, endIndex))
                .retrieve()
                .bodyToMono(WebClientDTO.class)
                .block();

        /**
         * 결과 확인 log
         log.info("반환되는 값 : {}", response.toString());
         */
        return response;
    }

    public String get2(Integer startIndex, Integer endIndex) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        //이렇게 URI의 빌드 설정을 완료하면 WebClient의 인스턴스를 생성할 때 해당 UriBuilder로 uri를 만든다고 설정하면 된다.
        // 기본 세팅 진행
        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        //필요한 값들 요청.
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("{api_key}/json/ListPublicReservationCulture/{startIndex}/{endIndex}")
                        .build(api_key, startIndex, endIndex))
                .retrieve()
                .bodyToMono(String.class)
                .block();


        /**
         * 결과 확인 log
         log.info("반환되는 값 : {}", response.toString());
         */
        return response;
    }

    public WebClientDTO getPublicServiceReservation(Integer startIndex, Integer endIndex) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        //모든 값 요청.

        WebClientDTO res = webClient.mutate() // mutate() => Return a builder to create a new WebClient whose settings are replicated from the current WebClient.
                .uriBuilderFactory(factory)
                .build()
                .get().uri(uriBuilder ->
                        uriBuilder.path("{api_key}/json/tvYeyakCOllect/{startIndex}/{endIndex}")
                                .build(api_key, startIndex, endIndex))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(WebClientDTO.class)
                .block();

        /**
         * 결과 확인 log
         log.info("반환되는 res : {}", res.toString());
         */

        return res;

    }

    public String get1365Data(Integer num) {
        //xml open api를 사용하기 위한 기본 세팅.
        /**
         * open api를 xml 형식으로 받아오기 위해 기본 세팅.
         */
        WebClient webClient = WebClient.builder()
                .baseUrl(BASE_URL_1365)
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientCodecConfigurer ->
                                clientCodecConfigurer.defaultCodecs()
                                        .jaxb2Encoder(new Jaxb2XmlEncoder())
                        ).codecs(clientCodecConfigurer ->
                                clientCodecConfigurer.defaultCodecs()
                                        .jaxb2Decoder(new Jaxb2XmlDecoder())
                        )
                        .build()
                )
                .build();
        /**
         * 내가 원하는 형식으로 반환 성공 !
         */
        String res = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/rest")
                        .path("/VolunteerPartcptnService")
                        .path("getVltrSearchWordList")
                        .queryParam("serviceKey", api_key_1365)
                        .queryParam("noticeEndde", num)
                        .build())
                .accept(MediaType.valueOf(MediaType.APPLICATION_XML_VALUE))
                .retrieve()
                .bodyToMono(String.class)
                .block();


        /**
         * String format을 활용한 xml 활용

        String url = String.format("http://openapi.1365.go.kr/openapi/service/rest/VolunteerPartcptnService/getVltrSearchWordList?serviceKey=%s&noticeEndde=%d", api_key_1365, num);
        String res = webClient.get()
                .uri(url)
                .accept(MediaType.valueOf(MediaType.APPLICATION_XML_VALUE))
                .retrieve()
                .bodyToMono(String.class)
                .block();
         */

        /**
         * 로그 확인
         log.info("num : {}", num);
         log.info("response : {}", res);
         */

        return res;
    }

    public Volunteer returnData(Integer num) {
        /**
         * open api를 xml 형식으로 받아오기 위해 기본 세팅.
         */
        WebClient webClient = WebClient.builder()
                .baseUrl(BASE_URL_1365)
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientCodecConfigurer ->
                                clientCodecConfigurer.defaultCodecs()
                                        .jaxb2Encoder(new Jaxb2XmlEncoder())
                        ).codecs(clientCodecConfigurer ->
                                clientCodecConfigurer.defaultCodecs()
                                        .jaxb2Decoder(new Jaxb2XmlDecoder())
                        )
                        .build()
                )
                .build();
        /**
         * 내가 원하는 형식으로 반환 성공 !
         */
        Volunteer res = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/rest")
                        .path("/VolunteerPartcptnService")
                        .path("getVltrSearchWordList")
                        .queryParam("serviceKey", api_key_1365)
                        .queryParam("noticeEndde", num)
                        .build())
                .accept(MediaType.valueOf(MediaType.APPLICATION_XML_VALUE))
                .retrieve()
                .bodyToMono(Volunteer.class)
                .block();
        /**
         * 로그 확인
         log.info("res : {}", res.toString());
         */
        return res;
    }

    public String employmentSupport(Integer startIndex, Integer endIndex) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        //이렇게 URI의 빌드 설정을 완료하면 WebClient의 인스턴스를 생성할 때 해당 UriBuilder로 uri를 만든다고 설정하면 된다.
        // 기본 세팅 진행
        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        //필요한 값들 요청.
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("{api_key}/json/tbViewProgram/{startIndex}/{endIndex}")
                        .build(api_key, startIndex, endIndex))
                .retrieve()
                .bodyToMono(String.class)
                .block();


        /**
         * 결과 확인 log
         log.info("반환되는 값 : {}", response.toString());
         */
        return response;
    }

    public EmploymentJsonDto returnEmploymentDto(Integer startIndex, Integer endIndex) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        EmploymentJsonDto res = webClient.mutate() // mutate() => Return a builder to create a new WebClient whose settings are replicated from the current WebClient.
                .uriBuilderFactory(factory)
                .build()
                .get().uri(uriBuilder ->
                        uriBuilder.path("{api_key}/json/tbViewProgram/{startIndex}/{endIndex}")
                                .build(api_key, startIndex, endIndex))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(EmploymentJsonDto.class)
                .block();

        return res;

    }


}
