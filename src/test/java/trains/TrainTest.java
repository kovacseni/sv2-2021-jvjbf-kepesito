package trains;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TrainTest {
    @Test
    void createTrainTest() {
        Train train = new Train(1L, "Budapest", LocalDateTime.of(2022, 3, 5, 12, 30), 130, 0);

        assertEquals(1L, train.getId());
        assertEquals("Budapest", train.getDestination());
        assertEquals(LocalDateTime.of(2022, 3, 5, 12, 30), train.getDeparture());
        assertEquals(130, train.getMaxCapacity());
        assertEquals(0, train.getNumberOfPassengers());
    }
}