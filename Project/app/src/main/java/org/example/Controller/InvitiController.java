package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.Controller.Responses.InvitoResponse;
import org.example.Gestori.GestoreInviti;
import org.example.Model.Invito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Caso d'uso: inviti a un team, accettazione/rifiuto. Invariante 1
 * (un utente in un solo team) è enforced dentro GestoreInviti.accettaInvito.
 */
@RestController
@RequestMapping("/inviti")
@RequiredArgsConstructor
class InvitiController {
    private final GestoreInviti gestoreInviti;

    @PostMapping
    ResponseEntity<InvitoResponse> invita(@RequestParam long utenteId, @RequestParam long teamId) {
        Invito invito = gestoreInviti.invita(utenteId, teamId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMapper.toResponse(invito));
    }

    @PostMapping("/{id}/accetta")
    InvitoResponse accettaInvito(@PathVariable long id) {
        return ResponseMapper.toResponse(gestoreInviti.accettaInvito(id));
    }

    @PostMapping("/{id}/rifiuta")
    InvitoResponse rifiutaInvito(@PathVariable long id) {
        return ResponseMapper.toResponse(gestoreInviti.rifiutaInvito(id));
    }

    @GetMapping("/utente/{utenteId}/pendenti")
    List<InvitoResponse> recuperaInvitiPendenti(@PathVariable long utenteId) {
        return gestoreInviti.recuperaInvitiPendenti(utenteId).stream().map(ResponseMapper::toResponse).toList();
    }
}
