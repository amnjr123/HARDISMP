<%@page import="GestionDevis.Communication"%>
<%@page import="GestionDevis.Proposition"%>
<%@page import="java.util.Locale"%>
<%@page import="GestionDevis.HistoriqueUtilisateurDevis"%>
<%@page import="GestionUtilisateur.UtilisateurHardis"%>
<%@page import="GestionDevis.Devis"%>
<%@page import="GestionDevis.DevisStandard"%>
<%@page import="GestionDevis.DevisNonStandard"%>
<%@page import="GestionCatalogue.Livrable"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="GestionCatalogue.Offre"%>
<%@page import="GestionCatalogue.ServiceStandard"%>
<%@page import="GestionCatalogue.ServiceNonStandard"%>
<%@page import="java.util.Collection"%>
<jsp:useBean id="devisStandard" scope="request" class="GestionDevis.DevisStandard"></jsp:useBean>
<jsp:useBean id="listCommunications" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:include page="header.jsp"/>

<%DevisStandard d = devisStandard;
Collection<Communication> listeMessages = listCommunications;
java.text.DateFormat dfjour = new java.text.SimpleDateFormat("dd/mm/yyyy ? HH:mm", Locale.FRENCH);
java.text.DateFormat dfheure = new java.text.SimpleDateFormat("dd/mm/yyyy ? HH:mm", Locale.FRENCH);
UtilisateurHardis uh = (UtilisateurHardis) request.getAttribute("uh");%>

