arr = [1, 2, 8, 3, 4, 5, 8, 9]
for i in range(0,len(arr)-1):
    swapped = False
    for j in range(0,len(arr)-i-1): 
        if(arr[j]>arr[j+1]):
            temp = arr[j]
            arr[j] = arr[j+1]
            arr[j+1] = temp
            swapped =  True
    if(not swapped):
        break
    print(arr)
print(arr)