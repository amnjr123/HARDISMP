<div class="container">
    <div>
        <form class="needs-validation" novalidate>
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="firstName">Prénom</label>
                    <div class="input-group">
                        <input name="prenom" type="text" class="form-control" id="firstName" placeholder="Votre prénom" value="" required>
                        <div class="input-group-prepend">
                            <a href="#" type="button" class="btn btn-primary"><i data-feather="check"></i></a>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 mb-3">
                    <label for="lastName">Nom</label>
                    <div class="input-group">
                        <input name="nom" type="text" class="form-control" id="lastName" placeholder="Votre nom" value="" required>
                        <div class="input-group-prepend">
                            <a href="#" type="button" class="btn btn-primary"><i data-feather="check"></i></a>
                        </div>
                    </div>
                </div>
            </div>


            <div class="mb-3">
                <label for="email">Email</label>
                <div class="input-group">
                    <input name="email" type="email" class="form-control" id="email" placeholder="vous@votreentreprise.com" disabled>
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
                <label for="inputPassword">Téléphone</label>
                <div class="input-group">
                    <input name="tel" type="tel" id="telephone" class="form-control" placeholder="Numéro de téléphone" required>
                    <div class="input-group-prepend">
                        <a href="#" type="button" class="btn btn-primary"><i data-feather="check"></i></a>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div class="modal fade" id="changerMDPModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Changer le mot de passe</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    </button>
                </div>
                <div class="modal-body">
                    <p>
                    <input id="ancienMDP" type="password" class="form-control" placeholder="Ancien mot de passe" required> 
                    </p>
                    <p>
                    <input id="nouveauMDP" type="password" class="form-control" placeholder="Nouveau mot de passe" required> 
                    </p>
                    

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                    <button type="button" class="btn btn-primary">Valider</button>
                </div>
            </div>
        </div>
    </div>
    
    <div class="modal fade" id="changerEmailModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Changer le mot de passe</h5>
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