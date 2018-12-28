package com.springcloud.eshop.product.server.support.base;

import com.springcloud.eshop.common.server.support.utils.ServerResponse;
import com.springcloud.eshop.common.server.support.vo.PageVo;
import com.springcloud.eshop.product.server.support.utils.PageUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author Exrickx
 */
public abstract class XbootBaseController<E, ID extends Serializable> {

    /**
     * 获取service
     * @return
     */
    @Autowired
    public abstract XbootBaseService<E,ID> getService();

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "通过id获取")
    public ServerResponse<E> get(@PathVariable ID id){

        E entity = getService().get(id);
        return ServerResponse.createBySuccess(entity);
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取全部数据")
    public ServerResponse<List<E>> getAll(){

        List<E> list = getService().getAll();
        return ServerResponse.createBySuccess(list);
    }

    @RequestMapping(value = "/getByPage",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "分页获取")
    public ServerResponse<Page<E>> getByPage(@ModelAttribute PageVo page){

        Page<E> data = getService().findAll(PageUtil.initPage(page));
        return ServerResponse.createBySuccess(data);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存数据")
    public ServerResponse<E> save(@ModelAttribute E entity){

        E e = getService().save(entity);
        return ServerResponse.createBySuccess(e);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新数据")
    public ServerResponse<E> update(@ModelAttribute E entity){

        E e = getService().update(entity);
        return ServerResponse.createBySuccess(e);
    }

    @RequestMapping(value = "/delByIds/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "批量通过id删除")
    public ServerResponse<Object> delAllByIds(@PathVariable ID[] ids){

        for(ID id:ids){
            getService().delete(id);
        }
        return ServerResponse.createBySuccessMessage("批量通过id删除数据成功");
    }
}
