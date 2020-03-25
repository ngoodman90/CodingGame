from collections import namedtuple
from queue import Queue
from typing import Optional

Group = namedtuple('Group', ['id', 'size'])
MemoisedTurn = namedtuple('MemoisedTurn', ['turn_index', 'current_earnings'])


def _get_profits(cart_size: int, num_of_turns: int, roller_coaster_queue: Queue) -> int:
    memo = dict()
    earnings = 0
    curr_group: Optional[Group] = None
    current_turn = 0
    while current_turn < num_of_turns:
        if curr_group is None:
            curr_group = roller_coaster_queue.get()
        first_group = curr_group
        memoised_first_group = memo.get(first_group.id)
        if memoised_first_group is not None \
                and current_turn + current_turn - memoised_first_group.turn_index <= num_of_turns:
            turns_that_passed = current_turn - memoised_first_group.turn_index
            earnings_diff = earnings - memoised_first_group.current_earnings
            while current_turn + turns_that_passed <= num_of_turns:
                earnings += earnings_diff
                current_turn += turns_that_passed
        else:
            num_of_riders_in_cart = 0
            current_ride = []
            while num_of_riders_in_cart < cart_size:
                if curr_group is None:
                    curr_group = roller_coaster_queue.get()
                if num_of_riders_in_cart + curr_group.size <= cart_size:
                    current_ride.append(curr_group)
                    curr_group = None
                else:
                    break
                num_of_riders_in_cart = sum([group.size for group in current_ride])
                if roller_coaster_queue.empty():
                    break
            for cr in current_ride:
                roller_coaster_queue.put(cr)
            memo[first_group.id] = MemoisedTurn(current_turn, earnings)
            earnings += num_of_riders_in_cart
            current_turn += 1
    return earnings


def main():
    roller_coaster_queue = Queue()
    l, c, n = [int(i) for i in input().split()]
    for i in range(n):
        roller_coaster_queue.put(Group(i, int(input())))
    return _get_profits(l, c, roller_coaster_queue)


if __name__ == "__main__":
    print(main())
