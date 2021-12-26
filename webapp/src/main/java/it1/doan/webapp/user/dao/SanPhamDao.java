package it1.doan.webapp.user.dao;

import it1.doan.webapp.common.ProductSearch;
import it1.doan.webapp.dao.Mapper.HomeSanPhamMapper;
import it1.doan.webapp.model.HomeSanPham;
import it1.doan.webapp.model.SanPham;
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

    public HomeSanPham getspbymasp(String masp){
        String sql = "SELECT * FROM V_SANPHAM " +
                "WHERE STATUS = 1 AND MASP = ?";
        HomeSanPhamMapper mapper = new HomeSanPhamMapper();
        HomeSanPham homeSanPham = this.getJdbcTemplate().queryForObject(sql,new Object[]{masp},mapper);
        return homeSanPham;
    }

    public List<HomeSanPham> getallsp(ProductSearch productSearch){
        String sql = "SELECT DISTINCT  V_SANPHAM.* " +
                " FROM V_SANPHAM LEFT JOIN SANPHAMSIZE ON V_SANPHAM.MASP = SANPHAMSIZE.MASP  " +
                " WHERE STATUS = 1 " ;

        if (productSearch.getMahang() != null && !productSearch.getMahang().isEmpty() && productSearch.getMahang()!="") {
            sql += " and MAHANG= '" + productSearch.getMahang() + "'";
        }
        if (productSearch.getMaloaisp() != null && !productSearch.getMaloaisp().isEmpty() && productSearch.getMaloaisp()!="") {
            sql += " and MALOAISP= '" + productSearch.getMaloaisp() + "'";
        }
        if (productSearch.getMasize() != null && !productSearch.getMasize().isEmpty() && productSearch.getMasize()!="") {
            sql += " and MASIZE= '" + productSearch.getMasize() + "'";
        }
        if (productSearch.getTensp() != null && !productSearch.getTensp().isEmpty()&& productSearch.getTensp()!="") {
            sql += " and TENSP LIKE  N'%" + productSearch.getTensp() + "%'";
        }

        HomeSanPhamMapper mapper = new HomeSanPhamMapper();
        List<HomeSanPham> homeSanPham = this.getJdbcTemplate().query(sql,mapper);
        return homeSanPham;
    }


    public List<HomeSanPham> getspSearch(ProductSearch productSearch, int start , int end ){
        String sql = "SELECT DISTINCT  V_SANPHAM.* " +
                " FROM V_SANPHAM LEFT JOIN SANPHAMSIZE ON V_SANPHAM.MASP = SANPHAMSIZE.MASP  " +
                " WHERE STATUS = 1 " ;
        if (productSearch.getMahang() != null && !productSearch.getMahang().isEmpty() && productSearch.getMahang()!="") {
            sql += " and MAHANG= '" + productSearch.getMahang() + "'";
        }
        if (productSearch.getMaloaisp() != null && !productSearch.getMaloaisp().isEmpty() && productSearch.getMaloaisp()!="") {
            sql += " and MALOAISP= '" + productSearch.getMaloaisp() + "'";
        }
        if (productSearch.getMasize() != null && !productSearch.getMasize().isEmpty() && productSearch.getMasize()!="") {
            sql += " and MASIZE= '" + productSearch.getMasize() + "'";
        }
        if (productSearch.getTensp() != null && !productSearch.getTensp().isEmpty()&& productSearch.getTensp()!="") {
            sql += " and TENSP LIKE  N'%" + productSearch.getTensp() + "%'";
        }
        sql +=  " ORDER BY STATUS DESC OFFSET " + (start-1) + "ROWS  " +
                " FETCH NEXT  "+ end + "ROWS ONLY  ";

        HomeSanPhamMapper mapper = new HomeSanPhamMapper();
        List<HomeSanPham> homeSanPham = this.getJdbcTemplate().query(sql,mapper);
        return homeSanPham;
    }


}
