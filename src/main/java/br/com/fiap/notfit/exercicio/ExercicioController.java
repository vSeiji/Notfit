package br.com.fiap.notfit.exercicio;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.notfit.user.User;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/exercicio")
public class ExercicioController {
    
    @Autowired
    ExercicioService service;

    @Autowired
    MessageSource message;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user) {
        model.addAttribute("username", user.getAttribute("name"));
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        model.addAttribute("exercicios", service.findAll());
        return "exercicio/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        if(service.delete(id)) {
            redirect.addFlashAttribute("success", getMessage("exercicio.delete.success"));
        }else{
            redirect.addFlashAttribute("error", getMessage("exercicio.notfound"));
        }
        return "redirect:/exercicio";
    }

    @GetMapping("new")
    public String form(Exercicio exercicio, Model model, @AuthenticationPrincipal OAuth2User user) {
        model.addAttribute("username", user.getAttribute("name"));
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        return "exercicio/form";
    }

    @PostMapping
    public String create(@Valid Exercicio exercicio , BindingResult result,RedirectAttributes redirect) {
        if (result.hasErrors()) return "exercicio/form";
        service.save(exercicio);
        redirect.addFlashAttribute("success", getMessage("exercicio.create.success"));
        return "redirect:/exercicio";
    }

    private String getMessage(String code){
        return message.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    @GetMapping("catch/{id}")
    public String catchExercicio(@PathVariable Long id, @AuthenticationPrincipal OAuth2User user) {
        service.catchExercicio(id, User.convert(user));
        return "redirect:/exercicio";
    }

    
    @GetMapping("drop/{id}")
    public String dropExercicio(@PathVariable Long id, @AuthenticationPrincipal OAuth2User user) {
        service.dropExercicio(id, User.convert(user));
        return "redirect:/exercicio";
    }


}
