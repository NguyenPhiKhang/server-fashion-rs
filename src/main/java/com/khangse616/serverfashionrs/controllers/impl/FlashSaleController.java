package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IFlashSaleController;
import com.khangse616.serverfashionrs.models.FlashSale;
import com.khangse616.serverfashionrs.models.dto.FlashSaleDTO;
import com.khangse616.serverfashionrs.models.dto.FlashSaleForTableDTO;
import com.khangse616.serverfashionrs.repositories.FlashSaleRepository;
import com.khangse616.serverfashionrs.services.IFlashSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1")
public class FlashSaleController implements IFlashSaleController {
    @Autowired
    private IFlashSaleService flashSaleService;

    @Autowired
    private FlashSaleRepository flashSaleRepository;

    @Override
    public List<FlashSaleDTO> getAllFlashSale() {
        List<FlashSale> flashSale = flashSaleService.getAllFlashSale();

        List<FlashSaleDTO> flashSaleDTOS = flashSale.stream().map(v -> {
            FlashSaleDTO flashSaleDTO = new FlashSaleDTO();
            flashSaleDTO.setId(v.getId());
            flashSaleDTO.setStartTime(v.getStartTime());
            flashSaleDTO.setEndTime(v.getEndTime());
            flashSaleDTO.setDate(v.getDateProgram());

            int dateProgram = Integer.parseInt(v.getDateProgram().toString().replace("-", ""));
            int dateNow = Integer.parseInt(new Date(System.currentTimeMillis()).toString().replace("-", ""));

            if (dateProgram == dateNow) {
                int timeNow = Integer.parseInt(new Time(System.currentTimeMillis()).toString().replace(":", ""));
                int timeStart = Integer.parseInt(v.getStartTime().toString().replace(":", ""));
                int timeEnd = Integer.parseInt(v.getEndTime().toString().replace(":", ""));

                if (timeNow == timeStart || (timeNow > timeStart && timeNow < timeEnd))
                    flashSaleDTO.setStatus("Đang diễn ra");
                else if (timeNow > timeEnd)
                    flashSaleDTO.setStatus("Đã kết thúc");
                else if (timeNow < timeStart)
                    flashSaleDTO.setStatus("Sắp diễn ra");
            } else {
                if (dateProgram < dateNow)
                    flashSaleDTO.setStatus("Đã kết thúc");
                else flashSaleDTO.setStatus("Chưa diễn ra");
            }

            return flashSaleDTO;
        }).collect(Collectors.toList());

        return flashSaleDTOS;
    }

    @Override
    public List<FlashSaleDTO> getFlashSaleForMobile() {
        List<FlashSale> flashSales = flashSaleService.getFlashSaleTime();

        List<FlashSaleDTO> flashSaleDTOS = flashSales.stream().map(v -> {
            FlashSaleDTO flashSaleDTO = new FlashSaleDTO();
            flashSaleDTO.setId(v.getId());
            flashSaleDTO.setStartTime(v.getStartTime());
            flashSaleDTO.setEndTime(v.getEndTime());
            flashSaleDTO.setDate(v.getDateProgram());

            int dateProgram = Integer.parseInt(v.getDateProgram().toString().replace("-", ""));
            int dateNow = Integer.parseInt(new Date(System.currentTimeMillis()).toString().replace("-", ""));

            int timeNow = Integer.parseInt(new Time(System.currentTimeMillis()).toString().replace(":", ""));
            int timeStart = Integer.parseInt(v.getStartTime().toString().replace(":", ""));
            int timeEnd = Integer.parseInt(v.getEndTime().toString().replace(":", ""));

            if (dateProgram == dateNow) {
                if (timeNow == timeStart || (timeNow > timeStart && timeNow < timeEnd))
                    flashSaleDTO.setStatus("Đang diễn ra");
                else if (timeNow > timeEnd)
                    flashSaleDTO.setStatus("Đã kết thúc");
                else if (timeNow < timeStart)
                    flashSaleDTO.setStatus("Sắp diễn ra");
            } else {
                if (dateProgram < dateNow)
                    flashSaleDTO.setStatus("Đã kết thúc");
                else {
                    if (dateProgram - dateNow == 1 && ((240000 - timeNow) + timeStart) < 120000)
                        flashSaleDTO.setStatus("Sắp diễn ra");
                    else flashSaleDTO.setStatus("Chưa diễn ra");
                }
            }

            return flashSaleDTO;
        }).collect(Collectors.toList());

        return flashSaleDTOS;
    }

