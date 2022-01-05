package it1.doan.webapp.admin.service;

import it1.doan.webapp.admin.dao.*;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Component
public class AdminServicepml implements AdminService {

    @Autowired
    AdminSanPhamDao adminSanPhamDao;
    @Autowired
    AdminSanPhamSizeDao adminSanPhamSizeDao;
    @Autowired
    AdminSanPhamKhuyenMaiDao adminSanPhamKhuyenMaiDao;
    @Autowired
    AdminSanPhamDanhGiaDao adminSanPhamDanhGiaDao;
    @Autowired
    AdminThuongHieuDAO adminThuongHieuDAO;
    @Autowired
    AdminLoaiSanPhamDao adminLoaiSanPhamDao;
    @Autowired
    AdminSizeDao adminSizeDao;
    @Autowired
    AdminNguoIDungDao adminNguoIDungDao;
    @Autowired
    AdminDonHangDao adminDonHangDao;
    @Autowired
    AdminKhuyenMaiDao adminKhuyenMaiDao;
    @Autowired
    AdminHoaDonDao adminHoaDonDao;

    @Override
    public List<SanPham> getAllsp(int status) {
        return adminSanPhamDao.getAllsp(status);
    }

    @Override
    public List<SanPham> getPagesp(int start, int end , String keyword) {
        return adminSanPhamDao.getPagesp(start,end,keyword);
    }

    @Override
    public List<SanPham> getallsp(String keyword) {
        return adminSanPhamDao.getAllsp(keyword);
    }


    @Override
    public List<SanPhamSize> getAllspsize(String keyword) {
        return adminSanPhamSizeDao.getAllspsize(keyword);
    }

    @Override
    public List<SanPhamSize> getPagespsize(int start, int end,String keyword) {
        return adminSanPhamSizeDao.getPagesanphamsize(start,end,keyword);
    }

    @Override
    public List<SanPhamKhuyenMai> getAllspkm(String keyword) {
        return adminSanPhamKhuyenMaiDao.getAllspkm(keyword);
    }

    @Override
    public List<SanPhamKhuyenMai> getPagespkm(int start, int end , String keyword) {
        return adminSanPhamKhuyenMaiDao.getPagespkm(start,end,keyword);
    }

    @Override
    public List<ThuongHieu> getPagethuonghieu(int start, int end , String keyword) {
        return adminThuongHieuDAO.getPagethuonghieu(start,end,keyword);
    }

    @Override
    public List<ThuongHieu> getAllthuonghieu(int status) {
        return adminThuongHieuDAO.getAllthuonghieu(status);
    }

    @Override
    public List<ThuongHieu> getallthuonghieu(String keyword) {
        return adminThuongHieuDAO.getallthuonghieu(keyword);
    }


    @Override
    public List<LoaiSanPham> getAllloaisp(int status) {
        return adminLoaiSanPhamDao.getAllloaisp(status);
    }

    @Override
    public List<LoaiSanPham> getPageloaisp(int start, int end , String keyword) {
        return adminLoaiSanPhamDao.getPageloaisp(start, end , keyword);
    }

    @Override
    public List<LoaiSanPham> getAllloaisp(String keyword) {
        return adminLoaiSanPhamDao.getAllloaisp(keyword);
    }

    @Override
    public List<Size> getAllsize(int status) {
        return adminSizeDao.getAllsize(status);
    }

    @Override
    public List<Size> getPageSize(int start, int end , String keyword) {
        return adminSizeDao.getPageSize(start,end,keyword);
    }

    @Override
    public List<Size> getallsize(String keyword) {
        return adminSizeDao.getallsize(keyword);
    }

    @Override
    public List<NguoiDung> getAllnd() {
        return adminNguoIDungDao.getAllnd();
    }

    @Override
    public List<NguoiDung> getPagend(int start, int end , String keyword) {
        return adminNguoIDungDao.getPagend(start,end,keyword);
    }

    @Override
    public List<NguoiDung> getAllnd(String keyword) {
        return adminNguoIDungDao.getAllnd(keyword);
    }

    @Override
    public List<DonHang> getAlldh() {
        return adminDonHangDao.getAlldh();
    }

    @Override
    public List<DonHang> getPagedh(int start, int end) {
        return adminDonHangDao.getPagedh(start,end);
    }

    @Override
    public List<DonHang> getAllhd(Date startdate , Date enddate) {
        return adminHoaDonDao.getAllhd(startdate,enddate);
    }

    @Override
    public List<DonHang> getPagehd(int start, int end , Date startdate , Date enddate) {
        return adminHoaDonDao.getPagehd(start,end,startdate,enddate);
    }

    @Override
    public List<KhuyenMai> getAllKhuyenMai() {
        return adminKhuyenMaiDao.getAllKhuyenMai();
    }

    @Override
    public List<KhuyenMai> getPagekm(int start, int end , String keyword) {
        return adminKhuyenMaiDao.getPagekhuyenmai(start,end , keyword);
    }

    @Override
    public List<KhuyenMai> getAllKhuyenMai(String keyword) {
        return adminKhuyenMaiDao.getAllKhuyenMai(keyword);
    }

    @Override
    public List<KhuyenMai> gethienKhuyenMai() {
        return adminKhuyenMaiDao.gethienKhuyenMai();
    }

}
