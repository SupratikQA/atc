package com.demo.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.demo.entity.TestEntity;
import com.monitorjbl.xlsx.StreamingReader;

public class XlsReader {

	public static String stestId;
	public static String stestCase;
	public static String skeyWord;
	public static String sactionName;
	public static String sidentifier;
	public static String sadditionalIdentifier;
	public static String stestData;
	public static String sexpectedResult;
	public static String ssuccessMessage;
	public static String sfailureMessage;

	public static List<TestEntity> getTest(String Testcasefile) throws FileNotFoundException {

		List<TestEntity> tls = new ArrayList<>();

		InputStream is = new FileInputStream(new File(System.getProperty("user.dir") + "/Testcase/" + Testcasefile));
		Workbook wb = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is);

		for (Sheet sht : wb) {
			for (Row r : sht) {
				for (Cell c : r) {

					switch (c.getColumnIndex()) {
					case 0:
						stestId = c.getStringCellValue();
						break;
					case 1:
						stestCase = c.getStringCellValue();
						break;
					case 2:
						skeyWord = c.getStringCellValue();
						break;
					case 3:
						sactionName = c.getStringCellValue();
						break;
					case 4:
						sidentifier = c.getStringCellValue();
						break;
					case 5:
						sadditionalIdentifier = c.getStringCellValue();
						break;
					case 6:
						stestData = c.getStringCellValue();
						break;
					case 7:
						sexpectedResult = c.getStringCellValue();
						break;
					case 8:
						ssuccessMessage = c.getStringCellValue();
						break;
					case 9:
						sfailureMessage = c.getStringCellValue();
						break;
					}
				}
				TestEntity te = new TestEntity(stestId, stestCase, skeyWord, sactionName, sidentifier,
						sadditionalIdentifier, stestData, sexpectedResult, ssuccessMessage, sfailureMessage);
				tls.add(te);
			}
		}

		return tls;

	}

}
