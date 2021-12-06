package it1.doan.webapp.admin.dao;


import com.sun.istack.internal.NotNull;
import it1.doan.webapp.dao.Mapper.NguoIDungMapper;
import it1.doan.webapp.model.NguoiDung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.sql.DataSource;
import java.util.List;

@Repository
public class AdminNguoIDungDao extends JdbcDaoSupport {
    @Autowired
    public AdminNguoIDungDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<NguoiDung> getPagend(int start , int end){
        String sql ="SELECT * FROM NGUOIDUNG ORDER BY ID DESC OFFSET " + (start-1) + "ROWS  " +
                " FETCH NEXT  "+ end + "ROWS ONLY  ";
        NguoIDungMapper mapper = new NguoIDungMapper();
        List<NguoiDung> nguoiDungs= this.getJdbcTemplate().query(sql,mapper);
        return  nguoiDungs;
    }

    public List<NguoiDung> getAllnd(){
        String sql ="SELECT * FROM NGUOIDUNG ";
        NguoIDungMapper mapper = new NguoIDungMapper();
        List<NguoiDung> nguoiDungs= this.getJdbcTemplate().query(sql,mapper);
        return  nguoiDungs;
    }

    public NguoiDung existUser(String email){
        String sql = "SELECT * FROM NGUOIDUNG WHERE STATUS = 1 AND EMAIL=? ";
        Object[] params = new Object[]{email};
        NguoIDungMapper userMapper = new NguoIDungMapper();
        try {
            NguoiDung user = this.getJdbcTemplate().queryForObject(sql,userMapper,params);
            return user;
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Transactional()
    public int saveUser(@NotNull NguoiDung user){
        NguoiDung user1 = existUser(user.getEmail());
        if(user1==null){
            try{
                String sql="INSERT INTO NGUOIDUNG (HOTEN,SDT,EMAIL,MK,STATUS,QUYEN) VALUES(?,?,?,?,?,?)";
                Object[] params = new Object[]{user.getHoten(),user.getSdt(),user.getEmail(),user.getMk(),user.getStatus(),user.getQuyen()};
                int index=this.getJdbcTemplate().update(sql,params);
                return index;
            }catch (EmptyResultDataAccessException exception){
                return -1;
            }
        }
        return -1;
    }

}
