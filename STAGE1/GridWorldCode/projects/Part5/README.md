# README

这是实训的Part5部分，共三个项目分为三个文件夹。

1. 包含SparseBoundedGrid.java文件，用ArrayList + LinkedList实现。
2. 包含SparseBoundedGrid2.java与SparseBoundedGrid3.java，分别使用HashMap和TreeMap实现。
3. 包含UnboundedGrid2.java文件，是用数组来实现可扩增的UnboundedGrid。

根目录下有SparseGridRunner.java文件，运行前需要先编译三个文件夹中的代码。

几种Grid的复杂度可供参考：

| Methods                      | SparseGridNode version | LinkedList<OccupantInCol> version | HashMap version | TreeMap version |
| ---------------------------- | ---------------------- | --------------------------------- | --------------- | --------------- |
| getNeighbors                 | O(c)                   | O(c)                              | O(1)            | O(logn)         |
| getEmptyAdjacentLocations    | O(c)                   | O(c)                              | O(1)            | O(logn)         |
| getOccupiedAdjacentLocations | O(c)                   | O(c)                              | O(1)            | O(logn)         |
| getOccupiedLocations         | O(n)                   | O(n)                              | O(n)            | O(n)            |
| get                          | O(c)                   | O(c)                              | O(1)            | O(logn)         |
| put                          | O(c)                   | O(c)                              | O(1)            | O(logn)         |
| remove                       | O(c)                   | O(c)                              | O(1)            | O(logn)         |