package it1.doan.webapp.user.dao;

import it1.doan.webapp.dao.Mapper.HoaDonUserMapper;
import it1.doan.webapp.model.ChiTietHoaDon;
import it1.doan.webapp.model.HoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class HoaDonDao extends JdbcDaoSupport {

    @Autowired
    public HoaDonDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<HoaDon> getallhd(){
        String sql = "SELECT * FROM HOADON ORDER BY MAHD DESC";
        HoaDonUserMapper mapper = new HoaDonUserMapper();
        List<HoaDon> hoaDons = this.getJdbcTemplate().query(sql,mapper);
        return hoaDons;
    }

    public void Insert(HoaDon hoaDon){
        String sql = "INSERT INTO HOADON(MAHD,ID ,NGUOINHAN,SDT,DIACHI) VALUES (?,?,?,?,?) ";
        this.getJdbcTemplate().update(sql,hoaDon.getMaHD(),hoaDon.getID(),hoaDon.getNguoinhan(),hoaDon.getSdt(),hoaDon.getDiachi());
    }

    public void Insert(List<ChiTietHoaDon> chiTietHoaDon){
        String sql ="";
        for (int i = 0 ; i<chiTietHoaDon.size() ; i++){
            sql += "INSERT INTO CHITIETHOADON(MAHD,MASP ,MASIZE,SOLUONG,DONGIA,TRANGTHAI) " +
                    " VALUES ('"+chiTietHoaDon.get(i).getMaHD()+"'," +
                    " '"+ chiTietHoaDon.get(i).getMaSP()+"'," +
                    " '"+chiTietHoaDon.get(i).getMaSize()+"'," +
                    " "+chiTietHoaDon.get(i).getSoluong()+"," +
                    " "+chiTietHoaDon.get(i).getDongia()+",0)";
        }
        this.getJdbcTemplate().execute(sql);
    }

}
