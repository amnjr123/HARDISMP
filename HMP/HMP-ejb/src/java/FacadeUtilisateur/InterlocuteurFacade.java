/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import GestionUtilisateur.Entreprise;
import GestionUtilisateur.Interlocuteur;
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
public class InterlocuteurFacade extends AbstractFacade<Interlocuteur> implements InterlocuteurFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InterlocuteurFacade() {
        super(Interlocuteur.class);
    }
    
    @Override
    public Interlocuteur creerInterlocuteur(String nom,String prenom, String mail,String telephone, String fonction, Entreprise entreprise){
        Interlocuteur i = new Interlocuteur();
        i.setNom(nom);
        i.setPrenom(prenom);
        i.setMail(mail);
        i.setTelephone(telephone);
        i.setFonction(fonction);
        i.setEntreprise(entreprise);
        create(i);  
        return i;
    }
    
    @Override
    public Interlocuteur modifierInterlocuteur(Interlocuteur i, String nom,String prenom, String mail,String telephone, String fonction){
        i.setNom(nom);
        i.setPrenom(prenom);
        i.setMail(mail);
        i.setTelephone(telephone);
        i.setFonction(fonction);
        edit(i);
        return i;
    }
    
    @Override
    public Interlocuteur supprimerInterlocuteur(Interlocuteur i){
        remove(i);
        return i;
    }
    
    @Override
    public Interlocuteur rechercheInterlocuteur(long id){
        return find(id);
    }
    
    @Override
    public List<Interlocuteur> rechercheInterlocuteur(){
        return findAll();
    }
    
    @Override
    public List<Interlocuteur> rechercheInterlocuteur(Entreprise entreprise) {
        Query requete = getEntityManager().createQuery("select i from Interlocuteur as i where i.entreprise=:entreprise");
        requete.setParameter("entreprise", entreprise);
        return requete.getResultList();
    }
}
