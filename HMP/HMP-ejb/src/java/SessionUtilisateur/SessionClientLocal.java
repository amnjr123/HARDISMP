/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionUtilisateur;

import GestionCatalogue.Livrable;
import GestionCatalogue.Offre;
import GestionCatalogue.Service;
import GestionDevis.Devis;
import GestionDevis.DevisNonStandard;
import GestionDevis.DevisStandard;
import GestionUtilisateur.Agence;
import GestionUtilisateur.Client;
import GestionUtilisateur.DemandeCreationEntreprise;
import GestionUtilisateur.DemandeRattachement;
import GestionUtilisateur.Entreprise;
import GestionUtilisateur.Interlocuteur;
import GestionUtilisateur.Utilisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5151882
 */
@Local
public interface SessionClientLocal {

    Client modifierClient(Long id, String nom, String prenom, String mail, String tel);

    DevisStandard creerDevisStandard(String commentaireClient, Long idServiceStandard, Long idClient);

    Utilisateur modifierClientMDP(Long id, String ancienMdp, String nouveauMdp);

    DemandeCreationEntreprise creerDemandeEntreprise(Long idClient, String nom, String siret, String adresse, Long idAgence);

    List<Agence> rechercherAgence();

    DemandeCreationEntreprise rechercherDemandeCreationEntreprise(Long idClient);

    List<DemandeRattachement> rechercherDemandeRattachementEntreprise(Long idEntreprise);

    DemandeRattachement rechercherDemandeRattachementClient(Long idClient);

    DemandeRattachement creerDemandeRattachement(Long idClient, String siret);

    DemandeRattachement validerDemandeRattachement(Long idDemande);

    DemandeRattachement refuserDemandeRattachement(Long idDemande);

    Interlocuteur creerInterlocuteur(String nom, String prenom, String telephone, String fonction, long idEntreprise);

    Interlocuteur supprimerInterlocuteur(Long idInterlocuteur);

    Interlocuteur modifierInterlocuteur(Long idInterlocuteur, String nom, String prenom, String telephone, String fonction);

    List<Interlocuteur> rechercherInterlocuteur(Long idEntreprise);

    Client rechercheClient(long id);

    List<Service> rechercherServices(Offre o);

    List<Livrable> afficherLivrables(Long idService);

    List<Offre> rechercherOffres();

    DevisNonStandard creerDevisNonStandard(String commentaireClient, Long idServiceNonStandard, Long idClient);

    List<Devis> rechercherDevis(Long idClient, String statutDevis);
}
