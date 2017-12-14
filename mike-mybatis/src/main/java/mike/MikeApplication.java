package mike;

import mike.mapper.InsertMapper;
import mike.mapper.SelectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MikeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MikeApplication.class, args);
    }

    @Autowired
    InsertMapper insertMapper;
    @Autowired
    SelectMapper selectMapper;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(selectMapper.findAll());

        insertMapper.insert("dummy");

        System.out.println(selectMapper.findAll());
    }
}
