package com.kcc.mission.service;

import com.kcc.mission.bean.Menu;
import com.kcc.mission.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuMapper mapper;

    public List<Menu> getAllMenus() {
        return mapper.getAllMenus();
    }

    public Menu getOneMenu() {
        return mapper.getOneMenu();
    }

    public void insertMenu(Menu menu) {
        mapper.insertMenu(menu);
    }

    public void updateMenu(long id, Menu menu) {
        menu.setId(id);
        mapper.updateMenu(menu);
    }

    public void deleteMenu(long id) {
        mapper.deleteMenu(id);
    }
}