<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Gestion du devis personnalis?</h1>
    </div>

    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2"><i data-feather="activity"></i>R?capitulatif</h1>
                <div class="btn-toolbar">
                </div>
            </div>
        </div>
        <%--Warning Or Sucess--%>   
        <% String error = (String) request.getAttribute("msgError");
            if (request.getAttribute("msgError") != null) {%>
        <div class="alert alert-danger alert-dismissible fade in show">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Attention !</strong>&nbsp;<%=(error)%>.
        </div>
        <%}%>

        <% String success = (String) request.getAttribute("msgSuccess");
            if (request.getAttribute("msgSuccess") != null) {%>
        <div class="alert alert-success alert-dismissible fade in show">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <%=(success)%>.
        </div>
        <%}%>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nom Client</th>
                            <th scope="col">Entreprise</th>
                            <th scope="col">Service</th>
                            <th scope="col">Montant</th>
                            <th scope="col">Etat</th>
                            <th scope="col">Date de cr?ation</th>
                            <%if(d.getStatut().equals("Envoye") || d.getStatut().equals("Valide") || d.getStatut().equals("Refuse") || d.getStatut().equals("AcompteRegle") || d.getStatut().equals("PrestationTerminee")){%>
                            <th scope="col">Date d'envoi au client</th>
                            <%}
                            if(d.getStatut().equals("Valide") || d.getStatut().equals("Refuse") || d.getStatut().equals("AcompteRegle") || d.getStatut().equals("PrestationTerminee")){%>
                            <th scope="col">Date de r?ponse</th>
                            <%}
                            if(d.getStatut().equals("AcompteRegle") || d.getStatut().equals("PrestationTerminee")){%>
                            <th scope="col">Date de versement de l'acompte</th>
                            <%}
                            if(d.getStatut().equals("PrestationTerminee")){%>
                            <th scope="col">Date de versement du restant</th>
                            <%}%>
                            <th scope="col">Reponsable du Devis</th>
                        </tr>
                    </thead>
                    <tbody>                                     
                        <tr>
                            <td><%=d.getId()%></td>
                            <td><%=d.getClient().getNom()%> <%=d.getClient().getPrenom()%></td>
                            <td><%=d.getClient().getEntreprise().getNom()%></td>
                            <td><%=d.getServiceStandard().getNom()%></td>
                            <td><%=d.getMontant()%></td>
                            <td><%=d.getStatut()%></td>
                            <td><%=dfjour.format(d.getDateCreation())%></td>
                            <%if(d.getStatut().equals("Envoye") || d.getStatut().equals("Valide") || d.getStatut().equals("Refuse") || d.getStatut().equals("AcompteRegle") || d.getStatut().equals("PrestationTerminee")){%>
                            <td><%=dfjour.format(d.getDateEnvoi())%></td>
                            <%}
                            if(d.getStatut().equals("Valide") || d.getStatut().equals("Refuse") || d.getStatut().equals("AcompteRegle") || d.getStatut().equals("PrestationTerminee")){%>
                            <td><%=dfjour.format(d.getDateReponse())%></td>
                            <%}
                            if(d.getStatut().equals("AcompteRegle") || d.getStatut().equals("PrestationTerminee")){%>
                            <td><%=dfjour.format(d.getDateAcompte())%></td>
                            <%}
                            if(d.getStatut().equals("PrestationTerminee")){%>
                            <td><%=dfjour.format(d.getDateReglement())%></td>
                            <%}%>
                            <td>
                                <p><%=d.getUtilisateurHardis()%></p>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2"><i data-feather="file-text"></i>Documents</h1>
                <div class="btn-toolbar">
                </div>
            </div>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Document</td>
                            <th>Date</td>
                            <th>T?l?charger</td>
                        </tr>
                    </thead>
                    <tbody>
                        <%if(!d.getStatut().equals("Incomplet") && !d.getStatut().equals("ReponseEnCours")){%>
                        <tr>
                            <td>Devis</td>
                            <td><%=dfjour.format(d.getDateEnvoi())%></td>
                            <td>
                                <a href="#" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="download"></i></a>
                            </td>
                        </tr>
                        <%}
                        if(!d.getStatut().equals("Incomplet") && !d.getStatut().equals("ReponseEnCours") && !d.getStatut().equals("Envoye") && !d.getStatut().equals("Refuse")){%>         
                        <tr>
                            <td>Bon de commande</td>
                            <td><%=dfjour.format(d.getDateReponse())%></td>
                            <td>
                                <a href="#" type="button" class="btn" style="background-color:transparent; color:yellowgreen"><i data-feather="download"></i></a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2"><i data-feather="message-square"></i>Messagerie</h1>
                <div class="btn-toolbar">
                </div>
            </div>
        </div>
        <div class="card-body">
            <div class="messaging">
            <div class="inbox_msg">
                <div class="mesgs">
                    <div class="msg_history" id="zoneMessages">
                        <%for (Communication comm : listeMessages) {
                                    if (comm.getUtilisateurHardis()!= null) {%>
                                        <div class="outgoing_msg">
                                            <div class="sent_msg">
                                                <p><%=comm.getContenu()%></p>
                                                <span class="time_date"><%=comm.getUtilisateurHardis().getPrenom()%> <%=comm.getUtilisateurHardis().getNom()%> le <%=dfjour.format(comm.getDateEnvoi())%></span> </div>
                                        </div>
                                    <%} else {%>
                                        <div class="incoming_msg">
                                            <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                                            <div class="received_msg">
                                                <div class="received_withd_msg">
                                                    <p><%=comm.getContenu()%></p>
                                                    <span class="time_date"><%=dfheure.format(comm.getDateEnvoi())%></span></div>
                                            </div>
                                        </div>
                                    <%}   
                            }%>
                    </div>
                            <%if(uh.getProfilTechnique().equals("Administrateur") || uh==d.getUtilisateurHardis()){%>
                    <div class="type_msg">
                        <div class="input_msg_write" id="newMessage">
                            <form method="POST" action="${pageContext.request.contextPath}/ServletUtilisateurHardis" id="formulaire">
                                <input type="hidden" name="action" value="repondreMessageDevisStandard">
                                <input type="hidden" name="idConversation" value="<%=d.getConversation().getId()%>">
                                <input type="hidden" name="idDevis" value="<%=d.getId()%>">
                                <input name="message" type="text" class="write_msg" placeholder="Ecrivez votre message ici" />
                                <button class="msg_send_btn" type="submit"><i data-feather="send" aria-hidden="true"></i></button>
                            </form>
                        </div>
                    </div>
                    <%}else{%>
                        <div class="type_msg">
                            <div class="input_msg_write" id="newMessage">
                                <input readonly name="message" type="text" class="write_msg" placeholder="Vous n'avez pas les droits n?cessaires pour participer ? cette conversation." />
                            </div>
                        </div>
                        <%}%>
                </div>
            </div>
        </div>
        </div>
    </div>
                    
    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2"><i data-feather="navigation"></i>Actions</h1>
                <div class="btn-toolbar">
                </div>
            </div>
        </div>
        <div class="card-body">
            
        </div>
    </div>
</main>
<jsp:include page="footer.jsp"/>