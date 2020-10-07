package com.co;



import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.Font;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Corona {
	
	
	public static String getData(String s) throws IOException
	{
		StringBuffer br=new StringBuffer();
		
		br.append("<html>" +
				"<body style='text-align:center;color:red;'>" );
		br.append(s.toUpperCase()+"<br>");
		String url="https://www.worldometers.info/coronavirus/country/" + s +"/";
		Document doc=Jsoup.connect(url).get();
		
		//maincounterwrap
		
		Elements elements= doc.select("#maincounter-wrap");
		
		elements.forEach((e)->{
			String text=e.select("h1").text();
			String count=e.select(".maincounter-number>span").text();
			br.append(text).append(count).append("<br>");
		
		});
		br.append("</body>"
				+ "</html>");
		return br.toString();
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*Scanner scan=new Scanner(System.in);
		System.out.println("enter the country name: ");
		String co=scan.nextLine();
		
		System.out.println(getData(co));
	*/
		
		//creating gui
		JFrame root=new JFrame("Details of Country");
		root.setSize(500, 500);
		
		Font f=new Font("Poppins", Font.BOLD, 30);
		
		//textField
		JTextField field=new JTextField();
		//Label
		JLabel data=new JLabel();
		field.setFont(f);
		data.setFont(f);
		field.setHorizontalAlignment(SwingConstants.CENTER);
		data.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton button = new JButton("Get");
		
		button.addActionListener(event->{
			
			//click
			try {
			String maindata=field.getText();
			String result=getData(maindata);
			data.setText(result);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			});
		
		button.setFont(f);
		
		root.setLayout(new BorderLayout());
		
		root.add(field,BorderLayout.NORTH);
		root.add(data, BorderLayout.CENTER);
		root.add(button,BorderLayout.SOUTH);
		
		root.setVisible(true);
		
		
		
		
		
		
		
		
	}

}
