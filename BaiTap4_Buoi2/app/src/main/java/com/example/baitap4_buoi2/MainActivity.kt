
package com.example.baitap4_buoi2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    MayTinhScreen()
                }
            }
        }
    }
}

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun MayTinhScreen() {
    var soThuNhat by remember { mutableStateOf("4") }
    var soThuHai by remember { mutableStateOf("4") }

    var ketQua by remember { mutableStateOf<String?>(null) }
    var phepToanDuocChon by remember { mutableStateOf("+") }
    var thongBaoLoi by remember { mutableStateOf("") }

    // Hàm tính toán
    fun tinhToan(phepToan: String) {
        val a = soThuNhat.trim().toDoubleOrNull()
        val b = soThuHai.trim().toDoubleOrNull()

        phepToanDuocChon = phepToan

        if (a == null || b == null) {
            ketQua = null
            thongBaoLoi = "Vui lòng nhập hai số hợp lệ."
            return
        }

        if (phepToan == "/" && b == 0.0) {
            ketQua = null
            thongBaoLoi = "Không thể chia cho 0."
            return
        }

        val ketQuaTinh = when (phepToan) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> a / b
            else -> {
                ketQua = null
                thongBaoLoi = "Phép toán không hợp lệ."
                return
            }
        }

        if (!ketQuaTinh.isFinite()) {
            ketQua = null
            thongBaoLoi = "Kết quả vượt giới hạn tính toán."
            return
        }

        ketQua = ketQuaTinh.toString()
        thongBaoLoi = ""
    }

    // Định dạng số nguyên không có phần .0
    fun dinhDangKetQua(value: String?): String {
        val number = value?.toDoubleOrNull() ?: return ""
        return if (number.isFinite() && number % 1.0 == 0.0) {
            number.toLong().toString()
        } else {
            number.toString()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Thực hành 03",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color.White
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(70.dp))

            Text(
                text = "Thực hành 03",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(64.dp))

            // Ô nhập số thứ nhất
            OutlinedTextField(
                value = soThuNhat,
                onValueChange = {
                    soThuNhat = it
                    ketQua = null
                    thongBaoLoi = ""
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                placeholder = {
                    Text("Nhập số thứ nhất")
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Bốn nút có chiều rộng bằng nhau
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NutPhepToan(
                    kyHieu = "+",
                    mauNen = Color(0xFFD94F45),
                    dangChon = phepToanDuocChon == "+",
                    modifier = Modifier.weight(1f),
                    onClick = { tinhToan("+") }
                )

                NutPhepToan(
                    kyHieu = "-",
                    mauNen = Color(0xFFE2B24F),
                    dangChon = phepToanDuocChon == "-",
                    modifier = Modifier.weight(1f),
                    onClick = { tinhToan("-") }
                )

                NutPhepToan(
                    kyHieu = "*",
                    mauNen = Color(0xFF5939D9),
                    dangChon = phepToanDuocChon == "*",
                    modifier = Modifier.weight(1f),
                    onClick = { tinhToan("*") }
                )

                NutPhepToan(
                    kyHieu = "/",
                    mauNen = Color(0xFF202020),
                    dangChon = phepToanDuocChon == "/",
                    modifier = Modifier.weight(1f),
                    onClick = { tinhToan("/") }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Ô nhập số thứ hai
            OutlinedTextField(
                value = soThuHai,
                onValueChange = {
                    soThuHai = it
                    ketQua = null
                    thongBaoLoi = ""
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                placeholder = {
                    Text("Nhập số thứ hai")
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Kết quả
            Text(
                text = if (ketQua != null) {
                    "Kết quả: ${dinhDangKetQua(ketQua)}"
                } else {
                    "Kết quả:"
                },
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                color = Color.Black
            )

            if (thongBaoLoi.isNotEmpty()) {
                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = thongBaoLoi,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFFD32F2F),
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
fun NutPhepToan(
    kyHieu: String,
    mauNen: Color,
    dangChon: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(48.dp)
            .then(
                if (dangChon) {
                    Modifier.border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    )
                } else {
                    Modifier
                }
            ),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = mauNen,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = kyHieu,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
