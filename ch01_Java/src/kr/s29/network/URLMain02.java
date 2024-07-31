package kr.s29.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class URLMain02 {
	public static void main(String[] args) {
		BufferedReader br = null;
		BufferedReader input = null;//서버로부터 데이터를 읽어오는 것
		try {
			//콘솔에서 입력 처리
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("웹사이트 주소 입력:");
			String name = br.readLine();
			
			URL url = new URL(name);
			//네트워크를 통해서 서버로부터 데이터를 읽어들여 입력 처리
			input = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
			
			String line;
			while((line = input.readLine())!=null) {
				System.out.println(line);
			}
			
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(br!=null)try {br.close();}catch(IOException e) {}
			if(input!=null)try {input.close();}catch(IOException e) {}
		}
	}
}
