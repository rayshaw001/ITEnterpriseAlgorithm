

import random
import copy
import os

mul=3
max_results=11
matrix=36
keys=[]
for i in [1,2]:
    for j in [1,2,3]:
        keys.append((i,j))
        
values=[]
for k in range(1,mul+1):
    for i in [1,2]:
        for j in [1,2,3]:
            values.append((i,j))
results=[]
while len(results)!=max_results:
    maps=dict.fromkeys(copy.copy(keys),values)
    for item in keys:
        maps.update({item:copy.copy(values)})
    key=(random.randint(0,1),random.randint(0,2))
    result=[]
    while len(result)!=matrix*mul:
        value=maps.get(key,[])
        if len(value)==0:
            break
        r=random.randint(0,len(value)-1)
        result.append((key,value[r]))
        key=value[r]
        del value[r]
    if len(result)==matrix*mul and results.count(result)==0:
        results.append(result)
  
updated_result=[]
updated_result_seq=[]

for item_list in results:
    tmp=(random.randint(1,3),random.randint(1,2))
    updated_result=[]
    for item in item_list:
        rel=(random.randint(1,3),random.randint(1,2))
        while rel[0]==tmp[0]:
            rel=(random.randint(1,3),random.randint(1,2))
            
        updated_result.append(((item[0][0],item[0][1],tmp[0],tmp[1]),(item[1][0],item[1][1],rel[0],rel[1])))
        tmp=rel
    updated_result_seq.append(updated_result)
    
if os.listdir('./').count('randomData')==0:
    os.mkdir("randomData")
count=0
dict_CI={1:'C',2:'I'}
dict_fs={1:'f',2:'fs',3:'s'}
for res in updated_result_seq:
    count=count+1
    f1=open('./randomData/data{}.txt'.format(count),'w+')
    f2=open('./randomData/data_single{}.txt'.format(count),'w+')
    for r in res:
        print >> f1 ,"{}    {}{}{}{}".format(r,dict_fs[r[0][1]],dict_CI[r[0][0]],dict_fs[r[1][1]],dict_CI[r[1][0]])
        print >> f2 ,"{}    {}{}".format(r[0],dict_fs[r[0][1]],dict_CI[r[0][0]])
    f1.close()
    f2.close()
