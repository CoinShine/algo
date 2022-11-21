package com.shine.other;

import com.alibaba.fastjson.JSON;
import com.shine.pojo.BinaryTreeNode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * description: 二叉树查找最有边的节点，深度优先搜索
 * @author shine
 * @date 2019/08/29 15:41
 * @version 1.0
 */
public class FindRightNodes {

	@Before
	public void init(){

		BinaryTreeNode h = new BinaryTreeNode("h");
		BinaryTreeNode g = new BinaryTreeNode("g");
		BinaryTreeNode f = new BinaryTreeNode("f");
		BinaryTreeNode c = new BinaryTreeNode("c",f,h);
		BinaryTreeNode e = new BinaryTreeNode("e",g,null);
		BinaryTreeNode d = new BinaryTreeNode("d");
		BinaryTreeNode b = new BinaryTreeNode("b",d,e);
		binaryTreeNode = new BinaryTreeNode("a",b,c);
	}
	private List<String> resultList = new ArrayList<>();
	private BinaryTreeNode binaryTreeNode;
	public int findNodes(BinaryTreeNode node, int currentDepth, int maxDepth, List<String> resultList){
		if(node==null){
			return maxDepth;
		}
		if (currentDepth > maxDepth) {
			maxDepth = currentDepth;
			resultList.add((String) node.val);
			maxDepth = findNodes(node.right, currentDepth+1, maxDepth, resultList);
			maxDepth = findNodes(node.left, currentDepth+1, maxDepth, resultList);
		} else {
			maxDepth = findNodes(node.right, currentDepth + 1, maxDepth, resultList);
			maxDepth = findNodes(node.left, currentDepth+1, maxDepth, resultList);
		}
		return maxDepth;


	}
	@Test
	public void testTreeNodes(){
		//init();
		findNodes(binaryTreeNode,0,-1,resultList);
		System.out.println(JSON.toJSONString(resultList));
	}
}
