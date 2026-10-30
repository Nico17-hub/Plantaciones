package ar.com.plantaciones.controller;


import java.util.List;

import ar.com.plantaciones.entities.Cultivos;
import ar.com.plantaciones.entities.Plantaciones;
import ar.com.plantaciones.services.ICultivosService;
import ar.com.plantaciones.services.IPlantacionesService;
import ar.com.plantaciones.utiles.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlantacionesController {

    @Autowired
    IPlantacionesService entityService;
    @Autowired
    ICultivosService cultivoService;

    @RequestMapping(value = "/plantaciones", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {        
        Page<Plantaciones> centroPage = entityService.findAll(pageable);
        PageWrapper<Plantaciones> page = new PageWrapper<Plantaciones>(centroPage, "/plantaciones");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Plantaciones());
        return "../plantaciones/index";
    }

    @RequestMapping("/plantaciones/refresh")
    public String refresh() {
        return "redirect:/plantaciones";
    }

    @RequestMapping(value = "/plantaciones/search", method = RequestMethod.POST)
    public String search(Model model, Plantaciones entity) {
        if (entity.getPeriodo().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getPeriodo()));
        model.addAttribute("entity", new Plantaciones());
        model.addAttribute("page", null);
        return "../plantaciones/index";
    }

    @RequestMapping("/plantaciones/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Plantaciones());
        List<Cultivos> list = cultivoService.getAll();
        model.addAttribute("cultivos", list);
        return "../plantaciones/edit";
    }

    @RequestMapping("/plantaciones/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        List<Cultivos> list = cultivoService.getAll();
        model.addAttribute("cultivos", list);
        return "../plantaciones/edit";
    }

    @RequestMapping(value = "plantaciones", method = RequestMethod.POST)
    public String save(Model model, @Validated Plantaciones entity) {
        String errores = "";
        if (entity.getPeriodo().equals("")) errores += "Período Incorrecto ";

        if (!errores.equals("")) {
            model.addAttribute("message", errores);
            model.addAttribute("entity", entity);
            List<Cultivos> list = cultivoService.getAll();
            model.addAttribute("cultivos", list);
            return "../plantaciones/edit";
        }

        entityService.save(entity);
        return "redirect:/plantaciones";
    }

    @RequestMapping("plantaciones/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Plantaciones entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/plantaciones";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Plantaciones> centroPage = entityService.findAll(pageable);
            PageWrapper<Plantaciones> page = new PageWrapper<Plantaciones>(centroPage, "/plantaciones");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Plantaciones());
            return "../plantaciones/index";
        }
    }   
    
}
