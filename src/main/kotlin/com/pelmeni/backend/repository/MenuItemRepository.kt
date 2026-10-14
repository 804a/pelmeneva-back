package com.pelmeni.backend.repository

import com.pelmeni.backend.entity.MenuItem
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface MenuItemRepository : JpaRepository<MenuItem, Long> {
    override fun findAll(pageable: Pageable): Page<MenuItem>
}