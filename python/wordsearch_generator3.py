# for python3
import string
import random

width = 12
height = 12

def put_word(word,grid):
    word = random.choice([word,word[::-1]])

    d = random.choice([[1,0],[0,1],[1,1]])
    xsize = width  if d[0] == 0 else width  - len(word)
    ysize = height if d[1] == 0 else height - len(word)

    x = random.randrange(0,xsize)
    y = random.randrange(0,ysize)

    print([x,y])

    for i in range(0,len(word)):
        grid[y + d[1]*i][x + d[0]*i] = word[i]
    return grid

grid = [[random.choice(string.ascii_uppercase) for i in range(0,width)] for j in range(0,height)]

for word in ["HELLO", "THERE", "AGAIN"]:
    grid = put_word(word, grid)

print("\n".join(map(lambda row: " ".join(row), grid)))

