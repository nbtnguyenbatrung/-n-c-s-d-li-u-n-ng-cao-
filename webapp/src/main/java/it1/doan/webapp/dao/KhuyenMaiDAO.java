package it1.doan.webapp.dao;

import it1.doan.webapp.dao.Mapper.KhuyenMaiMapper;
import it1.doan.webapp.model.KhuyenMai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository

public class KhuyenMaiDAO extends JdbcDaoSupport {
    @Autowired
    public KhuyenMaiDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<KhuyenMai> getAllKhuyenMai(){
        String sql ="SELECT * ,DATEDIFF(day, GETDATE(), KHUYENMAI.NGAYKT) AS SONGAY FROM KHUYENMAI";
        KhuyenMaiMapper mapper = new KhuyenMaiMapper();
        List<KhuyenMai> khuyenMais= this.getJdbcTemplate().query(sql,mapper);
        return  khuyenMais;
    }
}
