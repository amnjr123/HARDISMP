/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeCatalogue;

import GestionCatalogue.Service;
import FacadeUtilisateur.AbstractFacade;
import GestionCatalogue.Offre;
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
public class ServiceFacade extends AbstractFacade<Service> implements ServiceFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceFacade() {
        super(Service.class);
    }
    
    @Override
    public List<Service> rechercherService(){
        return findAll();
    }
    
    @Override
    public Service rechercherService(Long idService){
        return find(idService);
    }
    
    @Override
    public List<Service> rechercherService(Offre o){
        Query requete = em.createQuery("select s from Service as s where  s.offre=:o ");
        requete.setParameter("o", o);
        return requete.getResultList();
    }
    @Override
    public List<Service> rechercheServicesActuels(Offre o) {
        Query requete = getEntityManager().createQuery("select s from Service as s where s.dateFinValidite>:date AND s.offre=:offre");
        requete.setParameter("date", new Date());
        requete.setParameter("offre", o);
        return requete.getResultList();
    }
    
    @Override
    public List<Service> rechercheServicesAnciens(Offre o) {
        Query requete = getEntityManager().createQuery("select s from Service as s where s.dateFinValidite<=:date AND s.offre=:offre");
        requete.setParameter("date", new Date());
        requete.setParameter("offre", o);
        return requete.getResultList();
    }
    
    @Override
    public List<Service> rechercheServicesActuels() {
        Query requete = getEntityManager().createQuery("select s from Service as s where s.dateFinValidite>:date");
        requete.setParameter("date", new Date());
        return requete.getResultList();
    }
    
    @Override
    public List<Service> rechercheServicesAnciens() {
        Query requete = getEntityManager().createQuery("select s from Service as s where s.dateFinValidite<=:date");
        requete.setParameter("date", new Date());
        return requete.getResultList();
    }
}
