package interfaces;

import java.util.List;
import model.SpecialRequest;

public interface ISpecialRequestDAO {

    // Retrieves all special requests from the database
    List<SpecialRequest> selectAllRequests();

    // Retrieves a specific request based on its ID
    SpecialRequest selectRequest(int requestId);

    // Inserts a new special request record into the database
    void insertRequest(SpecialRequest request);

    // Updates an existing special request record in the database
    void updateRequest(SpecialRequest request);

    // Deletes a special request record from the database based on its ID
    void deleteRequest(int requestId);
}
