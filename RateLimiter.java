/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RateLimiter;

import java.util.concurrent.Semaphore;

/**
 *
 * @author HC
 */
public class RateLimiter {
    private Semaphore semaphore;
    private int maxRequestPerMinute;
    private final Semaphore Semaphore;
    
    public RateLimiter(int maxRequestPerMinute){
        this.maxRequestPerMinute=maxRequestPerMinute;
        this.Semaphore=new Semaphore(maxRequestPerMinute);
    }
    public boolean allowRequest(){
      return semaphore.tryAcquire();
    }
    public void release(){
       semaphore.release();
    }
    public static void main(String[] args) throws Exception{
       //Create a RateLimiter instance with a maximum 5 requests per minute
        RateLimiter rateLimiter=new  RateLimiter (5);
       //Simulate incoming request
       for(int i=0; i<10;i++){
           if(rateLimiter.allowRequest()){
               System.out.println("Request" + (i +1) +"allowed");
               
               //simulate request processing time
               Thread.sleep(1000);
               rateLimiter.release();
           }   else{
               System.out.println("Request" + (i+1) + "blocked");
           }   
       }
    }
}