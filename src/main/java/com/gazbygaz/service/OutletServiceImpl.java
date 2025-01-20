package com.gazbygaz.service;

import com.gazbygaz.entity.Outlet;
import com.gazbygaz.repository.OutletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OutletServiceImpl extends CommonService implements OutletService{

    @Autowired
    private OutletRepository outletRepository;

    @Override
    public Page<Outlet> findAll(Pageable pageable) {
        return outletRepository.findAll(pageable);
    }
}
