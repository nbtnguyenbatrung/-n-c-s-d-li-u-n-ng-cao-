package it1.doan.webapp.admin.dao;


import it1.doan.webapp.dao.Mapper.SanPhamSizeMapper;

import it1.doan.webapp.model.SanPhamSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AdminSanPhamSizeDao extends JdbcDaoSupport {
    @Autowired
    public AdminSanPhamSizeDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<SanPhamSize> getPagesanphamsize(int start , int end , String keyword){
        try {
            String sql ="SELECT sanpham.masp,sanpham.tensp,size.masize,size.tensize,sanphamsize.soluong" +
                    " FROM SANPHAM INNER JOIN SANPHAMSIZE ON SANPHAM.MASP = SANPHAMSIZE.MASP " +
                    " INNER JOIN SIZE ON SIZE.MASIZE = SANPHAMSIZE.MASIZE " ;
            if(keyword != ""){
                sql += " WHERE SANPHAM.MASP LIKE  N'%" + keyword + "%'  OR TENSP LIKE  N'%" + keyword + "%' " +
                        " OR SIZE.MASIZE LIKE  N'%" + keyword + "%'  OR TENSIZE LIKE  N'%" + keyword + "%' ";
            }
            sql +=  " ORDER BY SANPHAMSIZE.NGAYCREATE DESC OFFSET " + (start-1) + "ROWS  " +
                    " FETCH NEXT  "+ end + "ROWS ONLY  ";
            SanPhamSizeMapper mapper = new SanPhamSizeMapper();
            List<SanPhamSize> sanPhamSizes= this.getJdbcTemplate().query(sql,mapper);
            return  sanPhamSizes;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

     public List<SanPhamSize> getAllspsize(String keyword){
        try {
            String sql = "SELECT * FROM SANPHAM INNER JOIN SANPHAMSIZE ON SANPHAM.MASP = SANPHAMSIZE.MASP " +
                    "INNER JOIN SIZE ON SIZE.MASIZE = SANPHAMSIZE.MASIZE";
            if(keyword !=""){
                sql += " WHERE SANPHAM.MASP LIKE  N'%" + keyword + "%'  OR TENSP LIKE  N'%" + keyword + "%' " +
                        " OR SIZE.MASIZE LIKE  N'%" + keyword + "%'  OR TENSIZE LIKE  N'%" + keyword + "%' ";
            }
            SanPhamSizeMapper mapper = new SanPhamSizeMapper();
            List<SanPhamSize> sanPhamSize= this.getJdbcTemplate().query(sql,mapper);
            return  sanPhamSize;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

     }

    public String Insert(SanPhamSize sanPhamSize){
        try {
            String sql = "INSERT INTO SANPHAMSIZE(MASP,MASIZE ,SOLUONG) VALUES (?,?,?) ";
            this.getJdbcTemplate().update(sql,sanPhamSize.getMaSP(),sanPhamSize.getMaSize(),sanPhamSize.getSoluong());
            return "thêm sản phẩm size thành công ";
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public String Update(SanPhamSize sanPhamSize){
        try {
            String sql = "UPDATE SANPHAMSIZE SET SOLUONG = ? WHERE MASP = ? AND MASIZE = ?";
            this.getJdbcTemplate().update(sql,sanPhamSize.getSoluong(),sanPhamSize.getMaSP(),sanPhamSize.getMaSize());
            return "cập nhật thành công ";
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public SanPhamSize Get(String masize,String masp ){
        try {
            SanPhamSizeMapper mapper = new SanPhamSizeMapper();
            String sql = "SELECT sanpham.masp,sanpham.tensp,size.masize,size.tensize,sanphamsize.soluong" +
                    " FROM SANPHAM INNER JOIN SANPHAMSIZE ON SANPHAM.MASP = SANPHAMSIZE.MASP " +
                    " INNER JOIN SIZE ON SIZE.MASIZE = SANPHAMSIZE.MASIZE" +
                    " WHERE SANPHAMSIZE.MASIZE = ? AND SANPHAMSIZE.MASP = ? " ;
            SanPhamSize sanPhamSizes = this.getJdbcTemplate().queryForObject(sql,new Object[]{masize,masp},mapper);
            return sanPhamSizes;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public String Delete(String masize , String masp){
        try {
            String sql = "DELETE FROM SANPHAMSIZE WHERE MASIZE = '" + masize + "'  AND MASP =  '" + masp + "' " ;
            this.getJdbcTemplate().execute(sql);
            return " xóa thành công sản phẩm có maz size " + masize + " và có mã sản phẩm " + masp ;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }
}
