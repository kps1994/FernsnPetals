package fnp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Dataexc 
{
    public XSSFWorkbook wb = null;
    public XSSFSheet sheet = null;
    public XSSFRow row = null;
    public XSSFCell cell = null;
 
    public Dataexc(String Datasheet) throws IOException
    {
    	File f=new File(Datasheet);
		FileInputStream fis=new FileInputStream(f);
		wb=new XSSFWorkbook(fis);
        
        fis.close();
    }
    
    public int rowcount(int sheetindex)
	{
		int row=wb.getSheetAt(sheetindex).getLastRowNum();
		row++;
		return row;	
	}
 
    @SuppressWarnings("deprecation")
	public String getCellData(int sheetnum,int rowNum, int colNum)
    {
        try
        {
            sheet = wb.getSheetAt(sheetnum);
            
            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);
            //returns String value
            if(cell.getCellType() == CellType.STRING)
                return cell.getStringCellValue();
            //returns numeric value or date value
            else if(cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA)
            {
                String cellValue  = String.valueOf(cell.getNumericCellValue());
                if (DateUtil.isCellDateFormatted(cell))
                {
                    DateFormat df = new SimpleDateFormat("dd-MM-yy");
                    Date date = cell.getDateCellValue();
                    cellValue = df.format(date);
                }
                return cellValue;
                //return empty cell
            }else if(cell.getCellType() == CellType.BLANK)
                return "";
            //return boolean value
            else
                return String.valueOf(cell.getBooleanCellValue());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "row "+rowNum+" or column "+colNum +" does not exist  in Excel";
        }
    }

	



}
