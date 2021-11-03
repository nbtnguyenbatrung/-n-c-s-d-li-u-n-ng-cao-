package it1.doan.webapp.dao.Mapper;

import it1.doan.webapp.model.SanPham;
import it1.doan.webapp.model.SanPhamDanhGia;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SanPhamDanhGiaMapper implements RowMapper<SanPhamDanhGia> {
    @Override
    public SanPhamDanhGia mapRow(ResultSet resultSet, int i) throws SQLException {
        String maSP = resultSet.getString("MASP");
        String tenSP =resultSet.getString("TENSP");
        int sosao=resultSet.getInt("SOSAO");
        SanPhamDanhGia sanPhamDanhGia= new SanPhamDanhGia(maSP,tenSP,sosao);
        return sanPhamDanhGia;
    }
}
