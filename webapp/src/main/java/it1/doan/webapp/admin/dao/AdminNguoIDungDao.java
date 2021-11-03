package it1.doan.webapp.admin.dao;

import it1.doan.webapp.dao.Mapper.NguoIDungMapper;
import it1.doan.webapp.dao.Mapper.SanPhamMapper;
import it1.doan.webapp.dao.Mapper.ThuongHieuMapper;
import it1.doan.webapp.model.NguoiDung;
import it1.doan.webapp.model.SanPham;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AdminNguoIDungDao extends JdbcDaoSupport {
    @Autowired
    public AdminNguoIDungDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<NguoiDung> getPagend(int start , int end){
        String sql ="SELECT * FROM NGUOIDUNG ORDER BY ID DESC OFFSET " + (start-1) + "ROWS  " +
                " FETCH NEXT  "+ end + "ROWS ONLY  ";
        NguoIDungMapper mapper = new NguoIDungMapper();
        List<NguoiDung> nguoiDungs= this.getJdbcTemplate().query(sql,mapper);
        return  nguoiDungs;
    }

    public List<NguoiDung> getAllnd(){
        String sql ="SELECT * FROM NGUOIDUNG ";
        NguoIDungMapper mapper = new NguoIDungMapper();
        List<NguoiDung> nguoiDungs= this.getJdbcTemplate().query(sql,mapper);
        return  nguoiDungs;
    }
}
