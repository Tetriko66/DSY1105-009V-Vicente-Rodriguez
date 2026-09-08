class Paciente(
    val codigo: String?,
    val nombre: String?,
    val especie: String?,
    val tipoDueno: String?,
    val silvestre: Boolean = false,
    var disponible: Boolean = false
) {
    fun calcularTarifa(minutos: Int): Double{
        val horas = minutos / 60.0
        var tarifa: Double = 0.0

        when(especie){
            "Felino" -> {
                if (horas <= 60) tarifa = horas * 9000.0
            }
            "Canino" -> {
                tarifa = horas * 12000.0
            }
            "Exotico" -> {
                tarifa = horas * 20000.0
                if(silvestre) tarifa += 1.3
            }
        }
    return tarifa}

}

fun registrarEntrada(pacientes: MutableList<Paciente>) {
    print("Código de atención (ej: CA12CD): ")
    val codigo = readLine()?.trim()?.uppercase()

    print("Nombre: ")
    val nombre = readLine()?.trim()

    print("Especie (Canino/Felino/Exotico): ")
    val especie = readLine()?.trim()

    print("Tipo de dueño (Particular/Convenio/Municipal): ")
    val dueno = readLine()?.trim()

    var silvestre = false
    if (especie == "Exotico") {
        print("¿Es silvestre? (S/N): ")
        silvestre = readLine() == "S"
    }

    val nuevoPaciente = Paciente(codigo,nombre,especie,dueno,silvestre)
    pacientes.add(nuevoPaciente)
    println("Paciente $codigo $nombre Registrado")
}

fun registrarSalida(pacientes: MutableList<Paciente>) {
    println("Ingrese el Codigo del Paciente")
    val codigo = readLine()?.trim()?.uppercase()

    val paciente = pacientes.find{it.codigo?.uppercase() == codigo}
    if(paciente == null) {
        println("Paciente No Encontrado")
    }else if(!paciente.disponible)
        println("Paciente ya en Alta")
    else{
        println("Error.")
    }

    println("Ingrese Cantidad de Minutos de Atencion: ")
    val minutos = readLine()?.trim()?.toIntOrNull()
    if(minutos == null || minutos <= 0){
        println("Minutos Invalidos.")
        return
    }

    val tarifa = paciente?.calcularTarifa(minutos)
    paciente?.disponible = false
    println("Paciente $codigo dado de Alta. Tarifa: $tarifa\n")
}

fun mostrarBoxes(disponible: MutableList<Paciente>) {
    val totalBoxes = 10
    val enAtencion = disponible.count { it.disponible }
    val boxDisponibles = totalBoxes - enAtencion
    println("Boxes disponibles $boxDisponibles")
}

fun mostrarConvenio(pacientes: MutableList<Paciente>) {
    val tiposDueno = listOf("Particular", "Convenio", "Municipal")

    for(tipo in tiposDueno){
            println("Dueño Tipo: $tipo")
            val filtrados = pacientes.filter { it.tipoDueno == tipo }
        if (filtrados.isEmpty()) {
            println("No hay pacientes con este tipo de dueño.")
        } else {
            for (p in filtrados) {
                println("Código: ${p.codigo} | Nombre: ${p.nombre}")
            }
        }
        println()
    }
}
fun mostrarIngresoPromedio(pacientes: MutableList<Paciente>) {
    val atendidos = pacientes.filter { !it.disponible } // ya dados de alta
    if (atendidos.isEmpty()) {
        println("No hay pacientes atendidos aún.")
        return
    }
    val total = atendidos.sumOf { it.calcularTarifa(60) } // aquí deberías usar los minutos reales
    val promedio = total / atendidos.size
    println("Ingreso promedio por paciente: $promedio")
}

fun mostrarCodigosFinalizados(pacientes: MutableList<Paciente>) {
    val finalizados = pacientes.filter { !it.disponible }
    if (finalizados.isEmpty()) {
        println("No hay pacientes finalizados aún.")
    } else {
        println("Códigos de pacientes finalizados:")
        finalizados.forEach { println(it.codigo) }
    }
}

fun mostrarPacienteMasTiempo(pacientes: MutableList<Paciente>) {
    // aquí simulo minutos porque tu clase aún no guarda ese dato
    val pacienteMasTiempo = pacientes.maxByOrNull { (30..180).random() }
    if (pacienteMasTiempo == null) {
        println("No hay pacientes atendidos aún.")
    } else {
        println("Paciente con más tiempo de uso: ${pacienteMasTiempo.nombre} (${pacienteMasTiempo.codigo})")
    }
}

fun cerrarTurno(pacientes: MutableList<Paciente>) {
    println("\n===== REPORTE DE CIERRE =====")

    val atendidos = pacientes.filter { !it.disponible }
    var totalRecaudado = 0.0

    atendidos.forEachIndexed { i, p ->
        val minutos = (30..180).random() // simulado
        val monto = p.calcularTarifa(minutos)
        println("Ticket ${i+1}: Código ${p.codigo} | Tipo ${p.especie} | Tiempo: $minutos min | Monto: $monto")
        totalRecaudado += monto
    }

    val ingresoPromedio = if (atendidos.isNotEmpty()) totalRecaudado / atendidos.size else 0.0
    val boxesDisponibles = 10 - pacientes.count { !it.disponible }

    println("\nTotal recaudado: $totalRecaudado")
    println("Pacientes atendidos: ${atendidos.size}")
    println("Ingreso promedio: $ingresoPromedio")
    println("Boxes disponibles al cierre: $boxesDisponibles")
    println("=============================\n")
}




fun main() {
    val paciente: MutableList<Paciente> = mutableListOf()
    var opcion: Int? = null

    paciente.add(Paciente("CC12DD","Gohan", "Felino", "Particular", false))
    paciente.add(Paciente("CA10AD","Shiro", "Felino", "Municipal", false))
    paciente.add(Paciente("AC19AA","Seven", "Canino", "Convenio", false))
    paciente.add(Paciente("BB16DD","Pepe", "Exotico", "Municipal", false))
    paciente.add(Paciente("BC11BC","Rafael", "Exotico", "Particular", true))

    do {
        println("""
            ================================
                    PETCARE - MENÚ
            ================================
            1. Registrar entrada de paciente
            2. Registrar salida de paciente
            3. Ver boxes disponibles
            4. Ver pacientes convenio (historial)
            5. Ingreso promedio por paciente
            6. Códigos de pacientes finalizados
            7. Paciente con más tiempo de uso
            8. Cerrar turno (reporte final)
            0. Salir
            ================================
            Ingrese una opción: 
        """.trimIndent())

        opcion = readLine()?.toIntOrNull()

        when (opcion) {
            1 -> registrarEntrada(paciente)
            2 -> registrarSalida(paciente)
            3 -> mostrarBoxes(paciente)
            4 -> mostrarConvenio(paciente)
            5 -> mostrarIngresoPromedio(paciente)
            6 -> mostrarCodigosFinalizados(paciente)
            7 -> mostrarPacienteMasTiempo(paciente)
            8 -> cerrarTurno(paciente)
            0 -> println("Cerrando sistema PetCare")
            else -> println("Opción inválida.")
        }

    } while (opcion != 0)
}