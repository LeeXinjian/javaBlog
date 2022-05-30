package org.example.algorithm.bfs_dfs.face;

import org.example.algorithm.bfs_dfs.util.InitParmas;

public interface BFS {

    void BFSWay(Object... params);

    default void BFSWayRun(InitParmas parmas) {
        BFSWay(parmas.getParmas());
    }
}
