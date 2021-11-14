package it1.doan.webapp.dao.Mapper;

import it1.doan.webapp.model.HinhAnh;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HinhAnhMapper implements RowMapper<HinhAnh> {
    @Override
    public HinhAnh mapRow(ResultSet resultSet, int i) throws SQLException {
        String maHA = resultSet.getString("MAHA");
        String maSP = resultSet.getString("MASP");
        String ha = resultSet.getString("HINHANH");
        HinhAnh hinhAnh = new HinhAnh(maHA,maSP,ha);
        return hinhAnh;
    }
}
