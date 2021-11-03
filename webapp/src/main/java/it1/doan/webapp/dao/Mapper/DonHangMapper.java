package it1.doan.webapp.dao.Mapper;

import it1.doan.webapp.model.DonHang;
import it1.doan.webapp.model.KhuyenMai;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DonHangMapper implements RowMapper<DonHang> {
    @Override
    public DonHang mapRow(ResultSet resultSet, int i) throws SQLException {
        String maHD =resultSet.getString("MAHD");
        String maSP =resultSet.getString("MASP");
        String maSize =resultSet.getString("MASIZE");
        Float dongia = resultSet.getFloat("DONGIA");
        int soluong = resultSet.getInt("SOLUONG");
        int trangthai = resultSet.getInt("TRANGTHAI");
        String mota =resultSet.getString("MOTA");
        Date ngaylap = resultSet.getDate("NGAYLAP");
        String diachi =resultSet.getString("DIACHI");
        String nguoinhan =resultSet.getString("NGUOINHAN");
        String sdt =resultSet.getString("SDT");
        String tenSP =resultSet.getString("TENSP");
        String tenSize =resultSet.getString("TENSIZE");
        Float giatri = resultSet.getFloat("GIATRI");
        DonHang donHang = new DonHang(maHD,maSP,maSize,dongia,soluong,trangthai,mota,ngaylap,diachi,nguoinhan,sdt,tenSP,tenSize,giatri);
        return donHang;
    }
}
