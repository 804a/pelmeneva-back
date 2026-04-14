package com.pelmeni.backend.controller


import com.pelmeni.backend.repository.MenuItemRepository
import org.springframework.web.bind.annotation.*
import com.pelmeni.backend.entity.MenuItem

@RestController
@RequestMapping("/api/menu")
class MenuController(private val repository: MenuItemRepository) {

    @GetMapping
    fun getAll(): List<MenuItem> = repository.findAll()

    @PostMapping
    fun create(@RequestBody item: MenuItem): MenuItem = repository.save(item)
}