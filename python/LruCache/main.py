# https://leetcode.com/problems/lru-cache/
class Node:
    def __init__(self, key, value, prev_node=None, next_node=None):
        self.key = key
        self.value = value
        self.prev_node = prev_node
        self.next_node = next_node


class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.cache_dict = dict()
        self.cache_size = 0
        self.head_node = None
        self.last_node = None

    def get(self, key: int) -> int:
        curr_node = self.cache_dict.get(key)
        if curr_node is None:
            return -1
        if curr_node is not self.head_node:
            if curr_node is self.last_node:
                self.last_node.prev_node.next_node = None
                self.last_node = self.last_node.prev_node
            else:
                curr_node.prev_node.next_node = curr_node.next_node
                curr_node.next_node.prev_node = curr_node.prev_node
            self.head_node.prev_node = curr_node
            curr_node.next_node = self.head_node
            self.head_node = curr_node
        return curr_node.value

    def put(self, key: int, value: int) -> None:
        curr_node = self.cache_dict.get(key)
        if curr_node is not None:
            if curr_node is self.head_node and curr_node is self.last_node:
                self.head_node = self.last_node = None
            elif curr_node is self.head_node:
                self.head_node = self.head_node.next_node
                self.head_node.prev_node = None
            elif curr_node is self.last_node:
                self.last_node = self.last_node.prev_node
                self.last_node.next_node = None
            else:
                curr_node.prev_node.next_node = curr_node.next_node
                curr_node.next_node.prev_node = curr_node.prev_node
            self.cache_size -= 1

        new_node = Node(key, value)
        self.cache_dict[key] = new_node

        if self.cache_size == 0:
            self.head_node = new_node
            self.last_node = new_node
        else:
            new_node.next_node = self.head_node
            self.head_node.prev_node = new_node
            self.head_node = new_node

        self.cache_size += 1

        if self.cache_size > self.capacity:
            self.cache_dict[self.last_node.key] = None
            self.last_node = self.last_node.prev_node
            self.last_node.next_node = None
            self.cache_size -= 1
