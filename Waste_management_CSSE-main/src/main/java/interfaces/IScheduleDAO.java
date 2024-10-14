package interfaces;

import java.util.List;
import model.Schedule;

public interface IScheduleDAO {
    // Retrieves all schedules from the database
    List<Schedule> selectAllSchedules();

    // Retrieves a specific schedule based on its ID
    Schedule selectSchedule(int scheduleId);

    // Inserts a new schedule record into the database
    void insertSchedule(Schedule schedule);

    // Updates an existing schedule record in the database
    void updateSchedule(Schedule schedule);

    // Deletes a schedule record from the database based on its ID
    void deleteSchedule(int scheduleId);
}
