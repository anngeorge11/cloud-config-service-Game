package com.trilogyed.levelupservice.dao;

import com.trilogyed.levelupservice.model.Levelup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LevelupDaoJdbcTemplateImpl implements LevelupDao {

    private static final String INSERT_LEVELUP_SQL =
            "insert into level_up (customer_id, points, member_date) values (?, ?, ?)";
    private static final String SELECT_LEVELUP_BY_ID_SQL =
            "select * from level_up where level_up_id = ?";
    private static final String SELECT_ALL_LEVELUPS_SQL =
            "select * from level_up";

    private static final String UPDATE_LEVELUP_SQL =
            "update level_up set customer_id = ?, points = ?, member_date = ? where level_up_id = ?";
    private static final String DELETE_LEVELUP_SQL =
            "delete from level_up where level_up_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public LevelupDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Levelup createLevelup(Levelup levelup) {
        jdbcTemplate.update(INSERT_LEVELUP_SQL,
                levelup.getCustomer_id(),
                levelup.getMember_date(),
                levelup.getPoints());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        levelup.setLevel_up_id(id);

        return levelup;
    }

    @Override
    public Levelup getLevelup(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_LEVELUP_BY_ID_SQL, this::mapRowToLevelup, id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Levelup> getAllLevelups() {
        return jdbcTemplate.query(SELECT_ALL_LEVELUPS_SQL, this :: mapRowToLevelup);
    }

    @Override
    public void updateLevelup(Levelup levelup) {
        jdbcTemplate.update(UPDATE_LEVELUP_SQL,
                levelup.getCustomer_id(),
                levelup.getMember_date(),
                levelup.getPoints(),
                levelup.getLevel_up_id());


    }

    @Override
    public void deleteLevelup(int id) {
        jdbcTemplate.update(DELETE_LEVELUP_SQL, id);

    }

    private Levelup mapRowToLevelup(ResultSet rs, int rowNum) throws SQLException {
        Levelup m1 = new Levelup();
        m1.setLevel_up_id(rs.getInt("level_up_id"));
        m1.setCustomer_id(rs.getInt("customer_id"));
        m1.setMember_date(rs.getDate("member_date").toLocalDate());
        m1.setPoints(rs.getInt("points"));

        return m1;
    }
}
