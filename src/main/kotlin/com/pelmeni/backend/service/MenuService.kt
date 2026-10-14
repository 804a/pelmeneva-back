package com.pelmeni.backend.service

import com.pelmeni.backend.entity.MenuItem
import com.pelmeni.backend.repository.MenuItemRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class MenuService(private val repository: MenuItemRepository) {

    /**
     * Получить страницу меню.
     * Результат кэшируется в Redis под именем "menu".
     */
    @Cacheable("menu")
    fun getAllMenu(pageable: Pageable): Page<MenuItem> = repository.findAll(pageable)

    /**
     * Получить блюдо по ID.
     * Кэшируется отдельно для каждого ID.
     */
    @Cacheable(value = ["menuItem"], key = "#id")
    fun getMenuItemById(id: Long): MenuItem {
        return repository.findById(id).orElseThrow {
            RuntimeException("Блюдо с id=$id не найдено")
        }
    }

    /**
     * Удалить блюдо по ID.
     * После удаления очищаем весь кэш меню и кэш конкретного блюда.
     */
    @CacheEvict(value = ["menu", "menuItem"], allEntries = true)
    fun deleteMenuItem(id: Long) {
        if (!repository.existsById(id)) {
            throw RuntimeException("Блюдо с id=$id не найдено")
        }
        repository.deleteById(id)
    }

    /**
     * Создать новое блюдо.
     * После создания очищаем весь кэш меню.
     */
    @CacheEvict(value = ["menu"], allEntries = true)
    fun createMenuItem(item: MenuItem): MenuItem = repository.save(item)

    /**
     * Обновить блюдо.
     * После обновления очищаем весь кэш меню и кэш конкретного блюда.
     */
    @CacheEvict(value = ["menu", "menuItem"], allEntries = true)
    fun updateMenuItem(id: Long, item: MenuItem): MenuItem {
        val existing = repository.findById(id).orElseThrow {
            RuntimeException("Блюдо с id=$id не найдено")
        }
        existing.name = item.name
        existing.price = item.price
        existing.description = item.description
        return repository.save(existing)
    }
}