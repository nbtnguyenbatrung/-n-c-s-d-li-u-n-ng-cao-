package it1.doan.webapp.admin.dao;

import it1.doan.webapp.dao.Mapper.DonHangMapper;
import it1.doan.webapp.dao.Mapper.HoaDonMapper;
import it1.doan.webapp.dao.Mapper.SanPhamMapper;
import it1.doan.webapp.dao.Mapper.ThuongHieuMapper;
import it1.doan.webapp.model.DonHang;
import it1.doan.webapp.model.SanPham;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AdminDonHangDao extends JdbcDaoSupport {
    @Autowired
    public AdminDonHangDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<DonHang> getPagedh(int start , int end){
        String sql ="SELECT * FROM V_HOADON WHERE TRANGTHAI = 0 ORDER BY NGAYLAP" +
                " DESC OFFSET " + (start-1) + "ROWS  " +
                " FETCH NEXT  "+ end + "ROWS ONLY  ";
        DonHangMapper mapper = new DonHangMapper();
        List<DonHang> donHangs= this.getJdbcTemplate().query(sql,mapper);
        return  donHangs;
    }

    public List<DonHang> getAlldh(){
        String sql ="SELECT * FROM V_HOADON WHERE TRANGTHAI = 0 ORDER BY NGAYLAP ";
        DonHangMapper mapper = new DonHangMapper();
        List<DonHang> donHangs= this.getJdbcTemplate().query(sql,mapper);
        return  donHangs;
    }

    public DonHang Get(String mahd , String masp , String masize){
        try {
            String sql = "SELECT * FROM V_HOADON WHERE MAHD = ? and MASP = ? AND MASIZE = ? ";
            DonHangMapper mapper = new DonHangMapper();
            DonHang donHang = this.getJdbcTemplate().queryForObject(sql , new Object[]{mahd,masp,masize} , mapper);
            return  donHang;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public void Insert(int trangthai , List<String> list  ){
        String dk,mahd,masp,masize,sql = "";
        for(int i = 0 ; i < list.size() ; i++ ){
            dk = list.get(i);
            mahd =  dk.substring(2,dk.indexOf("SP") );
            masp = dk.substring(dk.indexOf("SP"),dk.indexOf("SI"));
            if(i != list.size()-1) {
                masize = dk.substring(dk.indexOf("SI"),dk.length()-1);
            }else{
                masize = dk.substring(dk.indexOf("SI"),dk.length()-2);
            }
                sql = sql + " UPDATE CHITIETHOADON SET TRANGTHAI = " + trangthai + " WHERE MAHD = '" + mahd + "' AND  MASP = '" + masp + "' AND MASIZE='" + masize + "' \n ";

        }
        this.getJdbcTemplate().execute(sql);
    }

}
