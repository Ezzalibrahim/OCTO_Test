package ma.octo.assignement.service;

import ma.octo.assignement.domain.AuditVersement;
import ma.octo.assignement.domain.util.EventType;
import ma.octo.assignement.repository.AuditVersementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditVersementService {

    @Autowired
    private AuditVersementRepository auditVersementRepository;
    Logger LOGGER = LoggerFactory.getLogger(AuditVirementService.class);


    public void auditVersement(String message) {

        LOGGER.info("Audit de l'événement {}", EventType.VERSEMENT);

        AuditVersement auditVersement = new AuditVersement();
        auditVersement.setEventType(EventType.VERSEMENT);
        auditVersement.setMessage(message);
        auditVersementRepository.save(auditVersement);
    }
}
