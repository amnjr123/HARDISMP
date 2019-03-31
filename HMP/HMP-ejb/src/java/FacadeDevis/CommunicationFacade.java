/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeDevis;

import GestionDevis.Communication;
import GestionDevis.Conversation;
import GestionUtilisateur.Client;
import GestionUtilisateur.UtilisateurHardis;
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
   
    @Override
    public Communication creerCommunication(String contenu, Client client, UtilisateurHardis uh, Conversation conversation){
        Communication c = new Communication();
        c.setDateEnvoi(new Date());
        c.setContenu(contenu);
        if(client != null){
            c.setClient(client);
        }
        else{
            c.setUtilisateurHardis(uh);
        }
        c.setConversation(conversation);
        conversation.getCommunications().add(c);
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
    
    @Override
    public List<Communication> rechercherCommunications(Conversation conv) {
        Query requete = getEntityManager().createQuery("select c from Communication as c where c.Conversation=:conv");
        requete.setParameter("conv",conv);
        return requete.getResultList();
    }
}
