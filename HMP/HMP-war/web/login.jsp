<jsp:include page="header.jsp"/>
<link href="${pageContext.request.contextPath}/css/custom/signin.css" rel="stylesheet">
</head>
<body class="text-center">
    <form class="form-signin" method="post" action="${pageContext.request.contextPath}/Servlet">
        <img class="mb-4" src="${pageContext.request.contextPath}/assets/baseline-person-24px.svg" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal">Connectez vous</h1>
        <label for="inputEmail" class="sr-only">Email</label>
        <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Adresse mail" required autofocus>
        <label for="inputPassword" class="sr-only">Mot de passe</label>
        <input type="password" name="pw" id="inputPassword" class="form-control" placeholder="mot de passe" required>
        <input type="hidden" name="action" value="login">
        <p>
            <input class="btn btn-lg btn-primary btn-block" type="submit" value="Se connecter">
        </p>
        
        <p><a href="./signup.jsp">Créer un compte</a></p>

        <button type="button" class="btn btn-link" data-toggle="modal" data-target="#pwOublieModal">
            Mot de passe oublié
        </button>
    </form>
        <div class="modal fade" id="pwOublieModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Mot de passe oublié</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        </button>
                    </div>
                    <div class="modal-body">
                        <label for="inputForgottenEmail" class="sr-only">Email</label>
                        <input type="email" id="inputForgottenEmail" class="form-control" placeholder="Adresse mail" required autofocus>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                        <button type="button" class="btn btn-primary">Envoi d'un mail de récupération du mot de passe</button>
                    </div>
                </div>
            </div>
        </div>    
    <jsp:include page="footer.jsp"/>