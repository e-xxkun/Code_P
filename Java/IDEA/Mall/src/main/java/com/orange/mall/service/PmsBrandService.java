package com.orange.mall.service;

import com.orange.mall.mbg.model.PmsBrand;

import java.util.List;

public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    PmsBrand getBrandById(Long id);

    int createBrand(PmsBrand pmsBrand);
}
