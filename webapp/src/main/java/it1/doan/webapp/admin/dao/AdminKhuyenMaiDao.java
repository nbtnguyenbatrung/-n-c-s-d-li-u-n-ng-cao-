package it1.doan.webapp.admin.dao;

import it1.doan.webapp.dao.Mapper.KhuyenMaiMapper;
import it1.doan.webapp.dao.Mapper.ThuongHieuMapper;
import it1.doan.webapp.model.KhuyenMai;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AdminKhuyenMaiDao extends JdbcDaoSupport {
    @Autowired
    public AdminKhuyenMaiDao (DataSource dataSource){
        this.setDataSource(dataSource);
    }

    public List<KhuyenMai> getPagekhuyenmai(int start , int end){
        try{
            String sql ="SELECT * ,DATEDIFF(day, GETDATE(), KHUYENMAI.NGAYKT) AS SONGAY FROM KHUYENMAI" +
                    " ORDER BY MAKM DESC OFFSET " + (start-1) + "ROWS  " +
                    " FETCH NEXT  "+ end + "ROWS ONLY  ";
            KhuyenMaiMapper mapper = new KhuyenMaiMapper();
            List<KhuyenMai> khuyenMais= this.getJdbcTemplate().query(sql,mapper);
            return  khuyenMais;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public List<KhuyenMai> getAllKhuyenMai(){
        String sql ="SELECT * ,DATEDIFF(day, GETDATE(), KHUYENMAI.NGAYKT) AS SONGAY" +
                " FROM KHUYENMAI ORDER BY MAKM DESC ";
        KhuyenMaiMapper mapper = new KhuyenMaiMapper();
        List<KhuyenMai> khuyenMais= this.getJdbcTemplate().query(sql,mapper);
        return  khuyenMais;
    }

    public List<KhuyenMai> gethienKhuyenMai(){
        String sql ="SELECT * ,DATEDIFF(day, GETDATE(), KHUYENMAI.NGAYKT) AS SONGAY" +
                " FROM KHUYENMAI WHERE DATEDIFF(day, GETDATE(), KHUYENMAI.NGAYKT) > 0  ORDER BY MAKM DESC  ";
        KhuyenMaiMapper mapper = new KhuyenMaiMapper();
        List<KhuyenMai> khuyenMais= this.getJdbcTemplate().query(sql,mapper);
        return  khuyenMais;
    }

    public String Insert(KhuyenMai khuyenMai){
        try {
            String sql = "INSERT INTO KHUYENMAI(MAKM,TENKM ,GIAKM,NGAYBD,NGAYKT,TYPE,MOTA,ANH) VALUES (?,?,?,?,?,?,?,?) ";
            this.getJdbcTemplate().update(sql,khuyenMai.getMaKM(),khuyenMai.getTenKM(),khuyenMai.getGiaKM(),
                    khuyenMai.getNgayBD(),khuyenMai.getNgayKT(),khuyenMai.getType(),khuyenMai.getMoTa(),khuyenMai.getImage());
            return "thêm sản phẩm thành công ";
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public String Update(KhuyenMai khuyenMai){
        try {
            String sql = "UPDATE KHUYENMAI SET TENKM = ? , GIAKM = ? , NGAYBD = ? ," +
                    " NGAYKT = ? , TYPE = ? , MOTA = ? , ANH = ? WHERE MAKM = ? ";
            this.getJdbcTemplate().update(sql,khuyenMai.getTenKM(),khuyenMai.getGiaKM(),
                    khuyenMai.getNgayBD(),khuyenMai.getNgayKT(),khuyenMai.getType(),
                    khuyenMai.getMoTa(),khuyenMai.getImage());
            return "cập nhật thành công ";
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public KhuyenMai Get(String makm){
        try {
            KhuyenMaiMapper mapper = new KhuyenMaiMapper();
            String sql = "SELECT *,DATEDIFF(day, GETDATE(), KHUYENMAI.NGAYKT) AS SONGAY " +
                    " FROM KHUYENMAI WHERE MAKM = ?" ;
            KhuyenMai khuyenMai = this.getJdbcTemplate().queryForObject(sql,new Object[]{makm},mapper);
            return khuyenMai;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public String Delete(String makm){
        try {
            String sql = "DELETE FROM KHUYENMAI WHERE MAKM = '" + makm + "' ";
            this.getJdbcTemplate().execute(sql);
            return " xóa thành công hãng cac mã " + makm;
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

}
