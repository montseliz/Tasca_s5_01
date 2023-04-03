package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n03.configuration;

import io.netty.channel.ChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfiguration {

    /**
     * Bean que crea i retorna un WebClient configurat per connectar-se a l'API del nivell 2.
     * Defineix el WebClient amb l'URL base de l'API, el connector del client HTTP personalitzat
     * i una capçalera per defecte per les sol·licituds realitzades.
     */
    @Bean
    public WebClient webClient() {

        /**
         * Client HTTP personalitzat que s'utilitza per crear el WebClient.
         * Inclou opcions de temps d'espera per la connexió i la resposta.
         */
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                .responseTimeout(Duration.ofSeconds(10));

        return WebClient.builder().baseUrl("http://localhost:9001/flowers")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}