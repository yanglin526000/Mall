package com.mall.file.file;

import com.mall.common.base.pojo.CommonPo;
import lombok.Data;

/**
 * @Author: nullWagesException
 * @Date: 2019/12/27 21:57
 * @Description:
 */
@Data
public class FastDFSFile extends CommonPo {
    /**
     * 文件内容
     */
    private byte[] content;
    /**
     * 文件扩展名
     */
    private String ext;
    /**
     * 文件MD5摘要值
     */
    private String md5;
    /**
     * 文件创建作者
     */
    private String author;

}
