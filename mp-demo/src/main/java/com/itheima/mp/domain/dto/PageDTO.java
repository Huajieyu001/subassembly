package com.itheima.mp.domain.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.func.Func;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页结果")
public class PageDTO<V> {
    @ApiModelProperty("总记录数")
    private Long total;
    @ApiModelProperty("总页数")
    private Long pages;
    @ApiModelProperty("数据集合")
    private List<V> list;

    public static <PO, VO>  PageDTO<VO> of(Page<PO> page, Class<VO> clazz){
        PageDTO<VO> vo = new PageDTO<>();

        vo.setPages(page.getPages());
        vo.setTotal(page.getTotal());

        List<PO> records = page.getRecords();
        if(CollUtil.isEmpty(records)){
            vo.setList(Collections.emptyList());
            return vo;
        }

        List<VO> voList = BeanUtil.copyToList(records, clazz);
        vo.setList(voList);

        return vo;
    }

    public static <PO, VO>  PageDTO<VO> of(Page<PO> page, Function<PO, VO> convertor){
        PageDTO<VO> vo = new PageDTO<>();

        vo.setPages(page.getPages());
        vo.setTotal(page.getTotal());

        List<PO> records = page.getRecords();
        if(CollUtil.isEmpty(records)){
            vo.setList(Collections.emptyList());
            return vo;
        }

        List<VO> collect = records.stream().map(convertor).collect(Collectors.toList());
        vo.setList(collect);

        return vo;
    }
}
