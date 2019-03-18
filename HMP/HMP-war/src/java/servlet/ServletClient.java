package servlet;

import GestionUtilisateur.Client;
import SessionUtilisateur.SessionClientLocal;
import SessionUtilisateur.SessionLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletClient", urlPatterns = {"/ServletClient"})
public class ServletClient extends HttpServlet {

    @EJB
    private SessionLocal sessionMain;

    @EJB
    private SessionClientLocal sessionClient;
    
    
    
    private final String ATT_SESSION_CLIENT = "sessionClient";
    
    private String jspClient = "/client/index.jsp";

    private Client c;    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sessionHttp = request.getSession();
        
        if(sessionHttp.getAttribute("sessionClient")!=null){
            c = (Client) sessionHttp.getAttribute("sessionClient");  
            if (request.getParameter("action") != null){
            String act = request.getParameter("action");
            //MODIFIER LE PRENOM DU CLIENT
            if(act.equals("modifierPrenomClient")){
                if (request.getParameter("nouveauPrenom") != null && !request.getParameter("nouveauPrenom").isEmpty()){
                String prenom = request.getParameter("nouveauPrenom");
                sessionClient.modifierClient(c.getId(), c.getNom(), prenom, c.getMail(), c.getTelephone());
                Client cli = sessionMain.rechercheClient(c.getId());
                request.getSession().setAttribute(ATT_SESSION_CLIENT, cli);//Attribuer le Token
                jspClient = "/client/monProfil.jsp";
                }
            }
            //MODIFIER LE NOM DU CLIENT
            if(act.equals("modifierNomClient")){
                if (request.getParameter("nouveauNom") != null && !request.getParameter("nouveauNom").equals("") ){
                String nom = request.getParameter("nouveauNom");
                sessionClient.modifierClient(c.getId(), nom, c.getPrenom(), c.getMail(), c.getTelephone());
                Client cli = sessionMain.rechercheClient(c.getId());
                request.getSession().setAttribute(ATT_SESSION_CLIENT, cli);//Attribuer le Token
                jspClient = "/client/monProfil.jsp";
                }
            }
            //MODIFIER LE TELEPHONE DU CLIENT
            if(act.equals("modifierTelephoneClient")){
                if (request.getParameter("nouveauTelephone") != null && !request.getParameter("nouveauTelephone").equals("") ){
                String tel = request.getParameter("nouveauTelephone");
                sessionClient.modifierClient(c.getId(), c.getNom(), c.getPrenom(), c.getMail(), tel);
                Client cli = sessionMain.rechercheClient(c.getId());
                request.getSession().setAttribute(ATT_SESSION_CLIENT, cli);//Attribuer le Token
                jspClient = "/client/monProfil.jsp";
                }
            }
            
        }
           
        }

        
        RequestDispatcher rd = getServletContext().getRequestDispatcher(jspClient);
        rd.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
