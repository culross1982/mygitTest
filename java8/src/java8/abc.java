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
		System.out.println("������Ҫ���ҵ��ַ���");
		String in=input.next();
		char[] cha=in.toCharArray();
		abc.findRow(file,cha);
		
	}
	
	/**
	 * ���Ұ����趨�ַ����к�
	 * @param file
	 */
	public static void findRow(File file,char[] cha){
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr=new FileReader(file);
			br=new BufferedReader(fr);
			String sb=new String();
			int row=0;//�к�
			
			while((sb=br.readLine())!=null){
				int count=0;//��ָ���ַ����ٳ���һ�ν��м���
				row++;
				for(int i=0;i<cha.length;i++){
					if(sb.indexOf(cha[i])!=-1){
						count++;
					}
				}
				if(count==cha.length){//��ָ���ַ���������һ���г��ֵ�����һ��ʱ��������������Ҫ��
					System.out.print("��"+row+"�а����ַ�");
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
