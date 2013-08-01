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
import java.util.LinkedList;
import java.util.List;

/**
 * <p>Title: 待考生实现类,提供公共接口操作</p>
 *
 * <p>Description: 各方法按要求实现和返回，考生不得修改参数和返回值</p>
 *
 * <p>Copyright: Copyright (c) 2013</p>
 *
 * <p>Company: Huawei</p>
 *
 * 对接口的说明：
 * 1、此方法定义了考试工程的接口，考生不得更改接口的参数和返回值；
 * 2、Initialize、Remove、Recover、Add方法只是修改集群的硬件配置，并不执行算法；
 * 3、调用Execute方法执行算法，分配分区，并将分区分配的结果以对象形式返回；
 * 4、只考虑故障/恢复/增加硬盘的场景，考生若有余力，可以考虑故障/恢复/增加机框或机柜的场景；
 * 5、（非常重要）硬件id的命名规则,请按以下规则命名，否则会导致测试用例失败：硬件被分为3类：rack、chassis、disk，
 *   每一类硬件的id从0开始按自然序递增，每一类硬件的id连续且唯一，例如，以下是一个集群的硬件配置情况：
 * -rack0
 *  -chassis0
 *      -disk0
 *      -disk1
 * -rack1
 *  -chassis1
 *      -disk2
 *  -chassis2
 *      -disk3
 *      -disk4
 *  若在此基础上增加一块新的硬盘，则该硬盘的id为5.
 *  
 * @author Huawei
 * @version 1.0
 */
public class AlgorithmOperation
{
	private static AlgorithmOperation instance;
	private static Object sync_this = new Object();//used for synchronization
	
	//集群的硬件配置信息，定义为static，生命周期同AlgorithmOperation单例
	private static int rackNum;//最少为1
	private static int chassisNumPerRack;//每个机柜rack的机框chassis数量，1：4
	private static int diskNumPerChassis;//每个机框chassis的硬盘disk数量，1：75
	private static int diskNumTotal;//磁盘总数
	private static int chunkGroup;//chunk组数
	private static int chunkNumTotal;//chunk总数
	private static GroupInfo[] groupInfo;//chunk组信息，每组32个chunk
	
	
    
	/**
     * 必须提供无参数构造函数，考生可在函数体中根据需要增加初始化代码
     * 程序库中会且只会生成一个AlgorithmOperation实例，并在整个进程生命周期中一直使用这个实例
     */
    public AlgorithmOperation()
    {
    }
    
	/**
	 * 并发环境下的单例实现
	 * @author cuixiaomin
	 * @return
	 */
	public static AlgorithmOperation getInstance(){
    	if(instance == null)
		{
			synchronized(sync_this){
				if(instance == null)
					instance = new AlgorithmOperation();
			}
		}
		return instance;
	}
	
