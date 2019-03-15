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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        try {
            s.setDateFinValidite(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2999"));
        } catch (ParseException ex) {
            Logger.getLogger(ServiceNonStandardFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            nouveauService.setDateFinValidite(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2999"));
        } catch (ParseException ex) {
            Logger.getLogger(ServiceNonStandardFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public ServiceStandard rechercheServiceStandard(long id){
        return find(id);
    }
    
    @Override
    public List<ServiceStandard> rechercheServiceStandard(){
        return findAll();
    }
}
