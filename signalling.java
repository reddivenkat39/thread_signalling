// thread wait and notify

class key
{
	
}
class thread_action implements Runnable
{
	key k1 = new key();
	public void run()
	{
		synchronized(k1)
		{
		System.out.println(Thread.currentThread().getName());
		
		if(Thread.currentThread().getName().equals("first"))
		{
		try {
			k1.wait();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		 
		}
		}
		
		synchronized(k1)
		{
			if(Thread.currentThread().getName().equals("second"))
			{
				k1.notify();
			}
		}
	}
}
public class signalling
{
	public static void main(String[] args)
	{
		thread_action ta = new thread_action();
		Thread t = new Thread(ta,"first");
		Thread t1 = new Thread(ta,"second");
		
		t.start();
		t1.start();
	}
}