<jsp:include page="header.jsp"/>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Entreprises</h1>
    </div>

    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Entreprises</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <button  class="btn btn-sm btn-success" data-toggle="modal" data-target="#exampleModal">
                        <span data-feather="plus"></span>
                        Nouvelle entreprise
                    </button>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <div class="input-group mb-3">
                            
                                <label for="siret" class="sr-only">SIRET *</label>
                                <input type="text" id="siret" class="form-control" placeholder="SIRET" required autofocus>
                                <div class="input-group-prepend">
                                    <a href="#" type="button" class="btn btn-primary"><i data-feather="search"></i></a>
                                </div>
                            
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <div class="input-group mb-3">
                            
                                <label for="nom" class="sr-only">Nom *</label>
                                <input type="text" id="nom" class="form-control" placeholder="Nom" required autofocus>
                                <div class="input-group-prepend">
                                    <a href="#" type="button" class="btn btn-primary"><i data-feather="search"></i></a>
                                </div>
                            
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
                            <th scope="col">SIRET</th>
                            <th scope="col">Nom</th>
                            <th scope="col">Adresse</th>
                            <th scope="col">Agence</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>15422651475448</td>
                            <td>Orange</td>
                            <td>Lyon</td>
                            <td>Lyon</td>
                            <td><a href="#" type="button" class="btn" style="background-color:transparent; color:green"><i data-feather="phone"></i></a>
                            <a href="#" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="edit-2"></i></a>
                            <a href="#" type="button" class="btn" style="background-color:transparent; color:red"><i data-feather="trash-2"></i></a></td>


                        </tr>

                    </tbody>
                </table>
            </div>
            <nav aria-label="...">
                <ul class="pagination">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">Précédent</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item active">
                        <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#">Suivant</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Nouvelle entreprise</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    </button>
                </div>
                <div class="modal-body">
                    <p>
                        <label for="siret" class="sr-only">SIRET *</label>
                        <input type="text" id="siret" class="form-control" placeholder="SIRET" required autofocus>
                    </p>
                    <p>
                        <label for="nom" class="sr-only">Nom *</label>
                        <input type="text" id="nom" class="form-control" placeholder="Nom" required>
                    </p>
                    <p>
                        <label for="adresse" class="sr-only">Adresse de facturation *</label>
                        <input type="text" id="adresse" class="form-control" placeholder="Adresse" required>
                    </p>
                    <p>
                        <label for="cle" class="sr-only">Agence *</label>
                        <input type="text" id="cle" class="form-control" placeholder="Agence" required>
                    </p>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                    <button type="button" class="btn btn-primary">Créer l'entreprise</button>
                </div>
            </div>
        </div>

    </div>

</main>


<jsp:include page="footer.jsp"/>