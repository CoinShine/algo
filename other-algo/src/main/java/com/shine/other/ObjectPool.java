package com.shine.other;

import java.util.Vector;

/**
 * DESCRIPTION: 简单对象池
 * https://www.cnblogs.com/nucdy/p/7573218.html
 * http://ifeve.com/generic-concurrent-object-pool/
 * @author Shine
 * @create 2019/11/28 18:27
 */
public class ObjectPool {
	private ParameterObject paraObj;//该对象池的属性参数对象
	private int currentNum = 0; //该对象池当前已创建的对象数目
	private Vector<Demo> pool;//用于存放对象的池

	public ObjectPool(ParameterObject paraObj) {
		this.paraObj = paraObj;
		pool = new Vector<>();
	}

	public Demo getObject() {
		//该对象池当前可以借出的对象
		Demo currentObj;
		if (pool.size() <= paraObj.getMinCount()) {
			if (currentNum <= paraObj.getMaxCount()) {
				currentObj = new Demo();
				currentNum++;
			} else {
			//如果当前池中无对象可用，而且所创建的对象数目已达到所限制的最大值，
			//就只能等待其它线程返回对象到池中
				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
					currentObj = pool.firstElement();
				}
			}
		} else {
			//如果当前池中有可用的对象，就直接从池中取出对象
			currentObj = pool.firstElement();
		}

		return currentObj;
	}


	public void returnObject(Object obj) {
		// 确保对象具有正确的类型
		if (obj instanceof Demo) {
			pool.addElement((Demo) obj);
		}
	}

	class ParameterObject {
		private int minCount;
		private int maxCount;

		public int getMinCount() {
			return minCount;
		}

		public void setMinCount(int minCount) {
			this.minCount = minCount;
		}

		public int getMaxCount() {
			return maxCount;
		}

		public void setMaxCount(int maxCount) {
			this.maxCount = maxCount;
		}
	}

	class Demo{}
}