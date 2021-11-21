package com.covtracker.covtracker;

import com.covtracker.covtracker.entities.Cidade;
import com.covtracker.covtracker.entities.Empresa;
import com.covtracker.covtracker.entities.Endereco;
import com.covtracker.covtracker.entities.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class CovtrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CovtrackerApplication.class, args);
    }

//    @PostConstruct
//    public void initUsuarios() {
//		new Cidade()
//
//        List<Usuario> usuarios =
//                Stream.of(new Usuario(
//						BigInteger.valueOf(12345678910L),
//						"Bruno", "bruno123",
//						"123",
//						LocalDate.of(2000, 10, 24),
//						"M",
//						BigDecimal.valueOf(74),
//						BigDecimal.valueOf(1.84),
//						"Estagiario",
//						new Empresa(
//								"12345678900001",
//								"Nome fantasia",
//								"Razao Social",
//								BigDecimal.valueOf(4999999999L),
//								"email@empresa.com",
//								"TI",
//								new Endereco(1, "Rua tal", "48", "Tal", )
//								)))
//    }

}
