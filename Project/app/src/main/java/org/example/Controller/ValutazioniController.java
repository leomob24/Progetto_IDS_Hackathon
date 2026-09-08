package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.Controller.Responses.ValutazioneResponse;
import org.example.Gestori.GestoreValutazioni;
import org.example.dto.DatiValutazione;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Caso d'uso: valutazione di una sottomissione da parte del Giudice assegnato.
 * Solo il Giudice assegnato all'hackathon, punteggio in [0,10].
 */
@RestController
@RequestMapping("/valutazioni")
@RequiredArgsConstructor
class ValutazioniController {
    private final GestoreValutazioni gestoreValutazioni;

    @PostMapping
    ResponseEntity<ValutazioneResponse> assegnaValutazione(@RequestBody DatiValutazione dati,
                                                              @RequestParam long sottomissioneId,
                                                              @RequestParam long giudiceId) {
        var valutazione = gestoreValutazioni.assegnaValutazione(dati, sottomissioneId, giudiceId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMapper.toResponse(valutazione));
    }
}
