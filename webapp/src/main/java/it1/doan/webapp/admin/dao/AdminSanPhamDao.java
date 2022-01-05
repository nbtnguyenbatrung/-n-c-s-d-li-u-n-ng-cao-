package it1.doan.webapp.admin.dao;

import it1.doan.webapp.admin.excaption.NotFoundException;
import it1.doan.webapp.dao.Mapper.SanPhamMapper;
import it1.doan.webapp.model.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
@Repository
public class AdminSanPhamDao extends JdbcDaoSupport {

    @Autowired
    public AdminSanPhamDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<SanPham> getPagesp(int start , int end , String keyword){
        String sql ="SELECT * FROM SANPHAM WHERE STATUS = 1 " ;
        if(keyword != null){
            sql += " AND MASP LIKE  N'%" + keyword + "%'  OR TENSP LIKE  N'%" + keyword + "%'";
        }
        sql +=  " ORDER BY MASP DESC OFFSET " + (start-1) + "ROWS  " +
                " FETCH NEXT  "+ end + "ROWS ONLY  ";
        SanPhamMapper mapper = new SanPhamMapper();
        List<SanPham> sanPhams= this.getJdbcTemplate().query(sql,mapper);
        return  sanPhams;
    }

    public List<SanPham> getAllsp(int status){
        try {
            if (status == 2){
                String sql ="SELECT * FROM SANPHAM ORDER BY MASP DESC";
                SanPhamMapper mapper = new SanPhamMapper();
                List<SanPham> sanPhams= this.getJdbcTemplate().query(sql,mapper);
                return sanPhams;
            }
            if (status == 1){
                String sql ="SELECT * FROM SANPHAM WHERE STATUS = "+ status;
                SanPhamMapper mapper = new SanPhamMapper();
                List<SanPham> sanPhams= this.getJdbcTemplate().query(sql,mapper);
                return  sanPhams;
            }
            return  null;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public List<SanPham> getAllsp(String keyword){
        try {

                String sql ="SELECT * FROM SANPHAM WHERE STATUS = 1";
                if(keyword != null){
                    sql += " AND MASP LIKE  N'%" + keyword + "%'  OR TENSP LIKE  N'%" + keyword + "%'";
                }
                SanPhamMapper mapper = new SanPhamMapper();
                List<SanPham> sanPhams= this.getJdbcTemplate().query(sql,mapper);
                return  sanPhams;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public String Insert(SanPham sanPham){
        try {
            String sql = "INSERT INTO SANPHAM(MASP,MAHANG ,MAKM,MALOAISP,TENSP,DONGIA,MANHINH,MOTA,STATUS" +
                    " ) VALUES (?,?,?,?,?,?,?,?,?,1) ";
            this.getJdbcTemplate().update(sql,sanPham.getMaSP(),sanPham.getMaHang(),sanPham.getMaKM(),
                    sanPham.getMaLoaiSP(),sanPham.getTenSP(),sanPham.getDonGia(),
                    sanPham.getManHinh(),sanPham.getMoTa());
            return "thêm sản phẩm thành công ";
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public String Update(SanPham sanPham){
        try {
            String sql = "UPDATE SANPHAM SET MAHANG = ? , MAKM = ? , MALOAISP = ? , " +
                    " TENSP = ? , DONGIA = ? , MANHINH = ? , MOTA = ? " +
                    " WHERE MASP = ? ";
            this.getJdbcTemplate().update(sql,sanPham.getMaHang(),sanPham.getMaKM(),
                    sanPham.getMaLoaiSP(),sanPham.getTenSP(),sanPham.getDonGia(),
                    sanPham.getManHinh(),sanPham.getMoTa() ,sanPham.getMaSP() );
            return "cập nhật thành công ";
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public SanPham Get(String masp){
        try {
            SanPhamMapper mapper = new SanPhamMapper();
            String sql = "SELECT * FROM SANPHAM WHERE MASP = ?" ;
            SanPham sanPham = this.getJdbcTemplate().queryForObject(sql,new Object[]{masp},mapper);
            return sanPham;

        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public String Delete(String masp){
        try {
            String sql = "UPDATE SANPHAM SET STATUS = 0 WHERE MASP = ? ";
            this.getJdbcTemplate().update(sql,masp);
            return " xóa thành công hãng cac mã " + masp;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

}
