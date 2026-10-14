package com.pelmeni.backend.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*

@Entity
@Table(name = "establishment_info")
@Schema(description = "Контактная информация заведения")
class EstablishmentInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор", accessMode = Schema.AccessMode.READ_ONLY)
    var id: Long? = null,

    @Column(nullable = false)
    @Schema(description = "Название заведения", example = "Пельмень Евы", required = true)
    var name: String = "",

    @Column(nullable = false)
    @Schema(description = "Номер телефона", example = "8-800-200-80-42", required = true)
    var phone: String = "",

    @Column(nullable = false)
    @Schema(description = "Адрес заведения", example = "г. Омск, ул. Ленина, д. 42", required = true)
    var address: String = "",

    @Column(nullable = false)
    @Schema(description = "Часы работы", example = "ежедневно с 11:00 до 23:00", required = true)
    var workingHours: String = "",

    @Column(length = 1000)
    @Schema(description = "Описание заведения", example = "Домашние пельмени с доставкой за 40 минут")
    var description: String = "",

    @Column(length = 500)
    @Schema(description = "Ссылка на страницу VK", example = "https://vk.com/pelmeni_eva")
    var socialVk: String = "",

    @Column(length = 500)
    @Schema(description = "Ссылка на Telegram", example = "https://t.me/pelmeni_eva")
    var socialTelegram: String = ""
)