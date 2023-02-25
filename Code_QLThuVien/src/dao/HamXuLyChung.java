/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Year;
import java.util.Vector;
import pojo.SoNguyen;

/**
 *
 * @author ADMIN
 */
public class HamXuLyChung {

    KetNoiSQL conSQL = new KetNoiSQL();

    public boolean kiemTraChuoiChuaToanKhoangTrang(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    public int timViTriVctcbb(Vector vctCbb, String gt) {
        for (int i = 0; i < vctCbb.size(); i++) {
            if (vctCbb.get(i).toString().equals(gt)) {
                return i;
            }
        }
        return -1;
    }
    //Mã hóa password theo MD5
    public String getHashMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

//    public boolean ktraKiTuIsNumber(String s) {
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
//                return false;
//            }
//        }
//        return true;
//    }
    public boolean kTraChuoiIsNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }



    //kiểm tra chuỗi s2 phải lớn hơn s1
    public boolean ktraNgay(String s1, String s2) {
        String date1[] = s1.split("-");
        int nam1 = Integer.parseInt(date1[0]);
        int thang1 = Integer.parseInt(date1[1]);
        int ngay1 = Integer.parseInt(date1[2]);

        String date2[] = s2.split("-");
        int nam2 = Integer.parseInt(date2[0]);
        int thang2 = Integer.parseInt(date2[1]);
        int ngay2 = Integer.parseInt(date2[2]);

        if (nam2 > nam1) {
            return true;
        } else if (nam2 < nam1) {
            return false;
        } else {
            if (thang2 > thang1) {
                return true;
            } else if (thang2 < thang1) {
                return false;
            } else {
                if (ngay2 >= ngay1) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public void layNgayThangNam(Vector d, Vector m, Vector y) {
        for (int i = 1; i <= 31; i++) {
            if (i >= 1 && i <= 9) {
                String a = "0" + i;
                d.add(a);
            } else {
                d.add(i);
            }
        }
        for (int i = 1; i <= 12; i++) {
            if (i >= 1 && i <= 9) {
                String a = "0" + i;
                m.add(a);
            } else {
                m.add(i);
            }
        }
        for (int i = 1975; i <= Year.now().getValue() + 4; i++) {
            y.add(i);
        }
    }

    

//    public void doiChuoiKhiNhapSai(SoNguyen sn) {
//        String s = sn.getValues2();
//        for (int i = 0; i < sn.getValues2().length(); i++) {
//            if (Character.isDigit(s.charAt(i)) == false) {
//                sn.setValues2(sn.getValues2().substring(0, i) + sn.getValues2().substring(i + 1));
//                i = i - 1;
//            }
//        }
//
//    }

    public boolean kTraChuoiAllIs0(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                return false;
            }
        }
        return true;
    }

    public boolean kTraNamNhuan(int nYear) {
        if ((nYear % 4 == 0 && nYear % 100 != 0) || nYear % 400 == 0) {
            return true;
        }
        return false;

        // <=> return ((nYear % 4 == 0 && nYear % 100 != 0) || nYear % 400 == 0);
    }

    // Hàm trả về số ngày trong tháng thuộc năm cho trước
    public int tinhSoNgayTrongThang(int nMonth, int nYear) {
        int nNumOfDays = 0;
        switch (nMonth) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                nNumOfDays = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                nNumOfDays = 30;
                break;
            case 2:
                if (kTraNamNhuan(nYear)) {
                    nNumOfDays = 29;
                } else {
                    nNumOfDays = 28;
                }
                break;
        }

        return nNumOfDays;
    }

// Hàm kiểm tra ngày dd/mm/yyyy cho trước có phải là ngày hợp lệ
    public boolean kTraNgayHopLe(int nDay, int nMonth, int nYear) {
        // Kiểm tra năm
        if (nYear < 0) {
            return false; // Ngày không còn hợp lệ nữa!
        }

        // Kiểm tra tháng
        if (nMonth < 1 || nMonth > 12) {
            return false; // Ngày không còn hợp lệ nữa!
        }

        // Kiểm tra ngày
        if (nDay < 1 || nDay > tinhSoNgayTrongThang(nMonth, nYear)) {
            return false; // Ngày không còn hợp lệ nữa!
        }

        return true; // Trả về trạng thái cuối cùng...
    }
    public boolean checkPhone(String phone){
        // Bieu thuc chinh quy mo ta dinh dang so dien thoai
        String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        return phone.matches(reg);
    }
     public boolean checkEmail(String email) {
           String reg = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           return email.matches(reg);
    }
     public boolean checkUsername(String username)
     {
         //chữ thường không dấu, có độ dài từ 6 kí tự trở lên
         String reg="[a-z0-9_-]{6,}$";
         return username.matches(reg);
     }
     public boolean checkPsssword(String username)
     {
         //Biểu thức mô tả định dạng password
         String reg="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
         //giải thích:
         //^ : Bắt đầu biểu thức 
         //(?=.*[0-9]) : Chứa ít nhất 1 chữ số.
         //(?=.*[a-z]) : Chứa ít nhất 1 chữ cái viết Thường.
         //(?=.*[A-Z]) : Chứa ít nhất 1 chữ cái viết Hoa.
         //(?=\\S+$)   : Không chứa khoảng trắng.
         //.{8,}       : từ 8 kí tự trở lên.
         //$ : Kết thúc biểu thức.
         //(?=.*[@#$%^&+=]): chứa ít nhất 1 kí tự đặc biệt (Không set trường hợp này).
         return username.matches(reg);
     }
     
}
