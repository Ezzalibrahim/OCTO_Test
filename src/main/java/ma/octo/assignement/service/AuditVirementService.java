package ma.octo.assignement.service;

import ma.octo.assignement.domain.AuditVersement;
import ma.octo.assignement.domain.AuditVirement;
import ma.octo.assignement.domain.util.EventType;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.repository.AuditVirementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuditVirementService {

    Logger LOGGER = LoggerFactory.getLogger(AuditVirementService.class);

    @Autowired
    private AuditVirementRepository auditVirementRepository;



    public void auditVirement(VirementDto virementDto) {

        String message =  "Virement depuis " + virementDto.getNrCompteEmetteur() + " vers " + virementDto.getNrCompteBeneficiaire() + " d'un montant de " + virementDto.getMontantVirement().toString();
        LOGGER.info("Audit de l'événement {}", EventType.VIREMENT);

        AuditVirement audit = new AuditVirement();
        audit.setEventType(EventType.VIREMENT);
        audit.setMessage(message);
        auditVirementRepository.save(audit);
    }



}
