package com.example.lionproject.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.http.codec.xml.Jaxb2XmlEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
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
    }

}