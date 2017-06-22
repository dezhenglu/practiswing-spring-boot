package bravo.cunkoriented;

import bravo.cunkoriented.listener.BravoChunkListener;
import bravo.cunkoriented.listener.BravoJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.SimpleBatchConfiguration;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link EnableBatchProcessing} をつけることで {@link SimpleBatchConfiguration} がインポートされる。
 * これで {@link JobBuilderFactory} などが使えるようになる。
 */
@Configuration
@EnableBatchProcessing
public class ChunkOrientedBatchConfiguration {

    @Bean
    public Job importUserJob(JobBuilderFactory jobs,
                             Step bravoStep,
                             BravoJobListener listener) {
        return jobs.get("bravoJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(bravoStep)
                .end()
                .build();
    }

    @Bean
    public Step bravoStep(StepBuilderFactory factory,
                      PhoneticToLetterItemProcessor processor,
                      ItemReader<Phonetic> reader,
                      ItemWriter<Letter> writer,
                      BravoChunkListener listener) {
        return factory.get("bravoStep")
                .<Phonetic, Letter>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(listener)
                .build();
    }

    @Bean
    public ItemWriter<Letter> writer(JdbcTemplate jdbcTemplate) {
        return items -> {
            List<Object[]> args = items.stream()
                    .map(item -> new Object[]{item.value})
                    .collect(Collectors.toList());
            jdbcTemplate.batchUpdate("INSERT INTO letter(value) VALUES(?)", args);
        };
    }

    @Bean
    public ItemReader<Phonetic> reader() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"letter", "phonetic"});
        FieldSetMapper<Phonetic> fieldSetMapper = fieldSet -> new Phonetic(fieldSet.readString("phonetic"));
        LineMapper<Phonetic> lineMapper = (line, lineNumber) -> fieldSetMapper.mapFieldSet(tokenizer.tokenize(line));

        FlatFileItemReader<Phonetic> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("sample-data.csv"));
        reader.setLineMapper(lineMapper);
        return reader;
    }
}
