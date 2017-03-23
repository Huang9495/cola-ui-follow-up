package com.colaui.system.service.impl;

import com.colaui.example.model.ColaRole;
import com.colaui.provider.Page;
import com.colaui.system.dao.ColaRoleDao;
import com.colaui.system.service.ColaRoleService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("roleService")
@Transactional
public class ColaRoleServiceImpl implements ColaRoleService {
    @Autowired
    private ColaRoleDao roleDao;

    public Page<ColaRole> getPage(int pageSize, int pageNo, String contain) {
        Criteria criteria = roleDao.createCriteria();
        if (StringUtils.isNotEmpty(contain)) {
            Criterion lastRest= Restrictions.like("lastName", contain, MatchMode.ANYWHERE);
            Criterion firstRest= Restrictions.like("firstName", contain, MatchMode.ANYWHERE);
            criteria.add(Restrictions.or(lastRest, firstRest));
        }
        return roleDao.getPage(pageSize, pageNo, criteria);
    }

    public void save(ColaRole role) {
        roleDao.save(role);
    }

    public void delete(long id) {
        roleDao.delete(id);
    }

    public void update(ColaRole role) {
        roleDao.update(role);
    }

    public ColaRole find(long id) {
        return roleDao.get(id);
    }

    public List<ColaRole> find(int from, int limit) {
        return roleDao.find(from, limit);
    }

}