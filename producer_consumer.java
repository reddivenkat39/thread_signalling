/*
 * Producer thread has to produce 
 * Consumer Thread has to consume
 * Producer cannot produce if there is no space
 * consumer cannot consume id there is no product
 */
class product
{
	static int product_count=0;
}
class key1
{
	
}
class consuming implements Runnable
{
	key1 k;
	consuming(key1 object)
	{
		this.k = object;
	}
	public void run()
	{
		for(int i=0;i<5;i++)
		{
		synchronized(k)
		{
		if(product.product_count == 0)
		{
			try {
				k.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+"is consuming"+" "+ product.product_count);
		product.product_count--;
		}
	}
	}
}
class production implements Runnable 
{
	key1 k;
	production(key1 object)
	{
		this.k = object;
	}
	public void run()
	{
		for(int i=0;i<5;i++)
		{
		
		synchronized(k)
		{
		if(product.product_count ==0)
		{
			product.product_count++;
			k.notify();
			
		}
		else
		product.product_count++;
		System.out.println(Thread.currentThread().getName()+ "is producing"+ " "+ product.product_count);
		}
		
	}
	}
}
public class producer_consumer {
	public static void main(String[] args)
	{
		key1 k = new key1();
		production p = new production(k);
		consuming c = new consuming(k);
	    Thread t = new Thread(p,"production ");
	    Thread t1 = new Thread(c,"consumer ");
	    t.start();
	    try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    t1.start();
	}
}
