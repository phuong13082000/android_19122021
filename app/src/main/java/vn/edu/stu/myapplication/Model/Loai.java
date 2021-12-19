package vn.edu.stu.myapplication.Model;

public class Loai {
    private Integer ID;
    private String Loai;
    private Integer Luong;
    private Integer DoanhThu;
    private Integer TroCap;
    private Integer SoLuong;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
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

    public Integer getDoanhThu() {
        return DoanhThu;
    }

    public void setDoanhThu(Integer doanhThu) {
        DoanhThu = doanhThu;
    }

    public Integer getTroCap() {
        return TroCap;
    }

    public void setTroCap(Integer troCap) {
        TroCap = troCap;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer soLuong) {
        SoLuong = soLuong;
    }

    public Loai() {
    }

    public Loai(String loai) {
        Loai = loai;
    }

    public Loai(Integer ID, String loai) {
        this.ID = ID;
        Loai = loai;
    }

    public Loai(Integer ID, String loai, Integer luong) {
        this.ID = ID;
        Loai = loai;
        Luong = luong;
    }

    public Loai(Integer ID, String loai, Integer luong, Integer doanhThu) {
        this.ID = ID;
        Loai = loai;
        Luong = luong;
        DoanhThu = doanhThu;
    }

    public Loai(Integer ID, String loai, Integer luong, Integer doanhThu, Integer troCap) {
        this.ID = ID;
        Loai = loai;
        Luong = luong;
        DoanhThu = doanhThu;
        TroCap = troCap;
    }

    public Loai(Integer ID, String loai, Integer luong, Integer doanhThu, Integer troCap, Integer soLuong) {
        this.ID = ID;
        Loai = loai;
        Luong = luong;
        DoanhThu = doanhThu;
        TroCap = troCap;
        SoLuong = soLuong;
    }

    @Override
    public String toString() {
        return Loai;
    }
}
