package mike.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperScannerConfigure {

    @Configuration
    @MapperScan(basePackages = "mike.mapper", annotationClass = HogeMapper.class)
    static class HogeScan {
    }

    @Configuration
    @MapperScan(basePackages = "mike.mapper", annotationClass = FugaMapper.class)
    static class FugaScan {
    }
}
