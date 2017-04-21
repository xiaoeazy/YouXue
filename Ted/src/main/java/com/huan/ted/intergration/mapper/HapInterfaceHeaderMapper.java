package com.huan.ted.intergration.mapper;

import com.huan.ted.mybatis.common.Mapper;

import com.huan.ted.intergration.dto.HapInterfaceHeader;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huanghuan
 * @version  2016/7/21.
 */
public interface HapInterfaceHeaderMapper extends Mapper<HapInterfaceHeader> {

    //查询所有的系统接口
   public  List<HapInterfaceHeader> getAllHeader(HapInterfaceHeader interfaceHeader);

    /*
    * 一对多关联查询 根据headerId 与语言
    * */
    public List<HapInterfaceHeader> getHeaderAndLineList(HapInterfaceHeader interfaceHeader);

    /*
    * 根据sysName 和 apiName 查询header 和 line
    * */
    public HapInterfaceHeader getHeaderAndLineBySysNameAndApiName(@Param("sysName") String sysName, @Param("apiName") String apiName);

    /*
    * 获取所有的header和line 数据--->HeaderAndHeaderTlDTO
    * */
    List<HapInterfaceHeader> getAllHeaderAndLine();

   /*
   * 根据headerId 获取header
   * */
   List<HapInterfaceHeader> getHeaderByHeaderId(HapInterfaceHeader interfaceHeader);

   /*
   * 根据lineId 获取数据  HeaderAndLineDTO
   * */
   HapInterfaceHeader getHeaderAndLineBylineId(HapInterfaceHeader interfaceHeader);


}
