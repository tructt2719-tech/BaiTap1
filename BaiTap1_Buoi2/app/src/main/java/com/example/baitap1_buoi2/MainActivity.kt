
package com.example.baitap1_buoi2
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. Khai báo class Student
data class Student(
    val name: String,
    val email: String?,
    val phone: String?
)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF3F4F8)
                ) {
                    StudentScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentScreen() {

    // 2. Tạo danh sách 5 sinh viên
    val students = listOf(
        Student("Nguyễn Văn An", "an@sv.edu.vn", "0901 234 567"),
        Student("Trần Thị Bình", null, "0912 888 999"),
        Student("Lê Minh Chi", "chi@sv.edu.vn", null),
        Student("Phạm Quốc Dũng", null, null),
        Student("Võ Thanh Em", "em@sv.edu.vn", "0933 111 222")
    )

    // 3. Dùng vòng lặp for đếm sinh viên thiếu email
    var missingEmail = 0

    for (student in students) {
        if (student.email == null) {
            missingEmail++
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Danh sách sinh viên",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF254A7C)
                )
            )
        },
        containerColor = Color(0xFFF3F4F8)
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(12.dp)
        ) {

            // 4. Dùng for tạo các thẻ sinh viên
            for (student in students) {
                StudentCard(student)
                Spacer(modifier = Modifier.height(8.dp))
            }

            // 5. Thống kê cuối danh sách
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFE5ECF8),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Tổng: ${students.size} sinh viên",
                    fontSize = 12.sp,
                    color = Color(0xFF254A7C),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Thiếu email: $missingEmail",
                    fontSize = 12.sp,
                    color = Color(0xFF254A7C),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun StudentCard(student: Student) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = student.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF202735)
            )

            Spacer(modifier = Modifier.height(5.dp))

            // Toán tử Elvis ?: tương đương ý nghĩa với ?? trong Dart
            Text(
                text = "Email: ${student.email ?: "Chưa cập nhật"}",
                fontSize = 12.sp,
                color = if (student.email == null)
                    Color(0xFF99634A)
                else
                    Color.Gray
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "SDT: ${student.phone ?: "Chưa cập nhật"}",
                fontSize = 12.sp,
                color = if (student.phone == null)
                    Color(0xFF99634A)
                else
                    Color.Gray
            )
        }
    }
}
