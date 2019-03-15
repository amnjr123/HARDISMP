/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeCatalogue;

import Enum.LieuIntervention;
import GestionCatalogue.ServiceNonStandard;
import FacadeUtilisateur.AbstractFacade;
import GestionCatalogue.Offre;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class ServiceNonStandardFacade extends AbstractFacade<ServiceNonStandard> implements ServiceNonStandardFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceNonStandardFacade() {
        super(ServiceNonStandard.class);
    }
    
    @Override
    public ServiceNonStandard creerServiceNonStandard(String nom, String description, LieuIntervention lieu, float cout, boolean fraisInclus, String conditions, int delaiRelance, Offre o){
        ServiceNonStandard s = new ServiceNonStandard();
        s.setNom(nom);
        s.setDescriptionService(description);
        s.setLieuIntervention(lieu);
        s.setCout(cout);
        s.setFraisInclus(fraisInclus);
        s.setConditions(conditions);
        s.setDelaiRelance(delaiRelance);
        s.setOffre(o);
        s.setDateDebutValidite(new Date());
        try {
            s.setDateFinValidite(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2999"));
        } catch (ParseException ex) {
            Logger.getLogger(ServiceNonStandardFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        s.setAncienID(0);
        create(s);  
        return s;
    }
    
    @Override
    public ServiceNonStandard modifierServiceNonStandard(ServiceNonStandard ancienService, String nom, String descriptionService, LieuIntervention lieu, float cout, boolean fraisInclus, String conditions, int delaiRelance, Offre o){
        ServiceNonStandard nouveauService = new ServiceNonStandard();
        nouveauService.setNom(nom);
        nouveauService.setDescriptionService(descriptionService);
        nouveauService.setLieuIntervention(lieu);
        nouveauService.setCout(cout);
        nouveauService.setFraisInclus(fraisInclus);
        nouveauService.setConditions(conditions);
        nouveauService.setDelaiRelance(delaiRelance);
        nouveauService.setOffre(o);
        nouveauService.setDateDebutValidite(new Date());
        try {
            nouveauService.setDateFinValidite(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2999"));
        } catch (ParseException ex) {
            Logger.getLogger(ServiceNonStandardFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        nouveauService.setAncienID(ancienService.getId());
        create(nouveauService);
        ancienService.setDateFinValidite(new Date());
        edit(ancienService);
        return nouveauService;
    }
    
    
    @Override
    public ServiceNonStandard rechercheServiceNonStandard(long id){
        return find(id);
    }
    
    @Override
    public List<ServiceNonStandard> rechercheServiceNonStandard(){
        return findAll();
    }
}
