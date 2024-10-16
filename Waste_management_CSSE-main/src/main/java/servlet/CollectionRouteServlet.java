package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CollectionRoute;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import dao.CollectionRouteDAO;
import interfaces.ICollectionRouteDAO;

@WebServlet("/collectionRoutes")
public class CollectionRouteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ICollectionRouteDAO iCollectionRouteDAO = new CollectionRouteDAO();
    private Gson gson = new Gson();

    public CollectionRouteServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        response.setContentType("application/json");

        if (action == null) {
            List<CollectionRoute> routes = iCollectionRouteDAO.selectAllRoutes();
            response.getWriter().write(gson.toJson(routes));
        } else if (action.equals("edit")) {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Missing route ID\"}");
                return;
            }

            idParam = idParam.trim();

            try {
                int routeId = Integer.parseInt(idParam);
                CollectionRoute route = iCollectionRouteDAO.selectRoute(routeId);
                if (route != null) {
                    response.getWriter().write(gson.toJson(route));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\": \"Route not found\"}");
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Invalid route ID format\"}");
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"error\": \"An unexpected error occurred\"}");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String action = request.getParameter("action");
        StringBuilder jsonBuffer = new StringBuilder();
        String line;

        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        }

        String json = jsonBuffer.toString();

        if (action.equals("create")) {
            CollectionRoute route = gson.fromJson(json, CollectionRoute.class);
            iCollectionRouteDAO.insertRoute(route);
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("{\"message\": \"Route created successfully\"}");
        } else if (action.equals("update")) {
            CollectionRoute route = gson.fromJson(json, CollectionRoute.class);
            iCollectionRouteDAO.updateRoute(route);
            response.getWriter().write("{\"message\": \"Route updated successfully\"}");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Missing route ID\"}");
            return;
        }

        idParam = idParam.trim();

        try {
            int routeId = Integer.parseInt(idParam);
            iCollectionRouteDAO.deleteRoute(routeId);
            response.getWriter().write("{\"message\": \"Route deleted successfully\"}");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid route ID format\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"An unexpected error occurred\"}");
        }
    }
}
