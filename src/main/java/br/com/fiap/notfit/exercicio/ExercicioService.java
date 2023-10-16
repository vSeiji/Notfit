package br.com.fiap.notfit.exercicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
