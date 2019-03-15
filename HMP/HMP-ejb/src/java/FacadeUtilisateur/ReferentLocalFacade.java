/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import Enum.ProfilTechnique;
import GestionCatalogue.Offre;
import GestionUtilisateur.Agence;
import GestionUtilisateur.ReferentLocal;
import java.util.Date;
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
public class ReferentLocalFacade extends AbstractFacade<ReferentLocal> implements ReferentLocalFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReferentLocalFacade() {
        super(ReferentLocal.class);
    }
    
    @Override
    public ReferentLocal creerReferentLocal(String nom,String prenom,String mail,String tel,String mdp,ProfilTechnique profil,Boolean actifInactif,float plafondDelegation,Offre offre,Agence agence){
        ReferentLocal rl = new ReferentLocal();
        rl.setNom(nom);
        rl.setPrenom(prenom);
        rl.setMail(mail);
        rl.setTelephone(tel);
        rl.setMdp(mdp);
        rl.setProfilTechnique(profil);
        rl.setActifInactif(actifInactif);
        rl.setPlafondDelegation(plafondDelegation);
        rl.setDateCreationCompte(new Date());
        rl.setOffre(offre);
        rl.setAgence(agence);
        create(rl);  
        return rl;
    }
    
    
    @Override //Méthode pour Administrateur
    public ReferentLocal modifierReferentLocal(ReferentLocal rl, String nom,String prenom,String mail,String tel,String mdp,ProfilTechnique profil,Boolean actifInactif,float plafondDelegation,Offre offre,Agence agence){
        rl.setNom(nom);
        rl.setPrenom(prenom);
        rl.setMail(mail);
        rl.setTelephone(tel);
        rl.setMdp(mdp);
        rl.setProfilTechnique(profil);
        rl.setActifInactif(actifInactif);
        rl.setPlafondDelegation(plafondDelegation);
        rl.setOffre(offre);
        rl.setAgence(agence);
        edit(rl);
        return rl;
    }
    
    @Override //Méthode pour ReferentLocal Gestionnaire ou Visualisation
    public ReferentLocal modifierReferentLocal(ReferentLocal rl, String mail,String tel,String mdp,Boolean actifInactif){
        rl.setMail(mail);
        rl.setTelephone(tel);
        rl.setMdp(mdp);
        rl.setActifInactif(actifInactif);
        edit(rl);
        return rl;
    }
    
    @Override
    public ReferentLocal supprimerReferentLocal(ReferentLocal rl){
        remove(rl);
        return rl;
    }
    
    @Override
    public ReferentLocal rechercheReferentLocal(long id){
        return find(id);
    }
    
    @Override
    public List<ReferentLocal> rechercheReferentLocal(){
        return findAll();
    }
    
    @Override
    public ReferentLocal rechercheReferentLocal(Agence a, Offre o){
        Query requete = em.createQuery("select r from ReferentLocal as r where  r.agence=:a and r.offre=:o");
        requete.setParameter("a", a);
        requete.setParameter("o", o);
        if (!requete.getResultList().isEmpty()) {
            return (ReferentLocal) requete.getSingleResult();
        } else {
            return null;
        }
    }
    
}
