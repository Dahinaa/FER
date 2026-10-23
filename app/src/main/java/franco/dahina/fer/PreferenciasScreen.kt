package franco.dahina.fer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale

private val azulFER = Color(0xFF172958)
private val grisSeleccion = Color(0xFFD5D5D5)
private val azulClaro = Color(0xFF9FD5E8)

@Composable
fun PreferenciasScreen(
    onContinuar: (String) -> Unit = {}
) {
    var seleccion by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 35.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "Bienvenido",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = azulFER
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Selecciona quién eres",
            fontSize = 15.sp,
            color = azulFER
        )

        Spacer(
            modifier = Modifier
                .width(32.dp)
                .height(3.dp)
                .background(azulClaro)
        )

        Spacer(modifier = Modifier.height(150.dp))

        OpcionFER(
            titulo = "Becario",
            descripcion = "Accede a avisos, convocatorias y beneficios exclusivos para becarios FER.",
            icono = R.drawable.graduation,
            seleccionado = seleccion == "Becario",
            onClick = { seleccion = "Becario" }
        )

        Spacer(modifier = Modifier.height(25.dp))

        OpcionFER(
            titulo = "Aspirante",
            descripcion = "Convocatorias y avisos para futuros becarios.",
            icono = R.drawable.binoculares,
            seleccionado = seleccion == "Aspirante",
            onClick = { seleccion = "Aspirante" }
        )

        Spacer(modifier = Modifier.height(65.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = azulFER,
                onClick = {
                    if (seleccion.isNotEmpty()) {
                        onContinuar(seleccion)
                    }
                }
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "→",
                        fontSize = 23.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun OpcionFER(
    titulo: String,
    descripcion: String,
    icono: Int,
    seleccionado: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 68.dp)
            .background(
                color = if (seleccionado)
                    grisSeleccion else Color.White,
                shape = RoundedCornerShape(17.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    azulFER,
                    RoundedCornerShape(9.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = icono),
                contentDescription = titulo,
                modifier = Modifier.size(30.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = titulo,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = azulFER
            )

            Text(
                text = descripcion,
                fontSize = 8.sp,
                lineHeight = 11.sp,
                color = azulFER
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        RadioButton(
            selected = seleccionado,
            onClick = onClick,
            modifier = Modifier.size(32.dp),
            colors = RadioButtonDefaults.colors(
                selectedColor = azulFER,
                unselectedColor = azulFER
            )
        )
    }
}
