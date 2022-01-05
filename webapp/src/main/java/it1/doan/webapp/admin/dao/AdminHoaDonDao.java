package it1.doan.webapp.admin.dao;

import it1.doan.webapp.dao.Mapper.DonHangMapper;
import it1.doan.webapp.dao.Mapper.HoaDonMapper;
import it1.doan.webapp.dao.Mapper.ThuongHieuMapper;
import it1.doan.webapp.model.DonHang;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class AdminHoaDonDao extends JdbcDaoSupport {
    public AdminHoaDonDao(DataSource dataSource){
        this.setDataSource(dataSource);
    }

    public List<DonHang> getPagehd(int start , int end , Date startdate , Date enddate){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String sql ="SELECT MAHD,NGAYLAP,SUM(DONGIA*SOLUONG) AS GIATRI" +
                " FROM V_HOADON  " ;
        if(!dateFormat.format(startdate).equals(dateFormat.format(new Date())) && !dateFormat.format(enddate).equals(dateFormat.format(new Date())) ){
            sql += " WHERE NGAYLAP between '" + dateFormat.format(startdate) + "' and '" + dateFormat.format(enddate) + "'";
        }
        sql +=  " GROUP BY MAHD,NGAYLAP " +
                " ORDER BY NGAYLAP DESC OFFSET " + (start-1) + " ROWS  " +
                " FETCH NEXT  "+ end + " ROWS ONLY  ";
        HoaDonMapper mapper = new HoaDonMapper();
        List<DonHang> donHangs= this.getJdbcTemplate().query(sql,mapper);
        return  donHangs;
    }

    public List<DonHang> getAllhd(Date startdate , Date enddate){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String sql ="SELECT MAHD,NGAYLAP,SUM(DONGIA*SOLUONG) AS GIATRI  " +
                "FROM V_HOADON " ;
        if(!dateFormat.format(startdate).equals(dateFormat.format(new Date())) && !dateFormat.format(enddate).equals(dateFormat.format(new Date()))){
            sql += " WHERE NGAYLAP between '" + dateFormat.format(startdate) + "' and '" + dateFormat.format(enddate) + "' ";
        }
        sql +=  "GROUP BY MAHD,NGAYLAP ORDER BY NGAYLAP ";
        HoaDonMapper mapper = new HoaDonMapper();
        List<DonHang> donHangs= this.getJdbcTemplate().query(sql,mapper);
        return  donHangs;
    }

    public List<DonHang> GetCT(String mahd){
        try {
            DonHangMapper mapper = new DonHangMapper();
            String sql ="SELECT * FROM V_HOADON WHERE MAHD = ? ";
            List<DonHang> donHang = this.getJdbcTemplate().query(sql,new Object[]{mahd},mapper);
            return  donHang;
        }catch (EmptyResultDataAccessException e ){
            return null;
        }
    }

    public List<DonHang> GetCTPage(String mahd , int start , int end){
        try {
            DonHangMapper mapper = new DonHangMapper();
            String sql ="SELECT * FROM V_HOADON WHERE mahd = ? " +
                    " ORDER BY NGAYLAP DESC OFFSET " + (start-1) +  " ROWS " +
                    " FETCH NEXT " + end + " ROWS ONLY ";
            List<DonHang> donHang = this.getJdbcTemplate().query(sql,new Object[]{mahd},mapper);
            return  donHang;
        }catch (EmptyResultDataAccessException e ){
            return null;
        }
    }

    public String Delete(String mahd){
        try {
            String sql = "DELETE HOADON WHERE MAHD = '"+ mahd +"' ";
            this.getJdbcTemplate().execute(sql);
            return " xóa thành công hãng cac mã " + mahd;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }


}
