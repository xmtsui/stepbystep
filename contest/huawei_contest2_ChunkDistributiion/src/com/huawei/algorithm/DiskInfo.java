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

import java.util.List;

/**
 * <p>Title: 考生可以根据自己的需求在本类中增加变量和函数，但不能修改已有变量和函数</p>
 * <p>Description: 生产线信息结构，一个实例代表一个硬盘信息</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Huawei</p>
 *
 * @author Huawei
 * @version 1.0
 */
public class DiskInfo
{
    //机柜id
    private int rackId;
    
    //机框id
    private int chassisId;
    
    //硬盘id
    private int diskId;
    
    //硬盘中的块
    private List<Integer> chunks;
    
    public DiskInfo(int rackId, int chassisId, int diskId, List<Integer> chunks)
    {
        this.rackId = rackId;
        this.chassisId = chassisId;
        this.diskId = diskId;
        this.chunks = chunks;
    }

    public int getRackId()
    {
        return rackId;
    }

    public void setRackId(int rackId)
    {
        this.rackId = rackId;
    }

    public int getChassisId()
    {
        return chassisId;
    }

    public void setChassisId(int chassisId)
    {
        this.chassisId = chassisId;
    }

    public int getDiskId()
    {
        return diskId;
    }

    public void setDiskId(int diskId)
    {
        this.diskId = diskId;
    }

    public List<Integer> getChunks()
    {
        return chunks;
    }

    public void setChunks(List<Integer> chunks)
    {
        this.chunks = chunks;
    }
    
}