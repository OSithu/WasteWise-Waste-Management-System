package interfaces;

import java.util.List;
import model.CollectionRoute;

public interface ICollectionRouteDAO {
    // Retrieves all collection routes from the database
    List<CollectionRoute> selectAllRoutes();
    
    // Retrieves a specific collection route based on its ID
    CollectionRoute selectRoute(int routeId);
    
    // Inserts a new collection route record into the database
    void insertRoute(CollectionRoute route);
    
    // Updates an existing collection route record in the database
    void updateRoute(CollectionRoute route);
    
    // Deletes a collection route record from the database based on its ID
    void deleteRoute(int routeId);
}
