<jsp:include page="header.jsp"/>
<link href="${pageContext.request.contextPath}/css/custom/form-validation.css" rel="stylesheet">
</head>

<body class="bg-light">

    <div class="container">
        <div class="py-5 text-center">
            <img class="d-block mx-auto mb-4" src="${pageContext.request.contextPath}/assets/baseline-person-24px.svg" alt="" width="72" height="72">
            <h2>Créez un compte</h2>
        </div>

        <div>
            <form class="needs-validation" novalidate method="post" action="${pageContext.request.contextPath}/Servlet">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="firstName">Prénom *</label>
                        <input name="prenom" type="text" class="form-control" id="firstName" placeholder="Votre prénom" value="" required>
                        <div class="invalid-feedback">
                            Le prénom est obligatoire.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="lastName">Nom *</label>
                        <input name="nom" type="text" class="form-control" id="lastName" placeholder="Votre nom" value="" required>
                        <div class="invalid-feedback">
                            Le nom est obligatoire.
                        </div>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="email">Email *</label>
                    <div class="input-group">
                        <input name="email" type="email" class="form-control" id="email" placeholder="vous@votreentreprise.com" required>
                        <div class="invalid-feedback" style="width: 100%;">
                            Veuillez entrer une adresse mail valide.
                        </div>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="inputPassword">Mot de passe *</label>
                    <div class="input-group">
                        <input name="pw" type="password" id="inputPassword" class="form-control" placeholder="Mot de passe" required>
                        <div class="invalid-feedback" style="width: 100%;">
                            Veuillez entrer un mot de passe.
                        </div>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="inputPassword">Téléphone *</label>
                    <div class="input-group">
                        <input name="tel" type="number" id="telephone" class="form-control" placeholder="Numéro de téléphone" required>
                        <div class="invalid-feedback" style="width: 100%;">
                            Le numéro de téléphone est obligatoire.
                        </div>
                    </div>
                </div>
                <%--
                            <div class="mb-3">
                              <label for="address">SIRET de votre entreprise *</label>
                              <input name="siret" type="text" class="form-control" id="address" placeholder="##############" required>
                              <div class="invalid-feedback">
                                Le SIRET Est obligatoire.
                              </div>
                            </div>

            <div class="mb-3">
              <label for="address">Code d'identification d'entreprise *</label>
              <input name="pwEntreprise" type="text" class="form-control" id="address" placeholder="Code de votre entreprise" required>
              <div class="invalid-feedback">
                Le code Est obligatoire.
              </div>
            </div>
                --%>
                <hr class="mb-4">
                <input type="hidden" name="action" value="creerClient">
                <button class="btn btn-primary btn-lg btn-block" type="submit">Créez votre compte</button>
            </form>
        </div>

    </div>

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
    <jsp:include page="footer.jsp"/>