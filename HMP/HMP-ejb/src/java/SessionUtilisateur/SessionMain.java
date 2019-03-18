package SessionUtilisateur;

import Enum.ProfilTechnique;
import FacadeUtilisateur.ClientFacadeLocal;
import FacadeUtilisateur.UtilisateurFacadeLocal;
import FacadeUtilisateur.UtilisateurHardisFacadeLocal;
import GestionUtilisateur.Client;
import GestionUtilisateur.Utilisateur;
import GestionUtilisateur.UtilisateurHardis;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class SessionMain implements SessionLocal {

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
        return clientFacade.creerClient(nom, prenom, mail, mdp, mdp);
    }

    @Override
    public Utilisateur rechercherUtilisateurExistant(String mail){
        return utilisateurFacade.rechercherUtilisateurParMail(mail);
    }
    
    @Override
    public void test(){
        //this.creerClient("NEJJARI","Amine","amnjr123@gmail.com","123456","0624318857");
        UtilisateurHardis uh = new UtilisateurHardis();
        uh.setNom("NEJJARI");
        uh.setPrenom("Amine");
        uh.setMail("amnjr456@gmail.com");
        uh.setMdp("7C4A8D09CA3762AF61E59520943DC26494F8941B");
        uh.setProfilTechnique(ProfilTechnique.Gestionnaire);
        utilisateurHardisFacade.ajouter(uh);
    }
        
}
