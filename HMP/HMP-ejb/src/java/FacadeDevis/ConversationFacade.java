/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeDevis;

import GestionDevis.Conversation;
import GestionDevis.Devis;
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
 * @author ManonMADRANGES
 */
@Stateless
public class ConversationFacade extends AbstractFacade<Conversation> implements ConversationFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConversationFacade() {
        super(Conversation.class);
    }
    
    //Hors devis
    @Override
    public Conversation creerConversation(Client client){
        //Première communication du client
        Conversation c = new Conversation();
        c.setClient(client);
        c.setDateCreation(new Date());
        create(c);  
        return c;
    }
    @Override
    public Conversation affecterUHConversation(Conversation c,UtilisateurHardis uh){
        //Affectation de l'utilisateur Hardis qui répond en premier
        c.setUtilisateurHardis(uh);
        edit(c);  
        return c;
    }
    
    //Dans un devis
    @Override
    public Conversation creerConversation(Devis d, Client client, UtilisateurHardis uh){
        Conversation c = new Conversation();
        c.setClient(client);
        c.setUtilisateurHardis(uh);
        c.setDevis(d);
        c.setDateCreation(new Date());
        create(c);  
        return c;
    }
    
    @Override
    public Conversation rechercheConversation(long id){
        return find(id);
    }
    
    @Override
    public List<Conversation> rechercheConversations(){
        return findAll();
    }
    
    @Override
    public List<Conversation> rechercherConversationsSansReponse() {
        Query requete = getEntityManager().createQuery("select c from Conversation as c where c.UtilisateurHardis is null");
        return requete.getResultList();
    }
    
    @Override
    public List<Conversation> rechercherConversations(Client c) {
        Query requete = getEntityManager().createQuery("select c from Conversation as c where c.Client=:c order by c.DateCreation desc");
        requete.setParameter("c", c);
        return requete.getResultList();
    }
    
    @Override
    public List<Conversation> rechercherConversations(UtilisateurHardis u) {
        Query requete = getEntityManager().createQuery("select c from Conversation as c where c.UtilisateurHardis=:uh order by c.DateCreation desc");
        requete.setParameter("uh", u);
        return requete.getResultList();
    }
    
    @Override
    public Conversation rechercherConversation(Devis d) {
        Query requete = getEntityManager().createQuery("select c from Conversation as c where c.devis=:d");
        requete.setParameter("d", d);
        if (!requete.getResultList().isEmpty()) {
            return (Conversation) requete.getSingleResult();
        } else {
            return null;
        }
    }
    
}
