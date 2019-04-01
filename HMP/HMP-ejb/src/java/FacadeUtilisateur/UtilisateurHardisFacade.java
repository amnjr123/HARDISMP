/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import GestionCatalogue.Offre;
import GestionUtilisateur.Agence;
import GestionUtilisateur.UtilisateurHardis;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 5151882
 */
@Stateless
public class UtilisateurHardisFacade extends AbstractFacade<UtilisateurHardis> implements UtilisateurHardisFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurHardisFacade() {
        super(UtilisateurHardis.class);
    }

    @Override
    public UtilisateurHardis rechercheUtilisateurHardis(long id) {
        return find(id);
    }

    @Override //Méthode pour un utilisateur Gestionnaire ou Visualisation sur son propre compte
    public UtilisateurHardis modifierUtilisateurHardis(UtilisateurHardis u, String mail, String tel, boolean actifInactif) {
        u.setMail(mail);
        u.setTelephone(tel);
        u.setActifInactif(actifInactif);
        edit(u);
        return u;
    }

    @Override
    public List<UtilisateurHardis> listUtilisateurHardisAgence(Agence a) {
        Query requete = em.createQuery("select u from UtilisateurHardis as u where u.agence=:agence");
        requete.setParameter("agence", a);
        return requete.getResultList();
    }

    //POUR LES TESTS ###############################################
    public void ajouter(UtilisateurHardis uh) {
        em.persist(uh);
    }
    //POUR LES TESTS ###############################################
}
