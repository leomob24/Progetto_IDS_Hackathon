package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.Controller.Requests.CreaSegnalazioneRequest;
import org.example.Controller.Responses.SegnalazioneResponse;
import org.example.Controller.Responses.TeamResponse;
import org.example.Gestori.GestoreSegnalazioni;
import org.example.Model.Segnalazione;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Caso d'uso: segnalazione di un team da parte di un Mentore, squalifica/rigetto da parte dell'Organizzatore.
 */
@RestController
@RequestMapping("/segnalazioni")
@RequiredArgsConstructor
class SegnalazioniController {
    private final GestoreSegnalazioni gestoreSegnalazioni;

    @PostMapping
    ResponseEntity<SegnalazioneResponse> creaSegnalazione(@RequestBody CreaSegnalazioneRequest richiesta) {
        Segnalazione segnalazione = gestoreSegnalazioni.creaSegnalazione(richiesta.motivazione(),
                richiesta.teamId(), richiesta.hackathonId(), richiesta.mentoreId());
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMapper.toResponse(segnalazione));
    }

    @GetMapping("/hackathon/{hackathonId}")
    List<SegnalazioneResponse> recuperaSegnalazioni(@PathVariable long hackathonId) {
        return gestoreSegnalazioni.recuperaSegnalazioni(hackathonId).stream().map(ResponseMapper::toResponse).toList();
    }

    @PostMapping("/{id}/squalifica")
    TeamResponse squalifica(@PathVariable long id) {
        return ResponseMapper.toResponse(gestoreSegnalazioni.squalifica(id));
    }

    @PostMapping("/{id}/respingi")
    SegnalazioneResponse respingiSegnalazione(@PathVariable long id) {
        return ResponseMapper.toResponse(gestoreSegnalazioni.respingiSegnalazione(id));
    }
}
