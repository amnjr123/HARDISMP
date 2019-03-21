<%@page import="GestionUtilisateur.UtilisateurHardis"%>
<jsp:include page="header.jsp"/>
<%
    UtilisateurHardis uh = (UtilisateurHardis) session.getAttribute("sessionHardis");
%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Mon profil</h1>
    </div>
</main>


<div class="container">
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
                    <form method="post" action="${pageContext.request.contextPath}/ServletUtilisateurHardis">
                        <input type="hidden" name="action" value="modifierPrenomClient">
                        <label for="nouveauPrenom">Pr�nom</label>
                        <div class="input-group">
                            <input name="nouveauPrenom" type="text" class="form-control" id="nouveauPrenom" placeholder="Votre pr�nom" value="<%=(uh.getPrenom())%>" required>
                            <div class="input-group-prepend">
                                <button type="submit" class="btn btn-primary"><i data-feather="check"></i></button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-6 mb-3">
                    <form method="post" action="${pageContext.request.contextPath}/ServletUtilisateurHardis">
                        <input type="hidden" name="action" value="modifierNomClient">
                        <label for="nouveauNom">Nom</label>
                        <div class="input-group">
                            <input name="nouveauNom" type="text" class="form-control" id="nouveauNom" placeholder="Votre nom" value="<%=(uh.getNom())%>" required>
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
                    <input name="email" type="email" class="form-control" id="email" placeholder="vous@votreentreprise.com" value="<%=(uh.getMail())%>" disabled>
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
                <form method="post" action="${pageContext.request.contextPath}/ServletUtilisateurHardis">
                    <input type="hidden" name="action" value="modifierTelephoneClient">
                    <label for="nouveauTelephone">T�l�phone</label>
                    <div class="input-group">
                        <input name="nouveauTelephone" type="tel" id="nouveauTelephone" class="form-control" placeholder="Num�ro de t�l�phone" value="<%=(uh.getTelephone())%>" required>
                        <div class="input-group-prepend">
                            <button type="submit" class="btn btn-primary"><i data-feather="check"></i></button>
                        </div>
                    </div>
                </form>
            </div>

            <div class="mb-3">
                <label">Profil technique</label>
                <input type="text" class="form-control" value="<%=(uh.getProfilTechnique())%>" disabled>
            </div>
            
            <div class="mb-3">
                <label>Type </label>
                <input type="text" class="form-control" value="<%=(uh.getDtype())%>" disabled>
            </div>
            
            <%
            if(uh.getDtype().equals("referentLocal") || uh.getDtype().equals("Consultant")){
            %>
            <div class="mb-3">
                <label>Plafond de d�l�gation</label>
                <input type="text" class="form-control" value="<%=(uh.getDtype())%>" disabled>
            </div>
            <%
            }
            %>
        </div>
    </div>

    <!-- MODALS-->

    <div class="modal fade" id="changerMDPModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form class="needs-validation" method="post" action="${pageContext.request.contextPath}/ServletUtilisateurHardis">
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