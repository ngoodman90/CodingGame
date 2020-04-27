# https://leetcode.com/problems/lru-cache/
class Node:
    def __init__(self, key, value, prev_node=None, next_node=None):
        self.key = key
        self.value = value
        self.prev_node = prev_node
        self.next_node = next_node


class LinkedList:
    def __init__(self, head=None, tail=None):
        self.head = head
        self.tail = tail

    def move_to_head(self, node):
        if node is self.head:
            return

        if node is self.tail:
            self.tail = self.tail.prev_node
            self.tail.next_node = None
        elif node.prev_node is not None and node.next_node is not None:
            node.prev_node.next_node = node.next_node
            node.next_node.prev_node = node.prev_node

        node.next_node = self.head
        self.head.prev_node = node
        self.head = node

    def remove_tail(self):
        self.tail = self.tail.prev_node
        self.tail.next_node = None


class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.cache_dict = dict()
        self.cache_size = 0
        self.ll = LinkedList()

    def get(self, key: int) -> int:
        curr_node = self.cache_dict.get(key)
        if curr_node is None:
            return -1
        self.ll.move_to_head(curr_node)
        return curr_node.value

    def put(self, key: int, value: int) -> None:
        curr_node = self.cache_dict.get(key)
        if curr_node is not None:
            curr_node.value = value
            self.ll.move_to_head(curr_node)
        else:
            new_node = Node(key, value)
            self.cache_dict[key] = new_node

            if self.cache_size == 0:
                self.ll.head = new_node
                self.ll.tail = new_node
            else:
                self.ll.move_to_head(new_node)

            self.cache_size += 1

            if self.cache_size > self.capacity:
                self.cache_dict[self.ll.tail.key] = None
                self.ll.remove_tail()
                self.cache_size -= 1
