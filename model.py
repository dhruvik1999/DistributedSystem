import numpy as np 
import random 
import math

x = np.array(range(149),'float64')


def activation_softmax(L):
	su = 0
	L = np.array(L,'float32')
	for i in range(len(L)):
		L[i] = np.exp(L[i])
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
l2 = activation_softmax(np.array(np.dot(l1,w2),'float64') + b2)

for i in l2:
	print(round(i,6),end=' ')
print()


