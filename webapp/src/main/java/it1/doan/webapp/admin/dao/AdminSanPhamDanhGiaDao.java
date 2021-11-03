package it1.doan.webapp.admin.dao;

import it1.doan.webapp.dao.Mapper.SanPhamDanhGiaMapper;
import it1.doan.webapp.dao.Mapper.SanPhamMapper;
import it1.doan.webapp.dao.Mapper.ThuongHieuMapper;
import it1.doan.webapp.model.SanPham;
import it1.doan.webapp.model.SanPhamDanhGia;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AdminSanPhamDanhGiaDao extends JdbcDaoSupport {
    @Autowired
    public AdminSanPhamDanhGiaDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<SanPhamDanhGia> getAllspdg(){
        String sql ="SELECT * FROM SANPHAM ";
        SanPhamDanhGiaMapper mapper = new SanPhamDanhGiaMapper();
        List<SanPhamDanhGia> sanPhamDanhGias= this.getJdbcTemplate().query(sql,mapper);
        return  sanPhamDanhGias;
    }
}
