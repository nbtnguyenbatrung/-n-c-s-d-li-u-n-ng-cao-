package it1.doan.webapp.admin.dao;

import it1.doan.webapp.admin.excaption.NotFoundException;
import it1.doan.webapp.dao.Mapper.BinhLuanMapper;
import it1.doan.webapp.model.BinhLuan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AdminBinhLuanDao extends JdbcDaoSupport {

    @Autowired
    public AdminBinhLuanDao(DataSource dataSource){
        this.setDataSource(dataSource);
    }

    public List<BinhLuan> getAllbl(String masp , int sosao , String bl , int start , int end){
        String sql = "EXEC BINHLUAN "+ sosao +",'" + bl + "','" + masp + "' ," + (start) + ","+end+" ";
        BinhLuanMapper mapper = new BinhLuanMapper();
        List<BinhLuan> binhLuans = this.getJdbcTemplate().query(sql,mapper);
        return binhLuans;
    }

}
