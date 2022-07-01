import java.util.*;

public class Mars
{        
	private static List<List<Integer>> blind = new ArrayList<List<Integer>>();
	private static List<Integer> result = new ArrayList<Integer>();
	private static List<Integer> free = new ArrayList<Integer>();
	private static List<Integer> finds = new ArrayList<Integer>();
	private static int N;
	
	public static boolean isBig(List<Integer> c,int x)
	{	
		int flag=0;
			if(blind.get(blind.size()-1).get(1) <=x )flag=1;
		if(count(c.get(1))-count(c.get(0))==1 && flag==0) return false;
		else return true;
	}
	public static int root(int x, int m)
	{	
		int y=-1;
		for(List<Integer> c : blind)
		{
			if(c.contains(x) && !c.contains(m)){
				if(c.get(0)==x) y=c.get(1);
				else y=c.get(0);
				break;}
		}
		for(List<Integer> c : blind)
		{
			if(c.contains(y) && !c.contains(x) && !result.contains(c.get(0)) && !result.contains(c.get(1))){
				if(c.get(0)==y) return c.get(1);
				else return c.get(0);}
		}
		return -1;
	}
	public static boolean find(int x)
	{
		for(int c : free)
			if(c+1==x && !finds.contains((c+1))){ finds.add(c+1); return true;}
		return false;
	}
	public static boolean isBlind(int x)
	{
		for(int c : result)
			if (blind.contains(Arrays.asList(c,x)) ||
				blind.contains(Arrays.asList(x,c))) return false;
		return true;
	}
	public static boolean check(int x)
	{	
		boolean res=true;
		for(List<Integer> c : blind)
		{
			if(c.contains(x)){
				if(c.get(0)!=x) res=res&&isBlind(c.get(0));
				else res=res&&isBlind(c.get(1));}
		}
		return res;
	}
	public static int count(int x)
	{
		int k=0;
		for(List<Integer> c : blind)	
			if(c.contains(x)) ++k;
		return k;
	}
	public static boolean isFree(List<List<Integer>> blind, int x)
	{	
		for(List<Integer> c : blind)
			if(c.contains(x)) return true;
		return false;
	}
	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		N=in.nextInt();
		int f=0;
		char[][] M=new char[N][N];
		for(int i=0;i<N;++i)
		{	
			int flag=0;
			for(int j=0;j<N;++j)
			{
				M[i][j]=in.next().charAt(0);
				if(j>i && M[i][j]=='+')
					blind.add(Arrays.asList(i+1,j+1));
			}
		}
		for(int i=1;i<N+1;++i)
			if(!isFree(blind,i)) free.add(i);
		for(List<Integer> c : blind)
		{	
			boolean t1=isBlind(c.get(0)) && isBlind(root(c.get(0),c.get(1))) 
						&& !result.contains(c.get(0)) && check(c.get(1));
			boolean t2=isBlind(c.get(1)) && isBlind(root(c.get(1),c.get(0)))
						&& !result.contains(c.get(1)) && check(c.get(0));
			if(!result.isEmpty() && t1 && t2 &&  (count(c.get(1))-count(c.get(0) ) )>=1 && isBig(c,c.get(1))
	 					 && blind.size()>free.size() && 							find(c.get(0)))
				result.add(c.get(1));
			else if(result.isEmpty() ||  t1 )
				result.add(c.get(0));
			else if(t2)
				result.add(c.get(1));
			else if(!result.contains(c.get(0)) && !result.contains(c.get(1)))
			{
				System.out.println("No solution");
				f=1;
				break;
			}
		}
		if(f==0)
		{
			for(int c : free)
				if(result.size()<N/2) result.add(c);
			Collections.sort(result);
			for(int c : result)
				System.out.print(c+" ");
			System.out.println();
		}

	}
}
