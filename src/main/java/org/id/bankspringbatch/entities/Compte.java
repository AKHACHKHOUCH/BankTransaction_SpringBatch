package org.id.bankspringbatch.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Compte {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCompte;
    private double solde;
    @OneToMany(mappedBy = "compte")
    private List<Transaction> transactions = new ArrayList<>();
}
