package com.orange.mall.service;

import com.orange.mall.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author xxkun
 * @creed Awaken the Giant Within
 * @description:
 * @date 2021-01-03 16:31
 */
public interface EsProductService {
    int importAll();

    void delete(Long id);

    void delete(List<Long> ids);

    EsProduct create(Long id);

    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);
}
