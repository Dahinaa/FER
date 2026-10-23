package franco.dahina.fer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap


private val azulFER = Color(0xFF172958)
private val grisSeleccion = Color(0xFFD5D5D5)
private val azulClaro = Color(0xFF9FD5E8)

@Composable
fun NivelEducativoScreen(
    onRegresar: () -> Unit = {},
    onContinuar: (String) -> Unit = {}
) {
    var nivelSeleccionado by remember {
        mutableStateOf("")
    }

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

        Spacer(modifier = Modifier.height(110.dp))

        OpcionNivel(
            titulo = "Preparatoria",
            descripcion = "Accede a avisos, convocatorias y beneficios educativos para becarios FER.",
            icono = R.drawable.backpack,
            seleccionado = nivelSeleccionado == "Preparatoria",
            onClick = { nivelSeleccionado = "Preparatoria" }
        )

        Spacer(modifier = Modifier.height(22.dp))

        OpcionNivel(
            titulo = "Universidad",
            descripcion = "Convocatorias y avisos para futuros becarios.",
            icono = R.drawable.education,
            seleccionado = nivelSeleccionado == "Universidad",
            onClick = { nivelSeleccionado = "Universidad" }
        )

        Spacer(modifier = Modifier.height(22.dp))

        OpcionNivel(
            titulo = "Maestría",
            descripcion = "Convocatorias y avisos para futuros becarios.",
            icono = R.drawable.airplane,
            seleccionado = nivelSeleccionado == "Maestría",
            onClick = { nivelSeleccionado = "Maestría" }
        )

        Spacer(modifier = Modifier.height(45.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            BotonNivel("←") {
                onRegresar()
            }

            BotonNivel("→") {
                if (nivelSeleccionado.isNotEmpty()) {
                    onContinuar(nivelSeleccionado)
                }
            }
        }
    }
}

@Composable
private fun OpcionNivel(
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
                if (seleccionado)
                    grisSeleccion else Color.White,
                RoundedCornerShape(17.dp)
            )
            .clickable(onClick = onClick)
            .padding(12.dp),
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

        RadioButton(
            selected = seleccionado,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = azulFER,
                unselectedColor = azulFER
            )
        )
    }
}


@Composable
private fun BotonNivel(
    simbolo: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.size(48.dp),
        shape = CircleShape,
        color = azulFER,
        onClick = onClick
    ) {
        Box(contentAlignment = Alignment.Center) {
            Canvas(modifier = Modifier.size(26.dp)) {
                val izquierda = simbolo == "←"
                val centroY = size.height / 2f

                val puntaX = if (izquierda)
                    size.width * 0.2f
                else
                    size.width * 0.8f

                val direccion = if (izquierda) 1f else -1f
                val grosor = 2.5.dp.toPx()
                val largo = size.width * 0.28f

                drawLine(
                    color = Color.White,
                    start = Offset(size.width * 0.2f, centroY),
                    end = Offset(size.width * 0.8f, centroY),
                    strokeWidth = grosor,
                    cap = StrokeCap.Round
                )

                drawLine(
                    color = Color.White,
                    start = Offset(
                        puntaX + direccion * largo,
                        size.height * 0.25f
                    ),
                    end = Offset(puntaX, centroY),
                    strokeWidth = grosor,
                    cap = StrokeCap.Round
                )

                drawLine(
                    color = Color.White,
                    start = Offset(puntaX, centroY),
                    end = Offset(
                        puntaX + direccion * largo,
                        size.height * 0.75f
                    ),
                    strokeWidth = grosor,
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

