package trains;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainRepositoryTest {
    Flyway flyway;
    TrainRepository repository;

    @BeforeEach
    void init() {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/examtest?useUnicode=true");
            dataSource.setUser("root");
            dataSource.setPassword("***");
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot reach DataBase!", sqle);
        }

        flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();

        repository = new TrainRepository(dataSource);
    }

    @Test
    void testInsertAndGetTrain() {
        long generatedId = repository.insertTrain("Debrecen", LocalDateTime.of(2022, 3, 10, 14, 30), 200);

        Train train = repository.findById(generatedId);

        assertEquals("Debrecen", train.getDestination());
        assertEquals(LocalDateTime.of(2022, 3, 10, 14, 30), train.getDeparture());
        assertEquals(200, train.getMaxCapacity());
        assertEquals(0, train.getNumberOfPassengers());

    }

    @Test
    void testGetTrainByDestination() {
        repository.insertTrain("Debrecen", LocalDateTime.of(2022, 3, 10, 14, 30), 200);
        repository.insertTrain("Budapest", LocalDateTime.of(2022, 3, 10, 14, 35), 200);
        repository.insertTrain("Debrecen", LocalDateTime.of(2022, 3, 15, 14, 30), 200);
        repository.insertTrain("Miskolc", LocalDateTime.of(2022, 3, 15, 14, 30), 200);

        assertEquals(2, repository.getTrainsByDestination("Debrecen").size());
        assertEquals(1, repository.getTrainsByDestination("Miskolc").size());
    }

    @Test
    void testGetTrainsByDestinationInOrder() {
        repository.insertTrain("Debrecen", LocalDateTime.of(2022, 3, 10, 15, 30), 200);
        repository.insertTrain("Budapest", LocalDateTime.of(2022, 3, 10, 14, 35), 200);
        repository.insertTrain("Debrecen", LocalDateTime.of(2022, 3, 10, 14, 30), 200);
        repository.insertTrain("Miskolc", LocalDateTime.of(2022, 3, 10, 14, 30), 200);

        List<Train> result = repository.getTrainsByDestination("Debrecen");

        assertEquals(LocalDateTime.of(2022, 3, 10, 14, 30), result.get(0).getDeparture());
        assertEquals(LocalDateTime.of(2022, 3, 10, 15, 30), result.get(1).getDeparture());
    }


    @Test
    void updateTrainPassengersTest() {
        long generatedId = repository.insertTrain("Debrecen", LocalDateTime.of(2022, 3, 10, 15, 30), 200);

        repository.updateNumberOfPassengersById(generatedId, 30);
        assertEquals(LocalDateTime.of(2022, 3, 10, 15, 30), repository.findById(generatedId).getDeparture());
        assertEquals(30, repository.findById(generatedId).getNumberOfPassengers());
    }

}