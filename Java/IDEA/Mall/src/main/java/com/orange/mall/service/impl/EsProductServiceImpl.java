package com.orange.mall.service.impl;

import com.orange.mall.nosql.elasticsearch.document.EsProduct;
import com.orange.mall.service.EsProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxkun
 * @creed Awaken the Giant Within
 * @description: EsProductService
 * @date 2021-01-03 16:42
 */
@Service
public class EsProductServiceImpl implements EsProductService {
    @Override
    public int importAll() {
        return 0;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(List<Long> ids) {

    }

    @Override
    public EsProduct create(Long id) {
        return null;
    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        return null;
    }
}
