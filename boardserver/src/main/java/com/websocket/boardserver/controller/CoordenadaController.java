package com.websocket.boardserver.controller;

import com.websocket.boardserver.component.CommandLineInit;
import com.websocket.boardserver.model.Coordenada;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author Jacobo Palacio$
 * Date: 19/11/2023$
 */
@Controller
public class CoordenadaController {

    private final CommandLineInit commandLineInit; // Inyectar la instancia de CommandLineInit

    public CoordenadaController(CommandLineInit commandLineInit) {
        this.commandLineInit = commandLineInit;
    }

    @MessageMapping("/tablero")
    @SendTo("/tablero/coordenada")
    public Coordenada envio(Coordenada coordenada) {
        // Procesar la coordenada del canvas y enviarla a los clientes suscritos al mismo tema
        commandLineInit.agregarCoordenada(coordenada); // Agregar la coordenada a CommandLineInit
        return coordenada;
    }
}
