//Checkpoint 1
fun main() {
    val list = listOf<Int>(18, 22, 25, 17, 29, 31, 20);

    for(grado in list){
        println("La Temperatura es $grado °C -> ${GradoClima(grado)}")
    }
    println("Hay ${Contador(list)} Temperaturas mas grandes que 25")
}

//Checkpoint 2
fun GradoClima(Temperatura: Int): String {
    if (Temperatura < 18)
        return "Frio"
    if (Temperatura in 18 .. 24)
        return "Templado"
    else
        return "Caluroso"

}

//Checkpoint 3
fun Contador (Temperaturas: List<Int>): Int {
    var Contador = 0
    for (grado in Temperaturas) {
        if (grado >= 25)
            Contador ++
    }
    return Contador
}

//Checkpoint 4
fun mutable() {
    val list = mutableListOf<Int>(18, 22, 25, 17, 29, 31, 20);

    list.add(26)

    println(list)
}