    /**
     * 待考生实现方法 生成初始的集群结构
     *
     * @param rackNum: 机柜数量
     * @param chassisNum: 每个机柜中的机框数量
     * @param diskNum: 每个机框中的硬盘数量
     * @return boolean: 生成结果，如果参数不合理，将返回false
     * @throws DiskArgumentsIllegalException 
     */
    public boolean Initialize(int rackNum, int chassisNumPerRack, int diskNumPerChassis)
    {
    	try {
    		doCheckInitialArguments(rackNum, chassisNumPerRack, diskNumPerChassis);
		} catch (DiskArgumentsIllegalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

    	AlgorithmOperation.rackNum = rackNum;
    	AlgorithmOperation.chassisNumPerRack = chassisNumPerRack;
    	AlgorithmOperation.diskNumPerChassis = diskNumPerChassis;
    	
//    	int chunkNumInTheory = rackNum*chassisNumPerRack*diskNumPerChassis*30;
//    	
//    	if( chunkNumInTheory%32 == 0)
//    		chunkGroup = chunkNumInTheory/32;
//    	else
//    		chunkGroup = chunkNumInTheory/32+1;
//    	
    	//组数等于 磁盘总数 减去 总数除16取整
    	AlgorithmOperation.diskNumTotal = rackNum*chassisNumPerRack*diskNumPerChassis;
    	int radix = AlgorithmOperation.diskNumTotal/16;
    	chunkGroup = AlgorithmOperation.diskNumTotal - radix;
    	
    	AlgorithmOperation.chunkNumTotal = AlgorithmOperation.chunkGroup*32;
    	AlgorithmOperation.groupInfo = new GroupInfo[AlgorithmOperation.chunkGroup];
    	for(int i=0; i<AlgorithmOperation.chunkGroup; ++i)
    	{
    		AlgorithmOperation.groupInfo[i] = new GroupInfo();
    		AlgorithmOperation.groupInfo[i].setGroupId(i);
    		for(int j=i*32; j<(i+1)*32; ++j)
    		{
    			AlgorithmOperation.groupInfo[i].getChunkIds()[j-i*32] = j;
    		}
    	}
    	
//    	System.out.println("chunkGroup: " + AlgorithmOperation.chunkGroup);
//    	System.out.println("chunkNumTotal: " + AlgorithmOperation.chunkNumTotal);
//    	for(int i=0; i<AlgorithmOperation.chunkGroup; ++i)
//    	{
//    		AlgorithmOperation.groupInfo[i].doTraverse();
//    	}
    	return true;
    }
    
    /**
     * 私有函数，用于检查初时配置参数的正确性
     * @param rackNum
     * @param chassisNumPerRack
     * @param diskNumPerChassis
     * @throws DiskArgumentsIllegalException
     */
    private void doCheckInitialArguments(int rackNum, int chassisNumPerRack, int diskNumPerChassis) throws DiskArgumentsIllegalException
    {
    	if (rackNum <= 0 || chassisNumPerRack < 1 || chassisNumPerRack > 4
				|| diskNumPerChassis < 1 || diskNumPerChassis > 75)
			throw new DiskArgumentsIllegalException("机柜最少为1，机框1到4，硬盘1到75");
		if (rackNum * chassisNumPerRack * diskNumPerChassis < 3)
			throw new DiskArgumentsIllegalException("磁盘总数不能小于3");
    }
    
    /**
     * 待考生实现方法 让某个硬盘故障
     *
     * @param type:硬件类型 0-柜，1-框，2-盘
     * @param diskId: 硬盘id
     * @return boolean: 生成结果，如果参数不合理，将返回false
     */
    public boolean Remove(int type, int diskId)
    {
        //TODO
    	switch(type){
    			
    	}
        return true;
    }
    
    /**
     * 待考生实现方法 让某个硬盘恢复工作
     *
     * @param type:硬件类型 0-柜，1-框，2-盘
     * @param diskId: 硬盘id
     * @return boolean: 生成结果，如果参数不合理，将返回false
     */
    public boolean Recover(int type, int diskId)
    {
        //TODO
    	switch(type){
		
    	}
        return true;
    }
    
    /**
     * 待考生实现方法 增加一块硬盘，需要指定该硬盘所在的机框和机柜id
     * 
     * @param rackId: 机柜id
     * @param chassisId: 机框id
     * @param diskId: 硬盘id
     * @return boolean: 生成结果，如果参数不合理，将返回false
     */
    public boolean Add(int rackId, int chassisId, int diskId)
    {
        //TODO
        return true;
    }
    
    /**
     * 待考生实现方法 执行算法，分配分区
     * 
     * @return AlgorithmRepository: 封装好的分配结果，用以评估结果的正确性
     */
    public AlgorithmRepository Execute() {
		// TODO
    	List<DiskInfo> diskInfos = new ArrayList<DiskInfo>();
    	for(int i=0; i<rackNum; ++i)
    	{
    		for(int j=i*chassisNumPerRack; j<(i+1)*chassisNumPerRack; ++j)
    		{
    			for(int k=j*diskNumPerChassis; k<(j+1)*diskNumPerChassis; ++k)
    			{
    				DiskInfo adisk = new DiskInfo(i,j,k,allocateChunks(i,j,k));
    				diskInfos.add(adisk);
    			}
    		}
    	}
    	System.out.println(chunkNumTotal);
    	AlgorithmRepository repo = new AlgorithmRepository();
		repo.setDiskInfos(diskInfos);
		
		return repo;
	}
    
    /**
     * 分配策略
     * @param rackId
     * @param chassisId
     * @param diskId
     * @return
     */
    private List<Integer> allocateChunks(int rackId, int chassisId, int diskId){
		
    	int cycle = diskNumTotal - chunkGroup;
    	List<Integer> chunks = new LinkedList<Integer>();
    	int radix = AlgorithmOperation.diskNumTotal/16;
    	for(int i=0; i<31; ++i)
    	{
    		int index=diskId+i*cycle;
    		if(index>=chunkGroup)
    			index=index%chunkGroup;
    		if(AlgorithmOperation.groupInfo[index].hasNext())
    		{
    			chunks.add(groupInfo[index].allocateNext());
    			chunkNumTotal--;
    		}
    	}
    	return chunks;
    }
}
