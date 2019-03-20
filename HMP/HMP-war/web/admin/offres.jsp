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
                    <button  class="btn btn-sm btn-success" data-toggle="modal" data-target="#exampleModal">
                        <span data-feather="plus"></span>
                        Nouvelle offre
                    </button>
                </div>
            </div>
        </div>

        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Libellé</th>
                            <th scope="col">Services</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  int i = 0;
                            for (Offre o : list) {%>                                      
                        <tr>
                            <td><%=o.getId()%></td>
                            <td><%=o.getLibelle()%></td>
                            <td><a href="${pageContext.request.contextPath}/ServletAdministrateur?action=services&id=<%=o.getId()%>" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="list"></i></a></td>
                            
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
                            <label for="libelle" class="sr-only">Libellé *</label>
                            <input name="libelle" type="text" id="localisation" class="form-control" placeholder="Libellé" required autofocus>
                        </p>
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

</main>


<jsp:include page="footer.jsp"/>