<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Il s'agit d'un Market Place, dotée de fonctionnalités que vous pourrez utiliser dans vos projets futurs.">
        <meta name="author" content="M2SIA">
        <title>Bienvenue Work Place Hardis-Group</title>
        <!-- Theme CSS - Includes Bootstrap -->
        <link href="${pageContext.request.contextPath}/css/custom/creative.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/custom/home.css" rel="stylesheet">
        <link href="https://fr.snatchbot.me/sdk/webchat.css" rel="stylesheet" type="text/css">    
        <link href="${pageContext.request.contextPath}/css/custom/form-validation.css" rel="stylesheet">


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
                        <h1 class="text-uppercase text-white font-weight-bold">Demandez votre devis dès aujourd'hui</h1>
                        <hr class="divider my-4">
                    </div>
                    <div class="col-lg-8 align-self-baseline">
                        <p class="text-white-75 font-weight-light mb-5">Il s'agit d'un Market Place, dotée de fonctionnalités que vous pourrez utiliser dans vos projets futurs.</p>
                        <p class="text-white-75 font-weight-light mb-5">Découvrez nos fonctionnalités pour parler avec nos équipes et découvrir nos offres & services.</p>
                        <a class="btn btn-primary btn-xl" href="#loginModal" data-toggle="modal">Se Connecter</a>
                        <a class="btn btn-secondary btn-xl" href="#signUpModal" data-toggle="modal">S'inscrire</a>
                    </div>
                </div>
            </div>
        </header>

        <!--Modal Signin-->
        <div id="loginModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3>Connexion</h3>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    </div>
                    <div class="modal-body">
                        <form class="needs-validation" novalidate class="form" role="form" autocomplete="off" id="formLogin" novalidate="" method="POST" action="${pageContext.request.contextPath}/Servlet">
                            <div class="form-group">
                                <a href="${pageContext.request.contextPath}/signup.jsp" class="float-right">Nouveau client ?</a>
                                <label for="email">Email</label>
                                <input name="email" type="mail" class="form-control form-control-lg" id="email" required>
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
        <div id="signUpModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
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
                                    <input name="pw" type="password" id="inputPassword" class="form-control" placeholder="Mot de passe" required>
                                    <div class="invalid-feedback" style="width: 100%;">
                                        Veuillez entrer un mot de passe.
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword">Verification Mot de passe *</label>
                                <div class="input-group">
                                    <input name="pwV" type="password" id="inputPasswordVerif" class="form-control" onkeyup="verif()" placeholder="Vérification Mot de passe" required>
                                    <div id="result" class="invalid-feedback" style="width: 100%;">
                                        Veuillez Répéter le mot de passe.
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="telephone">Téléphone *</label>
                                <div class="input-group">
                                    <input name="tel" type="tel" id="telephone" class="form-control" placeholder="Numéro de téléphone" required>
                                    <div class="invalid-feedback" style="width: 100%;">
                                        Le numéro de téléphone est obligatoire.
                                    </div>
                                </div>
                            </div>
                            <div class="form-group py-4 text-center">
                                <input type="hidden" name="action" value="creerClient">
                                <button type="submit" class="btn btn-success btn-lg " id="btnLogin" >Créer mon compte</button>
                                <button style="margin-left: 1em" class="btn btn-outline-secondary btn-lg" data-dismiss="modal" aria-hidden="true">Annuler</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function verif() {
                var val1 = document.getElementById("inputPassword").value,
                        val2 = document.getElementById("inputPasswordVerif").value,
                        result = document.getElementById("result");

                if (val1 != val2) {
                    result.innerHTML = "Ne correspond pas au mot de passe entré !";
                    result.style.color = "red";
                } else {
                    result.innerHTML = "Valide !";
                    result.style.color = "green";

                }
            }
        </script>
        <jsp:include page="footer.jsp"/>