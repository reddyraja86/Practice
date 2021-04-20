package com.spring.kafka;

class MyRunnable implements Runnable{

	 KafkaSender sender;
	 String dbName; 
	
	 public MyRunnable(String dbName,KafkaSender sender) {
		 this.dbName = dbName;
		 this.sender = sender;
	 }
	
	@Override
	public void run() {
		int i=0;
	//	while(i<20) {
			//System.out.println(dbName +"----");
			i++;
			try {
				//this.wait(1000);
				sender.send("DB Name --" + dbName + " ----- " + i);
				
			} catch (Throwable e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		//}
	}
	
}