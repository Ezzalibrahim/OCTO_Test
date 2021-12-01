package ma.octo.assignement.dto;

import ma.octo.assignement.domain.Compte;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

public class VersementDto {
    private BigDecimal montantVirement;
    private String nom_prenom_emetteur;
    private String compteBeneficiaireRib;
    private String motifVersement;

    public BigDecimal getMontantVirement() {
        return montantVirement;
    }

    public String getNom_prenom_emetteur() {
        return nom_prenom_emetteur;
    }

    public String getCompteBeneficiaireRib() {
        return compteBeneficiaireRib;
    }

    public String getMotifVersement() {
        return motifVersement;
    }

    public void setMontantVirement(BigDecimal montantVirement) {
        this.montantVirement = montantVirement;
    }

    public void setNom_prenom_emetteur(String nom_prenom_emetteur) {
        this.nom_prenom_emetteur = nom_prenom_emetteur;
    }

    public void setCompteBeneficiaireRib(String compteBeneficiaireRib) {
        this.compteBeneficiaireRib = compteBeneficiaireRib;
    }

    public void setMotifVersement(String motifVersement) {
        this.motifVersement = motifVersement;
    }
}
