package org.example.Gestori;

import lombok.RequiredArgsConstructor;
import org.example.Model.Staff;
import org.example.Model.TipoAccount;
import org.example.Model.Utente;
import org.example.Repository.RepositoryRuoloStaff;
import org.example.Repository.RepositoryStaff;
import org.example.Repository.RepositoryUtenti;
import org.example.dto.DatiRegistrazione;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GestoreAutenticazione {
    private final RepositoryStaff repositoryStaff;
    private final RepositoryUtenti repositoryUtenti;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public boolean registra(TipoAccount tipoAccount, DatiRegistrazione datiRegistrazione){
        validaDati(datiRegistrazione);
        datiRegistrazione.setPassword(passwordEncoder.encode(datiRegistrazione.getPassword()));
        if (tipoAccount == TipoAccount.UTENTE) {
            repositoryUtenti.save(new Utente(datiRegistrazione));
        } else if (tipoAccount == TipoAccount.STAFF) {
            repositoryStaff.save(new Staff(datiRegistrazione));
        } else {
            throw new IllegalArgumentException("Tipo account non valido");
        }
        return true;
    }
    private void validaDati(DatiRegistrazione datiRegistrazione){
        if (datiRegistrazione.getUsername() == null || datiRegistrazione.getUsername().isBlank()
                || datiRegistrazione.getPassword() == null || datiRegistrazione.getPassword().isBlank()) {
            throw new IllegalArgumentException("Username o password vuoti");
        }
        if (datiRegistrazione.getEmail() == null || datiRegistrazione.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email obbligatoria");
        }
        if (repositoryStaff.existsByUsername(datiRegistrazione.getUsername())
                || repositoryUtenti.existsByUsername(datiRegistrazione.getUsername())) {
            throw new IllegalArgumentException("Username già in uso");
        }
    }
    @Transactional(readOnly = true)
    public Utente autenticaUtente(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Username o password vuoti");
        }
        Utente utente = repositoryUtenti.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Credenziali non valide"));
        if (!passwordEncoder.matches(password, utente.getPassword())) {
            throw new IllegalArgumentException("Credenziali non valide");
        }
        return utente;
    }

    @Transactional(readOnly = true)
    public Staff autenticaStaff(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Username o password vuoti");
        }
        Staff staff = repositoryStaff.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Credenziali non valide"));
        if (!passwordEncoder.matches(password, staff.getPassword())) {
            throw new IllegalArgumentException("Credenziali non valide");
        }
        return staff;
    }

}
