package com.colaui.system.service.impl;

import com.colaui.system.model.ColaComponent;
import com.colaui.helper.Page;
import com.colaui.system.dao.ColaComponentDao;
import com.colaui.system.service.ColaComponentService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ColaComponentServiceImpl implements ColaComponentService {
    @Autowired
    private ColaComponentDao componentDao;

    public Page<ColaComponent> getPage(int pageSize, int pageNo, String contain) {
        Criteria criteria = componentDao.createCriteria();
        if (StringUtils.isNotEmpty(contain)) {
            Criterion lastRest = Restrictions.like("lastName", contain, MatchMode.ANYWHERE);
            Criterion firstRest = Restrictions.like("firstName", contain, MatchMode.ANYWHERE);
            criteria.add(Restrictions.or(lastRest, firstRest));
        }
        return componentDao.getPage(pageSize, pageNo, criteria);
    }

    public void save(ColaComponent component) {
        componentDao.save(component);
    }

    public void delete(long id) {
        componentDao.delete(id);
    }

    public void update(ColaComponent component) {
        componentDao.update(component);
    }

    public ColaComponent find(long id) {
        return componentDao.get(id);
    }

    public List<ColaComponent> find(int from, int limit) {
        return componentDao.find(from, limit);
    }

    @Override
    public Map<String, Boolean> getComponentAuth(String url, String componentId) {
        Map<String, String> params = new HashMap<>();
        params.put("url", url);
        params.put("componentId", componentId);
        Map<String, Boolean> rs = componentDao.getComponentAuth(params);
        return rs;
    }

}