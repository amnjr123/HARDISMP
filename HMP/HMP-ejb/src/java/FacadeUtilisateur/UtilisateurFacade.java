package FacadeUtilisateur;

import Enum.Helpers;
import GestionUtilisateur.Utilisateur;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 5151882
 */
@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> implements UtilisateurFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }

    @Override
    public Utilisateur authentification(String mail, String mdp) {
        Query requete = getEntityManager().createQuery("select u from Utilisateur as u where u.mail=:mail and u.mdp=:mdp");
        requete.setParameter("mail", mail);
        requete.setParameter("mdp", mdp);
        try {
            requete.setParameter("mdp", Helpers.sha1(mdp));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UtilisateurFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!requete.getResultList().isEmpty()) {
            return (Utilisateur) requete.getSingleResult();
        } else {
            return null;
        }
    }

    @Override
    public String getDType(Utilisateur u) {
        Query requete = getEntityManager().createQuery("select u.dtype from Utilisateur as u where  u=:utilisateur ");
        requete.setParameter("utilisateur", u);
        if (!requete.getResultList().isEmpty()) {
            System.out.println(requete.getResultList());
            return requete.getSingleResult().toString();
        } else {
            return null;
        }
    }

    @Override
    public Utilisateur rechercheUtilisateur(long id) {
        return find(id);
    }
    
    @Override
    public Utilisateur rechercherUtilisateurParMail(String mail){
        Query requete = em.createQuery("select u from Utilisateur as u where  u.mail=:mail ");
        requete.setParameter("mail", mail);
        if (!requete.getResultList().isEmpty()) {
            return (Utilisateur) requete.getSingleResult();
        } else {
            return null;
        }
    }
    
    @Override
    public Utilisateur modifierUtilisateurMDP(Utilisateur u, String mdp) {
        /*Hashage password*/ 
        try {
            u.setMdp(Helpers.sha1(mdp));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ClientFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*End Hashage*/
        edit(u);
        return u;
    }
    
}
