package com.city.common.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

public class CuratorEventDemo {

    public static void main(String[] args) throws Exception {

        /**
         * 三种 watcher 来做节点的监听
         * pathcache 监视一个路径下节点创建、删除、更新
         * NodeCache 监视一个节点的创建、更新、删除
         * TreeCache pathcache + NodeCache 的合体（监视路径下的创建、更新、删除事件）
         * 缓存路径下的所有子节点的数据
         */

        CuratorFramework curatorFramework = CuratorClientUtils.getInstance();

        /**
         * 节点变化
         */
        ///NodeCache:监视路径、false：缓存:路径是否做压缩
        NodeCache cache = new NodeCache(curatorFramework,"/NodeCache",false);
        cache.start(true);
        //增加事件
        cache.getListenable().addListener(()->System.out.println("节点数据发生变化，变化后的结果"+
        "："+new String(cache.getCurrentData().getData())));
        curatorFramework.setData().forPath("/NodeCache","123".getBytes());
        System.in.read();

        /**
         * PathChildren
         */
        PathChildrenCache childrenCache = new PathChildrenCache(curatorFramework,"event",true);
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        childrenCache.getListenable().addListener((curatorFramework1,pathChildrenCacheEvent)->{
            switch (pathChildrenCacheEvent.getType()){
                case CHILD_ADDED:
                    System.out.println("添加子节点");
                    break;
                case CHILD_REMOVED:
                    System.out.println("删除子节点");
                    break;
                case CHILD_UPDATED:
                    System.out.println("更新子节点");
                    break;
                default:break;
            }
        });

        curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/event","event".getBytes());
        TimeUnit.SECONDS.sleep(1);

        curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/event/event-1","1".getBytes());
        TimeUnit.SECONDS.sleep(1);

        curatorFramework.setData().forPath("/event/event-1","2".getBytes());
        TimeUnit.SECONDS.sleep(1);

        curatorFramework.delete().forPath("/event/event-1");
        System.in.read();

    }
}
