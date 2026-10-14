package com.pelmeni.backend.repository

import com.pelmeni.backend.entity.Promotion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface PromotionRepository : JpaRepository<Promotion, Long> {

    @Query("SELECT p FROM Promotion p WHERE p.isActive = true AND :today BETWEEN p.startDate AND p.endDate")
    fun findActivePromotions(@Param("today") today: LocalDate): List<Promotion>
}