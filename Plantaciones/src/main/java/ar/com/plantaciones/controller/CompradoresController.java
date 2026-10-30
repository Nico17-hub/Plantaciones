package ar.com.plantaciones.controller;

import ar.com.plantaciones.entities.*;
import ar.com.plantaciones.services.ICompradoresService;
import ar.com.plantaciones.utiles.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;


@Controller
public class CompradoresController {

    @Autowired
    ICompradoresService entityService;

    @RequestMapping(value = "/compradores", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        Page<Compradores> centroPage = entityService.findAll(pageable);
        PageWrapper<Compradores> page = new PageWrapper<Compradores>(centroPage, "/compradores");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Compradores());
        return "../compradores/index";
    }

    @RequestMapping("/compradores/refresh")
    public String refresh() {
        return "redirect:/compradores";
    }

    @RequestMapping(value = "compradores/search", method = RequestMethod.POST)
    public String search(Model model, Compradores entity) {
        if (entity.getName().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByName(entity.getName()));
        model.addAttribute("entity", new Compradores());
        model.addAttribute("page", null);
        return "../compradores/index";
    }

    @RequestMapping("compradores/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Compradores());
        return "../compradores/edit";
    }

    @RequestMapping("compradores/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        return "../compradores/edit";
    }

    @RequestMapping(value = "compradores", method = RequestMethod.POST)
    public String save(Model model, @Validated Compradores entity) {
        if (entity.getName().equals("")) {
            model.addAttribute("message", "Nombre Incorrecto");
            model.addAttribute("entity", entity);
            return "../compradores/edit";
        }

        entityService.save(entity);
        return "redirect:/compradores";
    }


@RequestMapping("compradores/delete/{id}")
public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
    try {
        Compradores entity = entityService.get(id);
        entityService.delete(entity);
        return "redirect:/compradores";
    } catch (Exception e) {
        model.addAttribute("message", e.getMessage().toString());
        Page<Compradores> centroPage = entityService.findAll(pageable);
        PageWrapper<Compradores> page = new PageWrapper<Compradores>(centroPage, "/compradores");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Compradores());
        return "../compradores/index";
    }
}

}
