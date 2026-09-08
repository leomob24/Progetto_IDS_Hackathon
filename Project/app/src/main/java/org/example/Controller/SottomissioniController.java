package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.Controller.Responses.IscrizioneResponse;
import org.example.Controller.Responses.SottomissioneResponse;
import org.example.Gestori.GestoreSottomissioni;
import org.example.Model.Sottomissione;
import org.example.dto.DatiSottomissione;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Caso d'uso: invio/aggiornamento sottomissione. Invariante 4
 * (scadenza + hackathon IN_CORSO + una sola sottomissione per iscrizione) enforced nel Gestore/State.
 */
@RestController
@RequestMapping("/sottomissioni")
@RequiredArgsConstructor
class SottomissioniController {
    private final GestoreSottomissioni gestoreSottomissioni;

    @PostMapping
    ResponseEntity<SottomissioneResponse> inviaSottomissione(@RequestBody DatiSottomissione dati,
                                                                @RequestParam long iscrizioneId) {
        Sottomissione sottomissione = gestoreSottomissioni.inviaSottomissione(dati, iscrizioneId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMapper.toResponse(sottomissione));
    }

    @GetMapping("/team/{teamId}/iscrizioni")
    List<IscrizioneResponse> recuperaIscrizioni(@PathVariable long teamId) {
        return gestoreSottomissioni.recuperaIscrizioni(teamId).stream().map(ResponseMapper::toResponse).toList();
    }

    @GetMapping("/hackathon/{hackathonId}")
    List<SottomissioneResponse> recuperaSottomissioni(@PathVariable long hackathonId) {
        return gestoreSottomissioni.recuperaSottomissioni(hackathonId).stream().map(ResponseMapper::toResponse).toList();
    }

    @GetMapping
    SottomissioneResponse getSottomissione(@RequestParam long teamId, @RequestParam long hackathonId) {
        return ResponseMapper.toResponse(gestoreSottomissioni.getSottomissione(teamId, hackathonId));
    }

    @PutMapping("/{id}")
    SottomissioneResponse aggiornaSottomissione(@PathVariable long id, @RequestBody DatiSottomissione dati) {
        return ResponseMapper.toResponse(gestoreSottomissioni.aggiornaSottomissione(id, dati));
    }
}
