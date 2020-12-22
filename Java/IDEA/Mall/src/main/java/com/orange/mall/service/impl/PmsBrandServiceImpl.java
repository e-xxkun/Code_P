package com.orange.mall.service.impl;

import com.orange.mall.mbg.mapper.PmsBrandMapper;
import com.orange.mall.mbg.model.PmsBrand;
import com.orange.mall.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
        return brandMapper.listAllBrands();
    }

    @Override
    public PmsBrand getBrandById(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public int createBrand(PmsBrand pmsBrand) {
        return brandMapper.insert(pmsBrand);
    }

    @Override
    public int updateBrand(Long id, PmsBrand pmsBrand) {
        pmsBrand.setId(id);
        return brandMapper.updateByPrimaryKey(pmsBrand);
    }
}
