import sys

def search(grid, rc, word, path):
    if word == "": return path
    if rc not in grid.keys() or rc in path or word[0] not in [grid[rc], '?']: return []
    return [p for r in [-1,0,1] for c in [-1,0,1]
              for p in search(grid, (rc[0]+r, rc[1]+c), word[1:], [rc]+path)]

with open(sys.argv[1], "r") as f: grid_s, word_s = f.read().split("\n\n")
grid = dict([((row,col),ch) for row, line in enumerate(grid_s.split("\n"))
                            for col, ch in enumerate(line)])
mark = [path for word in map ((lambda word: word.strip().upper()), word_s.split(","))
             for rc in grid.keys()
             for path in search(grid, rc, word, [])]

print "\n".join([" ".join([ch if (row,col) in mark else '.' for col,ch in enumerate(line)])
        for row,line in enumerate(grid_s.split("\n"))])
