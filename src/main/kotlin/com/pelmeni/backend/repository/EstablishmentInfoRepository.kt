package com.pelmeni.backend.repository

import com.pelmeni.backend.entity.EstablishmentInfo
import org.springframework.data.jpa.repository.JpaRepository

interface EstablishmentInfoRepository : JpaRepository<EstablishmentInfo, Long>