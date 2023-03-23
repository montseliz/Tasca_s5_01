package cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.controllers;

import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.model.dto.BranchOfficeDTO;
import cat.itacademy.barcelonactiva.liz.montse.s05.t01.n01.model.services.IBranchOfficeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Anotació @Controller per manipular sol·licituds HTTP i retornar una vista en format HTML.
 */
@Controller
@RequestMapping("/branchOffices")
public class BranchOfficeController {

    private IBranchOfficeService branchOfficeService;
    @Autowired
    public BranchOfficeController(IBranchOfficeService branchOfficeService) {
        super();
        this.branchOfficeService = branchOfficeService;
    }

    /**
     * Pàgina principal on es mostren totes les branch offices.
     */
    @GetMapping(value = {"/getAll", ""})
    public String viewBranchOffices(Model model) {
        model.addAttribute("branchOffices", branchOfficeService.getAllBranchOffices());
        return "branchOffices";
    }

    /**
     * Pàgina per mostrar una branch office per id.
     */
    @GetMapping("/getOne/{id}")
    public String viewBranchOffice(@PathVariable int id, Model model) {
        model.addAttribute("branchOffice", branchOfficeService.getBranchOfficeById(id));
        return "oneBranchOffice";
    }

    /**
     * Formulari per crear una nova branch office.
     */
    @GetMapping("/create")
    public String createBranchOffice(Model model) {
        BranchOfficeDTO newBranchOfficeDTO = new BranchOfficeDTO();
        model.addAttribute("newBranchOffice", newBranchOfficeDTO);
        return "createBranchOffice";
    }

    /**
     * Un cop creada, si compleix les validacions, s'afegeix la nova branch office a la base de dades
     * i es redirigeix a la pàgina principal.
     */
    @PostMapping("/add")
    public String addBranchOffice(@Valid @ModelAttribute("newBranchOffice") BranchOfficeDTO newBranchOfficeDTO, BindingResult result) {
        if(result.hasErrors()) {
            return "createBranchOffice";
        } else {
            branchOfficeService.createBranchOffice(newBranchOfficeDTO);
            return "redirect:/branchOffices";
        }
    }

    /**
     * Formulari per editar/modificar una branch office.
     */
    @GetMapping("/edit/{id}")
    public String editBranchOffice(@PathVariable int id, Model model) {
        model.addAttribute("editedBranchOffice", branchOfficeService.getBranchOfficeById(id));
        return "editBranchOffice";
    }

    /**
     * Un cop modificada, si compleix les validacions, s'actualitza la branch office a la base de dades
     * i es redirigeix a la pàgina principal on es mostra el llistat de branch offices.
     */
    @PostMapping("/update")
    public String updateBranchOffice(@Valid @ModelAttribute("editedBranchOffice") BranchOfficeDTO newBranchOfficeDTO, BindingResult result) {
        if(result.hasErrors()) {
            return "editBranchOffice";
        } else {
            branchOfficeService.updateBranchOffice(newBranchOfficeDTO.getId(), newBranchOfficeDTO);
            return "redirect:/branchOffices/getOne/" + newBranchOfficeDTO.getId();
        }
    }

    /**
     * Eliminar una branch office per id.
     */
    @GetMapping("delete/{id}")
    public String deleteBranchOffice(@PathVariable int id, RedirectAttributes redirectAttributes) {
        branchOfficeService.deleteBranchOffice(id);
        redirectAttributes.addFlashAttribute("message", "Branch Office deleted successfully");
        return "redirect:/branchOffices";
    }

}

