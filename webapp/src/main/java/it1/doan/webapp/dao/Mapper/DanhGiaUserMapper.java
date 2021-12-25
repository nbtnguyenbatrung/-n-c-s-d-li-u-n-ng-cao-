package it1.doan.webapp.dao.Mapper;

import it1.doan.webapp.model.DanhGiaByUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DanhGiaUserMapper implements RowMapper<DanhGiaByUser> {
    @Override
    public DanhGiaByUser mapRow(ResultSet resultSet, int i) throws SQLException {
        String maDG = resultSet.getString("MADG");
        int id = resultSet.getInt("ID");
        String maSP = resultSet.getString("MASP");
        int sasao = resultSet.getInt("SOSAO");
        String binhluan = resultSet.getString("BINHLUAN");
        DanhGiaByUser danhGiaByUser = new DanhGiaByUser(maDG,id,maSP,sasao,binhluan);
        return danhGiaByUser;
    }
}
