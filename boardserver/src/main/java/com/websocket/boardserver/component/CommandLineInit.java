package com.websocket.boardserver.component;

import com.websocket.boardserver.model.Coordenada;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacobo Palacio$
 * Date: 19/11/2023$
 */
@Component
public class CommandLineInit implements CommandLineRunner {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "http://localhost:8080/tableroapp/send-coordenada";

    private final List<Coordenada> coordenadas = new ArrayList<>();

    public void agregarCoordenada(Coordenada nuevaCoordenada) {
        coordenadas.add(nuevaCoordenada);
        System.out.println("Coordenada agregada: " + nuevaCoordenada);
        // Puedes realizar más acciones aquí si es necesario, como enviar a otras partes del sistema, etc.
    }

    @Override
    public void run(String... args) throws Exception {
        enviarCoordenadasPeriodicamente(coordenadas);
    }

    public void enviarCoordenadasPeriodicamente(List<Coordenada> coordenadas){
        for(Coordenada coordenada : coordenadas){
            enviarCoordenada(coordenada);
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    public void enviarCoordenada(Coordenada coordenada){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Coordenada> requestEntiy = new HttpEntity<>(coordenada, headers);

        ResponseEntity<Coordenada> responseEntity = restTemplate.postForEntity(url, requestEntiy, Coordenada.class);

        if(responseEntity.getStatusCode().is2xxSuccessful()){
            System.out.println("Coordenada enviada: " + coordenada);
        }else{
            System.out.println("Error al enviar coordenada: " + coordenada);
        }
    }
}