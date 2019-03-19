/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionUtilisateur;

import Enum.Helpers;
import Enum.LieuIntervention;
import Enum.ProfilTechnique;
import Enum.StatutDevis;
import FacadeCatalogue.LivrableFacadeLocal;
import FacadeCatalogue.OffreFacadeLocal;
import FacadeCatalogue.ServiceFacadeLocal;
import FacadeCatalogue.ServiceNonStandardFacadeLocal;
import FacadeCatalogue.ServiceStandardFacadeLocal;
import FacadeDevis.DevisFacadeLocal;
import FacadeUtilisateur.AgenceFacadeLocal;
import FacadeUtilisateur.CVFacadeLocal;
import FacadeUtilisateur.ClientFacadeLocal;
import FacadeUtilisateur.ConsultantFacadeLocal;
import FacadeUtilisateur.DemandeCreationEntrepriseFacadeLocal;
import FacadeUtilisateur.DemandeRattachementFacadeLocal;
import FacadeUtilisateur.DisponibiliteFacadeLocal;
import FacadeUtilisateur.EntrepriseFacadeLocal;
import FacadeUtilisateur.InterlocuteurFacadeLocal;
import FacadeUtilisateur.PorteurOffreFacadeLocal;
import FacadeUtilisateur.ReferentLocalFacadeLocal;
import FacadeUtilisateur.UtilisateurFacadeLocal;
import FacadeUtilisateur.UtilisateurHardisFacadeLocal;
import GestionCatalogue.Livrable;
import GestionCatalogue.Offre;
import GestionCatalogue.Service;
import GestionCatalogue.ServiceNonStandard;
import GestionCatalogue.ServiceStandard;
import GestionDevis.Devis;
import GestionUtilisateur.Agence;
import GestionUtilisateur.CV;
import GestionUtilisateur.Client;
import GestionUtilisateur.Consultant;
import GestionUtilisateur.DemandeCreationEntreprise;
import GestionUtilisateur.DemandeRattachement;
import GestionUtilisateur.Entreprise;
import GestionUtilisateur.Interlocuteur;
import GestionUtilisateur.PorteurOffre;
import GestionUtilisateur.ReferentLocal;
import GestionUtilisateur.Utilisateur;
import GestionUtilisateur.UtilisateurHardis;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.jasper.tagplugins.jstl.ForEach;

/**
 *
 * @author pedago
 */
@Stateless
public class SessionAdministrateur implements SessionAdministrateurLocal {

    @EJB
    private DevisFacadeLocal devisFacade;

    @EJB
    private LivrableFacadeLocal livrableFacade;

    @EJB
    private ServiceNonStandardFacadeLocal serviceNonStandardFacade;

    @EJB
    private UtilisateurHardisFacadeLocal utilisateurHardisFacade;

    @EJB
    private ServiceFacadeLocal serviceFacade;

    @EJB
    private ServiceStandardFacadeLocal serviceStandardFacade;

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

    @EJB
    private DemandeRattachementFacadeLocal demandeRattachementFacade;

    @EJB
    private DemandeCreationEntrepriseFacadeLocal demandeCreationEntrepriseFacade;

    @EJB
    private ReferentLocalFacadeLocal referentLocalFacade;

    @EJB
    private PorteurOffreFacadeLocal porteurOffreFacade;

    @EJB
    private OffreFacadeLocal offreFacade;

    @EJB
    private InterlocuteurFacadeLocal interlocuteurFacade;

    @EJB
    private EntrepriseFacadeLocal entrepriseFacade;

    @EJB
    private DisponibiliteFacadeLocal disponibiliteFacade;

    @EJB
    private ConsultantFacadeLocal consultantFacade;

    @EJB
    private ClientFacadeLocal clientFacade;

    @EJB
    private CVFacadeLocal cVFacade;

    @EJB
    private AgenceFacadeLocal agenceFacade;

/*GESTION DES AGENCES*/
    
    @Override
    public Agence creerAgence(String localisation, String adresse) {
        return agenceFacade.creerAgence(localisation, adresse);
    }
    
    @Override
    public List<Agence> afficherAgences(){
        return agenceFacade.rechercheAgences();
    }
    
    @Override
    public Agence modifierAgence(Long idAgence, String localisation, String adresse){
        Agence a = agenceFacade.rechercheAgence(idAgence);
        return agenceFacade.modifierAgence(a, localisation, adresse);
    }

/*GESTION ENTREPRISE*/
    
