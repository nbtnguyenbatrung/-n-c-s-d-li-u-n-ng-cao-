package it1.doan.webapp.dao.Mapper;

import it1.doan.webapp.model.HomeSanPham;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeSanPhamMapper implements RowMapper<HomeSanPham> {
    @Override
    public HomeSanPham mapRow(ResultSet resultSet, int i) throws SQLException {
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
        int status = resultSet.getInt("STATUS");
        String tenhang =resultSet.getString("TENHANG");
        String tenloai = resultSet.getString("TENLOAISP");
        Float giasau = resultSet.getFloat("GIASAU");
        HomeSanPham homeSanPham = new HomeSanPham(maSP,maHang,maKM,maLoaiSP,tenSP,donGia,soLuong,manHinh,soSao,moTa,status,tenhang,tenloai,giasau);
        return homeSanPham;
    }
}
