<%@page import="java.util.ArrayList"%>
<%@page import="GestionUtilisateur.Consultant"%>
<%@page import="GestionDevis.DevisStandard"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Collection"%>
<%@page import="GestionDevis.Intervention"%>
<%@page import="GestionUtilisateur.Disponibilite"%>

<jsp:useBean id="listConsultants" scope="request" class="java.util.Collection"></jsp:useBean>

<jsp:useBean id="devisStandard" scope="request" class="GestionDevis.DevisStandard"></jsp:useBean>
<jsp:include page="header.jsp"/>
<style>
    <jsp:include page="../css/bootstrap4-toggle.css"/>
</style>
<%
    Collection<Consultant> listeConsultants = listConsultants;
    DevisStandard devis = devisStandard;
%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Demander un devis pour le service <%=(devis.getServiceStandard().getNom())%> de l'offre <%=(devis.getServiceStandard().getOffre().getLibelle())%></h1>
        <div class="float-right">
            <a href="#"  data-toggle="modal" data-target="#supprimerDevis" type="button" class="btn btn-danger">Supprimer le devis</a>
        </div>
    </div>
</main>

<div class="container">
    <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletClient">
        <div class="form-group">
            <label for="description"><b>Description *</b></label>
            <textarea rows="4"  minlength="50" name="commentaire" type="text" id="commentaire" class="form-control" placeholder="votre commentaire" required autofocus></textarea>
            <div class="invalid-feedback">
                Un commentaire sur votre besoin est obligatoire et doit être au minimum 50 caractères.
            </div>
        </div>

        <div class="form-group">
            <label for="description"><b>Choix des consultants & date d'intervention *</b></label>
            <%for (Consultant c : listeConsultants) {%>
            <p><%=c.getPrenom()%> <%=c.getNom()%></p>
            
            <select name="disponibilites" class="form-control selectpicker" id="selectOffre"  multiple show-tick  style="width:auto;">  
                    <%//Liste pour retenir les journées déjà affichées
                        ArrayList<Disponibilite> listeVerifications = new ArrayList();
                        for (Disponibilite d : c.getDisponibilites()){
                        //REGROUPREMENT DISPO A LA JOURNEE
                        for(Disponibilite dTestMemeJournee : c.getDisponibilites()){
                            Calendar dcal = Calendar.getInstance();
                            dcal.setTime(d.getDateDebut());
                            Calendar dTest = Calendar.getInstance();
                            dTest.setTime(dTestMemeJournee.getDateFin());
                            //Vérification que d n'est pas la même que dTestMemeJournee 
                            if(d.getId()!=dTestMemeJournee.getId()){
                                //Vérification même journée
                                if(dcal.get(Calendar.YEAR)==dTest.get(Calendar.YEAR) && dcal.get(Calendar.MONTH)==dTest.get(Calendar.MONTH) && dcal.get(Calendar.DAY_OF_MONTH)==dTest.get(Calendar.DAY_OF_MONTH)){
                                    //Vérification qu'on n'a pas déjà affiché cette journée
                                    if(!listeVerifications.contains(dTestMemeJournee) && !listeVerifications.contains(d)){
                                        %><option value="<%=d.getId()%>/<%=dTestMemeJournee.getId()%>" ><%out.println(dcal.get(Calendar.DAY_OF_MONTH)+"/"+dcal.get(Calendar.MONTH)+"/"+dcal.get(Calendar.YEAR));%></option><%
                                        listeVerifications.add(dTestMemeJournee);
                                        listeVerifications.add(d);
                                    }
                                }
                            }
                        }  
                    }%> 
                </select>
            <%}%>
        </div>
        <div class="form-group">
            <input type="hidden" name="idDevis" value="<%=devis.getId()%>">
            <input type="hidden" name="action" value="completerDevisStandard">
            <button type="submit" class="btn btn-success">Valider</button>
            <button type="reset" class="btn btn-warning ">Rénisitaliser</button>
        </div>
    </form>
</div>

<div class="modal fade" id="supprimerDevis" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Supprimer le devis</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">X</button>
            </div>
            <div class="modal-body">
                <form class="needs-validation form" novalidate role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletClient">
                    <div class="text-center">
                        <input name="idDevis" data-toggle="toggle" data-size="lg" type="checkbox" value="<%=devis.getId()%>" required="true" data-onstyle="success" data-on="Je confirme" data-off="Je ne confirme pas" data-width="220" id="devis" >
                        <label for="devis" class="mt-3">&nbsp;de supprimer le Devis</label>
                        <div class="invalid-feedback" style="width: 100%;">
                            Il est obligatoire d'accepter pour supprimer le devis.
                        </div>
                    </div>
                    <center>
                        <div class="form-group py-2 text-center">
                            <input type="hidden" name="action" value="supprimerDevisStandard">
                            <button type="submit" class="btn btn-danger "><i data-feather='folder-minus'></i>&nbsp; Supprimer</button>
                            <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        </div>
                    </center>
                </form>

            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
