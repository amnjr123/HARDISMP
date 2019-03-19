<jsp:include page="header.jsp"/>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Utilisateurs HARDIS</h1>
    </div>
    <%@page import="GestionUtilisateur.ReferentLocal"%>
    <%@page import="GestionUtilisateur.Consultant"%>
    <%@page import="GestionUtilisateur.UtilisateurHardis"%>
    <%@page import="java.util.Collection"%>
    <jsp:useBean id="listeUtilisateursHardis" scope="request" class="java.util.Collection"></jsp:useBean>
    <% Collection<UtilisateurHardis> listeUH = listeUtilisateursHardis; %>
    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Utilisateurs HARDIS</h1>                
            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <div class="row ">
                    <div class="col-md-6 mb-3">
                        <div class="input-group mb-3">
                            <label for="nom" class="sr-only">Rechercher</label>
                            <input type="text" id="nom" class="form-control" placeholder="Nom, pr�nom, identifiant ou email" required autofocus>
                            <div class="input-group-prepend">
                                <a href="#" type="button" class="btn btn-primary"><i data-feather="search"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="btn-toolbar">
                            <button  class="btn btn-sm btn-success " data-toggle="modal" data-target="#addRO">
                                <span data-feather="user-plus"></span>
                                R�f�rent d'offre
                            </button>
                            <button  style="margin-left:1em" class="btn btn-sm btn-success " data-toggle="modal" data-target="#addPO">
                                <span data-feather="folder-plus"></span>
                                Porteur d'offre
                            </button>
                            <button style="margin-left:1em" class="btn btn-sm btn-success " data-toggle="modal" data-target="#addCO">
                                <span data-feather="user-plus"></span>
                                Consultant
                            </button>
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
                            <th scope="col">T�l�phone</th>
                            <th scope="col">Profil technique</th>
                            <!--th scope="col">Activit�</th-->
                            <th scope="col">Plafond de d�l�gation</th>
                            <th scope="col">Type</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (UtilisateurHardis uh : listeUH) {
                        %>
                        <tr>
                            <th scope="row"><%=(uh.getId())%></th>
                            <td><%=(uh.getNom())%> <%=(uh.getPrenom())%></td>
                            <td><%=(uh.getMail())%></td>
                            <td><%=(uh.getTelephone())%></td>
                            <td><%=(uh.getProfilTechnique())%></td>
                            <!--td>
                                <i data-feather="check" style="color :#34ce57"></i>
                                <i data-feather="x" style="color :#bd2130"></i>
                                <i data-feather="minus" style="color :#d39e00"></i>
                            </td-->
                            <%
                                if (uh.getDtype().equals("Consultant")) {
                                    Consultant c = (Consultant) uh;
                            %>
                            <td><%=(c.getPlafondDelegation())%> EUR</td>
                            <%
                            } else if (uh.getDtype().equals("ReferentLocal")) {
                                ReferentLocal rl = (ReferentLocal) uh;
                            %>
                            <td><%=(rl.getPlafondDelegation())%> EUR</td>
                            <%
                            } else {
                            %>
                            <td> - </td>
                            <%
                                }
                            %>

                            <td><%=(uh.getDtype())%></td>
                            <td><div class="dropdown">
                                    <button class="btn btn-primary btn-sm dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Action
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        <a class="dropdown-item" href="#"><i data-feather="edit-2"></i> Modifier</a>
                                        <a class="dropdown-item" href="#"><i data-feather="trash-2"></i> Supprimmer</a>
                                    </div>
                                </div>
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

        <div class="modal fade" id="addRO" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" method="POST" action="${pageContext.request.contextPath}/ServletAdministrateur">

                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Nouvel utilisateur HARDIS</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="firstName">Pr�nom *</label>
                            <input name="prenom" type="text" class="form-control" id="firstName" placeholder="Votre pr�nom" value="" required>
                            <div class="invalid-feedback">
                                Le pr�nom est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Nom *</label>
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
                            <label for="inputPassword">Mot de passe *</label>
                            <div class="input-group">
                                <input name="pw" type="password" id="inputPassword" class="form-control" onkeyup="verif(1)" placeholder="Mot de passe" required>
                                <div class="invalid-feedback" style="width: 100%;">
                                    Veuillez entrer un mot de passe.
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword">Verification Mot de passe *</label>
                            <div class="input-group">
                                <input name="pwV" type="password" id="inputPasswordVerif" class="form-control" onkeyup="verif(2)" placeholder="V�rification Mot de passe" required>
                                <div  class="invalid-feedback" style="width: 100%;">
                                    Veuillez R�p�ter le mot de passe.
                                </div>
                                <div id="result" style="width: 100%;">

                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="telephone">T�l�phone *</label>
                            <div class="input-group">
                                <input name="tel" type="tel" id="telephone" class="form-control" placeholder="(+33)6xxxxxxxxx ou 00336xxxxxxxxx ou 0xxxxxxxxx" pattern="^(?:0|\(?\+33\)?\s?|0033\s?)[1-79](?:[\.\-\s]?\d\d){4}$" required>
                                <div class="invalid-feedback" style="width: 100%;">
                                    Le num�ro de t�l�phone est obligatoire et doit �tre conforme
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <select name="profilTechnique" class="custom-select">
                                <option disabled selected>Profil Technique</option>
                                <option value="1">Administrateur</option>
                                <option value="2">Gestionnaire</option>
                                <option value="3">Visualisation</option>
                            </select>
                            <div class="invalid-feedback">
                                Le Profil Technique est obligatoire.
                            </div>
                        </div>
                        <div class="form-group">
                            <label for='siret' class='sr-only'>Plafond de d�l�guation</label>
                            <input name="plafond" type='text' id='Plafond de d�l�guation' class='form-control' placeholder='Plafond de d�l�guation' required autofocus>
                            <div class="invalid-feedback">
                                Le Profil m�tier est obligatoire.
                            </div>
                        </div>
                        <div class="input-group mb-3">
                            <select name="profilMetier" class="custom-select">
                                <option disabled selected>Profil m�tier</option>
                                <option value="1">R�f�rant local</option>
                                <option value="2">Porteur d'offre</option>
                                <option value="3">Consultant</option>
                            </select>
                            <div class="invalid-feedback">
                                Le Profil m�tier est obligatoire.
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success">Cr�er l'Utilisateur Hardis</button>
                        <button type="button" class="btn btn-warning " data-dismiss="modal">Fermer</button>
                        <input type="hidden" name="action" value="creerUtilisateurHardis">
                    </div>
                </form>
            </div>

        </div>
        </div>
</main>
<jsp:include page="footer.jsp"/>

