/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionUtilisateur;

import GestionCatalogue.Offre;
import GestionCatalogue.Service;
import GestionUtilisateur.Utilisateur;
import GestionUtilisateur.UtilisateurHardis;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface SessionHardisLocal {

    String getTypeUser(Utilisateur utilisateur);

    UtilisateurHardis modifierCompte(Long id, String mail, String tel, boolean actifInactif);

    Utilisateur modifierUtilisateurMDP(Long id, String ancienMdp, String nouveauMdp);

    List<Service> rechercherService(Offre o);

    List<Offre> rechercherOffres();
    
}
