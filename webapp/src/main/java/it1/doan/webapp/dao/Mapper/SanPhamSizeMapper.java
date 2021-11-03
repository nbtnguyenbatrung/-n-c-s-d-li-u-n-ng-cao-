package it1.doan.webapp.dao.Mapper;

import it1.doan.webapp.model.SanPham;
import it1.doan.webapp.model.SanPhamSize;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SanPhamSizeMapper implements RowMapper<SanPhamSize> {
    @Override
    public SanPhamSize mapRow(ResultSet resultSet, int i) throws SQLException {
        String maSP =resultSet.getString("MASP");
        String tenSP =resultSet.getString("TENSP");
        String maSize = resultSet.getString("MASIZE");
        String tenSize = resultSet.getString("TENSIZE");
        int soluong =resultSet.getInt("SOLUONG");
        SanPhamSize sanPhamSize= new SanPhamSize(maSP,tenSP,maSize,tenSize,soluong);
        return sanPhamSize;
    }
}
