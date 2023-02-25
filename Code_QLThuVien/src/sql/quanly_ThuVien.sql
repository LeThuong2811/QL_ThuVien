create database ql_ThuVien

use ql_ThuVien
go


create table tacGia
(
	maTacGia int identity(1,1) , 
	tenTacGia nvarchar (50) not null , 
	website nvarchar (50) , 
	ghiChu nvarchar (100) , 
	constraint PK_tacGia primary key (maTacGia)
)
go
create table theLoai 
(
	maTheLoai int identity(1,1) not null, 
	tenTheLoai nvarchar (50) ,
	constraint PK_theLoai primary key (maTheLoai)
)
go
create table nhaXuatBan 
(
	maNXB int identity(1,1) not null , 
	tenNXB nvarchar (100) not null , 
	diaChi nvarchar (250) ,
	email nvarchar (50) not null ,
	thongTinNguoiDaiDien nvarchar (50) , 
	constraint PK_nhaXuatBan primary key (maNXB)
)
----SET ansi_warnings OFF --
go
create table theThuVien 
(
	soThe nvarchar (10) not null , 
	ngayBatDau date , 
	ngayHetHan date , 
	ghiChu nvarchar (100) , 
	constraint PK_theThuVien primary key (soThe)
)
go
create table docGia
(
	maDocGia nvarchar (10) not null , 
	tenDocGia nvarchar (100) not null , 
	diaChi nvarchar (400) , 
	ngaySinh date,
	gioiTinh nvarchar(10),
	Email nvarchar(200),
	soDienThoai nvarchar(20),
	soThe nvarchar (10) not null , 
	constraint PK_docGia primary key (maDocGia,soThe),
	constraint FK_docGia_soThe foreign key (soThe) references theThuVien(soThe)
)

go
create table nhanVien 
(
	maNhanVien nvarchar (10) , 
	hoTen nvarchar (200) not null ,
	ngaySinh date , 
	soDienThoai char (15), 
	gioiTinh nvarchar(10),
	luong int default 3000000,
	hinh nvarchar(200),
	cmnd nvarchar(20),
	email nvarchar(200),
	tinhTrang nvarchar(200) default N'Đang làm',
	constraint PK_nhanVien primary key (maNhanVien)
)

