package com.pelmeni.backend.controller

import com.pelmeni.backend.entity.EstablishmentInfo
import com.pelmeni.backend.service.EstablishmentInfoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/info")
@Tag(name = "Информация о заведении", description = "API для получения контактной информации (основной филиал и по ID)")
class InfoController(private val infoService: EstablishmentInfoService) {

    @GetMapping
    @Operation(
        summary = "Получить основную информацию о заведении",
        description = "Возвращает одну запись (главный филиал). Для получения других филиалов используйте /api/info/{id}."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Информация успешно получена"),
            ApiResponse(responseCode = "500", description = "Информация не найдена в базе данных")
        ]
    )
    fun getMainInfo(): EstablishmentInfo {
        return infoService.getMainInfo()
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Получить информацию о заведении по ID",
        description = "Возвращает данные конкретного филиала. Если заведение не найдено — возвращает 404."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Информация найдена"),
            ApiResponse(responseCode = "404", description = "Заведение с указанным ID не найдено")
        ]
    )
    fun getInfoById(
        @Parameter(description = "ID заведения", example = "1") @PathVariable id: Long
    ): ResponseEntity<EstablishmentInfo> {
        return try {
            ResponseEntity.ok(infoService.getInfoById(id))
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    @Operation(
        summary = "Создать новую запись о заведении",
        description = "Добавляет новый филиал. Основная информация о заведении должна быть создана отдельно."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Информация сохранена"),
            ApiResponse(responseCode = "400", description = "Неверные данные")
        ]
    )
    fun createInfo(
        @Parameter(description = "Объект с контактной информацией", required = true)
        @RequestBody info: EstablishmentInfo
    ): EstablishmentInfo {
        return infoService.createInfo(info)
    }
}