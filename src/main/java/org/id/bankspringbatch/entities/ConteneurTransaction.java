package org.id.bankspringbatch.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor @AllArgsConstructor @Data @ToString
public class ConteneurTransaction {
    private Long idTransaction;
    private Long idCompte;
    private double montant;
    private Date dateTransaction;

}
