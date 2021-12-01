package ma.octo.assignement.service;


import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.mapper.VirementMapper;
import ma.octo.assignement.repository.VirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class VirementService {

    public static final int MONTANT_MAXIMAL = 10000;

    @Autowired
    private VirementRepository virementRepository;
    @Autowired
    private  CompteService compteService;
    @Autowired
    private AuditVirementService autiService;

    public Virement save(Virement virement){
        return virementRepository.save(virement);
    }

    public List<Virement> findAll() {
        return virementRepository.findAll();
    }

    public Optional<Virement> findById(Long id){
        return  virementRepository.findById(id);
    }



    public Virement createTransaction(VirementDto virementDto)
            throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {

        Compte compteEmetteur = compteService.compteIsExist(virementDto.getNrCompteEmetteur(), "Emetteur");
        Compte compteBeneficiaire = compteService.compteIsExist(virementDto.getNrCompteBeneficiaire(), "Beneficiaire");
        checkMontant(virementDto.getMontantVirement());
        checkMotif(virementDto.getMotif());
        compteService.checkSoldeVirement(compteEmetteur.getSolde().intValue() , virementDto.getMontantVirement().intValue());

        compteEmetteur.setSolde(compteEmetteur.getSolde().subtract(virementDto.getMontantVirement()));
        compteService.save(compteEmetteur);

        compteBeneficiaire.setSolde(new BigDecimal(compteBeneficiaire.getSolde().intValue() + virementDto.getMontantVirement().intValue()));
        compteService.save(compteBeneficiaire);

        Virement virement = VirementMapper.map(virementDto);
        virement.setCompteBeneficiaire(compteBeneficiaire);
        virement.setCompteEmetteur(compteEmetteur);


        autiService.auditVirement(virementDto);

        return save(virement);
    }




    public void checkMontant(BigDecimal virementDto) throws TransactionException {
        if (virementDto == null) {
            System.out.println("Montant vide");
            throw new TransactionException("Montant vide");
        } else if (virementDto.intValue() == 0) {
            System.out.println("Montant vide");
            throw new TransactionException("Montant vide");
        } else if (virementDto.intValue() < 10) {
            System.out.println("Montant minimal de virement non atteint");
            throw new TransactionException("Montant minimal de virement non atteint");
        } else if (virementDto.intValue() > MONTANT_MAXIMAL) {
            System.out.println("Montant maximal de virement dépassé");
            throw new TransactionException("Montant maximal de virement dépassé");
        }

    }

    public  void checkMotif(String motif) throws TransactionException {
        if (motif.length() < 0) {
            System.out.println("Motif vide");
            throw new TransactionException("Motif vide");
        }
    }

}
