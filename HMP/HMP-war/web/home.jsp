<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="GestionUtilisateur.UtilisateurHardis"%>
<%@page import="GestionUtilisateur.Client"%>
<%
    Client c = (Client) session.getAttribute("sessionClient");
    UtilisateurHardis u = (UtilisateurHardis) session.getAttribute("sessionHardis");
    String sessionUtilisateur = null;
    if (u != null) {
        sessionUtilisateur = "UtilisateurHardis";
    } else if (c != null) {
        sessionUtilisateur = "Client";
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta content="text/html; charset=UTF-8" http-equiv="Content-Type"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Il s'agit d'un Market Place, dotée de fonctionnalités que vous pourrez utiliser dans vos projets futurs.">
        <meta name="author" content="M2SIA">
        <title>Bienvenue Work Place Hardis-Group</title>
        <!-- Theme CSS - Includes Bootstrap -->
        <link href="${pageContext.request.contextPath}/css/custom/creative.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/custom/home.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/snatchbot.css" rel="stylesheet" type="text/css">    
        <link href="${pageContext.request.contextPath}/css/custom/form-validation.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap4-toggle.css" rel="stylesheet">
    </head>      
    <body class="gradiant-background">
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
            <div class="container">
                <a class="navbar-brand js-scroll-trigger" href="#page-top">Hardis-Group</a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto my-2 my-lg-0">         
                        <li class="nav-item">
                            <%if (sessionUtilisateur != null) {
                                    if (sessionUtilisateur.equalsIgnoreCase("UtilisateurHardis")) {
                                        out.print("<a class='nav-link' href='" + request.getContextPath() + "/hardisUser/index.jsp'>" + u.getNom() + " " + u.getPrenom() + "</a>");
                                    } else if (sessionUtilisateur.equalsIgnoreCase("Client")) {
                                        out.print("<a class='nav-link' href='" + request.getContextPath() + "/client/index.jsp'>" + c.getNom() + " " + c.getPrenom() + "</a>");
                                    }
                                } else {
                                    out.print("<a class='nav-link' href='#loginModal'  data-toggle='modal'>Se Connecter</a>");
                                    out.print("</li>");
                                    out.print("<li class='nav-item'>");
                                    out.print("<a class='nav-link' href='#signUpModal' data-toggle='modal'>S&apos;inscrire</a>");
                                    out.print("</li>");
                                }%>                                                            
                        <li class="nav-item">
                            <a class="nav-link" href="https://www.hardis-group.com/societe/hardis-group-ssii-esn-grenoble-lyon-paris-nantes-lille-dates-et-chiffres-cles">Qui sommes-nous ?</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="https://fr.snatchbot.me/webchat/?botID=49391&appID=pa6u0D3Ox5pHt3C1bO42">Contact</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Body -->
        <header class="masthead">
            <div class="container h-100">
                <div class="row h-100 align-items-center justify-content-center text-center">
                    <div class="col-lg-10 align-self-end">
                        <h1 class="text-uppercase text-white font-weight-bold">Demandez votre devis dès aujourd'hui</h1>
                        <hr class="divider my-4">
                    </div>
                    <div class="col-lg-8 align-self-baseline">
                        <p class="text-white-75 font-weight-light mb-5">Il s'agit d'un Market Place, dotée de fonctionnalités que vous pourrez utiliser dans vos projets futurs.</p>
                        <p class="text-white-75 font-weight-light mb-5">Découvrez nos fonctionnalités pour parler avec nos équipes et découvrir nos offres & services.</p>
                        <%if (sessionUtilisateur != null) {
                                if (sessionUtilisateur.equalsIgnoreCase("UtilisateurHardis")) {
                                    out.print("<a class='btn btn-light btn-xl' href='" + request.getContextPath() + "/hardisUser/index.jsp'>" + u.getNom() + " " + u.getPrenom() + "</a>");
                                } else if (sessionUtilisateur.equalsIgnoreCase("Client")) {
                                    out.print("<a class='btn btn-light btn-xl' href='" + request.getContextPath() + "/client/index.jsp'>" + c.getNom() + " " + c.getPrenom() + "</a>");
                                }
                            } else {
                                out.print("<a class='btn btn-light btn-xl' href='#loginModal' data-toggle='modal'>Se Connecter</a>");
                                out.print("<a class='btn btn-secondary btn-xl' href='#signUpModal' data-toggle='modal' style='margin:1em'>S&apos;inscrire</a>");
                            }%>

                    </div>
                    <% String error = (String) request.getAttribute("MsgError");
                        if (request.getAttribute("MsgError") != null) {%>
                    <div class="alert alert-danger alert-dismissible fade in show">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Attention !</strong>&nbsp;<%=error%>.
                        <%if (request.getAttribute("ErrorAdds") != null) {%>
                        <div class='text-center'>           
                            <button type='button' class='btn btn-link' data-toggle='modal' data-target='#pwOublieModal'>
                                Mot de passe oublié ?
                            </button>
                        </div>
                        <%}%>
                    </div>
                    <%}%>
                </div>
            </div>

        </header>

        <!--Modal Signin-->
        <div id="loginModal" class="modal fade" tabindex="-2" role="dialog" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3>Connexion</h3>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    </div>
                    <div class="modal-body">
                        <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" id="formLogin" method="POST" action="${pageContext.request.contextPath}/Servlet">
                            <div class="form-group">
                                <a href="${pageContext.request.contextPath}/signup.jsp" class="float-right"  data-toggle="modal" data-target="#signUpModal">Nouveau client ?</a>
                                <label for="email">Email</label>
                                <input name="email" type="email" class="form-control form-control-lg" id="email" required>
                                <div class="invalid-feedback" style="width: 100%;">
                                    Veuillez entrer une adresse mail valide.
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="pw">Mot de passe</label>
                                <input name="pw" type="password" class="form-control form-control-lg" id="pw" required>
                                <div class="invalid-feedback" style="width: 100%;">
                                    Veuillez entrer un mot de passe.
                                </div>
                            </div>
                            <div class="text-center">           
                                <button type="button" class="btn btn-link" data-toggle="modal" data-target="#pwOublieModal">
                                    Mot de passe oublié ?
                                </button>
                            </div>
                            <div class="form-group py-4 text-center">
                                <input type="hidden" name="action" value="login">
                                <button type="submit" class="btn btn-success btn-lg " id="btnLogin">Login</button>
                                <button style="margin-left: 1em" class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true">Annuler</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>        
        <!--Modal SignUp-->
        <div id="signUpModal" class="modal fade" tabindex="-2" role="dialog" aria-hidden="true">
            <div class="modal-dialog" role="document" style="max-width: 700px">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3>Inscription</h3>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    </div>
                    <div class="modal-body">
                        <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" id="formLogin" novalidate="" method="POST" action="${pageContext.request.contextPath}/Servlet">
                            <div class="form-group">
                                <label for="firstName">Prénom *</label>
                                <input name="prenom" type="text" class="form-control" id="firstName" placeholder="Votre prénom" value="" required>
                                <div class="invalid-feedback">
                                    Le prénom est obligatoire.
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
                                    <input name="pwV" type="password" id="inputPasswordVerif" class="form-control" onkeyup="verif(2)" placeholder="Vérification Mot de passe" required>
                                    <div  class="invalid-feedback" style="width: 100%;">
                                        Veuillez Répéter le mot de passe.
                                    </div>
                                    <div id="result" style="width: 100%;">

                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="telephone">Téléphone *</label>
                                <div class="input-group">
                                    <input name="tel" type="tel" id="telephone" class="form-control" placeholder="(+33)6xxxxxxxxx ou 00336xxxxxxxxx ou 0xxxxxxxxx" pattern="^(?:0|\(?\+33\)?\s?|0033\s?)[1-79](?:[\.\-\s]?\d\d){4}$" required>
                                    <div class="invalid-feedback" style="width: 100%;">
                                        Le numéro de téléphone est obligatoire et doit être conforme
                                    </div>
                                </div>
                            </div>
                            <div class="text-center">
                                <input name="rgpd" data-toggle="toggle" data-size="lg" type="checkbox" value="oui" required="true" data-onstyle="success" data-on="J'accepte" data-off="Je n'accepte pas" data-width="200" >
                                <label for="rgpd"> que mes données à caractère personnel soient collectées et traitées selon les conditions décrites à la page&nbsp;<a href="https://www.hardis-group.com/respect-des-donnees-personnelles">"respect des données personnelles"</a></label>
                                <div class="invalid-feedback" style="width: 100%;">
                                    Il est obligatoire d'accepter la RGPD.
                                </div>
                            </div>
                            <div class="form-group  text-center">
                                <input type="hidden" name="action" value="creerClient">
                                <button type="submit" class="btn btn-success btn-lg " id="btnLogin" >Créer mon compte</button>
                                <button style="margin-left: 1em" class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true">Annuler</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!--Modal mdp oublié-->

        <div class="modal fade" id="pwOublieModal" tabindex="-1" role="dialog" aria-labelledby="pwOublieModal" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form method="post" action="${pageContext.request.contextPath}/Servlet">
                        <input type="hidden" name="action" value="motDePasseOublie">
                        <div class="modal-header">
                            <h5 class="modal-title" id="pwOublieModal">Mot de passe oublié</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            </button>
                        </div>
                        <div class="modal-body">
                            <label for="inputForgottenEmail" class="sr-only">Email</label>
                            <input type="email" name="mail" id="inputForgottenEmail" class="form-control" placeholder="Adresse mail" required autofocus>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                            <button type="submit" class="btn btn-primary">Envoi d'un mail de récupération du mot de passe</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script>

            function verif(unOuDeux) {
                var val1 = document.getElementById("inputPassword").value,
                        val2 = document.getElementById("inputPasswordVerif").value,
                        result = document.getElementById("result");

                if (val1 != val2) {
                    result.style.color = "red";
                    if (unOuDeux == 1) {
                        result.innerHTML = "Veuillez retaper le mot de passe ici !";
                    } else {
                        result.innerHTML = "Ne correspond pas au mot de passe entré !";
                    }
                } else {
                    result.style.color = "green";
                    result.innerHTML = "Identique au mot de passe entré";
                }
            }
        </script>
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
        <script src="https://fr.snatchbot.me/sdk/webchat.min.js"></script>
        <script> Init('?botID=49391&appID=webchat', 600, 600, 'https://dvgpba5hywmpo.cloudfront.net/media/image/JIod5vjYEQFaz3yMV5FgTC2GG', 'bubble', '#00AFF0', 90, 90, 62.99999999999999, '', '1', '#FFFFFF', '#FFFFFF', 0); /* for authentication of its users, you can define your userID (add &userID={login}) */</script>
        <script src="${pageContext.request.contextPath}/js/bootstrap4-toggle.min.js" defer></script>
        <!-- Bootstrap core JavaScript
      ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script>
            // Example starter JavaScript for disabling form submissions if there are invalid fields
            (function () {
                'use strict';

                window.addEventListener('load', function () {
                    // Fetch all the forms we want to apply custom Bootstrap validation styles to
                    var forms = document.getElementsByClassName('needs-validation');

                    // Loop over them and prevent submission
                    var validation = Array.prototype.filter.call(forms, function (form) {
                        form.addEventListener('submit', function (event) {
                            if (form.checkValidity() === false) {
                                event.preventDefault();
                                event.stopPropagation();
                            }
                            form.classList.add('was-validated');
                        }, false);
                    });
                }, false);
            })();
        </script>    
        <script>
            /*Toggle deuxieme Modal*/
            $('#pwOublieModal').on('show.bs.modal', function (e) {
                $('#loginModal').toggleClass('show')
            })
            $('#pwOublieModal').on('hide.bs.modal', function (e) {
                $('#loginModal').toggleClass('show')
            }
            )
        </script>
    </body>
</html>