package ma.saad.gestion_des_etudiant.web;

import lombok.AllArgsConstructor;
import ma.saad.gestion_des_etudiant.entities.Etudiant;
import ma.saad.gestion_des_etudiant.repositories.EtudiantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
@AllArgsConstructor
public class EtudiantController {
    private EtudiantRepository etudiantRepository;
    @GetMapping(path = "/index")
    public String etudiants(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword){
        Page<Etudiant> etudiantPage=etudiantRepository.findByNomContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listEtudiant",etudiantPage.getContent());
        model.addAttribute("pages",new int[etudiantPage.getTotalPages()]);
        model.addAttribute("current",page);
        model.addAttribute("keyword",keyword);
        return "etudiant";
    }

    @GetMapping("/delete")
    public String delete(Long id){
        etudiantRepository.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }

    @GetMapping( "/formEtudiant")
    public String formEtudiant (Model model){
        model.addAttribute("etudiant", new Etudiant());
        return "formEtudiant";
    }

    @PostMapping(path ="/save")
    public String save (Model model, @Valid Etudiant etudiant, BindingResult bindingResult,
                        @RequestParam(defaultValue = "") String keyword,
                        @RequestParam(defaultValue = "0")int page) {
        if (bindingResult.hasErrors()) return "formEtudiant" ;
        etudiantRepository.save (etudiant);
        return "redirect:/index";///user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping( "/editEtudiant")
    public String editEtudiant (Model model,Long id){
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        if (etudiant==null){
            throw new RuntimeException("Etudiant introuvable");
        }
        model.addAttribute("etudiant", etudiant);
        return "editEtudiant";
    }

}
