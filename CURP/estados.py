class WordDictionary:

    def __init__(self):
        self.root = {}
    def __repr__(self):
        return f"{self.root}"
    def addWord(self, word: str) -> None:
        cur = self.root
        for c in word:
            cur = cur.setdefault(c,{})
        cur["!"] = {} 

    def search(self, word: str) -> bool:
        return self.dfs(0,word,self.root)
    # def dfs(self, j,word, root):
    #     cur = root
    #     i = j
    #     for c in word[i:]:
    #         if c ==".":
    #             for d in cur:
    #                 if self.dfs(i+1,word,cur[d]):
    #                     return True
    #                 # return False
    #         if c not in cur:
    #             return False
    #         cur = cur[c]
    #         i+=1
            
        # return "!" in cur
    

    def pruneHelper(self, curRoot):
        if type(curRoot) != dict:
            return None
        root = curRoot
        if len(root) == 1:
            for branch in root:
                root[branch] = "!"
                # root[root[branch]] = {"!":[]}
        else:
            for branch in root:
                self.pruneHelper(root[branch])
    def prune(self):
        self.pruneHelper(self.root)

    

    def print(self):
        self.printHelper(self.root, 0)

    def printHelper(self, curRoot, indentationLevel):
        tabs = "  " * indentationLevel
        root = curRoot
        if len(root) == 1:
            for branch in root:
                print(tabs + branch)
                # print()
        else:
            for branch in root:
                print(tabs + branch)
                self.printHelper(root[branch], indentationLevel + 1)

    def genSwitch(self):
        self.genSwitchHelper(self.root,0)
    def genSwitchHelper(self, curRoot, indentationLevel):
        tabs = "\t" * indentationLevel * 2
        switchVar = "i" * (indentationLevel + 1)
        root = curRoot
        print(tabs  + f"switch ({switchVar})" + "{")
        # tabs += "\t"
        tabs += "\t"
        if len(root) == 1:
            
            for branch in root:
                print(tabs + "case " + f"\"{branch}\" :" + "{")
                print(tabs + "\t"+ "break;")
                print(tabs + "\t" + "}")
                print(tabs + "default : {return dummy;}", end="")
                # print()
        else:
            for branch in root:
                print(tabs + "case " + f"\"{branch}\" :" + "{")
                # print(tabs + "{")
                self.genSwitchHelper(root[branch], indentationLevel + 1)
                print(tabs + "\t" + "}")
        print("}")

f = open("estados.txt", "r")
estados = []
for line in f.readlines():
    estados.append(line.upper())
f.close()


minKeys = WordDictionary()
for estado in estados:
    minKeys.addWord(estado)
# minKeys.print()
minKeys.prune()
# print(minKeys)
# minKeys.print()
minKeys.genSwitch()