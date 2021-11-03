package it1.doan.webapp.dao.Mapper;

import it1.doan.webapp.model.DonHang;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HoaDonMapper implements RowMapper<DonHang> {
    @Override
    public DonHang mapRow(ResultSet resultSet, int i) throws SQLException {
        String maHD =resultSet.getString("MAHD");
        Date ngaylap = resultSet.getDate("NGAYLAP");
        Float giatri = resultSet.getFloat("GIATRI");
        DonHang hoadon = new DonHang(maHD,ngaylap,giatri);
        return hoadon;
    }
}
