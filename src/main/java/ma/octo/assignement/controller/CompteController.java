package ma.octo.assignement.controller;


import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    @Autowired
    private CompteService compteService;


    @GetMapping
    List<Compte> loadAllCompte() {
        return compteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compte> findById(@PathVariable Long id){
        Optional<Compte> compte =  compteService.findById(id);
        return new ResponseEntity<>(compte.get() , HttpStatus.OK);
    }
}
