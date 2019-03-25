<%@page import="GestionUtilisateur.DemandeRattachement"%>
<%@page import="GestionUtilisateur.Interlocuteur"%>
<%@page import="GestionUtilisateur.Client"%>
<%@page import="GestionUtilisateur.Agence"%>
<%@page import="java.util.Collection"%>
<jsp:include page="header.jsp"/>
<jsp:useBean id="listeAgences" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:useBean id="listeInterlocuteurs" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:useBean id="demandesRattachement" scope="request" class="java.util.Collection"></jsp:useBean>
<%
    Client c = (Client) session.getAttribute("sessionClient");
    Collection<Agence> listAgences = listeAgences;
    Collection<Interlocuteur> interlocuteurs = listeInterlocuteurs;
    Collection<DemandeRattachement> demandes = demandesRattachement;
%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Mon profil</h1>
    </div>
</main>


<div class="container">
    <%
        if ((c.getDemandeCreationEntreprise() != null || c.getDemandeRattachement() != null) && c.getEntreprise() == null) {
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
        if (c.getEntreprise() == null && c.getDemandeCreationEntreprise() == null && c.getDemandeRattachement() == null) {
    %>
    <div class="card text-white bg-danger mb-3">
        <div class="card-header"><h4>Mon entreprise</h4></div>
        <div class="card-body">

            <h5 class="card-title p-3 mb-2 bg-danger text-white">Vous n'êtes rattaché à aucune enreprise<br> afin de pouvoir profiter de l'ensemble des fonctionnalités d'Hardis Market place, nous vous prions de compléter les informations suivantes:</h5>
            <form method="post" action="${pageContext.request.contextPath}/ServletClient" enctype="multipart/form-data">
                <input type="hidden" name="action" value="creerDemandeEntreprise">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="siret">SIRET *</label>
                        <div class="input-group">
                            <input name="siret" type="text" class="form-control" id="firstName" placeholder="SIRET de votre entreprise" value="" required>
                            <div class="invalid-feedback">
                                Le SIRET Est obligatoire.
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="nom">Raison sociale</label>
                        <div class="input-group">
                            <input name="nom" type="text" class="form-control" id="raisonSociale" placeholder="Raison sociale de votre entreprise" value="" required>
                        </div>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="adresse">Adresse</label>
                    <input name="adresse" type="text" class="form-control" id="address" placeholder="Adresse de facturation" required>
                </div>
                <div class="form-group">
                    <label for="agence"></label>
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
                                <input id="input-2" name="files[]" type="file" class="file" multiple data-show-upload="true" data-show-caption="true">
                            </span>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-warning">Demande de rattachement</button>
            </form>
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
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h4><%=c.getEntreprise().getNom()%> : Agence <%=c.getEntreprise().getAgence().getLocalisation()%></h4>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <button  class="btn btn-sm btn-success" data-toggle="modal" data-target="#interlocuteursModal">
                        <span data-feather="plus"></span>
                        Interlocuteur
                    </button>
                </div>
            </div>

        </div>
        <div class="card-body">
            <h4 class="card-header">Interlocuteurs</h4>
            <div class="table-responsive">
                <table class="table">
                    <caption>Interlocuteurs de votre entreprise </caption>
                    <thead>
                        <tr>
                            <th>id</th>
                            <th>Nom</th>
                            <th>Email</th>
                            <th>Téléphone</th>
                            <th>Fonction</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            if (interlocuteurs != null && !interlocuteurs.isEmpty()) {
                                for (Interlocuteur i : interlocuteurs) {
                        %>
                        <tr>
                            <th scope="row"><%=(i.getId())%></th>
                            <td><%=(i.getNom())%> <%=(i.getPrenom())%></td>
                            <td><%=(i.getMail())%></td>
                            <td><%=(i.getTelephone())%></td>
                            <td><%=(i.getFonction())%></td>
                            <td>
                                <a href="#" data-toggle="modal" data-target="#modificationInterlocuteur<%=(i.getId())%>" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="edit-2"></i></a>
                                <a href="${pageContext.request.contextPath}/ServletClient?action=supprimerInterlocuteur&idInterlocuteur=<%=i.getId()%>" type="button" class="btn" style="background-color:transparent; color:red"><i data-feather="trash-2"></i></a>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>


                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <%
        if (c.getAdministrateur()) {
    %>                   

    <div class="card text-white bg-dark mb-3">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h4>Demandes de rattachement à mon entreprise</h4>
            </div>

        </div>
        <div class="card-body">
            <h4 class="card-header">Demandes</h4>
            <div class="table-responsive">
                <table class="table">
                    <caption>Demandes de rattachement</caption>
                    <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Nom</th>
                            <th scope="col">email</th>
                            <th scope="col">telephone</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <%
                                for (DemandeRattachement d : demandes) {
                        %>
                        <tr>
                            <th scope="row"><%=(d.getId())%></th>
                            <td><%=(d.getClient().getNom())%> <%=(d.getClient().getPrenom())%></td>
                            <td><%=(d.getClient().getMail())%></td>
                            <td><%=(d.getClient().getTelephone())%></td>
                                                        <td>
                                <a href="#" type="button" data-toggle="modal" data-target="#modalDemande<%=(d.getId())%>" class="btn" style="background-color:transparent; color:#27ae60"><i data-feather="check-circle"></i></a>
                                <a href="#" type="button" class="btn" style="background-color:transparent; color:red"><i data-feather="x"></i></a>
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
    <%
            }
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

    <div class="modal fade" id="interlocuteursModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ajouter un interlocuteur</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    </button>
                </div>
                <%
                    if (c.getEntreprise() != null) {
                %>
                <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" id="formInterlocuteur" novalidate="" method="POST" action="${pageContext.request.contextPath}/ServletClient">
                    <input type="hidden" name="action" value="creerInterlocuteur">
                    <input type="hidden" name="idEntreprise" value="<%=(c.getEntreprise().getId())%>">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="prenom">Prénom *</label>
                            <input name="prenom" type="text" class="form-control" id="firstName" placeholder="Votre prénom" value="" required>
                            <div class="invalid-feedback">
                                Le prénom est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nom">Nom *</label>
                            <input name="nom" type="text" class="form-control" id="lastName" placeholder="Votre nom" value="" required>
                            <div class="invalid-feedback">
                                Le nom est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email">Email *</label>
                            <div class="input-group">
                                <input name="email" type="email" class="form-control" id="email" placeholder="vous@votreentreprise.com" required>
                                <div class="invalid-feedback" style="width: 100%;">
                                    Veuillez entrer une adresse mail valide.
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="telephone">Téléphone *</label>
                            <div class="input-group">
                                <input name="telephone" type="tel" id="telephone" class="form-control" placeholder="(+33)6xxxxxxxxx ou 00336xxxxxxxxx ou 0xxxxxxxxx" pattern="^(?:0|\(?\+33\)?\s?|0033\s?)[1-79](?:[\.\-\s]?\d\d){4}$" required>
                                <div class="invalid-feedback" style="width: 100%;">
                                    Le numéro de téléphone est obligatoire et doit être conforme
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="fonction">Fonction *</label>
                            <input name="fonction" type="text" class="form-control" id="lastName" placeholder="Fonction de l'interlocuteur" value="" required>
                            <div class="invalid-feedback">
                                La fonction est obligatoire.
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                        <button type="submit" class="btn btn-primary">Ajouter l'interlocuteur</button>
                    </div>
                </form>
                <%
                    }
                %>
            </div>
        </div>
    </div>
    <%
    for (Interlocuteur i : interlocuteurs) {
    %>
    <div class="modal fade" id="modificationInterlocuteur<%=(i.getId())%>" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modifier l'interlocuteur</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    </button>
                </div>
                <%
                    if (c.getEntreprise() != null) {
                %>
                <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" id="formInterlocuteur" novalidate="" method="POST" action="${pageContext.request.contextPath}/ServletClient">
                    <input type="hidden" name="action" value="modifierInterlocuteur">
                    <input type="hidden" name="idInterlocuteur" value="<%=(i.getId())%>">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="prenom">Prénom *</label>
                            <input name="prenom" type="text" class="form-control" id="firstName" placeholder="Votre prénom" value="<%=(i.getPrenom())%>" required>
                            <div class="invalid-feedback">
                                Le prénom est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nom">Nom *</label>
                            <input name="nom" type="text" class="form-control" id="lastName" placeholder="Votre nom" value="<%=(i.getNom())%>" required>
                            <div class="invalid-feedback">
                                Le nom est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email">Email *</label>
                            <div class="input-group">
                                <input name="email" type="email" class="form-control" id="email" placeholder="vous@votreentreprise.com" required value="<%=(i.getMail())%>">
                                <div class="invalid-feedback" style="width: 100%;">
                                    Veuillez entrer une adresse mail valide.
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="telephone">Téléphone *</label>
                            <div class="input-group">
                                <input name="telephone" type="tel" id="telephone" class="form-control" placeholder="(+33)6xxxxxxxxx ou 00336xxxxxxxxx ou 0xxxxxxxxx" pattern="^(?:0|\(?\+33\)?\s?|0033\s?)[1-79](?:[\.\-\s]?\d\d){4}$" required value="<%=(i.getTelephone())%>">
                                <div class="invalid-feedback" style="width: 100%;">
                                    Le numéro de téléphone est obligatoire et doit être conforme
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="fonction">Fonction *</label>
                            <input name="fonction" type="text" class="form-control" id="lastName" placeholder="Fonction de l'interlocuteur" value="<%=(i.getFonction())%>" required>
                            <div class="invalid-feedback">
                                La fonction est obligatoire.
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                        <button type="submit" class="btn btn-primary">Valider</button>
                    </div>
                </form>
                <%
                    }
                %>
            </div>
        </div>
    </div>
<%}%>
            
<%
for (DemandeRattachement d : demandes) {
%>
<div class="modal fade" id="modalDemande<%=(d.getId())%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletClient">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Demande n° <%=(d.getId())%></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    </button>
                </div>
                <div class="modal-body">
                    <h4 class="modal-title" id="exampleModalLabel">Demande n° <%=(d.getId())%> de rattachement du client <%=(d.getClient().getId()+" : "+d.getClient().getNom()+" "+d.getClient().getPrenom())%> à l'entreprise <%=(d.getEntreprise().getNom())%></h4>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Valider la demande</button>
                    <input type="hidden" name="action" value="validerDemandeRattachementEntreprise">
                    <input type="hidden" name="idDemande" value="<%=(d.getId())%>">
                </div>
            </form>
        </div>

    </div>
</div>
<%
}
%>






</div>
<jsp:include page="footer.jsp"/>