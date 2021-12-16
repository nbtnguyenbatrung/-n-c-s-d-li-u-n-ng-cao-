package it1.doan.webapp.dao.Mapper;


import it1.doan.webapp.model.HoaDon;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HoaDonUserMapper implements RowMapper<HoaDon> {
    @Override
    public HoaDon mapRow(ResultSet resultSet, int i) throws SQLException {
        String maHD =resultSet.getString("MAHD");
        int  ID = resultSet.getInt("ID");
        String nguoinhan = resultSet.getString("NGUOINHAN");
        String sdt = resultSet.getString("SDT");
        String address = resultSet.getString("DIACHI");
        HoaDon hoadon = new HoaDon(maHD,ID,nguoinhan,sdt,address);
        return hoadon;
    }
}
