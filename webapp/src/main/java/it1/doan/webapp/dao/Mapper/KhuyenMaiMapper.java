package it1.doan.webapp.dao.Mapper;

import it1.doan.webapp.model.KhuyenMai;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class KhuyenMaiMapper implements RowMapper<KhuyenMai> {
    @Override
    public KhuyenMai mapRow(ResultSet resultSet, int i) throws SQLException {
         String maKM =resultSet.getString("MAKM");
         String tenKM =resultSet.getString("TENKM");
         Float  giaKM =resultSet.getFloat("GIAKM");
         Date ngayBD=resultSet.getDate("NGAYBD");
         Date ngayKT=resultSet.getDate("NGAYKT");
         Boolean type=resultSet.getBoolean("Type");
         String moTa=resultSet.getString("Mota");
         String image=resultSet.getString("Anh");
         int songay = resultSet.getInt("SONGAY");
         return new KhuyenMai(maKM,tenKM,giaKM,ngayBD,ngayKT,type,moTa,image,songay);
    }
}
