package ar.com.plantaciones.controller;

import ar.com.plantaciones.entities.Compradores;
import ar.com.plantaciones.entities.Plantaciones;
import ar.com.plantaciones.entities.Ventas;
import ar.com.plantaciones.services.ICompradoresService;
import ar.com.plantaciones.services.IPlantacionesService;
import ar.com.plantaciones.services.IVentasService;
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
public class VentasController {

    @Autowired
    IVentasService entityService;

    @Autowired
    IPlantacionesService plantacionesService;

    @Autowired
    ICompradoresService compradoresService;

    @RequestMapping(value = "/ventas", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        Page<Ventas> centroPage = entityService.findAll(pageable);
        PageWrapper<Ventas> page = new PageWrapper<Ventas>(centroPage, "/ventas");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Ventas());
        model.addAttribute("compradores", compradoresService.getAll());
        return "../ventas/index";
    }

    @RequestMapping("ventas/refresh")
    public String refresh() {
        return "redirect:/ventas";
    }

    @RequestMapping(value = "ventas/search", method = RequestMethod.POST)
    public String search(Model model,
                         @RequestParam("comprador") Integer idComprador,
                         @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
                         @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        Compradores comprador = new Compradores();
        comprador.setId(idComprador);
        model.addAttribute("entities", entityService.getCompradoresFechas(comprador, desde, hasta));
        model.addAttribute("entity", new Ventas());
        model.addAttribute("compradores", compradoresService.getAll());
        model.addAttribute("page", null);
        return "../ventas/index";
    }

    @RequestMapping("ventas/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Ventas());
        cargarCombos(model);
        return "../ventas/edit";
    }

    @RequestMapping("ventas/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        cargarCombos(model);
        return "../ventas/edit";
    }

    @RequestMapping(value = "ventas", method = RequestMethod.POST)
    public String save(Model model, @Validated Ventas entity) {
        if (entity.getFecha() == null || entity.getPlantaciones() == null || entity.getCompradores() == null) {
            model.addAttribute("message", "Fecha, Plantación o Comprador Incorrecto");
            model.addAttribute("entity", entity);
            cargarCombos(model);
            return "../ventas/edit";
        }

        entityService.save(entity);
        return "redirect:/ventas";
    }

    @RequestMapping("ventas/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Ventas entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/ventas";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Ventas> centroPage = entityService.findAll(pageable);
            PageWrapper<Ventas> page = new PageWrapper<Ventas>(centroPage, "/ventas");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Ventas());
            model.addAttribute("compradores", compradoresService.getAll());
            return "../ventas/index";
        }
    }

    private void cargarCombos(Model model) {
        List<Plantaciones> plantacionesList = plantacionesService.getAll();
        model.addAttribute("plantaciones", plantacionesList);
        List<Compradores> compradoresList = compradoresService.getAll();
        model.addAttribute("compradores", compradoresList);
    }

}