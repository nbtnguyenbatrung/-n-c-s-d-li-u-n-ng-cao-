package it1.doan.webapp.admin.dao;

import it1.doan.webapp.dao.Mapper.ThuongHieuMapper;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AdminThuongHieuDAO extends JdbcDaoSupport {
    @Autowired
    public AdminThuongHieuDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<ThuongHieu> getPagethuonghieu(int start , int end , String keyword){
        try{
            String sql ="SELECT * FROM HANG WHERE STATUS = 1 " ;
            if(keyword != ""){
                sql += " AND MAHANG LIKE  N'%" + keyword + "%'  OR TENHANG LIKE  N'%" + keyword + "%'";
            }
            sql +=  " ORDER BY MAHANG DESC OFFSET " + (start-1) + "ROWS  " +
                    " FETCH NEXT  "+ end + "ROWS ONLY  ";
            ThuongHieuMapper mapper = new ThuongHieuMapper();
            List<ThuongHieu> thuongHieus= this.getJdbcTemplate().query(sql,mapper);
            return  thuongHieus;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<ThuongHieu> getAllthuonghieu(int status){
        try {
            if (status == 2){
                String sql = "SELECT * FROM HANG ORDER BY MAHANG DESC";
                ThuongHieuMapper mapper = new ThuongHieuMapper();
                List<ThuongHieu> thuongHieus = this.getJdbcTemplate().query(sql,mapper);
                return thuongHieus;
            }
            if(status == 1){
                String sql = "SELECT * FROM HANG WHERE STATUS = " + status;
                ThuongHieuMapper mapper = new ThuongHieuMapper();
                List<ThuongHieu> thuongHieus = this.getJdbcTemplate().query(sql,mapper);
                return thuongHieus;
            }
            return null;

        }
        catch (EmptyResultDataAccessException e){
            return  null;
        }

    }

    public List<ThuongHieu> getallthuonghieu(String keyword) {

        String sql = "SELECT * FROM HANG WHERE STATUS = 1";
        if(keyword!=""){
            sql += " AND MAHANG LIKE  N'%" + keyword + "%'  OR TENHANG LIKE  N'%" + keyword + "%'";
        }

        ThuongHieuMapper mapper = new ThuongHieuMapper();
        try {
            List<ThuongHieu> thuongHieus = this.getJdbcTemplate().query(sql, mapper);
            return thuongHieus;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public String Insert(ThuongHieu thuongHieu){
        try {
            String sql = "INSERT INTO HANG(MAHANG,TENHANG ,LOGO,STATUS) VALUES (?,?,?,1) ";
            this.getJdbcTemplate().update(sql,thuongHieu.getMaHang(),thuongHieu.getTenHang(),thuongHieu.getLogo());
            return "thêm sản phẩm thành công ";
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public String Update(ThuongHieu thuongHieu){
        try {
            String sql = "UPDATE HANG SET TENHANG = ? , LOGO = ? WHERE MAHANG = ? ";
            this.getJdbcTemplate().update(sql,thuongHieu.getTenHang(),thuongHieu.getLogo(),thuongHieu.getMaHang());
            return "cập nhật thành công ";
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public ThuongHieu Get(String mahang){
        try {
            ThuongHieuMapper mapper = new ThuongHieuMapper();
            String sql = "SELECT * FROM HANG WHERE MAHANG = ?" ;
            ThuongHieu thuongHieus = this.getJdbcTemplate().queryForObject(sql,new Object[]{mahang},mapper);
            return thuongHieus;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public String Delete(String mahang){
        try {
            String sql = "UPDATE HANG SET STATUS = 0 WHERE MAHANG = ? ";
            this.getJdbcTemplate().update(sql,mahang);
            return " xóa thành công hãng cac mã " + mahang;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }
}
