package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="LoginData")
	public String[][] getdata() throws IOException
	{
		String path=".\\testData\\testdata.xlsx";//TAKING XL From testdata, . here represet currnt proj loc
		
		ExcelUtils xlutil=new ExcelUtils(path);//creating obj
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1);
		
		
		String logindata[][]=new String[totalrows][totalcols];//created for two dimension array which can store
		
		for(int i=1;i<=totalrows;i++) { //read the data from xl storing in 2d array
			for(int j=0;j<totalcols;j++) {
			
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);//here index will start with 0 i.e i-0;
			}
		}
		
		return logindata;
	}
	
	//Data Provider 2
	//Data PROVIDER 3
	
}
