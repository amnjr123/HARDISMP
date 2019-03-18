/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import GestionUtilisateur.Client;
import GestionUtilisateur.DemandeRattachement;
import GestionUtilisateur.Entreprise;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface DemandeRattachementFacadeLocal {

    void create(DemandeRattachement demandeRattachement);

    void edit(DemandeRattachement demandeRattachement);

    void remove(DemandeRattachement demandeRattachement);

    DemandeRattachement find(Object id);

    List<DemandeRattachement> findAll();

    List<DemandeRattachement> findRange(int[] range);

    int count();

    List<DemandeRattachement> rechercherDemandeRattachement(Entreprise e);

    DemandeRattachement rechercherDemandeRattachement(Client c);

    DemandeRattachement rechercherDemandeRattachement(Long id);

    DemandeRattachement supprimerDemandeRattachement(DemandeRattachement d);

    DemandeRattachement creerDemandeRattachement(Client c, Entreprise e);

    List<DemandeRattachement> rechercherDemandeRattachement();

    List<DemandeRattachement> rechercherDemandeRattachementUrgentes();
    
}
