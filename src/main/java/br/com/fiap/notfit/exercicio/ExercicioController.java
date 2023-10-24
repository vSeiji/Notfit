package br.com.fiap.notfit.exercicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/exercicio")
public class ExercicioController {
    
    @Autowired
    ExercicioService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("exercicios", service.findAll());
        return "exercicio/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        if(service.delete(id)) {
            redirect.addFlashAttribute("success", "Exercicio apagado com sucesso");
        }else{
            redirect.addFlashAttribute("error", "Exercicio não foi apagado");
        }
        return "redirect:/exercicio";
    }

    @GetMapping("new")
    public String form(Exercicio exercicio) {
        return "exercicio/form";
    }

    @PostMapping
    public String create(@Valid Exercicio exercicio , BindingResult result,RedirectAttributes redirect) {
        if (result.hasErrors()) return "exercicio/form";
        service.save(exercicio);
        redirect.addFlashAttribute("success", "Exercicio cadastrado com sucesso");
        return "redirect:/exercicio";
    }
}
