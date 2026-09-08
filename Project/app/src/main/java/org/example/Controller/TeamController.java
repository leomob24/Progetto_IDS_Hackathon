package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.Controller.Requests.CreaTeamRequest;
import org.example.Controller.Requests.IbanRequest;
import org.example.Controller.Responses.TeamResponse;
import org.example.Controller.Responses.UtenteResponse;
import org.example.Gestori.GestoreTeam;
import org.example.Repository.RepositoryTeam;
import org.example.Model.Team;
import org.example.Model.Utente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Caso d'uso: creazione team, iscrizione a un hackathon, gestione membri.
 */
@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
class TeamController {
    private final GestoreTeam gestoreTeam;
    private final RepositoryTeam repositoryTeam;

    @GetMapping("/valida")
    boolean valida(@RequestParam String nome) {
        return gestoreTeam.valida(nome);
    }

    @PostMapping
    ResponseEntity<TeamResponse> creaTeam(@RequestBody CreaTeamRequest richiesta, @RequestParam long utenteId) {
        Team team = gestoreTeam.creaTeam(richiesta.nome(), utenteId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMapper.toResponse(team));
    }

    @PutMapping("/{id}/iban")
    TeamResponse addIban(@PathVariable long id, @RequestBody IbanRequest richiesta) {
        Team team = repositoryTeam.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Team non trovato"));
        return ResponseMapper.toResponse(gestoreTeam.addIban(team, richiesta.iban()));
    }

    @GetMapping("/iscrizione")
    boolean esisteIscrizione(@RequestParam long hackathonId, @RequestParam long teamId) {
        return gestoreTeam.esisteIscrizione(hackathonId, teamId);
    }

    @PostMapping("/iscrizioni")
    boolean iscriviTeam(@RequestParam long hackathonId, @RequestParam long teamId) {
        return gestoreTeam.iscriviTeam(hackathonId, teamId);
    }

    @DeleteMapping("/membri/{utenteId}")
    UtenteResponse rimuoviMembro(@PathVariable long utenteId) {
        Utente utente = gestoreTeam.rimuoviMembro(utenteId);
        return ResponseMapper.toResponse(utente);
    }

    @GetMapping("/hackathon/{hackathonId}")
    List<TeamResponse> recuperaTeamIscritti(@PathVariable long hackathonId) {
        return gestoreTeam.recuperaTeamIscritti(hackathonId).stream().map(ResponseMapper::toResponse).toList();
    }
}
