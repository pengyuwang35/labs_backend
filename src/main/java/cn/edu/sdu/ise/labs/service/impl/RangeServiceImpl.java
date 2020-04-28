package cn.edu.sdu.ise.labs.service.impl;

import cn.edu.sdu.ise.labs.constant.PrefixConstant;
import cn.edu.sdu.ise.labs.dao.Competition_eventMapper;
import cn.edu.sdu.ise.labs.dao.RangeMapper;
import cn.edu.sdu.ise.labs.dto.EventDTO;
import cn.edu.sdu.ise.labs.dto.RangeDTO;
import cn.edu.sdu.ise.labs.dto.RangeQueryDTO;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.model.Range;
import cn.edu.sdu.ise.labs.model.Token;
import cn.edu.sdu.ise.labs.service.KeyMaxValueService;
import cn.edu.sdu.ise.labs.service.RangeService;
import cn.edu.sdu.ise.labs.service.utils.RangeUtils;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.utils.PageUtils;
import cn.edu.sdu.ise.labs.utils.TokenContextHolder;
import cn.edu.sdu.ise.labs.vo.RangeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王蓬禹
 * @date 2020/4/11 9:33
 */
@Service
public class RangeServiceImpl implements RangeService {
    @Autowired
    private RangeMapper rangeMapper;
    @Autowired
    private KeyMaxValueService keyMaxValueService;
    @Autowired
    private Competition_eventMapper competition_eventMapper;

    /**
     * 获取场地详情
     *
     * @param rangeCode 场地编码
     * @return 场地详情
     */
    @Override
    public List<RangeVO> get(String rangeCode) {
        Token token = TokenContextHolder.getToken();
        rangeCode = FormatUtils.makeFuzzySearchTerm(rangeCode);
        List<Range> rangeList = rangeMapper.get(rangeCode);
        return rangeList.stream()
                .map(item -> RangeUtils.convertToVO(item))
                .collect(Collectors.toList());
    }

    /**
     * 分页查询获取场地详情
     *
     * @param queryDTO 查询条件
     * @return 分页查询结果
     */
    @Override
    public Page<RangeVO> listByPage(RangeQueryDTO queryDTO) {
        if (queryDTO == null) {
            // 入口参数不能为null
            queryDTO = new RangeQueryDTO();
        }
        queryDTO.setRangeName(FormatUtils.makeFuzzySearchTerm(queryDTO.getRangeName()));
        queryDTO.setRangeLocation(FormatUtils.makeFuzzySearchTerm(queryDTO.getRangeLocation()));
        queryDTO.setRangeCode(FormatUtils.makeFuzzySearchTerm(queryDTO.getRangeCode()));
        queryDTO.setStatus(queryDTO.getStatus());
        // 调用dao接口，获取命中数量
        Integer size = rangeMapper.countNum(queryDTO);
        // 通过分页工具类获取数据
        PageUtils pageUtils = new PageUtils(queryDTO.getPage(), queryDTO.getPageSize(), size);
        Page<RangeVO> pageData = new Page<>(pageUtils.getPage(), pageUtils.getPageSize(), pageUtils.getTotal(), new ArrayList<>());
        if (size == 0) {
            // 没有命中，则返回空数据。
            return pageData;
        }
        List<Range> list = rangeMapper.list(queryDTO, pageUtils.getOffset(), pageUtils.getLimit());
        for (Range range : list) {
            pageData.getList().add(RangeUtils.convertToVO(range));
        }
        return pageData;
    }

    /**
     * 新建场地
     *
     * @param rangeDTO 场地入参
     * @return 场地编码
     */
    @Override
    public String addRange(RangeDTO rangeDTO) {
        // 拿到token
        Token token = TokenContextHolder.getToken();
        // 校验输入数据正确性
        RangeUtils.validateRange(rangeDTO);
        // 创建实体对象，用以保存到数据库
        Range range = new Range();
        // 将输入的字段全部复制到实体对象中
        BeanUtils.copyProperties(rangeDTO, range);
        //检验场地唯一性
        RangeQueryDTO queryDTO = new RangeQueryDTO();
        queryDTO.setPage(1);
        queryDTO.setPageSize(10);
        queryDTO.setRangeName(rangeDTO.getRangeName());
        Integer size = rangeMapper.countNum(queryDTO);
        Assert.isTrue(size == 0, "场地名称已存在");
        // 生成唯一标识的场地代码
        range.setRangeCode(keyMaxValueService.generateRangeCode(PrefixConstant.RANGE));
        // 将token相关信息填入range对象
        TokenContextHolder.formatInsertRange(range);
        range.setCreatedBy(token.getCreatedBy());
        // 生成日期
        Date date = new Date();
        range.setCreatedAt(date);
        range.setUpdatedAt(date);
        // 调用DAO方法保存到数据库表
        rangeMapper.insert(range);
        return range.getRangeCode();
    }

    /**
     * 更新场地数据
     *
     * @param rangeDTO 场地入参
     * @return 场地编码
     */
    @Override
    public String updateRange(RangeDTO rangeDTO) {
        // 校验输入数据正确性
        Token token = TokenContextHolder.getToken();
        RangeUtils.validateRange(rangeDTO);
        Assert.hasText(rangeDTO.getRangeCode(), "场地代码不能为空");

        Range range = rangeMapper.getByCode(rangeDTO.getRangeCode());
        BeanUtils.copyProperties(rangeDTO, range);

        range.setUpdatedBy(token.getUpdatedBy());
        Date date = new Date();
        range.setUpdatedAt(date);
        rangeMapper.updateByPrimaryKey(range);

        //检验场地唯一性
        RangeQueryDTO queryDTO = new RangeQueryDTO();
        queryDTO.setPage(1);
        queryDTO.setPageSize(10);
        queryDTO.setRangeName(rangeDTO.getRangeName());
        Integer size = rangeMapper.countNum(queryDTO);
        Assert.isTrue(size <= 1, "场地名称已存在");

        return range.getRangeCode();
    }


    /**
     * 根据编码列表，批量删除场地
     *
     * @param rangeCodes 编码列表
     */
    @Override
    public void deleteByCodes(List<String> rangeCodes) {
        Assert.notEmpty(rangeCodes, "场地编码列表不能为空");
        Token token = TokenContextHolder.getToken();
        //判断场地是否被比赛占用
        for (String rangeCode : rangeCodes) {
            EventDTO eventDTO = new EventDTO();
            eventDTO.setRangeCode(rangeCode);
            eventDTO.setStatus(3);
            Integer size = competition_eventMapper.countNum(eventDTO);
            Assert.isTrue(size != 0, "场地被比赛占用，不能删除");
        }
        rangeMapper.deleteByCodes(rangeCodes);
    }
}
