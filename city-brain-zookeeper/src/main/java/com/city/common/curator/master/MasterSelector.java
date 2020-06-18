package com.city.common.curator.master;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

public class MasterSelector {

    private final static String CONNECTSTRING = "192.168.56.1:2181";
    private final static String MASTER_PATH = "/curator_master_path";

    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECTSTRING).
                retryPolicy(new ExponentialBackoffRetry(1000,3)).build();

        LeaderSelector leaderSelector = new LeaderSelector(curatorFramework, MASTER_PATH, new LeaderSelectorListener() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.out.println("获得leader成功");
                TimeUnit.SECONDS.sleep(2);
            }

            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

            }
        });
        leaderSelector.autoRequeue();
        //开始选举
        leaderSelector.start();
    }
}
