package ar.com.plantaciones.controller;

import ar.com.plantaciones.entities.Cosechas;
import ar.com.plantaciones.entities.Plantaciones;
import ar.com.plantaciones.services.ICosechasService;
import ar.com.plantaciones.services.IPlantacionesService;
import ar.com.plantaciones.utiles.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CosechasController {

    @Autowired
    ICosechasService entityService;

    @Autowired
    IPlantacionesService plantacionesService;

    @RequestMapping(value = "/cosechas", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        Page<Cosechas> centroPage = entityService.findAll(pageable);
        PageWrapper<Cosechas> page = new PageWrapper<Cosechas>(centroPage, "/cosechas");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Cosechas());
        return "../cosechas/index";
    }

    @RequestMapping("cosechas/refresh")
    public String refresh() {
        return "redirect:/cosechas";
    }

    @RequestMapping("cosechas/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Cosechas());
        List<Plantaciones> list = plantacionesService.getAll();
        model.addAttribute("plantaciones", list);
        return "../cosechas/edit";
    }

    @RequestMapping("cosechas/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        List<Plantaciones> list = plantacionesService.getAll();
        model.addAttribute("plantaciones", list);
        return "../cosechas/edit";
    }

    @RequestMapping(value = "cosechas", method = RequestMethod.POST)
    public String save(Model model, @Validated Cosechas entity) {
        if (entity.getFecha() == null || entity.getPlantaciones() == null) {
            model.addAttribute("message", "Fecha o Plantación Incorrecta");
            model.addAttribute("entity", entity);
            List<Plantaciones> list = plantacionesService.getAll();
            model.addAttribute("plantaciones", list);
            return "../cosechas/edit";
        }

        entityService.save(entity);
        return "redirect:/cosechas";
    }

    @RequestMapping(value = "cosechas/search", method = RequestMethod.POST)
    public String search(Model model,
                         @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
                         @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        model.addAttribute("entities", entityService.findByFecha(desde, hasta));
        model.addAttribute("entity", new Cosechas());
        model.addAttribute("page", null);
        return "../cosechas/index";
    }

    @RequestMapping("cosechas/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Cosechas entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/cosechas";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Cosechas> centroPage = entityService.findAll(pageable);
            PageWrapper<Cosechas> page = new PageWrapper<Cosechas>(centroPage, "/cosechas");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Cosechas());
            return "../cosechas/index";
        }
    }

}