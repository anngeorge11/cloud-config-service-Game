package com.trilogyed.levelupservice.dao;

import com.trilogyed.levelupservice.model.Levelup;

import java.util.List;

public interface LevelupDao {

    public Levelup createLevelup(Levelup levelup);
    public Levelup getLevelup(int id);
    public List<Levelup> getAllLevelups();
    // public List<Customer> getCustomersByCategory(String category);
    public void updateLevelup(Levelup levelup);
    public void deleteLevelup(int id);
}
