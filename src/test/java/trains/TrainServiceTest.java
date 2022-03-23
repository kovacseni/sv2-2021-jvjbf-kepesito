package trains;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TrainServiceTest {
    Flyway flyway;
    TrainRepository repository;
    TrainService service;

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
        service = new TrainService(repository);

    }


    @Test
    void testBuyTicket() {
        repository.insertTrain("Debrecen", LocalDateTime.of(2022, 3, 10, 14, 30), 200);
        repository.insertTrain("Budapest", LocalDateTime.of(2022, 3, 10, 14, 35), 200);
        repository.insertTrain("Debrecen", LocalDateTime.of(2022, 3, 10, 15, 30), 200);
        repository.insertTrain("Debrecen", LocalDateTime.of(2022, 3, 15, 14, 30), 200);

        Train train = service.buyTicketForTrain("Debrecen", 100);

        assertEquals("Debrecen", train.getDestination());
        assertEquals(LocalDateTime.of(2022, 3, 10, 14, 30), train.getDeparture());
        assertEquals(100, train.getNumberOfPassengers());

        train = service.buyTicketForTrain("Debrecen", 101);

        assertEquals("Debrecen", train.getDestination());
        assertEquals(LocalDateTime.of(2022, 3, 10, 15, 30), train.getDeparture());
        assertEquals(101, train.getNumberOfPassengers());

        train = service.buyTicketForTrain("Debrecen", 100);
        assertEquals("Debrecen", train.getDestination());
        assertEquals(LocalDateTime.of(2022, 3, 10, 14, 30), train.getDeparture());
        assertEquals(200, train.getNumberOfPassengers());
    }

    @Test
    void testCannotBuyTicket() {
        repository.insertTrain("Debrecen", LocalDateTime.of(2022, 3, 10, 14, 30), 200);
        repository.insertTrain("Budapest", LocalDateTime.of(2022, 3, 10, 14, 35), 50);
        repository.insertTrain("Debrecen", LocalDateTime.of(2022, 3, 10, 15, 30), 200);
        repository.insertTrain("Debrecen", LocalDateTime.of(2022, 3, 15, 14, 30), 200);

        IllegalStateException ise = assertThrows(IllegalStateException.class,
                () -> service.buyTicketForTrain("Debrecen", 201));
        assertEquals("Cannot buy ticket for train to: Debrecen", ise.getMessage());

        ise = assertThrows(IllegalStateException.class,
                () -> service.buyTicketForTrain("Budapest", 51));
        assertEquals("Cannot buy ticket for train to: Budapest", ise.getMessage());
    }

}