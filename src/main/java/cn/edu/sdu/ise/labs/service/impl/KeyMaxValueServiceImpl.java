package cn.edu.sdu.ise.labs.service.impl;

import cn.edu.sdu.ise.labs.dao.KeyMaxValueMapper;
import cn.edu.sdu.ise.labs.model.KeyMaxValue;
import cn.edu.sdu.ise.labs.model.Token;
import cn.edu.sdu.ise.labs.service.KeyMaxValueService;
import cn.edu.sdu.ise.labs.utils.TokenContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author 王蓬禹
 * @date 2020/4/11 9:33
 */
@Service
public class KeyMaxValueServiceImpl implements KeyMaxValueService {
    @Autowired
    private KeyMaxValueMapper keyMaxValueMapper;
    private final int INIT_VALUE = 1;
    private final int MAX_VALUE = 9999;

    /**
     * 生成各业务表唯一编码
     *
     * @param keyPrefix 前缀
     * @return 唯一编码
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String generateBusinessCode(String keyPrefix) {
        Token token = TokenContextHolder.getToken();
        SimpleDateFormat dateFormat = (new SimpleDateFormat("yyMMdd"));
        String datePart = dateFormat.format(new Date());
        KeyMaxValue keyMaxValue = new KeyMaxValue();
        keyMaxValue.setCurrentValue(1);
        keyMaxValue.setKeyPrefix(keyPrefix);
        keyMaxValue.setTenantCode(token.getTenantCode());
        keyMaxValue.setUpdatedAt(new Date());
        keyMaxValueMapper.insertAndUpdate(keyPrefix, datePart, INIT_VALUE, token.getTenantCode());
        keyMaxValue = keyMaxValueMapper.getKeyValue(keyPrefix, datePart, token.getTenantCode());
        if (keyMaxValue.getCurrentValue() > MAX_VALUE) {
            throw new RuntimeException("业务主键序数值超过了9999，请明天再来操作该业务");
        }

        return String.format("%s%s%04d", keyPrefix, datePart, keyMaxValue.getCurrentValue());
    }


    /**
     * 生成场地表唯一编码
     *
     * @param keyPrefix 前缀
     * @return 唯一编码
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String generateRangeCode(String keyPrefix) {
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
        return String.format("%s%s", keyPrefix, ldt.format(dtf));
    }
}

