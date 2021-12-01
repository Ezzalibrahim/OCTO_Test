package ma.octo.assignement.service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompteService {

    @Autowired
    private CompteRepository compteRepository;

    public Compte save(Compte compte){
        return compteRepository.save(compte);
    }

    public List<Compte> findAll() {
        return compteRepository.findAll();
    }

    public Optional<Compte> findById(Long id){
        return  compteRepository.findById(id);
    }

    public Compte findByRib(String rib){
        return compteRepository.findByRib(rib);
    }

    public Compte findByNrCompte(String nrCompte){
        return compteRepository.findByNrCompte(nrCompte);
    }


    public Compte compteIsExist(String numCompte , String type) throws CompteNonExistantException {
        Compte compte = findByNrCompte(numCompte);
        if (compte == null){
            throw new CompteNonExistantException("Compte "+ type + " Not Exist");
        }

        return compte;
    }

    public void checkSoldeVirement(int soldeEmtteur , int montantVirement) throws SoldeDisponibleInsuffisantException {
        if (soldeEmtteur - montantVirement < 0){
            throw new SoldeDisponibleInsuffisantException("Solde insuffisant pour l'utilisateur");
        }
    }


}
