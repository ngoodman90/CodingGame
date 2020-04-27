# https://leetcode.com/problems/lfu-cache/
from collections import OrderedDict, defaultdict


class LRUCache:
    def __init__(self):
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
        self.cache[key] = value

    def delete_last(self):
        return self.cache.popitem(last=False)[0]


class LFUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.cache_size = 0
        self.caches = defaultdict(LRUCache)
        self.num_of_calls = dict()

    def get(self, key: int) -> int:
        num_of_calls = self.num_of_calls.get(key)
        if num_of_calls is None:
            return -1
        cache = self.caches[num_of_calls].cache
        value = cache.get(key)
        del cache[key]
        if len(self.caches[num_of_calls].cache) == 0:
            del self.caches[num_of_calls]
        num_of_calls += 1
        self.num_of_calls[key] = num_of_calls
        self.caches[num_of_calls].put(key, value)
        return value

    def put(self, key, value):
        num_of_calls = self.num_of_calls.get(key)
        if num_of_calls is not None:
            self.get(key)
            self.set(key, value)
            return
        if self.cache_size > 0 and self.cache_size == self.capacity:
            min_calls = 1
            while self.caches.get(min_calls) is None:
                min_calls += 1
            deleted_key = self.caches[min_calls].delete_last()
            del self.num_of_calls[deleted_key]
            self.cache_size -= 1
            if len(self.caches[min_calls].cache) == 0:
                del self.caches[min_calls]
        if self.cache_size < self.capacity:
            num_of_calls = 1
            self.num_of_calls[key] = num_of_calls
            self.caches[num_of_calls].put(key, value)
            self.cache_size += 1

    def set(self, key, value):
        num_of_calls = self.num_of_calls[key]
        self.caches[num_of_calls].put(key, value)
