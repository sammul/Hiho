/*
时间限制:10000ms
单点时限:1000ms
内存限制:256MB
描述

万圣节的早上，小Hi和小Ho在经历了一个小时的争论后，终于决定了如何度过这样有意义的一天——他们决定去闯鬼屋！

在鬼屋门口排上了若干小时的队伍之后，刚刚进入鬼屋的小Hi和小Ho都颇饥饿，于是他们决定利用进门前领到的地图，找到一条通往终点的最短路径。

鬼屋中一共有N个地点，分别编号为1..N，这N个地点之间互相有一些道路连通，两个地点之间可能有多条道路连通，但是并不存在一条两端都是同一个地点的道路。那么小Hi和小Ho至少要走多少路程才能够走出鬼屋去吃东西呢？

提示：顺序！顺序才是关键。
输入

每个测试点（输入文件）有且仅有一组测试数据。

在一组测试数据中：

第1行为4个整数N、M、S、T，分别表示鬼屋中地点的个数和道路的条数，入口（也是一个地点）的编号，出口（同样也是一个地点）的编号。

接下来的M行，每行描述一条道路：其中的第i行为三个整数u_i, v_i, length_i，表明在编号为u_i的地点和编号为v_i的地点之间有一条长度为length_i的道路。

对于100%的数据，满足N<=10^3，M<=10^4, 1 <= length_i <= 10^3, 1 <= S, T <= N, 且S不等于T。

对于100%的数据，满足小Hi和小Ho总是有办法从入口通过地图上标注出来的道路到达出口。

输出

对于每组测试数据，输出一个整数Ans，表示那么小Hi和小Ho为了走出鬼屋至少要走的路程。

样例输入
5 23 5 4
1 2 708
2 3 112
3 4 721
4 5 339
5 4 960
1 5 849
2 5 98
1 4 99
2 4 25
2 1 200
3 1 146
3 2 106
1 4 860
4 1 795
5 4 479
5 4 280
3 4 341
1 4 622
4 2 362
2 3 415
4 1 904
2 1 716
2 5 575
样例输出
123
*/



import java.util.HashMap;
import java.util.Scanner;

public class ShortestPath{
    public static void main(String[] args) {
        Scanner sin = new Scanner(System.in);
        int vn = sin.nextInt();
        int en = sin.nextInt();
        int entry = sin.nextInt();
        int exit = sin.nextInt();
        int[][] edges = new int[vn+1][vn+1];
        for(int i=0; i<=vn ;i++){
        	for(int j=0; j<=vn; j++)
        		edges[i][j] = Integer.MAX_VALUE;
        }
        for(int i=0; i<en; i++){
        	int start = sin.nextInt();
        	int end = sin.nextInt();
        	int length = sin.nextInt();
        	edges[start][end] = Math.min(edges[start][end],length);
        	edges[end][start] = edges[start][end];
        }
        HashMap<Integer,Integer> visited = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> cand = new HashMap<Integer,Integer>();
        for(int i=1; i<=vn; i++){
        	if(i==entry)
        		continue;
        	cand.put(i,edges[entry][i]);
        }
        visited.put(entry,0);
        while(!visited.containsKey(exit)){
        	int min = Integer.MAX_VALUE;
        	int nextCand = -1;
        	for(int i : cand.keySet()){
        		if(cand.get(i) < min){
        			min = cand.get(i);
        			nextCand = i;
        		}
        	}
        	visited.put(nextCand,min);
        	cand.remove(nextCand);
        	for(int i=1; i<=vn; i++){
        		if(visited.containsKey(i))
        			continue;
        		cand.put(i,Math.min(cand.get(i),min+edges[nextCand][i]));
        	}
        }
        System.out.println(visited.get(exit));
        sin.close();
    }
}