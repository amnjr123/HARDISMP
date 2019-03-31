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
    public Utilisateur rechercherUtilisateurExistant(String mail) {
        return utilisateurFacade.rechercherUtilisateurParMail(mail);
    }

    @Override
    public boolean motDePasseOublie(String email) {
        if (utilisateurFacade.rechercherUtilisateurParMail(email) != null) {
            Utilisateur u = utilisateurFacade.rechercherUtilisateurParMail(email);
            utilisateurFacade.motDePasseOublie(u);
            return true;
        }
        return false;
    }

    @Override
    public void test() {
        //this.creerClient("NEJJARI","Amine","admin@gmail.com","123456","0624318857");
        /*ReferentLocal uh = new ReferentLocal();
        uh.setNom("Gestionnaire");
        uh.setPrenom("test");
        uh.setMail("test@gmail.com");
        uh.setMdp("40BD001563085FC35165329EA1FF5C5ECBDBBEEF");
        uh.setPlafondDelegation(Float.parseFloat("1000000"));
        uh.setProfilTechnique(ProfilTechnique.Gestionnaire);

        referentLocalFacade.create(uh);
*/
        ReferentLocal ur = new ReferentLocal();
        ur.setNom("Boudyach");
        ur.setPrenom("Anas");
        ur.setMail("admin@gmail.com");
        ur.setTelephone("0615962832");
        ur.setMdp("7C222FB2927D828AF22F592134E8932480637C0D");
        ur.setProfilTechnique(ProfilTechnique.Administrateur);
        referentLocalFacade.create(ur);
    }
}
