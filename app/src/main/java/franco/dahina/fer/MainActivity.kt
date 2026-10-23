
package franco.dahina.fer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.material3.MaterialTheme
import franco.dahina.fer.ui.theme.FERTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            FERTheme {
                MaterialTheme {

                    var pantallaActual by remember {
                        mutableStateOf(1)
                    }

                    var tipoUsuario by remember {
                        mutableStateOf("")
                    }

                    var nivelEducativo by remember {
                        mutableStateOf("")
                    }

                    when (pantallaActual) {

                        1 -> {
                            PreferenciasScreen(
                                onContinuar = { seleccion ->
                                    tipoUsuario = seleccion
                                    pantallaActual = 2
                                }
                            )
                        }

                        2 -> {
                            NivelEducativoScreen(
                                onRegresar = {
                                    pantallaActual = 1
                                },
                                onContinuar = { nivel ->
                                    nivelEducativo = nivel
                                    pantallaActual = 3
                                }
                            )
                        }

                        3 -> {
                            ResumenPreferencias(
                                tipoUsuario = tipoUsuario,
                                nivelEducativo = nivelEducativo,
                                onRegresar = {
                                    pantallaActual = 2
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
