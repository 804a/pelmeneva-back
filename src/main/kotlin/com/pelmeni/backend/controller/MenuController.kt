package com.pelmeni.backend.controller


import com.pelmeni.backend.repository.MenuItemRepository
import org.springframework.web.bind.annotation.*
import com.pelmeni.backend.entity.MenuItem
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/api/menu")
class MenuController(private val repository: MenuItemRepository) {

    @GetMapping
    fun getAll(): List<MenuItem> = repository.findAll()

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): ResponseEntity<MenuItem> {
        val item = repository.findById(id)
        return if (item.isPresent) {
            ResponseEntity.ok(item.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody item: MenuItem): ResponseEntity<MenuItem> {
        val existing = repository.findById(id)
        return if (existing.isPresent) {
            val updated = existing.get()
            updated.name = item.name
            updated.price = item.price
            updated.description = item.description
            ResponseEntity.ok(repository.save(updated))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun create(@RequestBody item: MenuItem): MenuItem = repository.save(item)
}