go
create table Sach 
(
	maSach nvarchar (10) not null , 
	tenSach nvarchar (200) not null , 
	maTacGia int,
	maTheLoai int , 
	maNXB int , 
	namXuatBan int ,
	soLuong int,
	hinh nvarchar(200),
	--constraint PK_Sach primary key(maSach,maTacGia,maTheLoai,maNXB),
	constraint PK_Sach primary key(maSach),
	constraint FK_Sach_tacGia foreign key (maTacGia) references tacGia(maTacGia),
	constraint FK_Sach_theLoai foreign key (maTheLoai) references theLoai(maTheLoai),
	constraint FK_Sach_nhaXuatBan foreign key (maNXB) references nhaXuatBan(maNXB)
)
go
create table muonTra 
(
	maMuonTra nvarchar (10) not null , 
	soThe nvarchar (10) not null , 
	maNhanVien nvarchar (10) , 
	ngayMuon date, 
	--constraint PK_muonTra primary key (maMuonTra, soThe ,maNhanVien),
	constraint PK_muonTra primary key (maMuonTra),
	constraint FK_muonTra_theThuVien foreign key (soThe) references theThuVien(soThe),
	constraint FK_muonTra_nhanVien foreign key (maNhanVien) references nhanVien(maNhanVien)
)
go
create table ctMuonTra 
(
	maMuonTra nvarchar (10) not null , 
	maSach nvarchar (10) not null ,  
	daTra int default (0),--Chưa trả
	ngayTra date ,
	soLuong int, 
	ghiChu nvarchar (100),
	constraint PK_ctMuonTra primary key(maMuonTra,maSach),
	constraint FK_ctMuonTra_muonTra foreign key (maMuonTra) references muonTra(maMuonTra),
	constraint FK_ctMuonTra_Sach foreign key (maSach) references Sach (maSach)
)
go
create table LoaiTaiKhoan
(
	maLoaiTK nvarchar (10) not null , 
	tenLoaiTK nvarchar (40) not null ,
	constraint PK_LoaiTK primary key(maLoaiTK)
)
go
create table TaiKhoan
(
	tenTaiKhoan nvarchar(30) not null,
	matKhau char(32) default('202cb962ac59075b964b07152d234b70') not null,
	maLoaiTK nvarchar (10) not null ,
	maNhanVien nvarchar (10) not null ,
	constraint PK_TaiKhoan primary key(tenTaiKhoan),
	constraint FK_TaiKhoan_LoaiTK foreign key (maLoaiTK) references LoaiTaiKhoan (maLoaiTK),
	constraint FK_TaiKhoan_NhanVien foreign key (maNhanVien) references NhanVien (maNhanVien)
)
--alter table TaiKhoan
--alter column tenTaiKhoan nvarchar(30)
--alter table TaiKhoan
--add constraint df_matKhau default('202cb962ac59075b964b07152d234b70') for matKhau
---------------
----------------------
go
set dateformat DMY
insert into nhanVien(maNhanVien,hoTen,ngaySinh,soDienThoai,gioiTinh,hinh,cmnd,email) values ('NV0000000', N'Lê Văn Thông','10/02/2001' ,'0396210106',N'Nam','src/HinhNhanVien/nv0.jpg','051201012625','levanthongqn35@gmail.com')
go
set dateformat DMY
insert into nhanVien(maNhanVien,hoTen,ngaySinh,soDienThoai,gioiTinh,hinh,cmnd,email) values 
('NV0000001', N'Hoàng Văn Thắng','01/01/1995' ,'0346179997',N'Nam','src/HinhNhanVien/nv1.jpg','05123847373','hoangvanthangNTTT35@gmail.com'),
('NV0000002', N'Nguyễn Văn Bình','23/08/1996' ,'0352456506',N'Nam','src/HinhNhanVien/nv2.jpg','051209687352','nguyenvanbinhNTTT35@gmail.com'),
('NV0000003', N'Nguyễn Ngọc Quí','12/08/1990' ,'0329521479',N'Nam','src/HinhNhanVien/nv3.jpg','051202910574','nguyenngocquiNTTT35@gmail.com'),
('NV0000004', N'Trần Thế Sinh','23/05/1993' ,'0866134413',N'Nam','src/HinhNhanVien/nv4.jpg','051204443327','tranthesinhqnNTTT35@gmail.com'),
('NV0000005', N'Trần Hồng Oanh','07/06/2000' ,'0387382379',N'Nữ','src/HinhNhanVien/nv5.jpg','051209685746','tranhongoanhNTTT35@gmail.com'),
('NV0000006', N'Trương Thị Thủy Tiên','20/02/2001' ,'0343178478',N'Nữ','src/HinhNhanVien/nv6.jpg','051200192846','truongthithuytienNTTT35@gmail.com'),
('NV0000007', N'Âu Thị Hồng Hoa','16/04/1996' ,'0348133349',N'Nữ','src/HinhNhanVien/nv7.jpg','051208970563','authihonghoaNTTT35@gmail.com'),
('NV0000008', N'Trịnh Thị Thu Hương','04/09/1999' ,'0368393786',N'Nữ','src/HinhNhanVien/nv8.jpg','051201234564','trinhthithuhuongNTTT35@gmail.com'),
('NV0000009', N'Đặng Thùy Trang','06/01/1998' ,'0394006336',N'Nữ','src/HinhNhanVien/nv9.jpg','051208574632','dangthuytrangNTTT35@gmail.com'),
('NV0000010', N'Bùi Thị Thùy Trang','15/03/1997' ,'0393414177',N'Nữ','src/HinhNhanVien/nv10.jpg','051202938473','buithithuytrangNTTT35@gmail.com')
insert into nhanVien(maNhanVien,hoTen,ngaySinh,soDienThoai,gioiTinh,hinh,cmnd,email) values ('NV0000011', N'Lê Trọng thưởng','01/01/1995' ,'0346170283',N'Nam','src/HinhNhanVien/default.jpg','0846372959392','letrongthuongNTTT35@gmail.com' )
insert into nhanVien(maNhanVien,hoTen,ngaySinh,soDienThoai,gioiTinh,hinh,cmnd,email) values ('NV0000012', N'Ngô Thụy Hồng Ngọc','01/01/1995' ,'0346179092',N'Nam','src/HinhNhanVien/default.jpg','051203475638','ngothuyhongn@gmail.com')
insert into nhanVien(maNhanVien,hoTen,ngaySinh,soDienThoai,gioiTinh,hinh,cmnd,email) values ('NV0000013', N'Đặng Quốc Anh Thái','01/01/1995' ,'0346179017',N'Nam','src/HinhNhanVien/default.jpg','051208273645','kaidang84@gmail.com')


