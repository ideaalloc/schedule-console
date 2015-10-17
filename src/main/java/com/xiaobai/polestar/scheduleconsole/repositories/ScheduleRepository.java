package com.xiaobai.polestar.scheduleconsole.repositories;

import com.xiaobai.polestar.scheduleconsole.models.Schedules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * Title.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-10-17
 */
@Repository
public class ScheduleRepository {
    @Autowired
    JdbcTemplate jdbc;

    public Long insertSchedule(final String name, final String cron, final String command, final Boolean enabled) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement("INSERT INTO POLESTAR_SCHEDULES (NAME, CRON, COMMAND, ENABLED) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, name);
                    ps.setString(2, cron);
                    ps.setString(3, command);
                    ps.setBoolean(4, enabled);
                    return ps;
                }, keyHolder
        );
        return keyHolder.getKey().longValue();
    }

    public List<Schedules> findAll(final Boolean enabled) {
        return jdbc.query("SELECT ID, NAME, CRON, COMMAND, ENABLED, CREATE_TIME FROM POLESTAR_SCHEDULES WHERE ENABLED = ?", (resultSet, i) -> new Schedules(resultSet.getLong("ID"), resultSet.getString("NAME"), resultSet.getString("CRON"), resultSet.getString("COMMAND"), resultSet.getBoolean("ENABLED"), resultSet.getTimestamp("CREATE_TIME")), enabled);
    }
}
