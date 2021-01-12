package Engine;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ReadExcelReusableMethods {


    Row row;
    Cell cell;
    Sheet mySheet;

    public ReadExcelReusableMethods(){

//        navigate to file -- Creates an input file stream to read from the specified file descriptor.
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("src\\test\\java\\Elements\\Values.xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        Open a workbook - Access to data
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Decide the which sheet we need to work with
        mySheet = workbook.getSheet("AllValues");



    }

    //    Get the specific data from excel
    public String getSpecificData(String whichData){


        int maxRow = mySheet.getPhysicalNumberOfRows();

        String result = null;

        for(int i = 0 ; i<maxRow ; i++){

            row = mySheet.getRow(i);

            cell = row.getCell(0);

            if(cell.toString().equals(whichData)){

                cell = row.getCell(1);
                result =cell.toString();

            }
        }



        return numConverter(result);
    }

    //    Get All the data from the excel
    public ArrayList<String> getAllData(){

        int maxRow= mySheet.getPhysicalNumberOfRows();

        ArrayList resultList = new ArrayList();

        for(int i = 0 ; i <maxRow ; i++){

            row = mySheet.getRow(i);

            int cellCount = row.getPhysicalNumberOfCells();

            for(int j = 0 ; j <cellCount ; j++){

                cell = row.getCell(j);

                resultList.add(cell.toString());
            }
        }
        return resultList;
    }

//    in the excel if I have 100 then excel return 100.0 that is why automation is failing. I am adding this method to remove these errors.
    public String numConverter(String val){

        double myNum = 0.0;

        try {
            myNum = Double.parseDouble(val);

            int num = (int) myNum;

            val = String.valueOf(num);
        }catch (Exception e){

            e.getMessage();
        }

        return  val;
    }


    public static void main(String[] args) {

        ReadExcelReusableMethods readExcelReusableMethods = new ReadExcelReusableMethods();

        String isernaem = readExcelReusableMethods.getSpecificData("Username1");

        System.out.println(isernaem);


    }

}