go
insert into LoaiTaiKhoan values('nv',N'Nhân viên')
insert into LoaiTaiKhoan values('ad',N'Admin')
insert into LoaiTaiKhoan values('lock',N'Tài khoản bị khóa.')
go
insert into TaiKhoan values('admin','21232f297a57a5a743894a0e4a801fc3','ad','NV0000000')--pass admin: được mã hóa thành chuỗi:21232f297a57a5a743894a0e4a801fc3
insert into TaiKhoan(tenTaiKhoan,maLoaiTK,maNhanVien) values('hoangvanthang','nv','NV0000001')--pass default '123':202cb962ac59075b964b07152d234b70
insert into TaiKhoan(tenTaiKhoan,maLoaiTK,maNhanVien) values('nguyenvanbinh','nv','NV0000002')
insert into TaiKhoan(tenTaiKhoan,maLoaiTK,maNhanVien) values('nguyenngocqui','nv','NV0000003')
insert into TaiKhoan(tenTaiKhoan,maLoaiTK,maNhanVien) values('tranthesinh','nv','NV0000004')
insert into TaiKhoan(tenTaiKhoan,maLoaiTK,maNhanVien) values('truongthithuytien','nv','NV0000006')
insert into TaiKhoan(tenTaiKhoan,maLoaiTK,maNhanVien) values('authihonghoa','nv','NV0000007')
insert into TaiKhoan(tenTaiKhoan,maLoaiTK,maNhanVien) values('trinhthithuhuong','nv','NV0000008')
insert into TaiKhoan(tenTaiKhoan,maLoaiTK,maNhanVien) values('dangthuytrang','nv','NV0000009')
insert into TaiKhoan(tenTaiKhoan,maLoaiTK,maNhanVien) values('tranhongoanh','nv','NV0000005')
insert into TaiKhoan(tenTaiKhoan,maLoaiTK,maNhanVien) values('buithithuytrang','nv','NV0000010')
---
insert into TaiKhoan(tenTaiKhoan,maLoaiTK,maNhanVien) values('thaidqa','ad','NV0000013')
insert into TaiKhoan(tenTaiKhoan,maLoaiTK,maNhanVien) values('ngocnth','ad','NV0000012')
insert into TaiKhoan(tenTaiKhoan,maLoaiTK,maNhanVien) values('thuonglt','ad','NV0000011')
go
insert into tacGia(tenTacGia,website,ghiChu)
values 
(N'Nguyễn Nhật Ánh',null,null),--có hai con mèo ngồi bên cửa sổ(Văn học)
(N'Jeffrey Archer','https://www.jeffreyarcher.co.uk/',null),--hai số phận(văn học)
(N'Ở đây zui nè',null,null),
(N'Paulo Coelho','https://paulocoelhoblog.com/',null),--nhà giả kim(văn học)
(N'John Gray','https://marsvenus.com/shop/books/',null),--đàn ông sao hoả(tâm lý/giáo dục)
(N'Brian Tracy','https://www.briantracy.com/,null',null),--những đòn tâm lý trong bán hàng(kinh doanh)
(N'Ernst Gombrich','https://gombrich.co.uk/',null),--câu chuyện nghệ thuật(kiến trúc)
(N'Verity Davidson ',null,null),--Lần Đầu làm cha mẹ(thường thức)
(N'Hae Min',null,null),-- Yêu những điều không hoàn hảo(Tâm lý-Giáo dục)
(N'Nguyễn Duy Cần',null,null),--Tôi tự học(Tâm Lý/Giáo Dục)
(N'Alexandre Garel','https://www.alexandregarel.com/',null),--Sài Gòn - Portrait Of A City 2011-2020(văn hoá/du lịch)
(N'Fujiko F. Fujio ','https://comics.shogakukan.co.jp/mangasho/rist.html',null),--Doraemon và vương quốc trên mây
(N'Phạm Huy Hoàng','https://toidicodedao.com/',null),--code dạo ký sự(sách chuyên ngành)
(N'Marc Reklau','https://www.marcreklau.com/',null),--Nơi đến hạnh phúc(kỹ năng sống)
(N'Nguyên Phong',null,null),--hành trình về phương đông(tôn giáo)
(N'Nguyễn Anh Đức',null,null),
(N'Mã Mạo Thiên',null,null),--Tâm lý học biển cảm(kỹ năng sống)
(N'Jason Schenker','https://www.jasonschenker.com/',null),--tương lai sau đại dịch covid(kinh doanh)
(N'Sara Pennypacker','http://sarapennypacker.com/',null),--cáo pax(thiếu nhi)
(N'Cheri J. Meiners','https://cherijmeiners.net/',null)--Học Cách Sống Hòa Thuận - Tớ Biết Nội Quy, Tớ Tuân Thủ!(thiếu nhi)
---
go
insert into theLoai(tenTheLoai)
values 
(N'Truyện Tranh'),
(N'Văn Học'),
(N'Sách Chuyên Ngành'),
(N'Du Lịch'),
(N'Phong Thuỷ'),
(N'Tâm Lý'),
(N'Nghệ Thuật'),
(N'Ngoại Ngữ'),
(N'Kinh Doanh'),
(N'Sách Giáo Khoa'),
(N'Làm Đẹp'),
(N'Tạp chí'),
(N'Thiếu Nhi'),
(N'Đời Sống'),
(N'Âm Nhạc')
---
go
insert into nhaXuatBan(tenNXB,diaChi,email,thongTinNguoiDaiDien)
values 
(N'Nhà Xuất Bản Trẻ',N'161B Lý Chính Thắng, Phường 7, Quận 3 , TP. Hồ Chí Minh','hopthubandoc@nxbtre.com.vn',null),
(N'Nhà Xuất Bản Kim Đồng',N'55 Quang Trung, Hà Nội, Việt Nam','info@nxbkimdong.com.vn',null),
(N'Nhà Xuất Bản Hà Nội',N'Số 4 Phố Tống Duy Tân, quận Hoàn Kiếm, Hà Nội','nxbhanoi@yahoo.com.vn',null),
(N'Nhà Xuất Bản Lao Động',N'175 Giảng Võ, Đống Đa, Hà Nội','nxblaodong@yahoo.com ',N'Võ Thị Kim Thanh'),
(N'Nhà Xuất Bản Giáo Dục Việt Nam',N'Số 81 Trần Hưng Đạo, Hoàn Kiếm, Hà Nội','veph@nxbgd.vn',null),
(N'Nhà Xuất Bản Phụ Nữ',N'39 Hàng Chuối, Q. Hai Bà Trưng, Hà Nội','truyenthongvaprnxbpn@gmail.com',N'Khúc Thị Hoa Phượng'),
(N'Nhà Xuất Bản Thanh Hoá',N'Số 248 Trần Phú, Phường Ba Đình, Thành phố Thanh Hoá, Thanh Hoá','nxbthanhhoa@nxbhcm.com.vn',N'Hoàng Văn Tú'),
(N'Nhà Xuất Bản Văn Học',N' 18 Nguyễn Trường Tộ, phường Trúc Bạch, quận Ba Đình, thành phố Hà Nội','info@nxbvanhoc.com.vn',N'Nguyễn Anh Vũ'),
(N'Nhà Xuất Bản Tổng Hợp Thành Phố Hồ Chí Minh',N'62 Nguyễn Thị Minh Khai, Phường Đa Kao, Quận 1,Tp. Hồ Chí Minh','tonghop@nxbhcm.com.vn',N'Nguyễn Thị Thanh Thuỷ'),
(N'Nhà Xuất Bản Hồng Đức',N'Số 65, phố Tràng Thi, Phường Hàng Bông, Quận Hoàn Kiếm, Hà Nội','nhaxuatbanhongduc65@gmail.com',N'Bùi Việt Bắc')
----
go
SET DATEFORMAT DMY
insert into theThuVien
values 
('T0000001','1/01/2020','10/8/2021',NULL),
('T0000002','5/03/2020','22/11/2021',NULL),
('T0000003','8/05/2020','24/10/2021',NULL),
('T0000004','20/07/2020','26/11/2021',NULL),
('T0000005','10/02/2020','28/08/2021',NULL),
('T0000006','14/04/2020','10/09/2021',NULL),
('T0000007','16/06/2020','11/10/2021',NULL),
('T0000008','18/04/2020','15/09/2021',NULL),
('T0000009','20/10/2020','17/12/2021',NULL),
('T0000010','22/03/2020','19/07/2021',NULL)
---

