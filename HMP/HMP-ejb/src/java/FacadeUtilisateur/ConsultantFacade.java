/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import Enum.ProfilTechnique;
import GestionCatalogue.Offre;
import GestionUtilisateur.Agence;
import GestionUtilisateur.Consultant;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 5151882
 */
@Stateless
public class ConsultantFacade extends AbstractFacade<Consultant> implements ConsultantFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultantFacade() {
        super(Consultant.class);
    }
    
    @Override
    public Consultant creerConsultant(String nom, String prenom,String mail,String tel,String mdp,ProfilTechnique profil,Boolean actifInactif,float plafondDelegation, Agence agence, List<Offre> offres){
        Consultant c = new Consultant();
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setMail(mail);
        c.setTelephone(tel);
        c.setMdp(mdp);
        c.setProfilTechnique(profil);
        c.setActifInactif(actifInactif);
        c.setPlafondDelegation(plafondDelegation);
        c.setDateCreationCompte(new Date());
        c.setAgence(agence);
        c.setOffres(offres);
        create(c);  
        return c;
    }
    
    
    @Override //Méthode pour Administrateur
    public Consultant modifierConsultant(Consultant c, String nom,String prenom,String mail,String tel,String mdp,ProfilTechnique profil,Boolean actifInactif,float plafondDelegation, List<Offre> offres){
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setMail(mail);
        c.setTelephone(tel);
        c.setMdp(mdp);
        c.setProfilTechnique(profil);
        c.setActifInactif(actifInactif);
        c.setPlafondDelegation(plafondDelegation);
        c.setOffres(offres);
        edit(c);
        return c;
    }
    
    @Override //Méthode pour Consultant Gestionnaire ou Visualisation
    public Consultant modifierConsultant(Consultant c, String mail,String tel,String mdp,Boolean actifInactif){
        c.setMail(mail);
        c.setTelephone(tel);
        c.setMdp(mdp);
        c.setActifInactif(actifInactif);
        edit(c);
        return c;
    }
    
    @Override
    public Consultant supprimerConsultant(Consultant c){
        remove(c);
        return c;
    }
    
    @Override
    public Consultant rechercheConsultant(long id){
        return find(id);
    }
    
    @Override
    public List<Consultant> rechercheConsultant(){
        return findAll();
    }
}
