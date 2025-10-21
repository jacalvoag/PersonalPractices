package com.example.navigation.data

class Student {

    data class Student(
        val id: Long,
        val name: String,
        val descrption: String
    )

    var students = listOf<Student>(
        Student(1L, "Emilia", "Es la jefa de grupo, organizada y exigente"),
        Student(2L, "Nadia", "Es la mejor amiga de Emilia y siempre la apoya"),
        Student(3L, "Luis", "Es el amigo de Nadia, aunque a veces discuten"),
        Student(4L, "Antonio", "Es el compañero que suele seguir a Luis en todo"),
        Student(5L, "Ángel", "Es el amigo de Antonio, el más tranquilo del grupo"),
        Student(6L, "Emma", "Es la nueva integrante que intenta integrarse con todos"),
    )

}