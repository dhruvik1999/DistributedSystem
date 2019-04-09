f=open("top.txt", "r")
a=[]
f1=f.readlines()
for i in f1:
	aa=i.split()
	for j in aa:
		a.append(j)
a=a[3:]
f.close()
f=open("result.txt","a")
for i in a:
	temp = i + ", "
	f.write(temp)
f.write("\n")
f.close()

