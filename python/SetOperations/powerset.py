def powerset(objs, acc):
    if len(objs) == 1:
        acc += [[], objs]
    else:
        powerset(objs[1:], acc)
        acc += [[objs[0]] + o for o in acc]

def min_or_max(i, heights):
    if i == 0 or i == len(heights) - 1 or len(heights) < 3:
        return True
    if heights[i - 1] == heights[i] and heights[i] == heights[i + 1]:
        return False
    return (heights[i - 1] <= heights[i] and heights[i] >= heights[i + 1]) or \
           (heights[i - 1] >= heights[i] and heights[i] <= heights[i + 1])


def largestRectangleArea(heights) -> int:
    max_rec = 0
    for i in range(len(heights)):
        if not min_or_max(i, heights):
            continue
        left_min = heights[i]
        left_max = heights[i]
        l = i
        while l > 0 and min(left_min, heights[l - 1]) * (i - l + 2) > left_max:
            left_min = min(left_min, heights[l - 1])
            left_max = left_min * (i - l + 2)
            l -= 1
        right_min = heights[i]
        right_max = heights[i]
        r = i
        while r < len(heights) - 1 and min(right_min, heights[r + 1]) * (r - i + 2) > right_max:
            right_min = min(right_min, heights[r + 1])
            right_max = right_min * (r - i + 2)
            r += 1
        print(l, r, left_max, right_max, min(left_min, right_min) * (r - l + 1))
        local_max = max(left_max, right_max, min(left_min, right_min) * (r - l + 1))
        if local_max > max_rec:
            max_rec = local_max

    return max_rec

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

def trees_equal(p, q):
    if p is None and q is None:
        return True
    if p is None or q is None:
        return False
    if p.val != q.val:
        return False
    if p.left is not None or q.left is not None:
        if not trees_equal(p.left, q.left):
            return False
    if p.right is not None or q.right is not None:
        return trees_equal(p.right, q.right)
    return True

def largestTimeFromDigits(A) -> str:
    t = []
    options_for_two = range(3, -1, -1)
    if 2 in A:
        for o in options_for_two:
            if o == 2:
                if A.count(2) >= 2:
                    t = [2, 2]
                    A.remove(2)
                    A.remove(2)
                    break
            elif o in A:
                t = [2, o]
                A.remove(2)
                A.remove(o)
                break
    if not t and 1 in A:
        t = [1]
        A.remove(1)
        m = max(A)
        t.append(m)
        A.remove(m)
    if not t and 0 in A:
        t = [0]
        A.remove(0)
        m = max(A)
        t.append(m)
        A.remove(m)
    if not t:
        return ""

    minutes_options = range(5, -1, -1)
    for o in minutes_options:
        if o in A:
            t.append(o)
            A.remove(o)
            t.append(A[0])
            A.pop()
    if A:
        return ""
    return f"{t[0]}{t[1]}:{t[2]}{t[3]}"

