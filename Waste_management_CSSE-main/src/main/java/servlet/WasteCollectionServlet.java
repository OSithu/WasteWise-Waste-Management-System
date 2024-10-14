package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.WasteCollection;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import dao.WasteCollectionDAO;
import interfaces.IWasteCollectionDAO;

@WebServlet("/collections")
public class WasteCollectionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IWasteCollectionDAO iWasteCollectionDAO = new WasteCollectionDAO();
    private Gson gson = new Gson(); 

    public WasteCollectionServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        response.setContentType("application/json");

        if (action == null) {
            List<WasteCollection> collections = iWasteCollectionDAO.selectAllCollections();
            response.getWriter().write(gson.toJson(collections));
        } else if (action.equals("edit")) {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Missing collection ID\"}");
                return;
            }

            try {
                int collectionId = Integer.parseInt(idParam.trim());
                WasteCollection collection = iWasteCollectionDAO.selectCollection(collectionId);
                if (collection != null) {
                    response.getWriter().write(gson.toJson(collection));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\": \"Collection not found\"}");
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Invalid collection ID format\"}");
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

        String jsonString = jsonBuffer.toString();
        WasteCollection collection;

        try {
            collection = gson.fromJson(jsonString, WasteCollection.class);
            if ("create".equals(action)) {
                iWasteCollectionDAO.insertCollection(collection);
                response.getWriter().write("{\"message\": \"Collection created successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Invalid action\"}");
            }
        } catch (JsonSyntaxException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid JSON format\"}");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        String collectionIdParam = request.getParameter("id");
        if (collectionIdParam == null || collectionIdParam.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Missing collection ID\"}");
            return;
        }

        StringBuilder jsonBuffer = new StringBuilder();
        String line;

        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        }

        String jsonString = jsonBuffer.toString();
        WasteCollection collection;

        try {
            int collectionId = Integer.parseInt(collectionIdParam.trim());
            collection = gson.fromJson(jsonString, WasteCollection.class);
            collection.setCollectionId(collectionId);
            iWasteCollectionDAO.updateCollection(collection);
            response.getWriter().write("{\"message\": \"Collection updated successfully\"}");
        } catch (NumberFormatException | JsonSyntaxException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid request data\"}");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String collectionIdParam = request.getParameter("id");
        if (collectionIdParam == null || collectionIdParam.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Missing collection ID\"}");
            return;
        }

        try {
            int collectionId = Integer.parseInt(collectionIdParam.trim());
            iWasteCollectionDAO.deleteCollection(collectionId);
            response.getWriter().write("{\"message\": \"Collection deleted successfully\"}");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid collection ID format\"}");
        }
    }
}
