package com.pelmeni.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "establishment_info")
class EstablishmentInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var phone: String = "",

    @Column(nullable = false)
    var address: String = "",

    @Column(nullable = false)
    var workingHours: String = "",

    @Column(length = 1000)
    var description: String = "",

    @Column(length = 500)
    var socialVk: String = "",

    @Column(length = 500)
    var socialTelegram: String = ""
)