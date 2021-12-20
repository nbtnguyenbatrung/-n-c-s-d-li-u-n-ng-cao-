package it1.doan.webapp.dao.Mapper;

import it1.doan.webapp.model.purchase;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class purchaseMapper implements RowMapper<purchase> {
    @Override
    public purchase mapRow(ResultSet resultSet, int i) throws SQLException {
        String mahd = resultSet.getString("MAHD");
        String masp = resultSet.getString("MASP");
        String masize = resultSet.getString("MASIZE");
        int soluong = resultSet.getInt("SOLUONG");
        Float dongia = resultSet.getFloat("DONGIA");
        String tensize = resultSet.getString("TENSIZE");
        String tensp = resultSet.getString("TENSP");
        Float giagoc = resultSet.getFloat("GIAGOC");
        String manhinh = resultSet.getString("MANHINH");
        Float tongtien = resultSet.getFloat("TONGTIEN");
        purchase purchase = new purchase(mahd,masp,masize,soluong,dongia,tensize,tensp,giagoc,manhinh,tongtien);
        return purchase;
    }
}
