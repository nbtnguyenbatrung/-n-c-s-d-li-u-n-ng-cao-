package it1.doan.webapp.admin.dao;

import it1.doan.webapp.dao.Mapper.SanPhamKhuyenMaiMapper;
import it1.doan.webapp.dao.Mapper.SanPhamMapper;
import it1.doan.webapp.dao.Mapper.SanPhamSizeMapper;
import it1.doan.webapp.dao.Mapper.ThuongHieuMapper;
import it1.doan.webapp.model.SanPham;
import it1.doan.webapp.model.SanPhamKhuyenMai;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AdminSanPhamKhuyenMaiDao extends JdbcDaoSupport {

    @Autowired
    public AdminSanPhamKhuyenMaiDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<SanPhamKhuyenMai> getPagespkm(int start , int end){
        String sql ="SELECT * , DATEDIFF(day, GETDATE(), KHUYENMAI.NGAYKT) AS SONGAY " +
                " FROM SANPHAM INNER JOIN KHUYENMAI ON SANPHAM.MAKM = KHUYENMAI.MAKM " +
                " ORDER BY KHUYENMAI.NGAYBD DESC OFFSET " + (start-1) + "ROWS  " +
                " FETCH NEXT  "+ end + "ROWS ONLY  ";
        SanPhamKhuyenMaiMapper mapper = new SanPhamKhuyenMaiMapper();
        List<SanPhamKhuyenMai> sanPhamKhuyenMais = this.getJdbcTemplate().query(sql,mapper);
        return  sanPhamKhuyenMais;
    }

    public List<SanPhamKhuyenMai> getAllspkm(){
        String sql ="SELECT * , DATEDIFF(day, GETDATE(), KHUYENMAI.NGAYKT) AS SONGAY " +
                "FROM SANPHAM INNER JOIN KHUYENMAI ON SANPHAM.MAKM = KHUYENMAI.MAKM ";
        SanPhamKhuyenMaiMapper mapper = new SanPhamKhuyenMaiMapper();
        List<SanPhamKhuyenMai> sanPhamKhuyenMai= this.getJdbcTemplate().query(sql,mapper);
        return  sanPhamKhuyenMai;
    }
}
