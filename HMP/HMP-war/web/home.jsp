<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Il s'agit d'un Market Place, dot�e de fonctionnalit�s que vous pourrez utiliser dans vos projets futurs.">
        <meta name="author" content="M2SIA">
        <title>Bienvenue Work Place Hardis-Group</title>
        <!-- Theme CSS - Includes Bootstrap -->
        <link href="${pageContext.request.contextPath}/css/custom/creative.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/custom/home.css" rel="stylesheet">
        <link href="https://fr.snatchbot.me/sdk/webchat.css" rel="stylesheet" type="text/css">    
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
                            <a class="nav-link js-scroll-trigger" href="#loginModal"  data-toggle="modal">Se Connecter</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link js-scroll-trigger" href="#signUpModal" data-toggle="modal">S'inscrire</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link js-scroll-trigger" href="https://www.hardis-group.com/societe/hardis-group-ssii-esn-grenoble-lyon-paris-nantes-lille-dates-et-chiffres-cles">Qui sommes-nous ?</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link js-scroll-trigger" href="https://fr.snatchbot.me/webchat/?botID=49391&appID=pa6u0D3Ox5pHt3C1bO42">Contact</a>
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
                        <h1 class="text-uppercase text-white font-weight-bold">Demandez votre devis d�s aujourd'hui</h1>
                        <hr class="divider my-4">
                    </div>
                    <div class="col-lg-8 align-self-baseline">
                        <p class="text-white-75 font-weight-light mb-5">Il s'agit d'un Market Place, dot�e de fonctionnalit�s que vous pourrez utiliser dans vos projets futurs.</p>
                        <p class="text-white-75 font-weight-light mb-5">D�couvrez nos fonctionnalit�s pour parler avec nos �quipes et d�couvrir nos offres & services.</p>
                        <a class="btn btn-light btn-xl" href="#loginModal" data-toggle="modal">Se Connecter</a>
                        <a class="btn btn-secondary btn-xl" href="#signUpModal" data-toggle="modal">S'inscrire</a>
                    </div>
                    <% String error = (String) request.getAttribute("msgError");
                        if (request.getAttribute("msgError") != null) {%>
                    <div class="alert alert-danger alert-dismissible fade in show">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Attention !</strong>&nbsp;<%=error%>.
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
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">�</button>
                    </div>
                    <div class="modal-body">
                        <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" id="formLogin" novalidate="" method="POST" action="${pageContext.request.contextPath}/Servlet">
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
                                    Mot de passe oubli�
                                </button></div>
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
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">�</button>
                    </div>
                    <div class="modal-body">
                        <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" id="formLogin" novalidate="" method="POST" action="${pageContext.request.contextPath}/Servlet">
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
                            <div class="text-center">
                                <input name="rgpd" data-toggle="toggle" data-size="lg" type="checkbox" value="oui" required="true" data-onstyle="success" data-on="J'accepte" data-off="Je n'accepte pas" data-width="200" >
                                <label for="rgpd"> que mes donn�es � caract�re personnel soient collect�es et trait�es selon les conditions d�crites � la page&nbsp;<a href="https://www.hardis-group.com/respect-des-donnees-personnelles">"respect des donn�es personnelles"</a></label>
                                <div class="invalid-feedback" style="width: 100%;">
                                    Il est obligatoire d'accepter la RGPD.
                                </div>
                            </div>
                            <div class="form-group  text-center">
                                <input type="hidden" name="action" value="creerClient">
                                <button type="submit" class="btn btn-success btn-lg " id="btnLogin" >Cr�er mon compte</button>
                                <button style="margin-left: 1em" class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true">Annuler</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!--Modal mdp oubli�-->

        <div class="modal fade" id="pwOublieModal" tabindex="-1" role="dialog" aria-labelledby="pwOublieModal" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="pwOublieModal">Mot de passe oubli�</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        </button>
                    </div>
                    <div class="modal-body">
                        <label for="inputForgottenEmail" class="sr-only">Email</label>
                        <input type="email" id="inputForgottenEmail" class="form-control" placeholder="Adresse mail" required autofocus>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                        <button type="button" class="btn btn-primary">Envoi d'un mail de r�cup�ration du mot de passe</button>
                    </div>
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
                        result.innerHTML = "Ne correspond pas au mot de passe entr� !";
                    }
                } else {
                    result.style.color = "green";
                    result.innerHTML = "Identique au mot de passe entr�";
                }
            }
        </script>
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
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