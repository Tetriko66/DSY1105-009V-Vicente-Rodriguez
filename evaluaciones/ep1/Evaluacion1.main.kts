enum class tipoDueno{Convenio, Particular, Municipal}

class Paciente(
    val codigo: String,
    val nombre: String,
    val especie: String,
    val tipo: String,
    val dueno: String,
    val silvestre: Boolean = false
) {
    fun calcularTarifa(minutos: Int): Double{
        val horas = minutos / 60
        var tarifa: Double = 0.0

        if(especie == "Canino"){
            tarifa = horas * 1200.0
        } else if (especie == "Felino" && horas <= 60){
            tarifa = horas * 9000.0
        }else if(especie == "Exotica" ){
            tarifa = horas * 20000.0
                if (especie == "Silvestre"){
                    tarifa *= 1.3
                }
        }
    return tarifa}
}

fun DisponibleBox(){

}

fun main() {
    val especie: MutableList<Paciente>
    var opcion: Int? = null

    p1 = 
}


