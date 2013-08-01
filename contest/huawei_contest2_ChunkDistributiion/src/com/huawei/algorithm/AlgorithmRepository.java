/*
 * Copyright Notice:
 *      Copyright  1998-2009, Huawei Technologies Co., Ltd.  ALL Rights Reserved.
 *
 *      Warning: This computer software sourcecode is protected by copyright law
 *      and international treaties. Unauthorized reproduction or distribution
 *      of this sourcecode, or any portion of it, may result in severe civil and
 *      criminal penalties, and will be prosecuted to the maximum extent
 *      possible under the law.
 */
package com.huawei.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: 考生可以根据自己的需求在本类中增加变量和函数，但不能修改已有变量和函数</p>
 * <p>Description: 算法信息结构，一个实例代表一次算法执行结果</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Huawei</p>
 *
 * @author Huawei
 * @version 1.0
 */
public class AlgorithmRepository
{
    //一次算法执行结果中所有硬盘的信息
    List<DiskInfo> diskInfos = new ArrayList<DiskInfo>();

    public List<DiskInfo> getDiskInfos()
    {
        return diskInfos;
    }

    public void setDiskInfos(List<DiskInfo> diskInfos)
    {
        this.diskInfos = diskInfos;
    }
}
