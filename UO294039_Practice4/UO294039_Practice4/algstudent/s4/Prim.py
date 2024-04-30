import Helper

def Prim(m):
    visited = [0]
    nodes = len(m)
    weights = [0]
    visited_nodes = [False] * nodes
    visited_nodes[0] = True
    while(len(visited) < nodes):
        min_weight = float('inf')
        min_pos = -1
        for i in range (len(visited)):
            weight, position = minimum(m,visited,i,visited_nodes)
            if(weight < min_weight):
                min_weight = weight
                min_pos = position
        weights.append(min_weight)
        visited.append(min_pos)
        visited_nodes[min_pos] = True
    return weights,visited

def minimum(matrix, visited,position,visited_nodes):
    min_weight = float('inf')
    min_pos = -1
    node = visited[position]
    for i in range(len(matrix)):
        weight = matrix[i][node]
        if(weight < min_weight and weight != 0 and not visited_nodes[i]):
            min_weight = matrix[i][node]
            min_pos = i
        weight = matrix[node][i]
        if(weight < min_weight and weight != 0 and not visited_nodes[i]):
            min_weight = matrix[node][i]
            min_pos = i

    return min_weight,min_pos

#print(Prim(Helper.triangularMatrixFromFile("graph8.txt")))