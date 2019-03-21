<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="GestionCatalogue.Offre"%>
<%@page import="java.util.Collection"%>
<jsp:useBean id="listOffres" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:include page="header.jsp"/>
<%Collection<Offre> list = listOffres;%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Gestion du catalogue</h1>
    </div>

    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Offres</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <button  class="btn btn-sm btn-success" data-toggle="modal" data-target="#creerOffre">
                        <span data-feather="plus"></span>
                        Nouvelle offre
                    </button>
                </div>
            </div>
        </div>
        <%--Warning Or Sucess--%>   
        <% String error = (String) request.getAttribute("msgError");
                        if (request.getAttribute("msgError") != null) {%>
        <div class="alert alert-danger alert-dismissible fade in show">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attention !</strong>&nbsp;<%=(error)%>.
        </div>
        <%}%>

        <% String success = (String) request.getAttribute("msgSuccess");
            if (request.getAttribute("msgSuccess") != null) {%>
        <div class="alert alert-success alert-dismissible fade in show">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <%=(success)%>.
        </div>
        <%}%>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Libellé</th>
                            <th scope="col">Actif ou obsolète</th>
                            <th scope="col">Services</th>
                            <th scope="col"></th>
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
                            for (Offre o : list) {%>                                      
                        <tr>
                            <td><%=o.getId()%></td>
                            <td><%=o.getLibelle()%></td>
                            <td><%if (o.getDateFinValidite().after(date)) {%><i data-feather="check-circle" style="color:green"></i><%} else {%><i data-feather="x" style="color:red"></i><%}%></td>
                            <td><a href="${pageContext.request.contextPath}/ServletAdministrateur?action=services&id=<%=o.getId()%>" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="list"></i></a></td>
                            <td>
                                <div class="dropdown">
                                    <a href="#" data-toggle="modal" data-target="#modifierOffre<%=(o.getId())%>" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="edit-2"></i></a>
                                        <%if (o.getDateFinValidite().after(date)) {%>
                                    <a href="${pageContext.request.contextPath}/ServletAdministrateur?action=supprimerOffre&idOffre=<%=o.getId()%>" type="button" class="btn" style="background-color:transparent; color:red"><i data-feather="trash-2"></i></a>
                                        <%} else {%>
                                    <a href="${pageContext.request.contextPath}/ServletAdministrateur?action=reactiverOffre&idOffre=<%=o.getId()%>" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="check-circle"></i></a>
                                        <%}%>
                                </div>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="modal fade" id="creerOffre" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">

            <div class="modal-content">
                <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Nouvelle Offre</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="libelle" class="sr-only">Libellé *</label>
                            <input name="libelle" type="text" id="localisation" class="form-control" placeholder="Libellé" required autofocus>
                            <div class="invalid-feedback">
                                Le nom de l'offre est obligatoire.
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer ">
                        <button type="submit" class="btn btn-success">Créer l'offre</button>
                        <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        <input type="hidden" name="action" value="creerOffre">
                    </div>
                </form>
            </div>
        </div>

    </div>
    <%  int n = 0;
    for (Offre o : list) {%>     
    <div class="modal fade" id="modifierOffre<%=o.getId()%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">

            <div class="modal-content">
                <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modifier l'offre</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="libelle" class="sr-only">Libellé *</label>
                            <input name="libelle" type="text" id="localisation" class="form-control" placeholder="Libellé" required autofocus value="<%=o.getLibelle()%>">
                            <div class="invalid-feedback">
                                Le nom de l'offre est obligatoire.
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer ">
                        <button type="submit" class="btn btn-success">Modifier l'offre</button>
                        <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        <input type="hidden" name="idOffre" value="<%=o.getId()%>">
                        <input type="hidden" name="action" value="modifierOffre">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%
        }
    %>

</main>


<jsp:include page="footer.jsp"/>