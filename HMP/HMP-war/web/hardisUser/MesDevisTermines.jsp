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
<jsp:include page="header.jsp"/>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Mes devis terminés</h1>
    </div>

    <div class="card">
        <div class="card-header">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center">
                <h1 class="h2">Devis</h1>
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
                            <th>ID</th>
                            <th>Nom Client</th>
                            <th>Entreprise</th>
                            <th>Service</th>
                            <th>Coût</th>
                            <th>Statut</th>
                            <th class="text-center">Voir le devis</th>
                    </thead>
                    <tbody>
                        <tr>
                            <td>815</td>
                            <td>Remedios Craft</td>
                            <td>Nisl Nulla Eu PC</td>
                            <td>Exécution d'une campagne de test > 5 jours </td>
                            <td>10,356 &euro;</td>
                            <td>Envoyé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>1537</td>
                            <td>Leandra Warner</td>
                            <td>Gravida Ltd</td>
                            <td> Conception d'une stratégie de test > 5 jours </td>
                            <td>11,433 &euro;</td>
                            <td> Validé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>2803</td>
                            <td>Nelle Donovan</td>
                            <td>Tincidunt Company</td>
                            <td> Réalisation d'un audit Flash Organisationnel </td>
                            <td>4,559 &euro;</td>
                            <td> Refusé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>3444</td>
                            <td>Hedda Bowen</td>
                            <td>Montes Nascetur Ridiculus LLP</td>
                            <td> Réalisation d'une mission IT advisory </td>
                            <td>7,535 &euro;</td>
                            <td> En Négociation </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>595</td>
                            <td>Lesley Mcclain</td>
                            <td>Vitae Sodales At Consulting</td>
                            <td> RPA Conception et automatisation des process </td>
                            <td>27,139 &euro;</td>
                            <td> Acompte Réglé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>134</td>
                            <td>Sierra Christensen</td>
                            <td>Erat LLP</td>
                            <td> Serious Gaming </td>
                            <td>24,179 &euro;</td>
                            <td> Prestation Terminée</td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>178</td>
                            <td>Chastity Powell</td>
                            <td>Semper Tellus Company</td>
                            <td> Animation d'un atelier Lego Serious Play</td>
                            <td>4,504 &euro;</td>
                            <td>Envoyé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>3116</td>
                            <td>Cheryl Mcintosh</td>
                            <td>Ipsum Porta LLC</td>
                            <td>Exécution d'une campagne de test > 5 jours </td>
                            <td>20,750 &euro;</td>
                            <td> Validé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>3213</td>
                            <td>Imogene Larsen</td>
                            <td>Arcu Sed LLP</td>
                            <td> Conception d'une stratégie de test > 5 jours </td>
                            <td>24,401 &euro;</td>
                            <td> Refusé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>3651</td>
                            <td>Noelani Gordon</td>
                            <td>Ligula Aliquam Consulting</td>
                            <td> Réalisation d'un audit Flash Organisationnel </td>
                            <td>28,159 &euro;</td>
                            <td> En Négociation </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>2939</td>
                            <td>Karen Parker</td>
                            <td>Felis Orci LLC</td>
                            <td> Réalisation d'une mission IT advisory </td>
                            <td>13,704 &euro;</td>
                            <td> Acompte Réglé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>986</td>
                            <td>Cathleen Zamora</td>
                            <td>Est Nunc Limited</td>
                            <td> RPA Conception et automatisation des process </td>
                            <td>29,391 &euro;</td>
                            <td> Prestation Terminée</td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>2629</td>
                            <td>Kyla Horne</td>
                            <td>Lectus Limited</td>
                            <td> Serious Gaming </td>
                            <td>3,722 &euro;</td>
                            <td>Envoyé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>93</td>
                            <td>Tatiana Wiley</td>
                            <td>Accumsan Foundation</td>
                            <td> Animation d'un atelier Lego Serious Play</td>
                            <td>9,620 &euro;</td>
                            <td> Validé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>2955</td>
                            <td>Blythe Nelson</td>
                            <td>Scelerisque Corporation</td>
                            <td>Exécution d'une campagne de test > 5 jours </td>
                            <td>13,203 &euro;</td>
                            <td> Refusé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>3053</td>
                            <td>Ila Baxter</td>
                            <td>Diam At Inc.</td>
                            <td> Conception d'une stratégie de test > 5 jours </td>
                            <td>3,321 &euro;</td>
                            <td> En Négociation </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>1017</td>
                            <td>Ava Huff</td>
                            <td>Lorem Eget Mollis Institute</td>
                            <td> Réalisation d'un audit Flash Organisationnel </td>
                            <td>12,502 &euro;</td>
                            <td> Acompte Réglé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>3347</td>
                            <td>Doris Allen</td>
                            <td>Ipsum Foundation</td>
                            <td> Réalisation d'une mission IT advisory </td>
                            <td>10,949 &euro;</td>
                            <td> Prestation Terminée</td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>259</td>
                            <td>Marny Rosario</td>
                            <td>Sapien Cras Dolor Inc.</td>
                            <td> RPA Conception et automatisation des process </td>
                            <td>2,258 &euro;</td>
                            <td>Envoyé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>846</td>
                            <td>Lilah Giles</td>
                            <td>Aliquet Phasellus Fermentum Inc.</td>
                            <td> Serious Gaming </td>
                            <td>17,909 &euro;</td>
                            <td> Validé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>2377</td>
                            <td>Ignacia Welch</td>
                            <td>Augue Porttitor Interdum Corporation</td>
                            <td> Animation d'un atelier Lego Serious Play</td>
                            <td>5,966 &euro;</td>
                            <td> Refusé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>3475</td>
                            <td>Sierra Keller</td>
                            <td>Pellentesque Habitant Industries</td>
                            <td>Exécution d'une campagne de test > 5 jours </td>
                            <td>15,491 &euro;</td>
                            <td> En Négociation </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>2948</td>
                            <td>Ivory Erickson</td>
                            <td>Tempus Mauris Institute</td>
                            <td> Conception d'une stratégie de test > 5 jours </td>
                            <td>17,865 &euro;</td>
                            <td> Acompte Réglé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>1050</td>
                            <td>McKenzie Mcleod</td>
                            <td>Egestas Industries</td>
                            <td> Réalisation d'un audit Flash Organisationnel </td>
                            <td>19,546 &euro;</td>
                            <td> Prestation Terminée</td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>1439</td>
                            <td>Zorita Meyer</td>
                            <td>Dolor Corporation</td>
                            <td> Réalisation d'une mission IT advisory </td>
                            <td>21,284 &euro;</td>
                            <td>Envoyé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>332</td>
                            <td>Astra Hicks</td>
                            <td>Curabitur Ut Odio Limited</td>
                            <td> RPA Conception et automatisation des process </td>
                            <td>15,677 &euro;</td>
                            <td> Validé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>1568</td>
                            <td>Haviva Fowler</td>
                            <td>Adipiscing Enim Associates</td>
                            <td> Serious Gaming </td>
                            <td>21,545 &euro;</td>
                            <td> Refusé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>1941</td>
                            <td>Jessica Kelley</td>
                            <td>Sit Institute</td>
                            <td> Animation d'un atelier Lego Serious Play</td>
                            <td>16,717 &euro;</td>
                            <td> En Négociation </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>325</td>
                            <td>Madeson Church</td>
                            <td>Nulla Tempor Augue Foundation</td>
                            <td>Exécution d'une campagne de test > 5 jours </td>
                            <td>28,876 &euro;</td>
                            <td> Acompte Réglé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>3040</td>
                            <td>Hillary Donovan</td>
                            <td>Donec Vitae Erat Incorporated</td>
                            <td> Conception d'une stratégie de test > 5 jours </td>
                            <td>14,211 &euro;</td>
                            <td> Prestation Terminée</td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>694</td>
                            <td>Ivana Russo</td>
                            <td>Adipiscing Enim Inc.</td>
                            <td> Réalisation d'un audit Flash Organisationnel </td>
                            <td>12,620 &euro;</td>
                            <td>Envoyé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>1485</td>
                            <td>Oprah Solomon</td>
                            <td>Lorem Sit Amet Industries</td>
                            <td> Réalisation d'une mission IT advisory </td>
                            <td>29,038 &euro;</td>
                            <td> Validé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                        <tr>
                            <td>2601</td>
                            <td>Fiona Wood</td>
                            <td>Nunc Ac Mattis Foundation</td>
                            <td> RPA Conception et automatisation des process </td>
                            <td>7,879 &euro;</td>
                            <td> Refusé </td>
                            <td class="text-center"><a href="#">Voir le détail</a></td></tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp"/>