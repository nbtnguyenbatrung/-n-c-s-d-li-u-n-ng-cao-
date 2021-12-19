package it1.doan.webapp.dao.Mapper;

import it1.doan.webapp.model.Size;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductSizeMapper implements RowMapper<Size> {
    @Override
    public Size mapRow(ResultSet resultSet, int i) throws SQLException {
        String maSize =resultSet.getString("MASIZE");
        String tenSize =resultSet.getString("TENSIZE");
        int soluong = resultSet.getInt("SOLUONG");
        Size size = new Size(maSize,tenSize,soluong);
        return size;
    }
}
