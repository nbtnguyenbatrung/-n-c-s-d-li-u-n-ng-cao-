package it1.doan.webapp.admin.dao;

import it1.doan.webapp.dao.Mapper.SizeMapper;
import it1.doan.webapp.dao.Mapper.ThuongHieuMapper;
import it1.doan.webapp.model.Size;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AdminSizeDao extends JdbcDaoSupport {
    @Autowired
    public AdminSizeDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Size> getPageSize(int start , int end){
        try {
            String sql ="SELECT * FROM SIZE WHERE STATUS = 1 ORDER BY MASIZE DESC OFFSET " + (start-1) + "ROWS  " +
                    " FETCH NEXT  "+ end + "ROWS ONLY  ";
            SizeMapper mapper = new SizeMapper();
            List<Size> sizes= this.getJdbcTemplate().query(sql,mapper);
            return  sizes;
        }catch (EmptyResultDataAccessException e ){
            return null;
        }

    }

    public List<Size> getAllsize(int status){
        try {
            if(status == 2){
                String sql ="SELECT * FROM SIZE ORDER BY MASIZE DESC";
                SizeMapper mapper = new SizeMapper();
                List<Size> sizes= this.getJdbcTemplate().query(sql,mapper);
                return  sizes;
            }
            if (status == 1){
                String sql ="SELECT * FROM SIZE WHERE STATUS = " + status;
                SizeMapper mapper = new SizeMapper();
                List<Size> sizes= this.getJdbcTemplate().query(sql,mapper);
                return  sizes;
            }
            return  null;

        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public String Insert(Size size){
        try {
            String sql = "INSERT INTO SIZE(MASIZE,TENSIZE,STATUS) VALUES (?,?,1) ";
            this.getJdbcTemplate().update(sql,size.getMaSize(),size.getTenSize());
            return "thêm sản phẩm thành công ";
        }catch (EmptyResultDataAccessException e){
            return  null;
        }

    }

    public String Update(Size size){
        try {
            String sql = "UPDATE SIZE SET TENSIZE = ? WHERE MASIZE = ? ";
            this.getJdbcTemplate().update(sql,size.getTenSize(),size.getMaSize());
            return "cập nhật thành công ";
        }catch (EmptyResultDataAccessException e){
            return  null;
        }

    }

    public Size Get(String masize){
        try {
            SizeMapper mapper = new SizeMapper();
            String sql = "SELECT * FROM SIZE WHERE MASIZE = ?" ;
            Size sizes = this.getJdbcTemplate().queryForObject(sql,new Object[]{masize},mapper);
            return sizes;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public String Delete(String masize){
        try {
            String sql = "UPDATE SIZE SET STATUS = 0 WHERE MASIZE = ? ";
            this.getJdbcTemplate().update(sql,masize);
            return " xóa thành công hãng cac mã " + masize;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }
}
