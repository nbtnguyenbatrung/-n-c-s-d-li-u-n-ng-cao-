package it1.doan.webapp.dao.Mapper;


import it1.doan.webapp.model.BinhLuan;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BinhLuanMapper implements RowMapper<BinhLuan> {

    @Override
    public BinhLuan mapRow(ResultSet resultSet, int i) throws SQLException {
        String HOTEN = resultSet.getString("HOTEN");
        String BINHLUAN = resultSet.getString("BINHLUAN");
        Float SOSAO = resultSet.getFloat("SOSAO");
        Timestamp NGAYDG = resultSet.getTimestamp("NGAYDG");
        BinhLuan binhluan = new BinhLuan(HOTEN,BINHLUAN,SOSAO,NGAYDG);
        return binhluan;
    }
}
