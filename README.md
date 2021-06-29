# msNote
## Ứng dụng ghi chú sử dụng Web service

Đây là mã nguồn của project Android  `Ứng dụng ghi chú sử dụng Web service`
Ứng dụng này được viết bằng ngôn ngữ java, php sử dụng cơ sở dữ liệu mySql
Ứng dụng được xây dựng bằng cách chia hệ thống làm 2 thành phần client – server. Thực hiện kết nối, trao đổi dữ liệu bằng web service.
Trong project này, ứng dụng cài trên điện thoại đóng vai trò là client, thực hiện gửi và nhận dữ liệu với 1 cơ sở dữ liệu được đặt trên hosting thông qua kết nối internet, phần này đóng vai trò server.
Việc trao đổi dữ liệu bằng cách sử dụng các phương thức của thư viện OkHTTP để thực hiện truy cập internet, nhận dữ liệu dạng mảng Json và gửi dữ liệu bằng giao thức POST. Json sau khi được nhận về sẽ được xử lý bằng cách chia nhỏ thành các Object rồi lấy từng giá trị thành phần. Các hoạt động gửi nhận dữ liệu và xử lý json được thực hiện bằng 1 hàm kế thừa từ AsyncTask.
Ở server, viết các file php để nhận, xử lý dữ liệu được gửi từ client và trả lại kết quả. Dữ liệu được nhận bằng giao thức POST.  Quá trình xử lý là việc thực hiện các thao tác select, update, delete với cơ sở dữ liệu mySql. Việc gọi lệnh sql để thực hiện thao tác với cơ sở dữ liệu sẽ trả lại kết quả nếu thành công hoặc thất bại, “true” nếu thành công hoặc các mã lỗi  “ERROR__” nếu thất bại vd: ERROR01, ERROR02…

