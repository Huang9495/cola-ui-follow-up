package com.colaui.system.service;

import com.colaui.example.model.ColaRole;
import com.colaui.provider.Page;

import java.util.List;

public interface ColaRoleService {
    Page<ColaRole> getPage(int pageSize, int pageNo, String contain);

    void save(ColaRole role);

    void delete(long id);

    void update(ColaRole role);

    ColaRole find(long id);

    List<ColaRole> find(int from, int limit);
}