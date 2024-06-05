package arnav.unitconverter
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.compose.ui.graphics.Color
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> UnitConverter() // Call UnitConverter instead of Greeting
                }
            }
        }
    }
}
@Preview
@Composable
fun UnitConverter(){
    var inputvalue by remember{ mutableStateOf(value="") }
    var outputvalue by remember{ mutableStateOf(value="") }
    var inputvalueunit by remember{ mutableStateOf(value="Meters") }
    var outputvalueunit by remember{ mutableStateOf(value="Meters") }
    var iExpanded by remember{ mutableStateOf(value = false) }
    var oExpanded by remember{ mutableStateOf(value = false) }
    val conversionfactor= remember { mutableStateOf(value = 1.0) }
    val oconversionfactor= remember { mutableStateOf(value = 1.0) }

    fun ConvertUnits(){
        //elvis opreator-?:
       val inputvalueDouble=inputvalue.toDoubleOrNull()?:0.0
        val result=(inputvalueDouble*conversionfactor.value*100.0/oconversionfactor.value).roundToInt() / 100.0
        outputvalue=result.toString()
    }
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement= Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){//UI elements stacked one above the other

        Text(text = "Unit Converter",style=MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))
     OutlinedTextField(value = inputvalue, onValueChange ={inputvalue=it
         ConvertUnits()//when outlined txt field changes this should also change} )
    }   , label = {Text("Enter value")})
        Spacer(modifier = Modifier.height(20.dp))
    Row { //UI elements will be stacked besides eachother
      Box{
Button(onClick = {iExpanded=true}) {
    Text(text = "input_Unit")
    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down" )

}
          DropdownMenu(expanded =iExpanded, onDismissRequest = { iExpanded=false }) {
              DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                  iExpanded=false
              inputvalueunit="Centimeters"
              conversionfactor.value=0.01
              ConvertUnits()})
              Spacer(modifier = Modifier.height(5.dp))
              DropdownMenuItem(text = { Text("Meters") }, onClick = {  iExpanded=false
                  inputvalueunit="Meters"
                  conversionfactor.value=1.0
                  ConvertUnits()})
              Spacer(modifier = Modifier.height(5.dp))
              DropdownMenuItem(text = { Text("Feet") }, onClick = {  iExpanded=false
                  inputvalueunit="Feet"
                  conversionfactor.value=0.3048
                  ConvertUnits() })
              Spacer(modifier = Modifier.height(5.dp))
              DropdownMenuItem(text = { Text("Millimeters") }, onClick = {  iExpanded=false
                  inputvalueunit="Millimeters"
                  conversionfactor.value=0.001
                  ConvertUnits()})

          }
      }
        Spacer(modifier = Modifier.width(20.dp))
        Box {
            Button(onClick = { oExpanded=true }) {
                Text(text = "Output_Unit")
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down" )

            }
            DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                    oExpanded=false
                    outputvalueunit="Centimeters"
                    oconversionfactor.value=0.01
                    ConvertUnits()

                })
                Spacer(modifier = Modifier.height(5.dp))
                DropdownMenuItem(text = { Text("Meters") }, onClick = {
                    oExpanded=false
                    outputvalueunit="Meters"
                    oconversionfactor.value=1.00
                    ConvertUnits()
                })
                Spacer(modifier = Modifier.height(5.dp))
                DropdownMenuItem(text = { Text("Feet") }, onClick = {
                    oExpanded=false
                    outputvalueunit="Feet"
                    oconversionfactor.value=0.3048
                    ConvertUnits()
                })
                Spacer(modifier = Modifier.height(5.dp))
                DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                    oExpanded=false
                    outputvalueunit="Milliimeters"
                    oconversionfactor.value=0.001
                    ConvertUnits()
                })

            }
        }

    }
        Spacer(modifier = Modifier.height(20.dp))
    Text(text = "RESULT :- $outputvalue $outputvalueunit",
        style=MaterialTheme.typography.headlineMedium)
    }
}