## Giao diện
![Screenshot_2021-06-29-05-30-17-66_a4e1cf726750bb7f4830b7074dbaf8c2](https://user-images.githubusercontent.com/76141709/123713337-61ab9800-d89e-11eb-9cc4-a8409758f310.jpg)
 [Chi tiết giao diện và hướng dẫn sử dụng](https://github.com/daoduytuong/msNote/blob/c5b16c154c110c1449b23dcf42a6e7f196b338c8/hdsd.pdf)
## Chức năng chính

- Tự động đăng nhập
- Đăng ký tài khoản/Đăng nhập
- Xem danh sách các ghi chú
- Xem chi tiết ghi chú
- Thêm ghi chú dạng văn bản và đánh dấu màu ghi chú
- Sửa nội dung ghi chú
- Xoá ghi chú
- Tìm kiếm ghi chú
- Lọc ghi chú nhóm
- Sao chép nội dung ghi chú

## Cơ sở dữ liệu
- Bảng user
  |  Tên  |  Kiểu dữ liệu  |  Ghi chú  |
  |  :-----  |  :----------  |  :--------------  |  
  | ID | int| Key | 
  | username | varchar |  | 
  | password | varchar |  |  

- Bảng note
  
  |  Tên  |  Kiểu dữ liệu  |  Ghi chú  |
  |  :-----  |  :----------  |  :--------------  |  
  | ID | int| Key | 
  | username | varchar |  | 
  | TieuDe | varchar |  |  
  | NoiDung | varchar |  | 
  | Nhan | varchar |  | 

## Các thành phần quan trọng
- Widgets (TextView, SearchView, Button, FloatingActionButton... )
- Spinner
- Dialog/Toast
- Menu
- Intent
- Drawer navigation
- Web service
- Custom ListView/Custom Adapter
- SharedPreferences
- AsyncTask
## Hướng dẫn cài đặt
- Với hosting
  - Tạo hosting(hostbuddy...) và cơ sở dữ liệu
  - Sửa thông tin database trong các file php và up lên host
  - Sửa liên kết trong các hàm Post dữ liệu trong mã nguồn ứng dụng
- Với Local Host
  - Tạo cơ sở dữ liệu
  - Copy file PHP vàp htdocs và sửa thông tin database
  - Lấy IP của máy, ứng dụng sẽ thể truy cập trực tiếp bằng link localhost vì vậy cần lấy IP của máy [xem tại đây](https://github.com/daoduytuong/msNote/blob/387ec2a70a202231790978de58b54eda55e78dbb/PHP%20-%20mySql/L%E1%BA%A5y%20IP%20PC%20ch%E1%BA%A1y%20localhost.pdf)
  - Sửa liên kết trong các hàm Post dữ liệu trong mã nguồn ứng dụng
  - Chạy và sửa lỗi
### Thư viện
	- com.squareup.okhttp3:okhttp:4.9.0 
	- com.google.android.material:material:1.4.0-rc01
	- androidx.
### Tham khảo
- [Video hướng dẫn set màu cho icon trên Navigation Draw, không bị đè bởi màu hệ thống](https://www.youtube.com/watch?v=6SrKOBV_hx8&t=893s)
- SearchView: https://www.youtube.com/watch?v=uiU6L_8j0ws
- [Android OkHttp - Bài 3: Post dữ liệu lên server - YouTube](https://www.youtube.com/watch?v=aJJcpR6PvYo)
- [Android OkHttp - Bài 4: Post file lên server - YouTube](https://www.youtube.com/watch?v=x43xArbrJpI)
- [Lập trình Android: Listview Custom Adapter - Phần 1 - YouTube](https://www.youtube.com/watch?v=grTGykNJ74A)
- [TextInputEditText - Material Design Edit Text | Android Studio Tutorial - YouTube](https://www.youtube.com/watch?v=IxhIa3eZxz8)
- [android cb Custom ListView trong Android lab4 P1 - YouTube](https://www.youtube.com/watch?v=dM7JhBExmsc)
- [Android Navigation Drawer Menu Material Design | Android studio tutorial | Part 1 - YouTube](https://www.youtube.com/watch?v=HwYENW0RyY4)
- [Customise Status bar | transparent status bar | Change status bar colour | Change icon colours - YouTube](https://www.youtube.com/watch?v=a8NOQ6gIul0)
- [Lập trình Android A-Z - Bài 193: Submit App | Tạo key store và build app release - YouTube](https://www.youtube.com/watch?v=yKnyYBTA_C4)
- [Lập trình Android - SharedPreferences Bài 3: Lưu và lấy dữ liệu - YouTube](https://www.youtube.com/watch?v=a-GYAfWEwIc)
- https://viblo.asia/p/okhttp-3-l0rvmxmkGyqA
- https://viblo.asia/p/cach-tao-navigation-drawer-ByEZk0NElQ0
- https://stackoverflow.com/questions/17546101/get-real-path-for-uri-android
- [Lập trình Android: Floating Action Button - Phần 1 - YouTube](https://www.youtube.com/watch?v=h3ZC5JeWXto)
- [Navigation Drawer + Toolbar + Fragment trong Android - [Android Tutorial - #30] - YouTube](https://www.youtube.com/watch?v=fIrQNDqKpzs)
- [Material Buttons: Text, Outlined, and Contained Button with icons. Android Studio Tutorial (Kotlin) - YouTube](https://www.youtube.com/watch?v=tOwZTXPOmJU)
- [Lập trình Android: Listview Custom Adapter - Phần 1 - YouTube](https://www.youtube.com/watch?v=grTGykNJ74A)
- [Copy Text to Clipboard | Android Studio - YouTube](https://www.youtube.com/watch?v=nlYT3rfsXN4)
- [Change Status Bar Color In Xamarin forms - YouTube](https://www.youtube.com/watch?v=6sRTOvrFYm0)
## Tác giả

Project được thực hiện bởi:
- Đào Duy Tường
-	Email: daoduytuong.dhqn@gmail.com
-	Github: https://github.com/daoduytuong
	
ĐHQN, Quy Nhơn - Tháng 6 năm 2021

