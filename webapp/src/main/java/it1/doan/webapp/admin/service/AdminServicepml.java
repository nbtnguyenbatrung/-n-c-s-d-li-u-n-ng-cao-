package it1.doan.webapp.admin.service;

import it1.doan.webapp.admin.dao.*;
import it1.doan.webapp.admin.service.impl.AdminService;
import it1.doan.webapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public List<SanPham> getPagesp(int start, int end) {
        return adminSanPhamDao.getPagesp(start,end);
    }


    @Override
    public List<SanPhamSize> getAllspsize() {
        return adminSanPhamSizeDao.getAllspsize();
    }

    @Override
    public List<SanPhamSize> getPagespsize(int start, int end) {
        return adminSanPhamSizeDao.getPagesanphamsize(start,end);
    }

    @Override
    public List<SanPhamKhuyenMai> getAllspkm() {
        return adminSanPhamKhuyenMaiDao.getAllspkm();
    }

    @Override
    public List<SanPhamKhuyenMai> getPagespkm(int start, int end) {
        return adminSanPhamKhuyenMaiDao.getPagespkm(start,end);
    }

    @Override
    public List<ThuongHieu> getPagethuonghieu(int start, int end) {
        return adminThuongHieuDAO.getPagethuonghieu(start,end);
    }

    @Override
    public List<ThuongHieu> getAllthuonghieu(int status) {
        return adminThuongHieuDAO.getAllthuonghieu(status);
    }


    @Override
    public List<LoaiSanPham> getAllloaisp(int status) {
        return adminLoaiSanPhamDao.getAllloaisp(status);
    }

    @Override
    public List<LoaiSanPham> getPageloaisp(int start, int end) {
        return adminLoaiSanPhamDao.getPageloaisp(start, end);
    }

    @Override
    public List<Size> getAllsize(int status) {
        return adminSizeDao.getAllsize(status);
    }

    @Override
    public List<Size> getPageSize(int start, int end) {
        return adminSizeDao.getPageSize(start,end);
    }

    @Override
    public List<NguoiDung> getAllnd() {
        return adminNguoIDungDao.getAllnd();
    }

    @Override
    public List<NguoiDung> getPagend(int start, int end) {
        return adminNguoIDungDao.getPagend(start,end);
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
    public List<DonHang> getAllhd() {
        return adminHoaDonDao.getAllhd();
    }

    @Override
    public List<DonHang> getPagehd(int start, int end) {
        return adminHoaDonDao.getPagehd(start,end);
    }

    @Override
    public List<KhuyenMai> getAllKhuyenMai() {
        return adminKhuyenMaiDao.getAllKhuyenMai();
    }

    @Override
    public List<KhuyenMai> getPagekm(int start, int end) {
        return adminKhuyenMaiDao.getPagekhuyenmai(start,end);
    }

    @Override
    public List<KhuyenMai> gethienKhuyenMai() {
        return adminKhuyenMaiDao.gethienKhuyenMai();
    }

}
