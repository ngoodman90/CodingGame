def permutate(objs):
    if len(objs) == 1:
        return [objs]
    acc = []
    for i in range(len(objs)):
        acc.extend([[objs[i]] + o for o in permutate(objs[:i] + objs[i+1:])])
    return acc
