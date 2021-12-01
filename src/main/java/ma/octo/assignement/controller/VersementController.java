package ma.octo.assignement.controller;

import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.VersementRepository;
import ma.octo.assignement.service.VersementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController(value = "/api/versements")
@RequestMapping("/api/versements")
public class VersementController {

    @Autowired
    private VersementService versementService;

    @GetMapping
    public ResponseEntity<List<Versement>> getAllVersement(){
        List allVersement = versementService.findAll();
        if (allVersement.size() == 0){

        }

        return new ResponseEntity<>(allVersement , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Versement> findById(@PathVariable Long id){
        Optional<Versement> versement =  versementService.findById(id);
        System.out.println(versement);

        return new ResponseEntity<>(versement.get() , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Versement> addVersement(@RequestBody VersementDto versementDto){
        try {
            versementService.validateVersementDto(versementDto);
            Versement versement = versementService.save(versementService.createVersementFromDto(versementDto));
            return new ResponseEntity<>(versement, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
