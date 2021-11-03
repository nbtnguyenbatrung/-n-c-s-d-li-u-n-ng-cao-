package it1.doan.webapp.dao.Mapper;

import it1.doan.webapp.model.SanPhamKhuyenMai;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SanPhamKhuyenMaiMapper implements RowMapper<SanPhamKhuyenMai> {
    @Override
    public SanPhamKhuyenMai mapRow(ResultSet resultSet, int i) throws SQLException {
        String tenSP =resultSet.getString("TENSP");
        Float giaSP = resultSet.getFloat("DONGIA");
        Float giakm = resultSet.getFloat("GIAKM");
        Date ngaybd = resultSet.getDate("NGAYBD");
        Date ngaykt = resultSet.getDate("NGAYKT");
        Integer songay = resultSet.getInt("SONGAY");
        SanPhamKhuyenMai sanPhamKhuyenMai= new SanPhamKhuyenMai(tenSP,giaSP,giakm,ngaybd,ngaykt,songay);
        return sanPhamKhuyenMai;
    }
}
