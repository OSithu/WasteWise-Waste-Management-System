package interfaces;

import java.util.List;
import model.WasteCollection;

public interface IWasteCollectionDAO {
    // Retrieves all waste collections from the database
    public List<WasteCollection> selectAllCollections();

    // Retrieves a specific waste collection based on its ID
    public WasteCollection selectCollection(int collectionId);

    // Inserts a new waste collection record into the database
    public void insertCollection(WasteCollection collection);

    // Updates an existing waste collection record in the database
    public void updateCollection(WasteCollection collection);

    // Deletes a waste collection record from the database based on its ID
    public void deleteCollection(int collectionId);
}
