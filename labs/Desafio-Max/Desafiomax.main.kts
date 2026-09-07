class Bicicleta(
    var id: Int,
    var Tipo: String?,
    var Valor: Int?,
    var Disponibilidad: Boolean = true
)

fun main() {
    var bicicleta = mutableListOf<Bicicleta>()
    var opcion: Int? = null

    while (true) {
        println("=== Menu Bicicleta ===")
        println("1. Registrar Bicicleta.")
        println("2. Arrendar Bicicleta.")
        println("3. Calcular Costo Arriendo.")
        println("4. Devolver Bicicleta.")
        println("5. Ver Disponibilidad.")
        println("6. Buscar por ID.")
        println("0. Salir.")

        opcion = readlnOrNull()?.toIntOrNull()

        when (opcion) {
            1 -> {
                Registrar(bicicleta)
            }

            2 -> {
                arrendarBicicletas(bicicleta)
            }

            3 -> {
                calculo(bicicleta)
            }

            4 -> {
                devolverBicicletas(bicicleta)
            }

            5 -> {
                mostrarDisponible(bicicleta)
            }

            6 -> {
                buscarID(bicicleta)
            }

            0 -> {
                println("Saliendo...")
                break
            }

            else -> println("Opción inválida")
        }
    }
}

//Opcion 1
fun Registrar(bicicleta: MutableList<Bicicleta>) {
    println("Ingrese ID: ")
    val id = readlnOrNull()?.toIntOrNull()

    println("Ingrese Tipo Bicicleta")
    val tipo = readlnOrNull().toString()

    println("Ingrese Precio*Hora Bicicleta")
    val precio = readlnOrNull()?.toIntOrNull()

    if (id != null && tipo.isNotBlank() && precio != null) {
        bicicleta.add(Bicicleta(id, tipo, precio))
        println("Bicicleta Ingresada con Id: $id Tipo: $tipo y Precio: $precio")
    }
}

//Opcion 2
fun arrendarBicicletas(bicicletas: MutableList<Bicicleta>) {
    println("==== Lista de Bicicletas ====")
    bicicletas.forEach { bici ->
        val estado = if (bici.Disponibilidad) "Disponible" else "No Disponible"
        println("Bicicleta ${bici.id}:, Tipo${bici.Tipo}: ,Valor${bici.Valor}: ,Estado${estado}")
    }
    println("Elija ID Bicicleta: ")
    val seleccion = readlnOrNull()?.toIntOrNull()

    if (seleccion != null) {
        val bici = bicicletas.find{ it.id == seleccion }

        if(bici != null && bici.Disponibilidad){
            println("Bicicleta Arrendada ID${bici.id} , Tipo${bici.Tipo}: ,Valor${bici.Valor}: ")
        }else println("Bicicleta no Disponible")

    }
}

//Opcion 3
fun calculo(bicicletas: MutableList<Bicicleta>) {
    println("==== Calculo Costo Arriendo ====")
    println("Ingrese Valor Bicicleta")
    val costo = readlnOrNull()?.toIntOrNull()

    println("Ingrese Cantidad Horas")
    val horas = readlnOrNull()?.toIntOrNull()

    if (costo == null || horas == null) {
        println("El costo debe ser mayor a 0")
    } else if (costo <= 0) {
        println("El costo debe ser mayor a 0")
    } else {
        val calculo = costo * horas
        println("El costo es: $calculo")
    }
}

//Opcion 4
fun devolverBicicletas(bicicletas: MutableList<Bicicleta>) {
    println("==== Lista de Bicicletas ====")
    println("Ingrese ID Bicicleta: ")
    val seleccion = readlnOrNull()?.toIntOrNull()

    if (seleccion != null) {
        val bici = bicicletas.find{ it.id == seleccion }

        if(bici != null && bici.Disponibilidad){
            bici.Disponibilidad = true
            println("Bicicleta Devuelta ID${bici.id} , Tipo${bici.Tipo}: ,Valor${bici.Valor}: ")
        }else println("Bicicleta no encontrada o ya estaba disponible")

    }else println("Bicicleta no encontrada")
}

//Opcion 5
fun mostrarDisponible(bicicletas: MutableList<Bicicleta>) {
    println("=== Lista Disponibilidad ===")
    bicicletas.forEach { bici ->
        if (bici.Disponibilidad) {
            println("Bicicleta con ID: ${bici.id}, Tipo: ${bici.Tipo}, Valor: ${bici.Valor}")
        } else {
            println("Bicicleta ${bici.id} (${bici.Tipo}) NO está disponible")
        }
    }
}

//Opcion 6
fun buscarID(bicicletas: MutableList<Bicicleta>) {
    println("==== Buscar por ID ====")
    val seleccion = readlnOrNull()?.toIntOrNull()

    if (seleccion != null) {
        val bici = bicicletas.find { it.id == seleccion }

        if (bici != null) {
            if (bici.Disponibilidad) {
                println("Bicicleta encontrada: ID=${bici.id}, Tipo=${bici.Tipo}, Valor=${bici.Valor} (Disponible)")
            } else {
                println("Bicicleta encontrada: ID=${bici.id}, Tipo=${bici.Tipo}, Valor=${bici.Valor} (No Disponible)")
            }
        } else {
            println("ID Inválido.")
        }
    } else {
        println("ID Incorrecto")
    }
}