    @Override
    public List<FlashSaleForTableDTO> getFlashSaleForTable(int page, int pageSize, int status) {
        int pageNew = page < 1 ? 0 : (page - 1) * pageSize;

        if (status == 0) {
            return flashSaleRepository.getAllFlashSaleForTable(pageNew, pageSize).stream().map(v -> {
                FlashSaleForTableDTO flashSaleForTableDTO = new FlashSaleForTableDTO();
                flashSaleForTableDTO.setId((int) v[0]);
                flashSaleForTableDTO.setStartTime((Time) v[1]);
                flashSaleForTableDTO.setEndTime((Time) v[2]);
                flashSaleForTableDTO.setDate((Date) v[3]);

                BigInteger totalProduct = (BigInteger) v[4];
                BigDecimal totalSale = (BigDecimal) v[5];
                flashSaleForTableDTO.setTotalProduct(totalProduct.intValue());
                flashSaleForTableDTO.setTotalSale(totalSale.intValue());

                int dateProgram = Integer.parseInt(flashSaleForTableDTO.getDate().toString().replace("-", ""));
                int dateNow = Integer.parseInt(new Date(System.currentTimeMillis()).toString().replace("-", ""));

                int timeNow = Integer.parseInt(new Time(System.currentTimeMillis()).toString().replace(":", ""));
                int timeStart = Integer.parseInt(flashSaleForTableDTO.getStartTime().toString().replace(":", ""));
                int timeEnd = Integer.parseInt(flashSaleForTableDTO.getEndTime().toString().replace(":", ""));


                if (dateProgram == dateNow) {
                    if (timeNow == timeStart || (timeNow > timeStart && timeNow < timeEnd))
                        flashSaleForTableDTO.setStatus(1);
                    else if (timeNow > timeEnd)
                        flashSaleForTableDTO.setStatus(3);
                    else if (timeNow < timeStart)
                        flashSaleForTableDTO.setStatus(2);
                } else {
                    if (dateProgram < dateNow)
                        flashSaleForTableDTO.setStatus(3);
                    else flashSaleForTableDTO.setStatus(2);
                }
                return flashSaleForTableDTO;
            }).collect(Collectors.toList());
        } else if (status == 1) {
            return flashSaleRepository.getAllFlashSaleForTableInProgress().stream().map(v -> {
                FlashSaleForTableDTO flashSaleForTableDTO = new FlashSaleForTableDTO();
                flashSaleForTableDTO.setId((int) v[0]);
                flashSaleForTableDTO.setStartTime((Time) v[1]);
                flashSaleForTableDTO.setEndTime((Time) v[2]);
                flashSaleForTableDTO.setDate((Date) v[3]);

                BigInteger totalProduct = (BigInteger) v[4];
                BigDecimal totalSale = (BigDecimal) v[5];
                flashSaleForTableDTO.setTotalProduct(totalProduct.intValue());
                flashSaleForTableDTO.setTotalSale(totalSale.intValue());
                flashSaleForTableDTO.setStatus(1);
                return flashSaleForTableDTO;
            }).collect(Collectors.toList());
        } else if (status == 2) {
            return flashSaleRepository.getAllFlashSaleForTableNoOccur(pageNew, pageSize).stream().map(v -> {
                FlashSaleForTableDTO flashSaleForTableDTO = new FlashSaleForTableDTO();
                flashSaleForTableDTO.setId((int) v[0]);
                flashSaleForTableDTO.setStartTime((Time) v[1]);
                flashSaleForTableDTO.setEndTime((Time) v[2]);
                flashSaleForTableDTO.setDate((Date) v[3]);

                BigInteger totalProduct = (BigInteger) v[4];
                BigDecimal totalSale = (BigDecimal) v[5];
                flashSaleForTableDTO.setTotalProduct(totalProduct.intValue());
                flashSaleForTableDTO.setTotalSale(totalSale.intValue());
                flashSaleForTableDTO.setStatus(2);
                return flashSaleForTableDTO;
            }).collect(Collectors.toList());
        } else {
            return flashSaleRepository.getAllFlashSaleForTableEnd(pageNew, pageSize).stream().map(v -> {
                FlashSaleForTableDTO flashSaleForTableDTO = new FlashSaleForTableDTO();
                flashSaleForTableDTO.setId((int) v[0]);
                flashSaleForTableDTO.setStartTime((Time) v[1]);
                flashSaleForTableDTO.setEndTime((Time) v[2]);
                flashSaleForTableDTO.setDate((Date) v[3]);

                BigInteger totalProduct = (BigInteger) v[4];
                BigDecimal totalSale = (BigDecimal) v[5];
                flashSaleForTableDTO.setTotalProduct(totalProduct.intValue());
                flashSaleForTableDTO.setTotalSale(totalSale.intValue());
                flashSaleForTableDTO.setStatus(3);
                return flashSaleForTableDTO;
            }).collect(Collectors.toList());
        }
    }

    @Override
    public int countForTable(int status) {
        return status == 0 ? flashSaleRepository.countAllFlashSaleForTable() :
                status == 1 ? flashSaleRepository.countAllFlashSaleForTableInProgress() :
                        status == 2 ? flashSaleRepository.countAllFlashSaleForTableNoOccur() :
                                flashSaleRepository.countAllFlashSaleForTableEnd();
    }
}
