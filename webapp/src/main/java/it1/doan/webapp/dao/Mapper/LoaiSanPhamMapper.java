package it1.doan.webapp.dao.Mapper;

import it1.doan.webapp.model.LoaiSanPham;
import it1.doan.webapp.model.SanPham;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoaiSanPhamMapper implements RowMapper<LoaiSanPham> {
    @Override
    public LoaiSanPham mapRow(ResultSet resultSet, int i) throws SQLException {
        String maLoaiSP =resultSet.getString("MALOAISP");
        String tenLoaiSP =resultSet.getString("TENLOAISP");
        int soLuong =resultSet.getInt("SOLUONG");
        LoaiSanPham loaiSanPham = new LoaiSanPham(maLoaiSP,tenLoaiSP,soLuong);
        return loaiSanPham;
    }

}
