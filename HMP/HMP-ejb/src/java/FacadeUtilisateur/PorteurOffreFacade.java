/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import Enum.ProfilTechnique;
import GestionCatalogue.Offre;
import GestionUtilisateur.Agence;
import GestionUtilisateur.PorteurOffre;
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
public class PorteurOffreFacade extends AbstractFacade<PorteurOffre> implements PorteurOffreFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PorteurOffreFacade() {
        super(PorteurOffre.class);
    }

    @Override
    public PorteurOffre creerPorteurOffre(String nom,String prenom,String mail,String tel,String mdp,ProfilTechnique profil,Boolean actifInactif,Offre offre,Agence agence){
        PorteurOffre po = new PorteurOffre();
        po.setNom(nom);
        po.setPrenom(prenom);
        po.setMail(mail);
        po.setTelephone(tel);
        po.setMdp(mdp);
        po.setProfilTechnique(profil);
        po.setActifInactif(actifInactif);
        po.setDateCreationCompte(new Date());
        po.setOffre(offre);
        po.setAgence(agence);
        create(po);  
        return po;
    }
    
    
    @Override //Méthode pour Administrateur
    public PorteurOffre modifierPorteurOffre(PorteurOffre po, String nom,String prenom,String mail,String tel,String mdp,ProfilTechnique profil,Boolean actifInactif,Offre offre, Agence agence){
        po.setNom(nom);
        po.setPrenom(prenom);
        po.setMail(mail);
        po.setTelephone(tel);
        po.setMdp(mdp);
        po.setProfilTechnique(profil);
        po.setActifInactif(actifInactif);
        po.setOffre(offre);
        po.setAgence(agence);
        edit(po);
        return po;
    }
    
    @Override //Méthode pour Porteur d'Offre Gestionnaire ou Visualisation
    public PorteurOffre modifierPorteurOffre(PorteurOffre po, String mail,String tel,String mdp,Boolean actifInactif){
        po.setMail(mail);
        po.setTelephone(tel);
        po.setMdp(mdp);
        po.setActifInactif(actifInactif);
        edit(po);
        return po;
    }
    
    @Override
    public PorteurOffre supprimerConsultant(PorteurOffre c){
        remove(c);
        return c;
    }
    
    @Override
    public PorteurOffre recherchePorteurOffre(long id){
        return find(id);
    }
    
    @Override
    public List<PorteurOffre> recherchePorteurOffre(){
        return findAll();
    }
    
    
}
