package org.id.bankspringbatch.items;

import org.id.bankspringbatch.entities.Transaction;
import org.id.bankspringbatch.repositories.TransactionRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionItemWriter implements ItemWriter<Transaction> {
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public void write(List<? extends Transaction> list) throws Exception {
        transactionRepository.saveAll(list);

    }
}
