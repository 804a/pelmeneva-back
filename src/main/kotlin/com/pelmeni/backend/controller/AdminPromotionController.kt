package com.pelmeni.backend.controller

import com.pelmeni.backend.entity.Promotion
import com.pelmeni.backend.service.PromotionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/admin/promotions")
@Tag(name = "Администрирование акций", description = "Управление акциями (только для админа)")
class AdminPromotionController(private val promotionService: PromotionService) {

    @PostMapping
    @Operation(summary = "Создать акцию")
    fun create(@RequestBody promotion: Promotion): Promotion {
        return promotionService.createPromotion(promotion)
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить акцию")
    fun update(@PathVariable id: Long, @RequestBody promotion: Promotion): Promotion {
        return promotionService.updatePromotion(id, promotion)
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить акцию")
    fun delete(@PathVariable id: Long) {
        promotionService.deletePromotion(id)
    }
}