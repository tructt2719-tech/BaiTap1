
package com.example.baitap3_buoi2

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

// 1. Khai báo class Student
data class Student(
    val name: String,
    val score: Double?
)

// 2. Hàm xếp loại học lực, sử dụng lại logic của Bài 2
fun xepLoaiHocLuc(score: Double): String {
    return if (score < 0 || score > 10) {
        "Không hợp lệ"
    } else if (score >= 8.0) {
        "Giỏi"
    } else if (score >= 6.5) {
        "Khá"
    } else if (score >= 5.0) {
        "Trung bình"
    } else {
        "Yếu"
    }
}

// Màu nền theo xếp loại
fun mauNen(xepLoai: String): Color {
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

// Màu chữ theo xếp loại
fun mauChu(xepLoai: String): Color {
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
                    BangDiemScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BangDiemScreen() {
    // 3. Dữ liệu mẫu gồm 5 sinh viên
    val students = listOf(
        Student("Nguyễn Văn An", 9.0),
        Student("Trần Thị Bình", 7.2),
        Student("Lê Minh Chi", null),
        Student("Phạm Quốc Dũng", 4.5),
        Student("Võ Thanh Em", 5.8)
    )

    // 4. Tính thống kê bằng vòng lặp for và if
    var totalScore = 0.0
    var studentWithScore = 0
    var excellentCount = 0
    var missingScoreCount = 0
    var highestScore = 0.0

    for (student in students) {
        if (student.score == null) {
            missingScoreCount++
        } else {
            val score = student.score

            totalScore += score
            studentWithScore++

            if (score >= 8.0 && score <= 10.0) {
                excellentCount++
            }

            if (score > highestScore) {
                highestScore = score
            }
        }
    }

    val averageScore = if (studentWithScore > 0) {
        totalScore / studentWithScore
    } else {
        0.0
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Bảng điểm lớp",
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
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            // 5. Thẻ thống kê tổng quan
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF304978)
                )
            ) {
                Column(
                    modifier = Modifier.padding(14.dp)
                ) {
                    Text(
                        text = "Điểm trung bình lớp",
                        fontSize = 11.sp,
                        color = Color(0xFFD7E0F0)
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = String.format(
                            java.util.Locale.US,
                            "%.2f",
                            averageScore
                        ),
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color(0xFF536A91))
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ThongKeItem(
                            title = "Giỏi",
                            value = excellentCount.toString()
                        )

                        ThongKeItem(
                            title = "Chưa có điểm",
                            value = missingScoreCount.toString()
                        )

                        ThongKeItem(
                            title = "Cao nhất",
                            value = String.format(
                                java.util.Locale.US,
                                "%.1f",
                                highestScore
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "DANH SÁCH · ${students.size} SINH VIÊN",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF707782)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 6. Dùng for hiển thị danh sách sinh viên
            for (student in students) {
                StudentScoreCard(student)
                Spacer(modifier = Modifier.height(7.dp))
            }
        }
    }
}

@Composable
fun ThongKeItem(title: String, value: String) {
    Column(
        modifier = Modifier.padding(end = 6.dp)
    ) {
        Text(
            text = title,
            fontSize = 10.sp,
            color = Color(0xFFD7E0F0)
        )

        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun StudentScoreCard(student: Student) {
    val score = student.score

    val result = if (score == null) {
        "Chưa có điểm"
    } else {
        xepLoaiHocLuc(score)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = student.name,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF252A33)
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = result,
                    fontSize = 11.sp,
                    color = mauChu(result),
                    fontWeight = FontWeight.Medium
                )
            }

            Text(
                text = score?.let {
                    String.format(java.util.Locale.US, "%.1f", it)
                } ?: "—",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = if (score == null) {
                    Color(0xFF9CA3AF)
                } else {
                    Color(0xFF252A33)
                }
            )
        }
    }
}
