package franco.dahina.fer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResumenPreferencias(
    tipoUsuario: String,
    nivelEducativo: String,
    onRegresar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Preferencias seleccionadas",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Tipo de usuario: $tipoUsuario")

        Spacer(modifier = Modifier.height(12.dp))

        Text("Nivel educativo: $nivelEducativo")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onRegresar) {
            Text("Regresar")
        }
    }
}
