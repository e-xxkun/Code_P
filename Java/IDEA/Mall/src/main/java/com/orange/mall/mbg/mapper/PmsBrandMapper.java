package com.orange.mall.mbg.mapper;

import com.orange.mall.mbg.model.PmsBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PmsBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsBrand record);

    int insertSelective(PmsBrand record);

    PmsBrand selectByPrimaryKey(Long id);

    List<PmsBrand> listAllBrands();

    int updateByPrimaryKeySelective(PmsBrand record);

    int updateByPrimaryKeyWithBLOBs(PmsBrand record);

    int updateByPrimaryKey(PmsBrand record);
}