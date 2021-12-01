package ma.octo.assignement.controller;

import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.service.VirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController(value = "/api/virements")
@RequestMapping("/api/virements")
public class VirementController {

    @Autowired
    private VirementService virementService;

    @GetMapping
    List<Virement> loadAll() {
        List<Virement> all = virementService.findAll();

        if (CollectionUtils.isEmpty(all)) {
            return null;
        } else {
            return all;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Virement> findById(@PathVariable Long id){
        Optional<Virement> virement =  virementService.findById(id);

        return new ResponseEntity<>(virement.get() , HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Virement> createTransactionVirement(@RequestBody VirementDto virementDto)
            throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {

        Virement virement = virementService.createTransaction(virementDto);
        return new ResponseEntity<>(virement,HttpStatus.OK);
    }

}
