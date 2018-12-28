package com.springcloud.eshop.product.server.repository.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springcloud.eshop.product.server.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product>{
    @Select("select * from product")
    List<Product> findAllProducts();

    @Insert("insert \n" +
            "t_product \n" +
            "(id, name, description, category_id, brand_id,create_by, create_time, update_time, update_by, del_flag) \n" +
            "value \n" +
            "(#{product.id}, #{product.name}, #{product.description}, #{product.categoryId}, #{product.brandId}, #{product.createBy}, \n" +
            "#{product.createTime}, #{product.updateTime}, #{product.updateBy}, #{product.delFlag})")
    void addProduct(@Param("product") Product product);

    @Update("UPDATE t_product \n" +
            "SET \n" +
            "name = #{product.name}, description = #{product.description}, category_id = #{product.categoryId}, \n" +
            "create_by = #{product.createBy}, create_time = #{product.createTime}, update_by = #{product.updateBy}, \n" +
            "update_time = #{product.updateTime}, del_flag = #{product.delFlag} \n" +
            "WHERE id = #{product.id}")
    void updateProduct(@Param("product") Product product);

    @Delete("DELETE FROM t_product WHERE id = #{id}")
    void deleteProduct(@Param("id") String id);

    @Select("SELECT * FROM t_product where id = #{id}")
    Product findByProductId(@Param("id") String id);


}
