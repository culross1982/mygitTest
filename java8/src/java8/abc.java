package java8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class abc {
	public static void main(String[] args) {
		File file=new File("d:/abc.txt");
		Scanner input=new Scanner(System.in);
		System.out.println("请输入要查找的字符：");
		String in=input.next();
		char[] cha=in.toCharArray();
		abc.findRow(file,cha);
		
	}
	
	/**
	 * 查找包含设定字符的行号
	 * @param file
	 */
	public static void findRow(File file,char[] cha){
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr=new FileReader(file);
			br=new BufferedReader(fr);
			String sb=new String();
			int row=0;//行号
			
			while((sb=br.readLine())!=null){
				int count=0;//对指定字符至少出现一次进行计数
				row++;
				for(int i=0;i<cha.length;i++){
					if(sb.indexOf(cha[i])!=-1){
						count++;
					}
				}
				if(count==cha.length){//当指定字符的数量和一行中出现的数量一致时，表明该行满足要求
					System.out.print("第"+row+"行包含字符");
					System.out.println(cha);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
