# https://leetcode.com/problems/lru-cache/
class Node:
    def __init__(self, key, value, prev_node=None, next_node=None):
        self.key = key
        self.value = value
        self.prev_node = prev_node
        self.next_node = next_node

    def move_to_head(self, head, tail):
        if self is head:
            return head, tail

        if self is tail:
            tail = tail.prev_node
            tail.next_node = None
        else:
            self.prev_node.next_node = self.next_node
            self.next_node.prev_node = self.prev_node
        self.next_node = head
        head.prev_node = self
        return self, tail


class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.cache_dict = dict()
        self.cache_size = 0
        self.head_node = None
        self.tail_node = None

    def get(self, key: int) -> int:
        curr_node = self.cache_dict.get(key)
        if curr_node is None:
            return -1
        self.head_node, self.tail_node = curr_node.move_to_head(self.head_node, self.tail_node)
        return curr_node.value

    def put(self, key: int, value: int) -> None:
        curr_node = self.cache_dict.get(key)
        if curr_node is not None:
            curr_node.value = value
            self.head_node, self.tail_node = curr_node.move_to_head(self.head_node, self.tail_node)
        else:
            new_node = Node(key, value)
            self.cache_dict[key] = new_node

            if self.cache_size == 0:
                self.head_node = new_node
                self.tail_node = new_node
            else:
                new_node.next_node = self.head_node
                self.head_node.prev_node = new_node
                self.head_node = new_node

            self.cache_size += 1

            if self.cache_size > self.capacity:
                self.cache_dict[self.tail_node.key] = None
                self.tail_node = self.tail_node.prev_node
                self.tail_node.next_node = None
                self.cache_size -= 1
