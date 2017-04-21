package com.huan.ted.Thread.impl;

public class ContextThread implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i =0;i<2;i++){
			System.out.println("线程池执行:"+i);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
