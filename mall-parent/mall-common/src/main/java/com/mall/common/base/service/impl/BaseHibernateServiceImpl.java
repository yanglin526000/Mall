package com.mall.common.base.service.impl;

import com.mall.common.base.service.BaseHibernateService;
import com.mall.common.utils.ConstantUtil;
import com.mall.common.utils.ParamUtil;
import com.mall.common.utils.ResultMap;
import com.mall.common.utils.SnowflakeIdWorker;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 描述: 公共持久层接口实现
 * </p>
 * <p>
 * 创建时间: 2019-11-15 09:38
 * </p>
 *
 * @param <T> 标签
 * @author yanglin
 */
@Service
public class BaseHibernateServiceImpl<T> implements BaseHibernateService<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public T save(T t) {
        Object idO = ParamUtil.getField(t, "id");
        Object o = idO == null ? ParamUtil.getCustomConstructor(t.getClass()) : entityManager.find(t.getClass(), idO);
        ParamUtil.putValuesToObject(t, o);
        return (T) entityManager.merge((T) o.getClass().cast(o));
    }

    @SuppressWarnings({"unchecked"})
    @Transactional
    @Override
    public T delete(T t) {
        Object idO = ParamUtil.getField(t, "id");
        Object o = entityManager.find(t.getClass(), idO);
        entityManager.remove(o);
        return (T) o;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T info(T t) {
        Object idO = ParamUtil.getField(t, "id");
        return (T) entityManager.find(t.getClass(), idO);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> list(T t, Pageable pageable) {
        Map<String, Object> result = new HashMap<>(ConstantUtil.RESULT_MAP_INIT_COUNT);
        // 设置条件
        String simName = t.getClass().getSimpleName();
        StringBuilder sqlCondition = new StringBuilder(" WHERE 1 = 1");
        StringBuilder sqlSort = new StringBuilder(" ORDER BY " + simName + ".id DESC ");
        List<Field> fs = ParamUtil.getSelfAndSuperClassFields(t);
        for (Field f : fs) {
            Object fieldValue = ParamUtil.getField(t, f);
            if (fieldValue != null && !"".equals(fieldValue.toString().trim())) {
                if (String.class.getName().equals(f.getType().getName())) {
                    sqlCondition.append(" AND ").append(simName).append(".").append(f.getName()).append(" LIKE '%").append(fieldValue).append("%'");
                } else {
                    sqlCondition.append(" AND ").append(simName).append(".").append(f.getName()).append("='").append(fieldValue).append("'");
                }
            }
        }
        // 查询列表
        Query queryList = entityManager.createQuery("FROM " + simName + " AS " + simName + sqlCondition + sqlSort);
        queryList.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        queryList.setMaxResults(pageable.getPageSize());
        List<T> list = queryList.getResultList();
        result.put("list", list);
        // 查询总数
        Query queryCount = entityManager.createQuery("SELECT COUNT(1) FROM " + simName + " AS " + simName + sqlCondition);
        Long count = (Long) queryCount.getSingleResult();
        // 分页信息
        return ResultMap.pageInfo(result, count, pageable.getPageNumber(), pageable.getPageSize());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> listAccurate(T t, Pageable pageable) {
        Map<String, Object> result = new HashMap<>(ConstantUtil.RESULT_MAP_INIT_COUNT);
        // 设置条件
        String simName = t.getClass().getSimpleName();
        StringBuilder sqlCondition = new StringBuilder(" WHERE 1 = 1");
        StringBuilder sqlSort = new StringBuilder(" ORDER BY " + simName + ".id DESC ");
        List<Field> fs = ParamUtil.getSelfAndSuperClassFields(t);
        for (Field f : fs) {
            Object fieldValue = ParamUtil.getField(t, f);
            if (fieldValue != null && !"".equals(fieldValue.toString().trim())) {
                sqlCondition.append(" AND " + simName + "." + f.getName() + "='" + fieldValue + "'");
            }
        }
        // 查询列表
        Query queryList = entityManager.createQuery("FROM " + simName + " AS " + simName + sqlCondition + sqlSort);
        queryList.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        queryList.setMaxResults(pageable.getPageSize());
        List<T> list = queryList.getResultList();
        result.put("list", list);
        // 查询总数
        Query queryCount = entityManager.createQuery("SELECT COUNT(1) FROM " + simName + " AS " + simName + sqlCondition);
        Long count = (Long) queryCount.getSingleResult();
        // 分页信息
        return ResultMap.pageInfo(result, count, pageable.getPageNumber(), pageable.getPageSize());
    }

    @Override
    public Map<String, Object> listAccurate(T t) {
        Pageable pageable = PageRequest.of(
                Integer.parseInt(ConstantUtil.DEFAULT_PAGE_INDEX),
                Integer.parseInt(ConstantUtil.DEFAULT_PAGE_SIZE)
        );
        return listAccurate(t, pageable);
    }


    @Transactional
    @Override
    public List<T> batchAdd(List<T> t) {
        for (T o : t) {
            ParamUtil.putField(o, "id", SnowflakeIdWorker.nextIdString());
            entityManager.persist(o);
        }
        return t;
    }
}
