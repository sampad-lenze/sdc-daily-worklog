package com.lenze.sdc.worklog.rest.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lenze.sdc.worklog.rest.dto.DailyStatusResponse;
import com.lenze.sdc.worklog.rest.dto.DownloadRequestDto;
import com.lenze.sdc.worklog.rest.service.DailyStatusService;

@RestController
@RequestMapping("/api/export")
public class ExcelExportController {


	private final DailyStatusService dailyStatusService;

	ExcelExportController(DailyStatusService dailyStatusService) {
		this.dailyStatusService = dailyStatusService;
	}
    @PostMapping("/generate-excel")
    public ResponseEntity<byte[]> generateExcel(@RequestBody DownloadRequestDto requestDto) throws IOException {
    	
//		String startDate = requestDto.getStartDate();
//		String endDate = formData.get("endDate");
//		String userName = formData.get("userName");
		
    	List<DailyStatusResponse> dataList = dailyStatusService.exportToExcel(requestDto.getUserName(), requestDto.getStartDate(), requestDto.getEndDate());
    	  
		Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        // Create header row
        Row headerRow = sheet.createRow(0);
        int headerCellIndex = 0;
        for (String key : DailyStatusResponse.getFieldNames()) {
            Cell cell = headerRow.createCell(headerCellIndex++);
            cell.setCellValue(key);
        }

        // Create data rows
        int rowIndex = 1;
        for (DailyStatusResponse data : dataList) {
            Row dataRow = sheet.createRow(rowIndex++);
            int dataCellIndex = 0;
            for (String value : data.getValues()) {
                Cell cell = dataRow.createCell(dataCellIndex++);
                cell.setCellValue(value);
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        byte[] excelBytes = outputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=data.xlsx");

        return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
    }
}
