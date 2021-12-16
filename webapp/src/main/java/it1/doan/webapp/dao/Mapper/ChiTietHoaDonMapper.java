package it1.doan.webapp.dao.Mapper;


import it1.doan.webapp.model.ChiTietHoaDon;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ChiTietHoaDonMapper implements RowMapper<ChiTietHoaDon> {
    @Override
    public ChiTietHoaDon mapRow(ResultSet resultSet, int i) throws SQLException {
        String maHD = resultSet.getString("MAHD");
        String maSP = resultSet.getString("MASP");
        String maSize = resultSet.getString("MASIZE");
        int soluong = resultSet.getInt("SOLUONG");
        Float dongia = resultSet.getFloat("DONGIA");
        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(maHD,maSP,maSize,soluong,dongia);
        return chiTietHoaDon;
    }
}
