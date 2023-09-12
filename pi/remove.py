f = open("del.txt", "r", encoding="utf-8")
g = open("corrected.txt", "a", encoding="utf-8")

for line in f.readlines():
    newLine = ""
    for c in line:
        if c == "-":
            break
        
        if c not in "+-*[]{}()":
            newLine += c 
        else: 
            newLine +=  " "
    g.write(newLine)


g.close()

f.close()