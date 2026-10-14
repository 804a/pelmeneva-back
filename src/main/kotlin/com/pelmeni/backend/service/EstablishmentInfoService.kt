package com.pelmeni.backend.service

import com.pelmeni.backend.entity.EstablishmentInfo
import com.pelmeni.backend.repository.EstablishmentInfoRepository
import org.springframework.stereotype.Service

@Service
class EstablishmentInfoService(private val repository: EstablishmentInfoRepository) {

    fun getMainInfo(): EstablishmentInfo {
        return repository.findAll().firstOrNull()
            ?: throw RuntimeException("Информация о заведении не найдена")
    }

    fun getInfoById(id: Long): EstablishmentInfo {
        return repository.findById(id).orElseThrow {
            RuntimeException("Заведение с id=$id не найдено")
        }
    }

    fun createInfo(info: EstablishmentInfo): EstablishmentInfo = repository.save(info)
}