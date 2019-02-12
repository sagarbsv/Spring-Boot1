package com.hackerrank.sample.batch;
 
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
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
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.hackerrank.sample.model.Sku;
 
@Configuration
@EnableBatchProcessing
public class BatchConfig {
     
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
 
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
 
    @Value("classPath:/input/inputData.csv")
    private Resource inputResource;
 
    @Bean
    public Job readCSVFileJob() {
        return jobBuilderFactory
                .get("readCSVFileJob")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }
 
    @Bean
    public Step step() {
        return stepBuilderFactory
                .get("step")
                .<Sku, Sku>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
     
    @Bean
    public ItemProcessor<Sku, Sku> processor() {
        return new DBLogProcessor();
    }
     
    @Bean
    public FlatFileItemReader<Sku> reader() {
        FlatFileItemReader<Sku> itemReader = new FlatFileItemReader<Sku>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(inputResource);
        return itemReader;
    }
 
    @Bean
    public LineMapper<Sku> lineMapper() {
        DefaultLineMapper<Sku> lineMapper = new DefaultLineMapper<Sku>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[] {"inventory_On_Hand", "min_Qty_Req","price","product_Label","product_Name" });
        lineTokenizer.setIncludedFields(new int[] { 0, 1, 2, 3, 4});
        BeanWrapperFieldSetMapper<Sku> fieldSetMapper = new BeanWrapperFieldSetMapper<Sku>();
        fieldSetMapper.setTargetType(Sku.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
 
    @Bean
    public JdbcBatchItemWriter<Sku> writer() {
        JdbcBatchItemWriter<Sku> itemWriter = new JdbcBatchItemWriter<Sku>();
        itemWriter.setDataSource(dataSource());
        itemWriter.setSql("INSERT INTO Sku (inventory_On_Hand, min_Qty_Req, price,product_Label,product_Name) VALUES (:inventory_On_Hand,:min_Qty_Req,:price,:product_Label,:product_Name)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Sku>());
        return itemWriter;
    }
     
  
    public DataSource dataSource(){
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder.addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .setType(EmbeddedDatabaseType.H2) 
                .build();
    }
}