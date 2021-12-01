package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;

public class VersementMapper {

    public static Versement map(VersementDto  versementDto){
        Versement versement = new Versement();
        versement.setMotifVersement(versementDto.getMotifVersement());
        versement.setMontantVirement(versementDto.getMontantVirement());
        versement.setNom_prenom_emetteur(versementDto.getNom_prenom_emetteur());

        return  versement;
    }
}
