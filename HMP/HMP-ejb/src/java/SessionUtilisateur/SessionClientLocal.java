/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionUtilisateur;

import GestionCatalogue.Offre;
import GestionCatalogue.Service;
import GestionDevis.DevisStandard;
import GestionUtilisateur.Client;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface SessionClientLocal {
    List<Service> rechercherService(Offre o);

    Client modifierClient(Long id, String nom, String prenom, String mail, String tel);

    DevisStandard creerDevisStandard(String commentaireClient, Long idServiceStandard, Long idClient);

    Client modifierClientMDP(Long id, String ancienMdp, String nouveauMdp);
}
