package com.trilogyed.levelupservice;

import com.trilogyed.levelupservice.dao.LevelupDao;
import com.trilogyed.levelupservice.model.Levelup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LevelUpDaoTest {

    @Autowired
    protected LevelupDao dao;

    @Before
    public void setUp() throws Exception {
        // clean out the test db
        List<Levelup> mList = dao.getAllLevelups();

        mList.stream()
                .forEach(inventory -> dao.deleteLevelup(inventory.getLevel_up_id()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteLevelup() {
        Levelup levelup = new Levelup();
        levelup.setCustomer_id(1);
        levelup.setMember_date(LocalDate.of(2019,10,10));
        levelup.setPoints(20);

        levelup = dao.createLevelup(levelup);

        Levelup levelup2 = dao.getLevelup(levelup.getLevel_up_id());
        assertEquals(levelup.toString(), levelup2.toString());
        dao.deleteLevelup(levelup.getLevel_up_id());
        assertNull(levelup2);

    }


    @Test
    public void getAllLevelups() {
        Levelup levelup = new Levelup();
        levelup.setCustomer_id(1);
        levelup.setMember_date(LocalDate.of(2019,10,10));
        levelup.setPoints(20);

        levelup = dao.createLevelup(levelup);

        List<Levelup> inventoryList = dao.getAllLevelups();

        assertEquals(inventoryList.size(), 1);
    }


    @Test
    public void updateLevelup() {
        Levelup levelup = new Levelup();
        levelup.setCustomer_id(1);
        levelup.setMember_date(LocalDate.of(2019,10,10));
        levelup.setPoints(20);

        levelup = dao.createLevelup(levelup);

        levelup.setPoints(10);

        dao.updateLevelup(levelup);
        Levelup levelup1 =  dao.getLevelup(levelup.getLevel_up_id());


        assertEquals(levelup1.toString(), levelup.toString());

    }

}
