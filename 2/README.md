## Thiết kế lớp

| Lớp | Thuộc tính và trách nhiệm |
| --- | --- |
| `Goods` (trừu tượng) | Mã hàng, tên, số lượng tồn, đơn giá; khai báo loại hàng, thuế suất, đánh giá tiêu thụ; tính tiền VAT và xuất thông tin chung. |
| `Food` | Kế thừa `Goods`; ngày sản xuất, ngày hết hạn, nhà cung cấp; VAT 5%. |
| `Electronics` | Kế thừa `Goods`; số tháng bảo hành, công suất kW; VAT 10%. |
| `Crockery` | Kế thừa `Goods`; nhà sản xuất, ngày nhập kho; VAT 10%. |
| `DSHH` | Quản lý một mảng `Goods` (`Array<Goods?>` trong Kotlin); thêm hàng, kiểm tra trùng mã, xuất danh sách, tổng số lượng và VAT theo loại. Mảng tự tăng kích thước khi đầy. |
| `Main` | Menu nhập dữ liệu, chọn loại hàng và gọi chức năng quản lý. |

`DSHH` chứa các đối tượng thuộc ba lớp con qua kiểu chung `Goods`. Khi xuất thông tin, phương thức đánh giá tiêu thụ và thuế suất của từng lớp con được gọi theo cơ chế đa hình.

## Quy tắc

- Số lượng, đơn giá, tháng bảo hành và công suất không âm. Mã, tên, nhà cung cấp và nhà sản xuất không được để trống.
- Ngày hết hạn phải bằng hoặc sau ngày sản xuất.
- Điện tử: tồn kho `< 3` → `Selling well` (bán chạy).
- Thực phẩm: tồn kho `> 0` và ngày hết hạn trước hôm nay → `Hard to sell` (khó bán). Ngày hết hạn đúng hôm nay chưa được coi là đã hết hạn.
- Sành sứ: tồn kho `> 50` và thời gian lưu kho `> 10` ngày → `Slow sale` (bán chậm).
- Các trường hợp khác → `Not evaluated` (không đánh giá).
- VAT của một mặt hàng = số lượng tồn × đơn giá × thuế suất. Báo cáo cộng số lượng và VAT của từng loại, bao gồm cả hàng đã hết hạn.
- Mã được bỏ khoảng trắng ở hai đầu, phân biệt chữ hoa/thường và không được trùng giữa bất kỳ loại hàng nào.
- Ngày nhập theo định dạng `yyyy-MM-dd`. Dữ liệu chỉ lưu trong bộ nhớ trong phiên chạy.

## Chạy từ thư mục gốc repository

Java:

```bash
mkdir -p /tmp/inventory-java
javac -d /tmp/inventory-java 2/java/*.java
java -cp /tmp/inventory-java Main
```

Kotlin:

```bash
kotlinc 2/kotlin/*.kt -include-runtime -d /tmp/inventory-kotlin.jar
java -jar /tmp/inventory-kotlin.jar
```

Menu: `1` thêm hàng (chọn tiếp loại hàng), `2` xem danh sách và đánh giá, `3` xem tổng tồn kho và VAT, `0` thoát. Nếu một mặt hàng vi phạm điều kiện, chương trình báo lỗi và không thêm mặt hàng đó.
