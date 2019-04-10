import numpy as np 
import random 
import math
inp = open('input.txt','r')
inp = inp.read()
inp = inp.split(',')
inp.pop()
inp.pop()

#print(inp)

x = [float(i) for i in inp]
x = np.array(x,'float64')
#print(x.shape)

def activation_softmax(L):
	su = 0
	L = np.array(L,'float32')
	MX = np.max(L)
	for i in range(len(L)):
		L[i] = np.exp(L[i]/MX)
		su += L[i]
	for i in range(len(L)):
		L[i]/=su
	return L

def activation_relu(L):
	L = np.array(L,'float64')
	for i in range(len(L)):
		L[i] = max(L[i],0)
	return L

w1= np.array(np.load('w1.npy'),'float64')
w2 = np.array(np.load('w2.npy'),'float64')
b1= np.array(np.load('b1.npy'),'float64')
b2 = np.array(np.load('b2.npy'),'float64')
l1 = activation_relu(np.array(np.dot(x,w1),'float64') + b1)
#print(np.array(np.dot(l1,w2),'float64'))

l2 = activation_softmax(np.array(np.dot(l1,w2),'float64') + b2)
#print(l2)
for i in l2:
	print(round(i,6),end=' ')
fil = open('output.txt','w')
for i in l2:
	fil.write(str(i)+'\n')
print()


