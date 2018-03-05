import sys
def permutliste(seq, er=False, k=0):
    n = len(seq)
    if k == n-1:
        return []
    p = []
    z = seq[:]
    for c in range(0, n-k):
        if er==False or (z not in p):
            p.append(z[:])
            p.extend(permutliste(z, er, k+1)[1:])
        z.append(z.pop(k))
    return p

def permutchaine(ch, er=False):
    return [''.join(z) for z in permutliste(list(ch), er)]

def gen_combine(taille):
    ret = []
    x = taille
    y = 0
    for i in range(taille + 1):
        s = ''
        for j in range(x):
            s = s + '+'
        for j in range(y):
            s = s + '-'
        ret.append(s)
        x = x - 1
        y = y + 1
    return ret

def popi(tab, operations):
    tabici = []
    for i in tab:
        tabici.append(i)
    x = tabici.pop(0)
    for i in operations:
        y = tabici.pop(0)
        if i == '+':
            x = x + y
        else:
            x = x - y
    return x

if __name__ == '__main__':
    chaine = sys.argv[1]
    print(chaine)
    tab = chaine.split('0')

    while '' in tab:
        tab.remove('')

    tab_num = []
    for i in tab:
        tab_num.append(len(i))

    taille_op = len(tab_num) - 1

    op = gen_combine(taille_op)
    liste_combi = []
    for i in op:
        for j in permutchaine(i, True):
            liste_combi.append(j)
    res = []
    for i in liste_combi:
        res.append(popi(tab_num, i))

    ret = 99999
    for i in res:
        if i > 0 and i < ret:
            ret = i
    print(ret)
