# Leet mock interview
from collections import defaultdict
from typing import List


def network_delay_time(times: List[List[int]], n: int, k: int) -> int:
    visited = {k: 0}
    edges = defaultdict(list)
    for t in times:
        edges[t[0]].append((t[1], t[2]))
    queue = [k]
    while queue:
        node = queue.pop(0)
        for edge in edges[node]:
            time = visited[node] + edge[1]
            if edge[0] not in visited or time < visited[edge[0]]:
                visited[edge[0]] = time
                queue.append(edge[0])
    return max(visited.values()) if len(visited) == n else -1
