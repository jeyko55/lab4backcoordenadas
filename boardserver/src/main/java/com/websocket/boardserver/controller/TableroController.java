package com.websocket.boardserver.controller;

import com.websocket.boardserver.model.Coordenada;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jacobo Palacio$
 * Date: 19/11/2023$
 */
@RestController
@RequestMapping("/tableroapp")
public class TableroController {
    private final SimpMessagingTemplate template;

    public TableroController(SimpMessagingTemplate template){
        this.template = template;
    }

    @PostMapping("/send-coordenada")
    public void sendCoordenada(@RequestBody Coordenada coordenada){
        this.template.convertAndSend("/tablero/coordenada", coordenada);
    }
}