/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import GestionUtilisateur.Agence;
import GestionUtilisateur.Client;
import GestionUtilisateur.DemandeCreationEntreprise;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface DemandeCreationEntrepriseFacadeLocal {

    void create(DemandeCreationEntreprise demandeCreationEntreprise);

    void edit(DemandeCreationEntreprise demandeCreationEntreprise);

    void remove(DemandeCreationEntreprise demandeCreationEntreprise);

    DemandeCreationEntreprise find(Object id);

    List<DemandeCreationEntreprise> findAll();

    List<DemandeCreationEntreprise> findRange(int[] range);

    int count();

    List<DemandeCreationEntreprise> rechercheDemandeCreationEntreprise();

    DemandeCreationEntreprise rechercheDemandeCreationEntrepriseSiret(String siret);

    DemandeCreationEntreprise rechercheDemandeCreationEntreprise(long id);

    DemandeCreationEntreprise supprimerDemandeCreationEntreprise(DemandeCreationEntreprise e);

    DemandeCreationEntreprise creerDemandeCreationEntreprise(String nom, String siret, String adresse, Agence a, Client c);

    DemandeCreationEntreprise rechercheDemandeCreationEntrepriseClient(Client client);
    
}
