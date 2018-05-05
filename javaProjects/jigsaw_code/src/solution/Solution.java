package solution;
import java.util.*;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

    private Queue<JigsawNode> openList;
    private Queue<JigsawNode> closeList;
    /**
     * 拼图构造函数
     */
    public Solution() {
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {

        final int DIRS = 4;
        this.openList = new ArrayDeque<>();
        this.closeList = new ArrayDeque<>();
        this.beginJNode = new JigsawNode(bNode);
        this.endJNode = new JigsawNode(eNode);
        this.currentJNode = null;
        // (1)将起始节点放入openList中
        this.openList.add(this.beginJNode);

        // (2) 如果openList为空，则搜索失败，问题无解;否则循环直到求解成功
        while (!openList.isEmpty()) {
            // (2-1)访问open列表中的第一个节点v，置为当前节点currentJNode
            //      若v为目标节点，则搜索成功，，计算解路径，退出
            this.currentJNode = this.openList.peek();
            if (this.currentJNode.equals(eNode)) {
                this.getPath();
                break;
            }

            // （2-2) 从open列表中删除节点v，放入close列表中
            this.closeList.add(this.openList.poll());

            // 记录并显示搜索过程
            // System.out.println("Searching.....Number of searched nodes:" + searchedNodesNum +
            //     "    Est:" + this.currentJNode.getEstimatedValue() +
            //     "    Current state:" + this.currentJNode.toString());

            JigsawNode[] nextNodes = new JigsawNode[]{
                    new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode),
                    new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode)
            };

            // (2-2)寻找所有与currentJNode邻接且未曾被访问的节点
            //         并加入openList中
            for (int i = 0; i < DIRS; i++) {
                if (nextNodes[i].move(i) && !this.closeList.contains(nextNodes[i])) {
                    this.openList.add(nextNodes[i]);
                }
            }
        }
        this.getPath();
        String res = this.getSolutionPath();
        System.out.println("Jigsaw BFSearch Result:");
        System.out.println("Begin state:" + this.getBeginJNode().toString());
        System.out.println("End state:" + this.getEndJNode().toString());
        // System.out.println("Solution Path: ");
        // System.out.println(this.getSolutionPath());
        System.out.println("Depth of the current node is:" + this.getCurrentJNode().getNodeDepth());
        System.out.println("Solution path is: " + res);
        return this.isCompleted();
    }


    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        int estimateV = 0 * estimate0(jNode) + estimate1(jNode) + estimate2(jNode);
        jNode.setEstimatedValue(estimateV);
    }
    //所有放错位的数码的个数
    public int estimate0(JigsawNode jNode){
        int n = 0;
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index <= dimension * dimension; index++) {
            if (jNode.getNodesState()[index] != getEndJNode().getNodesState()[index]) {
                n++;
            }
        }
        return n;
    }
    // 后续节点不正确的数码个数
    public int estimate1(JigsawNode jNode){
        int n = 0;
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                n++;
            }
        }
        return n;
    }
    //所有放错位的数码与其正确位置的距离之和
    public int estimate2(JigsawNode jNode){
        int n = 0, row, col, rowR, colR;
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index <= dimension * dimension; index++) {
            if (jNode.getNodesState()[index] != getEndJNode().getNodesState()[index]) {
                row = (int) (index - 1) / dimension;
                col = (int) (index + 4) % dimension;
                for(int j = 1; j <= dimension * dimension; j++){
                    if(jNode.getNodesState()[index] == getEndJNode().getNodesState()[j]){
                        rowR = (int) (j - 1) / dimension;
                        colR = (int) (j + 4) % dimension;
                        n += (Math.abs(row - rowR) + Math.abs(col - colR));
                    }
                }
            }
        }
        return n;
    }
}
