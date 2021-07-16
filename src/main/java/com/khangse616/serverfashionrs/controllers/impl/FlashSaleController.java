package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IFlashSaleController;
import com.khangse616.serverfashionrs.models.FlashSale;
import com.khangse616.serverfashionrs.models.dto.FlashSaleDTO;
import com.khangse616.serverfashionrs.services.IFlashSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1")
public class FlashSaleController implements IFlashSaleController {
    @Autowired
    private IFlashSaleService flashSaleService;

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
}
