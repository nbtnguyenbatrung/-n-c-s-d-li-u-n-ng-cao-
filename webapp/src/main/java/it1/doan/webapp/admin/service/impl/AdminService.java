package it1.doan.webapp.admin.service.impl;

import it1.doan.webapp.model.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public interface AdminService {
    // sản phẩm
    List<SanPham> getAllsp(int status);
    List<SanPham> getPagesp(int start , int end ,String keyword);
    List<SanPham> getallsp(String keyword);
    // sản phẩm  size
    List<SanPhamSize> getAllspsize(String keyword);
    List<SanPhamSize> getPagespsize(int start , int end,String keyword );
    // sản phảm khuyến mại
    List<SanPhamKhuyenMai> getAllspkm(String keyword);
    List<SanPhamKhuyenMai> getPagespkm(int start , int end,String keyword);
    // sản phẩm đánh giá

    // thương hiệu
    List<ThuongHieu> getPagethuonghieu(int start , int end ,String keyword);
    List<ThuongHieu> getAllthuonghieu(int status);
    List<ThuongHieu> getallthuonghieu(String keyword);
    // loại sản phẩm
    List<LoaiSanPham> getAllloaisp(int status);
    List<LoaiSanPham> getPageloaisp(int start , int end ,String keyword);
    List<LoaiSanPham> getAllloaisp(String keyword);
    // size
    List<Size> getAllsize(int status);
    List<Size> getPageSize(int start , int end , String keyword);
    List<Size> getallsize(String keyword);
    // người dùng
    List<NguoiDung> getAllnd();
    List<NguoiDung> getPagend(int start , int end ,String keyword);
    List<NguoiDung> getAllnd(String keyword);
    // đơn hàng
    List<DonHang> getAlldh();
    List<DonHang> getPagedh(int start , int end );
    // hóa đơn
    List<DonHang> getAllhd(Date startdate , Date enddate);
    List<DonHang> getPagehd(int start , int end ,Date startdate , Date enddate);

    // khuyến mại
    List<KhuyenMai> getAllKhuyenMai();
    List<KhuyenMai> getPagekm(int start , int end , String keyword);
    List<KhuyenMai> getAllKhuyenMai(String keyword);
    List<KhuyenMai> gethienKhuyenMai();
}
