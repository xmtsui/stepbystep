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

import java.util.Iterator;
import java.util.List;

public class AlgorithmMain
{
    
    /**
     * <p>Title: 主执行类</p>
     *
     * <p>Description: 考生可以在该方法内调用预定义接口，用于调试</p>
     *
     * <p>Copyright: Copyright (c) 20103</p>
     *
     * <p>Company: Huawei</p>
     *
     * @author Huawei
     * @version 1.0
     */
    public static void main(String[] args)
    {
        // TODO 考生可以在此方法内调用预定义接口，采用屏幕打印的方式调试
    	int rackNum, chassisNumPerRack, diskNumPerChassis;
    	rackNum = 1;
    	chassisNumPerRack = 1;
    	diskNumPerChassis = 75;
    	
    	//获得单例
    	AlgorithmOperation alg_oper = AlgorithmOperation.getInstance();
    	
    	// 初始配置
		boolean t = alg_oper.Initialize(rackNum, chassisNumPerRack,
				diskNumPerChassis);

		if (t) {
			// 执行配置
			AlgorithmRepository result = alg_oper.Execute();
			// 遍历集群分配信息
			doTraverse(result);
		}
    }
    
    
    /**
     * 遍历集群分配信息
     * @param result
     */
    private static void doTraverse(AlgorithmRepository result)
    {
    	List<DiskInfo> diskInfos = result.getDiskInfos();
    	Iterator<DiskInfo> disk_itr = diskInfos.iterator();
    	
    	while(disk_itr.hasNext())
    	{
    		System.out.println("-------------------------");
    		DiskInfo tmp = disk_itr.next();
    		System.out.println("rack: " + tmp.getRackId());
    		System.out.println("chassis: " + tmp.getChassisId());
    		System.out.println("disk: " + tmp.getDiskId());
    		List<Integer> chunks = tmp.getChunks();
    		Iterator<Integer> chunk_itr = chunks.iterator();
    		System.out.print("chunk: ");
    		while(chunk_itr.hasNext())
    		{
    			System.out.print(chunk_itr.next()+" ");
    		}
    		System.out.println("\n-------------------------");
    	}
    }
}
