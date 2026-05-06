package com.pelmeni.backend.controller

import com.pelmeni.backend.entity.EstablishmentInfo
import com.pelmeni.backend.repository.EstablishmentInfoRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/info")
class InfoController(private val repository: EstablishmentInfoRepository) {

    @GetMapping
    fun getInfo(): EstablishmentInfo {
        return repository.findAll().firstOrNull()
            ?: throw RuntimeException("Информация о заведении не найдена")
    }

    @PostMapping("/init")
    fun createInitialInfo(@RequestBody info: EstablishmentInfo): EstablishmentInfo {
        return repository.save(info)
    }
}