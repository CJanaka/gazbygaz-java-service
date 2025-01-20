package com.gazbygaz.common;

import com.gazbygaz.dto.Pagination;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ServiceContext {

    public static Pageable pagination(Pagination pagination){
//        Pageable pageable = PageRequest.of(0, 0);
//        System.out.println("01 pageable.isUnpaged() "+pageable.isUnpaged());
//        System.out.println("01 pageable.isPaged() "+pageable.isPaged());
        if (pagination.getPage() == 0){
//            pageable.isUnpaged();
//            return pageable;
        }

        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize());
        System.out.println("02 pageable.isUnpaged() "+pageable.isUnpaged());
        System.out.println("02 pageable.isPaged() "+pageable.isPaged());
        return pageable;
    }
}
