package br.com.fiap.notfit.exercicio;

import br.com.fiap.notfit.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Exercicio {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    String title;

    @Size(min = 10, message = "{exercicio.description.size}")
    String description;

    @Positive
    Integer Score;

    boolean status;

    @ManyToOne
    User user;
}
