-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: mysql5045.site4now.net
-- Thời gian đã tạo: Th6 26, 2021 lúc 07:20 PM
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
  `username` varchar(10) NOT NULL,
  `TieuDe` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `NoiDung` varchar(3000) NOT NULL,
  `Nhan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `note`
--

INSERT INTO `note` (`ID`, `username`, `TieuDe`, `NoiDung`, `Nhan`) VALUES
(64, 'Admin', 'Ghi chú số 1', 'Nội dung số 1', 'NULL'),
(65, 'Admin', 'Android', 'Android là một hệ điều hành dựa trên nền tảng Linux được thiết kế dành cho các thiết bị di động có màn hình cảm ứng như điện thoại thông minh và máy tính bảng. Ban đầu, Android được phát triển bởi Android, Inc. với sự hỗ trợ tài chính từ Google và sau này được chính Google mua lại vào năm 2005.', '1'),
(66, 'Admin', 'Web Service', 'Dịch vụ web (tiếng Anh: web service) là sự kết hợp các máy tính cá nhân với các thiết bị khác, các cơ sở dữ liệu và các mạng máy tính để tạo thành một cơ cấu tính toán ảo mà người sử dụng có thể làm việc thông qua các trình duyệt mạng.[1]\n\nBản thân các dịch vụ này sẽ chạy trên các máy chủ trên nền Internet chứ không phải là các máy tính cá nhân, do vậy có thể chuyển các chức nǎng từ máy tính cá nhân lên Internet. Người sử dụng có thể làm việc với các dịch vụ thông qua bất kỳ loại máy nào có hỗ trợ web service và có truy cập Internet, kể cả các thiết bị cầm tay. Do đó các web service sẽ làm Internet biến đổi thành một nơi làm việc chứ không phải là một phương tiện để xem và tải nội dung.\n\nĐiều này cũng sẽ đưa các dữ liệu và các ứng dụng từ máy tính cá nhân tới các máy phục vụ của một nhà cung cấp dịch vụ web. Các máy phục vụ này cũng cần trở thành nguồn cung cấp cho người sử dụng cả về độ an toàn, độ riêng tư và khả nǎng truy nhập.\n\nCác máy phục vụ ứng dụng sẽ là một phần quan trọng của các web service bởi vì thường thì các máy chủ này thực hiện các hoạt động ứng dụng phức tạp dựa trên sự chuyển giao giữa người sử dụng và các chương trình kinh doanh hay các cơ sở dữ liệu của một tổ chức nào đó.', '2'),
(67, 'Admin', 'Firebase', 'Firebase là dịch vụ cơ sở dữ liệu hoạt động trên nền tảng đám mây – cloud. Kèm theo đó là hệ thống máy chủ cực kỳ mạnh mẽ của Google. Chức năng chính là giúp người dùng lập trình ứng dụng bằng cách đơn giản hóa các thao tác với cơ sở dữ liệu. Cụ thể là những giao diện lập trình ứng dụng API đơn giản.', '3');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `ID` int NOT NULL,
  `username` varchar(10) NOT NULL,
  `password` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`ID`, `username`, `password`) VALUES
(1, 'admin', '1');

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
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