set dateformat DMY
insert into docGia values 
('DG0000001',N'Huỳnh Thuý Duy',N'TP.HCM','10/1/2000',N'Nam','duyht22@gmail.com' ,'0396210106','T0000001'),
('DG0000002',N'Nguyễn Thanh Thao',N'Bà Rịa','25/5/2000',N'Nữ','thaont22@gmail.com' ,'0396211111','T0000002'),
('DG0000003',N'Phạm Huyền Trân',N'Vũng Tàu','4/2/2001',N'Nữ','tranph22@gmail.com' ,'0981112223','T0000003'),
('DG0000004',N'Nguyễn Thị Tường Vi',N'Bến Tre','18/7/1999',N'Nữ','vintt22@gmail.com' ,'0322222222','T0000004'),
('DG0000005',N'Trần Hồng Oanh',N'Bình Định','20/4/2000',N'Nữ','oanhth22@gmail.com' ,'0399999999','T0000005'),
('DG0000006',N'Bùi Nguyễn Khánh Tiên',N'Phú Tân','22/10/2001',N'Nữ','tienpnk22@gmail.com' ,'0398888888','T0000006'),
('DG0000007',N'Nguyễn Trung Kiên',N'Trà Vinh','19/9/1998','Nam','kiennt22@gmail.com' ,'0390000000','T0000007'),
('DG0000008',N'Nguyễn Anh Tài',N'Hải Phòng','20/12/2004','Nam','taina22@gmail.com' ,'0391111111','T0000008'),
('DG0000009',N'Nguyễn Kim Yến',N'Châu Đốc','15/11/2003',N'Nữ','yennk22@gmail.com' ,'0396333333','T0000009'),
('DG0000010',N'Phan Thị Kim Ngân',N'Cà Mau','24/9/2002',N'Nữ','nganptk22@gmail.com' ,'0396444444','T0000010')
---
go

