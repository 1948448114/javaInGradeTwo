package test;

import java.io.File;
import java.io.FilenameFilter;

public class test {
	static long x;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		long a=16l;
//		int x=10;
//		System.out.println(x);
//		System.out.println(a);
//		int z=0x1A;
//		System.out.println(z);
//		char x='��';
//		System.out.println(x);
//		int a=2147483647;
//		long b=a*4;
//		System.out.println(b);
//		System.out.println(b);
//		boolean c=true;
//		System.out.println(c);
//		int e=-4;
//		System.out.println(e>>2);
//		String s1="abc";
//		String s2=new String("abc");
//		if(s1==s2)
//			System.out.println("Yes");
//		String s1 ="����abc";
//				String s2 = "����abc";
//				String s3 = new String("����abc");
//				String s4 = new String("����abc");
//
//				System.out.println(s1 == s2);
//				System.out.println(s1 == s3);
//				System.out.println(s3 == s4);
//	
		
		
		
//		
//		int a=1;
//		Car car=new Car(a);
//		System.out.println(a);
		
		
		
//		car.a=1;
//		System.out.println(car.a);
//		try{Root leaf;
//		//=new Root();
//		Leaf leaf2;
//		}
		
		//=new Leaf();
//		if(leaf2 instanceof Root)
//			System.out.println("yes");
//		//Leaf leaf2=new Root();���Ϸ�
		
		
//		int a=1;
//		
//		try {
//			DeepExcp excp=new DeepExcp();
//			
//	        try {  
//	        	  excp.f2();
//	        	excp.f1(a);
//	        	
//	          
//	          
//	        }
//	        catch (MyException2 e2) {
//	            System.out.println("�����쳣2");
//	        }
//	        System.out.println("get");	
////	        finally {
////	            System.out.println("��һ��finally����!");
////	        }
////	        
//	    }
//	    catch (MyException1 e1) {
//	        System.out.println("�����쳣1");
//	    }
//	
//	    finally {
//	        System.out.println("�ڶ���finally����!");
//	    }
//		
//		System.out.println(a);
			
			
			//System.exit(0);
		
		
//		String path="e:/C++���/�����/";
//		File file=new File(path);
//		myFileFileter myFileFileter=new myFileFileter();
//		File[] files=file.listFiles(myFileFileter);
//		for(File file1:files){
//			System.out.println(file1.getPath());
//			System.out.println(file1.getParent());
//			System.out.println(file1.getName());
//			System.out.println();
//		}
//		String[] list=file.list(myFileFileter);
//		for(String s:list)
//			{
//			System.out.println(s);
//			}

		
		Two two;
		Five five=new Five();
		
		four<Five> four1=new four<Five>(five);
		
	
	
	}

}



class myFileFileter implements FilenameFilter{

	@Override
	public boolean accept(File dir, String name) {
		// TODO Auto-generated method stub
		if(name.endsWith(".jpg"))
		return true;
		else 
			return false;
	}
	
}

class MyException1 extends Exception
{

}

class MyException2 extends Exception 
{

}
 class DeepExcp {

    static void f1(int a) throws MyException1 {
    	a++;
    	System.out.println(a);
        throw new MyException1();
    }

    static void f2() throws MyException2 {
        throw new MyException2();
    }
 }





abstract class One{
	abstract int a();
	void b(){
		
	}
}

class Three{
	
}

class four<E>{
	public four(E a) {
		
	}
	
}

class Five{
	int get(){
		return 0;
	}
	void set(){
		
	}
}
interface Two{
	
}


class Car extends One{
	int a;
	public Car(int a){
		a+=10;
		
	}
	@Override
	int a() {
		// TODO Auto-generated method stub
		return 0;
	}
}


class Root 
{  
    static{  
        System.out.println("Root �ľ�̬��ʼ����");  
    }  
    {  
        System.out.println("Root ����ͨ��ʼ����");  
    }  
    public Root()  
    {  
        System.out.println("Root ���޲����Ĺ�����");  
    }  
}  
  
class Mid extends Root  
{  
    static{  
        System.out.println("Mid �ľ�̬��ʼ����");  
    }  
    public Mid()  
    {  
        System.out.println("Mid ���޲����Ĺ�����");  
    }  
    public Mid(String msg)  
    {  
        //ͨ��this����ͬһ�������صĹ�����  
        this();  
        System.out.println("Mid�Ĵ��ι�����,�����ֵ:" + msg);  
    }  
}  
  
class Leaf extends Mid  
{  
    static{  
        System.out.println("Leaf �ľ�̬��ʼ����");  
    }  
    {  
        System.out.println("Leaf ����ͨ��ʼ����");  
    }  
    public Leaf()  
    {  
        //ͨ��surper ���ø�������һ���ַ��������Ĺ�����  
        super("Struts 2Ȩ��ָ��");  
        System.out.println("ִ��Leaf�Ĺ�����");  
    }
    
    void get(){
    	
    }
}  
  