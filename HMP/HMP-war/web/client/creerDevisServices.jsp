<%@page import="java.util.Calendar"%>
<%@page import="GestionCatalogue.Livrable"%>
<%@page import="java.util.Date"%>
<%@page import="GestionCatalogue.Offre"%>
<%@page import="GestionCatalogue.ServiceStandard"%>
<%@page import="GestionCatalogue.ServiceNonStandard"%>
<%@page import="java.util.Collection"%>
<jsp:useBean id="listeServicesStandards" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:useBean id="listeServicesNonStandards" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:useBean id="offre" scope="request" class="GestionCatalogue.Offre"></jsp:useBean>
<jsp:include page="header.jsp"/>
<style>
    <jsp:include page="../css/bootstrap4-toggle.css"/>
</style>
<%Collection<ServiceNonStandard> listServicesNonStandards = listeServicesNonStandards;%>
<%Collection<ServiceStandard> listServicesStandards = listeServicesStandards;%>
<%Offre o = offre;%>

<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Demander un devis</h1>
    </div>

    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Choisir un service standard</h1>
            </div>
        </div>

        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Nom</th>
                            <th scope="col">Description</th>
                            <th scope="col">Lieu</th>
                            <th scope="col">Coût</th>
                            <th scope="col">Voir le détail</th>
                            <th scope="col">Choisir</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (ServiceStandard st : listServicesStandards) {
                        %>                                      
                        <tr>
                            <td><%=st.getNom()%></td>
                            <td style="max-width:20em"><%=st.getDescriptionService()%></td>
                            <td><%if (st.getLieuIntervention().toString().equals("Agence_Hardis")) {%>Agence Hardis<%} else if (st.getLieuIntervention().toString().equals("Site_Client")) {%>Site Client<%} else {%>Mixte<%}%></td>
                            <td><%=st.getCout()%></td>
                            <td><a data-toggle="modal" data-target="#detailServiceStandard<%=(st.getId())%>" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="zoom-in"></i></a></td>
                            <td><a href="#"  data-toggle="modal" data-target="#creerServiceStandardConfirmation<%=(st.getId())%>" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="check-circle"></i></a></td>
                        </tr>                  
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <%-- href="${pageContext.request.contextPath}/ServletClient?action=creerDevisStandard&id=<%=st.getId()%>" --%>
    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Choisir un service personnalisé</h1>
            </div>
        </div>

        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Nom</th>
                            <th scope="col">Description</th>
                            <th scope="col">Lieu</th>
                            <th scope="col">Coût</th>
                            <th scope="col">Voir le détail</th>
                            <th scope="col">Choisir</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  int n = 0;
                            for (ServiceNonStandard st : listServicesNonStandards) {%>                                      
                        <tr>
                            <td><%=st.getNom()%></td>
                            <td style="max-width:20em"><%=st.getDescriptionService()%></td>
                            <td><%if (st.getLieuIntervention().toString().equals("Agence_Hardis")) {%>Agence Hardis<%} else if (st.getLieuIntervention().toString().equals("Site_Client")) {%>Site Client<%} else {%>Mixte<%}%></td>
                            <td><%=st.getCout()%></td>
                            <td><a data-toggle="modal" data-target="#detailServiceNonStandard<%=(st.getId())%>" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="zoom-in"></i></a></td>
                            <td><a href="#"  data-toggle="modal" data-target="#creerServiceNonStandardConfirmation<%=(st.getId())%>" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="check-circle"></i></a></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <%
        for (ServiceStandard st : listServicesStandards) {
    %>
    <div class="modal fade" id="detailServiceStandard<%=(st.getId())%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">

            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><%=(st.getNom())%></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    </button>
                </div>
                <div class="modal-body">

                    <p class="font-weight-bold">Description du service</p>
                    <p class="font-weight-light"><%=(st.getDescriptionService())%></p>
                    <p class="font-weight-light"><%=(st.getDescriptionPrestation())%></p>
                    <p><span class="font-weight-bold">Lieu de l'intervention : </span><span class="font-weight-light"><%if (st.getLieuIntervention().toString().equals("Agence_Hardis")) {%>Agence Hardis<%} else if (st.getLieuIntervention().toString().equals("Site_Client")) {%>Site Client<%} else {%>Mixte<%}%></span></p>
                    <p><span class="font-weight-bold">Prix : </span><span class="font-weight-light"><%=(st.getCout())%> euros, <%if (st.getFraisInclus() == true) {%>Frais inclus<%} else {%>Frais non inclus<%}%></span></p>
                    <p class="font-weight-bold">Nombre de jours de travail</p>
                    <p class="font-weight-light"> Consultant Senior : <%=(st.getNbrJoursConsultantSenior())%> jours</p>
                    <p class="font-weight-light">Consultant Confirmé : <%=(st.getNbrJoursConsultantConfirme())%> jours</p>
                    <p class="font-weight-light">Consultant Junior : <%=(st.getNbrJoursConsultantJunior())%> jours</p>
                    <p class="font-weight-bold">Nombre d'heures</p>
                    <p class="font-weight-light">Ateliers et entretiens : <%=(st.getNbrHeuresAtelierEntretienPrevu())%> heures</p>
                    <p class="font-weight-light">Support téléphonique : <%=(st.getNbrHeuresSupportTel())%> heures</p>
                    <p class="font-weight-bold">Livrables :</p>
                    <%
                        if (st.getLivrables().size() > 0) {
                            for (Livrable l : st.getLivrables()) {
                    %>
                    <p class="font-weight-light"><%=l.getLibelle()%></p>
                    <%
                            }
                        }
                    %>
                    <p class="font-weight-bold">Conditions générales</p>
                    <div style="overflow-y: scroll; height:4em"> 
                        <p class="font-weight-light"><%=(st.getConditions())%></p>
                    </div>
                </div>
                <div class="modal-footer ">
                    <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>

    </div>
    <%
        }
    %>
    <%
        for (ServiceNonStandard st : listServicesNonStandards) {
    %>
    <div class="modal fade" id="detailServiceNonStandard<%=(st.getId())%>" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><%=(st.getNom())%></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    </button>
                </div>
                <div class="modal-body">
                    <p class="font-weight-bold">Description du service</p>
                    <p class="font-weight-light"><%=(st.getDescriptionService())%></p>
                    <p><span class="font-weight-bold">Lieu de l'intervention : </span><span class="font-weight-light"><%if (st.getLieuIntervention().toString().equals("Agence_Hardis")) {%>Agence Hardis<%} else if (st.getLieuIntervention().toString().equals("Site_Client")) {%>Site Client<%} else {%>Mixte<%}%></span></p>
                    <p><span class="font-weight-bold">Prix : </span><span class="font-weight-light"><%=(st.getCout())%> euros, <%if (st.getFraisInclus() == true) {%>Frais inclus<%} else {%>Frais non inclus<%}%></span></p>
                    <p class="font-weight-bold">Conditions générales</p>
                    <div style="overflow-y: scroll; height:4em"> 
                        <p class="font-weight-light"><%=(st.getConditions())%></p>
                    </div>
                </div>
                <div class="modal-footer ">
                    <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>

    </div>
    <%
        }
    %>

    <%
        for (ServiceStandard st : listServicesStandards) {
    %>     
    <div class="modal fade" id="creerServiceStandardConfirmation<%=(st.getId())%>" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog" role="document">

            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Creer devis standard pour le service&nbsp;<%=(st.getNom())%></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    </button>
                </div>
                <div class="modal-body">
                    <form class="needs-validation form" novalidate role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletClient">
                        <div class="text-center">
                            <input name="choixDevisId" data-toggle="toggle" data-size="lg" type="checkbox" value="<%=(st.getId())%>" required="true" data-onstyle="success" data-on="J'accepte" data-off="Je n'accepte pas" data-width="200" >
                            <label for="devis" class="mt-3"> De créer un Devis standard pour le service <%=st.getNom()%></label>
                            <div class="invalid-feedback" style="width: 100%;">
                                Il est obligatoire d'accepter la création du devis.
                            </div>
                        </div>
                        <center>
                            <div class="form-group py-2 text-center">
                                <input type="hidden" name="action" value="creerDevisStandard">
                                <input type="hidden" name="idOffre" value="<%=st.getId()%>">
                                <button type="button" class="btn btn-success "><i data-feather='folder-plus'></i>&nbsp; Valider</button>
                                <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                            </div>
                        </center>
                    </form>

                </div>
            </div>
        </div>
    </div>

    <%}%>

    <%
        for (ServiceNonStandard st : listServicesNonStandards) {
    %>     
    <div class="modal fade" id="creerServiceNonStandardConfirmation<%=(st.getId())%>" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Creer devis non standard pour le service&nbsp;<%=(st.getNom())%></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    </button>
                </div>
                <div class="modal-body">
                    <form class="needs-validation form" novalidate role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletClient">
                        <div class="text-center">
                            <input name="choixDevisId" data-toggle="toggle" data-size="lg" type="checkbox" value="<%=(st.getId())%>" required="true" data-onstyle="success" data-on="J'accepte" data-off="Je n'accepte pas" data-width="200" >
                            <label for="devis" class="mt-3"> De créer un Devis non standard pour le service <%=st.getNom()%></label>
                            <div class="invalid-feedback" style="width: 100%;">
                                Il est obligatoire d'accepter la création du devis.
                            </div>
                        </div>
                        <center>
                            <div class="form-group py-2 text-center">
                                <input type="hidden" name="action" value="creerDevisNonStandard">
                                <input type="hidden" name="idOffre" value="<%=st.getId()%>">
                                <button type="button" class="btn btn-success "><i data-feather='folder-plus'></i>&nbsp; Valider</button>
                                <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                            </div>
                        </center>
                    </form>

                </div>
            </div>
        </div>
    </div>

    <%}%>
</main>


<jsp:include page="footer.jsp"/>
