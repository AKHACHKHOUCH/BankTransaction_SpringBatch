package org.id.bankspringbatch.configuration;

import org.id.bankspringbatch.entities.ConteneurTransaction;
import org.id.bankspringbatch.entities.Transaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private ItemReader<ConteneurTransaction> itemReader;
    @Autowired
    private ItemWriter<Transaction> itemWriter;
    @Autowired
    private ItemProcessor<ConteneurTransaction, Transaction> itemProcessor;

    @Bean
    public Job job() {
        Step step = stepBuilderFactory.get("Transaction importée")
                .<ConteneurTransaction,Transaction>chunk(30)
                .reader(itemReader)
                .writer(itemWriter)
                .processor(itemProcessor)
                .build();
        return jobBuilderFactory.get("load ")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }
    @Bean
    public FlatFileItemReader<ConteneurTransaction> flatFileItemReader(@Value("${file}") Resource resource){
        FlatFileItemReader<ConteneurTransaction> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setName("csv reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }
    @Bean
    public LineMapper<ConteneurTransaction> lineMapper() {
        DefaultLineMapper<ConteneurTransaction> lineMapper = new DefaultLineMapper();
        DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("idTransaction","idCompte","monatant","dateTransaction");
        lineMapper.setLineTokenizer(lineTokenizer);
        BeanWrapperFieldSetMapper<ConteneurTransaction> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(ConteneurTransaction.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }


}
