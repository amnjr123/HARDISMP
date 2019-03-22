<%@page import="GestionUtilisateur.Agence"%>
<%@page import="java.util.Collection"%>
<jsp:useBean id="listAgences" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:include page="header.jsp"/>
<%Collection<Agence> list = listAgences;%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Agences</h1>
    </div>

    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Agences</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <button  class="btn btn-sm btn-success" data-toggle="modal" data-target="#exampleModal">
                        <span data-feather="plus"></span>
                        Nouvelle agence
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
                    <caption>Liste des Agences</caption>
                    <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Localisation</th>
                            <th scope="col">Adresse</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  int i = 0;
                            for (Agence a : list) {%>                                      
                        <tr>
                            <td><%=a.getId()%></td>
                            <td><%=a.getLocalisation()%></td>
                            <td><%=a.getAdresse()%></td>
                            <td>
                                <a href=""  type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="edit-2"></i></a>
                                <a href="" type="button" class="btn" style="background-color:transparent; color:red"><i data-feather="trash-2"></i></a>
                            </td>

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
                        <h5 class="modal-title" id="exampleModalLabel">Nouvelle Agence</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>
                            <label for="localisation" class="sr-only">Localisation/ Ville *</label>
                            <input name="localisation" type="text" id="localisation" class="form-control" placeholder="Localisation/ Ville" required autofocus>
                        </p>
                        <p>
                            <label for="adresse" class="sr-only">Adresse *</label>
                            <input name="adresse" type="text" id="adresse" class="form-control" placeholder="Adresse complete" required autofocus>
                        </p>
                    </div>
                    <div class="modal-footer ">
                        <button type="submit" class="btn btn-success">Créer l'agence</button>
                        <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        <input type="hidden" name="action" value="creerAgence">
                    </div>
                </form>
            </div>
        </div>

    </div>

</main>


<jsp:include page="footer.jsp"/>