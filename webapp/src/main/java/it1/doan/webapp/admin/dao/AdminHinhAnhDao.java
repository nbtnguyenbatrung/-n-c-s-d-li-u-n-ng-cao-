package it1.doan.webapp.admin.dao;

import it1.doan.webapp.dao.Mapper.HinhAnhMapper;
import it1.doan.webapp.model.HinhAnh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AdminHinhAnhDao extends JdbcDaoSupport {
    @Autowired
    public AdminHinhAnhDao(DataSource dataSource){
        this.setDataSource(dataSource);
    }

    public List<HinhAnh> getallHinhAnh(String masp){
        try {
            String sql = "SELECT * FROM HINHANH WHERE MASP = '"+masp+"' "  ;
            HinhAnhMapper mapper = new HinhAnhMapper();
            List<HinhAnh> hinhAnhs = this.getJdbcTemplate().query(sql,mapper);
            return hinhAnhs;
        }catch (EmptyResultDataAccessException e){
            return  null ;
        }
    }

    public void Insert(String masp , List<String> list ){

        String sql = "";
        String dk,ha1,ha2;
        if (list.size() == 1){
            dk=list.get(0);
            ha1 = dk.substring(2, dk.length() - 2);
            ha2 = ha1;
            sql = sql + "INSERT INTO HINHANH(MASP , HINHANH ) VALUES ( '" + masp + "','" + ha2 + "' ) \n ";
        }else {
            for (int i = 0; i < list.size(); i++) {
                dk = list.get(i);
                if (i == 0) {
                    ha1 = dk.substring(2, dk.length() - 1);
                    ha2 = ha1;
                } else if (i == list.size() - 1) {
                    ha1 = dk.substring(1, dk.length() - 2);
                    ha2 = ha1;
                } else {
                    ha1 = dk.substring(1, dk.length() - 1);
                    ha2 = ha1;
                }
                sql = sql + "INSERT INTO HINHANH(MASP , HINHANH ) VALUES ( '" + masp + "','" + ha2 + "' ) \n ";
            }
        }
        this.getJdbcTemplate().execute(sql);

    }

    public void Delete(String maha){
        String sql = "DELETE FROM HINHANH WHERE MAHA = '"+maha+"' " ;
        this.getJdbcTemplate().execute(sql);
    }

    public void Update(String maha , String hinhanh){
        String sql = "UPDATE HINHANH SET HINHANH = '"+hinhanh+"' WHERE MAHA = '"+maha+"'";
        this.getJdbcTemplate().execute(sql);
    }
}
