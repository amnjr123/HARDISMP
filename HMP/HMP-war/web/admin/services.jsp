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
        <h1 class="h2">Offre <%o.getLibelle();%></h1>
    </div>

    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Services associ�es</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <button  class="btn btn-sm btn-success" data-toggle="modal" data-target="#exampleModal">
                        <span data-feather="plus"></span>
                        Ajouter un service
                    </button>
                </div>
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
                            <th scope="col">Co�t</th>
                            <th scope="col">Frais inclus ou non</th>
                            <th scope="col">Conditions</th>
                            <th scope="col">D�lai de relance</th>
                            <th scope="col">D�but de validit�</th>
                            <th scope="col">Fin de validit�</th>
                            <th scope="col">Jours Consultant Senior</th>
                            <th scope="col">Jours Consultant Confirm�</th>
                            <th scope="col">Jours Consultant Junior</th>
                            <th scope="col">Heures Ateliers/Entretiens</th>
                            <th scope="col">Heures support t�l�phonique</th>
                            <th scope="col">D�tails description</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  int i = 0;
                            for (ServiceStandard st : listServicesStandards) {%>                                      
                        <tr>
                            <td><%=st.getId()%></td>
                            <td><%=st.getNom()%></td>
                            <td><%=st.getDescriptionService()%></td>
                            <td><%=st.getLieuIntervention()%></td>
                            <td><%=st.getCout()%></td>
                            <td><%=st.getFraisInclus()%></td>
                            <td><%=st.getConditions()%></td>
                            <td><%=st.getDelaiRelance()%></td>
                            <td><%=st.getDateDebutValidite()%></td>
                            <td><%=st.getDateFinValidite()%></td>
                            <td><%=st.getNbrJoursConsultantSenior()%></td>
                            <td><%=st.getNbrJoursConsultantConfirme()%></td>
                            <td><%=st.getNbrJoursConsultantJunior()%></td>
                            <td><%=st.getNbrHeuresAtelierEntretienPrevu()%></td>
                            <td><%=st.getNbrHeuresSupportTel()%></td>
                            <td><%=st.getDescriptionPrestation()%></td>
                            <td><div class="dropdown">
                            <td><a href="#" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="phone"></i></a>
                                <a href="#" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="edit-2"></i></a>
                                <a href="#" type="button" class="btn" style="background-color:transparent; color:red"><i data-feather="trash-2"></i></a></td>
                                </div></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
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
                            <th scope="col">Co�t</th>
                            <th scope="col">Frais inclus ou non</th>
                            <th scope="col">Conditions</th>
                            <th scope="col">D�lai de relance</th>
                            <th scope="col">D�but de validit�</th>
                            <th scope="col">Fin de validit�</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  int n = 0;
                            for (ServiceStandard st : listServicesStandards) {%>                                      
                        <tr>
                            <td><%=st.getId()%></td>
                            <td><%=st.getNom()%></td>
                            <td><%=st.getDescriptionService()%></td>
                            <td><%=st.getLieuIntervention()%></td>
                            <td><%=st.getCout()%></td>
                            <td><%=st.getFraisInclus()%></td>
                            <td><%=st.getConditions()%></td>
                            <td><%=st.getDelaiRelance()%></td>
                            <td><%=st.getDateDebutValidite()%></td>
                            <td><%=st.getDateFinValidite()%></td>
                            <td><div class="dropdown">
                            <td><a href="#" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="phone"></i></a>
                                <a href="#" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="edit-2"></i></a>
                                <a href="#" type="button" class="btn" style="background-color:transparent; color:red"><i data-feather="trash-2"></i></a></td>
                                </div></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">

            <div class="modal-content">
                <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Nouvelle Offre</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>
                            <label for="libelle" class="sr-only">Libell� *</label>
                            <input name="libelle" type="text" id="localisation" class="form-control" placeholder="Libell�" required autofocus>
                        </p>
                    </div>
                    <div class="modal-footer ">
                        <button type="submit" class="btn btn-success">Cr�er l'offre</button>
                        <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        <input type="hidden" name="action" value="creerOffre">
                    </div>
                </form>
            </div>
        </div>

    </div>

</main>


<jsp:include page="footer.jsp"/>
