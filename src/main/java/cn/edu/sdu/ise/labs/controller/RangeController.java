package cn.edu.sdu.ise.labs.controller;

import cn.edu.sdu.ise.labs.dto.RangeDTO;
import cn.edu.sdu.ise.labs.dto.RangeQueryDTO;
import cn.edu.sdu.ise.labs.model.ResultContext;
import cn.edu.sdu.ise.labs.service.RangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 场地管理后端服务模块
 *
 * @author 王蓬禹
 * @date 2020/4/9 11:07
 */

@RestController

//控制器的路径 range

@RequestMapping("range")
public class RangeController {
    @Autowired
    private RangeService rangeService;

    @PostMapping("get")
    public ResultContext get(@RequestBody String rangeCode) {
        return ResultContext.returnSuccess(rangeService.get(rangeCode));
    }

    @PostMapping("list")
    public ResultContext list(@RequestBody RangeQueryDTO queryDTO) {
        return ResultContext.returnSuccess(rangeService.listByPage(queryDTO));
    }

    @PostMapping("add")
    public ResultContext add(@RequestBody RangeDTO rangeDTO) {
        return ResultContext.returnSuccess(rangeService.addRange(rangeDTO));
    }

    @PostMapping("update")
    public ResultContext update(@RequestBody RangeDTO rangeDTO) {
        return ResultContext.returnSuccess(rangeService.updateRange(rangeDTO));
    }

    @PostMapping("delete")
    public ResultContext delete(@RequestBody List<String> rangeCode) {
        rangeService.deleteByCodes(rangeCode);
        return ResultContext.returnSuccess(true);
    }
}
