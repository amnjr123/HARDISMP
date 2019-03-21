/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import Enum.Helpers;
import Enum.ProfilTechnique;
import Enum.SendMail;
import GestionCatalogue.Offre;
import GestionUtilisateur.Agence;
import GestionUtilisateur.Consultant;
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
    public Consultant creerConsultant(String nom, String prenom, String mail, String tel, ProfilTechnique profil, float plafondDelegation, Agence agence, List<Offre> offres) {
        Consultant c = new Consultant();
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setMail(mail);
        c.setTelephone(tel);
        c.setProfilTechnique(profil);
        c.setActifInactif(true);
        c.setPlafondDelegation(plafondDelegation);
        c.setDateCreationCompte(new Date());
        c.setAgence(agence);
        for (Offre o : offres) {
            c.getOffres().add(o);
            System.out.println("Inside Facade id offre " + o.getId() + " " + o.getLibelle());

        }
        c.setDtype("Consultant");
        /*MDP*/
 /*Envoi mail avec mdp géneré*/
        SendMail s = new SendMail();
        String mdp = s.sendMailUtilisateurHardisMdp(c, "Consultant");
        /*Hashage password*/
        try {
            c.setMdp(Helpers.sha1(mdp));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ClientFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        create(c);
        return c;
    }

    @Override //Méthode pour Administrateur
    public Consultant modifierConsultant(Consultant c, String nom, String prenom, String mail, String tel, ProfilTechnique profil, boolean actifInactif, float plafondDelegation, List<Offre> offres, Agence a) {
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setMail(mail);
        c.setTelephone(tel);
        c.setProfilTechnique(profil);
        c.setActifInactif(actifInactif);
        c.setPlafondDelegation(plafondDelegation);
        c.setOffres(offres);
        c.setAgence(a);
        edit(c);
        return c;
    }

    @Override //Méthode pour Consultant Gestionnaire ou Visualisation
    public Consultant modifierConsultant(Consultant c, String mail, String tel, boolean actifInactif) {
        c.setMail(mail);
        c.setTelephone(tel);
        c.setActifInactif(actifInactif);
        edit(c);
        return c;
    }

    @Override
    public Consultant supprimerConsultant(Consultant c) {
        remove(c);
        return c;
    }

    @Override
    public Consultant rechercheConsultant(long id) {
        return find(id);
    }

    @Override
    public List<Consultant> rechercheConsultant() {
        return findAll();
    }
}