    @Override
    public List<DemandeCreationEntreprise> rechercheDemandeCreationEntreprise(){
        return demandeCreationEntrepriseFacade.rechercheDemandeCreationEntreprise();
    }
    
    @Override
    public List rechercheEntreprise(){
        return entrepriseFacade.rechercheEntreprise();
    }
    
    @Override
    public ArrayList paginer(int page,int nbreItems, List liste){
        int nblignes=liste.size();
        int nbPages=1;
        int modulo = nblignes%nbreItems;
        if (modulo!=0){
            nbPages = ((nblignes-modulo)/nbreItems)+1;
        } else {
            nbPages = nblignes/nbreItems;
        }
        if (page>nbPages-1 || page<0){
            page = 0;
        }
        
        ArrayList lr = new ArrayList();
        for(int i=page*nbreItems; i<=(page*nbreItems+nbreItems); i++){
            try{
              lr.add(liste.get(i));
              System.out.print(i);
            } catch (Exception e) {
              System.out.print(i+" out of bounds");
            }
        }
        return lr;
    }
   
    @Override
    public Entreprise entrepriseExistante(String siret){
        return entrepriseFacade.rechercheEntrepriseSiret(siret);
    }
    
    /*public List<DemandeRattachement> afficherDemandesRattachement;*/
    
    @Override
    public Entreprise validerDemandeCreationEntreprise(Long idDemande) {
        DemandeCreationEntreprise d = demandeCreationEntrepriseFacade.rechercheDemandeCreationEntreprise(idDemande);
        //Vérification si entreprise déjà existante
        Entreprise e = entrepriseFacade.rechercheEntrepriseSiret(d.getSiret());
        if(e==null){
            // Si non alors on la crée
            e = entrepriseFacade.creerEntreprise(d.getNom(), d.getSiret(), d.getAdresseFacturation(), d.getAgence());
            //On affecte l'entreprise au client
            clientFacade.affecterEntreprise(d.getClient(), e);
            //Le client devient client Admin de l'enteprise
            clientFacade.modifierAdmin(d.getClient());
            //On supprime la demande de création
            demandeCreationEntrepriseFacade.supprimerDemandeCreationEntreprise(d);
        }
        else{
            //Si l'entreprise existe alors on transforme en demande de rattachement qui sera envoyée à l'admin Client
            DemandeRattachement dr = demandeRattachementFacade.creerDemandeRattachement(d.getClient(),e);
            //On supprime la demande de création
            demandeCreationEntrepriseFacade.supprimerDemandeCreationEntreprise(d);
        }
        return e;
    }
    
    @Override
    public DemandeCreationEntreprise refuserDemandeCreationEntreprise(Long idDemande) {
        DemandeCreationEntreprise d = demandeCreationEntrepriseFacade.rechercheDemandeCreationEntreprise(idDemande);
        return demandeCreationEntrepriseFacade.supprimerDemandeCreationEntreprise(d);
    }
    
    @Override
    public Entreprise creerEntreprise(String nom, String siret, String adresseFacturation, long idAgence) {
        //Méthode pour que l'admin crée manuellement une enteprise
        return entrepriseFacade.creerEntreprise(nom, siret, adresseFacturation, agenceFacade.rechercheAgence(idAgence));
    }
    
    @Override
    public Client rattacherClientAdmin(Long idClient, Long idEntreprise) {
        //Méthode pour que l'admin rattache manuellement le Client Admin à une entreprise
        Client c = clientFacade.rechercheClient(idClient);
        Entreprise e = entrepriseFacade.rechercheEntreprise(idEntreprise);
        c = clientFacade.affecterEntreprise(c,e);
        clientFacade.modifierAdmin(c);
        return c;
    }
    
    @Override
    public Client rattacherClient(Long idClient, Long idEntreprise) {
        //Méthode pour que l'admin rattache manuellement les clients à une entreprise
        Client c = clientFacade.rechercheClient(idClient);
        Entreprise e = entrepriseFacade.rechercheEntreprise(idEntreprise);
        c = clientFacade.affecterEntreprise(c,e);
        return c;
    }
    
    @Override
    public List<Interlocuteur> rechercherInterlocuteur(){
        return interlocuteurFacade.rechercheInterlocuteur();
    }
    
