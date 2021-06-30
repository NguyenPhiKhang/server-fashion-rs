package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IOptionProductVarcharController;
import com.khangse616.serverfashionrs.models.OptionProductVarchar;
import com.khangse616.serverfashionrs.services.IOptionProductVarcharService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OptionProductVarcharController implements IOptionProductVarcharController {
    @Autowired
    private IOptionProductVarcharService optionProductVarcharService;

    @Override
    public List<OptionProductVarchar> getOptionByAttributeId(int optionId) {
        return optionProductVarcharService.findOptionByAttributeId(optionId);
    }
}
