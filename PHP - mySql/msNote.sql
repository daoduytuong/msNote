-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: mysql5045.site4now.net
-- Thời gian đã tạo: Th6 28, 2021 lúc 04:10 PM
-- Phiên bản máy phục vụ: 8.0.22
-- Phiên bản PHP: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `db_a764dd_tuongdb`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `note`
--

CREATE TABLE `note` (
  `ID` int NOT NULL,
  `username` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TieuDe` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `NoiDung` varchar(3000) COLLATE utf8_unicode_ci NOT NULL,
  `Nhan` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `note`
--

INSERT INTO `note` (`ID`, `username`, `TieuDe`, `NoiDung`, `Nhan`) VALUES
(66, 'Admin', 'Web Service', 'Dịch vụ web (tiếng Anh: web service) là sự kết hợp các máy tính cá nhân với các thiết bị khác, các cơ sở dữ liệu và các mạng máy tính để tạo thành một cơ cấu tính toán ảo mà người sử dụng có thể làm việc thông qua các trình duyệt mạng.[1]\n\nBản thân các dịch vụ này sẽ chạy trên các máy chủ trên nền Internet chứ không phải là các máy tính cá nhân, do vậy có thể chuyển các chức nǎng từ máy tính cá nhân lên Internet. Người sử dụng có thể làm việc với các dịch vụ thông qua bất kỳ loại máy nào có hỗ trợ web service và có truy cập Internet, kể cả các thiết bị cầm tay. Do đó các web service sẽ làm Internet biến đổi thành một nơi làm việc chứ không phải là một phương tiện để xem và tải nội dung.\n\nĐiều này cũng sẽ đưa các dữ liệu và các ứng dụng từ máy tính cá nhân tới các máy phục vụ của một nhà cung cấp dịch vụ web. Các máy phục vụ này cũng cần trở thành nguồn cung cấp cho người sử dụng cả về độ an toàn, độ riêng tư và khả nǎng truy nhập.\n\nCác máy phục vụ ứng dụng sẽ là một phần quan trọng của các web service bởi vì thường thì các máy chủ này thực hiện các hoạt động ứng dụng phức tạp dựa trên sự chuyển giao giữa người sử dụng và các chương trình kinh doanh hay các cơ sở dữ liệu của một tổ chức nào', '2'),
(72, 'Admin', 'SpaceX', 'Tập đoàn Công nghệ Khai phá Không gian, viết tắt theo tiếng Anh SpaceX (Space Exploration Technologies Corporation), là một công ty tư nhân về tên lửa đẩy và tàu vũ trụ của Mỹ có trụ sở tại Hawthorne, California. Công ty được thành lập năm 2002 bởi Elon Musk, một trong những doanh nhân đã sáng lập công ty PayPal và Tesla Motors. SpaceX đã phát triển các tên lửa đẩy Falcon 1, Falcon 9 và Falcon Heavy với mục đích trở thành các tên lửa có thể tái sử dụng được. Công ty đã phát triển tàu không gian Dragon và Crew Dragon được phóng lên quỹ đạo nhờ tên lửa Falcon 9 và siêu tên lửa Starship với dự định phóng lên sao Hoả trong năm 2023.', '1'),
(73, 'Admin', 'Vinfast', 'VinFast (hay VinFast LLC; viết tắt là VF), tên đầy đủ là Công ty trách nhiệm hữu hạn sản xuất và kinh doanh VinFast là một nhà sản xuất ô tô và xe máy điện của Việt Nam được thành lập vào năm 2017, có trụ sở đặt tại thành phố Hải Phòng, do ông James Benjamin DeLuca cùng Lê Thanh Hải làm Giám đốc điều hành. Công ty này là một thành viên của tập đoàn Vingroup, được ông Phạm Nhật Vượng sáng lập. Tên gọi công ty được viết tắt từ cụm từ \"Việt Nam – Phong cách – An toàn – Sáng tạo – Tiên phong\" (chữ Ph đổi thành F).[', '3'),
(74, 'Admin', 'tsmc', 'Taiwan Semiconductor Manufacturing Co., Ltd (TSMC) (tạm dịch: Công ty TNHH Sản xuất chế tạo chất bán dẫn Đài Loan, tiếng Trung: 台灣積體電路製造公司; bính âm: Táiwān Jītǐ Diànlù Zhìzào Gōngsī), còn được gọi là Taiwan Semiconductor, là tập đoàn chuyên về chế tạo chất bán dẫn lớn nhất thế giới, với trụ sở chính và các hoạt động chính nằm trong Khu Khoa học và Công nghiệp Tân Trúc tại thành phố Tân Trúc, Đài Loan', '4'),
(75, 'Admin', 'Android', 'Android là một hệ điều hành dựa trên nền tảng Linux được thiết kế dành cho các thiết bị di động có màn hình cảm ứng như điện thoại thông minh và máy tính bảng. Ban đầu, Android được phát triển bởi Android, Inc. với sự hỗ trợ tài chính từ Google và sau này được chính Google mua lại vào năm 2005.', '2'),
(83, 'Admin', 'Github', 'GitHub là một dịch vụ cung cấp kho lưu trữ mã nguồn Git dựa trên nền web cho các dự án phát triển phần mềm. GitHub cung cấp cả phiên bản trả tiền lẫn miễn phí cho các tài khoản. Các dự án mã nguồn mở sẽ được cung cấp kho lưu trữ miễn phí. Tính đến tháng 4 năm 2016, GitHub có hơn 14 triệu người sử dụng với hơn 35 triệu kho mã nguồn[3], làm cho nó trở thành máy chủ chứa mã nguồn lớn trên thế giới.[4]', '4');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `ID` int NOT NULL,
  `username` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(8) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`ID`, `username`, `password`) VALUES
(1, 'admin', '1'),
(21, 'Ad', '1'),
(22, 'Ads', '1');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `note`
--
ALTER TABLE `note`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
