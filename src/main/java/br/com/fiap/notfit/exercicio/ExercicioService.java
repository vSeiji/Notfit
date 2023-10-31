package br.com.fiap.notfit.exercicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.notfit.user.User;

@Service
public class ExercicioService {

    @Autowired
    ExercicioRepository repository;

    public List<Exercicio> findAll() {
        return repository.findAll();
    }

    public boolean delete(Long id) {
        var ex = repository.findById(id);
        if(ex.isEmpty()) return false;
        repository.deleteById(id);
        return true;
    }

    public void save(Exercicio exercicio) {
        repository.save(exercicio);
    }

    public void catchExercicio(Long id, User user) {
        var opt = repository.findById(id);
        if (opt.isEmpty())
            throw new RuntimeException("Exercicio não encontrado");

        var exercicio = opt.get();

        if ( exercicio.getUser() != null && exercicio.getUser().equals(user))
            throw new RuntimeException("Você já selecionou esse exercicio");

        if (exercicio.getUser() != null)
            throw new RuntimeException("Exercicio ja atribuido");

        exercicio.setUser(user);
        repository.save(exercicio);
    }

        public void dropExercicio(Long id, User user) {
        var opt = repository.findById(id);
        if (opt.isEmpty())
            throw new RuntimeException("Exercicio não encontrado");

        var exercicio = opt.get();

        if (exercicio.getUser() != null)
            throw new RuntimeException("Exercicio não atribuido");

        if (!exercicio.getUser().equals(user))
            throw new RuntimeException("Você não pode largar o exercicio de outro");

        exercicio.setUser(null);
        repository.save(exercicio);
    }

}
