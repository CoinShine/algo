package com.shine.bytedance;

import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

/**
 * description: 原子的数量
 * 给定一个化学式formula（作为字符串），返回每种原子的数量。
 *
 * 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
 *
 * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
 *
 * 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。
 *
 * 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
 *
 * 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
 *
 * 示例 1:
 *
 * 输入:
 * formula = "H2O"
 * 输出: "H2O"
 * 解释:
 * 原子的数量是 {'H': 2, 'O': 1}。
 *
 * 方法一：递归
 * 算法：
 *
 * 编写一个方法 parse 来解析化学式，返回一个由原子名称映射到原子个数的哈希表 count。
 * 将把 i 设为全局变量：在调用 parse 函数中递增 i。
 * 当遇到 '('，则解析括号内的内容（直到括号结束），并将其添加到计数中。
 * 否则，则应该遇到一个大写字符：我们将解析其余的字母以获得名称，并在哈希表中添加该字符（若表中存在则增加计数）。
 * 最终，我们将乘以括号系数以得到最终结果
 *
 * 时间复杂度：O(N^2) N 指的是化学式的长度。
 * 空间复杂度：O(N)，我们没有记录到比公式更多的信息。
 * @author shine
 * @date 2019/11/16 11:21
 * @version 1.0
 */
public class Test726 {
	int i;
	public String countOfAtoms(String formula) {
		StringBuilder ans = new StringBuilder();
		i = 0;
		Map<String, Integer> count = parse(formula);
		for (String name: count.keySet()) {
			ans.append(name);
			int multiplicity = count.get(name);
			if (multiplicity > 1) ans.append("" + multiplicity);
		}
		return new String(ans);
	}

	/**
	 * 递归方法 由原子名称映射到原子个数的哈希表 count
	 * @param formula
	 * @return
	 */
	private Map<String, Integer> parse(String formula) {
		int N = formula.length();
		Map<String, Integer> count = new TreeMap<>(); // 使用TreeMap是为了保证有序
		while (i < N && formula.charAt(i) != ')') { // 如果不是右括号
			if (formula.charAt(i) == '(') { // 遇到左括号递归调用
				i++;
				for (Map.Entry<String, Integer> entry: parse(formula).entrySet()) {
					count.put(entry.getKey(), count.getOrDefault(entry.getKey(), 0) + entry.getValue()); // 没有的话 0+value，有值的话取出原来的值在加上value
				}
			} else {
				int iStart = i++; //指针向后移动
				while (i < N && Character.isLowerCase(formula.charAt(i))) i++; // 如果是小写字符，指针后移
				String name = formula.substring(iStart, i); // 截取出元素 比如 He
				iStart = i; // 设置元素数量的数字指针
				while (i < N && Character.isDigit(formula.charAt(i))) i++; // 如果是数字，指针后移
				int multiplicity = iStart < i ? Integer.parseInt(formula.substring(iStart, i)) : 1; // 截取出元素后的数字，没有数字设置为1
				count.put(name, count.getOrDefault(name, 0) + multiplicity); // 将结果放置到map中
			}
		}
		int iStart = ++i; // 记录右括号的指针位置
		while (i < N && Character.isDigit(formula.charAt(i))) i++;
		if (iStart < i) {  // 括号中的元素乘以括号外的系数
			int multiplicity = Integer.parseInt(formula.substring(iStart, i));
			for (String key: count.keySet()) {
				count.put(key, count.get(key) * multiplicity);
			}
		}
		return count;
	}


	@Test
	public void test01(){
		String formula = "H2O";
		String formula2 = "Mg(OH)2";
		String formula3 = "K4(ON(SO3)2)2";
		String s = countOfAtoms(formula2);
		System.out.println(s);
	}
}
