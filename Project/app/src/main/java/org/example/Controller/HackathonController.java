package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.Controller.Responses.HackathonResponse;
import org.example.Controller.Responses.TeamResponse;
import org.example.Gestori.GestoreHackathon;
import org.example.Model.Hackathon;
import org.example.Model.Team;
import org.example.dto.DatiHackathon;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Caso d'uso: creazione/consultazione hackathon, proclamazione vincitore, classifica.
 */
@RestController
@RequestMapping("/hackathon")
@RequiredArgsConstructor
class HackathonController {
    private final GestoreHackathon gestoreHackathon;

    @PostMapping
    ResponseEntity<HackathonResponse> creaHackathon(@RequestBody DatiHackathon dati,
                                                      @RequestParam long organizzatoreId,
                                                      @RequestParam long giudiceId,
                                                      @RequestParam List<Long> mentoriIds) {
        Hackathon hackathon = gestoreHackathon.creaHackathon(dati, organizzatoreId, giudiceId, mentoriIds);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMapper.toResponse(hackathon));
    }

    @GetMapping
    List<HackathonResponse> getListaHackathon() {
        return gestoreHackathon.getListaHackathon().stream().map(ResponseMapper::toResponse).toList();
    }

    @GetMapping("/in-iscrizione")
    List<HackathonResponse> getHackathonInIscrizione() {
        return gestoreHackathon.getHackathonInIscrizione().stream().map(ResponseMapper::toResponse).toList();
    }

    @GetMapping("/{id}/classifica")
    List<TeamResponse> getClassifica(@PathVariable long id) {
        return gestoreHackathon.getClassifica(id).stream().map(ResponseMapper::toResponse).toList();
    }

    @GetMapping("/{id}/ruoli-assegnati")
    boolean ruoliStaffAssegnati(@PathVariable long id) {
        return gestoreHackathon.ruoliStaffAssegnati(id);
    }

    @PostMapping("/{id}/avvia")
    HackathonResponse avviaHackathon(@PathVariable long id) {
        return ResponseMapper.toResponse(gestoreHackathon.avviaHackathon(id));
    }

    @PostMapping("/{id}/valuta")
    HackathonResponse valutaHackathon(@PathVariable long id) {
        return ResponseMapper.toResponse(gestoreHackathon.valutaHackathon(id));
    }

    @PostMapping("/{id}/concludi")
    HackathonResponse concludiHackathon(@PathVariable long id) {
        return ResponseMapper.toResponse(gestoreHackathon.concludiHackathon(id));
    }

    @PostMapping("/{id}/vincitore")
    TeamResponse proclamaVincitore(@PathVariable long id, @RequestParam long teamId) {
        Team team = gestoreHackathon.proclamaVincitore(id, teamId);
        return ResponseMapper.toResponse(team);
    }
}
