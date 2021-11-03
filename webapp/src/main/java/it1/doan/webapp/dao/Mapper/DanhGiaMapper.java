package it1.doan.webapp.dao.Mapper;

import it1.doan.webapp.model.DanhGia;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DanhGiaMapper implements RowMapper<DanhGia> {
    @Override
    public DanhGia mapRow(ResultSet resultSet, int i) throws SQLException {
        String maSP =resultSet.getString("MASP");
        String tenSP =resultSet.getString("TENSP");
        int motsao = resultSet.getInt("s1");
        int haisao = resultSet.getInt("s2");
        int basao = resultSet.getInt("s3");
        int bonsao = resultSet.getInt("s4");
        int namsao = resultSet.getInt("s5");
        int sobl = resultSet.getInt("SOBL");
        DanhGia danhGia = new DanhGia(maSP,tenSP,motsao,haisao,basao,bonsao,namsao,sobl);
        return danhGia;
    }
}
