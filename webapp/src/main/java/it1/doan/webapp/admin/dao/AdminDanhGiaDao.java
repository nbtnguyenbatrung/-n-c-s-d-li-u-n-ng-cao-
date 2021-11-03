package it1.doan.webapp.admin.dao;

import it1.doan.webapp.dao.Mapper.DanhGiaMapper;
import it1.doan.webapp.model.DanhGia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AdminDanhGiaDao extends JdbcDaoSupport {

    @Autowired
    public AdminDanhGiaDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<DanhGia> getAllDanhgia(String masp){
        try {
            String sql = " IF NOT EXISTS\n" +
                    "(SELECT V_DANHGIA.* ,COUNT(DANHGIA.MADG) AS SOBL\n" +
                    "from V_DANHGIA LEFT join DANHGIA on V_DANHGIA.MASP = DANHGIA.MASP \n" +
                    "WHERE V_DANHGIA.MASP = '"+ masp +"' \n" +
                    "GROUP BY V_DANHGIA.MASP,V_DANHGIA.TENSP,s1,s2,s3,s4,s5 )\n" +
                    "BEGIN\n" +
                    "\tSELECT V_DANHGIA.* ,COUNT(DANHGIA.MADG) AS SOBL\n" +
                    "from V_DANHGIA LEFT join DANHGIA on V_DANHGIA.MASP = DANHGIA.MASP \n" +
                    "WHERE V_DANHGIA.MASP = '"+ masp +"' \n" +
                    "GROUP BY V_DANHGIA.MASP,V_DANHGIA.TENSP,s1,s2,s3,s4,s5\n" +
                    "END\n" +
                    "ELSE \n" +
                    "BEGIN \n" +
                    "SELECT V_DANHGIA.* ,COUNT(DANHGIA.MADG) AS SOBL\n" +
                    "from V_DANHGIA LEFT join DANHGIA on V_DANHGIA.MASP = DANHGIA.MASP \n" +
                    "WHERE V_DANHGIA.MASP = '" + masp + "' AND DANHGIA.BINHLUAN is not null\n" +
                    "GROUP BY V_DANHGIA.MASP,V_DANHGIA.TENSP,s1,s2,s3,s4,s5\n" +
                    "END\n ";
            DanhGiaMapper mapper = new  DanhGiaMapper();
            List<DanhGia> danhGias = this.getJdbcTemplate().query(sql , mapper);
            return danhGias;
        }catch (EmptyResultDataAccessException e){
            return  null;
        }
    }
}
