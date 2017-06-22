package bravo.cunkoriented.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BravoJobListener implements JobExecutionListener {

    private static final Logger logger = LoggerFactory.getLogger(BravoJobListener.class);

    private final JdbcTemplate jdbcTemplate;

    public BravoJobListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("beforeJob");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info("afterJob");

        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            jdbcTemplate.queryForList("SELECT value FROM letter", String.class)
                    .forEach(value -> logger.info("Found <{}> in the database.", value));
        }
    }
}
