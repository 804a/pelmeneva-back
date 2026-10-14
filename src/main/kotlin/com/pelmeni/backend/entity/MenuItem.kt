package com.pelmeni.backend.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*

@Entity
@Table(name = "menu_items")
@Schema(description = "Сущность блюда в меню")
class MenuItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор блюда", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    var id: Long? = null,

    @Column(nullable = false)
    @Schema(description = "Название блюда", example = "Пельмени Сибирские", required = true)
    var name: String = "",

    @Column(nullable = false)
    @Schema(description = "Цена в рублях", example = "350", required = true)
    var price: Int = 0,

    @Column(length = 1000)
    @Schema(description = "Подробное описание блюда", example = "Домашние пельмени из говядины и свинины", required = false)
    var description: String = ""
)