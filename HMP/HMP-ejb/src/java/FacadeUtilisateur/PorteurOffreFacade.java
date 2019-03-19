/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import Enum.Helpers;
import Enum.ProfilTechnique;
import GestionCatalogue.Offre;
import GestionUtilisateur.Agence;
import GestionUtilisateur.PorteurOffre;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public PorteurOffre creerPorteurOffre(String nom,String prenom,String mail,String tel,String mdp,ProfilTechnique profil,Offre offre,Agence agence){
        PorteurOffre po = new PorteurOffre();
        po.setNom(nom);
        po.setPrenom(prenom);
        po.setMail(mail);
        po.setTelephone(tel);
        /*Hashage password*/ 
        try {
            po.setMdp(Helpers.sha1(mdp));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ClientFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*End Hashage*/
        po.setProfilTechnique(profil);
        po.setActifInactif(true);
        po.setDateCreationCompte(new Date());
        po.setOffre(offre);
        po.setAgence(agence);
        create(po);  
        return po;
    }
    
    
    @Override //Méthode pour Administrateur
    public PorteurOffre modifierPorteurOffre(PorteurOffre po, String nom,String prenom,String mail,String tel,ProfilTechnique profil,boolean actifInactif,Offre offre, Agence agence){
        po.setNom(nom);
        po.setPrenom(prenom);
        po.setMail(mail);
        po.setTelephone(tel);
        po.setProfilTechnique(profil);
        po.setActifInactif(actifInactif);
        po.setOffre(offre);
        po.setAgence(agence);
        edit(po);
        return po;
    }
    
    @Override //Méthode pour Porteur d'Offre Gestionnaire ou Visualisation
    public PorteurOffre modifierPorteurOffre(PorteurOffre po, String mail,String tel,boolean actifInactif){
        po.setMail(mail);
        po.setTelephone(tel);
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

    @Override
    public PorteurOffre creerPO(PorteurOffre po) {
         em.persist(po);
         return po;
    }
    
    
}
