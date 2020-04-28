package cn.edu.sdu.ise.labs.service;

import cn.edu.sdu.ise.labs.dto.EventDTO;
import cn.edu.sdu.ise.labs.dto.RangeDTO;
import cn.edu.sdu.ise.labs.dto.RangeQueryDTO;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.model.Token;
import cn.edu.sdu.ise.labs.utils.TokenContextHolder;
import cn.edu.sdu.ise.labs.vo.RangeVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 *
 * @author 王蓬禹
 * @date 2020-4-11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("cn.edu.sdu.ise.labs.dao")
@Slf4j
@Data
public class RangeServiceTests {
    @Autowired
    private RangeService rangeService;

    @Test
    public void testGet() {
        initRangeToken();
        List<String> codeList = new ArrayList<>();
        codeList.add("001");
        List<RangeVO> list = rangeService.get("001");
        assert list.size() != 0;
    }

    @Test
    public void testListByPage() {
        initRangeToken();
        RangeQueryDTO queryDTO = new RangeQueryDTO();
        queryDTO.setPage(1);
        queryDTO.setPageSize(10);
        queryDTO.setRangeLocation("N5-348");
        Page<RangeVO> pageData = rangeService.listByPage(queryDTO);
        assert pageData.getList().size() > 0;
    }

    @Test
    public void testAdd() {
        initRangeToken();
        RangeDTO rangeDTO = new RangeDTO();
        rangeDTO.setRangeName("宿舍");
        rangeDTO.setRangeLocation("S1-625");
        rangeDTO.setStatus(1);
        rangeDTO.setCloseReason(null);
        rangeDTO.setDescription("崇新学堂");
        assert rangeService.addRange(rangeDTO) != null;
    }

    @Test
    public void testUpdate() {
        initRangeToken();
        RangeDTO rangeDTO = new RangeDTO();
        rangeDTO.setRangeCode("002");
        rangeDTO.setRangeName("场地");
        rangeDTO.setRangeLocation("N5-625");
        rangeDTO.setStatus(2);
        rangeDTO.setCloseReason(null);
        rangeDTO.setDescription("崇新学堂");
        assert rangeService.updateRange(rangeDTO) != null;
    }

    @Test
    public void testDelete() {
        initRangeToken();
        EventDTO eventDTO = new EventDTO();
        eventDTO.setStatus(2);
        List<String> rangeCodes = new ArrayList<>();
        rangeCodes.add("003");
        rangeService.deleteByCodes(rangeCodes);
    }

    private void initRangeToken() {
        Token token = new Token();
        token.setCreatedBy("王蓬禹");
        token.setUpdatedBy("王蓬禹");
        TokenContextHolder.setToken(token);
    }
}
