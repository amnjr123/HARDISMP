<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="GestionCatalogue.Offre"%>
<%@page import="GestionCatalogue.ServiceStandard"%>
<%@page import="GestionCatalogue.ServiceNonStandard"%>
<%@page import="java.util.Collection"%>
<jsp:useBean id="listeServicesStandards" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:useBean id="listeServicesNonStandards" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:useBean id="offre" scope="request" class="GestionCatalogue.Offre"></jsp:useBean>
<jsp:include page="header.jsp"/>
<%Collection<ServiceNonStandard> listServicesNonStandards = listeServicesNonStandards;%>
<%Collection<ServiceStandard> listServicesStandards = listeServicesStandards;%>
<%Offre o = offre;%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Catalogue des services Hardis : Offre <%=o.getLibelle()%></h1>
    </div>

    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Services standards</h1>
            </div>
        </div>

        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nom</th>
                            <th scope="col">Description</th>
                            <th scope="col">Lieu</th>
                            <th scope="col">Coût</th>
                            <th scope="col" class="text-center">Actif ou obsolète</th>
                            <th scope="col" class="text-center">Voir le détail</th>
                        </tr>
                    </thead>
                    <tbody>
                       <%  int i = 0;
                            Calendar cal = Calendar.getInstance();
                            cal.set(Calendar.MILLISECOND, 0);
                            cal.set(Calendar.SECOND, 0);
                            cal.set(Calendar.MINUTE, 0);
                            cal.set(Calendar.HOUR_OF_DAY, 0);
                            cal.set(Calendar.DAY_OF_MONTH, 1);
                            cal.set(Calendar.MONTH, 0);
                            cal.set(Calendar.YEAR, 2099);
                            Date date = cal.getTime();
                            for (ServiceStandard st : listServicesStandards) {%>                                      
                        <tr>
                            <td><%=st.getId()%></td>
                            <td><%=st.getNom()%></td>
                            <td><%=st.getDescriptionService()%></td>
                            <td><%if(st.getLieuIntervention().toString().equals("Agence_Hardis")){%>Agence Hardis<%}else if(st.getLieuIntervention().toString().equals("Site_Client")){%>Site Client<%}else{%>Mixte<%}%></td>
                            <td><%=st.getCout()%></td>
                            <td class="text-center"><%if(st.getDateFinValidite().after(date)){%><i data-feather="check-circle" style="color:green"></i><%}else{%><i data-feather="x" style="color:red"></i><%}%></td>
                            <td class="text-center"><a data-toggle="modal" data-target="#detailServiceStandard<%=(st.getId())%>" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="list"></i></a></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
                            
    
    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Services Non Standards</h1>
            </div>
        </div>

        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nom</th>
                            <th scope="col">Description</th>
                            <th scope="col">Lieu</th>
                            <th scope="col">Coût</th>
                            <th class="text-center" scope="col">Actif ou obsolète</th>
                            <th class="text-center" scope="col">Voir le détail</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  int n = 0;
                            for (ServiceNonStandard st : listServicesNonStandards) {%>                                      
                        <tr>
                         <td><%=st.getId()%></td>
                            <td><%=st.getNom()%></td>
                            <td><%=st.getDescriptionService()%></td>
                            <td><%if(st.getLieuIntervention().toString().equals("Agence_Hardis")){%>Agence Hardis<%}else if(st.getLieuIntervention().toString().equals("Site_Client")){%>Site Client<%}else{%>Mixte<%}%></td>
                            <td><%=st.getCout()%></td>
                            <td class="text-center"><%if(st.getDateFinValidite().after(date)){%><i data-feather="check-circle" style="color:green"></i><%}else{%><i data-feather="x" style="color:red"></i><%}%></td>
                            <td class="text-center"><a data-toggle="modal" data-target="#detailServiceNonStandard<%=(st.getId())%>" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="list"></i></a></td>
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
                            <p><span class="font-weight-bold">Lieu de l'intervention : </span><span class="font-weight-light"><%if(st.getLieuIntervention().toString().equals("Agence_Hardis")){%>Agence Hardis<%}else if(st.getLieuIntervention().toString().equals("Site_Client")){%>Site Client<%}else{%>Mixte<%}%></span></p>
                            <p><span class="font-weight-bold">Prix : </span><span class="font-weight-light"><%=(st.getCout())%> euros, <%if(st.getFraisInclus()==true){%>Frais inclus<%}else{%>Frais non inclus<%}%></span></p>
                            <p class="font-weight-bold">Nombre de jours de travail</p>
                            <p class="font-weight-light"> Consultant Senior : <%=(st.getNbrJoursConsultantSenior())%> jours</p>
                            <p class="font-weight-light">Consultant Confirmé : <%=(st.getNbrJoursConsultantConfirme())%> jours</p>
                            <p class="font-weight-light">Consultant Junior : <%=(st.getNbrJoursConsultantJunior())%> jours</p>
                            <p class="font-weight-bold">Nombre d'heures</p>
                            <p class="font-weight-light">Ateliers et entretiens : <%=(st.getNbrHeuresAtelierEntretienPrevu())%> heures</p>
                            <p class="font-weight-light">Support téléphonique : <%=(st.getNbrHeuresSupportTel())%> heures</p>
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
    <div class="modal fade" id="detailServiceNonStandard<%=(st.getId())%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                            <p><span class="font-weight-bold">Lieu de l'intervention : </span><span class="font-weight-light"><%if(st.getLieuIntervention().toString().equals("Agence_Hardis")){%>Agence Hardis<%}else if(st.getLieuIntervention().toString().equals("Site_Client")){%>Site Client<%}else{%>Mixte<%}%></span></p>
                            <p><span class="font-weight-bold">Prix : </span><span class="font-weight-light"><%=(st.getCout())%> euros, <%if(st.getFraisInclus()==true){%>Frais inclus<%}else{%>Frais non inclus<%}%></span></p>
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


</main>


<jsp:include page="footer.jsp"/>
