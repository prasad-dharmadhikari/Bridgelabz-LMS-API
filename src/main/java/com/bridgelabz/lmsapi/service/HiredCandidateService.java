package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.dto.HiredCandidateDTO;
import com.bridgelabz.lmsapi.dto.Response;
import com.bridgelabz.lmsapi.model.HiredCandidate;
import com.bridgelabz.lmsapi.repository.HiredCandidateRepository;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

@Service
public class HiredCandidateService implements IHiredCandidateService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private HiredCandidateRepository hiredCandidateRepository;

    @Override
    public Response saveDataInBatchToDatabase(MultipartFile file) {
        HiredCandidateDTO hiredCandidateDTO = new HiredCandidateDTO();
        boolean flag = true;
        try (InputStream fis = file.getInputStream()) {
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            //Iterate through each rows one by one
            Iterator rows = sheet.rowIterator();
            XSSFCell cell;
            //For each row, iterate through all the columns
            while (rows.hasNext()) {
                XSSFRow row = (XSSFRow) rows.next();
                Iterator cells = row.cellIterator();
                if (!flag) {
                    while (cells.hasNext()) {
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setId((long) cell.getNumericCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setFirstName(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setMiddleName(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setLastName(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setEmail(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setHiredCity(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setDegree(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setHiredDate(cell.getDateCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setMobileNumber((long) cell.getNumericCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setPermanentPincode((int) cell.getNumericCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setHiredLab(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setAttitude(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setCommunicationRemark(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setKnowledgeRemark(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setAggregateRemark(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setStatus(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setCreatorStamp(cell.getDateCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDTO.setCreatorUser(cell.getStringCellValue());
                        save(hiredCandidateDTO);
                    }
                }
                flag = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Response(200, "Data Saved Successfully");
    }

    @Override
    public List getHiredCandidateList() {
        return hiredCandidateRepository.findAll();
    }

    @Override
    public void save(HiredCandidateDTO hiredCandidateDTO) {
        HiredCandidate hiredCandidate = modelMapper.map(hiredCandidateDTO, HiredCandidate.class);
        hiredCandidateRepository.save(hiredCandidate);
    }

    @Override
    public HiredCandidate getCandidateProfile(long id) {
        return hiredCandidateRepository.findById(id).get();
    }
}
