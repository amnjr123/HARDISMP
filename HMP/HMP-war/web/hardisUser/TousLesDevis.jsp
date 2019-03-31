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
	<th>Client</th>
	<th>Entreprise</th>
	<th>Sevice</th>
	<th>Coût</th>
	<th>Statut</th>
	<th>Nom Consultant</th>
        <th>Voir le devis</th>
</tr>
                    </thead>
                    <tbody>
<tr>
	<td>1614</td>
	<td>Honorato Shannon</td>
	<td>Nascetur Ridiculus Ltd</td>
	<td>Exécution d'une campagne de test > 5 jours </td>
	<td>11,271 &euro;</td>
	<td>Réponse En Cours </td>
	<td>BOUDYACH Anas </td>
        <td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>770</td>
	<td>Oliver Mcdowell</td>
	<td>Nulla Magna Associates</td>
	<td> Conception d'une stratégie de test > 5 jours </td>
	<td>11,183 &euro;</td>
	<td> Envoyé </td>
	<td> MADRANGES Manon </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>1014</td>
	<td>Duncan Russell</td>
	<td>Nunc Ullamcorper Velit PC</td>
	<td> Réalisation d'un audit Flash Organisationnel </td>
	<td>8,735 &euro;</td>
	<td> Validé </td>
	<td> LOUAFDI Mehdi </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>2173</td>
	<td>Valentine Adams</td>
	<td>Ultricies Corporation</td>
	<td> RPA Conception et automatisation des process </td>
	<td>3,408 &euro;</td>
	<td> Refusé </td>
	<td> NEJJARI Amine </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>3829</td>
	<td>Asher Dorsey</td>
	<td>Fermentum Fermentum Arcu Ltd</td>
	<td> Réalisation d'une mission IT advisory </td>
	<td>11,507 &euro;</td>
	<td> En Négociation </td>
	<td> Andrieu Domitille</td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>1118</td>
	<td>Kane Solis</td>
	<td>Sem Consequat Limited</td>
	<td> RPA Conception et automatisation des process</td>
	<td>23,107 &euro;</td>
	<td> Acompte Réglé </td>
	<td>BOUDYACH Anas </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>919</td>
	<td>Thane Baldwin</td>
	<td>Elit Pede Associates</td>
	<td>Exécution d'une campagne de test > 5 jours </td>
	<td>4,837 &euro;</td>
	<td> Prestation Terminée</td>
	<td> MADRANGES Manon </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>784</td>
	<td>Ivan Odonnell</td>
	<td>Et Ltd</td>
	<td> Conception d'une stratégie de test > 5 jours </td>
	<td>11,533 &euro;</td>
	<td>Réponse En Cours </td>
	<td> LOUAFDI Mehdi </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>3928</td>
	<td>Lamar Morin</td>
	<td>Eros Foundation</td>
	<td> Réalisation d'un audit Flash Organisationnel </td>
	<td>15,617 &euro;</td>
	<td> Envoyé </td>
	<td> NEJJARI Amine </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>3325</td>
	<td>Troy Daniels</td>
	<td>In Faucibus Corp.</td>
	<td> RPA Conception et automatisation des process </td>
	<td>11,047 &euro;</td>
	<td> Validé </td>
	<td> Andrieu Domitille</td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>3078</td>
	<td>Hasad Blackwell</td>
	<td>Eget Magna Foundation</td>
	<td> Réalisation d'une mission IT advisory </td>
	<td>5,676 &euro;</td>
	<td> Refusé </td>
	<td>BOUDYACH Anas </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>1706</td>
	<td>Curran Alvarez</td>
	<td>Nisi Cum Sociis Limited</td>
	<td> RPA Conception et automatisation des process</td>
	<td>24,646 &euro;</td>
	<td> En Négociation </td>
	<td> MADRANGES Manon </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>2355</td>
	<td>Quinlan Jenkins</td>
	<td>Sem Magna Nec Ltd</td>
	<td>Exécution d'une campagne de test > 5 jours </td>
	<td>20,164 &euro;</td>
	<td> Acompte Réglé </td>
	<td> LOUAFDI Mehdi </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>2036</td>
	<td>Hashim Vance</td>
	<td>Arcu LLP</td>
	<td> Conception d'une stratégie de test > 5 jours </td>
	<td>17,853 &euro;</td>
	<td> Prestation Terminée</td>
	<td> NEJJARI Amine </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>1618</td>
	<td>Chester Rice</td>
	<td>Odio LLC</td>
	<td> Réalisation d'un audit Flash Organisationnel </td>
	<td>17,811 &euro;</td>
	<td>Réponse En Cours </td>
	<td> Andrieu Domitille</td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>3177</td>
	<td>Brennan Ratliff</td>
	<td>Suspendisse Dui Industries</td>
	<td> RPA Conception et automatisation des process </td>
	<td>1,772 &euro;</td>
	<td> Envoyé </td>
	<td>BOUDYACH Anas </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>2866</td>
	<td>Chaney Burch</td>
	<td>Morbi Tristique Senectus Industries</td>
	<td> Réalisation d'une mission IT advisory </td>
	<td>2,064 &euro;</td>
	<td> Validé </td>
	<td> MADRANGES Manon </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>1206</td>
	<td>Cade Slater</td>
	<td>Ultricies Ornare PC</td>
	<td> RPA Conception et automatisation des process</td>
	<td>6,452 &euro;</td>
	<td> Refusé </td>
	<td> LOUAFDI Mehdi </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>614</td>
	<td>Gabriel Whitley</td>
	<td>Velit Aliquam Nisl LLC</td>
	<td>Exécution d'une campagne de test > 5 jours </td>
	<td>10,437 &euro;</td>
	<td> En Négociation </td>
	<td> NEJJARI Amine </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>2407</td>
	<td>Reece Russo</td>
	<td>Augue Eu Institute</td>
	<td> Conception d'une stratégie de test > 5 jours </td>
	<td>14,883 &euro;</td>
	<td> Acompte Réglé </td>
	<td> Andrieu Domitille</td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>307</td>
	<td>Gage Cleveland</td>
	<td>Nulla PC</td>
	<td> Réalisation d'un audit Flash Organisationnel </td>
	<td>27,022 &euro;</td>
	<td> Prestation Terminée</td>
	<td>BOUDYACH Anas </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>1814</td>
	<td>Kenyon Greer</td>
	<td>Nam LLC</td>
	<td> RPA Conception et automatisation des process </td>
	<td>21,406 &euro;</td>
	<td>Réponse En Cours </td>
	<td> MADRANGES Manon </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>359</td>
	<td>Roth Castaneda</td>
	<td>Tincidunt Nunc Foundation</td>
	<td> Réalisation d'une mission IT advisory </td>
	<td>21,099 &euro;</td>
	<td> Envoyé </td>
	<td> LOUAFDI Mehdi </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>3066</td>
	<td>Brody Caldwell</td>
	<td>Lorem Tristique Aliquet Consulting</td>
	<td> RPA Conception et automatisation des process</td>
	<td>3,907 &euro;</td>
	<td> Validé </td>
	<td> NEJJARI Amine </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>256</td>
	<td>Uriah Huber</td>
	<td>Mattis Velit LLC</td>
	<td>Exécution d'une campagne de test > 5 jours </td>
	<td>4,103 &euro;</td>
	<td> Refusé </td>
	<td> Andrieu Domitille</td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>2708</td>
	<td>Yasir Small</td>
	<td>Auctor LLP</td>
	<td> Conception d'une stratégie de test > 5 jours </td>
	<td>22,866 &euro;</td>
	<td> En Négociation </td>
	<td>BOUDYACH Anas </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>2397</td>
	<td>Dorian Howe</td>
	<td>Tellus Lorem Ltd</td>
	<td> Réalisation d'un audit Flash Organisationnel </td>
	<td>29,436 &euro;</td>
	<td> Acompte Réglé </td>
	<td> MADRANGES Manon </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>1811</td>
	<td>Guy Marquez</td>
	<td>Ullamcorper Associates</td>
	<td> RPA Conception et automatisation des process </td>
	<td>20,377 &euro;</td>
	<td> Prestation Terminée</td>
	<td> LOUAFDI Mehdi </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>818</td>
	<td>Paul Holman</td>
	<td>Mauris LLC</td>
	<td> Réalisation d'une mission IT advisory </td>
	<td>2,148 &euro;</td>
	<td>Réponse En Cours </td>
	<td> NEJJARI Amine </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>2951</td>
	<td>Walker Fuller</td>
	<td>Aliquet Sem Ut Corporation</td>
	<td> RPA Conception et automatisation des process</td>
	<td>14,456 &euro;</td>
	<td> Envoyé </td>
	<td> Andrieu Domitille</td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>3277</td>
	<td>Rooney Rios</td>
	<td>Quam A Ltd</td>
	<td>Exécution d'une campagne de test > 5 jours </td>
	<td>8,707 &euro;</td>
	<td> Validé </td>
	<td>BOUDYACH Anas </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>1303</td>
	<td>Leonard Caldwell</td>
	<td>Vulputate LLP</td>
	<td> Conception d'une stratégie de test > 5 jours </td>
	<td>1,317 &euro;</td>
	<td> Refusé </td>
	<td> MADRANGES Manon </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>1434</td>
	<td>Magee Mcmahon</td>
	<td>Rutrum Eu Ltd</td>
	<td> Réalisation d'un audit Flash Organisationnel </td>
	<td>17,608 &euro;</td>
	<td> En Négociation </td>
	<td> LOUAFDI Mehdi </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>1541</td>
	<td>Kieran Mccormick</td>
	<td>Eget LLP</td>
	<td> RPA Conception et automatisation des process </td>
	<td>26,613 &euro;</td>
	<td> Acompte Réglé </td>
	<td> NEJJARI Amine </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>1291</td>
	<td>Darius Cantu</td>
	<td>Tempus Scelerisque Lorem Industries</td>
	<td> Réalisation d'une mission IT advisory </td>
	<td>17,277 &euro;</td>
	<td> Prestation Terminée</td>
	<td> Andrieu Domitille</td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>303</td>
	<td>Ashton Elliott</td>
	<td>Vel Corporation</td>
	<td> RPA Conception et automatisation des process</td>
	<td>12,298 &euro;</td>
	<td>Réponse En Cours </td>
	<td>BOUDYACH Anas </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>770</td>
	<td>Edan Bryan</td>
	<td>Elit Sed Consequat Corp.</td>
	<td>Exécution d'une campagne de test > 5 jours </td>
	<td>24,737 &euro;</td>
	<td> Envoyé </td>
	<td> MADRANGES Manon </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>2686</td>
	<td>Colin Chen</td>
	<td>Ornare Facilisis Eget LLP</td>
	<td> Conception d'une stratégie de test > 5 jours </td>
	<td>10,300 &euro;</td>
	<td> Validé </td>
	<td> LOUAFDI Mehdi </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>298</td>
	<td>Hammett Riggs</td>
	<td>Curabitur Consequat Lectus Corporation</td>
	<td> Réalisation d'un audit Flash Organisationnel </td>
	<td>15,808 &euro;</td>
	<td> Refusé </td>
	<td> NEJJARI Amine </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>301</td>
	<td>Grady Sellers</td>
	<td>Turpis Company</td>
	<td> RPA Conception et automatisation des process </td>
	<td>27,551 &euro;</td>
	<td> En Négociation </td>
	<td> Andrieu Domitille</td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>3761</td>
	<td>Keefe Parrish</td>
	<td>Proin Company</td>
	<td> Réalisation d'une mission IT advisory </td>
	<td>9,330 &euro;</td>
	<td> Acompte Réglé </td>
	<td>BOUDYACH Anas </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>1417</td>
	<td>Caldwell Wilcox</td>
	<td>Consectetuer Consulting</td>
	<td> RPA Conception et automatisation des process</td>
	<td>10,870 &euro;</td>
	<td> Prestation Terminée</td>
	<td> MADRANGES Manon </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
<tr>
	<td>2945</td>
	<td>Steven Holt</td>
	<td>In Ornare Sagittis Corp.</td>
	<td>Exécution d'une campagne de test > 5 jours </td>
	<td>28,175 &euro;</td>
	<td>Réponse En Cours </td>
	<td> LOUAFDI Mehdi </td>
<td class="text-center"><a href="#">Voir le détail</a></td>
</tr>
                    </tbody>

                </table>
            </div>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp"/>