package com.littlemoth.common.exception.file;

import com.littlemoth.common.exception.base.BaseException;

/**
 * 文件信息异常类
 * 
 * @author xiusan
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
