package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun ActionButtonWithFeedback(
    label: String,
    onAction: suspend (onSuccess: (String) -> Unit, onError: (String) -> Unit) -> Unit,
    onSuccess: () -> Unit = {},
    modifier: Modifier = Modifier,
    height: Dp = 50.dp,
    width: Dp = Dp.Unspecified,
    textSize: TextUnit = MaterialTheme.typography.bodyLarge.fontSize,
    containerColor: Color = colorScheme.error,
    shape: RoundedCornerShape = RoundedCornerShape(50)
) {
    var isLoading by remember { mutableStateOf(false) }
    var successMessage by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .then(if (width != Dp.Unspecified) Modifier.width(width) else Modifier)
        ),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                isLoading = true
                successMessage = null
                errorMessage = null

                scope.launch {
                    onAction(
                        { message ->
                            successMessage = message
                            isLoading = false
                            onSuccess()
                        },
                        { error ->
                            errorMessage = error
                            isLoading = false
                        }
                    )
                }
            },
            enabled = !isLoading,
            modifier = Modifier
                .padding(8.dp)
                .height(height)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = containerColor),
            shape = shape
        ) {
            if (isLoading) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CircularProgressIndicator(
                        color = colorScheme.onPrimary,
                        strokeWidth = 2.dp,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 8.dp)
                    )
                    Text("Đang xử lý...", fontSize = textSize)
                }
            } else {
                Text(label, fontSize = textSize)
            }
        }

        // Dialog thành công
        successMessage?.let {
            AlertDialog(
                onDismissRequest = { successMessage = null },
                title = { Text("🎉 Thành công", fontWeight = FontWeight.Bold) },
                text = { Text(it) },
                confirmButton = {
                    TextButton(onClick = {
                        successMessage = null
                        onSuccess()
                    }) {
                        Text("OK")
                    }
                }
            )
        }

        // Dialog lỗi
        errorMessage?.let {
            AlertDialog(
                onDismissRequest = { errorMessage = null },
                title = { Text("❌ Lỗi", fontWeight = FontWeight.Bold) },
                text = { Text(it) },
                confirmButton = {
                    TextButton(onClick = { errorMessage = null }) {
                        Text("Đóng")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActionButtonWithFeedbackPreview() {
    MaterialTheme {
        ActionButtonWithFeedback(
            label = "Xác nhận",
            onAction = { onSuccess, _ ->
                kotlinx.coroutines.GlobalScope.launch {
                    kotlinx.coroutines.delay(1000)
                    onSuccess("Thành công!")
                }
            },
            height = 60.dp,
            width = 300.dp,
            textSize = 18.sp,
            containerColor = colorScheme.primary
        )
    }
}
