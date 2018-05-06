# 实训总结报告

​	一个月的实训生活终于结束了，整体上感觉难度还是在掌控范围之内，和初级实训相比起来，那时要显得仓促许多，也许这一年下来写代码的能力还是有所提高的吧。

​	这次中级实训的一大难点就是Java语言的使用。因为我个人之前基本没怎么使用过Java来编程，所以要在四周之内完成各项任务还是挺不容易的。因为没有进行过系统的学习，所以和初级实训时用C语言(C++)来编程相比较，更有一种使用轮子的感觉，导致对于各种bug的处理可能不是那么的完美。不过也得益于C++的基础，在短时间内至少能够使用Java来完成所给的任务了。

## 阶段一

​	阶段一主要就是学习Java语言并配置Java的环境，除了Java jdk之外，还需要ant(类似cmake，一键打包)，Junit(进行测试)，以及SonarQube(检测代码规范)。

​	其中，使用ant需要编写一个build.xml，在里面添加各项如init、compile、jar的工作。而Junit则需要编写一个test类的java文件。它们都既能在命令行下直接运行，也可以通过ide来运行，但一开始最好还是先学习如何在命令行下运行，这样更能够理解它们背后的运行机制。

​	SonarQube是用来检测代码规范的，使用过SonarQube之后我才发现自己代码里一些不规范的部分，这些都是以前没有注意到的，而且也是可以改正的。不过，我也感觉SonarQube中有些地方并不是那么正确。

## 阶段二

​	阶段二就是正式开始用Java编写程序了，这个阶段分为四个部分，由浅入深的让我理解了GridWorld这个类(也是应用)，并且完善了它，也让我深刻的认识到为什么Java是一个完全的面向对象的语言。

​	这个阶段可能不是最难的，但一定是内容最多的，尤其是问答题，其实我觉得问答题有些过于多了，基本上不用回答全部就已经对这个阶段有很透彻的理解了。

​	这一阶段还对数据结构部分有所介绍，在实现呈现地图的Grid类时，要在多种数据结构中进行取舍，这时就要用到复杂度估计了，最好的情况就是选择到一个综合各方面的复杂度都较低的结构来实现。

## 阶段三

​	阶段三是最后一个阶段，称为扩展任务。实际上这一阶段相比较第二阶段来说，任务轻了一些。这一阶段并不需要那么多的coding，反而需要思考的部分更多些。

​	阶段三有三个任务：图像处理、迷宫和拼图。图像处理是要用二进制流读取图像、并进行处理(读取色彩通道、转成灰度图像等)，最后保存图像。只要弄懂了bitmapImage文件的结构，问题就迎刃而解了。图像处理部分还需要用Junit进行测试，这里我借鉴了junit官方在github上的参数测试的例子：[Parameterized-test。](https://github.com/junit-team/junit4/wiki/Parameterized-tests)参数测试可以很方便的加入多个要进行测试的图片。

​	迷宫，MazeBug，则需要用到深度优先搜索算法，它是沿着树的深度遍历树的节点，尽可能深的搜索树的分支(有路就走)，当节点的所有边都探寻过，搜索就回溯到发现节点的那条边的起始节点(转向重来)，直到发现终点为止。对于迷宫这个任务可以优化虫子的路径选择，于我来说，如果虫子先前对于某个方向选择的次数最多，且恰好这次又能够选择这个方向，那它就会选择这个方向；如果虫子对于多个方向选择的次数一样多呢？此时就随机选择了，实践证明这样确实能够优化完成的步数。

​	最后一个任务是拼图-Npuzzle，N-数码问题。源文件代码量挺多，初看起来容易让人晕了头，不过只要耐着性子看下来，还是能够理清思路的。代码由四个类，JigsawNode、Jigsaw、Solution和演示部分，需要完成的就是Solution类中的两个方法：广度优先搜索和启发式搜索。

​	广度优先搜索是一种盲目搜索策略，它每次都尽可能"广"地搜索每一个节点的邻接点，来找到从源节点到目标节点的最短路径。启发式搜索则优化了一些，它使用一个估价函数来动态地确定搜索节点的排序，以减少搜索范围、降低问题复杂度。可以使用多个估价函数，但这是各个方法的权重就不是那么容易确定的了。有一个方法是用多重循环来找到较优的解，但这样复杂度较高，运行的很慢。

# 总结

​	总的来说，这次实训还是学到了很多东西，也学得比较浅显，还是需要多加练习来巩固这些知识。毕竟打码无它，唯手熟尔。