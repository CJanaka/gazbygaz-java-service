package com.gazbygaz.service;

import com.gazbygaz.entity.Outlet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OutletService {
    Page<Outlet> findAll(Pageable pageable);
}
