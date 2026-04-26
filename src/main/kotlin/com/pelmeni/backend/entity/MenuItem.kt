package com.pelmeni.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "menu_items")
class MenuItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var price: Int = 0,

    @Column(length = 1000)
    var description: String = ""

)