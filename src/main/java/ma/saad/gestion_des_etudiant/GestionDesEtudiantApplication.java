package ma.saad.gestion_des_etudiant;

import ma.saad.gestion_des_etudiant.entities.Etudiant;
import ma.saad.gestion_des_etudiant.repositories.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class GestionDesEtudiantApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionDesEtudiantApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(EtudiantRepository etudiantRepository){
        return args -> {
            etudiantRepository.save(new Etudiant(null,"moutta","achraf","achraf@gmail.com",new Date(),"Male",true));
            etudiantRepository.save(new Etudiant(null,"bamoussa","kaoutar","kaoutar@gmail.com",new Date(),"Female",false));
            etudiantRepository.save(new Etudiant(null,"kabboura","yasin","yasin@gmail.com",new Date(),"Male",false));
            etudiantRepository.save(new Etudiant(null,"sahle","hassna","hassna@gmail.com",new Date(),"Female",true));

            etudiantRepository.findAll().forEach(e->{
                System.out.println(e.getNom()) ;
            });

        };
    }
}
