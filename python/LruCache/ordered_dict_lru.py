from collections import OrderedDict


class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.cache_size = 0
        self.cache = OrderedDict()

    def get(self, key: int) -> int:
        val = self.cache.get(key)
        if val is None:
            return -1
        del self.cache[key]
        self.cache[key] = val
        return val

    def put(self, key: int, value: int) -> None:
        existing_val = self.cache.get(key)
        if existing_val is not None:
            del self.cache[key]
            self.cache_size -= 1
        self.cache[key] = value
        self.cache_size += 1
        if self.cache_size > self.capacity:
            self.cache.popitem(last=False)
            self.cache_size -= 1
