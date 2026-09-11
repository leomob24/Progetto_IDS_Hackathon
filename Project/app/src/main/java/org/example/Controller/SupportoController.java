package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.Controller.Requests.CreaRichiestaSupportoRequest;
import org.example.Controller.Responses.IscrizioneResponse;
import org.example.Controller.Responses.RichiestaDiSupportoResponse;
import org.example.Gestori.GestoreSupporto;
import org.example.Model.RichiestaDiSupporto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Caso d'uso: richieste di supporto e pianificazione call col Mentore.
 */
@RestController
@RequestMapping("/supporto")
@RequiredArgsConstructor
class SupportoController {
    private final GestoreSupporto gestoreSupporto;

    @PostMapping
    ResponseEntity<RichiestaDiSupportoResponse> creaRichiestaDiSupporto(@RequestBody CreaRichiestaSupportoRequest richiesta,
                                                                          @RequestParam long iscrizioneId) {
        RichiestaDiSupporto richiestaDiSupporto = gestoreSupporto.creaRichiestaDiSupporto(
                richiesta.oggetto(), richiesta.descrizione(), iscrizioneId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMapper.toResponse(richiestaDiSupporto));
    }

    @GetMapping("/team/{teamId}/iscrizioni")
    List<IscrizioneResponse> recuperaIscrizioniTeam(@PathVariable long teamId) {
        return gestoreSupporto.recuperaIscrizioniTeam(teamId).stream().map(ResponseMapper::toResponse).toList();
    }

    @GetMapping("/hackathon/{hackathonId}")
    List<RichiestaDiSupportoResponse> recuperaRichiesteDiSupporto(@PathVariable long hackathonId) {
        return gestoreSupporto.recuperaRichiesteDiSupporto(hackathonId).stream().map(ResponseMapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    RichiestaDiSupportoResponse recuperaDati(@PathVariable long id) {
        return ResponseMapper.toResponse(gestoreSupporto.recuperaDati(id));
    }
}
