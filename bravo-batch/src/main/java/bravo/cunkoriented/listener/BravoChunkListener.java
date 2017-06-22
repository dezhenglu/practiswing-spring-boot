package bravo.cunkoriented.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public class BravoChunkListener implements ChunkListener {

    private static final Logger logger = LoggerFactory.getLogger(BravoChunkListener.class);

    @Override
    public void beforeChunk(ChunkContext context) {
        logger.info("beforeChunk: {}", context.getStepContext());
    }

    @Override
    public void afterChunk(ChunkContext context) {
        logger.info("afterChunk: {}", context.getStepContext());
    }

    @Override
    public void afterChunkError(ChunkContext context) {
        logger.info("afterChunkError: {}", context.getStepContext());
    }
}
