package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.Controller.Requests.LoginRequest;
import org.example.Controller.Responses.StaffResponse;
import org.example.Controller.Responses.UtenteResponse;
import org.example.Gestori.GestoreAutenticazione;
import org.example.Model.Staff;
import org.example.Model.TipoAccount;
import org.example.Model.Utente;
import org.example.dto.DatiRegistrazione;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Caso d'uso: registrazione e login.
 * Autenticazione senza sessione/JWT: il client riusa l'id restituito dal login
 * come utenteId/staffId nei parametri delle chiamate successive.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
class AutenticazioneController {
    private final GestoreAutenticazione gestoreAutenticazione;

    @PostMapping("/utenti")
    ResponseEntity<Void> registraUtente(@RequestBody DatiRegistrazione dati) {
        gestoreAutenticazione.registra(TipoAccount.UTENTE, dati);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/staff")
    ResponseEntity<Void> registraStaff(@RequestBody DatiRegistrazione dati) {
        gestoreAutenticazione.registra(TipoAccount.STAFF, dati);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login/utente")
    UtenteResponse loginUtente(@RequestBody LoginRequest login) {
        Utente utente = gestoreAutenticazione.autenticaUtente(login.username(), login.password());
        return ResponseMapper.toResponse(utente);
    }

    @PostMapping("/login/staff")
    StaffResponse loginStaff(@RequestBody LoginRequest login) {
        Staff staff = gestoreAutenticazione.autenticaStaff(login.username(), login.password());
        return ResponseMapper.toResponse(staff);
    }
}
