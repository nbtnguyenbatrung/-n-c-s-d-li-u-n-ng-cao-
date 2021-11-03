package it1.doan.webapp.dao.Mapper;

import it1.doan.webapp.model.KhuyenMai;
import it1.doan.webapp.model.SanPham;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SanPhamMapper implements RowMapper<SanPham> {
    @Override
    public SanPham mapRow(ResultSet resultSet, int i) throws SQLException {
        String maSP =resultSet.getString("MASP");
        String maHang =resultSet.getString("MAHANG");
        String maKM =resultSet.getString("MAKM");
        String maLoaiSP =resultSet.getString("MALOAISP");
        String tenSP =resultSet.getString("TENSP");
        Float donGia =resultSet.getFloat("DONGIA");
        int soLuong =resultSet.getInt("SOLUONG");
        String manHinh =resultSet.getString("MANHINH");
        Float soSao =resultSet.getFloat("SOSAO");
        String moTa =resultSet.getString("MOTA");
        SanPham sanPham= new SanPham(maSP,maHang,maKM,maLoaiSP,tenSP,donGia,soLuong,manHinh,soSao,moTa);
        return sanPham;
    }
}
