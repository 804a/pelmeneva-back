package com.pelmeni.backend.controller

import com.pelmeni.backend.entity.MenuItem
import com.pelmeni.backend.service.MenuService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/menu")
@Tag(name = "Меню", description = "API для работы с меню блюд")
class MenuController(private val menuService: MenuService) {

    @GetMapping
    @Operation(
        summary = "Получить список блюд с пагинацией",
        description = "Возвращает страницу блюд. Поддерживает параметры page (номер страницы, с 0), size (количество на странице) и sort (поле и направление сортировки, например sort=price,desc)."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Список блюд успешно получен"),
            ApiResponse(responseCode = "400", description = "Неверные параметры запроса")
        ]
    )
    fun getAll(
        @Parameter(hidden = true) @PageableDefault(size = 10, sort = ["name"]) pageable: Pageable
    ): Page<MenuItem> {
        return menuService.getAllMenu(pageable)
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Получить блюдо по ID",
        description = "Возвращает карточку блюда для просмотра. Если блюдо не найдено — возвращает 404."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Блюдо найдено"),
            ApiResponse(responseCode = "404", description = "Блюдо с указанным ID не найдено")
        ]
    )
    fun getOne(
        @Parameter(description = "ID блюда", example = "1") @PathVariable id: Long
    ): ResponseEntity<MenuItem> {
        return try {
            ResponseEntity.ok(menuService.getMenuItemById(id))
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить блюдо по ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Блюдо удалено"),
            ApiResponse(responseCode = "404", description = "Блюдо не найдено")
        ]
    )
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            menuService.deleteMenuItem(id)
            ResponseEntity.noContent().build()
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    @Operation(
        summary = "Добавить новое блюдо",
        description = "Создаёт новое блюдо в меню. ID генерируется автоматически."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Блюдо успешно создано"),
            ApiResponse(responseCode = "400", description = "Неверные данные (например, пустое имя или цена)")
        ]
    )
    fun create(
        @Parameter(description = "Объект блюда. Поля: name (обязательно), price (обязательно), description (опционально)", required = true)
        @RequestBody item: MenuItem
    ): MenuItem {
        return menuService.createMenuItem(item)
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Обновить существующее блюдо",
        description = "Обновляет название, цену и описание блюда. Если блюдо не найдено — возвращает 404."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Блюдо успешно обновлено"),
            ApiResponse(responseCode = "404", description = "Блюдо с указанным ID не найдено"),
            ApiResponse(responseCode = "400", description = "Неверные данные")
        ]
    )
    fun update(
        @Parameter(description = "ID блюда для обновления", example = "1") @PathVariable id: Long,
        @Parameter(description = "Новые данные блюда", required = true) @RequestBody item: MenuItem
    ): ResponseEntity<MenuItem> {
        return try {
            ResponseEntity.ok(menuService.updateMenuItem(id, item))
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }
}