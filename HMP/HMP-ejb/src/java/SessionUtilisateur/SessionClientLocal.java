/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionUtilisateur;

import GestionCatalogue.Livrable;
import GestionCatalogue.Offre;
import GestionCatalogue.Service;
import GestionCatalogue.ServiceNonStandard;
import GestionCatalogue.ServiceStandard;
import GestionDevis.Communication;
import GestionDevis.Conversation;
import GestionDevis.Devis;
import GestionDevis.DevisNonStandard;
import GestionDevis.DevisStandard;
import GestionUtilisateur.Agence;
import GestionUtilisateur.Client;
import GestionUtilisateur.Consultant;
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

    Utilisateur modifierClientMDP(Long id, String ancienMdp, String nouveauMdp);

    DemandeCreationEntreprise creerDemandeEntreprise(Long idClient, String nom, String siret, String adresse, Long idAgence);

    List<Agence> rechercherAgence();

    DemandeCreationEntreprise rechercherDemandeCreationEntreprise(Long idClient);

    List<DemandeRattachement> rechercherDemandeRattachementEntreprise(Long idEntreprise);

    DemandeRattachement rechercherDemandeRattachementClient(Long idClient);

    DemandeRattachement creerDemandeRattachement(Long idClient, String siret);

    DemandeRattachement validerDemandeRattachement(Long idDemande);

    DemandeRattachement refuserDemandeRattachement(Long idDemande);

    Interlocuteur supprimerInterlocuteur(Long idInterlocuteur);

    Interlocuteur modifierInterlocuteur(Long idInterlocuteur, String nom, String prenom,String mail, String telephone, String fonction);

    List<Interlocuteur> rechercherInterlocuteur(Long idEntreprise);

    Client rechercheClient(long id);

    List<Livrable> afficherLivrables(Long idService);

    List<Devis> rechercherDevis(Long idClient, String statutDevis);

    String modifierDevisIncomplet(Long idDevis, String commentaireClient);

    String DemandeCreationOuRattachement(Long idClient, String nom, String siret, String adresse, Long idAgence);

    Offre rechercherOffre(Long idOffre);

    List<ServiceStandard> rechercherServicesStandards(Long idOffre);

    List<ServiceNonStandard> rechercherServicesNonStandards(Long idOffre);

    ServiceStandard rechercherServiceStandard(Long idService);

    ServiceNonStandard rechercherServiceNonStandard(Long idService);

    Interlocuteur creerInterlocuteur(String nom, String prenom, String telephone, String mail, String fonction, long idEntreprise);

    DevisNonStandard creerDevisNonStandard(Long idServiceNonStandard, Long idClient);

    DevisStandard creerDevisStandard(Long idServiceStandard, Long idClient);

    List<Offre> rechercherOffresClient();

    Devis afficherLeDevis(long id);

    Conversation creerConversation(Long idClient);

    List<Conversation> afficherConversations(Long idClient);

    Communication creerCommunication(String message, Long idConversation);

    List<Communication> afficherCommunications(Long idConversation);

    Conversation afficherConversation(Long idConversation);

    void supprimerDevisStandardIncomplet(Long idDevis);

    void supprimerDevisNonStandardIncomplet(Long idDevis);

    List<Consultant> listConsultant(Long idAgence);
}
