package it1.doan.webapp.user.dao;

import it1.doan.webapp.dao.Mapper.DanhGiaUserMapper;
import it1.doan.webapp.dao.Mapper.purchaseMapper;
import it1.doan.webapp.model.DanhGiaByUser;
import it1.doan.webapp.model.purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class purchaseDao extends JdbcDaoSupport {

    @Autowired
    public purchaseDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public void updatepass(int id , String passnew){
        String sql = "UPDATE NGUOIDUNG SET MK = ? WHERE ID = ?";
        this.getJdbcTemplate().update(sql , passnew , id);
    }

    public List<purchase> getallpurchase( int id,int type ){
        if(type < 0){
            String sql = "SELECT * FROM V_PURCHASE WHERE ID = "+ id ;
            purchaseMapper mapper = new purchaseMapper();
            List<purchase> purchases = this.getJdbcTemplate().query(sql,mapper);
            return purchases;
        }else {
            String sql = "SELECT * FROM V_PURCHASE WHERE TRANGTHAI =" + type + "AND ID = " +id;
            purchaseMapper mapper = new purchaseMapper();
            List<purchase> purchases = this.getJdbcTemplate().query(sql, mapper);
            return purchases;
        }
    }
    public List<purchase> getdonhang(int id,int type , int start , int end){

        String sql = "";
        if(type < 0 ){
            sql = "SELECT * FROM V_PURCHASE WHERE ID = " + id +
                    " ORDER BY NGAYLAP DESC OFFSET " + (start-1) + "ROWS  " +
                    " FETCH NEXT  "+ end + "ROWS ONLY  ";
        }
        if(type == 0){
            sql = "SELECT * FROM V_PURCHASE WHERE TRANGTHAI = " + type + "AND ID = " + id +
                    " ORDER BY NGAYLAP DESC OFFSET " + (start-1) + "ROWS  " +
                    " FETCH NEXT  "+ end + "ROWS ONLY  ";
        }
        if(type == 1){
            sql = "SELECT * FROM V_PURCHASE WHERE TRANGTHAI = " + type + "AND ID = " + id +
                    " ORDER BY NGAYLAP DESC OFFSET " + (start-1) + "ROWS  " +
                    " FETCH NEXT  "+ end + "ROWS ONLY  ";
        }
        if(type == 2){
            sql = "SELECT * FROM V_PURCHASE WHERE TRANGTHAI = " + type + "AND ID = " + id +
                    " ORDER BY NGAYLAP DESC OFFSET " + (start-1) + "ROWS  " +
                    " FETCH NEXT  "+ end + "ROWS ONLY  ";
        }

        purchaseMapper mapper = new purchaseMapper();
        List<purchase> purchases = this.getJdbcTemplate().query(sql,mapper);
        return purchases;
    }

    public void updateuserdh(String mahd , String masp , String masize){
        String sql = "UPDATE CHITIETHOADON SET TRANGTHAI = 2 WHERE MAHD = ? AND MASP = ? AND MASIZE = ? ";
        this.getJdbcTemplate().update(sql,mahd,masp,masize);
    }

    public void Insert(String madg , int id , String masp , int sosao , String binhluan ){
        String sql = "INSERT INTO DANHGIA(MADG,ID,MASP,SOSAO,BINHLUAN) VALUES (?,?,?,?,?)";
        this.getJdbcTemplate().update(sql,madg,id,masp,sosao,binhluan);
    }

    public List<DanhGiaByUser> getallDanhgia(){
        String sql = "SELECT * FROM DANHGIA ORDER BY MADG DESC";
        DanhGiaUserMapper mapper = new DanhGiaUserMapper();
        List<DanhGiaByUser> danhGiaByUsers = this.getJdbcTemplate().query(sql,mapper);
        return danhGiaByUsers;
    }
}
