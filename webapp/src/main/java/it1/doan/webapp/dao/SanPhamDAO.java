package it1.doan.webapp.dao;

import it1.doan.webapp.dao.Mapper.KhuyenMaiMapper;
import it1.doan.webapp.dao.Mapper.SanPhamMapper;
import it1.doan.webapp.model.KhuyenMai;
import it1.doan.webapp.model.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SanPhamDAO extends JdbcDaoSupport {

    @Autowired
    public SanPhamDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<SanPham> getAllSpTheoLoai(String type){
        String sql ="SELECT * FROM SANPHAM INNER JOIN LOAISANPHAM ON SANPHAM.MALOAISP=LOAISANPHAM.MALOAISP WHERE TENLOAISP="+"'"+type+"'";
        SanPhamMapper mapper = new SanPhamMapper();
        List<SanPham> sanPhams= this.getJdbcTemplate().query(sql,mapper);
        return  sanPhams;
    }

    public List<SanPham> getAllSanPham(){
        String sql ="SELECT * FROM SANPHAM ";
        SanPhamMapper mapper = new SanPhamMapper();
        List<SanPham> sanPhams= this.getJdbcTemplate().query(sql,mapper);
        return  sanPhams;
    }
}
