package com.example.demo.service;

import com.example.demo.dto.ProcessingUnitDto;
import com.example.demo.dto.ProcessingUnitProductsDto;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.entity.Project;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.repo.ProjectRepo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author : Isuru Bandara <icbandara@controlunion.com>
 * @since : 01/11/2023
 **/

@Service
public class ExcelService {

    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private MyApiService apiService;

    public void convert(MultipartFile file, String token) {
        try {
            System.out.println("start sending request");
            apiService.sendPost(readThirdColumn(file), token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, ProjectDTO> readThirdColumn(MultipartFile excelFile) throws IOException {
        List<String> thirdColumnData = new ArrayList<>();
        HashMap<Integer, ProjectDTO> projectHashMap = new HashMap<>();
        System.out.println("Start reading excel");
        try (InputStream inputStream = excelFile.getInputStream();
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            int rowCount = 0;
            int rowCountHave = 0;
            String uName = "";
            String pName = "";
            String products = "";
            String address = "";

            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Project project = null;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getColumnIndex()) {
                        case 0 -> uName = cell.getStringCellValue();
                        case 1 -> {
                            if (cell.getCellType() == CellType.STRING)
                                address = cell.getStringCellValue();
                        }
                        case 2 -> {
                            pName = cell.getStringCellValue();
                            project = projectRepo.findFirstByProName(pName.trim()).orElse(null);
                        }
                        case 3 -> products = cell.getStringCellValue();
                    }
                }

                Cell c = row.createCell(4);
                if (project != null) {
//                    System.out.println(project.getProID());

                    c.setCellValue(project.getProID());
                    ProjectDTO dto = projectMapper.toProjectDTO(project);
                    projectHashMap.putIfAbsent(project.getProID(), dto);
                    if (project.getProID() > 0) {
                        rowCountHave++;
                        ProcessingUnitDto processingUnitDto = new ProcessingUnitDto();
                        processingUnitDto.setProcessingID(0);
                        processingUnitDto.setAddress(address);
                        processingUnitDto.setName(uName);

                        String[] arr = products.split("\n");
                        List<ProcessingUnitProductsDto> list = new ArrayList<>();
                        for (String product : arr) {
                            ProcessingUnitProductsDto processingUnitProductsDto = new ProcessingUnitProductsDto();
                            processingUnitProductsDto.setProductID(0);
                            processingUnitProductsDto.setName(product);
                            list.add(processingUnitProductsDto);
                        }
                        processingUnitDto.setProcessingUnitProducts(list);
                        ProjectDTO dto1 = projectHashMap.get(project.getProID());
                        List<ProcessingUnitDto> list1 = new ArrayList<>();
                        if (dto1.getProcessingUnits() != null) {
                            list1 = dto1.getProcessingUnits();
                        }
                        list1.add(processingUnitDto);
                        dto1.setProcessingUnits(list1);
                    }
                } else
                    c.setCellValue(0);
            }
            try (FileOutputStream fileOut = new FileOutputStream(excelFile.getOriginalFilename())) {
                workbook.write(fileOut);
            }
        }

        System.out.println("Done===================================================================================================");
        return projectHashMap;
    }

}
