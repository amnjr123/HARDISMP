/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeDevis;

import GestionDevis.Communication;
import GestionDevis.Devis;
import GestionUtilisateur.Client;
import GestionUtilisateur.UtilisateurHardis;
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
public class CommunicationFacade extends AbstractFacade<Communication> implements CommunicationFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommunicationFacade() {
        super(Communication.class);
    }
    //Dans le cadre d'un devis
    @Override
    public Communication creerCommunication(String contenu,Client client, UtilisateurHardis uh, Devis devis){
        Communication c = new Communication();
        c.setDateEnvoi(new Date());
        c.setContenu(contenu);
        c.setClient(client);
        c.setUtilisateurHardis(uh);
        c.setDevis(devis);
        create(c);  
        return c;
    }
    
    //Hors devis
    @Override
    public Communication creerCommunication(String contenu,Client client, UtilisateurHardis uh){
        Communication c = new Communication();
        c.setDateEnvoi(new Date());
        c.setContenu(contenu);
        c.setClient(client);
        c.setUtilisateurHardis(uh);
        create(c);  
        return c;
    }
    
    @Override
    public Communication supprimerCommunication(Communication c){
        remove(c);
        return c;
    }
    
    @Override
    public Communication rechercheCommunication(long id){
        return find(id);
    }
    
    @Override
    public List<Communication> rechercheCommunication(){
        return findAll();
    }
}
