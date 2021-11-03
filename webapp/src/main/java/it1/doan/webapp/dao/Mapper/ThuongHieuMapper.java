package it1.doan.webapp.dao.Mapper;

import it1.doan.webapp.model.SanPham;
import it1.doan.webapp.model.ThuongHieu;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ThuongHieuMapper implements RowMapper<ThuongHieu> {
    @Override
    public ThuongHieu mapRow(ResultSet resultSet, int i) throws SQLException {
        String maHang =resultSet.getString("MAHANG");
        String tenHang =resultSet.getString("TENHANG");
        String logo =resultSet.getString("LOGO");
        ThuongHieu thuongHieu = new ThuongHieu(maHang,tenHang,logo);
        return thuongHieu;
    }
}
