package pl.ormlite.example.Utils;


import pl.ormlite.example.Model.WinPQModelObj;
import pl.ormlite.example.Model.WinPQData;

public class ConverterWinPQData {
	public static WinPQData convertToDbObject(WinPQModelObj dataObj){
		WinPQData winPQData = new WinPQData();

		winPQData.setColumnsNames(dataObj.getColumnsNames());
		winPQData.setDate(Utils.convertToDate(dataObj.getLocalDateTime().toLocalDate()));
		winPQData.setTime(Utils.convertToTime(dataObj.getLocalDateTime().toLocalTime()));
		winPQData.setRecords(dataObj.getRecords());
		winPQData.setFlag(dataObj.getFlags());
		return winPQData;
	}
}
