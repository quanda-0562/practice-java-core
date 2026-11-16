# Bài 3: Quản lý phương tiện giao thông

Hai bản Java và Kotlin có cùng chức năng. Dữ liệu được lưu trong bộ nhớ trong phiên chạy.

## Thiết kế

| Lớp | Trách nhiệm |
| --- | --- |
| `Vehicle` (trừu tượng) | Số xe, hãng sản xuất, năm sản xuất, màu xe, chủ xe; kiểm tra dữ liệu chung và xuất thông tin. |
| `Car` | Kế thừa `Vehicle`, bổ sung số chỗ ngồi và loại động cơ. |
| `Motorcycle` | Kế thừa `Vehicle`, bổ sung dung tích động cơ (cc). |
| `Truck` | Kế thừa `Vehicle`, bổ sung trọng tải (tấn). |
| `Owner` | CMND, họ tên, email; một chủ xe có thể sở hữu nhiều xe. |
| `Manufacturer` | Enum giới hạn hãng: Honda, Yamaha, Toyota, Suzuki. |
| `VehicleManager` | Thêm, tìm kiếm, xóa, sắp xếp và thống kê; quản lý danh sách xe và hồ sơ chủ xe theo CMND. |
| `Main` | Menu tương tác để thực hiện 7 chức năng của spec. |

`VehicleManager` quản lý các lớp con qua kiểu `Vehicle`; khi xuất thông tin, loại xe và thông số riêng được lấy bằng đa hình.

## Quy tắc và cách hiểu spec

- Số xe có đúng 5 ký tự, bỏ khoảng trắng hai đầu và chuyển sang chữ hoa trước khi lưu/tìm. Không cho phép trùng số giữa các loại xe.
- Năm sản xuất phải lớn hơn 2000 và không vượt quá năm hiện tại, được lấy tại thời điểm chạy.
- CMND là chuỗi đúng 12 chữ số, giữ được số 0 đầu. Mỗi CMND xác định một hồ sơ chủ xe duy nhất. Khi nhập thêm xe cùng CMND, chương trình sử dụng lại hồ sơ có sẵn. API cũng chặn hồ sơ có cùng CMND nhưng khác tên/email.
- Email được kiểm tra theo dạng thông dụng `ten@tenmien.duoi`, không có khoảng trắng. Việc kiểm tra không xác minh hộp thư tồn tại.
- Tên, màu, loại động cơ không được trống. Số chỗ ngồi, dung tích và trọng tải phải dương; giá trị số thực phải hữu hạn.
- Mục 6 được hiểu là sắp xếp **theo số xe giảm dần**, bằng thứ tự chuỗi của số xe đã chuẩn hóa; thao tác cập nhật thứ tự danh sách và hiển thị kết quả.
- Mục 5 trả về tất cả hãng đồng hạng có nhiều xe nhất; danh sách trống sẽ báo chưa có xe.
- Xóa theo hãng xóa tất cả xe thuộc hãng đó, không xóa hồ sơ chủ xe. Báo cáo chỉ đếm các xe hiện còn quản lý, có hiển thị loại xe có số lượng 0.

## Chạy từ thư mục gốc repository

Java:

```bash
mkdir -p /tmp/vehicles-java
javac -d /tmp/vehicles-java 3/java/*.java
java -cp /tmp/vehicles-java Main
```

Kotlin:

```bash
kotlinc 3/kotlin/*.kt -include-runtime -d /tmp/vehicles-kotlin.jar
java -jar /tmp/vehicles-kotlin.jar
```

Chọn JDK 25 trên macOS nếu cần:

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 25)
export PATH="$JAVA_HOME/bin:$PATH"
```

Menu: `1` thêm xe, `2` tìm theo số xe, `3` tìm theo CMND, `4` xóa theo hãng, `5` hãng có nhiều xe nhất, `6` sắp xếp số xe giảm dần, `7` thống kê từng loại xe, `0` thoát. Khi nhập sai điều kiện, chương trình báo lỗi và không thêm xe.
