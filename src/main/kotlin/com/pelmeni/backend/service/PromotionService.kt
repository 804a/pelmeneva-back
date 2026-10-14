package com.pelmeni.backend.service

import com.pelmeni.backend.entity.Promotion
import com.pelmeni.backend.repository.PromotionRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class PromotionService(private val repository: PromotionRepository) {

    fun getActivePromotions(): List<Promotion> {
        return repository.findActivePromotions(LocalDate.now())
    }

    fun createPromotion(promotion: Promotion): Promotion {
        return repository.save(promotion)
    }

    fun updatePromotion(id: Long, promotion: Promotion): Promotion {
        val existing = repository.findById(id).orElseThrow {
            RuntimeException("Акция с id=$id не найдена")
        }
        existing.title = promotion.title
        existing.description = promotion.description
        existing.imageUrl = promotion.imageUrl
        existing.startDate = promotion.startDate
        existing.endDate = promotion.endDate
        existing.isActive = promotion.isActive
        return repository.save(existing)
    }

    fun deletePromotion(id: Long) {
        if (!repository.existsById(id)) {
            throw RuntimeException("Акция с id=$id не найдена")
        }
        repository.deleteById(id)
    }
}