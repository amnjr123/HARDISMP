<%@page import="GestionUtilisateur.Client"%>
<%@page import="java.util.List"%>
<jsp:useBean id="listeClients" scope="request" class="java.util.List"></jsp:useBean>
<% List<Client> listeCli = listeClients; %>

<div class="card">
    <div class="card-header">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
            <h1 class="h2">Clients</h1>
            
        </div>
    </div>
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-md-6 mb-3">
                    <div class="input-group mb-3">

                        <label for="nom" class="sr-only">Rechercher</label>
                        <input type="text" id="nom" class="form-control" placeholder="Nom, prénom, identifiant ou email" required autofocus>
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
                        <th scope="col">Nom</th>
                        <th scope="col">Email</th>
                        <th scope="col">Téléphone</th>
                        <th scope="col">Entreprise</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for(Client c : listeCli){
                            %>
                    <tr>
                        <th scope="row"><%=(c.getId())%></th>
                        <td><%=(c.getNom())%> <%=(c.getPrenom())%></td>
                        <td><%=(c.getMail())%></td>
                        <td><%=(c.getTelephone())%></td>
                        
                        <td>
                            <% if(c.getEntreprise()!= null) { %>
                            <%=(c.getEntreprise().getNom())%>
                            <% } %>
                        </td>
                        <td>
                            <a href="#" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="edit-2"></i></a>
                            <a href="#" type="button" class="btn" style="background-color:transparent; color:red"><i data-feather="trash-2"></i></a>
                        </td>
                    </tr>
                    <%
                        }
                    %>

                </tbody>
            </table>
        </div>

    </div>
</div>