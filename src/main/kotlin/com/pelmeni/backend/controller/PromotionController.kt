package com.pelmeni.backend.controller

import com.pelmeni.backend.entity.Promotion
import com.pelmeni.backend.service.PromotionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/promotions")
@Tag(name = "Акции", description = "Просмотр акций (публичный доступ)")
class PromotionController(private val promotionService: PromotionService) {

    @GetMapping("/active")
    @Operation(summary = "Получить активные акции для главной страницы")
    fun getActivePromotions(): List<Promotion> {
        return promotionService.getActivePromotions()
    }
}