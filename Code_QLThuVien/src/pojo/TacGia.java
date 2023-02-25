/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author ADMIN
 */
public class TacGia {
    private String maTG,tenTG,website,ghiChu;
private int soLuotMuon;

    public int getSoLuotMuon() {
        return soLuotMuon;
    }

    public void setSoLuotMuon(int soLuotMuon) {
        this.soLuotMuon = soLuotMuon;
    }

    public String getMaTG() {
        return maTG;
    }

    public void setMaTG(String maTG) {
        this.maTG = maTG;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public TacGia() {
        this.maTG = "";
        this.tenTG = "";
        this.website = "";
        this.ghiChu = "";
    }

    public TacGia(String maTG, String tenTG, String website, String ghiChu) {
        this.maTG = maTG;
        this.tenTG = tenTG;
        this.website = website;
        this.ghiChu = ghiChu;
    }

    public TacGia(String tenTG, String website, String ghiChu) {
        this.tenTG = tenTG;
        this.website = website;
        this.ghiChu = ghiChu;
    }
    
    
    
    
}
