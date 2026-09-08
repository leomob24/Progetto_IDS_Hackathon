package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.Controller.Responses.UtenteResponse;
import org.example.Gestori.GestoreUtenti;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Caso d'uso: consultazione utenti senza team — usato per popolare la lista
 * di utenti invitabili in un team.
 */
@RestController
@RequestMapping("/utenti")
@RequiredArgsConstructor
class UtentiController {
    private final GestoreUtenti gestoreUtenti;

    @GetMapping("/senza-team")
    List<UtenteResponse> getUtentiSenzaTeam() {
        return gestoreUtenti.getUtentiSenaTeam().stream().map(ResponseMapper::toResponse).toList();
    }
}
