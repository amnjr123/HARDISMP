package SessionUtilisateur;

import Enum.ProfilTechnique;
import FacadeUtilisateur.ClientFacadeLocal;
import FacadeUtilisateur.PorteurOffreFacadeLocal;
import FacadeUtilisateur.ReferentLocalFacadeLocal;
import FacadeUtilisateur.UtilisateurFacadeLocal;
import FacadeUtilisateur.UtilisateurHardisFacadeLocal;
import GestionUtilisateur.Client;
import GestionUtilisateur.PorteurOffre;
import GestionUtilisateur.ReferentLocal;
import GestionUtilisateur.Utilisateur;
import GestionUtilisateur.UtilisateurHardis;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class SessionMain implements SessionLocal {

    @EJB
    private ReferentLocalFacadeLocal referentLocalFacade;

    @EJB
    private PorteurOffreFacadeLocal porteurOffreFacade;

    @EJB
    private UtilisateurHardisFacadeLocal utilisateurHardisFacade;

    @EJB
    private ClientFacadeLocal clientFacade;

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

    
    
    @Override
    public Utilisateur authentification(String mail, String mdp) {
        return utilisateurFacade.authentification(mail, mdp);
    }

    @Override
    public String getTypeUser(Utilisateur utilisateur) {
        return utilisateurFacade.getDType(utilisateur);
    }

    @Override
    public Client rechercheClient(long id) {
        return clientFacade.rechercheClient(id);
    }

    @Override
    public UtilisateurHardis rechercheUtilisateurHardis(Long id) {
        return utilisateurHardisFacade.find(id);
    }

    @Override
    public Client creerClient(String nom, String prenom, String mail, String mdp, String telephone) {
        return clientFacade.creerClient(nom, prenom, mail, mdp, telephone);
    }

    @Override
    public Utilisateur rechercherUtilisateurExistant(String mail){
        return utilisateurFacade.rechercherUtilisateurParMail(mail);
    }
    
    @Override
    public void test(){
        //this.creerClient("NEJJARI","Amine","amnjr123@gmail.com","123456","0624318857");
        ReferentLocal uh = new ReferentLocal();
        uh.setNom("Gestionnaire");
        uh.setPrenom("test");
        uh.setMail("test1@gmail.com");
        uh.setMdp("40BD001563085FC35165329EA1FF5C5ECBDBBEEF");
        uh.setPlafondDelegation(Float.parseFloat("1000000"));
        uh.setProfilTechnique(ProfilTechnique.Gestionnaire);
        
        referentLocalFacade.create(uh);
                
          UtilisateurHardis ur = new UtilisateurHardis();
        ur.setNom("Admin");
        ur.setPrenom("test");
        ur.setMail("amnjr123@gmail.com");
        ur.setMdp("40BD001563085FC35165329EA1FF5C5ECBDBBEEF");
        ur.setProfilTechnique(ProfilTechnique.Administrateur);
        utilisateurHardisFacade.ajouter(ur);
    }
        
}
