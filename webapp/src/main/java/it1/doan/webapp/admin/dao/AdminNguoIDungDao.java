package it1.doan.webapp.admin.dao;


import com.sun.istack.internal.NotNull;
import it1.doan.webapp.dao.Mapper.NguoIDungMapper;
import it1.doan.webapp.dao.Mapper.ThuongHieuMapper;
import it1.doan.webapp.model.NguoiDung;

import it1.doan.webapp.model.ThuongHieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


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

    public NguoiDung existUser(String email){
        String sql = "SELECT * FROM NGUOIDUNG WHERE STATUS = 1 AND EMAIL=? ";
        Object[] params = new Object[]{email};
        NguoIDungMapper userMapper = new NguoIDungMapper();
        try {
            NguoiDung user = this.getJdbcTemplate().queryForObject(sql,userMapper,params);
            return user;
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    public void Insert (NguoiDung nguoiDung){
        try {
            String sql = "INSERT INTO NGUOIDUNG(HOTEN,SDT ,EMAIL,MK,STATUS,QUYEN) VALUES (?,?,?,?,?,?) ";
            this.getJdbcTemplate().update(sql,nguoiDung.getHoten(),nguoiDung.getSdt(),nguoiDung.getEmail(),nguoiDung.getMk(),nguoiDung.getStatus(),nguoiDung.getQuyen());
        }catch (EmptyResultDataAccessException e){
            e.getMessage();
        }
    }

    public String Update(NguoiDung nguoiDung){
        try {
            String sql = "UPDATE NGUOIDUNG SET HOTEN = ? , SDT = ? , EMAIL = ? , STATUS = ? , QUYEN = ?   WHERE ID = ? ";
            this.getJdbcTemplate().update(sql,nguoiDung.getHoten(),nguoiDung.getSdt(),nguoiDung.getEmail(),nguoiDung.getStatus(),nguoiDung.getQuyen(),nguoiDung.getID());
            return "cập nhật thành công ";
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public NguoiDung Get(int ID){
        try {
            NguoIDungMapper mapper = new NguoIDungMapper();
            String sql = "SELECT * FROM NGUOIDUNG WHERE ID = ?" ;
            NguoiDung nguoiDung = this.getJdbcTemplate().queryForObject(sql,new Object[]{ID},mapper);
            return nguoiDung;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public String Delete(int ID , int status){
        try {
            String sql = "UPDATE NGUOIDUNG SET STATUS = "+status+" WHERE ID = ? ";
            this.getJdbcTemplate().update(sql,ID);
            return " xóa thành công hãng cac mã " + ID;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

}
