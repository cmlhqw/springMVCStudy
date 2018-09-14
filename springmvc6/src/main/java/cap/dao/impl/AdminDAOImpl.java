package cap.dao.impl;


import cap.dao.AdminDAO;
import cap.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wolftrace
 * @version 1.0.0
 * @createTime 2018-09-13 9:53
 */


@Repository
public class AdminDAOImpl implements AdminDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Admin login(Admin admin) {
        String sql = "select * from admin where username=? and password=?";
        List<Admin> list = jdbcTemplate.query(sql, new Object[]{admin.getUsername(), admin.getPassword()}, ParameterizedBeanPropertyRowMapper.newInstance(Admin.class));
        if (list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Admin> list() {
        String sql = "select * from admin";
        return jdbcTemplate.queryForList(sql,Admin.class);
    }

    @Override
    public Admin getById(Integer id) {
        String sql = "select * from admin where id = ?";
        List<Admin> list = jdbcTemplate.query(sql, new Object[]{id}, ParameterizedBeanPropertyRowMapper.newInstance(Admin.class));
        if (list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public Integer insert(Admin admin) {
        String sql = "insert into admin(username,password) value(?,?)";
        return jdbcTemplate.update(sql,new Object[]{admin.getUsername(),admin.getPassword()});
    }

    @Override
    public Integer update(Admin admin) {
        String sql = "update admin set username = ?,password = ? where id = ?";
        return jdbcTemplate.update(sql,new Object[]{admin.getUsername(),admin.getPassword(),admin.getId()});
    }

    @Override
    public Integer delete(Integer id) {
        String sql = "delete from admin where id = ?";
        return jdbcTemplate.update(sql,new Object[]{id});
    }

    @Override
    public List<Admin> getByPage(Integer pageNo, Integer pageSize) {
        String sql = "select * from admin limit ?,?";
        int start = (pageNo - 1) * pageSize;
        return jdbcTemplate.query(sql, new Object[]{start, pageSize}, ParameterizedBeanPropertyRowMapper.newInstance(Admin.class));
    }

    @Override
    public Integer getTotalCount() {
        String sql = "select count(*) from admin";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }



}
