# TheGoldMiner
参考视频 https://www.bilibili.com/video/BV1454y1J7DZ?p=26

修正了视频中的一些bug，例如state的值可能为null，state使用枚举，药水可以反复购买，初始化时钩爪有几率碰到金块或石块导致黑屏等。

*积分可以为负数，不是bug

UI使用swing AWT制作

游戏规则：

1.点击鼠标右键以开始游戏。游戏中点击鼠标左键伸出钩爪抓取。

2.拿到石头积一分，小金块两份，中金块4分，大金块8分。不同的物体会导致绳子收回速度不同。其中石头最慢。

3.在收回过程中点击鼠标右键可以使用药水。如果钩爪上是石头，则直接扔掉，如果是金块，则加快收绳子的速度。

4.每一关达到相应要求后进入下一关，其间可以使用积分购买药水，每关结束后药水价格不同，左键购买，右键继续游戏。

5.所有关卡结束后进入成功界面，反之进入失败界面。在成功、失败界面点击鼠标左键重置游戏。
