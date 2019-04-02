/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeDevis;

import Enum.StatutDevis;
import GestionDevis.Devis;
import GestionUtilisateur.Client;
import GestionUtilisateur.UtilisateurHardis;
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
public class DevisFacade extends AbstractFacade<Devis> implements DevisFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DevisFacade() {
        super(Devis.class);
    }
    
    @Override
    public List<Devis> rechercherDevis() {
        return findAll();
    }
    
    @Override
    public Devis rechercherDevis(Long id) {
        return find(id);
    }
    
    @Override
    public List<Devis> rechercherDevis(UtilisateurHardis uh) {
        Query requete = getEntityManager().createQuery("select d from Devis as d where d.utilisateurHardis=:utilisateur");
        requete.setParameter("utilisateur", uh);
        return requete.getResultList();
    }
    
    @Override
    public List<Devis> rechercherDevis(StatutDevis statutDevis) {
        Query requete = getEntityManager().createQuery("select d from Devis as d where d.statut=:statut");
        requete.setParameter("statut", statutDevis);
        return requete.getResultList();
    }
    
    @Override
    public List<Devis> rechercherDevis(UtilisateurHardis uh, StatutDevis statutDevis) {
        Query requete = getEntityManager().createQuery("select d from Devis as d where d.statut=:statut and d.utilisateurHardis=:utilisateur");
        requete.setParameter("statut", statutDevis);
        requete.setParameter("utilisateur", uh);
        return requete.getResultList();
    }
    
    @Override
    public List<Devis> rechercherDevis(Client c) {
        Query requete = getEntityManager().createQuery("select d from Devis as d where d.client=:client");
        requete.setParameter("client", c);
        return requete.getResultList();
    }
    
    @Override
    public List<Devis> rechercherDevis(Client c, StatutDevis statutDevis) {
        Query requete = getEntityManager().createQuery("select d from Devis as d where d.statut=:statut and d.client=:client");
        requete.setParameter("statut", statutDevis);
        requete.setParameter("client", c);
        return requete.getResultList();
    }
    
    @Override
    public List<Devis> rechercherDevis(UtilisateurHardis uh,Client c) {
        Query requete = getEntityManager().createQuery("select d from Devis as d where d.utilisateurHardis=:utilisateur and d.client=:client");
        requete.setParameter("utilisateur", uh);
        requete.setParameter("client", c);
        return requete.getResultList();
    }
    
    @Override
    public List<Devis> rechercherDevis(UtilisateurHardis uh, Client c, StatutDevis statutDevis) {
        Query requete = getEntityManager().createQuery("select d from Devis as d where d.utilisateurHardis=:utilisateur and d.client=:client and d.statut=:statut");
        requete.setParameter("utilisateur", uh);
        requete.setParameter("client", c);
        requete.setParameter("statut", statutDevis);
        return requete.getResultList();
    }
    
    @Override
    public List<Devis> rechercherDevisSaufIncomplet(UtilisateurHardis uh) {
        Query requete = getEntityManager().createQuery("select d from Devis as d where d.utilisateurHardis=:utilisateur and d.statut!=:incomplet and d.statut!=:encours");
        requete.setParameter("utilisateur", uh);
        requete.setParameter("incomplet", StatutDevis.valueOf("Incomplet"));
        requete.setParameter("encours", StatutDevis.valueOf("ReponseEnCours"));
        return requete.getResultList();
    }
    
    @Override
    public List<Devis> rechercherDevisSaufIncomplet() {
        Query requete = getEntityManager().createQuery("select d from Devis as d where d.statut!=:incomplet");
        requete.setParameter("incomplet", StatutDevis.valueOf("Incomplet"));
        return requete.getResultList();
    }
    
    @Override
    public List<Devis> rechercherDevisEncours(Client c) {
        Query requete = getEntityManager().createQuery("select d from Devis as d where d.client=:client and d.statut!=:prestationterminee and d.statut!=:acompte and d.statut!=:negociation and d.statut!=:refuse and d.statut!=:valide");
        requete.setParameter("client", c);
        requete.setParameter("prestationterminee", StatutDevis.valueOf("PrestationTerminee"));
        requete.setParameter("acompte", StatutDevis.valueOf("AcompteRegle"));
        requete.setParameter("negociation", StatutDevis.valueOf("EnNegociation"));
        requete.setParameter("refuse", StatutDevis.valueOf("Refuse"));
        requete.setParameter("valide", StatutDevis.valueOf("Valide"));
        return requete.getResultList();
    }
    
    @Override
    public List<Devis> rechercherDevisTermines(Client c) {
        Query requete = getEntityManager().createQuery("select d from Devis as d where d.client=:client and d.statut!=:reponseencours and d.statut!=:incomplet and d.statut!=:envoye");
        requete.setParameter("client", c);
        requete.setParameter("reponseencours", StatutDevis.valueOf("ReponseEnCours"));
        requete.setParameter("incomplet", StatutDevis.valueOf("Incomplet"));
        requete.setParameter("envoye", StatutDevis.valueOf("Envoye"));
        return requete.getResultList();
    }
}
