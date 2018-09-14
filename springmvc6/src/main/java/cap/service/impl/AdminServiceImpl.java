package cap.service.impl;


import cap.dao.AdminDAO;
import cap.entity.Admin;
import cap.service.AdminService;
import cap.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wolftrace
 * @version 1.0.0
 * @createTime 2018-09-13 9:55
 */


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;
    @Override
    public Admin login(Admin admin) {
        return adminDAO.login(admin);
    }

    @Override
    public Admin getById(Integer id) {
        return adminDAO.getById(id);
    }

    @Override
    public PageBean getByPage(Integer pageNo, Integer pageSize) {
        PageBean pageBean = new PageBean();
        pageBean.setList(adminDAO.getByPage(pageNo,pageSize));
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRecords(adminDAO.getTotalCount());
        return pageBean;
    }

    @Override
    public Integer delete(Integer id) {
        return adminDAO.delete(id);
    }

    @Override
    public Integer update(Admin admin) {
        return adminDAO.update(admin);
    }

    @Override
    public Integer insert(Admin admin) {
        return adminDAO.insert(admin);
    }
}
