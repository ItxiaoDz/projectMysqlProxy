/**
 * 
 */
package net.taodianzhang.mysqlproxy.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

/**
 * @author CZX
 * @date 2015-6-10
 */
public class TCPEchoServer {
	private static final int BUFSIZE=32;
	/**
	 * @param args
	 * @author CZX
	 * @date 2015-6-10
	 */
	public static void main(String[] args) throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		/*if(args.length!=1){
			throw new IllegalArgumentException("Parameter(s):<Port>");
		}*/
		
		int servPort=5000;
		
		//1.创建一个ServerSocket实例并制定本地端口。此套接字的功能是侦听该制定端口收到的连接。
		ServerSocket servSock=new ServerSocket(servPort);
		
		int recvMsgSize;
		
		byte [] receiveBuf=new byte[BUFSIZE];
		
		//2.重复执行
		while(true){
			//a.调用ServerSocket的accept()方法以获取下一个客户端连接。
			//基于新建立的客户端连接，创建一个Socket实例，并由accept()方法返回
			Socket clntSock=servSock.accept();
			SocketAddress clientAddress=clntSock.getRemoteSocketAddress();
			System.out.println("Handling client at "+clientAddress);
			
			/*//b,使用所返回的Socket实例的InputStream和OutputStream与客户端进行通信
			InputStream in=clntSock.getInputStream();
			OutputStream out=clntSock.getOutputStream();
			//in.read(receiveBuf);
			while((recvMsgSize=in.read(receiveBuf))!=-1){
				out.write(receiveBuf, 0, recvMsgSize);
			}
			out.write(null);
			String line;
            BufferedReader in=new BufferedReader(new InputStreamReader(clntSock.getInputStream()));
            //由Socket对象得到输入流，并构造相应的BufferedReader对象
            PrintWriter writer=new PrintWriter(clntSock.getOutputStream());
            //由Socket对象得到输出流，并构造PrintWriter对象
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            //由系统标准输入设备构造BufferedReader对象
            System.out.println("Client:"+in.readLine());*/
			
			String server="192.168.1.200";
			
			byte [] data="datassssssssssssssss".getBytes();
			
			int servPort2=3306;
			
			//1.创建一个Socket实例：构造函数向指定的远程主机和端口建立一个TCP连接
			Socket socket=new Socket(server,servPort2);
			System.out.println("Connected to server... sending echo string");
			
			/**
			 *2. 通过套接字的输入输出流进行通信：一个Socket连接实例包括一个InputStream和一个OutputStream，它们的用法同于其他Java输入输出流。
			 */
			InputStream in=socket.getInputStream();
			OutputStream out=socket.getOutputStream();
			OutputStream clnout=clntSock.getOutputStream();
			//out.write(data);
			
			int totalBytesRcvd=0;
			int bytesRcvd;
			
			while((recvMsgSize=in.read(receiveBuf))!=-1){
				clnout.write(receiveBuf, 0, recvMsgSize);
			}
			clnout.flush();
			/*String line;
			BufferedReader brin=new BufferedReader(new InputStreamReader(in));
            //由系统标准输入设备构造BufferedReader对象
            while((line=brin.readLine())!=null){
            	System.out.println("Client:"+line);
			}*/
            
			
			/*while(totalBytesRcvd<data.length){
				if((bytesRcvd=in.read(data, totalBytesRcvd, data.length-totalBytesRcvd))==-1){
					throw new SocketException("Connection closed prematurely");
				}
				totalBytesRcvd+=bytesRcvd;
			}*/
			System.out.println("Receved: "+new String(data));
			
			//3.使用Socet类的close（）方法关闭连接
			socket.close();
		
			//c，通信完成后，使用Socket的close()方法关闭该客户端套接字链接
			clntSock.close();
		}
	}

}
