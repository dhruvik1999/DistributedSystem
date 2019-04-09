#replace 10 with number of lines you want in the output file
x=''
	top -b -n 1 > top.txt
	sed -n -i '1,5p' top.txt
	sed -i 's/[a-zA-Z]//g' top.txt
	sed  -i 's/[-://%(),]//g' top.txt 
	sed -i '5s/[.]//' top.txt
	sed -i -E "s/[[:space:]]+/ /g" top.txt 
	python3 script.py 
	x=`cat result.txt`
	rm result.txt
echo $x