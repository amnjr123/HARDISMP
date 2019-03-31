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
import GestionDevis.Intervention;
import GestionDevis.Proposition;
import GestionUtilisateur.CV;
import GestionUtilisateur.Disponibilite;
import GestionUtilisateur.Utilisateur;
import GestionUtilisateur.UtilisateurHardis;
import java.util.Date;
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

    CV afficherCVOffreUtilisateur(Long idUtilisateurHardis, Long idOffre);

    List<CV> afficherCVUtilisateur(Long idUtilisateurHardis);

    List<CV> afficherCVOffre(Long idOffre);

    List<CV> afficherCV();

    CV supprimerCV(Long idCV);

    CV modifierCV(Long idCV, String chemin);

    CV creerCV(String chemin, Long idUtilisateurHardis);

    CV creerCV(String chemin, Long idUtilisateur, Long idOffre);

    List<Livrable> afficherLivrables(Long idService);

    List<Devis> rechercherDevis(Long idUtilisateurHardis, Long idClient, String statutDevis);

    Proposition creerProposition(Date dateDebutValidite, Date dateFinValidite, String cheminDocument, Long idUtilisateurHardis, Long idDevisNonStandard);

    void transfererDevisNonStandard(Long idDevisNonStandard, Long idUtilisateurHardis);

    Disponibilite supprimerDisponibilite(Long idDispo);

    List<Offre> afficherOffres();

    List<ServiceStandard> afficherServicesStandards(Long idOffre);

    List<ServiceNonStandard> afficherServicesNonStandards(Long idOffre);

    Offre afficheOffre(Long id);

    Disponibilite creerDisponibilite(Long idUtilisateurHardis, Date dateDispo, int i);
    
    CV afficherCVSansOffre(Long idUtilisateurHardis);
    
    CV afficherCv(Long idCV);
    
    DevisNonStandard envoyerDevisNonStandard(Long idDevisNonStandard);

    List<Disponibilite> afficherDisponibilites(Long idUtilisateur);

    List<Intervention> afficherInterventions(Long idUtilisateur);

    Communication creerCommunication(String message, Long idConversation);

    List<Conversation> afficherConversations(Long idClient);

    Conversation afficherConversation(Long idConversation);

    List<Communication> afficherCommunications(Long idConversation);

    Conversation affecterUHConversation(Long idUH, Long idConversation);

    List<Devis> rechercherDevisSaufIncomplets(Long idUH);

    DevisStandard rechercherDevisStandard(Long idDevisNonStandard);

    DevisNonStandard rechercherDevisNonStandard(Long idDevisNonStandard);
   
}