---
go
insert into Sach
values 
('S0000001',N'Có Hai Con Mèo Ngồi Bên Cửa Sổ',1,2,1,2018,0,'src/imageSach/s1.jpg'),--NXB Trẻ
('S0000002',N'Hai Số Phận',2,2,8,2014,300,'src/imageSach/s2.jpg'),--Nhà sách Minh Thắng
('S0000003',N'Vui Vẻ Không Quạo',3,1,6,2020,320,'src/imageSach/s3.jpg'),--Skybook
('S0000004',N'Vui Vẻ Không Quạo Nha 2-Một Cuốn Sách Buồn Cười',3,1,6,2021,400,'src/imageSach/s4.jpg'),
('S0000005',N'Nhà Giả Kim',4,1,8,2018,0,'src/imageSach/s5.jpg'),--Nhã Nam
('S0000006',N'Đàn Ông Sao Hoả Đàn Bà Sao Kim',5,6,10,2019,220,'src/imageSach/s6.jpg'),--bizbooks
('S0000007',N'Những Đòn Tâm Lý Trong Bán Hàng',6,9,4,2018,230,'src/imageSach/s7.jpg'),--Alphabooks
('S0000008',N'Câu Truyện Nghệ Thuật',7,7,10,2020,300,'src/imageSach/s8.jpg'),--Omega plus
('S0000009',N'Doraemon Và Vương Quốc Trên Mây',10,1,2,2019,303,'src/imageSach/s9.jpg'),--Kim Đồng
('S0000010',N'Yêu Những Điều Không Hoàn Hảo',9,6,10,2020,290,'src/imageSach/s10.jpg')--Nhã Nam
---
go
set dateformat DMY
insert into muonTra
values	
('MT0000001','T0000001','NV0000002','20/03/2021'),
('MT0000002','T0000003','NV0000004','12/03/2021'),
('MT0000003','T0000005','NV0000005','13/04/2021'),
('MT0000004','T0000007','NV0000008','10/03/2021'),
('MT0000005','T0000009','NV0000010','22/02/2021'),
('MT0000006','T0000002','NV0000001','27/01/2021'),
('MT0000007','T0000004','NV0000002','15/03/2021'),
('MT0000008','T0000006','NV0000003','01/02/2021'),
('MT0000009','T0000008','NV0000004','04/03/2021'),
('MT0000010','T0000010','NV0000005','09/01/2021')
---
go
set dateformat DMY
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values ('MT0000001','S0000001',NULL,0,'10/09/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000001','S0000002',NULL,0,'10/09/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000001','S0000003',NULL,0,'10/09/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000002','S0000001',NULL,0,'10/10/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000002','S0000002',NULL,0,'10/09/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000006','S0000006',NULL,0,'10/09/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000006','S0000007',NULL,0,'10/09/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000006','S0000008',NULL,0,'10/01/2022',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000007','S0000008',NULL,0,'10/09/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000007','S0000009',NULL,0,'10/09/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000007','S0000010',NULL,0,'10/12/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000008','S0000001',NULL,0,'10/08/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000008','S0000003',NULL,0,'01/07/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000008','S0000007',NULL,0,'15/08/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000008','S0000005',NULL,0,'22/08/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000002','S0000004',NULL,0,'20/08/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000002','S0000005',NULL,0,'10/08/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000002','S0000006',NULL,0,'26/07/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000003','S0000010',NULL,0,'14/07/2021',1)
go
set dateformat DMY
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000003','S0000003',NULL,1,'10/08/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000003','S0000004',NULL,1,'14/07/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000003','S0000005',NULL,1,'24/07/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000004','S0000001',NULL,1,'19/08/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000004','S0000002',NULL,1,'22/06/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000004','S0000003',NULL,1,'12/08/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000005','S0000006',NULL,1,'17/07/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000005','S0000007',NULL,1,'20/07/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000005','S0000008',NULL,1,'18/07/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000008','S0000010',NULL,1,'03/06/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000008','S0000004',NULL,1,'03/06/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000009','S0000005',NULL,1,'20/07/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000009','S0000006',NULL,1,'20/07/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000009','S0000007',NULL,1,'20/07/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000010','S0000002',NULL,1,'30/07/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000010','S0000008',NULL,1,'30/07/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000010','S0000003',NULL,1,'30/07/2021',1)
insert into ctMuonTra(maMuonTra,maSach,ghiChu,daTra,ngayTra,soLuong)values('MT0000002','S0000003',NULL,1,'20/04/2021',1)

go
create trigger tg_CapNhatSoLuongTon_KhiThemCTMuon on ctMuonTra
for insert
as
	update Sach
	set soLuong = soLuong -  (select soLuong from inserted)
	where maSach = (select maSach from inserted)
go
go
create trigger tg_CapNhatSoLuongTon_KhiCapNhatCTMuon on ctMuonTra
for update
as
	update Sach
	set soLuong = (soLuong +  (select soLuong from deleted)) - (select soLuong from inserted)
	where maSach = (select maSach from inserted)

go
create trigger tg_CapNhatSoLuongTon_KhiXoaCTMuon on ctMuonTra
for delete
as
	update Sach
	set soLuong = (soLuong +  (select soLuong from deleted))
	where maSach = (select maSach from deleted)
go


------------------------------------END--------------------------------------------------------------

--select* from Sach,theLoai,tacGia,nhaXuatBan 
--where Sach.maTheLoai=theLoai.maTheLoai and Sach.maNXB=nhaXuatBan.maNXB and Sach.maTacGia=tacGia.maTacGia and (CAST(namXuatBan as nvarchar(5)) like '%'+'1999'+'%' or tenSach like '%'+'Có'+'%')

--select* from docGia,theThuVien where docGia.soThe=theThuVien.soThe
--select MAX(right(maDocGia,6)) from docGia
--select* from docGia

--select COUNT(*) as sl
--from muonTra,ctMuonTra,theThuVien,docGia 
--where muonTra.maMuonTra=ctMuonTra.maMuonTra and muonTra.soThe=theThuVien.soThe and theThuVien.soThe = docGia.soThe and maDocGia = 'DG0000012'

--select docGia.*
--from muonTra,ctMuonTra,theThuVien,docGia
--where muonTra.maMuonTra=ctMuonTra.maMuonTra and muonTra.soThe=theThuVien.soThe and theThuVien.soThe = docGia.soThe and maSach='S0000001'

--select sach.*,docGia.*,tenTheLoai
--from muonTra,ctMuonTra,theThuVien,docGia,Sach,theLoai
--where muonTra.maMuonTra=ctMuonTra.maMuonTra and muonTra.soThe=theThuVien.soThe 
--		and theThuVien.soThe = docGia.soThe and Sach.maSach = ctMuonTra.maSach and sach.maTheLoai= theLoai.maTheLoai
--		and muonTra.maMuonTra='MT0000001' and Sach.maSach='S0000002'

--select* from muonTra
--select* from ctMuonTra

--select* from muonTra,ctMuonTra,nhanVien
--where muonTra.maMuonTra=ctMuonTra.maMuonTra and muonTra.maNhanVien=nhanVien.maNhanVien and daTra = '0' and ngayTra < getdate()

--Update muonTra
--Set maNhanVien = 'NV001'
--from nhanVien
--where nhanVien.maNhanVien = muonTra.maNhanVien

---- Số lượt mượn theo sách
--select ctMuonTra.maSach,tenSach,COUNT(*) as soLuotMuon
--from ctMuonTra, Sach 
--where ctMuonTra.maSach=Sach.maSach
--group by ctMuonTra.maSach,tenSach
----Muon trong 1 tháng
--select ctMuonTra.maSach,tenSach,COUNT(*) as soLuotMuon
--from ctMuonTra, Sach,muonTra
--where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra and DATEDIFF(MONTH,ngayMuon,ngayTra) = 1
--group by ctMuonTra.maSach,tenSach
----Muon trong 1 tuần
--select ctMuonTra.maSach,tenSach,COUNT(*) as soLuotMuon
--from ctMuonTra, Sach,muonTra
--where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra and DATEDIFF(DAY,ngayMuon,ngayTra) <= 7
--group by ctMuonTra.maSach,tenSach
----Mượn từ ngày dđ/MM/YYYY -> dd/MM/YYYY
--select ctMuonTra.maSach,tenSach,COUNT(*) as soLuotMuon
--from ctMuonTra, Sach,muonTra
--where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra and CONVERT(nvarchar(30),ngayMuon) = '2021-03-20' and CONVERT(nvarchar(30),ngayTra)= '2021-09-10'
--group by ctMuonTra.maSach,tenSach
-------------------------------------------------------------------------------------------
---- Số lượt mượn theo đọc giả
--select docGia.maDocGia,tenDocGia,COUNT(*) as soLuotMuon
--from ctMuonTra,muonTra,Sach,theThuVien,docGia
--where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra and muonTra.soThe=theThuVien.soThe and docGia.soThe=theThuVien.soThe
--group by docGia.maDocGia,tenDocGia
----Muon trong 1 tháng
--select docGia.maDocGia,tenDocGia,COUNT(*) as soLuotMuon
--from ctMuonTra,muonTra,Sach,theThuVien,docGia
--where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra and muonTra.soThe=theThuVien.soThe and docGia.soThe=theThuVien.soThe and DATEDIFF(MONTH,ngayMuon,ngayTra) = 1
--group by docGia.maDocGia,tenDocGia
----Muon trong 1 tuần
--select docGia.maDocGia,tenDocGia,COUNT(*) as soLuotMuon
--from ctMuonTra,muonTra,Sach,theThuVien,docGia
--where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra and muonTra.soThe=theThuVien.soThe and docGia.soThe=theThuVien.soThe and DATEDIFF(DAY,ngayMuon,ngayTra) <= 7
--group by docGia.maDocGia,tenDocGia
-------------------------------------------------------------------------------------------
---- Số lượt mượn theo nhà xuất bản
--select nhaXuatBan.maNXB,tenNXB,COUNT(*) as soLuotMuon
--from ctMuonTra,Sach,nhaXuatBan
--where ctMuonTra.maSach=Sach.maSach and Sach.maNXB=nhaXuatBan.maNXB
--group by nhaXuatBan.maNXB,tenNXB
----Muon trong 1 tháng
--select nhaXuatBan.maNXB,tenNXB,COUNT(*) as soLuotMuon
--from ctMuonTra,Sach,nhaXuatBan,muonTra
--where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra and Sach.maNXB=nhaXuatBan.maNXB and DATEDIFF(MONTH,ngayMuon,ngayTra) = 1
--group by nhaXuatBan.maNXB,tenNXB
----Muon trong 1 tuần
--select nhaXuatBan.maNXB,tenNXB,COUNT(*) as soLuotMuon
--from ctMuonTra,Sach,nhaXuatBan,muonTra
--where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra and Sach.maNXB=nhaXuatBan.maNXB and DATEDIFF(DAY,ngayMuon,ngayTra) <= 7
--group by nhaXuatBan.maNXB,tenNXB
-------------------------------------------------------------------------------------------
---- Số lượt mượn theo tác giả
--select tacGia.maTacGia,tenTacGia,COUNT(*) as soLuotMuon
--from ctMuonTra,Sach,tacGia
--where ctMuonTra.maSach=Sach.maSach and Sach.maTacGia=tacGia.maTacGia
--group by tacGia.maTacGia,tenTacGia
----Muon trong 1 tháng
--select tacGia.maTacGia,tenTacGia,COUNT(*) as soLuotMuon
--from ctMuonTra,Sach,tacGia,muonTra
--where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra and Sach.maTacGia=tacGia.maTacGia and DATEDIFF(MONTH,ngayMuon,ngayTra) = 1
--group by tacGia.maTacGia,tenTacGia
----Muon trong 1 tuần
--select tacGia.maTacGia,tenTacGia,COUNT(*) as soLuotMuon
--from ctMuonTra,Sach,tacGia,muonTra
--where ctMuonTra.maSach=Sach.maSach and muonTra.maMuonTra=ctMuonTra.maMuonTra and Sach.maTacGia=tacGia.maTacGia and DATEDIFF(DAY,ngayMuon,ngayTra) = 7
--group by tacGia.maTacGia,tenTacGia
-------------------------------------------------------------------------------------------
---- Số lượt mượn theo thể loại
--select theLoai.maTheLoai,tenTheLoai,COUNT(*) as soLuotMuon
--from ctMuonTra,Sach,theLoai
--where ctMuonTra.maSach=Sach.maSach and Sach.maTheLoai=theLoai.maTheLoai
--group by theLoai.maTheLoai,tenTheLoai
----Muon trong 1 tháng
--select theLoai.maTheLoai,tenTheLoai,COUNT(*) as soLuotMuon
--from ctMuonTra,Sach,theLoai,muonTra
--where ctMuonTra.maSach=Sach.maSach and Sach.maTheLoai=theLoai.maTheLoai and muonTra.maMuonTra=ctMuonTra.maMuonTra and DATEDIFF(MONTH,ngayMuon,ngayTra) = 1
--group by theLoai.maTheLoai,tenTheLoai
----Muon trong 1 tuần
--select theLoai.maTheLoai,tenTheLoai,COUNT(*) as soLuotMuon
--from ctMuonTra,Sach,theLoai,muonTra
--where ctMuonTra.maSach=Sach.maSach and Sach.maTheLoai=theLoai.maTheLoai and muonTra.maMuonTra=ctMuonTra.maMuonTra and DATEDIFF(DAY,ngayMuon,ngayTra) <= 7
--group by theLoai.maTheLoai,tenTheLoai
-------------------------------------------------------------------------------------------
---- Danh sách đọc giả hết hạn thẻ
--select maDocGia,tenDocGia,theThuVien.ngayHetHan
--from docGia,theThuVien
--where docGia.soThe=theThuVien.soThe and ngayHetHan < GETDATE()
---- Sách đã mượn hết
--select maSach,tenSach,soLuong
--from Sach
--where soLuong=0
---- Danh sách vi phạm
--select maSach,tenSach,soLuong
--from Sach
--where soLuong=0

--update TaiKhoan
--set maLoaiTK = 'lock'
--where maNhanVien = ''

