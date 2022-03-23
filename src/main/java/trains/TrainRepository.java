package trains;

//import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

public class TrainRepository {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public TrainRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long insertTrain(String destination, LocalDateTime departure, int maxCapacity) {

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                        PreparedStatement ps =
                                connection.prepareStatement("insert into trains(destination, max_capacity) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, destination);
                        //ps.setDate(2, departure.toLocalDate());
                        ps.setInt(3, maxCapacity);
                        return ps;
                    }, keyHolder
            );

            return keyHolder.getKey().longValue();
    }


    public Train findById(long id) {
        return jdbcTemplate.queryForObject("select * from trains where id = ?;", (rs, rowNum) ->
                new Train(rs.getLong("id"), rs.getString("destination"), rs.getInt("max_capacity"), rs.getInt("number_of_passengers") ) , id);//
    }

    public List<Train> getTrainsByDestination(String destination) {
        return jdbcTemplate.query("select * from trains where destination = ?;",
                (rs, rowNum)-> new Train(rs.getLong("id"), rs.getString("destination"), rs.getInt("max_capacity"), rs.getInt("number_of_passengers")), destination);
    }

    public void updateNumberOfPassengersById(long id, int amount) {

        jdbcTemplate.update("update trains set number_of_passengers = ? where id = ?;", amount, id);
    }




}
