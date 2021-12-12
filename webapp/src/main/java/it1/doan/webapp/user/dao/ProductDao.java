package it1.doan.webapp.user.dao;

import it1.doan.webapp.dao.Mapper.BinhLuanMapper;
import it1.doan.webapp.dao.Mapper.HinhAnhMapper;
import it1.doan.webapp.dao.Mapper.SizeMapper;
import it1.doan.webapp.model.BinhLuan;
import it1.doan.webapp.model.HinhAnh;
import it1.doan.webapp.model.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ProductDao extends JdbcDaoSupport {

    @Autowired
    public ProductDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Size> getsizebymasp(String masp){
        String sql = "SELECT SIZE.MASIZE , SIZE.TENSIZE " +
                " FROM SIZE INNER JOIN SANPHAMSIZE ON SIZE.MASIZE = SANPHAMSIZE.MASIZE " +
                " WHERE MASP = '"+masp+"' AND STATUS = 1 ";
        SizeMapper mapper = new SizeMapper();
        List<Size> sizes = this.getJdbcTemplate().query(sql,mapper);
        return sizes;
    }

    public List<HinhAnh> gethabymasp(String masp){
        String sql = " SELECT * FROM HINHANH WHERE MASP = '"+masp+"'" ;
        HinhAnhMapper mapper = new HinhAnhMapper();
        List<HinhAnh> hinhAnhs = this.getJdbcTemplate().query(sql,mapper);
        return hinhAnhs;
    }

    public List<BinhLuan> getAllbl(String masp ){
        String sql = "SELECT HOTEN , BINHLUAN , SOSAO , NGAYDG " +
                " FROM DANHGIA INNER JOIN NGUOIDUNG ON DANHGIA.ID = NGUOIDUNG.ID " +
                " WHERE MASP = '"+masp+"' ";
        BinhLuanMapper mapper = new BinhLuanMapper();
        List<BinhLuan> binhLuans = this.getJdbcTemplate().query(sql,mapper);
        return binhLuans;
    }

    public List<BinhLuan> getpagebl(String masp , int start , int end){
        String sql = "SELECT HOTEN , BINHLUAN , SOSAO , NGAYDG " +
                " FROM DANHGIA INNER JOIN NGUOIDUNG ON DANHGIA.ID = NGUOIDUNG.ID " +
                " WHERE MASP = '"+masp+"' " +
                " ORDER BY NGAYDG DESC OFFSET " + (start-1) + "ROWS  " +
                " FETCH NEXT  "+ end + "ROWS ONLY  ";
        BinhLuanMapper mapper = new BinhLuanMapper();
        List<BinhLuan> binhLuans = this.getJdbcTemplate().query(sql,mapper);
        return binhLuans;
    }

}
