package it1.doan.webapp.admin.dao;

import it1.doan.webapp.dao.Mapper.LoaiSanPhamMapper;
import it1.doan.webapp.model.LoaiSanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AdminLoaiSanPhamDao extends JdbcDaoSupport {
    @Autowired
    public AdminLoaiSanPhamDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<LoaiSanPham> getPageloaisp(int start , int end , String keyword){
        String sql ="SELECT * FROM LOAISANPHAM WHERE STATUS = 1" ;
        if(keyword != null){
            sql += " AND MALOAISP LIKE  N'%" + keyword + "%'  OR TENLOAISP LIKE  N'%" + keyword + "%'";
        }
        sql +=  " ORDER BY MALOAISP DESC OFFSET " + (start-1) + "ROWS  " +
                " FETCH NEXT  "+ end + "ROWS ONLY  ";
        LoaiSanPhamMapper mapper = new LoaiSanPhamMapper();
        List<LoaiSanPham> loaiSanPhams= this.getJdbcTemplate().query(sql,mapper);
        return  loaiSanPhams;
    }

    public List<LoaiSanPham> getAllloaisp(int status){

        try {
            if (status == 2){
                String sql = "SELECT * FROM LOAISANPHAM ORDER BY MALOAISP DESC";
                LoaiSanPhamMapper mapper = new LoaiSanPhamMapper();
                List<LoaiSanPham> loaiSanPhams = this.getJdbcTemplate().query(sql,mapper);
                return loaiSanPhams;
            }
            if(status == 1){
                String sql = "SELECT * FROM LOAISANPHAM WHERE STATUS = " + status;
                LoaiSanPhamMapper mapper = new LoaiSanPhamMapper();
                List<LoaiSanPham> loaiSanPhams = this.getJdbcTemplate().query(sql,mapper);
                return loaiSanPhams;
            }
            return null;

        }
        catch (EmptyResultDataAccessException e){
            return  null;
        }
    }

    public List<LoaiSanPham> getAllloaisp(String keyword){

        try {

                String sql = "SELECT * FROM LOAISANPHAM WHERE STATUS = 1" ;
                if(keyword != null){
                    sql += " AND MALOAISP LIKE  N'%" + keyword + "%'  OR TENLOAISP LIKE  N'%" + keyword + "%'";
                }
                LoaiSanPhamMapper mapper = new LoaiSanPhamMapper();
                List<LoaiSanPham> loaiSanPhams = this.getJdbcTemplate().query(sql,mapper);
                return loaiSanPhams;

        }
        catch (EmptyResultDataAccessException e){
            return  null;
        }
    }

    public String Insert(LoaiSanPham loaiSanPham){
        try {
            String sql = "INSERT INTO LOAISANPHAM(MALOAISP,TENLOAISP ,STATUS) VALUES (?,?,1) ";
            this.getJdbcTemplate().update(sql,loaiSanPham.getMaLoaiSP(),loaiSanPham.getTenLoaiSP());
            return "thêm sản phẩm thành công ";
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public String Update(LoaiSanPham loaiSanPham){
        try {
            String sql = "UPDATE LOAISANPHAM SET TENLOAISP = ? WHERE MALOAISP = ? ";
            this.getJdbcTemplate().update(sql,loaiSanPham.getTenLoaiSP(),loaiSanPham.getMaLoaiSP());
            return "cập nhật thành công ";
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public LoaiSanPham Get(String maloaisp){
        try {
            LoaiSanPhamMapper mapper = new LoaiSanPhamMapper();
            String sql = "SELECT * FROM LOAISANPHAM WHERE MALOAISP = ?" ;
            LoaiSanPham loaiSanPham = this.getJdbcTemplate().queryForObject(sql,new Object[]{maloaisp},mapper);
            return loaiSanPham;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public String Delete(String maloaisp){
        try {
            String sql = "UPDATE LOAISANPHAM SET STATUS = 0 WHERE MALOAISP = ? ";
            this.getJdbcTemplate().update(sql,maloaisp);
            return " xóa thành công hãng cac mã " + maloaisp;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }
}
