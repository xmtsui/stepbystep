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
 * <p>Title: Group</p>
 * <p>Description: 生产线信息结构，一个实例代表一个组</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Huawei</p>
 *
 * @author cuixiaomin
 * @version 1.0
 */
public class GroupInfo
{
    //块id
	private int groupId;
    private int[] chunkIds;
    private int index;//依此增加
    private long checker;//用于按位检测该位置是否已分配
    
    /**
     * 初始化组信息
     */
    public GroupInfo()
    {
    	chunkIds = new int[32];
    	index=0;
    	checker=0;
    }
    
    /**
     * 分配下一个
     * @return
     */
    public int allocateNext()
    {
    	if(!isAllocated(index))
    	{
    		int bit_location = chunkIds[index]-chunkIds[0];
    		checker |= (1l<<bit_location);
    	}
    	return chunkIds[index++];
    }
    
    /**
     * 判断某个index是否已分配
     * @param index
     * @return
     */
    public boolean isAllocated(int index)
    {
    	int bit_location = chunkIds[index]-chunkIds[0];
    	if((checker & (1<<bit_location)) > 0)
    		return true;
    	else
    		return false;
    }
    
    /**
     * 判断该组是否分配完
     * @return
     */
    public boolean hasNext()
    {
    	if(checker == 4294967295l)
    	{
    		return false;
    	}
    	else
    		return true;
    }
    
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int[] getChunkIds() {
		return chunkIds;
	}

	public void setChunkIds(int[] chunkIds) {
		this.chunkIds = chunkIds;
	}
	
	//方便测试
	public void doTraverse(){
		System.out.println("groupId: " + groupId);
		System.out.print("chunkIds: ");
		for(int i=0; i<chunkIds.length; ++i)
			System.out.print(chunkIds[i]+" "); 
		System.out.println();
	}
}