package mx.edu.utez.examenlibros.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

public class initialConfig implements CommandLineRunner {
    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void run(String... args) throws Exception {
    }
}
