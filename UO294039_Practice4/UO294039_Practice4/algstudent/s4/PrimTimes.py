import Helper
import Prim
import time

def main():
    size = 256

    for i in range(100):
        graph = Helper.triangularMatrixRandomIntegers(size,100,999)
        start = time.time()
        Prim.Prim(graph)
        end = time.time()
        print('Size: ',size, " - Time: ", end - start)
        size *=2
    
    return 0

m = main()

