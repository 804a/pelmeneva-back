package com.pelmeni.backend.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "promotions")
class Promotion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
// Название акции
    @Column(nullable = false)
    var title: String = "",
// Подробное описание акции
    @Column(length = 1000)
    var description: String = "",
// Ссылка на картинку для баннера акции
    var imageUrl: String = "",
// Дата начала акции. По умолчанию — сегодня
    @Column(nullable = false)
    var startDate: LocalDate = LocalDate.now(),
// Дата окончания. По умолчанию — через 30 дней
    @Column(nullable = false)
    var endDate: LocalDate = LocalDate.now().plusDays(30),
// Флаг: активна ли акция
    var isActive: Boolean = true
)