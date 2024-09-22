package com.itheima.mp.query;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.mp.domain.po.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
@ApiModel("分页查询实体")
public class PageQuery {
    @ApiModelProperty("页码")
    private Integer pageNo = 1;
    @ApiModelProperty("页数")
    private Integer pageSize = 5;
    @ApiModelProperty("排序字段")
    private String sortBy;
    @ApiModelProperty("排序方式")
    private Boolean isAsc = true;


    public <T> Page<T> toMpPage(OrderItem... orderItems) {
        Page<T> page = Page.of(this.pageNo, this.pageSize);
        if (this.sortBy != null) {
            page.addOrder(new OrderItem(this.sortBy, this.isAsc));
        } else if(orderItems != null){
            page.addOrder(orderItems);
        } else{
            page.addOrder(new OrderItem("update_time", false));
        }

        return page;
    }

    public <T> Page<T> toMpPageOrderByCreateTime(){
        return this.toMpPage(new OrderItem("create_time", false));
    }

    public <T> Page<T> toMpPageOrderByUpdateTime(){
        return this.toMpPage(new OrderItem("update_time", false));
    }

    public <T> Page<T> toMpPage(String sortBy, Boolean isAsc){
        return this.toMpPage(new OrderItem(sortBy, isAsc));
    }
}
