/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionUtilisateur;

import GestionCatalogue.Offre;
import GestionCatalogue.Service;
import GestionCatalogue.ServiceStandard;
import GestionUtilisateur.Agence;
import GestionUtilisateur.Client;
import GestionUtilisateur.Consultant;
import GestionUtilisateur.DemandeCreationEntreprise;
import GestionUtilisateur.Entreprise;
import GestionUtilisateur.Interlocuteur;
import GestionUtilisateur.PorteurOffre;
import GestionUtilisateur.ReferentLocal;
import GestionUtilisateur.Utilisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pedago
 */
@Local
public interface SessionAdministrateurLocal {

    Agence creerAgence(String localisation);

    Interlocuteur creerInterlocuteur(String nom, String prenom, String telephone, String fonction, long idEntreprise);

    Entreprise creerEntreprise(String nom, String siret, String adresseFacturation, long idAgence);

    Entreprise validerDemandeCreationEntreprise(Long idDemande);

    Interlocuteur modifierInterlocuteur(Long idInterlocuteur, String nom, String prenom, String telephone, String fonction);

    Interlocuteur supprimerInterlocuteur(Long idInterlocuteur);

    List<Interlocuteur> rechercherInterlocuteur(Long idEntreprise);

    Client rattacherClientAdmin(Long idClient, Long idEntreprise);

    Client rattacherClient(Long idClient, Long idEntreprise);

    ReferentLocal creerReferentLocal(String nom, String prenom, String mail, String tel, String mdp, String profilTechnique, float plafondDelegation, Long idOffre, Long idAgence);

    Consultant creerConsultant(String nom, String prenom, String mail, String tel, String mdp, String profilTechnique, float plafondDelegation, Long idAgence, List<Long> listeIdOffres);

    PorteurOffre creerPO(String nom, String prenom, String mail, String tel, String mdp, String profilTechnique, Long idOffre, Long idAgence);

    PorteurOffre modifierPO(Long idPO, String nom, String prenom, String mail, String tel, String profilTechnique, boolean actifInactif, Long idOffre, Long idAgence);

    Consultant modifierConsultant(Long idPO, String nom, String prenom, String mail, String tel, String profilTechnique, boolean actifInactif, float plafondDelegation, Long idAgence, List<Long> listeIdOffres);

    ReferentLocal modifierReferentLocal(Long idReferentLocal, String nom, String prenom, String mail, String tel, String profilTechnique, boolean actifInactif, float plafondDelegation, Long idOffre, Long idAgence);

    Utilisateur modifierUtilisateurMDP(Long id, String ancienMdp, String nouveauMdp);

    Offre creerOffre(String nom);

    Offre modifierOffre(Long idOffre, String nom);

    Offre supprimerOffre(Long idOffre);

    List<Offre> afficherOffres();

    ServiceStandard creerServiceStandard(String nom, String descriptionService, String lieuString, float cout, boolean fraisInclus, String conditions, int delaiRelance, Long idOffre, int nbJoursConsultantSenior, int nbJoursConsultantConfirme, int nbJoursConsultantJunior, int nbHeuresAtelierEntretien, int nbHeuresSupportTel, String descriptionPrestation);

    ServiceStandard modifierServiceStandard(Long idServiceStandard, String nom, String descriptionService, String lieuString, float cout, boolean fraisInclus, String conditions, int delaiRelance, Long idOffre, int nbJoursConsultantSenior, int nbJoursConsultantConfirme, int nbJoursConsultantJunior, int nbHeuresAtelierEntretien, int nbHeuresSupportTel, String descriptionPrestation);

    List<Service> afficherServices();

    DemandeCreationEntreprise refuserDemandeCreationEntreprise(Long idDemande);

    Entreprise entrepriseExistante(String siret);

    List<DemandeCreationEntreprise> rechercheDemandeCreationEntreprise();

    List<Interlocuteur> rechercherInterlocuteur();

    List<Entreprise> rechercherEntreprisePagine(int page);
    
}
