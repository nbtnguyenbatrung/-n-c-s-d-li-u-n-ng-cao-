package it1.doan.webapp.user.dao;

import it1.doan.webapp.dao.Mapper.GioHangMapper;
import it1.doan.webapp.model.GioHang;
import org.springframework.beans.factory.annotation.Autowired;
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
}
