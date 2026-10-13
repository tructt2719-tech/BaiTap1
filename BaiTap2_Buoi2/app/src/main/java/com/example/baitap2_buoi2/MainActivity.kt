
package com.example.baitap2_buoi2
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Hàm phân loại điểm học lực
fun xepLoaiHocLuc(diem: Double): String {
    return if (diem < 0 || diem > 10) {
        "Không hợp lệ"
    } else if (diem >= 8.0) {
        "Giỏi"
    } else if (diem >= 6.5) {
        "Khá"
    } else if (diem >= 5.0) {
        "Trung bình"
    } else {
        "Yếu"
    }
}

// Màu sắc tương ứng với từng mức xếp loại
fun mauXepLoai(xepLoai: String): Color {
    return if (xepLoai == "Giỏi") {
        Color(0xFFE5F3E8)
    } else if (xepLoai == "Khá") {
        Color(0xFFE3EDF9)
    } else if (xepLoai == "Trung bình") {
        Color(0xFFFFF0D8)
    } else if (xepLoai == "Yếu") {
        Color(0xFFF9E5E2)
    } else {
        Color(0xFFECEEF2)
    }
}

fun mauChuXepLoai(xepLoai: String): Color {
    return if (xepLoai == "Giỏi") {
        Color(0xFF397749)
    } else if (xepLoai == "Khá") {
        Color(0xFF3769A7)
    } else if (xepLoai == "Trung bình") {
        Color(0xFF9A6A24)
    } else if (xepLoai == "Yếu") {
        Color(0xFFAD5549)
    } else {
        Color(0xFF737986)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF4F3F8)
                ) {
                    ManHinhXepLoai()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManHinhXepLoai() {
    // Điểm chính theo đề bài
    val diem = 8.5
    val xepLoai = xepLoaiHocLuc(diem)

    // Các điểm dùng để kiểm tra thêm
    val danhSachDiem = listOf(9.0, 7.0, 5.5, 3.0, 11.0)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Xếp loại học lực",
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF304978)
                )
            )
        },
        containerColor = Color(0xFFF4F3F8)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 14.dp, vertical = 12.dp)
        ) {
            // Thẻ hiển thị điểm chính
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 1.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Điểm trung bình",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = diem.toString(),
                        fontSize = 44.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF202631)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Màu xếp loại được chọn bằng if
                    Text(
                        text = xepLoai,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = mauXepLoai(xepLoai),
                                shape = RoundedCornerShape(9.dp)
                            )
                            .padding(vertical = 10.dp),
                        color = mauChuXepLoai(xepLoai),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "KIỂM TRA THÊM",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF707782)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Duyệt danh sách điểm và hiển thị kết quả
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(13.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 1.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    for (i in danhSachDiem.indices) {
                        val diemKiemTra = danhSachDiem[i]
                        val loaiKiemTra = xepLoaiHocLuc(diemKiemTra)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Điểm ${diemKiemTra.toString().removeSuffix(".0")}",
                                fontSize = 13.sp,
                                color = Color(0xFF30343B)
                            )

                            Text(
                                text = loaiKiemTra,
                                modifier = Modifier
                                    .background(
                                        color = mauXepLoai(loaiKiemTra),
                                        shape = RoundedCornerShape(5.dp)
                                    )
                                    .padding(
                                        horizontal = 8.dp,
                                        vertical = 4.dp
                                    ),
                                color = mauChuXepLoai(loaiKiemTra),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        if (i < danhSachDiem.lastIndex) {
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color(0xFFEEEEF1))
                            )
                        }
                    }
                }
            }
        }
    }
}
