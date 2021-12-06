package it1.doan.webapp.user.dao;

import it1.doan.webapp.dao.Mapper.HomeSanPhamMapper;
import it1.doan.webapp.dao.Mapper.SanPhamMapper;
import it1.doan.webapp.model.HomeSanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SanPhamDao extends JdbcDaoSupport {

    @Autowired
    public SanPhamDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<HomeSanPham> gettypesp(String tenloai){
        String sql ="SELECT * FROM V_SANPHAM " +
                " WHERE TENLOAISP = N'"+tenloai+"'" +
                " ORDER BY GIASAU DESC OFFSET 0 ROWS  " +
                " FETCH NEXT  7 ROWS ONLY  ";
        HomeSanPhamMapper mapper = new HomeSanPhamMapper();
        List<HomeSanPham> sanPhams= this.getJdbcTemplate().query(sql,mapper);
        return  sanPhams;
    }


}
