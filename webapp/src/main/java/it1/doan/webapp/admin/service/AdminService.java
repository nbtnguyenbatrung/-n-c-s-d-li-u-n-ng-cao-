package it1.doan.webapp.admin.service;

import it1.doan.webapp.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AdminService {
    // sản phẩm
    List<SanPham> getAllsp(int status);
    List<SanPham> getPagesp(int start , int end );
    // sản phẩm  size
    List<SanPhamSize> getAllspsize();
    List<SanPhamSize> getPagespsize(int start , int end );
    // sản phảm khuyến mại
    List<SanPhamKhuyenMai> getAllspkm();
    List<SanPhamKhuyenMai> getPagespkm(int start , int end );
    // sản phẩm đánh giá

    // thương hiệu
    List<ThuongHieu> getPagethuonghieu(int start , int end );
    List<ThuongHieu> getAllthuonghieu(int status);
    // loại sản phẩm
    List<LoaiSanPham> getAllloaisp(int status);
    List<LoaiSanPham> getPageloaisp(int start , int end );
    // size
    List<Size> getAllsize(int status);
    List<Size> getPageSize(int start , int end );
    // người dùng
    List<NguoiDung> getAllnd();
    List<NguoiDung> getPagend(int start , int end );
    // đơn hàng
    List<DonHang> getAlldh();
    List<DonHang> getPagedh(int start , int end );
    // hóa đơn
    List<DonHang> getAllhd();
    List<DonHang> getPagehd(int start , int end );

    // khuyến mại
    List<KhuyenMai> getAllKhuyenMai();
    List<KhuyenMai> getPagekm(int start , int end);
    List<KhuyenMai> gethienKhuyenMai();
}
