package bravo.cunkoriented;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * バッチの本体処理とも言えるもの。
 * バッチのシンプルなStepは、 {@link ItemReader#read()} の戻り値が {@link ItemProcessor#process(Object)} に渡され、
 * さらにその戻り値が {@link ItemWriter#write(List)} に渡される形で構成される。
 */
@Component
public class PhoneticToLetterItemProcessor implements ItemProcessor<Phonetic, Letter> {

    private static final Logger logger = LoggerFactory.getLogger(PhoneticToLetterItemProcessor.class);

    @Override
    public Letter process(Phonetic phonetic) throws Exception {
        Letter letter = new Letter(phonetic.value.substring(0, 1));

        logger.info("Converting ({}) into ({})", phonetic, letter);

        return letter;
    }
}
