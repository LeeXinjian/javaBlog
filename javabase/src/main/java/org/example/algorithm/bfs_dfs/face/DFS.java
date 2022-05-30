package org.example.algorithm.bfs_dfs.face;

import org.example.algorithm.bfs_dfs.util.InitParmas;

public interface DFS {

    void DFSWay(Object... params);

    default void DFSWayRun(InitParmas parmas) {
        DFSWay(parmas.getParmas());
    }

}
