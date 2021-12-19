package it1.doan.webapp.user.dao;

import it1.doan.webapp.dao.Mapper.GioHangMapper;
import it1.doan.webapp.model.GioHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GiaoHangDao extends JdbcDaoSupport {

    @Autowired
    public GiaoHangDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<GioHang> getghbynd(int id){
        String sql = "SELECT * FROM V_GIOHANG WHERE ID = "+id;
        GioHangMapper mapper = new GioHangMapper();
        List<GioHang> gioHangs = this.getJdbcTemplate().query(sql,mapper);
        return gioHangs;
    }

    public void Insert(GioHang gioHang){
        try {
            String sql = "INSERT INTO GIOHANG(ID,MASP ,MASIZE,SOLUONG) VALUES (?,?,?,?)";
            this.getJdbcTemplate().update(sql,gioHang.getID(),gioHang.getMaSP(),gioHang.getMaSize(),gioHang.getSoluong());
        }catch (EmptyResultDataAccessException e){
            e.getMessage();
        }
    }

    public void update(GioHang gioHang){
        try {
            String sql = "UPDATE GIOHANG SET SOLUONG = ? WHERE ID = ? AND MASP = ? AND MASIZE = ?";
            this.getJdbcTemplate().update(sql , gioHang.getSoluong(),gioHang.getID(),gioHang.getMaSP(),gioHang.getMaSize());
        }catch (EmptyResultDataAccessException e){
            e.getMessage();
        }
    }

    public void updateadd(GioHang gioHang){
        try {
            String sql = "UPDATE GIOHANG SET SOLUONG = SOLUONG + ? WHERE ID = ? AND MASP = ? AND MASIZE = ?";
            this.getJdbcTemplate().update(sql , gioHang.getSoluong(),gioHang.getID(),gioHang.getMaSP(),gioHang.getMaSize());
        }catch (EmptyResultDataAccessException e){
            e.getMessage();
        }
    }

    public void delete(GioHang gioHang){

        String sql = " DELETE FROM GIOHANG " +
                " WHERE ID = " + gioHang.getID() +
                " AND MASP = '" + gioHang.getMaSP() +
                "' AND MASIZE='" + gioHang.getMaSize() + "' ";
        this.getJdbcTemplate().execute(sql);
    }

    public void delete (List<GioHang> gioHangs){
        String sql = "";
        for(int i = 0 ; i < gioHangs.size() ; i++ ){
            sql = sql + " DELETE FROM GIOHANG " +
                    " WHERE ID = " + gioHangs.get(i).getID() +
                    " AND MASP = '" + gioHangs.get(i).getMaSP() +
                    "' AND MASIZE='" + gioHangs.get(i).getMaSize() + "' \n ";
        }

        this.getJdbcTemplate().execute(sql);
    }

    public void delete (int id){
        String sql = " DELETE FROM GIOHANG " +
                " WHERE ID = " + id ;
        this.getJdbcTemplate().execute(sql);
    }

    public boolean getgiohang(GioHang gioHang){
        String sql = "SELECT * FROM V_GIOHANG WHERE ID = " + gioHang.getID() +
                " AND MASP = '"+gioHang.getMaSP()+"' AND MASIZE = '"+gioHang.getMaSize()+"' ";
        GioHangMapper mapper = new GioHangMapper();
        List<GioHang> gioHangs = this.getJdbcTemplate().query(sql,mapper);
        if(gioHangs.size() > 0 ){
            return true;
        }
        return false;
    }

    public List<GioHang> getgiohangbyone(GioHang gioHang){
        String sql = "SELECT * FROM V_GIOHANG WHERE ID = " + gioHang.getID() +
                " AND MASP = '"+gioHang.getMaSP()+"' AND MASIZE = '"+gioHang.getMaSize()+"' ";
        GioHangMapper mapper = new GioHangMapper();
        List<GioHang> gioHangs = this.getJdbcTemplate().query(sql,mapper);
        return gioHangs;
    }
}
