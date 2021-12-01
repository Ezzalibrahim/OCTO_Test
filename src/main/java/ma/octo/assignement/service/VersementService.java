package ma.octo.assignement.service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.mapper.VersementMapper;
import ma.octo.assignement.repository.VersementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class VersementService {

    @Autowired
    private VersementRepository versementRepository;

    @Autowired
    private CompteService compteService;

    public List<Versement> findAll() {
        return versementRepository.findAll();
    }

    public Optional<Versement> findById(Long id){
        return  versementRepository.findById(id);
    }

    public Versement save(Versement versement){
        return versementRepository.save(versement);
    }

    public Versement createVersementFromDto(VersementDto versementDto){
        Versement versement = VersementMapper.map(versementDto);
        Compte compteBeneficiaire =  compteService.findByRib(versementDto.getCompteBeneficiaireRib());
        BigDecimal newSolde = new BigDecimal(compteBeneficiaire.getSolde().intValue() + versementDto.getMontantVirement().intValue());
        compteBeneficiaire.setSolde(newSolde);
        compteService.save(compteBeneficiaire);
        versement.setCompteBeneficiaire(compteBeneficiaire);

        return  versement;
    }


    public void validateVersementDto(VersementDto versementDto) throws TransactionException, CompteNonExistantException {
        boolean nomIsValid =  checkInputString(versementDto.getNom_prenom_emetteur());
        boolean motifIsValid =  checkInputString(versementDto.getMotifVersement());
        boolean ribIsValid =  checkRib(versementDto.getCompteBeneficiaireRib());
        boolean montantIsValid = checkMontant(versementDto.getMontantVirement());
        Compte compte = compteService.findByRib(versementDto.getCompteBeneficiaireRib());
        if (compte == null)
            throw new CompteNonExistantException("the Account With Rib " +versementDto.getCompteBeneficiaireRib()+" Not Exist");

        if (!(nomIsValid && montantIsValid && ribIsValid && motifIsValid) == true)
            throw new TransactionException("Invalid Info Check the request body");

    }

    private boolean checkInputString(String input) {
        return !(input == null || input.trim().length() == 0);
    }

    private boolean checkRib(String rib){
        if (rib.length() == 28)
            return true;
        return  false;
    }
    private boolean checkMontant(BigDecimal montant){
        return montant.intValue() > 10;
    }
}
