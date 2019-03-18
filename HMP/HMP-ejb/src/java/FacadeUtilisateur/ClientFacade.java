/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import Enum.Helpers;
import GestionUtilisateur.Client;
import GestionUtilisateur.Entreprise;
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
 * @author
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> implements ClientFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }

    @Override
    public Client creerClient(String nom, String prenom, String mail, String tel, String mdp) {
        Client c = new Client();
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setMail(mail);
        c.setTelephone(tel);
        /*Hashage password*/ 
        try {
            c.setMdp(Helpers.sha1(mdp));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ClientFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*End Hashage*/
        c.setDateCreationCompte(new Date());
        //c.setAdministrateur(false);
        create(c);
        return c;
    }

    @Override
    public Client modifierClient(Client c, String nom, String prenom, String mail, String tel) {
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setMail(mail);
        c.setTelephone(tel);
        edit(c);
        return c;
    }
    
    @Override
    public Client modifierClientMDP(Client c, String mdp) {
        /*Hashage password*/ 
        try {
            c.setMdp(Helpers.sha1(mdp));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ClientFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*End Hashage*/
        edit(c);
        return c;
    }
    

    @Override
    public Client supprimerClient(Client c) {
        remove(c);
        return c;
    }

    @Override
    public Client rechercheClient(long id) {
        return find(id);
    }

    @Override
    public List<Client> rechercheClient() {
        return findAll();
    }

    @Override
    public Client affecterEntreprise(Client c, Entreprise e) {
        c.setEntreprise(e);
        edit(c);
        return c;
    }
    
    @Override
    public Client modifierAdmin(Client c) {
        c.setAdministrateur(true);
        edit(c);
        return c;
    }
    
    @Override
    public Client modifierAdmin(Client ancienAdmin, Client nouveauAdmin) {
        ancienAdmin.setAdministrateur(false);
        nouveauAdmin.setAdministrateur(true);
        edit(ancienAdmin);
        edit(nouveauAdmin);
        return nouveauAdmin;
    }
    
    
    

}
