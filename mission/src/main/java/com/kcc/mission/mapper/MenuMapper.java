package com.kcc.mission.mapper;

import com.kcc.mission.bean.Menu;
import com.kcc.mission.bean.Restaurant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    public List<Menu> getAllMenus();
    public Menu getOneMenu();
    public void insertMenu(Menu menu);
    public void updateMenu(Menu menu);
    public void deleteMenu(long id);
}