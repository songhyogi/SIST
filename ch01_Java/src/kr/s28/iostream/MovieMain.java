package kr.s28.iostream;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MovieMain {
	/*
	 * 메뉴 : 1.영화정보입력,2.영화정보출력,3.파일생성,4.파일읽기,5.종료
	 *          console이 목적지        파일이 목적지 
	 * 메서드명 : 메뉴 callMenu()
	 *         영화정보입력 inputMovie()
	 *         영화정보출력 printMovie()
	 *         파일생성 createFile() filewriter로
	 *         파일읽기 readFile()   filereader로
	 * 입력시 조건 체크 : 상영시간 - 0보다 크게 입력하세요.
	 */
	ArrayList<Movie> list;
	BufferedReader br;

	public MovieMain() {
		list = new ArrayList<Movie>();
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			callMenu();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(br!=null)try {br.close();}catch(IOException e) {}
		}
	}
	//메뉴
	public void callMenu()throws IOException{
		while(true) {
			System.out.print("1.영화정보입력,2.영화정보출력,3.파일생성,4.파일읽기,5.종료>");
			try {
				int num = Integer.parseInt(br.readLine());
				if(num == 1) {//영화정보입력
					inputMovie();
				}else if(num == 2) {//영화정보출력
					printMovie();
				}else if(num == 3) {//파일생성
					createFile();
				}else if(num == 4) {//파일읽기
					readFile();
				}else if(num == 5) {//종료
					System.out.println("프로그램 종료");
					break;
				}else {
					System.out.println("잘못 입력했습니다.");
				}
			}catch(NumberFormatException e) {
				System.out.println("숫자만 입력하세요!");
			}
		}
	}
	//영화정보입력
	public void inputMovie()throws  IOException{
		Movie m = new Movie();
		System.out.print("영화제목:");
		m.setName(br.readLine());
		System.out.print("제작연도:");
		m.setCreate_year(br.readLine());
		System.out.print("감독:");
		m.setDirector(br.readLine());
		System.out.print("배우:");
		m.setActor(br.readLine());
		m.setTime(parseInputData("상영시간:"));

		list.add(m);
		System.out.println("영화정보 1건을 입력했습니다.");
	}

	//상영시간 조건체크
	public int parseInputData(String time)throws IOException{
		while(true) {
			System.out.print(time);
			try {
				int num = Integer.parseInt(br.readLine());
				if(num<=0) {
					System.out.println("0보다 크게 입력하세요");
					continue;
				}
				return num;
			}catch(NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			}
		}
	}

	//영화정보출력
	public void printMovie()throws IOException{
		if(list.size()>0) {
			System.out.println("---------------------------------");
			System.out.println("영화제목\t제작연도\t감독\t배우\t상영시간");
			System.out.println("---------------------------------");
			for(Movie m : list) {
				/*
				System.out.print(m.getName() + "\t");
				System.out.print(m.getCreate_year() + "\t");
				System.out.print(m.getDirector() + "\t");
				System.out.print(m.getActor() + "\t");
				System.out.print(m.getTime() + "\n");
				*/
				System.out.print(m.toString());
			}//end of for
		}else {
			System.out.println("출력할 영화정보가 없습니다.");
		}
	}

	//파일생성
	public void createFile() {
		FileWriter fw = null;
		try {
			fw = new FileWriter("movie.txt");
			fw.write("----------------------------\n");
			fw.write("영화제목\t제작연도\t감독\t배우\t상영시간\n");
			fw.write("----------------------------\n");
			for(Movie m : list) {
				fw.write(m.toString());
			}
			fw.flush();
			System.out.println("파일에 영화정보를 저장했습니다.");
		}catch(IOException e) {
			System.out.println("파일에 영화정보 저장 오류");
		}finally {
			if(fw!=null)try {fw.close();}catch(IOException e) {}
		}
	}

	//파일읽기
	public void readFile() {
		FileReader fr = null;
		int readChar;
		try {
			fr = new FileReader("Movie.txt");
			while((readChar = fr.read())!=-1) {
				System.out.print((char)readChar);
			}
		}catch(FileNotFoundException e) {
			System.out.println("영화 파일을 찾을 수 없습니다.");
		}catch(IOException e) {
			System.out.println("파일 읽기 오류");
		}finally {
			if(fr!=null)try {fr.close();}catch(IOException e) {}
		}
	}

	public static void main(String[] args) {
		new MovieMain();
	}
}
