<%@page import="GestionUtilisateur.Client"%>
<%@page import="GestionUtilisateur.Agence"%>
<%@page import="java.util.Collection"%>
<jsp:include page="header.jsp"/>
<jsp:useBean id="listeAgences" scope="request" class="java.util.Collection"></jsp:useBean>
<%
    Client c = (Client) session.getAttribute("sessionClient");
    Collection<Agence> listAgences = listeAgences;
%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Mon profil</h1>
    </div>
</main>




<div class="container">
    <%
        if (c.getDemandeCreationEntreprise() != null && c.getEntreprise() == null) {
    %>
    <div class="card text-white bg-warning mb-3">
        <div class="card-header"><h4>Mon entreprise</h4></div>
        <div class="card-body">
            <h4 class="card-title p-3 mb-2">L'attachement à votre entreprise est en cours de traitement</h4>
        </div>
    </div>
    <%
        }
    %>
    <%
        if (c.getEntreprise() == null && c.getDemandeCreationEntreprise() == null) {
    %>
    <div class="card text-white bg-danger mb-3">
        <div class="card-header"><h4>Mon entreprise</h4></div>
        <div class="card-body">
            <h5 class="card-title p-3 mb-2 bg-danger text-white">Vous n'êtes rattaché à aucune enreprise<br> afin de pouvoir profiter de l'ensemble des fonctionnalités d'Hardis Market place, nous vous prions de compléter les informations suivantes:</h5>
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="firstName">SIRET *</label>
                    <div class="input-group">
                        <input name="siret" type="text" class="form-control" id="firstName" placeholder="SIRET de votre entreprise" value="" required>
                        <div class="invalid-feedback">
                            Le SIRET Est obligatoire.
                        </div>
                    </div>
                </div>
                <div class="col-md-6 mb-3">
                    <label for="raisonSociale">Raison sociale</label>
                    <div class="input-group">
                        <input name="nom" type="text" class="form-control" id="raisonSociale" placeholder="Raison sociale de votre entreprise" value="" required>
                    </div>
                </div>
            </div>

            <div class="mb-3">
                <label for="address">Adresse</label>
                <input name="adresse" type="text" class="form-control" id="address" placeholder="Adresse de facturation" required>
            </div>
            <div class="form-group">
                <label for="selectAgence"></label>
                <select name="agence" class="form-control" id="selectAgence">
                    <option disabled selected>Choisir l'agence</option>
                    <%for (Agence a : listAgences) {%>
                    <option value="<%=a.getId()%>">Agence de&nbsp;<%=a.getLocalisation()%></option>
                    <%}%>                       
                </select>
            </div>

            <div class="mb-3">
                <div class="form-group">
                    <label class="col-sm-3 control-label">
                        Attachment(s)
                        (Attach multiple files.)
                    </label>
                    <div class="col-sm-9">
                        <span class="btn btn-default btn-file">
                            <input id="input-2" name="input2[]" type="file" class="file" multiple data-show-upload="true" data-show-caption="true">
                        </span>
                    </div>
                </div>
            </div>
            <a href="#" class="btn btn-warning">Demande de rattachement</a>
        </div>
    </div>
    <%
        }
    %>

    <div class="card text-white bg-dark mb-3">
        <% String error = (String) request.getAttribute("msgError");
            if (request.getAttribute("msgError") != null) {%>
        <div class="alert alert-danger alert-dismissible fade in show">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attention !</strong>&nbsp;<%=error%>.
        </div>
        <%}%>

        <% String success = (String) request.getAttribute("msgSuccess");
            if (request.getAttribute("msgSuccess") != null) {%>
        <div class="alert alert-success alert-dismissible fade in show">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <%=success%>.
        </div>
        <%}%>
        <div class="card-header"><h4>Mes informations personnelles</h4></div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-6 mb-3">
                    <form method="post" action="${pageContext.request.contextPath}/ServletClient">
                        <input type="hidden" name="action" value="modifierPrenomClient">
                        <label for="nouveauPrenom">Prénom</label>
                        <div class="input-group">
                            <input name="nouveauPrenom" type="text" class="form-control" id="nouveauPrenom" placeholder="Votre prï¿½nom" value="<%=(c.getPrenom())%>" required>
                            <div class="input-group-prepend">
                                <button type="submit" class="btn btn-primary"><i data-feather="check"></i></button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-6 mb-3">
                    <form method="post" action="${pageContext.request.contextPath}/ServletClient">
                        <input type="hidden" name="action" value="modifierNomClient">
                        <label for="nouveauNom">Nom</label>
                        <div class="input-group">
                            <input name="nouveauNom" type="text" class="form-control" id="nouveauNom" placeholder="Votre nom" value="<%=(c.getNom())%>" required>
                            <div class="input-group-prepend">
                                <button type="submit" class="btn btn-primary"><i data-feather="check"></i></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>


            <div class="mb-3">
                <label for="email">Email</label>
                <div class="input-group">
                    <input name="email" type="email" class="form-control" id="email" placeholder="vous@votreentreprise.com" value="<%=(c.getMail())%>" disabled>
                    <div class="input-group-prepend">
                        <a href="#" type="button" class="btn btn-primary"data-toggle="modal" data-target="#changerEmailModal"><i data-feather="edit"></i></a>
                    </div>
                </div>
            </div>

            <div class="mb-3">
                <label for="inputPassword">Mot de passe</label>
                <div class="input-group">
                    <input type="password" class="form-control" value="Mot de passe" disabled>
                    <div class="input-group-prepend">
                        <a href="#" type="button" class="btn btn-primary" data-toggle="modal" data-target="#changerMDPModal"><i data-feather="edit"></i></a>
                    </div>
                </div>
            </div>

            <div class="mb-3">
                <form method="post" action="${pageContext.request.contextPath}/ServletClient">
                    <input type="hidden" name="action" value="modifierTelephoneClient">
                    <label for="nouveauTelephone">Téléphone</label>
                    <div class="input-group">
                        <input name="nouveauTelephone" type="tel" id="nouveauTelephone" class="form-control" placeholder="Numï¿½ro de tï¿½lï¿½phone" value="<%=(c.getTelephone())%>" required>
                        <div class="input-group-prepend">
                            <button type="submit" class="btn btn-primary"><i data-feather="check"></i></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <%
        if (c.getEntreprise() != null) {
    %>
    <div class="card text-white bg-dark mb-3">
        <div class="card-header"><h4><%=c.getEntreprise().getNom()%> : Agence <%=c.getEntreprise().getAgence().getLocalisation()%></h4></div>
        <div class="card-body">
            <h4 class="card-header">Interlocuteurs</h4>
            <div class="table-responsive">
                <table class="table">
                    <caption>Interlocuteurs de votre entreprises </caption>
                    <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Nom</th>
                            <th scope="col">Email</th>
                            <th scope="col">Téléphone</th>
                            <th scope="col">Fonction</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>NEJJARI Amine</td>
                            <td>amnjr123@gmail.com</td>
                            <td>06 24 31 88 57</td>
                            <td>Gestionnaire RH</td>
                        </tr>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <%
        }
    %>




    <!-- MODALS-->

    <div class="modal fade" id="changerMDPModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form class="needs-validation" method="post" action="${pageContext.request.contextPath}/ServletClient">
                    <input type="hidden" name="action" value="modifierMDPClient">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Changer le mot de passe</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>
                            <input name="ancienMDP" type="password" class="form-control" placeholder="Ancien mot de passe" required> 
                        </p>
                        <p>
                            <input name="nouveauMDP" type="password" class="form-control" placeholder="Nouveau mot de passe" required> 
                        </p>


                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                        <button type="submit" class="btn btn-primary">Valider</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="changerEmailModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Changer votre adresse mail</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    </button>
                </div>
                <div class="modal-body">
                    <input name="email" type="email" class="form-control" id="email" placeholder="vous@votreentreprise.com" required>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                    <button type="button" class="btn btn-primary">Envoyer un mail de validation</button>
                </div>
            </div>
        </div>
    </div>





</div>
<jsp:include page="footer.jsp"/>