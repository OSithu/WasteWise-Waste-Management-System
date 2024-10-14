package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SpecialRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import dao.SpecialRequestDAO;
import interfaces.ISpecialRequestDAO;

@WebServlet("/specialRequests")
public class SpecialRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ISpecialRequestDAO iSpecialRequestDAO = new SpecialRequestDAO();
    private Gson gson = new Gson(); 

    public SpecialRequestServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        response.setContentType("application/json");

        if (action == null) {
            List<SpecialRequest> requests = iSpecialRequestDAO.selectAllRequests();
            response.getWriter().write(gson.toJson(requests));
        } else if (action.equals("edit")) {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Missing request ID\"}");
                return;
            }

            idParam = idParam.trim();

            try {
                int requestId = Integer.parseInt(idParam);
                SpecialRequest request1 = iSpecialRequestDAO.selectRequest(requestId);
                if (request1 != null) {
                    response.getWriter().write(gson.toJson(request1));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\": \"Request not found\"}");
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Invalid request ID\"}");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            jsonBuilder.append(line);
        }

        try {
            SpecialRequest specialRequest = gson.fromJson(jsonBuilder.toString(), SpecialRequest.class);
            specialRequest.setRequestDate(new Timestamp(System.currentTimeMillis())); // Set the request date to now
            iSpecialRequestDAO.insertRequest(specialRequest);
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("{\"message\": \"Request created successfully\"}");
        } catch (JsonSyntaxException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid request data\"}");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            jsonBuilder.append(line);
        }

        try {
            SpecialRequest specialRequest = gson.fromJson(jsonBuilder.toString(), SpecialRequest.class);
            iSpecialRequestDAO.updateRequest(specialRequest);
            response.getWriter().write("{\"message\": \"Request updated successfully\"}");
        } catch (JsonSyntaxException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid request data\"}");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Missing request ID\"}");
            return;
        }

        idParam = idParam.trim();

        try {
            int requestId = Integer.parseInt(idParam);
            iSpecialRequestDAO.deleteRequest(requestId);
            response.getWriter().write("{\"message\": \"Request deleted successfully\"}");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid request ID\"}");
        }
    }
}
