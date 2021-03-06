/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeCatalogue;

import Enum.LieuIntervention;
import GestionCatalogue.ServiceStandard;
import javax.ejb.Stateless;
import FacadeUtilisateur.AbstractFacade;
import GestionCatalogue.Offre;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 5151882
 */
@Stateless
public class ServiceStandardFacade extends AbstractFacade<ServiceStandard> implements ServiceStandardFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceStandardFacade() {
        super(ServiceStandard.class);
    }
    
    @Override
    public ServiceStandard creerServiceStandard(String nom, String descriptionService, LieuIntervention lieu, float cout, boolean fraisInclus, String conditions, int delaiRelance, Offre o,  int nbJoursConsultantSenior, int nbJoursConsultantConfirme, int nbJoursConsultantJunior, int nbHeuresAtelierEntretien, int nbHeuresSupportTel, String descriptionPrestation){
        ServiceStandard s = new ServiceStandard();
        s.setNom(nom);
        s.setDescriptionService(descriptionService);
        s.setLieuIntervention(lieu);
        s.setCout(cout);
        s.setFraisInclus(fraisInclus);
        s.setConditions(conditions);
        s.setDelaiRelance(delaiRelance);
        s.setOffre(o);
        s.setDateDebutValidite(new Date());
        s.setDtype("ServiceStandard");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.YEAR, 2100);
        s.setDateFinValidite(cal.getTime());
        s.setAncienID(0);
        s.setNbrJoursConsultantSenior(nbJoursConsultantSenior);
        s.setNbrJoursConsultantConfirme(nbJoursConsultantConfirme);
        s.setNbrJoursConsultantJunior(nbJoursConsultantJunior);
        s.setNbrHeuresAtelierEntretienPrevu(nbHeuresAtelierEntretien);
        s.setNbrHeuresSupportTel(nbHeuresSupportTel);
        s.setDescriptionPrestation(descriptionPrestation);
        create(s);  
        return s;
    }
    
    @Override
    public ServiceStandard modifierServiceStandard(ServiceStandard ancienService,String nom, String descriptionService, LieuIntervention lieu, float cout, boolean fraisInclus, String conditions, int delaiRelance, Offre o,  int nbJoursConsultantSenior, int nbJoursConsultantConfirme, int nbJoursConsultantJunior, int nbHeuresAtelierEntretien, int nbHeuresSupportTel, String descriptionPrestation){
        ServiceStandard nouveauService = new ServiceStandard();
        nouveauService.setNom(nom);
        nouveauService.setDescriptionService(descriptionService);
        nouveauService.setLieuIntervention(lieu);
        nouveauService.setCout(cout);
        nouveauService.setFraisInclus(fraisInclus);
        nouveauService.setConditions(conditions);
        nouveauService.setDelaiRelance(delaiRelance);
        nouveauService.setOffre(o);
        nouveauService.setDateDebutValidite(new Date());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.YEAR, 2100);
        nouveauService.setDateFinValidite(cal.getTime());
        nouveauService.setAncienID(ancienService.getId());
        nouveauService.setNbrJoursConsultantSenior(nbJoursConsultantSenior);
        nouveauService.setNbrJoursConsultantConfirme(nbJoursConsultantConfirme);
        nouveauService.setNbrJoursConsultantJunior(nbJoursConsultantJunior);
        nouveauService.setNbrHeuresAtelierEntretienPrevu(nbHeuresAtelierEntretien);
        nouveauService.setNbrHeuresSupportTel(nbHeuresSupportTel);
        nouveauService.setDescriptionPrestation(descriptionPrestation);
        create(nouveauService);
        ancienService.setDateFinValidite(new Date());
        edit(ancienService);
        return nouveauService;
    }
    
    @Override
    public ServiceStandard supprimerServiceStandard(ServiceStandard service){
        service.setDateFinValidite(new Date());
        return service;
    }
    
    @Override
    public ServiceStandard rechercheServiceStandard(long id){
        return find(id);
    }
    
    @Override
    public List<ServiceStandard> rechercheServiceStandard(){
        return findAll();
    }
    
    @Override
    public List<ServiceStandard> rechercherServiceStandard(Offre o){
        Query requete = em.createQuery("select s from ServiceStandard as s where s.offre=:o ");
        requete.setParameter("o", o);
        return requete.getResultList();
    }
    
    @Override
    public List<ServiceStandard> rechercheServicesStandardsActuels(Offre o) {
        Query requete = getEntityManager().createQuery("select s from ServiceStandard as s where s.dateFinValidite>:date AND s.offre=:offre");
        requete.setParameter("date", new Date());
        requete.setParameter("offre", o);
        return requete.getResultList();
    }

}
