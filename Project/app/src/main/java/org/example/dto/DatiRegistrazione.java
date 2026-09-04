package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatiRegistrazione {
    private String nome;
    private String cognome;
    private String email;
    private String username;
    private String password;
}
