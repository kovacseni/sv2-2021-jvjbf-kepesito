package trains;

//import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

public class TrainService {

    private TrainRepository trainRepository;
    private JdbcTemplate jdbcTemplate;

    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    public Train buyTicketForTrain(String destination, int amount) {
        return null;
    }

}