    @Override
    public List<Interlocuteur> rechercherInterlocuteur(Long idEntreprise){
        Entreprise e = entrepriseFacade.rechercheEntreprise(idEntreprise);
        return interlocuteurFacade.rechercheInterlocuteur(e);
    }
    
    @Override
    public Interlocuteur creerInterlocuteur(String nom, String prenom, String telephone, String fonction, long idEntreprise) {
        return interlocuteurFacade.creerInterlocuteur(nom, prenom, nom, telephone, fonction, entrepriseFacade.rechercheEntreprise(idEntreprise));
    }
    
    @Override
    public Interlocuteur modifierInterlocuteur(Long idInterlocuteur, String nom, String prenom, String telephone, String fonction) {
        Interlocuteur i = interlocuteurFacade.rechercheInterlocuteur(idInterlocuteur);
        return interlocuteurFacade.modifierInterlocuteur(i,nom, prenom, nom, telephone, fonction);
    }
    
    @Override
    public Interlocuteur supprimerInterlocuteur(Long idInterlocuteur) {
        Interlocuteur i = interlocuteurFacade.rechercheInterlocuteur(idInterlocuteur);
        return interlocuteurFacade.supprimerInterlocuteur(i);
    }
    
/*GESTION DES COMPTES UTILISATEUR HARDIS*/
    
    @Override
    public PorteurOffre creerPO(String nom, String prenom, String mail, String tel, String mdp, String profilTechnique, Long idOffre, Long idAgence){
        Agence a = agenceFacade.rechercheAgence(idAgence);
        Offre o = offreFacade.rechercheOffre(idOffre);
        ProfilTechnique profil = ProfilTechnique.valueOf(profilTechnique);
        PorteurOffre po = porteurOffreFacade.creerPorteurOffre(nom, prenom, mail, tel, mdp, profil, o, a);
        return po;
    }
    
    @Override
    public Consultant creerConsultant(String nom, String prenom, String mail, String tel, String mdp, String profilTechnique,float plafondDelegation, Long idAgence, List<Long> listeIdOffres){
        Agence a = agenceFacade.rechercheAgence(idAgence);
        ProfilTechnique profil = ProfilTechnique.valueOf(profilTechnique);
        Consultant c = null;
        //On vérifie que la liste d'offres n'est pas vide
        if(!listeIdOffres.isEmpty()){
            int i = 0;
            List<Offre> listeOffres = new ArrayList<Offre>();
            for(i=0;i>=listeIdOffres.size();i++){
                Long id = listeIdOffres.get(i);
                Offre o = offreFacade.rechercheOffre(id);
                if(o!=null){
                   listeOffres.add(o);
                }
            }
            //On vérifie que le plafond n'est pas négatif
            if(plafondDelegation>=0){
                c = consultantFacade.creerConsultant(nom, prenom, mail, tel, mdp, profil, plafondDelegation, a, listeOffres);
            }
        }
        return c;
    }
    
    @Override
    public ReferentLocal creerReferentLocal(String nom, String prenom, String mail, String tel, String mdp, String profilTechnique,float plafondDelegation, Long idOffre, Long idAgence){
        Agence a = agenceFacade.rechercheAgence(idAgence);
        Offre o = offreFacade.rechercheOffre(idOffre);
        ProfilTechnique profil = ProfilTechnique.valueOf(profilTechnique);
        ReferentLocal rl = null;
        //On vérifie que le plafond est > à 0
            if(plafondDelegation>0){
                rl = referentLocalFacade.creerReferentLocal(nom, prenom, mail, tel, mdp, profil,plafondDelegation, o, a);
            }
        return rl;
    }
    
    @Override
    public PorteurOffre modifierPO(Long idPO, String nom, String prenom, String mail, String tel, String profilTechnique, boolean actifInactif, Long idOffre, Long idAgence){
        Agence a = agenceFacade.rechercheAgence(idAgence);
        Offre o = offreFacade.rechercheOffre(idOffre);
        PorteurOffre po = porteurOffreFacade.recherchePorteurOffre(idPO);
        ProfilTechnique profil = ProfilTechnique.valueOf(profilTechnique);
        if (po.getMail().equalsIgnoreCase(mail)) {
            //Si le mail n'a pas changé alors on peut modifier
            return porteurOffreFacade.modifierPorteurOffre(po,nom, prenom, mail, tel, profil, actifInactif, o, a);
        } else {
            //Si le mail a changé alors on vérifie qu'il n'est pas déjà utilisé
            Utilisateur u = utilisateurFacade.rechercherUtilisateurParMail(mail);
            if (u == null) {
                //Si pas utilisé alors on peut modifier
                return porteurOffreFacade.modifierPorteurOffre(po,nom, prenom, mail, tel, profil, actifInactif, o, a);
            } else {
                //Si déjà utilisé alors on renvoie null pour message erreur
                return null;
            }
        }
    }
    
