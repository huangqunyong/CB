package cb;

import java.util.Scanner;

/**
 * 
 * @Title: RPS.java
 * @Package cb
 * @Description:Relationship between point and surface(点与面的关系)
 * @author hqy
 * @date 2020-11-13
 * @version V1.0
 */
public class RPS {
	
	private static RPS rps = new RPS();
	
	public static void main(String[] args) {
		System.out.println("请以空格分隔坐标如：x y"); 
		System.out.println("请输入A点的坐标："); 
		Double[] a = rps.point();
		System.out.println("请输入B点的坐标：");
		Double[] b = rps.point();
		System.out.println("请输入C点的坐标：");
		Double[] c = rps.point();
		System.out.println("请输入D点的坐标：");
		Double[] d = rps.point();
		System.out.println("请输入E点的坐标：");
		Double[] e = rps.point();
		rps.relationship(a, b, c, d, e);
	}
	
	//将输入的坐标点存储在一个一维数组中
	private Double[] point() {
		Scanner sc = new Scanner(System.in);
		Double x = sc.nextDouble();
		Double y = sc.nextDouble();
		return new Double[]{x,y};
	}
	
	//三角形面积算法
	private Double triangle(Double[] a,Double[] b,Double[] c) {
		//BC线段长度
		Double bc = Math.sqrt((c[1] - b[1])*(c[1] - b[1]) + (c[0] - b[0])*(c[0] - b[0]));
		//线段BC的斜率（斜率有可能求值可能会出现0，需要先判断，防止除法运算错误）
		Double kbc;
		if((c[0] - b[0]) == 0) 
			kbc = 0.0;
		else
			kbc = (c[1] - b[1])/(c[0] - b[0]);
		//线段BC的截距
		Double bbc = c[1] - kbc*c[0];
		//A到线段BC的距离
		Double a_bc = Math.abs((a[1] - kbc*a[0] - bbc)/Math.sqrt(kbc*kbc + 1));
		//返回三角形面积
		return 0.5*bc*(a_bc);
	}
	
	
	//用于判断点与面的关系
	private void relationship(Double[] a,Double[] b,Double[] c,Double[] d,Double[] e) {
		//判断四边形ABCD的点是否按顺序输入
		//线段AB斜率
		Double k = (b[1] - a[1])/(b[0] - a[0]);
		//线段AB截距
		Double b0 = a[1] - k*a[0];
		//修正顺序以防出现求取面积出bug
		if((c[1] - k*c[0] - b0)*(d[1] - k*d[0] - b0) < 0) {
			Double[] f = b;
			b = d;
			Double[] g = c;
			c = f;
			d = g;
		}
		
		//以AC为对角线分割四边形，求两个三角形的面积来求解四边形面积
		Double abcd = rps.triangle(a, b, c) + rps.triangle(a, c, d);
		
		//以E为顶点，与四个点构成的4个三角形面积
		Double e_abcd = rps.triangle(e, a, b) + rps.triangle(e, b, c) + rps.triangle(e, c, d) + rps.triangle(e, d, a);
		
		//判断E点和四边形ABCD面的关系
		if(abcd.equals(e_abcd))
			System.out.println("E点在四边形ABCD上");
		else
			System.out.println("E点不在四边形ABCD上");
	}
	
}
