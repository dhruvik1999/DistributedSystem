
static final mx = 100000000;
int individualTime(int sys, int start, int end)
{
	return time;	
}

int getTime(double p1, double p2, double p3)
{
	int number = 100000000;
	int left = number, start = 1;
	int time[] = new time[4];
	for(int i=0;i<4;i++)
		time[i] = mx;
	if(left > 0 && p1 != 0)
	{
		int end = (int)(p1*number);
		time[0] = individualTime(0, start, start + end-1);
		left -= end;
		start += end;
	}
	if(left > 0 && p2 != 0)
	{
		int end = (int)(p1*number);
		time[1] = individualTime(1, start, start + end-1);
		left -= end;
		start += end;
	}
	if(left > 0 && p3 != 0)
	{
		int end = (int)(p1*number);
		time[2] = individualTime(2, start, start + end-1);
		left -= end;
		start += end;
	}
	if(left > 0)
	{
		int end = (int)(p1*number);
		time[3] = individualTime(3, start, number);
		left -= end;
		start += end;
	}
	int mxTime = 0;
	for(int i=0;i<4;i++)
		mxTime = Math.max(mxTime, time[i]);
	return mxTime;

}



double p1, p2;
int mntime = mx;
for(p1=0;p1<=100;p1+=0.1)
{
	for(p2=p1;p2<=100;p2+=0.1)
	{
		double lo = p2, hi = 100, m1, m2, time1, time2;
		m1 = lo+(hi-lo)/3, m2 = lo+2*(hi-lo)/3;
		if(m2-m1<=0.1)
		{
			time1 = getTime(p1, p2-p1, m1-p2);
			mntime = Math.min(mntime, time1);
		}
		while(m2-m1 > 0.1)
		{
			time1 = getTime(p1, p2-p1, m1-p2);
			time2 = getTime(p1, p2-p1, m2-p2);
			if(time1 > time2)
			{
				lo = m1;
			}
			else{
				hi = m2;
			}
		}
		mntime = Math.min(mntime, time1);
	}

}
return mntime;