    @Override
    public Consultant modifierConsultant(Long idConsultant, String nom, String prenom, String mail, String tel, String profilTechnique,boolean actifInactif, float plafondDelegation, Long idAgence, List<Long> listeIdOffres){
        Agence a = agenceFacade.rechercheAgence(idAgence);
        Consultant c = consultantFacade.rechercheConsultant(idConsultant);
        ProfilTechnique profil = ProfilTechnique.valueOf(profilTechnique);
        //On vérifie que la liste d'offres n'est pas vide
        if(!listeIdOffres.isEmpty()){
            int i = 0;
            List<Offre> listeOffres = new ArrayList<Offre>();
            for(i=0;i>=listeIdOffres.size();i++){
                Long id = listeIdOffres.get(i);
                Offre o = offreFacade.rechercheOffre(id);
                if(o!=null){
                   listeOffres.add(o);
                }
            }
            //On vérifie que le plafond n'est pas négatif
            if(plafondDelegation>=0){
                if (c.getMail().equalsIgnoreCase(mail)) {
                    //Si le mail n'a pas changé alors on peut modifier
                    return consultantFacade.modifierConsultant(c,nom, prenom, mail, tel, profil, actifInactif,plafondDelegation, listeOffres, a);
                } else {
                    //Si le mail a changé alors on vérifie qu'il n'est pas déjà utilisé
                    Utilisateur u = utilisateurFacade.rechercherUtilisateurParMail(mail);
                    if (u == null) {
                        //Si pas utilisé alors on peut modifier
                        return consultantFacade.modifierConsultant(c,nom, prenom, mail, tel, profil, actifInactif,plafondDelegation, listeOffres, a);
                    } else {
                        //Si déjà utilisé alors on renvoie null pour message erreur
                        return null;
                    }
                }
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }
    
    @Override
    public ReferentLocal modifierReferentLocal(Long idReferentLocal, String nom, String prenom, String mail, String tel, String profilTechnique, boolean actifInactif,float plafondDelegation, Long idOffre, Long idAgence){
        Agence a = agenceFacade.rechercheAgence(idAgence);
        Offre o = offreFacade.rechercheOffre(idOffre);
        ReferentLocal rl = referentLocalFacade.rechercheReferentLocal(idReferentLocal);
        ProfilTechnique profil = ProfilTechnique.valueOf(profilTechnique);
        //On vérifie que le plafond est > à 0
        if(plafondDelegation>0){
            if (rl.getMail().equalsIgnoreCase(mail)) {
                //Si le mail n'a pas changé alors on peut modifier
                return referentLocalFacade.modifierReferentLocal(rl,nom, prenom, mail, tel, profil, actifInactif,plafondDelegation, o, a);
            } else {
                //Si le mail a changé alors on vérifie qu'il n'est pas déjà utilisé
                Utilisateur u = utilisateurFacade.rechercherUtilisateurParMail(mail);
                if (u == null) {
                    //Si pas utilisé alors on peut modifier
                    return referentLocalFacade.modifierReferentLocal(rl,nom, prenom, mail, tel, profil, actifInactif,plafondDelegation, o, a);
                } else {
                    //Si déjà utilisé alors on renvoie null pour message erreur
                    return null;
                }
            }
        }
        else{
            return null;
        }
    }
    
    @Override
    public Utilisateur modifierUtilisateurMDP(Long id, String ancienMdp, String nouveauMdp) {
        Utilisateur u = utilisateurFacade.rechercheUtilisateur(id);
        Utilisateur retour = null;
        try {
            //Vérification si ancien mdp correct
            if (u.getMdp().equals(Helpers.sha1(ancienMdp))) {
                //Si correct alors on peut modifier
                retour = utilisateurFacade.modifierUtilisateurMDP(u, nouveauMdp);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SessionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;
    }
    
/*GESTION DES CV*/
    
    @Override
    public CV creerCV(String chemin, Long idUtilisateur, Long idOffre){
        UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateur);
        Offre o = offreFacade.rechercheOffre(idOffre);
        return cVFacade.creerCV(chemin, uh, o);
    }
    
    @Override
    public CV creerCV(String chemin, Long idUtilisateurHardis){
        UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
        return cVFacade.creerCV(chemin, uh);
    }
    
    @Override
    public CV modifierCV(Long idCV, String chemin){
        CV cv = cVFacade.rechercheCV(idCV);
        return cVFacade.modifierCV(cv, chemin);
    }
    
    @Override
    public CV supprimerCV(Long idCV){
        CV cv = cVFacade.rechercheCV(idCV);
        return cVFacade.supprimerCV(cv);
    }
    
    @Override
    public List<CV> afficherCV(){
        return cVFacade.rechercheCV();
    }
    
    @Override
    public List<CV> afficherCVOffre(Long idOffre){
        Offre o = offreFacade.rechercheOffre(idOffre);
        return cVFacade.rechercherCV(o);
    }
    
    @Override
    public List<CV> afficherCVUtilisateur(Long idUtilisateurHardis){
        UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
        return cVFacade.rechercherCV(uh);
    }
    
    @Override
    public CV afficherCVOffreUtilisateur(Long idUtilisateurHardis, Long idOffre){
        UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
        Offre o = offreFacade.rechercheOffre(idOffre);
        return cVFacade.rechercherCV(o, uh);
    }
    
/*GESTION DU CATALOGUE*/
    
    @Override
    public Offre creerOffre(String nom){
        return offreFacade.creerOffre(nom);
    }
    
    @Override
    public Offre modifierOffre(Long idOffre, String nom){
        return offreFacade.modifierOffre(offreFacade.rechercheOffre(idOffre),nom);
    }
    
    @Override
    public Offre supprimerOffre(Long idOffre){
        return offreFacade.supprimerOffre(offreFacade.rechercheOffre(idOffre));
    }
    
    @Override
    public List<Offre> afficherOffres(){
        return offreFacade.rechercheOffre();
    }
    
    @Override
    public ServiceStandard creerServiceStandard(String nom, String descriptionService, String lieuString, float cout, boolean fraisInclus, String conditions, int delaiRelance, Long idOffre,  int nbJoursConsultantSenior, int nbJoursConsultantConfirme, int nbJoursConsultantJunior, int nbHeuresAtelierEntretien, int nbHeuresSupportTel, String descriptionPrestation){
        LieuIntervention lieu = LieuIntervention.valueOf(lieuString);
        Offre offre = offreFacade.rechercheOffre(idOffre);
        return serviceStandardFacade.creerServiceStandard(nom, descriptionService, lieu, cout, fraisInclus, conditions, delaiRelance, offre, nbJoursConsultantSenior, nbJoursConsultantConfirme, nbJoursConsultantJunior, nbHeuresAtelierEntretien, nbHeuresSupportTel, descriptionPrestation);
    }
    
    @Override
    public ServiceStandard modifierServiceStandard(Long idServiceStandard, String nom, String descriptionService, String lieuString, float cout, boolean fraisInclus, String conditions, int delaiRelance, Long idOffre,  int nbJoursConsultantSenior, int nbJoursConsultantConfirme, int nbJoursConsultantJunior, int nbHeuresAtelierEntretien, int nbHeuresSupportTel, String descriptionPrestation){
        LieuIntervention lieu = LieuIntervention.valueOf(lieuString);
        Offre offre = offreFacade.rechercheOffre(idOffre);
        ServiceStandard ancienService = serviceStandardFacade.rechercheServiceStandard(idServiceStandard);
        return serviceStandardFacade.modifierServiceStandard(ancienService, nom, descriptionService, lieu, cout, fraisInclus, conditions, delaiRelance, offre, nbJoursConsultantSenior, nbJoursConsultantConfirme, nbJoursConsultantJunior, nbHeuresAtelierEntretien, nbHeuresSupportTel, descriptionPrestation);
    }
    
    @Override
    public List<Service> afficherServices(){
        return serviceFacade.rechercherService();
    } 
    
    public ServiceNonStandard creerServiceNonStandard(String nom, String descriptionService, String lieuString, float cout, boolean fraisInclus, String conditions, int delaiRelance, Long idOffre){
        LieuIntervention lieu = LieuIntervention.valueOf(lieuString);
        Offre offre = offreFacade.rechercheOffre(idOffre);
        return serviceNonStandardFacade.creerServiceNonStandard(nom, descriptionService, lieu, cout, fraisInclus, conditions, delaiRelance, offre);
    }
    
    @Override
    public ServiceNonStandard modifierServiceNonStandard(Long idServiceNonStandard, String nom, String descriptionService, String lieuString, float cout, boolean fraisInclus, String conditions, int delaiRelance, Long idOffre){
        LieuIntervention lieu = LieuIntervention.valueOf(lieuString);
        Offre offre = offreFacade.rechercheOffre(idOffre);
        ServiceNonStandard ancienService = serviceNonStandardFacade.rechercheServiceNonStandard(idServiceNonStandard);
        return serviceNonStandardFacade.modifierServiceNonStandard(ancienService,nom, descriptionService, lieu, cout, fraisInclus, conditions, delaiRelance, offre);
    }
    
    @Override
    public Livrable creerLivrable(String libelle, Long idService){
        Service service = serviceFacade.rechercherService(idService);
        return livrableFacade.creerLivrable(libelle, service);
    }
    
    @Override
    public Livrable modifierLivrable(Long idLivrable, String libelle){
        Livrable livrable = livrableFacade.rechercheLivrable(idLivrable);
        return livrableFacade.modifierLivrable(livrable, libelle);
    }
    
    @Override
    public Livrable supprimerLivrable(Long idLivrable){
        Livrable livrable = livrableFacade.rechercheLivrable(idLivrable);
        return livrableFacade.supprimerLivrable(livrable);
    }
    
    @Override
    public List<Livrable> afficherLivrables(Long idService){
        Service service = serviceFacade.rechercherService(idService);
        return livrableFacade.rechercheLivrable(service);
    }
    
/*GESTION DES DEVIS*/
    
    @Override
    public List<Devis> rechercherDevis(Long idUtilisateurHardis, Long idClient, String statutDevis){
        //A TESTER
        //Une seule méthode de recherche
        //Envoyer null pour les paramètres non utilisés pour votre recherche
        if(idUtilisateurHardis==null && idClient==null && statutDevis==null){
            //Afficher tous les devis
            return devisFacade.rechercherDevis();
        }
        else if(idUtilisateurHardis!=null && idClient==null && statutDevis==null){
            //Afficher tous les devis d'un utilisateur
            UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
            return devisFacade.rechercherDevis(uh);
        }
        else if(idUtilisateurHardis==null && idClient!=null && statutDevis==null){
            //Afficher tous les devis d'un client
            Client c = clientFacade.rechercheClient(idClient);
            return devisFacade.rechercherDevis(c);
        }
        else if(idUtilisateurHardis==null && idClient==null && statutDevis!=null){
            //Afficher tous les devis qui ont un statut
            StatutDevis statut = StatutDevis.valueOf(statutDevis);
            return devisFacade.rechercherDevis(statut);
        }
        else if(idUtilisateurHardis!=null && idClient!=null && statutDevis==null){
            //Afficher tous les devis d'un utilisateur et un client
            UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
            Client c = clientFacade.rechercheClient(idClient);
            return devisFacade.rechercherDevis(uh,c);
        }
        else if(idUtilisateurHardis!=null && idClient==null && statutDevis!=null){
            //Afficher tous les devis d'un utilisateur et un statut
            UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
            StatutDevis statut = StatutDevis.valueOf(statutDevis);
            return devisFacade.rechercherDevis(uh,statut);
        }
        else if(idUtilisateurHardis==null && idClient!=null && statutDevis!=null){
            //Afficher tous les devis d'un client et un statut
            Client c = clientFacade.rechercheClient(idClient);
            StatutDevis statut = StatutDevis.valueOf(statutDevis);
            return devisFacade.rechercherDevis(c,statut);
        }
        else {
            //Afficher tous les devis d'un utilisateur pour un client avec un statut
            UtilisateurHardis uh = utilisateurHardisFacade.rechercheUtilisateurHardis(idUtilisateurHardis);
            Client c = clientFacade.rechercheClient(idClient);
            StatutDevis statut = StatutDevis.valueOf(statutDevis);
            return devisFacade.rechercherDevis(uh,c,statut);
        }
    }
    
    
}
