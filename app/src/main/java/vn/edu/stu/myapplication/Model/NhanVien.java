package vn.edu.stu.myapplication.Model;

public class NhanVien {
    private Integer ID;
    private String Ten;
    private Integer SoDienThoai;
    private byte[] Anh;
    private String Loai;
    private Integer Luong;
    private Integer LuongCoBan;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public Integer getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(Integer soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public byte[] getAnh() {
        return Anh;
    }

    public void setAnh(byte[] anh) {
        Anh = anh;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public Integer getLuong() {
        return Luong;
    }

    public void setLuong(Integer luong) {
        Luong = luong;
    }

    public Integer getLuongCoBan() {
        return LuongCoBan;
    }

    public void setLuongCoBan(Integer luongCoBan) {
        LuongCoBan = luongCoBan;
    }

    public NhanVien() {
    }

    public NhanVien(String ten) {
        Ten = ten;
    }

    public NhanVien(Integer ID, String ten) {
        this.ID = ID;
        Ten = ten;
    }

    public NhanVien(Integer ID, String ten, Integer soDienThoai) {
        this.ID = ID;
        Ten = ten;
        SoDienThoai = soDienThoai;
    }

    public NhanVien(Integer ID, String ten, Integer soDienThoai, byte[] anh) {
        this.ID = ID;
        Ten = ten;
        SoDienThoai = soDienThoai;
        Anh = anh;
    }

    public NhanVien(Integer ID, String ten, Integer soDienThoai, byte[] anh, String loai) {
        this.ID = ID;
        Ten = ten;
        SoDienThoai = soDienThoai;
        Anh = anh;
        Loai = loai;
    }

    public NhanVien(Integer ID, String ten, Integer soDienThoai, byte[] anh, String loai, Integer luong) {
        this.ID = ID;
        Ten = ten;
        SoDienThoai = soDienThoai;
        Anh = anh;
        Loai = loai;
        Luong = luong;
    }

    public NhanVien(Integer ID, String ten, Integer soDienThoai, byte[] anh, String loai, Integer luong, Integer luongCoBan) {
        this.ID = ID;
        Ten = ten;
        SoDienThoai = soDienThoai;
        Anh = anh;
        Loai = loai;
        Luong = luong;
        LuongCoBan = luongCoBan;
    }

    @Override
    public String toString() {
        return Loai;
    }
}
