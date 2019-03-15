<jsp:include page="header.jsp"/>
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
                </div
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
                <hr class="mb-4">
                <input type="hidden" name="action" value="creerClient">
                <button class="btn btn-primary btn-lg btn-block" type="submit">Créez votre compte</button>
            </form>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>