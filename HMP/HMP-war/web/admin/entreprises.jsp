<%@page import="GestionUtilisateur.Agence"%>
<%@page import="GestionUtilisateur.Entreprise"%>
<%@page import="java.util.Collection"%>
<jsp:include page="header.jsp"/>
<jsp:useBean id="listeEntreprises" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:useBean id="listeAgences" scope="request" class="java.util.Collection"></jsp:useBean>
<% 
    Collection<Entreprise> listEntreprises = listeEntreprises;
    Collection<Agence> listAgences = listeAgences;
%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Entreprises</h1>
    </div>

    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Entreprises</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <button  class="btn btn-sm btn-success" data-toggle="modal" data-target="#exampleModal">
                        <span data-feather="plus"></span>
                        Nouvelle entreprise
                    </button>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <div class="input-group mb-3">

                            <label for="siret" class="sr-only">SIRET *</label>
                            <input type="text" id="siret" class="form-control" placeholder="SIRET" required autofocus>
                            <div class="input-group-prepend">
                                <a href="#" type="button" class="btn btn-primary"><i data-feather="search"></i></a>
                            </div>

                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <div class="input-group mb-3">

                            <label for="nom" class="sr-only">Nom *</label>
                            <input type="text" id="nom" class="form-control" placeholder="Nom" required autofocus>
                            <div class="input-group-prepend">
                                <a href="#" type="button" class="btn btn-primary"><i data-feather="search"></i></a>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <caption>Liste des entreprises </caption>
                    <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">SIRET</th>
                            <th scope="col">Nom</th>
                            <th scope="col">Adresse</th>
                            <th scope="col">Agence</th>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            for (Entreprise e : listEntreprises) {
                        %>
                        <tr>
                            <th scope="row"><%=(e.getId())%></th>
                            <td><%=(e.getSiret())%></td>
                            <td><%=(e.getNom())%></td>
                            <td><%=(e.getAdresseFacturation())%></td>
                            <td>agence</td>
                            <td><a href="#" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="phone"></i></a>
                                <a href="#" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="edit-2"></i></a>
                                <a href="#" type="button" class="btn" style="background-color:transparent; color:red"><i data-feather="trash-2"></i></a></td>


                        </tr>
                        <%
                            }
                        %>
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
                        <h5 class="modal-title" id="exampleModalLabel">Nouvelle entreprise</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>
                            <label for="siret" class="sr-only">SIRET *</label>
                            <input type="text" name="siret" id="siret" class="form-control" placeholder="SIRET" required autofocus>
                        </p>
                        <p>
                            <label for="nom" class="sr-only">Nom *</label>
                            <input type="text" name="nom" id="nom" class="form-control" placeholder="Nom" required>
                        </p>
                        <p>
                            <label for="adresse" class="sr-only">Adresse de facturation *</label>
                            <input type="text" name="adresse" id="adresse" class="form-control" placeholder="Adresse" required>
                        </p>
                        <div class="form-group">
                            <label for="selectAgence"></label>
                            <select name="agence" class="form-control" id="selectAgence">
                                <option disabled selected>Choisir l'agence</option>
                                <%for(Agence a : listAgences){%>
                                    <option value="<%=a.getId()%>">Agence de&nbsp;<%=a.getLocalisation()%></option>
                                <%}%>                       
                            </select>
                        </div>
                    
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success">Créer l'entreprise</button>
                        <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        <input type="hidden" name="action" value="creerEntreprise">
                    </div>
                </form>
            </div>
        </div>

    </div>

</main>


<jsp:include page="footer.jsp"/>