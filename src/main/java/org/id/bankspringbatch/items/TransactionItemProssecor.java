package org.id.bankspringbatch.items;

import org.id.bankspringbatch.entities.Compte;
import org.id.bankspringbatch.entities.ConteneurTransaction;
import org.id.bankspringbatch.entities.Transaction;
import org.id.bankspringbatch.repositories.CompteRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TransactionItemProssecor implements ItemProcessor<ConteneurTransaction, Transaction> {
    Date date = new Date();
    @Autowired
    private CompteRepository compteRepository;
    @Override
    public Transaction process(ConteneurTransaction conteneurTransaction) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setIdTransaction(conteneurTransaction.getIdTransaction());
        transaction.setDateTransaction(conteneurTransaction.getDateTransaction());
        transaction.setMontant(conteneurTransaction.getMontant());
        transaction.setDateDebit(date);
        Compte compte = compteRepository.findById(conteneurTransaction.getIdCompte()).get();
        transaction.setCompte(compte);
        return transaction;
    }
}
