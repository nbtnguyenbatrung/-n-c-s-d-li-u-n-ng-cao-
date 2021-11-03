package it1.doan.webapp.dao.Mapper;

import it1.doan.webapp.model.NguoiDung;
import it1.doan.webapp.model.SanPham;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NguoIDungMapper implements RowMapper<NguoiDung> {
    @Override
    public NguoiDung mapRow(ResultSet resultSet, int i) throws SQLException {
        int ID = resultSet.getInt("ID");
        String hoten =resultSet.getString("HOTEN");
        String sdt =resultSet.getString("SDT");
        String email =resultSet.getString("EMAIL");
        String mk =resultSet.getString("MK");
        int Status =resultSet.getInt("Status");
        String quyen =resultSet.getString("QUYEN");
        Date ngaytao =resultSet.getDate("NGAYTAO");
        NguoiDung nguoiDung = new NguoiDung(ID,hoten,sdt,email,mk,Status,quyen,ngaytao);
        return nguoiDung;
    }
}
