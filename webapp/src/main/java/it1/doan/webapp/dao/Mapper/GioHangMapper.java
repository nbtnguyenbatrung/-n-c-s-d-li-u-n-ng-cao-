package it1.doan.webapp.dao.Mapper;


import it1.doan.webapp.model.GioHang;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GioHangMapper implements RowMapper<GioHang> {
    @Override
    public GioHang mapRow(ResultSet resultSet, int i) throws SQLException {

        Integer ID = resultSet.getInt("ID");
        String maSP = resultSet.getString("MASP");
        String maSize = resultSet.getString("MASIZE");
        Integer soluong = resultSet.getInt("SOLUONG");
        String Image = resultSet.getString("MANHINH");
        String TenSP = resultSet.getString("TENSP");
        String TenSize = resultSet.getString("TENSIZE");
        Float Dongia = resultSet.getFloat("DONGIA");
        Float GiaSau = resultSet.getFloat("GIASAU");
        GioHang gioHang = new GioHang(ID,maSP,maSize,soluong,Image,TenSP,TenSize,Dongia,GiaSau);
        return gioHang;
    }
}
