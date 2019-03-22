/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import GestionUtilisateur.Disponibilite;
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
public class DisponibiliteFacade extends AbstractFacade<Disponibilite> implements DisponibiliteFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DisponibiliteFacade() {
        super(Disponibilite.class);
    }

    @Override
    public Disponibilite creerDisponibilite(Date dateDebut, Date dateFin, UtilisateurHardis uh) {
        Disponibilite d = new Disponibilite();
        d.setDateDebut(dateDebut);
        d.setDateFin(dateFin);
        d.setUtilisateurHardis(uh);
        create(d);
        return d;
    }

    @Override
    public Disponibilite modifierDisponibilite(Disponibilite d, Date dateDebut, Date dateFin) {
        d.setDateDebut(dateDebut);
        d.setDateFin(dateFin);
        edit(d);
        return d;
    }

    @Override
    public Disponibilite supprimerDisponibilite(Disponibilite d) {
        remove(d);
        return d;
    }

    @Override
    public Disponibilite rechercheDisponibilite(long id) {
        return find(id);
    }

    @Override
    public List<Disponibilite> rechercheDisponibilites() {
        return findAll();
    }

    @Override
    public List<Disponibilite> rechercheDisponibilites(UtilisateurHardis uh) {
        Query requete = getEntityManager().createQuery("select d from Disponibilite as d where d.utilisateurHardis=:uh");
        requete.setParameter("uh", uh);
        return requete.getResultList();
    }

}
