package org.id.bankspringbatch.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity @NoArgsConstructor @AllArgsConstructor @Data @ToString
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTransaction;
    private double montant;
    private Date dateTransaction;
    private Date dateDebit;
    @ManyToOne
    private Compte compte;

}
