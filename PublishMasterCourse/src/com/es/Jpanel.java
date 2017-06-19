package com.es;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Jpanel {
	
	 

	public static String[]input () throws Exception {
		String vcdNrUrl,vcdNpUrl,CgieNrUrl,CgieNpUrl,PpeUrl,
		vcdNrUserName,VcdNpUsername,CgieNrUsername,CgieNpUsername,PpeUsername,
		vcdNrPassword,VcdNpPassword,CgieNrPassword,CgieNpPassword,PpePassword,
		value1,value2,value3,value4,value5;
		String[] values =  new String[5];
		
		vcdNrUrl=property("vcdNrUrl");
		vcdNpUrl=property("vcdNpUrl");
		CgieNrUrl=property("CgieNrUrl");
		CgieNpUrl=property("CgieNpUrl");
		PpeUrl=property("PpeUrl");
		vcdNrUserName=property("vcdNrUserName");
		VcdNpUsername=property("VcdNpUsername");
		CgieNrUsername=property("CgieNrUsername");
		CgieNpUsername=property("CgieNpUsername");
		PpeUsername=property("PpeUsername");
		vcdNrPassword=property("vcdNrPassword");
		VcdNpPassword=property("VcdNpPassword");
		CgieNrPassword=property("CgieNrPassword");
		CgieNpPassword=property("CgieNpPassword");
		PpePassword=property("PpePassword");
		
		value1=vcdNrUrl  +" {"+vcdNrUserName  +" / "+vcdNrPassword+"}";
		value2=vcdNpUrl  +" {"+VcdNpUsername  +" / "+VcdNpPassword +"}";
		value3=CgieNrUrl +" {"+CgieNrUsername +" / "+CgieNrPassword +"}";
		value4=CgieNpUrl +" {"+CgieNpUsername +" / "+CgieNpPassword +"}";
		value5=PpeUrl   +" {"+PpeUsername    +" / "+PpePassword+"}";
		
		values[0]=value1;
		values[1]=value2;
		values[2]=value3;
		values[3]=value4;
		values[4]=value5;
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new GridLayout(2,2));
		
		JLabel label=new JLabel("WS course ID to publish:");
		JTextField s=new JTextField("1111111");
		JLabel logins=new JLabel("Select Enivironments / Logins");
		panel.add(label, BorderLayout.WEST);
		panel.add(s,     BorderLayout.CENTER);
		panel.add(logins,BorderLayout.  SOUTH);
		Object selected = JOptionPane.showInputDialog(null,panel, "Select the environment details",
				JOptionPane.DEFAULT_OPTION, null, values, "0");
		String []valuesToReturn=new String[4];
		if (selected != null)
		{// null if the user cancels.
			String selectedEnvironment = selected.toString();
			int envi=Arrays.asList(values).indexOf(selectedEnvironment);
			switch (envi) {
			case 0:
							valuesToReturn[0] = vcdNrUrl;
							valuesToReturn[1] = vcdNrUserName;
						 	valuesToReturn[2] = vcdNrPassword;
							valuesToReturn[3] = s.getText();
										
				break;
			case 1:
							valuesToReturn[0] = vcdNrUrl;
							valuesToReturn[1] = VcdNpUsername;
							valuesToReturn[2] = VcdNpPassword;
							valuesToReturn[3] = s.getText();
				break;
			case 2:
							valuesToReturn[0] = CgieNrUrl;
							valuesToReturn[1] = CgieNrUsername;
							valuesToReturn[2] = CgieNrPassword;
							valuesToReturn[3] = s.getText();
				break;
			case 3:
							valuesToReturn[0] = CgieNpUrl;
							valuesToReturn[1] = CgieNpUsername;
							valuesToReturn[2] = CgieNpPassword;
							valuesToReturn[3] = s.getText();
				break;
			case 4:
							valuesToReturn[0] = PpeUrl;
							valuesToReturn[1] = PpeUsername;
							valuesToReturn[2] = PpePassword;
							valuesToReturn[3] = s.getText();
				break;
			default:
				JOptionPane.showMessageDialog(null,	"Invalid environment");
				break;
			}
		}
		else throw new Exception("User cancelled the test");
		
		return valuesToReturn; 
		
		 
		}

	protected static String property(String param) throws IOException
	{
		File file=new File("C:\\Users\\partha\\Desktop\\Environmentdetail.properties");
		if (!file.exists()) 
		{
			System.out.println(file);
			JOptionPane.showMessageDialog( null, "File not found in 'C:\\Users\\partha\\Desktop\\Environmentdetail.properties' ", null, JOptionPane.INFORMATION_MESSAGE);
		}
		FileInputStream fis = new FileInputStream(file);
				 
		Properties p = new Properties();
		p.load(fis);
		String val = p.getProperty(param);
		fis.close(); 
		return val;
		
	}

	}


