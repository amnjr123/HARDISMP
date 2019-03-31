<%@page import="GestionDevis.Communication"%>
<%@page import="GestionDevis.Conversation"%>
<%@page import="java.util.Collection"%>
<jsp:useBean id="listConversations" scope="request" class="java.util.Collection"></jsp:useBean>
<jsp:useBean id="conversation" scope="request" class="GestionDevis.Conversation"></jsp:useBean>
<jsp:include page="header.jsp"/>
<%
    Collection<Conversation> listeConversations = listConversations;
    Conversation conversationActive = null;
    if (conversation != null) {
        conversationActive = conversation;
    }
%>
<main role="main" class="col-md-auto ml-sm-auto col-lg-auto">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
        <h1 class="h2">Ma messagerie</h1>
    </div>
    <div class="container">
        <div class="messaging">
            <div class="inbox_msg">
                <div class="inbox_people">
                    <div class="inbox_chat">
                        <%for (Conversation c : listeConversations) {%>
                        <a href="${pageContext.request.contextPath}/ServletClient?action=messages&idConversation=<%=c.getId()%>">
                            <div class="chat_list <% if (conversationActive != null) {
                                    if (c.getId() == conversationActive.getId()) {%>active_chat<%}
                                }%>" id="conversation<%=c.getId()%>">
                                <div class="chat_people">
                                    <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                                    <div class="chat_ib">
                                        <h5><%if (c.getUtilisateurHardis() != null) {
                                                out.print(c.getUtilisateurHardis().getNom() + " " + c.getUtilisateurHardis().getPrenom());
                                            } else {%>Consultant Hardis<%}%> <span class="chat_date"><%=c.getCommunications().get(c.getCommunications().size() - 1).getDateEnvoi()%></span></h5>
                                        <p><%=c.getCommunications().get(c.getCommunications().size() - 1).getContenu()%></p>
                                    </div>
                                </div>
                            </div>
                        </a>
                        <%}%>
                    </div>
                        <button class="msg_send_btn" type="button"><i data-feather="plus" aria-hidden="true"></i></button>
                </div>
                <div class="mesgs">
                    <div class="msg_history">
                        <%if (conversationActive != null) {
                                for (Communication comm : conversationActive.getCommunications()) {
                                    if (comm.getClient() != null) {%>
                        <div class="outgoing_msg">
                            <div class="sent_msg">
                                <p><%=comm.getContenu()%></p>
                                <span class="time_date"><%=comm.getDateEnvoi()%></span> </div>
                        </div>
                        <%} else {%>
                        <div class="incoming_msg">
                            <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                            <div class="received_msg">
                                <div class="received_withd_msg">
                                    <p><%=comm.getContenu()%></p>
                                    <span class="time_date"><%=comm.getDateEnvoi()%></span></div>
                            </div>
                        </div>
                        <%}}}%>
                    </div>
                    <div class="type_msg">
                        <div class="input_msg_write">
                            <form method="POST" action="${pageContext.request.contextPath}/ServletClient">
                                <%if (conversationActive == null) {%>
                                <input type="hidden" name="action" value="nouvelleConversation">
                                <input name="message" type="text" class="write_msg" placeholder="Posez votre question ici pour contacter un consultant Hardis" />
                                <%} else {%>
                                <input type="hidden" name="action" value="repondreMessage">
                                <input type="hidden" name="idConversation" value="<%=conversationActive.getId()%>">
                                <input name="message" type="text" class="write_msg" placeholder="Ecrivez votre message ici" />
                                <%}%>
                                <button class="msg_send_btn" type="submit"><i data-feather="send" aria-hidden="true"></i></button>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>
<jsp:include page="footer.jsp"/>